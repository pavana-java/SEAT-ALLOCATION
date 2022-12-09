package com.tataelxsi.ott.repository;

import com.tataelxsi.ott.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("From User where emailId = ?1")
    Optional<User> getUserByEmail(String emailId);

    @Query("from User where empId = ?1")
    User getUserByEmpId(int id);

}
