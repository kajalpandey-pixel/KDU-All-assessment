package org.example.library.web.controller;

import org.example.library.api.dto.request.BorrowBookRequest;
import org.example.library.api.dto.request.ReturnBookRequest;
import org.example.library.api.dto.response.LoanResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    // POST /loans/{bookId}/borrow → MEMBER
    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<LoanResponse> borrowBook(
            @PathVariable String bookId,
            @RequestBody(required = false) BorrowBookRequest request
    ) {
        return ResponseEntity.ok().build();
    }

    // POST /loans/{bookId}/return → MEMBER
    @PostMapping("/{bookId}/return")
    public ResponseEntity<LoanResponse> returnBook(
            @PathVariable String bookId,
            @RequestBody(required = false) ReturnBookRequest request
    ) {
        return ResponseEntity.ok().build();
    }
}
