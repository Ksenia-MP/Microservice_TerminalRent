package com.example.auth_service.controllers;


import com.example.auth_service.dtos.AuthenticationRequest;
import com.example.auth_service.dtos.AuthenticationResponse;
import com.example.auth_service.dtos.MessageObject;
import com.example.auth_service.entities.User;
import com.example.auth_service.services.JwtService;
import com.example.auth_service.services.JwtUserDetailsService;
import com.example.auth_service.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
            final String jwt = jwtService.createJwtToken(authenticationRequest);
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }
        catch (Exception ex){
            return ResponseEntity.status(401).body(new MessageObject("Не существует пользователя с таким логином и паролем"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body(new MessageObject("Имя пользователя занято"));
        }
        userService.save(user);
        return ResponseEntity.ok(new MessageObject("Пользователь успешно зарегистрирован"));
    }

    @PostMapping("/verify")
    public ResponseEntity<?>  getUserDetails(@RequestBody String username){
        if(username == null){
            return ResponseEntity.status(404).body(null);
        }
        User user = userService.findByUsername(username);
        if(user == null){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(user);
    }
}