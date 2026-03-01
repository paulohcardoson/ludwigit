package com.ludwigit.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ludwigit.app.dto.requests.CreateShortURLRequestBody;
import com.ludwigit.app.exceptions.ShortedURLNotFoundException;
import com.ludwigit.app.services.ShortedURLService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class ShortedURLControllerTest {

	ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private MockMvc mockMvc;
	@MockitoBean
	private ShortedURLService shortedURLService;

	@Test
	@DisplayName("Deve criar uma URL encurtada a partir de uma URL original válida")
	@SneakyThrows
	public void createShortedURLTest1() {
		mockMvc.perform(
			MockMvcRequestBuilders
				.post("/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
					objectMapper.writeValueAsString(
						CreateShortURLRequestBody.builder()
							.url("https://www.example.com")
							.build()
					)
				)
		).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@SneakyThrows
	@Test
	@DisplayName("Deve retornar erro de validação para URL original inválida")
	public void createShortedURLTest2() {
		String invalidURL = "invalid-url";

		mockMvc.perform(
			MockMvcRequestBuilders
				.post("/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
					objectMapper.writeValueAsString(
						CreateShortURLRequestBody.builder()
							.url(invalidURL)
							.build()
					)
				)
		).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@SneakyThrows
	@Test
	@DisplayName("Deve redirecionar para a URL original ao acessar a URL encurtada")
	public void redirectToOriginalURLTest1() {
		String originalURL = "https://www.example.com";
		String shortedURL = "abc123";

		Mockito.when(shortedURLService.retrieveUrl(shortedURL)).thenReturn(originalURL);

		mockMvc
			.perform(MockMvcRequestBuilders.get("/" + shortedURL))
			.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.header().string("Location", originalURL));
	}

	@SneakyThrows
	@Test
	@DisplayName("Deve retornar erro 401 para URL encurtada inválida")
	public void redirectToOriginalURLTest2() {
		String invalidShortedURL = "invalid-url";

		Mockito.when(shortedURLService.retrieveUrl(invalidShortedURL)).thenThrow(new ShortedURLNotFoundException());

		mockMvc
			.perform(MockMvcRequestBuilders.get("/" + invalidShortedURL))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@SneakyThrows
	@Test
	@DisplayName("Deve retornar erro 404 para URL encurtada inexistente")
	public void redirectToOriginalURLTest3() {
		String shortedURL = "nonexistent";

		Mockito.when(shortedURLService.retrieveUrl(shortedURL)).thenThrow(new ShortedURLNotFoundException());

		mockMvc
			.perform(MockMvcRequestBuilders.get("/" + shortedURL))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
