package jeremic.petar.fon.springgames.controller;

import jeremic.petar.fon.springgames.dto.LoginRequestDto;
import jeremic.petar.fon.springgames.dto.UserDto;
import jeremic.petar.fon.springgames.exception.AuthenticationFailedException;
import jeremic.petar.fon.springgames.security.jwt.JWTTokenProvider;
import jeremic.petar.fon.springgames.security.user.UserPrincipal;
import jeremic.petar.fon.springgames.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"","auth"})
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JWTTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, JWTTokenProvider jwtTokenProvider) {
        this.authenticationService = authenticationService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto){

        try {
            UserDto result = authenticationService.login(loginRequestDto);
            HttpHeaders headers = getJwtHeader(authenticationService.getUserPrincipal(loginRequestDto));
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        }catch (Exception ex){
            throw new AuthenticationFailedException("Login failed. Check your username and/or password.");
        }

    }


    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Jwt-token", jwtTokenProvider.generateJwtToken(user));
        return headers;
    }

}
