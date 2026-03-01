package com.ludwigit.app.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@Builder
public class CreateShortURLRequestBody {
	@NotNull
	@NotBlank
	@URL
	private String url;
}
