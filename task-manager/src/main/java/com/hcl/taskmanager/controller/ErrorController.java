package com.hcl.taskmanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception exception) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("exception", exception.getLocalizedMessage());
		mav.addObject("url", request.getRequestURL());

		mav.setViewName("error");

		return mav;

	}

}
