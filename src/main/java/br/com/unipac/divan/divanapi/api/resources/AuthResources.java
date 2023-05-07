package br.com.unipac.divan.divanapi.api.resources;


import br.com.unipac.divan.divanapi.api.dto.request.authentication.AuthenticationRequest;
import br.com.unipac.divan.divanapi.api.dto.request.authentication.SignupRequest;
import br.com.unipac.divan.divanapi.api.dto.request.user.TokenRefreshRequest;
import br.com.unipac.divan.divanapi.api.dto.response.authentication.AuthenticationResponse;
import br.com.unipac.divan.divanapi.api.dto.response.authentication.SignupResponse;
import br.com.unipac.divan.divanapi.api.dto.response.user.TokenRefreshResponse;
import br.com.unipac.divan.divanapi.api.mapper.SigninMapper;
import br.com.unipac.divan.divanapi.api.mapper.SignupMapper;
import br.com.unipac.divan.divanapi.exception.TokenRefreshException;
import br.com.unipac.divan.divanapi.model.entities.user.RefreshToken;
import br.com.unipac.divan.divanapi.model.entities.user.User;
import br.com.unipac.divan.divanapi.model.service.AuthService;
import br.com.unipac.divan.divanapi.model.service.RefreshTokenService;
import br.com.unipac.divan.divanapi.util.JWTUtil;
import br.com.unipac.divan.divanapi.util.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/v1/auth")
public class AuthResources {

    @Autowired
    private SignupMapper signupMapper;
    @Autowired
    private SigninMapper signinMapper;
    @Autowired
    private AuthService authService;
    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> authenticateUser(
            @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        User from = signinMapper.from(authenticationRequest);
        Optional<User> validate = authService.validate(from);
        if (validate.isPresent()) {
            AuthenticationResponse authenticationResponse = signinMapper.to(validate.get());
            authenticationResponse.setAccessToken(JWTUtil.createToken(validate.get().getEmail()));
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(validate.get().getId());
            authenticationResponse.setRefreshToken(refreshToken.getToken());
            return ResponseEntity.ok(authenticationResponse);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successful");
    }

    /**
     * Register response entity.
     *
     * @param authenticationRequest the account request
     * @return the response entity
     */
    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest authenticationRequest) throws Exception {
        User from = signupMapper.from(authenticationRequest);
        Optional<User> registered = authService.register(from);

        if (registered.isPresent()) {
            URI location = RestUtils.getUri(registered.get().getId());

            SignupResponse signupResponse = signupMapper.toSignup(registered.get());
            return ResponseEntity.created(location).body(signupResponse);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Register response entity.
     *
     * @param request the account request
     * @return the response entity
     */
    @PostMapping("/refreshtoken")
    @ResponseBody
    public ResponseEntity<TokenRefreshResponse> refreshtoken(@RequestBody TokenRefreshRequest request) throws Exception {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = JWTUtil.createToken(user.getEmail());
                    TokenRefreshResponse tokenRefreshResponse = TokenRefreshResponse
                            .builder()
                            .accessToken(token)
                            .refreshToken(requestRefreshToken)
                            .build();
                    return ResponseEntity.ok(tokenRefreshResponse);
                }).orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

}
