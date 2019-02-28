package com.bluementors.security.aa;

import com.bluementors.security.AARequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/auth")
public class AAResource {

    private Logger logger = LoggerFactory.getLogger(AAResource.class);

    @Autowired
    private AAServiceFeignClient aaServiceFeignClient;


    @PostMapping("login")
    public ResponseEntity login(@RequestBody AARequest aaRequest) {
        logger.info("login requested " + aaRequest.getEmail());
        return aaServiceFeignClient.login(aaRequest);
    }

    @GetMapping("logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        return aaServiceFeignClient.logout();
    }

}
