package cat.api.login.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("security.testuser")
@Getter @Setter
public class TestUserConfig {
	
	private String email;
	private String password;
	private List<String> roles;
	
}