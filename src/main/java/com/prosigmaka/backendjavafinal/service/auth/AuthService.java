package com.prosigmaka.backendjavafinal.service.auth;

import com.prosigmaka.backendjavafinal.entity.auth.UserEntity;
import com.prosigmaka.backendjavafinal.helper.Role;
import com.prosigmaka.backendjavafinal.model.auth.AuthRequestModel;
import com.prosigmaka.backendjavafinal.model.auth.AuthResponseModel;
import com.prosigmaka.backendjavafinal.model.auth.RegisterUserModel;
import com.prosigmaka.backendjavafinal.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseModel registerUser(RegisterUserModel request){
        var user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .date(request.getDate())
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponseModel.builder()
                .token(jwtToken)
                .build();
    }
    public AuthResponseModel registerAdmin(RegisterUserModel register){
        var user = UserEntity.builder()
                .firstName(register.getFirstName())
                .lastName(register.getLastName())
                .email(register.getEmail())
                .password(passwordEncoder.encode(register.getPassword()))
                .role(Role.ADMIN)
                .date(register.getDate())
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponseModel.builder()
                .token(jwtToken)
                .build();
    }
    public AuthResponseModel authentication(AuthRequestModel requestModel){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestModel.getEmail(),
                        requestModel.getPassword()
                )
        );
        var user = userRepo.findByEmail(requestModel.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponseModel.builder()
                .token(jwtToken)
                .build();
    }
}
