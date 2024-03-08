package com.poc.tweet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.tweet.entity.Tweet;
import com.poc.tweet.kafka.MessageProducer;
import com.poc.tweet.repository.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TweetService {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    MessageProducer messageProducer;

    @Value("${app.tweet.topic}")
    String topic;

    public Tweet addTweet(Tweet tweet){
        Tweet newTweet = tweetRepository.save(tweet);
        newTweet.setCreateTimeStamp(""+ System.currentTimeMillis());
        sendTweetMessage(newTweet);
        return newTweet;
    }

    public Optional<Tweet> getTweet(Long tweetId){
        return tweetRepository.findById(tweetId);
    }

    public List<Tweet> getTweetsByUserId(Long userId){
        return tweetRepository.findByUserId(userId);
    }

    public Tweet updateTweet(Tweet tweet){
        Tweet newTweet = tweetRepository.save(tweet);
        newTweet.setCreateTimeStamp(""+ System.currentTimeMillis());
        sendTweetMessage(newTweet);
        return newTweet;
    }

    public void deleteTweet(Long tweeId){
        tweetRepository.deleteById(tweeId);
    }

    private void sendTweetMessage(Tweet tweet){
        ObjectMapper objectMapper = new ObjectMapper();
        String tweetMessage = null;
        try {
            tweetMessage = objectMapper.writeValueAsString(tweet);
        } catch (JsonProcessingException e) {
            log.error("Error creating the tweet message.", e);
        }
        messageProducer.sendMessage(topic, tweetMessage);
    }
}
