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

@RestController
@RequestMapping("/public")
@Tag(name = "Public controller", description = "Endpoint testing unauthorized access")
public class PublicController {

    @Operation(summary = "Get public welcome message", description = "Returns sample welcome message",
            responses = {
                    @ApiResponse(description = "Public welcome message",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "message": "Hello unauthorized user"
                                            }""")
                            ))
            })
    @GetMapping
    public ResponseEntity<AppResponse> getPublic() {
        AppResponse response = new AppResponse("Hello unauthorized user");
        return ResponseEntity.ok(response);
    }
}
