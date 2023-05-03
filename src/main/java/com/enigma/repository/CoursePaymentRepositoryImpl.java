package com.enigma.repository;

import com.enigma.exception.NotFoundException;
import com.enigma.exception.RestTemplateException;
import com.enigma.model.request.CoursePaymentRequest;
import com.enigma.model.response.CoursePaymentResponse;
import com.enigma.model.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Repository
public class CoursePaymentRepositoryImpl implements CoursePaymentRepository {

    @Value("${service.paymentUrl}")
    String paymentServiceUrl;

    private RestTemplate restTemplate;

    public CoursePaymentRepositoryImpl(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CoursePaymentResponse callPaymentApi(CoursePaymentRequest coursePaymentRequest) {
        try {
            ResponseEntity<SuccessResponse> response = restTemplate.postForEntity(paymentServiceUrl, coursePaymentRequest, SuccessResponse.class );
            SuccessResponse<String> paymentResponse = response.getBody();
            CoursePaymentResponse coursePaymentResponse = new CoursePaymentResponse();

            if (!paymentResponse.getStatus().equals("OK")) {
                coursePaymentResponse.setStatus(false);
                coursePaymentResponse.setTransactionId("");

                return coursePaymentResponse;
            }

            coursePaymentResponse.setStatus(true);
            coursePaymentResponse.setTransactionId(paymentResponse.getData());
            return coursePaymentResponse;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new NotFoundException("Data not found");
            }
            throw new RestTemplateException(paymentServiceUrl, HttpStatus.UNPROCESSABLE_ENTITY, "Service is down");
        } catch (Exception e) {
            throw new RestTemplateException(paymentServiceUrl, HttpStatus.SERVICE_UNAVAILABLE, "Service time out");
        }
    }
}
