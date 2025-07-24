package com.ali.taskflow.shared.filter;

import com.ali.taskflow.shared.service.JwtService;
import com.ali.taskflow.user.entity.SecurityUser;
import com.ali.taskflow.user.projection.UserDetailProjection;
import com.ali.taskflow.user.repository.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final IUserRepository userRepository;

    public JWTAuthenticationFilter(JwtService jwtService, IUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtService.isTokenValid(token)){
            filterChain.doFilter(request,response);
            return;
        }


        String username = jwtService.getUsername(token);
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetailProjection user = this.userRepository.findUserWithUserDetail(username)
                    .orElseThrow();
            SecurityUser securityUser = new SecurityUser(user);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    securityUser,null,null
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);

    }
}
