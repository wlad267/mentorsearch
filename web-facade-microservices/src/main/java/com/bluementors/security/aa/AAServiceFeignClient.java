package com.bluementors.security.aa;

import com.bluementors.security.AARequest;
import com.bluementors.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("mentor-search")
public interface AAServiceFeignClient {

    @PostMapping("/mentor-search/api/auth/login")
    ResponseEntity<User> login(@RequestBody AARequest aaRequest);

    @GetMapping("/mentor-search/api/auth/logout")
    ResponseEntity logout();
}
