package io.barth.library_management_system.borrowingBook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class BorrowingRecordController {

    private final BorrowingRecordServiceImp borrowingRecordServiceImp;

    public BorrowingRecordController(BorrowingRecordServiceImp borrowingRecordServiceImp) {
        this.borrowingRecordServiceImp = borrowingRecordServiceImp;
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId){
        borrowingRecordServiceImp.borrowBook(bookId, patronId);
        return ResponseEntity.ok("Book borrowed successfully");
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, @PathVariable Long patronId){
        borrowingRecordServiceImp.returnBook(bookId, patronId);
        return ResponseEntity.ok("Book returned successfully");
    }
}
