package com.jcg.examples.delegate;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.jcg.examples.controller.LoginController;
import com.jcg.examples.service.UserService;

public class LoginDelegate
{
	   private static final Logger LOGGER = Logger.getLogger(LoginDelegate.class);

	
		private UserService userService;

		public UserService getUserService()
		{
				return this.userService;
		}

		public void setUserService(UserService userService)
		{
				this.userService = userService;
		}

		public boolean isValidUser(String username, String password) throws SQLException
    {
		    return userService.isValidUser(username, password);
    }
}
