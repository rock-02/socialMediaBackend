package com.example.mediaScocial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mediaScocial.Enity.User;

@Repository
public interface userRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
    List<User> searchUser(@Param("query") String query);


    @Query(value = "SELECT * FROM user ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<User> findSomeRandomUsers();
}
