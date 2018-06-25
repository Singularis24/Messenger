package com.singularis.messenger.repository;

import com.singularis.messenger.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("select b from Message b where b.id_dialog = :id_dialog")
    List<Message> findByIdDialog(@Param("id_dialog") int id_dialog);
}
