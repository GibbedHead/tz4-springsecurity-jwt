package ru.chaplyginma.SpringSecurityJwtHomework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.AppResponse;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.AppErrorResponse;

import java.security.Principal;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin controller", description = "Endpoint testing access of authenticated user with ADMIN role")
public class AdminController {

    @Operation(summary = "Get admin welcome message", description = "Returns welcome message using current admin user name",
            responses = {
                    @ApiResponse(description = "Admin welcome message",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "message": "Hello, admin 'admin'"
                                            }""")
                            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppErrorResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "status": 401,
                                                "message": "Access token expired",
                                                "timestamp": "2024-06-04T22:40:46.7924577"
                                            }""")
                            )
                    )
            })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<AppResponse> getAdmin(Principal principal) {
        AppResponse response = new AppResponse("Hello, admin '%s'".formatted(principal.getName()));
        return ResponseEntity.ok(response);
    }
}
