package com.ludwigit.app.services;

import com.ludwigit.app.config.HashIdConfig;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HashIdsService {

	private final Hashids hashids;

	public HashIdsService(
		HashIdConfig hashIdConfig
	) {
		String salt = hashIdConfig.getSalt();
		Integer minLength = hashIdConfig.getMinLength();

		this.hashids = new Hashids(salt, minLength);
	}

	public String encode(Long id) {
		return this.hashids.encode(id);
	}

	public Optional<Long> decode(String hash) throws IllegalArgumentException {
		long[] decodedIds = this.hashids.decode(hash);

		if (decodedIds.length == 0) {
			return Optional.empty();
		}

		return Optional.of(decodedIds[0]);
	}
}
