package com.example.demo.controller;

import com.example.demo.models.Movie;
import com.example.demo.models.VoteRequest;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/list")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("/vote/{movieId}")
    public ResponseEntity<String> vote(@PathVariable Long movieId, @RequestBody VoteRequest voteRequest) {
        movieService.voteForMovie(movieId, voteRequest.getName(), voteRequest.getEmail());
        return ResponseEntity.ok("Vote cast successfully!");
    }

    @GetMapping("/results")
    public List<Movie> getResults() {
        return movieService.getVotingResults();
    }

    @GetMapping("/winner")
    public ResponseEntity<Movie> getWinner() {
        return ResponseEntity.ok(movieService.getWinningMovie());
    }
}
