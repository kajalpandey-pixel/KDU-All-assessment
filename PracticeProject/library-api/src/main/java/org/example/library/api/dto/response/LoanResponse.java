package org.example.library.api.dto.response;

import java.time.Instant;
import java.util.UUID;

public class LoanResponse {
    private UUID id;
    private UUID bookId;
    private UUID borrowerId;
    private Instant borrowedAt;
    private Instant returnedAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getBookId() { return bookId; }
    public void setBookId(UUID bookId) { this.bookId = bookId; }

    public UUID getBorrowerId() { return borrowerId; }
    public void setBorrowerId(UUID borrowerId) { this.borrowerId = borrowerId; }

    public Instant getBorrowedAt() { return borrowedAt; }
    public void setBorrowedAt(Instant borrowedAt) { this.borrowedAt = borrowedAt; }

    public Instant getReturnedAt() { return returnedAt; }
    public void setReturnedAt(Instant returnedAt) { this.returnedAt = returnedAt; }
}
