package cinema.controller;

import cinema.exception.AuthenticationException;
import cinema.model.User;
import cinema.model.dto.request.UserLoginRequestDto;
import cinema.model.dto.request.UserRequestDto;
import cinema.model.dto.response.JwtResponseDto;
import cinema.model.dto.response.UserResponseDto;
import cinema.security.jwt.JwtProvider;
import cinema.service.AuthenticationService;
import cinema.service.mapper.ResponseDtoMapper;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final ResponseDtoMapper<UserResponseDto, User> userDtoResponseMapper;
    private final JwtProvider jwtProvider;

    public AuthenticationController(AuthenticationService authService,
                                    ResponseDtoMapper<UserResponseDto, User> userDtoResponseMapper,
                                    JwtProvider jwtProvider) {
        this.authService = authService;
        this.userDtoResponseMapper = userDtoResponseMapper;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(@RequestBody @Valid UserRequestDto requestDto) {
        User user = authService.register(requestDto.getEmail(), requestDto.getPassword());
        return userDtoResponseMapper.mapToDto(user);
    }

    @PostMapping("/login")
    public JwtResponseDto login(@RequestBody @Valid UserLoginRequestDto loginDto)
            throws AuthenticationException {
        User user = authService.login(loginDto.getEmail(), loginDto.getPassword());
        String generatedJwt = jwtProvider.generateJwt(user.getEmail());
        return jwtProvider.generateResponseEntity(generatedJwt);
    }
}
