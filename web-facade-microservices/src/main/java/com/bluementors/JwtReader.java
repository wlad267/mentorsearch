package com.bluementors;

import com.bluementors.security.jwt.JwtConfiguration;
import com.bluementors.security.jwt.JwtRequestExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtReader {
    @Autowired
    private JwtRequestExtractor jwtRequestExtractor;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    public String parseAuthentication(HttpServletRequest httpServletRequest) {
        return jwtConfiguration.jwtAuthSchema
                .concat(" ")
                .concat(jwtRequestExtractor.readToken(httpServletRequest));
    }
}
