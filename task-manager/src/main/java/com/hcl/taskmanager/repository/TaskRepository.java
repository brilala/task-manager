package com.hcl.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.taskmanager.model.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
	List<Tasks> findByUserName(String userName);

}
