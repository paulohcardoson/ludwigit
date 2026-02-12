package com.ludwigit.app.repositories;

import com.ludwigit.app.dto.requests.CreateShortURLRequestBody;
import com.ludwigit.app.model.ShortedURL;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ShortedURLRepositoryTest {

	@Autowired
	ShortedURLRepository shortedURLRepository;

	@Autowired
	EntityManager entityManager;

	@Test
	@DisplayName("Deve encontrar uma URL original a partir de uma URL encurtada")
	void findByOriginalUrlCase1() {
		String originalUrl = "https://www.google.com";

		CreateShortURLRequestBody createShortURLRequestBody = new CreateShortURLRequestBody();
		createShortURLRequestBody.setUrl(originalUrl);

		this.createShortedURL(createShortURLRequestBody);
		Optional<ShortedURL> shortedURL = this.shortedURLRepository.findByOriginalUrl(originalUrl);

		assertThat(shortedURL.orElseThrow().getOriginalUrl()).isEqualTo(originalUrl);
	}

	@Test
	@DisplayName("Não deve encontrar uma URL original a partir de uma URL encurtada inexistente")
	void findByOriginalUrlCase2() {
		String originalUrl = "https://www.google.com";

		Optional<ShortedURL> shortedURL = this.shortedURLRepository.findByOriginalUrl(originalUrl);

		assertThat(shortedURL.isEmpty()).isTrue();
	}

	private ShortedURL createShortedURL(CreateShortURLRequestBody data) {
		ShortedURL shortedURL = ShortedURL
			.builder()
			.originalUrl(data.getUrl())
			.build();

		this.entityManager.persist(shortedURL);

		return shortedURL;
	}

}
