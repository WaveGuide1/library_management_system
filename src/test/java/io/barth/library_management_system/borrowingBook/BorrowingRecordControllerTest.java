package io.barth.library_management_system.borrowingBook;

import io.barth.library_management_system.book.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays; import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class BorrowingRecordControllerTest {
    @Mock
    private BorrowingRecordServiceImp borrowingRecordServiceImp;
    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @Test
    public void testBorrowBook_Success() {
        // Mock data
        Long bookId = 1L;
        Long patronId = 1L;
        doNothing().when(borrowingRecordServiceImp).borrowBook(bookId, patronId);

        // Test
        ResponseEntity<String> response = borrowingRecordController.borrowBook(bookId, patronId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book borrowed successfully", response.getBody());
        verify(borrowingRecordServiceImp, times(1)).borrowBook(bookId, patronId);
    }
    @Test
    public void testReturnBook_Success() {
        // Test
        ResponseEntity<String> response = borrowingRecordController.returnBook(1L, 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book returned successfully", response.getBody());
    }
}
