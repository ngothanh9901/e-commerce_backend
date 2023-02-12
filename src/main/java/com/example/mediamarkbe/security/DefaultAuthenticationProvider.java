package com.example.mediamarkbe.security;


import com.example.mediamarkbe.common.enumeration.exception.UnauthorizedException;
import com.example.mediamarkbe.model.User;
import com.example.mediamarkbe.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * This class to build custom authentication provider
 *
 * @author trungnt9
 */
@Component("defaultAuthProvider")
@Primary
@Slf4j
public class DefaultAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailServices;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public DefaultAuthenticationProvider(UserDetailsService userDetailServices,
                                         UserRepository userRepository,
                                         PasswordEncoder passwordEncoder) {
        this.userDetailServices = userDetailServices;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = Objects.toString(authentication.getCredentials(), null);
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            throw new UnauthorizedException("Wrong username or password")
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .displayMessage("auth.wrong_credentials");
        }
        UserPrincipal userPrincipal = (UserPrincipal) userDetailServices.loadUserByUsername(name);
        if (Objects.nonNull(userPrincipal)) {
            User user = userPrincipal.getUser();
            if (user.getLoginFailedTimes() > 5) {
                throw new UnauthorizedException("Login failed too many")
                        .displayMessage("auth.login_failed_too_many");
            }
            if (passwordEncoder.matches(password, userPrincipal.getPassword())) {
                user.setLoginFailedTimes(0);
                if (!user.getLoginTimes().equals(0)) {
                    user.setLoginTimes(user.getLoginTimes() + 1);
                }
                return new UsernamePasswordAuthenticationToken(userPrincipal, password, userPrincipal.getAuthorities());
            } else {
                user.setLoginFailedTimes(user.getLoginFailedTimes() + 1);
            }
            userRepository.save(user);
        }
        throw new UnauthorizedException("Wrong password")
                .code(HttpStatus.UNAUTHORIZED.value())
                .displayMessage("auth.wrong_credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
