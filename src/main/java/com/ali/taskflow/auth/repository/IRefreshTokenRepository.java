package com.ali.taskflow.auth.repository;


import com.ali.taskflow.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IRefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    @Query(value = "DELETE FROM refresh_token WHERE user_id=:userId",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByUserId(@Param("userId") long userId);

    boolean existsByToken(String token);

    RefreshToken findByToken(String token);
}
