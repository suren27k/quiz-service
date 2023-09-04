# quiz-service
Quiz micro service of the quiz app

Hello.. This is a simple readme file which will contain all details of the quiz app with links to repos of other micro services.

This app currently has 4 services:
1. Question Service - https://github.com/suren27k/question-service
   a. It stores questions in its database.
   b. When a quiz service requests for questions to create a quiz, it returns a set of questions.
   c. When someone attempts a quiz, it returns the questions mapped to that quiz.
   d. Admin can create, update, and delete specific questions.
3. Quiz Service (current repo) - https://github.com/suren27k/quiz-service
   a. Allows logged in users to create quizzes using categories and difficulties.
   b. Users can also mention the number of questions needed for a quiz.
   c. Once quiz is created, users can see and access the quiz from their profile.
   d. They can share a quiz to anybody to attempt.
   e. Score is calculated for any submitted quiz.
4. Eureka Service Registry - https://github.com/suren27k/service-registry
5. API Gateway - https://github.com/suren27k/api-gateway
   
