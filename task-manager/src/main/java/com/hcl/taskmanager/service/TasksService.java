package com.hcl.taskmanager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.taskmanager.model.Tasks;
import com.hcl.taskmanager.repository.TaskRepository;

@Service
public class TasksService implements MyTasksService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<Tasks> getTasksByUserName(String userName) {
		return taskRepository.findByUserName(userName);
	}

	@Override
	public Optional<Tasks> getTasksById(long id) {
		return taskRepository.findById(id);
	}

	@Override
	public void updateTasks(Tasks tasks) {
		taskRepository.save(tasks);
	}

	@Override
	public void addTasks(String userName, String taskName, Date startDate, Date endDate, String severity) {
		taskRepository.save(new Tasks(userName, taskName, startDate, endDate, severity));
	}

	@Override
	public void deleteTasks(long id) {
		Optional<Tasks> tasks = taskRepository.findById(id);
		if (tasks.isPresent()) {
			taskRepository.delete(tasks.get());
		}
	}

	@Override
	public void saveTasks(Tasks tasks) {
		taskRepository.save(tasks);
	}

}
