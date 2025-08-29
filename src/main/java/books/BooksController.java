package books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookService service;

    @PostMapping
    public BookModel createBook(@RequestBody BookModel book) {
        return service.save(book);
    }

    @GetMapping
    public List<BookModel> getAllBooks() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BookModel getBookById(@PathVariable Long id) {
        return service.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public BookModel updateBook(@PathVariable Long id, @RequestBody BookModel book) {
        return service.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.delete(id);
    }
}
