package com.product.review.controller;

import java.util.List;

import com.product.review.dto.FeedbackDTO;
import com.product.review.entity.Feedback;
import com.product.review.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.product.review.dto.ProductDTO;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = {"http://localhost:9090", "http://localhost:4200"},allowedHeaders = "*")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
@GetMapping("/allfeedback")
public List<Feedback> listFeedbacks() {
    return feedbackService.getAllFeedback();
}

@PostMapping("/givefeedback/{productId}")
    public void givesFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO, @PathVariable int productId){
    feedbackService.addFeedback(feedbackDTO, productId);
}

@PutMapping("/update/{id}")
public void updatesDetails(@PathVariable int id, @RequestBody FeedbackDTO feedbackDTO, ProductDTO productDTO ) {
feedbackService.updateDetails(id, feedbackDTO, productDTO);
}
    @GetMapping("/getfeedback/{feedbackId}")
    public Feedback getAFeedbackById(@PathVariable int feedbackId) {
        return feedbackService.getFeedbackById(feedbackId);
    }
    @DeleteMapping("/deletefeedback/{feedbackId}")
    public ResponseEntity<String> deleteAFeedback(@PathVariable int feedbackId){
        feedbackService.deleteFeedbackById(feedbackId);
        return new ResponseEntity<>("Feedback Deleted SuccessFully", HttpStatus.OK);
    }

    @GetMapping("/sortfeedback/{field}")
    public List<Feedback> findFeedbacksWithSorting(@PathVariable String field) {return feedbackService.findFeedbacksWithSorting(field);
    }
    @GetMapping("/paginationfeedback/{offset}/{pageSize}")
    public Page<Feedback> findFeedbacksWithPagination(@PathVariable int offset,@PathVariable int pageSize) {return feedbackService.findFeedbacksWithPagination(offset, pageSize);
    }
    @GetMapping("/pagefeedback/{offset}/{pageSize}/{field}")
    public Page<Feedback> findFeedbacksWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {return feedbackService.findFeedbacksWithPaginationAndSorting(offset, pageSize, field);
    }




}