package org.example.library.domain.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(

        name = "Loans" ,

        // I am creating indexes here so that  it becomes easy to search it

        indexes = {
@Index(name = "idx_loans_book_id", columnList = "book_id"),
@Index(name = "idx_loans_borrower_id", columnList = "borrower_id")
        }

)

public class Loan {

    @Id
    @GeneratedValue
    private  UUID id ;


    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id" , nullable = false)
    private Book book ;

    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id" , nullable = false)
    private User borrower ;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant borrowedAt;

    @Column(nullable = true)
    private Instant returnedAt;

    // getters/setters

    public UUID getId() { return id; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public User getBorrower() { return borrower; }
    public void setBorrower(User borrower) { this.borrower = borrower; }

    public Instant getBorrowedAt() { return borrowedAt; }

    public Instant getReturnedAt() { return returnedAt; }
    public void setReturnedAt(Instant returnedAt) { this.returnedAt = returnedAt; }
}




