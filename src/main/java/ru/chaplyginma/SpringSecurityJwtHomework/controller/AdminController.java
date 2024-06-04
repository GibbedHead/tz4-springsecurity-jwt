package ru.chaplyginma.SpringSecurityJwtHomework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.AppResponse;

import java.security.Principal;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<AppResponse> getAdmin(Principal principal) {
        AppResponse response = new AppResponse("Hello, admin '%s'".formatted(principal.getName()));
        return ResponseEntity.ok(response);
    }
}
