package com.edwinbaquiax.library.repositories;

import com.edwinbaquiax.library.models.entities.Book;
import com.edwinbaquiax.library.models.enums.RentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<Book,Long> {

    @Query("SELECT DISTINCT b FROM Book b JOIN b.rents r WHERE r.status = :status")
    List<Book> totalByStateRent(@Param("status") RentStatus status);

    @Query("SELECT  b FROM Book b JOIN b.rents r WHERE r.status = :status")
    List<Book> findALlBy(@Param("status") RentStatus status);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Book b JOIN b.rents r WHERE r.status = :status AND b.id = :id")
    boolean existsByStatusRent(@Param("id") Long id, @Param("status") RentStatus status);
}
