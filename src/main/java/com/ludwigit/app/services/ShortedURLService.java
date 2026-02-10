package com.ludwigit.app.services;

import com.ludwigit.app.exceptions.ShortedURLNotFoundException;
import com.ludwigit.app.exceptions.URLAlreadyExistsException;
import com.ludwigit.app.model.ShortedURL;
import com.ludwigit.app.repositories.ShortedURLRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class ShortedURLService {

	private final HashIdsService hashIdsService;
	private final ShortedURLRepository shortedUrlRepository;
	private final URI baseUri;

	@SneakyThrows
	public ShortedURLService(
		ShortedURLRepository shortedUrlRepository,
		HashIdsService hashIdsService,
		@Value("${app.base-url}") String BASE_URL
	) {
		this.hashIdsService = hashIdsService;
		this.shortedUrlRepository = shortedUrlRepository;
		this.baseUri = new URI(BASE_URL);
	}

	@SneakyThrows
	public String createShortedURL(String originalUrl) {
		Optional<ShortedURL> alreadyShortedUrl = shortedUrlRepository.findByOriginalUrl(originalUrl);

		if (alreadyShortedUrl.isPresent()) {
			throw new URLAlreadyExistsException();
		}

		ShortedURL newShortedUrl = ShortedURL.builder()
			.originalUrl(originalUrl)
			.build();

		ShortedURL shortedUrl = shortedUrlRepository.save(newShortedUrl);
		Long id = shortedUrl.getId();
		String obfuscatedBase62URL = hashIdsService.encode(id);

		System.out.println(obfuscatedBase62URL);

		return this.baseUri.resolve(obfuscatedBase62URL).toString();
	}

	@SneakyThrows
	public String retrieveUrl(String shortedURL) {
		Long decodedId;

		try {
			decodedId = hashIdsService.decode(shortedURL);
		} catch (IllegalArgumentException e) {
			// Do not show the exact error message to the user, as it may contain sensitive information about the hashids configuration
			throw new ShortedURLNotFoundException();
		}

		return shortedUrlRepository
			.findById(decodedId)
			.orElseThrow(ShortedURLNotFoundException::new)
			.getOriginalUrl();
	}

}
