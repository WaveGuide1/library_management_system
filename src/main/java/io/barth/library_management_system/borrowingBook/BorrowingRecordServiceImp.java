package io.barth.library_management_system.borrowingBook;

import io.barth.library_management_system.book.Book;
import io.barth.library_management_system.book.BookRepository;
import io.barth.library_management_system.exception.PatronHaveBorrowedTheBookException;
import io.barth.library_management_system.exception.RecordNotFoundException;
import io.barth.library_management_system.patron.Patron;
import io.barth.library_management_system.patron.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

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

    // borrow book
    @Override
    @Transactional
    public void borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RecordNotFoundException("No book with id " + bookId));

        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new RecordNotFoundException("No patron with id " + patronId));
        List<BorrowingRecord> books = borrowingRecordRepository.findAll();
        if(!books.isEmpty()){
            borrowingRecordRepository
                    .findByBookIdAndPatronIdAndReturnDateIsNotNull(bookId, patronId)
                    .orElseThrow(() -> new PatronHaveBorrowedTheBookException("This book was borrowed and has not been returned by the same patron"));
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowedDate(LocalDateTime.now());

        borrowingRecordRepository.save(borrowingRecord);
    }

    // Returned borrowed book
    @Override
    @Transactional
    public void returnBook(Long bookId, Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository
                .findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new RecordNotFoundException("No borrowing record found for bookId "
                        + bookId + " patronId " + patronId));
        borrowingRecord.setReturnDate(LocalDateTime.now());

        borrowingRecordRepository.save(borrowingRecord);
    }
}
