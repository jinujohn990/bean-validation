package com.jinu.validator.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jinu.validator.dto.request.AddCandidateRequest;
import com.jinu.validator.dto.response.Response;

@RestController
public class ValidatorController {
	@Autowired
	Validator validator;

	@PostMapping("/addCandidate")
	public Response addCandidate(@RequestBody AddCandidateRequest request) {
		System.out.println("Entering addCandidate...");
		Response response = new Response();
		try {
			validateRequest(request);
			response.setStatus("succeess");
			response.setMessage("Candidate is successfully validated");
		} catch (Exception e) {
			response.setStatus("fail");
			response.setMessage(e.getMessage());
		}
		return response;
	}

	private void validateRequest(@Valid AddCandidateRequest request) throws Exception {

		Set<ConstraintViolation<AddCandidateRequest>> violations = validator.validate(request);
		if (violations.size() > 0) {
			List<String> validationMessages = violations.stream().map(e -> e.getMessage()).collect(Collectors.toList());
			throw new Exception(String.join(",", validationMessages));
		}

	}
}
