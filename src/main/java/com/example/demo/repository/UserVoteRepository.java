package com.example.demo.repository;

import com.example.demo.models.UserVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVoteRepository extends JpaRepository<UserVote, Long> {
    boolean existsByEmail(String email);
}

