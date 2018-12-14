package com.bluementors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User u where u.firstName like :searchString or u.lastName like :searchString")
    User findByName(String searchString);
}
