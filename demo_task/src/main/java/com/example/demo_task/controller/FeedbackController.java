package com.example.demo_task.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_task.model.Feedback;
import com.example.demo_task.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:5173")
public class FeedbackController {

	private final FeedbackService feedbackService;

	public FeedbackController(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

	@PostMapping
	public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
		return new ResponseEntity<>(feedbackService.createFeedback(feedback), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Feedback>> getAllFeedback() {
		return new ResponseEntity<>(feedbackService.getAllFeedback(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Feedback> getFeedback(@PathVariable int id) {
		return new ResponseEntity<>(feedbackService.getFeedback(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Feedback> updateFeedback(@PathVariable int id, @RequestBody Feedback feedback) {
		return new ResponseEntity<>(feedbackService.updateFeedback(id, feedback), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFeedback(@PathVariable int id, @RequestParam int userId) {
		feedbackService.deleteFeedback(userId, id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
