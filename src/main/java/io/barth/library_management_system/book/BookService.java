package io.barth.library_management_system.book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public List<Book> getAllBook();

    public Book createBook(Book book);

    public Book updateBook(Long id, Book book);

    public Optional<Book> getBookById(Long id);

    public void deleteBook(Long id);
}
