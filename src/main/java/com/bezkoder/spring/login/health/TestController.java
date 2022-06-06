package com.bezkoder.spring.login.health;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/actuator/")
public class TestController {
    @GetMapping()
    public String healthCheck() {
        return "App is running.";
    }

    @GetMapping("deployment")
    public String healthCheckDeployment() {
        return "Deployment is ok";
    }
}