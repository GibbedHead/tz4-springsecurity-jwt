package ru.chaplyginma.SpringSecurityJwtHomework.secutity;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.AppErrorResponse;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        AppErrorResponse appErrorResponse = new AppErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Invalid access token. Exception: '%s'".formatted(authException.getMessage())
        );
        String jsonResponse = objectMapper.writeValueAsString(appErrorResponse);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        response.flushBuffer();
    }
}
