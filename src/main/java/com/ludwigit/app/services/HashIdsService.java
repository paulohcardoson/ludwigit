package com.ludwigit.app.services;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HashIdsService {

	private final Hashids hashids;

	public HashIdsService(
		@Value("${hashids.salt}") String salt,
		@Value("${hashids.min-length}") Integer minLength
	) {
		this.hashids = new Hashids(salt, minLength);
	}

	public String encode(Long id) {
		return this.hashids.encode(id);
	}

	public Long decode(String hash) {
		long[] decodedIds = this.hashids.decode(hash);

		if (decodedIds.length == 0) {
			throw new IllegalArgumentException("Invalid hash");
		}

		return decodedIds[0];
	}
}
