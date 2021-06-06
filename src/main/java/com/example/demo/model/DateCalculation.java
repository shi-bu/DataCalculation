package com.example.demo.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DateCalculation {

	private int id;
	
	@NotBlank
	private String dateId;
	
	@NotBlank
	private String dateName;
	
	@NotBlank
	private int adjustmentYear;
	
	@NotBlank
	private int adjustmentMonth;
	
	@NotBlank
	private int adjustmentDay;
	
}
