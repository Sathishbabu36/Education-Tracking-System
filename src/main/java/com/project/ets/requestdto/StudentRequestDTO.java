package com.project.ets.requestdto;

import java.time.Year;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDTO extends UserRequestDTO{

	@NotBlank(message = "Degree is required")
	@Size(max = 100, message = "Degree name must not exceed 100 characters")
	private String degree;

	@NotBlank(message = "Stream is required")
	@Size(max = 100, message = "Stream name must not exceed 100 characters")
	private String stream;

	@NotNull(message = "Year of passing is required")
	@PastOrPresent(message = "Year of passsing must be in the past or the current year")
	private Year yop;

	@NotNull(message = "Degree Percentage is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Degree percentage must be greater than 0")
	@DecimalMax(value = "100.0", inclusive = true, message = "Degree percentage must be less than or equal to 100")
	private int degreePercentage;

	@NotNull(message = "Tenth percentage is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Tenth percentage must be greater the 0")
	@DecimalMax(value = "100.0", inclusive = true, message = "Tenth percentage must be less than or equal to 100")
	private int tenthPercentage;

	@NotNull(message = "Twelth percentage is required.")
	@DecimalMin(value = "0.0", inclusive = false, message = "Twelth percentage must be greater than 0.")
	@DecimalMax(value = "100.0", inclusive = true, message = "Twefth percentage must be less than or equal to 100.")
	private int twelvethPercentage;


}
