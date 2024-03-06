package com.poc.tweet.repository;

import com.poc.tweet.entity.Tweet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TweetRepository extends CrudRepository<Tweet, Long> {

    List<Tweet> findByUserId(Long userId);
}
