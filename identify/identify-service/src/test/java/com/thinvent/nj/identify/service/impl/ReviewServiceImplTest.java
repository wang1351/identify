package com.thinvent.nj.identify.service.impl;

import com.thinvent.nj.identify.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewServiceImplTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    public void getRandomExpertListByReviewId() throws Exception {

       // Map<Integer, List<String>> experts = reviewService.getRandomExpertListByReviewId("7BC516BEDDD483A9E05010AC0C054E22");

        //System.out.println(experts);

    }

}