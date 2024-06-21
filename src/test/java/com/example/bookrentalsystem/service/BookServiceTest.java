package com.example.bookrentalsystem.service;

import com.example.bookrentalsystem.entity.Book;
import com.example.bookrentalsystem.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testGetAllBooks() {
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book("Book1", "Author1", "Genre1"));
        mockBooks.add(new Book("Book2", "Author2", "Genre2"));

        given(bookRepository.findAll()).willReturn(mockBooks);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
    }

    @Test
    public void testCreateBook() {
        Book book = new Book("New Book", "New Author", "New Genre");

        given(bookRepository.save(any(Book.class))).willReturn(book);

        Book createdBook = bookService.createBook(book);

        assertEquals("New Book", createdBook.getTitle());
        assertEquals("New Author", createdBook.getAuthor());
        assertEquals("New Genre", createdBook.getGenre());

        // Verify that save method of repository was called
        verify(bookRepository).save(any(Book.class));
    }
}

