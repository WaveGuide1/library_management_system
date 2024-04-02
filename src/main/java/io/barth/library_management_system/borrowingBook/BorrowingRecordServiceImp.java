package io.barth.library_management_system.borrowingBook;

import io.barth.library_management_system.book.Book;
import io.barth.library_management_system.book.BookRepository;
import io.barth.library_management_system.exception.EntityNotFoundException;
import io.barth.library_management_system.patron.Patron;
import io.barth.library_management_system.patron.PatronRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BorrowingRecordServiceImp implements BorrowingRecordService{

    private final BorrowingRecordRepository borrowingRecordRepository;

    private final BookRepository bookRepository;

    private final PatronRepository patronRepository;

    public BorrowingRecordServiceImp(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Override
    public void borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book with id " + bookId));

        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new EntityNotFoundException("No patron with id " + patronId));

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowedDate(LocalDateTime.now());

        borrowingRecordRepository.save(borrowingRecord);
    }

    @Override
    public void returnBook(Long bookId, Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository
                .findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new EntityNotFoundException("No borrowing record found for bookId "
                        + bookId + " patronId " + patronId));
        borrowingRecord.setReturnDate(LocalDateTime.now());
        borrowingRecordRepository.save(borrowingRecord);
    }
}
