package com.poc.tweet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.tweet.entity.Tweet;
import com.poc.tweet.service.TweetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TweetControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TweetService tweetService;

    private static final Logger logger =
            LogManager.getLogger(TweetControllerTest.class);


    @Test
    public void testAddTweet() throws Exception {
        Tweet tweet = new Tweet();
        tweet.setId(1L);
        tweet.setUserId(1L);
        tweet.setContent("My first tweet app");

        Mockito.doReturn(tweet).when(tweetService).addTweet(Mockito.any(Tweet.class));

        ObjectMapper mapper = new ObjectMapper();
        String requestJson=mapper.writeValueAsString(tweet);

        logger.info("Input Json = " + requestJson);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tweeter/tweets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated());


    }

}
