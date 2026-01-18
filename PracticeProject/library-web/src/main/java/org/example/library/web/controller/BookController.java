package org.example.library.web.controller;

import jakarta.validation.Valid;
import org.example.library.api.dto.request.CreateBookRequest;
import org.example.library.api.dto.response.BookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    // POST /books  → LIBRARIAN
    @PostMapping
    public ResponseEntity<BookResponse> createBook(
            @Valid @RequestBody CreateBookRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // PATCH /books/{id}/catalog → LIBRARIAN
    @PatchMapping("/{id}/catalog")
    public ResponseEntity<BookResponse> updateCatalog(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    // GET /books → LIBRARIAN, MEMBER
    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.ok().build();
    }
}
