package com.poc.tweet.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TweetDTO {

    @Id
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private String content;
}
