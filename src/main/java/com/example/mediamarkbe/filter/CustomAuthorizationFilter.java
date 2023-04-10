package com.example.mediamarkbe.filter;

import com.example.mediamarkbe.model.User;
import com.example.mediamarkbe.security.UserPrincipal;
import com.example.mediamarkbe.util.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/login")){
            filterChain.doFilter(request,response);
        }else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){

                String token = authorizationHeader.substring("Bearer ".length());
                JwtTokenUtil.validateAccessToken(token);

                String[] subject = JwtTokenUtil.getSubject(token).split(",");
                String username = subject[1];
                Long id = Long.valueOf(subject[0]);
                List<String> roles= (List<String>) JwtTokenUtil.getRoles(token);

//                Set authorities and roles
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                roles.stream().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
                UserPrincipal userPrincipal = UserPrincipal.create(new User(id,username));

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal,null,authorities);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                filterChain.doFilter(request,response);
            }else{
                filterChain.doFilter(request,response);
            }
        }


    }
}
