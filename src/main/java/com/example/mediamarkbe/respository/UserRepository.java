package com.example.mediamarkbe.respository;


import com.example.mediamarkbe.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsernameIgnoreCaseAndDeletedFalse(String username);

    Optional<User> findByUsernameIgnoreCase(String username);
    @Query("SELECT e FROM User e WHERE e.name LIKE :name AND e.email LIKE :email AND e.username LIKE :username")
    Page<User> findUsers (Pageable pageable, @Param("name") String name, @Param("email") String email, @Param("username") String username);
    @Query("SELECT e FROM User e WHERE e.name LIKE :name AND e.email LIKE :email AND e.username LIKE :username")
    List<User> findAll(@Param("name") String name, @Param("email") String email, @Param("username") String username);

    boolean existsByUsernameAndIdNot(String username,Long id);

//    boolean existsByUsernameAndDeletedIAndIdNot(String username,boolean delete,Long id);
    boolean existsByUsernameAndDeletedFalseAndIdNot(String username,Long id);

//    boolean existsByUsernameAndDeleted(String username,boolean delete);
    boolean existsByUsernameAndDeletedFalse(String username);
    boolean existsByUsername(String username);
}
