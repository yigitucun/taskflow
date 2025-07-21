package com.ali.taskflow.user.repository;

import com.ali.taskflow.user.entity.User;
import com.ali.taskflow.user.projection.UserDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query(value = "SELECT id,username,password FROM users",nativeQuery = true)
    Optional<UserDetailProjection> findUserWithUserDetail(String username);
}
