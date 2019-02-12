package mentor.search.com.mentorsearch;

import com.bluementors.security.aa.AAResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(AAResource.class)
//@SpringBootConfiguration
@Configuration
public class AAResourceTest {

    @Test
    public void test_login_invalid_credentials() {

    }

    @Test
    public void test_login_valid_user() {

    }

    @Test
    public void test_logout_invalid_credentials() {

    }

    @Test
    public void test_logout_valid_user() {

    }

}
