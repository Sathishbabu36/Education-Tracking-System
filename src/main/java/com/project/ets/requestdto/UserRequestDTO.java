package com.project.ets.requestdto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRequestDTO {
	
	@NotNull(message="user name should not be null")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{2,19}$", message = "user name should contain alphabets and numbers and special characters are optional")
	private String username;

	@NotNull(message = "email should not be null")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "email should only end with @gmail.com")
	private String email;
	
}
