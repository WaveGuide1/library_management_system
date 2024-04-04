package io.barth.library_management_system.book;

import io.barth.library_management_system.exception.EntityNotFoundException;
import io.barth.library_management_system.exception.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private final BookServiceImp bookServiceImp;

    public BookController(BookServiceImp bookServiceImp) {
        this.bookServiceImp = bookServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        try {
            return new ResponseEntity<>(bookServiceImp.getAllBook(), HttpStatus.OK);
        } catch (InternalServerErrorException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        try {
            Book newBook = bookServiceImp.createBook(book);
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        } catch (InternalServerErrorException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
        try {
            Book updatedBook = bookServiceImp.updateBook(id, book);
            return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);
        } catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {

        try { Book book = bookServiceImp.getBookById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InternalServerErrorException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        try {
            bookServiceImp.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
