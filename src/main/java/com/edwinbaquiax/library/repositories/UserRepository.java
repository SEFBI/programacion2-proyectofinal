package com.edwinbaquiax.library.repositories;

import com.edwinbaquiax.library.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsernameAndPassword(String username, String password);
}
