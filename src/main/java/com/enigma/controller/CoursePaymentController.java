package com.enigma.controller;

import com.enigma.model.request.CoursePaymentRequest;
import com.enigma.model.response.CoursePaymentResponse;
import com.enigma.model.response.ErrorResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.CoursePaymentService;
import com.enigma.util.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlMapping.COURSE_PAYMENT)
public class CoursePaymentController {
    private CoursePaymentService coursePaymentService;

    public CoursePaymentController(@Autowired CoursePaymentService coursePaymentService) {
        this.coursePaymentService = coursePaymentService;
    }

    @PostMapping
    public ResponseEntity coursePayment(@RequestBody CoursePaymentRequest coursePaymentRequest) {
        CoursePaymentResponse response = coursePaymentService.pay(coursePaymentRequest);
        if (response.isStatus()) {
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success payment", response.getTransactionId()));
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X78", "Failed payment"));
    }
}
