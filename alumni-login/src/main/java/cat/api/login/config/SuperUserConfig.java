package cat.api.login.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("security.superuser")
@Getter @Setter
public class SuperUserConfig {
	
	private String email;
	private List<String> roles;
	
}