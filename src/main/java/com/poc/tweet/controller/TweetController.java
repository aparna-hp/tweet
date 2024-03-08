package com.poc.tweet.controller;

import com.poc.tweet.entity.Tweet;
import com.poc.tweet.service.TweetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@Slf4j
@Validated
@RequestMapping(path = {"/api/v1/tweeter/tweets"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TweetController {

    @Autowired
    TweetService tweetService;

    @Operation(summary = "Add a tweet.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Tweet is created", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Tweet.class))}),
            @ApiResponse(responseCode = "500", description = "Error creating the tweet.", content = @Content)})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addTweet(@Valid @RequestBody Tweet tweet){
        Tweet response = tweetService.addTweet(tweet);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update a tweet.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Tweet is updated", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Tweet.class))}),
            @ApiResponse(responseCode = "500", description = "Error updating the tweet.", content = @Content)})
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTweet(@Valid @RequestBody Tweet tweet){
        Tweet response = tweetService.updateTweet(tweet);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get tweet by Id.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Tweet obtained successfully.", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Tweet.class))}),
            @ApiResponse(responseCode = "204", description = "No tweet was found.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error getting the tweet.", content = @Content)})
    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getTweetById(@PathVariable(value = "id") Long id){
        Optional<Tweet> response = tweetService.getTweet(id);
        if(response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @Operation(summary = "Get tweet for the user.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Tweet obtained successfully.", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Tweet.class))}),
            @ApiResponse(responseCode = "204", description = "No tweet was found.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error getting the tweet.", content = @Content)})
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<Object> getTweet(@RequestParam(value = "id") Long userId){
        List<Tweet> response = tweetService.getTweetsByUserId(userId);
        if(response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete tweet by Id.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Tweet obtained successfully.", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Tweet.class))}),
            @ApiResponse(responseCode = "204", description = "No tweet was found.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error getting the tweet.", content = @Content)})
    @DeleteMapping
    public ResponseEntity<Object> deleteTweet(@RequestParam(value = "id") Long id){
        tweetService.deleteTweet(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
