package com.product.review.service;

import com.product.review.dto.FeedbackDTO;
import com.product.review.entity.Feedback;
import com.product.review.entity.Product;
import com.product.review.repository.FeedbackRepository;
import com.product.review.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FeedbackServiceTest {
    @Mock
    private FeedbackRepository feedbackRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    private Feedback feedback;

    @BeforeEach
    public void setup() {

        feedback = Feedback.builder()
                .feedbackId(1)
                .rating(4)
                .feedback("ok")
                .build();
    }


    @Test
    public void testAddFeedback(){
        int productId = 1;
        FeedbackDTO feedbackDTO1 = FeedbackDTO.builder()
                .feedbackId(2)
                .rating(2)
                .feedback("well")
                .build();
        given(feedbackRepository.save(feedback)).willReturn(feedback);
        Optional<Product> op = productRepository.findById(productId);
        if(op.isPresent()) {
            Feedback feedback1 = feedbackService.addFeedback(feedbackDTO1, productId);
            assertThat(feedback1).isNotNull();
        }
    }




    @Test
    public void testFeedbackList(){

        Feedback feedback1 = Feedback.builder()
                .feedbackId(2)
                .rating(3)
                .feedback("well")
                .build();

        given(feedbackRepository.findAll()).willReturn(List.of(feedback,feedback1));
        List<Feedback> feedbackList = feedbackService.getAllFeedback();
        assertThat(feedbackList).isNotNull();
        assertThat(feedbackList.size()).isEqualTo(2);
    }
    @Test
    public void testFeedbackListNegative(){

        given(feedbackRepository.findAll()).willReturn(Collections.emptyList());

        List<Feedback> feedbackList = feedbackService.getAllFeedback();

        assertThat(feedbackList).isEmpty();
        assertThat(feedbackList.size()).isEqualTo(0);
    }

    @Test
    public void testGetFeedbackId(){
        int feedbackId=2;
        given(feedbackRepository.findById(feedbackId)).willReturn(Optional.of(feedback));

        Optional<Feedback> optional = feedbackRepository.findById(feedbackId);
        if(optional.isPresent()) {
            Feedback savedFeedback = feedbackService.getFeedbackById(feedbackId);
            assertThat(savedFeedback).isNotNull();
        }



    }

    @Test
    public void testDeleteFeedback() {
        int feedbackId = 1;

        willDoNothing().given(feedbackRepository).deleteById(feedbackId);
    }
    @Test
    public void testUpdateFeedbackPositive(){

        when(feedbackRepository.save(feedback)).thenReturn(feedback);
        feedback.setFeedback("very good");
        feedback.setRating(5);
        assertEquals("very good", feedback.getFeedback());
        assertEquals(5, feedback.getRating());
    }



}
