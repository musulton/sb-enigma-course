package com.enigma.service;

import com.enigma.model.request.CoursePaymentRequest;
import com.enigma.model.response.CoursePaymentResponse;
import com.enigma.repository.CoursePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursePaymentServiceImpl implements CoursePaymentService {

    private CoursePaymentRepository coursePaymentRepository;

    public CoursePaymentServiceImpl(@Autowired CoursePaymentRepository coursePaymentRepository) {
        this.coursePaymentRepository = coursePaymentRepository;
    }

    @Override
    public CoursePaymentResponse pay(CoursePaymentRequest coursePaymentRequest) {
        return coursePaymentRepository.callPaymentApi(coursePaymentRequest);
    }
}
