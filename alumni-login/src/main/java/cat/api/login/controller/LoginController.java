package cat.api.login.controller;


import cat.api.login.dto.AuthenticationRequest;
import cat.api.login.dto.AuthenticationResponse;
import cat.api.login.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/businessassistantbcn/api/v1")
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;
	
	@GetMapping("/test")
	public String test() {
		log.info("** Saludos desde el logger **");
		
		return "Hello from BusinessAssistant Barcelona!!!";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			loginService.authenticate(authenticationRequest);
		} catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		String jwt = loginService.generateToken();
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}