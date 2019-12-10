package com.revature.course.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.course.exception.DBException;

public class DBUtils {
	public static Connection getConnection()
		{
		String driverClassName = "com.mysql.cj.jdbc.Driver";
	       String url = "jdbc:mysql://localhost:3306/chandra_db";
	       String username = "root";
	       String password = "root";
		Connection conn=null;
		
		try 
			{
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
			}
		catch (ClassNotFoundException e)
			{
			throw new DBException("unable to load driver class "+e.getMessage());
			}
		catch (SQLException e) 
			{
			throw new DBException("unable to get DataBase Connection "+e.getMessage());
			} 
		
		
		return conn;
	}
	public static void close(Connection connection,PreparedStatement preparedStatement) 
	{
		if(preparedStatement!=null) 
			{
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DBException("unable to close preparedStatement object.",e);
			}
			}
		if(connection!=null) 
		{
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DBException("unable to close connection object. ",e);
			}
		}
	}
	
	public static void close(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet) 
	{
		if(resultSet!=null) 
		{
		try {
			resultSet.close();
		} catch (SQLException e) {
			throw new DBException("unable to close ResultSet object.",e);
		}
		}
		if(preparedStatement!=null) 
			{
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DBException("unable to close preparedStatement object.",e);
			}
			}
		if(connection!=null) 
		{
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DBException("unable to close connection object. ",e);
			}
		}
	}
	public static void close(Connection connection, PreparedStatement preparedStatement, FileInputStream fis) {
		if(fis!=null) 
		{
		try {
			fis.close();
		} catch (IOException e) {
			throw new DBException("unable to close FileInputStream object.",e);
		}
		}
		if(preparedStatement!=null) 
			{
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DBException("unable to close preparedStatement object.",e);
			}
			}
		if(connection!=null) 
		{
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DBException("unable to close connection object. ",e);
			}
		}
		
	}
	
}