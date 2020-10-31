package com.jcg.examples.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jcg.examples.delegate.LoginDelegate;
import com.jcg.examples.viewBean.LoginBean;


@Controller
public class LoginController
{
	   private static final Logger LOGGER = Logger.getLogger(LoginController.class);

		@Autowired
		private LoginDelegate loginDelegate;

		@RequestMapping(value="/login",method=RequestMethod.GET)
		public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean)
		{
			ModelAndView model = new ModelAndView("login");
			//LoginBean loginBean = new LoginBean();
			model.addObject("loginBean", loginBean);
			return model;
		}
		@RequestMapping(value="/login",method=RequestMethod.POST)
		public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginBean loginBean)
		{
				ModelAndView model= null;
				try
				{
						boolean isValidUser = loginDelegate.isValidUser(loginBean.getUsername(), loginBean.getPassword());
						if(isValidUser)
						{
								System.out.println("User Login Successful");
								LOGGER.info(" Login Successful Into Controller Module");
								request.setAttribute("loggedInUser", loginBean.getUsername());
								model = new ModelAndView("welcome");
						}
						else
						{
								model = new ModelAndView("login");
								
								if (LOGGER.isInfoEnabled() ){
									LOGGER.info("LoginController User Login Successful Into Controller Module Invalid credentials!!");
	
								}
								
								if (LOGGER.isDebugEnabled() ){
									LOGGER.info("LoginController User Login Successful Into Controller Module Invalid credentials!!");
	
								}
								
								
																request.setAttribute("message", "Invalid credentials!!");
						}

				}
				catch(Exception e)
				{
						e.printStackTrace();
						
						//logs exception
					      LOGGER.error("Logging a sample exception", new Exception("Testing"));
				}

				return model;
		}
}
