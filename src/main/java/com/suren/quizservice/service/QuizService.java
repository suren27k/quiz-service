package com.suren.quizservice.service;

import com.suren.quizservice.dao.QuizDao;
import com.suren.quizservice.entity.QuestionWrapper;
import com.suren.quizservice.entity.Quiz;
import com.suren.quizservice.entity.QuizResponse;
import com.suren.quizservice.feign.FeignClientForQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService
{
	@Autowired
	QuizDao quizDao;

	@Autowired
	FeignClientForQuestionService feignClient;


	public ResponseEntity<String> createQuiz(String categoryName, Integer noOfQuestions, String title)
	{
		 ResponseEntity<List<Integer>> respEntity = feignClient.generateQuestionsForQuiz(categoryName, noOfQuestions);

		List<Integer> questionIds = respEntity.getBody();

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questionIds);

		quizDao.save(quiz);

		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id)
	{
		Optional<Quiz> quizOptional = quizDao.findById(id);

		//handle empty quiz object here and throw exception if id is not valid
		//Quiz::new is a method reference used in place a lambda to return a new empty quiz object.
		// Quiz:: new is same as () -> new Quiz()
		Quiz quiz = quizOptional.orElseGet(Quiz::new);

		List<Integer> questionIds = quiz.getQuestionIds();

		ResponseEntity<List<QuestionWrapper>> questionWrappersResponseEntity = feignClient.getQuestionsForQuiz(questionIds);

		return questionWrappersResponseEntity;
	}

	public ResponseEntity<Integer> evaluateResult(Integer id, List<QuizResponse> quizResponses)
	{
		ResponseEntity<Integer> resp = feignClient.getScore(quizResponses);

		Integer score = resp.getBody();

		return new ResponseEntity<>(score, HttpStatus.OK);
	}
}
