package com.example.bookrentalsystem.service;


import com.example.bookrentalsystem.entity.Book;
import com.example.bookrentalsystem.entity.Rental;
import com.example.bookrentalsystem.entity.User;
import com.example.bookrentalsystem.repository.RentalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalService rentalService;

    @Test
    public void testRentBook() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");

        given(rentalRepository.save(any(Rental.class))).willAnswer(invocation -> invocation.getArgument(0));

        Rental rental = rentalService.rentBook(user, book);

        assertEquals(user.getId(), rental.getUser().getId());
        assertEquals(book.getId(), rental.getBook().getId());
        assertEquals(LocalDate.now(), rental.getRentalDate());

        // Verify that save method of repository was called
        verify(rentalRepository).save(any(Rental.class));
    }

    @Test
    public void testReturnBook() {
        Rental rental = new Rental();
        rental.setId(1L);
        rental.setActive(true);

        given(rentalRepository.save(any(Rental.class))).willAnswer(invocation -> invocation.getArgument(0));

        rentalService.returnBook(rental);

        assertEquals(false, rental.isActive());
        assertEquals(LocalDate.now(), rental.getReturnDate());

        // Verify that save method of repository was called
        verify(rentalRepository).save(any(Rental.class));
    }
}

