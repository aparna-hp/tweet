package com.poc.tweet.service;

import com.poc.tweet.entity.Tweet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TweetServiceTest {

    @Autowired
    TweetService tweetService;

    @Test
    public void testAddTweet(){
        Tweet tweet = new Tweet();
        tweet.setUserId(1L);
        tweet.setCreateTimeStamp("" + System.currentTimeMillis());
        tweet.setContent("My first tweet.");

        tweetService.addTweet(tweet);
    }
}
