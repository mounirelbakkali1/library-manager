package com.hcl.liberaryManager.controller;

import com.hcl.liberaryManager.dto.*;
import com.hcl.liberaryManager.exception.NewSignUpException;
import com.hcl.liberaryManager.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto requestDto) {
        try {
            AuthResponse authenticate = authService.authenticate(requestDto);
            return ResponseEntity.ok().body(authenticate);
        } catch (NewSignUpException exception) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .status("failed")
                            .errors(List.of(ErrorDto.builder().message(exception.getMessage()).build())));
        }
    }

    @PostMapping("signup")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid SignUpRequestDto requestDto) throws NewSignUpException {
        return ResponseEntity.ok(authService.registerNewUser(requestDto));
    }

}
