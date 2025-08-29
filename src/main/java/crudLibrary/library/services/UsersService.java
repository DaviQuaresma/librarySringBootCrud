package crudLibrary.library.services;

import crudLibrary.library.models.BookModel;
import crudLibrary.library.models.UsersModel;
import crudLibrary.library.repositories.BookJpaRepository;
import crudLibrary.library.repositories.UsersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersJpaRepository usersRepository;

    @Autowired
    private BookJpaRepository bookRepository;

    public UsersModel save(UsersModel user) {
        return usersRepository.save(user);
    }

    public List<UsersModel> findAll() {
        return usersRepository.findAll();
    }

    public Optional<UsersModel> findById(Long id) {
        return usersRepository.findById(id);
    }

    public UsersModel lendBook(Long userId, Long bookId) {
        UsersModel user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        BookModel book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + bookId));

        // Verifica se o livro já está emprestado
        if (book.getUserId() != null) {
            throw new RuntimeException("Book is already loaned to another user");
        }

        // Atualiza o array borrowedBooks do usuário
        Integer[] currentBooks = user.getBorrowedBooks();
        if (currentBooks == null) {
            currentBooks = new Integer[0];
        }

        Integer[] updatedBooks = new Integer[currentBooks.length + 1];
        System.arraycopy(currentBooks, 0, updatedBooks, 0, currentBooks.length);
        updatedBooks[currentBooks.length] = book.getId().intValue();

        user.setBorrowedBooks(updatedBooks);
        user.setUpdatedAt(new java.util.Date());

        // Atualiza o livro para marcar o usuário
        book.setUserId(user.getId());
        book.setUserLoan(true);
        book.setLoanDate(new java.util.Date());

        // Salva as mudanças
        bookRepository.save(book);
        return usersRepository.save(user);
    }


    public UsersModel update(Long id, UsersModel userDetails) {
        return usersRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setUsername(userDetails.getUsername());
            user.setPasswordHash(userDetails.getPasswordHash());
            user.setRole(userDetails.getRole());
            user.setLastLogin(userDetails.getLastLogin());
            user.setAge(userDetails.getAge());
            user.setEmail(userDetails.getEmail());
            user.setCpf(userDetails.getCpf());
            user.setBorrowedBooks(userDetails.getBorrowedBooks());
            user.setUpdatedAt(new java.util.Date());
            return usersRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void delete(Long id) {
        usersRepository.deleteById(id);
    }
}
