package crudLibrary.library.repositories;

import crudLibrary.library.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJpaRepository extends JpaRepository<BookModel, Long> {
}
