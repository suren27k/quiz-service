package com.suren.quizservice.entity;

import lombok.Data;

@Data
public class QuizDto
{

	// DTO - Data Transfer Object (Some intermediate object used for data transfer)

	private String categoryName;
	private Integer noOfQuestions;
	private String title;
}
