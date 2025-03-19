package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class UserVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // Getters and Setters
}


