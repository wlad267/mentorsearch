package com.bluementors.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Value("${app.jwtName}")
    private String jwtName;

    @Value("${app.AppAuthSchema}")
    private String authSchema;

    @Value("${app.jwtSecret}")
    private String jwtSecret;



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.info("checking JWT for " + httpServletRequest.getRemoteAddr());

        String token = null;

        // 1. check the token coockie
        for (Cookie cookie: httpServletRequest.getCookies()){
            if (jwtName.equals(cookie.getName())){
                token = cookie.getValue();
                break;
            }
        }

        if (isNull(token)){
            // 2. if not cookie try to get the authentication header as supposed to be passed in the authentication header
            String header = httpServletRequest.getHeader(jwtName);
            // 3. validate the header and check the prefix
            if(header == null || !header.startsWith(authSchema)) {

                // If there is no token provided and hence the user won't be authenticated.
                // It's Ok. Maybe the user accessing a public path or asking for a token.

                filterChain.doFilter(httpServletRequest, httpServletResponse);
                //if not valid, go to the next filter.
                return;
            }
            token = header.replace(authSchema, "");
        }



        try {	// exceptions might be thrown in creating the claims if for example the token is expired

            // 4. Validate the token
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            if(username != null) {
                @SuppressWarnings("unchecked")
                List<String> authorities = (List<String>) claims.get("authorities");

                // 5. Create auth object
                // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
                // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

                // 6. Authenticate the user
                // Now, user is authenticated
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
            // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
            SecurityContextHolder.clearContext();
        }


        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
