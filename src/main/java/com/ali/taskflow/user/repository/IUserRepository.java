package com.ali.taskflow.user.repository;

import com.ali.taskflow.user.entity.User;
import com.ali.taskflow.user.projection.ListUserProjection;
import com.ali.taskflow.user.projection.UserDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsById(long id);

    @Query(value = "SELECT id,username,password FROM users WHERE username=:username",nativeQuery = true)
    Optional<UserDetailProjection> findUserWithUserDetail(@Param("username") String username);

    @Query(value = "SELECT id,username,password FROM users WHERE id=:id",nativeQuery = true)
    UserDetailProjection findUserWithUserDetailById(@Param("id") long id);

    List<ListUserProjection> findAllBy();


}
