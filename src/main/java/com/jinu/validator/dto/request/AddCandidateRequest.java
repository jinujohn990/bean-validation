package com.jinu.validator.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class AddCandidateRequest {
	@NotEmpty(message = "Name cannot be null or empty")
	private String name;
	@Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",message = "Email id is not valid")
	private String email;
	@NotEmpty
	@Size(max = 12)
	private String phone;
	private String interviewerId;
}
