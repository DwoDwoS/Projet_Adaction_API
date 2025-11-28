package adaction.controllers;

import adaction.models.LoginRequest;
import adaction.models.Volunteer;
import adaction.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "${cors.allowed.origins}")
public class LoginController {
    private final LoginService loginService;

    public LoginController (LoginService loginService) {
        this.loginService = loginService;
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> Login (@RequestBody LoginRequest request){
        try {
            Volunteer volunteer = loginService.userAuthentification(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(volunteer);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
