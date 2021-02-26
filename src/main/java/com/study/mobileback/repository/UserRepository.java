package com.study.mobileback.repository;

import com.study.mobileback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

//    @Query("SELECT u FROM User u WHERE u.status = 1")
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.password = :password where u.email = :email")
    void update (String email, String password);

}
