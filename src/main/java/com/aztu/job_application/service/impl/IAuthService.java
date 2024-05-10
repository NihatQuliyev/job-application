package com.aztu.job_application.service.impl;

import com.aztu.job_application.model.dao.AuthenticationDetails;
import com.aztu.job_application.model.dto.request.AuthRequest;
import com.aztu.job_application.model.dto.response.AuthenticationResponse;
import com.aztu.job_application.model.entity.RefreshToken;
import com.aztu.job_application.model.entity.User;
import com.aztu.job_application.model.enums.Static;
import com.aztu.job_application.model.exception.ApplicationException;
import com.aztu.job_application.repository.RefreshTokenRepository;
import com.aztu.job_application.repository.UserRepository;
import com.aztu.job_application.service.AuthService;
import com.aztu.job_application.service.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.aztu.job_application.model.enums.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class IAuthService implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final ExceptionService exceptionService;

    @Value("${application.security.refresh-token-expiration}")
    private long tokenLifeTime;

    @Override
    public AuthenticationResponse authentication(AuthRequest dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        AuthenticationDetails authenticationDetails = (AuthenticationDetails) authentication.getDetails();
        long userId = authenticationDetails.getId();
        User user = findById(userId);
        String accessToken = jwtService.generateToken(dto.getUsername(), userId, getAuthorities(authentication.getAuthorities()));
        String refreshTokenUUID = generateUUID();

        Optional<RefreshToken> refreshTokenRepositoryByUser = refreshTokenRepository.findByUser(user);
        refreshTokenRepositoryByUser.ifPresentOrElse(
                refreshToken -> ifHasExistUpdate(user, refreshToken, refreshTokenUUID),
                () -> ifNotExistCreate(user, refreshTokenUUID)
        );

        return new AuthenticationResponse(accessToken, refreshTokenUUID);
    }

    @Override
    @Transactional
    public AuthenticationResponse accessTokensByRefreshToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByTokenAndRevoked(refreshToken, false).orElseThrow(() -> new RuntimeException("user not found by given token: " + refreshToken));
        User user = token.getUser();

        isTokenExpired(token);
        userByGivenTokenElseThrow(token);

        String accessToken = jwtService.generateToken(user.getUsername(), user.getId(), getAuthorities(user.getAuthorities()));
        String refreshTokenUUID = generateUUID();

        ifHasExistUpdate(user, token, refreshTokenUUID);


        return new AuthenticationResponse(accessToken, refreshTokenUUID);
    }

    private void isTokenExpired(RefreshToken token) {
        if (token.getTableDetail().getUpdateAt().getNano() < LocalDateTime.now().getNano()) {
            throw new ApplicationException(exceptionService.exceptionResponse(TOKEN_EXPIRED_EXCEPTION.getMessage(), TOKEN_EXPIRED_EXCEPTION.getHttpStatus()));
        }
    }

    private void userByGivenTokenElseThrow(RefreshToken token) {
        if (token.getUser() == null) throw new ApplicationException(exceptionService.exceptionResponse(
                USER_NULL_POINTER_EXCEPTION.getMessage(),
                USER_NULL_POINTER_EXCEPTION.getHttpStatus()));
    }

    private Map<String, Object> getAuthorities(Collection<? extends GrantedAuthority> grantedAuthorities) {
        HashMap<String, Object> extraClaims = new HashMap<>();

        List<String> authorities = grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        extraClaims.put(Static.KEY_AUTHORITIES, authorities);
        return extraClaims;
    }

    private User findById(long id) {
        return userRepository.findById(id).orElseThrow(() ->  new ApplicationException(exceptionService.exceptionResponse(
                ID_NOT_FOUND_EXCEPTION.getMessage(),
                ID_NOT_FOUND_EXCEPTION.getHttpStatus())));
    }

    protected void ifNotExistCreate(User user, String refreshTokenUUID) {

        userNotBeNull(user);

        RefreshToken refreshToken = generateRefreshToken(user, refreshTokenUUID);
        refreshTokenRepository.save(refreshToken);

    }

    protected void ifHasExistUpdate(User user, RefreshToken refreshToken, String refreshTokenUUID) {
        userNotBeNull(user);

        if (refreshToken == null) {
            throw new RuntimeException();
        }

        refreshToken.setToken(refreshTokenUUID);
        refreshTokenRepository.save(refreshToken);

    }

    private void userNotBeNull(User user) {
        if (user == null) {
            throw new RuntimeException("user must not be null");
        }
    }

    private RefreshToken generateRefreshToken(User user, String refreshTokenUUID) {
        return RefreshToken.builder()
                .token(refreshTokenUUID)
                .lifeTime(tokenLifeTime)
                .user(user)
                .build();
    }


    private String generateUUID() {
        return UUID.randomUUID().toString();
    }



    public User getAuthenticatedUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByEmailAndIsEnableTrue(username).orElseThrow();
    }
}
