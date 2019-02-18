package com.bluementors;

import com.bluementors.security.aa.AARequest;
import com.bluementors.security.aa.AAResource;
import com.bluementors.security.jwt.JwtConfiguration;
import com.bluementors.security.jwt.JwtTokenProvider;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.bluementors.AppCodes.APP_ERROR;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AAResourceTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AAResource aaResource;

    @MockBean
    private UserService userService;

    @Before
    public void setpup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(aaResource).build();
    }

    @Test
    public void login__invalid_credentials() throws Exception {
        AARequest aaRequest = new AARequest();
        aaRequest.setEmail("a@b.ro");
        aaRequest.setPassword("123456");

        this.mockMvc.perform(
                post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(aaRequest))
        ).andExpect(status().is(APP_ERROR));
    }

    @Test
    public void login_valid_user__return_jwt_token() throws Exception {
        AARequest aaRequest = new AARequest();
        aaRequest.setEmail("user@bluementors.com");
        aaRequest.setPassword("123456");

        when(userService.findByEmail(aaRequest.getEmail()))
                .thenReturn(new User.Builder()
                        .email("user@bluementors.com")
                        .authenticationString(passwordEncoder.encode("123456"))
                        .build()
                );

        when(jwtTokenProvider.generateToken(any(Authentication.class))).thenReturn("THE_TOKEN");

        this.mockMvc.perform(
                post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(aaRequest))
        )
                .andExpect(status().isOk())
                .andExpect(cookie().value(jwtConfiguration.jwtTokenName, "THE_TOKEN"));

    }

    @Test
    public void test_logout_invalid_credentials() {

    }

    @Test
    public void test_logout_valid_user() {

    }

}
