package com.project.ets.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ets.requestdto.BatchRequestDTO;
import com.project.ets.responsedto.BatchResponse;
import com.project.ets.service.BatchService;
import com.project.ets.util.AppResponseBuilder;
import com.project.ets.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BatchController {
	private BatchService batchService;
	private AppResponseBuilder responseBuilder;
	
	@PostMapping("/admins/batches")
	public ResponseEntity<ResponseStructure<BatchResponse>> saveBatch(@RequestBody BatchRequestDTO request){
		BatchResponse response = batchService.saveBatch(request);
		return responseBuilder.success(HttpStatus.CREATED, "batch created successfully", response);
	}
	
	@PutMapping("/admins/batches/{batchId}")
	public ResponseEntity<ResponseStructure<BatchResponse>> updateBatch(@RequestBody BatchRequestDTO request,@PathVariable String batchId){
		BatchResponse response = batchService.updateBatch(request, batchId);
		return responseBuilder.success(HttpStatus.OK, "batch updated successfully", response);
	}
	
	@PatchMapping("/admins/batches/{batchId}/cancel")
	public ResponseEntity<ResponseStructure<BatchResponse>> cancelBatch(@PathVariable String batchId){
		BatchResponse response = batchService.cancelBatch(batchId);
		return responseBuilder.success(HttpStatus.OK, "batch canceled succefully", response);
	}
	
	@PatchMapping("/admins/batches/{batchId}/close")
	public ResponseEntity<ResponseStructure<BatchResponse>> closeBatch(@PathVariable String batchId){
		BatchResponse response = batchService.cancelBatch(batchId);
		return responseBuilder.success(HttpStatus.OK, "batch closed successfully", response);
	}
}
