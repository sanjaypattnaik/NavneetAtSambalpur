package com.jcg.examples.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.jcg.examples.dao.UserDao;


/**
 * @author CENTAUR
 */
public class UserDaoImpl implements UserDao
{

	
	   private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

		DataSource dataSource;

		public DataSource getDataSource()
		{
				return this.dataSource;
		}

		public void setDataSource(DataSource dataSource)
		{
				this.dataSource = dataSource;
		}

		@Override
		public boolean isValidUser(String username, String password) throws SQLException
		{
			
			LOGGER.info("INTO DB - Valid User   " + username + "<====  xxx  ==>" + password);
				String query = "Select count(1) from user where username = ? and password = ?";
				LOGGER.info(" <====    Query Created   ====> ");
				
				
				 LOGGER.info("<======    skp == Connection trying out   ============= >");
				 Connection conn = dataSource.getConnection();
				 LOGGER.info("<======    skp == Connection is    ============= >" + conn);
				 
				 if (conn!=null) {
					 LOGGER.info("<======    skp == Connection is NOT NULL   ============= >");
				 }
				
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				
				 if (pstmt!=null) {
					 LOGGER.info("<======    skp == Prepare Statement is NOT NULL   ============= >" + pstmt);
				 }
				
				 
				
				
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				LOGGER.info("BEFORE QUERY EXECUTED ");
				
				try {
					ResultSet resultSet = pstmt.executeQuery();
				
					
					if (resultSet!=null) {
						 LOGGER.info("<======    skp == resultSet is NOT NULL   ============= >" + resultSet);
					 }
					
					
					
					
					if (resultSet.next()) {
						LOGGER.info("SANAJY ======= >User Login Successful");
							return (resultSet.getInt(1) > 0);
					}
					else {
							return false;
					}

				} catch (Throwable  e) {
					StackTraceElement[] stack = e.getStackTrace();
				    String exception = "";
				    for (StackTraceElement s : stack) {
				        exception = exception + s.toString() + "\n\t\t";
				        
				    }
				    LOGGER.info(exception);
						
				}
				
				LOGGER.info("AFTER QUERY EXECUTED");
				return true;
		}

}