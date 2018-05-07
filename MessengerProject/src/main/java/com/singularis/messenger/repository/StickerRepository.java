package com.singularis.messenger.repository;

import com.singularis.messenger.domain.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StickerRepository extends JpaRepository<Sticker, Integer> {
}
