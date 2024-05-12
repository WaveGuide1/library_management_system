package io.barth.library_management_system.authentication;

import io.barth.library_management_system.exception.AuthenticationFailedException;
import io.barth.library_management_system.exception.UserAlreadyRegisterException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final UserAuthenticationService userAuthenticationService;

    public AuthenticationController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    // Registration endpoint
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody User request){
        try {
            return ResponseEntity.ok(userAuthenticationService.register(request));
        } catch (UserAlreadyRegisterException e){
            throw e;
        }catch (Exception e){
            throw new AuthenticationFailedException("Invalid credentials");
        }

    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login (@RequestBody User request){
        try {
            return ResponseEntity.ok(userAuthenticationService.authenticate(request));
        } catch (Exception e){
            throw new AuthenticationFailedException("Invalid credentials");
        }

    }
}
