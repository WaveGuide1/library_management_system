package io.barth.library_management_system.borrowingBook;

import io.barth.library_management_system.exception.GeneralExceptionHandler;
import io.barth.library_management_system.exception.PatronHaveBorrowedTheBookException;
import io.barth.library_management_system.exception.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class BorrowingRecordController {

    private final BorrowingRecordServiceImp borrowingRecordServiceImp;

    public BorrowingRecordController(BorrowingRecordServiceImp borrowingRecordServiceImp) {
        this.borrowingRecordServiceImp = borrowingRecordServiceImp;
    }

    // Borrow book
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId){
        try {
            borrowingRecordServiceImp.borrowBook(bookId, patronId);
            return ResponseEntity.ok("Book borrowed successfully");
        } catch (RecordNotFoundException | PatronHaveBorrowedTheBookException e){
            throw e;
        } catch (Exception e){
            throw new GeneralExceptionHandler("Something went wrong");
        }

    }

    // Return borrowed book
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, @PathVariable Long patronId){
        try {
            borrowingRecordServiceImp.returnBook(bookId, patronId);
            return ResponseEntity.ok("Book returned successfully");
        } catch (RecordNotFoundException e){
            throw e;
        } catch (Exception e){
            throw new GeneralExceptionHandler("Something went wrong");
        }
    }
}
