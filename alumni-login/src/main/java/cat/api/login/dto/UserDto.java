package cat.api.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor // default constructor needed for JSON Parsing
@AllArgsConstructor
@Getter @Setter
public class UserDto {
	
	private String uuid;
	private String email;
	private List<String> roles;
	
	public UserDto(AuthenticationRequest request) {
		email = request.getEmail();
	}
	
	public UserDto(String email, List<String> roles) {
		this.email = email;
		this.roles = roles;
	}
	
}