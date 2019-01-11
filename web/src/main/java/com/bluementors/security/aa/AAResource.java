package com.bluementors.security.aa;

import com.bluementors.security.AppRoles;
import com.bluementors.security.jwt.JwtTokenProvider;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.bluementors.AppCodes.APP_ERROR;
import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/auth")
public class AAResource {

    private Logger logger = LoggerFactory.getLogger(AAResource.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Value("${app.jwtName}")
    private String jwtName;

    @PostMapping("login")
    //@RolesAllowed({AppRoles.Names.ADMIN, AppRoles.Names.USER, AppRoles.Names.MENTOR})
    public ResponseEntity login(@RequestBody AARequest aaRequest) {
        logger.info("login requested " + aaRequest.getEmail());

        User user = userService.findUserByEmail(aaRequest.getEmail());
        if (isNull(user)) {
            return ResponseEntity.status(APP_ERROR).build();
        }


        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        aaRequest.getEmail(),
                        aaRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Authorization", "Bearer " + jwt);

        headers.add("Set-Cookie",jwtName + "=" + jwt + "; Path=/");
        return new ResponseEntity(user, headers, HttpStatus.OK);
    }

    @GetMapping("logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.ok().build();
    }

}
