package io.barth.library_management_system.authentication;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import io.barth.library_management_system.utility.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {
    @Mock
    private UserAuthenticationService userAuthenticationService;
    @Mock
    private Role role;
    @InjectMocks
    private AuthenticationController authenticationController;
    @Test public void testRegister() {
        User user = new User(1L, "foo", "moo", "testUser", "password", role);
        AuthenticationResponse response = new AuthenticationResponse("token");
        when(userAuthenticationService.register(user)).thenReturn(response);
        ResponseEntity<AuthenticationResponse> entity = authenticationController.register(user);
        verify(userAuthenticationService).register(user);
        // Verify the response
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(response, entity.getBody());
    }

    @Test
    public void testLogin() {
        User user = new User(1L, "foo", "moo", "testUser", "password", role);
        AuthenticationResponse response = new AuthenticationResponse("token");
        when(userAuthenticationService.authenticate(user)).thenReturn(response);
        ResponseEntity<AuthenticationResponse> entity = authenticationController.login(user);
        verify(userAuthenticationService).authenticate(user);
        // Verify the response
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(response, entity.getBody());
    }
}
