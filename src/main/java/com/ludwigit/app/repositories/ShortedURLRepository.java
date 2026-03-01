package com.ludwigit.app.repositories;

import com.ludwigit.app.model.ShortedURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortedURLRepository extends JpaRepository<ShortedURL, Long> {
	Optional<ShortedURL> findByOriginalUrl(String originalUrl);
}
