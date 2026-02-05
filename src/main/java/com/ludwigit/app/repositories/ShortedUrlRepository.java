package com.ludwigit.app.repositories;

import com.ludwigit.app.model.ShortedURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortedUrlRepository extends JpaRepository<ShortedURL, Long> {
}
