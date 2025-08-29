package crudLibrary.library.repositories;

import crudLibrary.library.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersJpaRepository extends JpaRepository<UsersModel, Long> {
}

