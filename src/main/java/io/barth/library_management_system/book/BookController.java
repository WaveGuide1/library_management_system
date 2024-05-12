package io.barth.library_management_system.book;

import io.barth.library_management_system.exception.GeneralExceptionHandler;
import io.barth.library_management_system.exception.RecordNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private final BookServiceImp bookServiceImp;

    public BookController(BookServiceImp bookServiceImp) {
        this.bookServiceImp = bookServiceImp;
    }

    // Get all book
    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        try {
            return new ResponseEntity<>(bookServiceImp.getAllBook(), HttpStatus.OK);
        } catch (Exception ex){
            throw  new GeneralExceptionHandler("Something went wrong");
        }
    }

    // Save a book
    @PostMapping
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book){
        try {
            Book newBook = bookServiceImp.createBook(book);
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        } catch (Exception ex){
            throw  new GeneralExceptionHandler("Something went wrong");
        }
    }

    // Update a book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@Valid @PathVariable Long id, @RequestBody Book book){
        try {
            Book updatedBook = bookServiceImp.updateBook(id, book);
            return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);
        } catch (RecordNotFoundException ex){
            throw ex;
        } catch (Exception ex){
            throw  new GeneralExceptionHandler("Something went wrong");
        }
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {

        try { Book book = bookServiceImp.getBookById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (RecordNotFoundException ex){
            throw ex;
        } catch (Exception ex) {
            throw  new GeneralExceptionHandler("Something went wrong");
        }
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        try {
            bookServiceImp.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (RecordNotFoundException ex){
            throw ex;
        } catch (Exception ex){
            throw  new GeneralExceptionHandler("Something went wrong");
        }
    }
}
