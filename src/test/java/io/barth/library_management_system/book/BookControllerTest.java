package io.barth.library_management_system.book;

import io.barth.library_management_system.exception.RecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays; import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @Mock
    private BookServiceImp bookServiceImp;
    @InjectMocks
    private BookController bookController;

    @Test
    public void testGetAllBooks_Success() {
        // Mock data
        Book book1 = new Book(1L,"Title 1", "Author 1", 2022, "ISBN-1", LocalDateTime.now(), null);
        Book book2 = new Book(2L, "Title 2", "Author 2", 2023, "ISBN-2", LocalDateTime.now(), null);
        List<Book> books = Arrays.asList(book1, book2);
        when(bookServiceImp.getAllBook()).thenReturn(books);
        // Test
        ResponseEntity<List<Book>> response = bookController.getBooks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }
    @Test
    public void testGetAllBooks_EmptyList() {
        // Mock data
        List<Book> books = List.of();
        when(bookServiceImp.getAllBook()).thenReturn(books);
        // Test
        ResponseEntity<List<Book>> response = bookController.getBooks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, Objects.requireNonNull(response.getBody()).size());
    }
    @Test
    public void testGetBookById_Success() {
        // Mock data
        Long bookId = 1L;
        Book book = new Book(bookId, "Title", "Author", 2022, "ISBN", LocalDateTime.now(), null);
        when(bookServiceImp.getBookById(bookId)).thenReturn(book);
        // Test
        ResponseEntity<Book> response = bookController.getBookById(bookId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }


    @Test
    public void testAddBook_Success() {
        // Mock data
        Book book = new Book(1L, "Title", "Author", 2022, "ISBN", LocalDateTime.now(), null);
        when(bookServiceImp.createBook(book)).thenReturn(book);
        // Test
        ResponseEntity<Book> response = bookController.saveBook(book);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testUpdateBook_Success() {
        // Mock data
        Long bookId = 1L;
        Book updatedBook = new Book(bookId, "Updated Title", "Updated Author", 2023, "ISBN-Updated", null, LocalDateTime.now());
        when(bookServiceImp.updateBook(bookId, updatedBook)).thenReturn(updatedBook);
        // Test
        ResponseEntity<Book> response = bookController.updateBook(bookId, updatedBook);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(updatedBook, response.getBody());
    }

    @Test
    public void testDeleteBook_Success() {
        // Mock data
        Long bookId = 1L;
        doNothing().when(bookServiceImp).deleteBook(bookId);
        // Test
        ResponseEntity<Void> response = bookController.deleteBook(bookId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}