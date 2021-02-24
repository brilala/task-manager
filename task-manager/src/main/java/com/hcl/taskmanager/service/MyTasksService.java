package com.hcl.taskmanager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.hcl.taskmanager.model.Tasks;

public interface MyTasksService {

	List<Tasks> getTasksByUserName(String userName);

	Optional<Tasks> getTasksById(long id);

	void updateTasks(Tasks tasks);

	void addTasks(String userName, String taskName, Date startDate, Date endDate, String severity);

	void deleteTasks(long id);

	void saveTasks(Tasks tasks);

}
