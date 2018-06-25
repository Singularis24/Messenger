package com.singularis.messenger.repository;

import com.singularis.messenger.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select b from User b where b.login = :login")
    User findByLogin(@Param("login") String login);

    @Query("select b from User b where b.firstName = :firstName")
    List<User> findByFirstName(@Param("firstName") String firstName);

    @Query("select b from User b where b.lastName = :lastName")
    List<User> findByLastName(@Param("lastName") String lastName);

    @Query("select avatarLink from User as u where u.id = :id")
    String findAvatarByUserId(@Param("id") int id);

}
