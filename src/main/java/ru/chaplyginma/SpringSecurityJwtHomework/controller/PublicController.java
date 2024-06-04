package ru.chaplyginma.SpringSecurityJwtHomework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.AppResponse;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping
    public ResponseEntity<AppResponse> getPublic() {
        AppResponse response = new AppResponse("This is the public");
        return ResponseEntity.ok(response);
    }
}
