package com.example.demo.service;

import com.example.demo.models.Movie;
import com.example.demo.models.UserVote;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserVoteRepository userVoteRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void voteForMovie(Long movieId, String name, String email) {
        if (userVoteRepository.existsByEmail(email)) {
            throw new RuntimeException("You have already voted!");
        }

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        UserVote userVote = new UserVote();
        userVote.setName(name);
        userVote.setEmail(email);
        userVote.setMovie(movie);
        userVoteRepository.save(userVote);

        movie.setVotes(movie.getVotes() + 1);
        movieRepository.save(movie);
    }

    public List<Movie> getVotingResults() {
        return movieRepository.findAll(Sort.by(Sort.Direction.DESC, "votes"));
    }

    public Movie getWinningMovie() {
        return movieRepository.findAll(Sort.by(Sort.Direction.DESC, "votes"))
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No movies found"));
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long movieId, Movie updatedMovie) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.setTitle(updatedMovie.getTitle());
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new RuntimeException("Movie not found");
        }
        movieRepository.deleteById(movieId);
    }
}

