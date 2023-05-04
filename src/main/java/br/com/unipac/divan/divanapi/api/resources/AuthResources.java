package br.com.unipac.divan.divanapi.api.resources;


import br.com.unipac.divan.divanapi.api.dto.request.login.AuthenticationRequest;
import br.com.unipac.divan.divanapi.model.service.AuthService;
import br.com.unipac.divan.divanapi.security.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1/auth")
public class AuthResources {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        /*Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword())
        );*/

        /*SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);
        LoginResponse.builder().userId().build();

        return ResponseEntity.ok(new AuthenticationResponse(jwt));*/
        authService.validate(authenticationRequest);
        return null;
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successful");
    }
}
