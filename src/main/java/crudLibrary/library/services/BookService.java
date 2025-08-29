package crudLibrary.library.services;

import crudLibrary.library.models.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crudLibrary.library.repositories.BookJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookJpaRepository repository;

    public BookModel save(BookModel book) {
        return repository.save(book);
    }

    public List<BookModel> findAll() {
        return repository.findAll();
    }

    public Optional<BookModel> findById(Long id) {
        return repository.findById(id);
    }

    public BookModel update(Long id, BookModel bookDetails) {
        return repository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPublishDate(bookDetails.getPublishDate());
            book.setUserId(bookDetails.getUserId());
            book.setUserLoan(bookDetails.isUserLoan());
            book.setLoanDate(bookDetails.getLoanDate());
            book.setDueDate(bookDetails.getDueDate());
            book.setOverdue(bookDetails.isOverdue());
            book.setNotes(bookDetails.getNotes());
            return repository.save(book);
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
