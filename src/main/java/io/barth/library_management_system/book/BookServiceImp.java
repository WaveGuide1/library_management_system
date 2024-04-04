package io.barth.library_management_system.book;

import io.barth.library_management_system.exception.BadRequestException;
import io.barth.library_management_system.exception.EntityNotFoundException;
import io.barth.library_management_system.exception.InternalServerErrorException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BookServiceImp implements BookService{

    private static final Logger logger = Logger.getLogger(BookService.class.getName());

    private final BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        if (book.getTitle() == null || book.getAuthor() == null ||
                book.getPublicationYear() == null || book.getIsbn() == null) {
            throw new BadRequestException("Title, author, publication year, and ISBN are required for creating a book");
        }
        book.setCreatedDate(LocalDateTime.now());
        logger.info("Book added successfully: " + book);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        if(!bookRepository.existsById(id)){
            throw new EntityNotFoundException("Could not find a book with id "  + id);
        }
        book.setId(id);
        book.setLastModified(LocalDateTime.now());
        logger.info("Book updated successfully: " + book);
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        logger.info("Getting book by id: " + id);
        Book book = bookRepository.findById(id) .orElseThrow(() -> { logger.severe("Book not found with id: " + id);
        return new InternalServerErrorException("An internal server error occurred while retrieving the book with id: " + id);
        });
        logger.info("Book retrieved successfully: " + book);
        return book;
    }

    @Override
    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)){
            throw new EntityNotFoundException("No book with the id of " + id);
        }
        logger.info("Book deleted successfully. The id is : " + id);
        bookRepository.deleteById(id);
    }
}
