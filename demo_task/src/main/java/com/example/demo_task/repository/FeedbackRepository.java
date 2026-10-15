package com.example.demo_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo_task.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

}
