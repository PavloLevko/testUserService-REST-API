package com.example.testuserservice.repository;

import com.example.testuserservice.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
    @Query(value = "SELECT * FROM test WHERE birth_data BETWEEN :from AND :to", nativeQuery = true)
    List<User> findByDataRange (@Param("from") Date from, @Param("to") Date to);
}
