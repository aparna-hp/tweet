package com.poc.tweet.service;

import com.poc.tweet.entity.Tweet;
import com.poc.tweet.repository.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TweetService {

    @Autowired
    TweetRepository tweetRepository;

    public Tweet addTweet(Tweet tweet){
        return tweetRepository.save(tweet);
    }

    public Optional<Tweet> getTweet(Long tweetId){
        return tweetRepository.findById(tweetId);
    }

    public List<Tweet> getTweetsByUserId(Long userId){
        return tweetRepository.findByUserId(userId);
    }

    public Tweet updateTweet(Tweet tweet){
        return tweetRepository.save(tweet);
    }

    public void deleteTweet(Long tweeId){
        tweetRepository.deleteById(tweeId);
    }
}
