package crudLibrary.library.dto;

import crudLibrary.library.models.BookModel;
import crudLibrary.library.models.UsersModel;

public class LendResponseDTO {
    private UsersModel user;
    private BookModel book;

    public LendResponseDTO(UsersModel user, BookModel book) {
        this.user = user;
        this.book = book;
    }

    public UsersModel getUser() { return user; }
    public BookModel getBook() { return book; }
}
