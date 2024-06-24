package com.task.uploaddocument.service.reposistry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.uploaddocument.Entity.Login;

public interface LoginRegisterReposistry extends JpaRepository<Login,Integer> {
	Optional<Login> findByEmail(String email);
}

