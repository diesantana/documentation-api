package com.devsuperior.dsmovie.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.services.ScoreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/scores")
@Tag(name = "Score", description = "Controller for Score movie")
public class ScoreController {
	
	@Autowired
	private ScoreService service;

	@Operation(
			description = "Save score",
			summary = "Save score",
			responses = {
					@ApiResponse(description = "Ok", responseCode = "200"),
					@ApiResponse(description = "Unauthorized", responseCode = "401"),
					@ApiResponse(description = "Forbidden", responseCode = "403"),
					@ApiResponse(description = "Unprocessable Entity", responseCode = "422")
			}
	)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@PutMapping(produces = "application/json")
	public MovieDTO saveScore(@Valid @RequestBody ScoreDTO dto) {
		MovieDTO movieDTO = service.saveScore(dto);
		return movieDTO;
	}
}
