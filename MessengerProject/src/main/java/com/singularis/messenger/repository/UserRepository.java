package com.singularis.messenger.repository;

import com.singularis.messenger.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select b from User b where b.login = :login")
    User findByLogin(@Param("login") String name);

}
