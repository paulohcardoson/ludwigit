package com.ludwigit.app.repositories;

import com.ludwigit.app.dto.requests.CreateShortURLRequestBody;
import com.ludwigit.app.model.ShortedURL;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ShortedURLRepositoryTest {

	@Autowired
	private ShortedURLRepository shortedURLRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("Deve ser possível salvar uma URL encurtada a partir de uma URL original")
	void testSaveShortedURL() {
		String originalUrl = "https://www.example.com";

		this.createShortedURL(
			CreateShortURLRequestBody.builder()
				.url(originalUrl)
				.build()
		);

		ShortedURL shortedURL = this.shortedURLRepository.findByOriginalUrl(originalUrl).orElseThrow();

		Assertions.assertEquals(
			originalUrl,
			shortedURL.getOriginalUrl()
		);
	}

	@Test
	@DisplayName("Não deve ser possível encontrar uma URL encurtada a partir de uma URL original que não existe")
	void testFindByOriginalUrlNotFound() {
		String originalUrl = "https://www.nonexistent.com";

		Assertions.assertTrue(this.shortedURLRepository.findByOriginalUrl(originalUrl).isEmpty());
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
