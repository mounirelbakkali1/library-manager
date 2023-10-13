package com.hcl.liberaryManager.service;

import com.hcl.liberaryManager.dto.AuthResponse;
import com.hcl.liberaryManager.dto.LoginRequestDto;
import com.hcl.liberaryManager.dto.SignUpRequestDto;
import com.hcl.liberaryManager.entity.Role;
import com.hcl.liberaryManager.entity.User;
import com.hcl.liberaryManager.exception.NewSignUpException;
import com.hcl.liberaryManager.repository.UserRepository;
import com.hcl.liberaryManager.util.JwtUtils;
import com.hcl.liberaryManager.util.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtils jwtUtils ;
    private final AuthenticationManager authenticationManager ;
    private final UserDtoMapper userDtoMapper ;
    private final UserRepository userRepository ;
    private final PasswordEncoder encoder;


    public AuthResponse authenticate(LoginRequestDto requestDto) throws NewSignUpException {
        Authentication authentication ;
        try{
             authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.username(),requestDto.password()
                    )
            );
        }catch (Exception exception){
            throw  new NewSignUpException("Not matched with our records");
        }

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtUtils.generateToken(user);
        return AuthResponse
                .builder()
                .user(userDtoMapper.mapToUserDto(user))
                .accessToken(accessToken)
                .build();
    }

    public AuthResponse registerNewUser(SignUpRequestDto request) throws NewSignUpException {
        if (userRepository.existsByName(request.name()))
            throw new NewSignUpException(
                    "an other user has already signed up with this name " + request.username());
        if (userRepository.existsByUsername(request.username()))
            throw new NewSignUpException("Username already exist");

        User user = User.builder()
                .username(request.username())
                .password(encoder.encode(request.password()))
                .enabled(true)
                .role(Role.USER)
                .name(request.name())
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .user(userDtoMapper.mapToUserDto(user))
                .build();
    }
}
