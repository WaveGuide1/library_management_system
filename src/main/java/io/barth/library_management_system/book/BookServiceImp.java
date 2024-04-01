package io.barth.library_management_system.book;

import io.barth.library_management_system.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService{

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
        book.setCreatedDate(LocalDateTime.now());
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        if(!bookRepository.existsById(id)){
            throw new EntityNotFoundException("Could not find a book with id "  + id);
        }
        book.setId(id);
        book.setLastModified(LocalDateTime.now());
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)){
            throw new EntityNotFoundException("No book with the id of " + id);
        }
        bookRepository.deleteById(id);
    }
}
