package com.project.ets.requestdto;

import java.util.List;

import com.project.ets.enums.Subject;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerRequestDTO extends UserRequestDTO{
	
	@NotNull(message = "subjects list cannot be null")
	@NotEmpty(message = "subjects list cannot be empty")
	@Enumerated(EnumType.STRING)
	private List<Subject> subjects;
	
}
