package crudLibrary.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
@RestController
public class CrudLibraryProjectApplication {

    private final DataSource dataSource;

    public CrudLibraryProjectApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {
        SpringApplication.run(CrudLibraryProjectApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/check-db")
    public String checkDatabaseConnection() {
        try (Connection conn = dataSource.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                return "Connected to database!";
            } else {
                return "Failed to connect to database.";
            }
        } catch (Exception e) {
            return "Database error: " + e.getMessage();
        }
    }
}
