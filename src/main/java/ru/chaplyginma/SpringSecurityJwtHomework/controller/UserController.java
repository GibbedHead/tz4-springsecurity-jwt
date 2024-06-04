package ru.chaplyginma.SpringSecurityJwtHomework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.AppResponse;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.AppErrorResponse;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@Tag(name = "User controller", description = "Endpoint testing access of authenticated user")
public class UserController {

    @Operation(summary = "Get user welcome message", description = "Returns welcome message using current user name",
            responses = {
                    @ApiResponse(description = "User welcome message",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "message": "Hello, user 'user'"
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
    @GetMapping
    public ResponseEntity<AppResponse> getUser(Principal principal) {
        AppResponse response = new AppResponse("Hello, user '%s'".formatted(principal.getName()));
        return ResponseEntity.ok(response);
    }
}
