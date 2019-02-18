package com.bluementors.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private static final String NULL_TOKEN = null;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    private List<Predicate<HttpServletRequest>> permitted = new ArrayList<>();

    public JwtAuthenticationFilter(String[] permitted) {
        for (String uri : permitted) {
            this.permitted.add(
                    (request -> new AntPathRequestMatcher(uri).matches(request)));
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.info("checking JWT for " + httpServletRequest.getRemoteAddr());

        // 1. if permitted, go to the next filter.
        if (isResourcePermitted(httpServletRequest)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // 2. check the token cookie
        String token = this.readCookieToken(httpServletRequest);

        // 3. if no cookie than maybe header
        if (isNull(token)) {
            token = this.readHeaderToken(httpServletRequest);
            if (isNull(token)) {
                // If there is no token provided and hence the user won't be authenticated.
                // It's Ok. Maybe the user accessing a public path or asking for a token.
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                //if not valid, go to the next filter.
                return;
            }
        }


        // 4.1 Validate the token
        UsernamePasswordAuthenticationToken auth = tokenProvider.springAuthToken(token);

        // 4.2 Authenticate the user
        // Now, user is authenticated
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    private boolean isResourcePermitted(HttpServletRequest request) {
        return permitted.stream().filter(p -> p.test(request)).findFirst().isPresent();
    }

    private String readCookieToken(HttpServletRequest httpServletRequest) {
        if (isNull(httpServletRequest.getCookies()) || httpServletRequest.getCookies().length == 0) {
            return NULL_TOKEN;
        }

        for (Cookie cookie : httpServletRequest.getCookies()) {
            if (jwtConfiguration.jwtTokenName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return NULL_TOKEN;
    }

    private String readHeaderToken(HttpServletRequest httpServletRequest) {
        // 3.1 if not cookie try to get the authentication header as supposed to be passed in the authentication header
        String header = httpServletRequest.getHeader(jwtConfiguration.jwtTokenName);
        // 3.2 validate the header and check the prefix
        if (header == null || !header.startsWith(jwtConfiguration.jwtAuthSchema)) {

            // If there is no token provided and hence the user won't be authenticated.
            // It's Ok. Maybe the user accessing a public path or asking for a token.
            return NULL_TOKEN;
        }

        return header.replace(jwtConfiguration.jwtAuthSchema, "");
    }

}
