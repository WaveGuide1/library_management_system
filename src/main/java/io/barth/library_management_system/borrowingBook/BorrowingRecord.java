package io.barth.library_management_system.borrowingBook;

import io.barth.library_management_system.book.Book;
import io.barth.library_management_system.patron.Patron;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class BorrowingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Patron patron;

    private LocalDateTime borrowedDate;

    private LocalDateTime returnDate;
}
