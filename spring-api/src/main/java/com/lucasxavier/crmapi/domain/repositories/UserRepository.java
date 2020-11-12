package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user_details WHERE username = ?1", nativeQuery = true)
    Optional<User> findByUserName(String userName);
}
