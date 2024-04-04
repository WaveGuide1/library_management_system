package io.barth.library_management_system.borrowingBook;

import io.barth.library_management_system.book.Book;
import io.barth.library_management_system.book.BookRepository;
import io.barth.library_management_system.book.BookService;
import io.barth.library_management_system.exception.EntityNotFoundException;
import io.barth.library_management_system.exception.ForbiddenException;
import io.barth.library_management_system.patron.Patron;
import io.barth.library_management_system.patron.PatronRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BorrowingRecordServiceImp implements BorrowingRecordService{

    private static final Logger logger = Logger.getLogger(BookService.class.getName());

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
        List<BorrowingRecord> books = borrowingRecordRepository.findAll();
        if(!books.isEmpty()){
            borrowingRecordRepository
                    .findByBookIdAndPatronIdAndReturnDateIsNotNull(bookId, patronId)
                    .orElseThrow(() -> new ForbiddenException("This book was borrowed and has not been returned by the same patron"));
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowedDate(LocalDateTime.now());

        logger.info("Book borrowed successfully: " + borrowingRecord);
        borrowingRecordRepository.save(borrowingRecord);
    }

    @Override
    public void returnBook(Long bookId, Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository
                .findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new ForbiddenException("No borrowing record found for bookId "
                        + bookId + " patronId " + patronId));
        borrowingRecord.setReturnDate(LocalDateTime.now());

        logger.info("Book with id " + bookId + " has been returned by patron with id " + patronId);
        borrowingRecordRepository.save(borrowingRecord);
    }
}
