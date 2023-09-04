package com.suren.quizservice.controller;

import com.suren.quizservice.entity.QuestionWrapper;
import com.suren.quizservice.entity.Quiz;
import com.suren.quizservice.entity.QuizDto;
import com.suren.quizservice.entity.QuizResponse;
import com.suren.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController
{
	@Autowired
	QuizService quizService;

	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
	{
		return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNoOfQuestions(), quizDto.getTitle());
	}

	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
	{
		return quizService.getQuizQuestions(id);
	}

	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizResponse> quizResponses)
	{
		return quizService.evaluateResult(id, quizResponses);
	}
}
