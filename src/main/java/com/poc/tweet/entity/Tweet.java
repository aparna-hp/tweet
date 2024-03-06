package com.poc.tweet.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Tweet {

    @Id
    private Long id;

    private Long userId;

    private String content;

    private String createTimeStamp;
}
