package ru.chaplyginma.SpringSecurityJwtHomework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.AppResponse;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ResponseEntity<AppResponse> getUser(Principal principal) {
        AppResponse response = new AppResponse("Hello, user '%s'".formatted(principal.getName()));
        return ResponseEntity.ok(response);
    }
}
