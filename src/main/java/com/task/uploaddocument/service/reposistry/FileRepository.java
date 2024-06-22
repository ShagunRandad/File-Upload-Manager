package com.task.uploaddocument.service.reposistry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.uploaddocument.Entity.File;

public interface FileRepository extends JpaRepository<File, Integer> {

}
