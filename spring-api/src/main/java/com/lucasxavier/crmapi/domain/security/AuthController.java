package com.lucasxavier.crmapi.domain.security;

import com.lucasxavier.crmapi.domain.repositories.UserRepository;
import com.lucasxavier.crmapi.domain.security.jwt.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider,
                          UserRepository userRepository){
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }


    @ApiOperation(value ="Add new Manufacturer", tags = {"Montadora"})
    @PostMapping(value = "/signin",produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<Map<Object, Object>> signin(@RequestBody AccountCredentialsDTO credentials) {
        try {
            var username = credentials.getUsername();
            var password = credentials.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUserName(username)
                    .orElseThrow(()-> new UsernameNotFoundException(username));
            var token = "";

            token = tokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", user.getUsername());
            model.put("token", token);

            return ResponseEntity.ok(model);


        } catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username/ password.");
        }
    }



}
