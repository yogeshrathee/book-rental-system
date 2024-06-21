package com.example.bookrentalsystem.service;
import com.example.bookrentalsystem.entity.Book;
import com.example.bookrentalsystem.entity.Rental;
import com.example.bookrentalsystem.entity.User;
import com.example.bookrentalsystem.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public Rental rentBook(User user, Book book) {
        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.setRentalDate(LocalDate.now());
        return rentalRepository.save(rental);
    }

    public void returnBook(Rental rental) {
        rental.setActive(false);
        rental.setReturnDate(LocalDate.now());
        rentalRepository.save(rental);
    }

    // Other methods as needed
}
