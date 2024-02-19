package com.pear.digitallibrary.controller;

import com.pear.digitallibrary.domain.Book;
import com.pear.digitallibrary.service.BookRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRegistrationService bookRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerBook(@RequestBody Book book) {
        try {
            bookRegistrationService.registerBook(book);
            return ResponseEntity.ok("Book registered successfully");
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ISBN: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error registering book: " + e.getMessage());
        }
    }
}
