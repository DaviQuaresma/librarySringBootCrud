package crudLibrary.library.controllers;


import crudLibrary.library.dto.LendResponseDTO;
import crudLibrary.library.models.BookModel;
import crudLibrary.library.models.UsersModel;
import crudLibrary.library.services.BookService;
import crudLibrary.library.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService service;

    @Autowired
    private BookService bookService;

    @PostMapping
    public UsersModel createUser(@RequestBody UsersModel user) {
        return service.save(user);
    }

    @PostMapping("/lend/user/{userId}/book/{bookId}")
    public ResponseEntity<Map<String, Object>> lendBook(
            @PathVariable Long userId,
            @PathVariable Long bookId
    ) {
        UsersModel user = service.lendBook(userId, bookId);
        BookModel book = bookService.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("book", book);

        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public List<UsersModel> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UsersModel getUserById(@PathVariable Long id) {
        return service.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public UsersModel updateUser(@PathVariable Long id, @RequestBody UsersModel user) {
        return service.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.delete(id);
    }

}
