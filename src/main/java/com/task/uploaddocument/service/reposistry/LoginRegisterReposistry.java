package com.task.uploaddocument.service.reposistry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.uploaddocument.Entity.Login;

public interface LoginRegisterReposistry extends JpaRepository<Login,Integer> {

}
