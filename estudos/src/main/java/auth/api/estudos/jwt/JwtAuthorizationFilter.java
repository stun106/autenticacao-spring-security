package auth.api.estudos.jwt;

import auth.api.estudos.service.exception.ForbiddenExeceptionHandle;
import auth.api.estudos.service.exception.UnnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    final String token = request.getHeader(JwtUtils.JWT_AUTHORIZATION);

    if (token == null || !token.startsWith(JwtUtils.JWT_BEARER)) {
        log.info("Jwt nulo, Vazio ou não iniciado como bearer...");
        filterChain.doFilter(request,response);
        throw new ForbiddenExeceptionHandle( "Jwt nulo, Vazio ou não iniciado como bearer..." );
    }

    if (!JwtUtils.isTokenValid(token)) {
        log.info("Token inválido ou expirado...");
        filterChain.doFilter(request,response);
        throw new ForbiddenExeceptionHandle( "token inválido ou expirado..." );
    }

    String userEmail = JwtUtils.getUserEmailFromToken(token);

    toAuthenticate(request, userEmail);

    filterChain.doFilter(request,response);

    }

    private void toAuthenticate(HttpServletRequest request, String username) {
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authenticationToken =
                UsernamePasswordAuthenticationToken
                        .authenticated(userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
