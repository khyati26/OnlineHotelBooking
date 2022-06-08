package com.narola.spring.SpringMVC_DEMO1;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BookController {

    @GetMapping(name = "Test API",value = "/hello")
    public ResponseEntity<String> testHello() {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/addbook")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        return ResponseEntity.ok("Book added");
    }

}
