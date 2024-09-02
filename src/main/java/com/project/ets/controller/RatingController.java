package com.project.ets.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ets.requestdto.RatingRequest;
import com.project.ets.responsedto.RatingResponse;
import com.project.ets.service.RatingService;
import com.project.ets.util.AppResponseBuilder;
import com.project.ets.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RatingController {
	
	RatingService ratingService;
	AppResponseBuilder responseBuilder;

	@Operation(description = "The API endpoint is used to update ratings",responses = {
			@ApiResponse(responseCode = "200",description = "ratings updated"),
			@ApiResponse(responseCode = "500",description = "Internal server error",
			content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))
			})
	})
	@PutMapping("/ratings/{ratingId}")
	public ResponseEntity<ResponseStructure<RatingResponse>> updateRatings(@RequestBody RatingRequest request,
			@PathVariable String ratingId) {
		RatingResponse response = ratingService.updateStudentRating(request, ratingId);
		return responseBuilder.success(HttpStatus.OK, "ratings updated", response);
	}
}
