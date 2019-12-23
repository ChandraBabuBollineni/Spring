package com.revature.course.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.course.exception.DBException;

public class DBUtils {
	/**
	 * 
	 * @param connection
	 * @param preparedStatement
	 * @throws DBException
	 * @see java.sql.Connection
	 * @see java.sql.Connection
	 * @see com.revature.course.exception.DBException
	 */
	public static void close(Connection connection, PreparedStatement preparedStatement) throws DBException {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DBException("unable to close preparedStatement object.", e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DBException("unable to close connection object. ", e);
			}
		}
	}
/**
 * 
 * @param connection
 * @param preparedStatement
 * @param resultSet
 * @throws DBException
 * @see java.sql.Connection
 * @see java.sql.Connection
 * @see java.sql.ResultSet
 * @see com.revature.course.exception.DBException
 */
	public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
			throws DBException {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DBException("unable to close ResultSet object.", e);
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DBException("unable to close preparedStatement object.", e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DBException("unable to close connection object. ", e);
			}
		}
	}
	/**
	 * 
	 * @param connection
	 * @param preparedStatement
	 * @param fileInputStream
	 * @throws DBException
	 * @see java.sql.Connection
	 * @see java.sql.Connection
	 * @see java.io.FileInputStream
	 * @see com.revature.course.exception.DBException
	 */
	public static void close(Connection connection, PreparedStatement preparedStatement, FileInputStream fis)
			throws DBException {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				throw new DBException("unable to close FileInputStream object.", e);
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw new DBException("unable to close preparedStatement object.", e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DBException("unable to close connection object. ", e);
			}
		}
	}
}