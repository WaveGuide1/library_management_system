package io.barth.library_management_system.book;

import io.barth.library_management_system.exception.RecordNotFoundException;

import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
@CacheConfig(cacheNames = "books")
public class BookServiceImp implements BookService{


    private final BookRepository bookRepository;


    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Get all books
    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    // Save a book
    @Override
    @Transactional
    public Book createBook(Book book) {

        book.setCreatedDate(LocalDateTime.now());
        return bookRepository.save(book);
    }

    // Update a book
    @CachePut(key = "#id")
    @Override
    public Book updateBook(Long id, Book book) {
        Book oldBook = bookRepository.findById(id) .orElseThrow(() ->  new RecordNotFoundException("Book not found with id: " + id));
        book.setId(id);
        book.setCreatedDate(oldBook.getCreatedDate());

        book.setLastModified(LocalDateTime.now());
        return bookRepository.save(book);
    }

    // Get a book by ID
    @Cacheable(key = "#id")
    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id) .orElseThrow(() -> new RecordNotFoundException("Book not found with id: " + id));
    }

    // Delete a book
    @CacheEvict(key = "#id")
    @Override
    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)){
            throw new RecordNotFoundException("No book with the id of " + id);
        }
        bookRepository.deleteById(id);
    }
}
