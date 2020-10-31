/**
 *
 */
package com.jcg.examples.service;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.jcg.examples.controller.LoginController;

/**
 * @author CENTAUR
 *
 */
public interface UserService
{
	

		public boolean isValidUser(String username, String password) throws SQLException;
}
