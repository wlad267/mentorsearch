package com.bluementors;

import com.bluementors.mentor.Mentor;
import com.bluementors.mentor.MentorResource;
import com.bluementors.mentor.MentorService;
import com.bluementors.security.AppRoles;
import com.bluementors.security.jwt.JwtConfiguration;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MentorResourceIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private MentorService mentorService;

    @Autowired
    private MentorResource mentorResource;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    private String jwtToken;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {

        //this.mockMvc = MockMvcBuilders.standaloneSetup(mentorResource).build();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilters(this.springSecurityFilterChain).build();

        jwtToken = Jwts.builder()
                .setSubject("admin@mentorsearch.com")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtConfiguration.jwtSecret.getBytes())
                .claim("authorities", Arrays.asList(new SimpleGrantedAuthority(AppRoles.Names.USER))
                        .stream()
                        .map(s -> s.getAuthority())
                        .collect(Collectors.toList()))
                .compact();
    }

    //TODO: rework this one once the controller advice will be introduced
    @Test
    public void call_no_jct__authorization_failure() {
        try {
            this.mockMvc.perform(
                    get("/api/mentors/byUserId/123")
                            .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isForbidden());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(AuthenticationCredentialsNotFoundException.class);
        }
    }

    @Test
    public void fetch_mentor_by_userId() throws Exception {
        when(mentorService.fetchByUserId(123L)).thenReturn(new Mentor.Builder().build());

        this.mockMvc.perform(
                get("/api/mentors/byUserId/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .cookie(new Cookie(jwtConfiguration.jwtName, jwtToken))
        ).andExpect(status().isOk());
    }

}
