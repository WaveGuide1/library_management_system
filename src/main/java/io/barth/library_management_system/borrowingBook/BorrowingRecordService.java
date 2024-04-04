package io.barth.library_management_system.borrowingBook;

public interface BorrowingRecordService {

    public void borrowBook(Long bookId, Long PatronId);

    public void returnBook(Long bookId, Long patronId);
}
