package br.com.compasso.uol.backend.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/compasso-uol/health")
public class HealthResource {

    @GetMapping
    public ResponseEntity<Object> checkStatus() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
