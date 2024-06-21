package com.example.bookrentalsystem.service;

import com.example.bookrentalsystem.entity.Book;
import com.example.bookrentalsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(bookDetails.getTitle());
            existingBook.setAuthor(bookDetails.getAuthor());
            existingBook.setGenre(bookDetails.getGenre());
            existingBook.setAvailabilityStatus(bookDetails.getAvailabilityStatus());
            return bookRepository.save(existingBook);
        }
        return null;
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}

