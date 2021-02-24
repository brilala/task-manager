package com.hcl.taskmanager.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.taskmanager.model.Tasks;
import com.hcl.taskmanager.service.MyTasksService;

@Controller
public class TasksController {

	@Autowired
	private MyTasksService taskService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-tasks", method = RequestMethod.GET)
	public String showTasks(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("tasks", taskService.getTasksByUserName(name));
		return "list-tasks";

	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	@RequestMapping(value = "/add-tasks", method = RequestMethod.GET)
	public String showAddTasksPage(ModelMap model) {
		model.addAttribute("tasks", new Tasks());
		return "tasks";
	}

	@RequestMapping(value = "/delete-tasks", method = RequestMethod.GET)
	public String deleteTask(@RequestParam long id) {
		taskService.deleteTasks(id);
		return "redirect:/list-tasks";
	}

	@RequestMapping(value = "/update-tasks", method = RequestMethod.GET)
	public String showUpdateTasksPage(@RequestParam long id, ModelMap model) {
		Tasks tasks = taskService.getTasksById(id).get();
		model.put("tasks", tasks);
		return "tasks";
	}

	@RequestMapping(value = "/update-tasks", method = RequestMethod.POST)
	public String updateTasks(ModelMap model, @Valid Tasks tasks, BindingResult result) {
		if (result.hasErrors()) {
			return "tasks";
		}
		
		tasks.setUserName(getLoggedInUserName(model));
		taskService.updateTasks(tasks);
		return "redirect:/list-tasks";
	}

	@RequestMapping(value = "/add-tasks", method = RequestMethod.POST)
	public String addTasks(ModelMap model, @Valid Tasks tasks, BindingResult result) {
		if (result.hasErrors()) {
			return "tasks";
		}
		
		tasks.setUserName(getLoggedInUserName(model));
		taskService.saveTasks(tasks);
		return "redirect:/list-tasks";
	}
}