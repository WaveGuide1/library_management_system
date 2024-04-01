package io.barth.library_management_system.book;

import io.barth.library_management_system.utility.ISBN;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book must have a title")
    private String title;

    @NotBlank(message = "Book must have an Author")
    private String author;

    private int publicationYear;

    @ISBN
    private String isbn;

}