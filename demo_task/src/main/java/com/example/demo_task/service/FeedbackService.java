package com.example.demo_task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo_task.exception.ResourseNotFoundException;
import com.example.demo_task.model.Feedback;
import com.example.demo_task.model.User;
import com.example.demo_task.repository.FeedbackRepository;
import com.example.demo_task.repository.UserRepository;

@Service
public class FeedbackService {

	private final FeedbackRepository feedbackRepository;
	private final UserRepository userRepository;

	public FeedbackService(FeedbackRepository feedbackRepository, UserRepository userRepository) {
		this.feedbackRepository = feedbackRepository;
		this.userRepository = userRepository;
	}

	public Feedback createFeedback(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}

	public Feedback updateFeedback(int id, Feedback feedback) {
		Feedback current = getFeedback(id);
		current.setMessage(feedback.getMessage());
		return feedbackRepository.save(current);
	}

	public Feedback getFeedback(int fid) {
		return feedbackRepository.findById(fid)
				.orElseThrow(() -> new ResourseNotFoundException("Feedback with id: " + fid + " not exist"));
	}

	public List<Feedback> getAllFeedback() {
		return feedbackRepository.findAll();
	}

	public void deleteFeedback(int userId, int feedbackId) {
		if (!feedbackRepository.existsById(feedbackId)) {
			throw new ResourseNotFoundException("Feedback with id: " + feedbackId + " not exist");
		}

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User with id: " + userId + " not exist"));

		if (!"ADMIN".equals(user.getRole())) {
			return; 
		}
		feedbackRepository.deleteById(feedbackId);
	}
}
