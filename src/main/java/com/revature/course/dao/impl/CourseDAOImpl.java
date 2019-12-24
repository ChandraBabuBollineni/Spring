package com.revature.course.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.revature.course.configuration.DBUtils;
import com.revature.course.dao.CourseDAO;
import com.revature.course.exception.DBException;
import com.revature.course.model.Category;
import com.revature.course.model.Course;
import com.revature.course.model.CourseUsers;
import com.revature.course.model.Level;
import com.revature.course.model.ReferenceArtifacts;
import com.revature.course.model.ReferenceUrl;


@Repository
public class CourseDAOImpl implements CourseDAO {
	private static final Logger logger = LoggerFactory.getLogger(CourseDAOImpl.class);
	private final DataSource dataSource;
	@Autowired
	public CourseDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public int addCourse(Course course) throws DBException {
		int courseId = 0;
		int result = 0;
		String sql = null;
		FileInputStream fis = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			File fs = new File("C:/Users/Revature1/Downloads/rev-logo-2.png");
			fis = new FileInputStream(fs);
			String fileName = fs.getName();
			sql = "insert into courses (name,level_id,category_id,tags,slug,is_level_over_ride,enrollment_point,completion_point,is_presignup,is_loggedin_via_slug,description,meta_keyword,meta_description,icon,icon_name,created_by) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, course.getName());
			preparedStatement.setInt(2, course.getLevel().getId());
			preparedStatement.setInt(3, course.getCategory().getId());
			preparedStatement.setString(4, course.getTags());
			preparedStatement.setString(5, course.getSlug());
			preparedStatement.setBoolean(6, course.isLevelOverRide());
			preparedStatement.setInt(7, course.getEnrollmentPoint());
			preparedStatement.setInt(8, course.getCompletionPoint());
			preparedStatement.setBoolean(9, course.isPreSignUp());
			preparedStatement.setBoolean(10, course.isLoggedInViaSlug());
			preparedStatement.setString(11, course.getDescription());
			preparedStatement.setString(12, course.getMetaKeyword());
			preparedStatement.setString(13, course.getMetaDescription());
			preparedStatement.setBlob(14, fis);
			preparedStatement.setString(15, fileName);
			preparedStatement.setInt(16, course.getCreatedBy().getId());
			result = preparedStatement.executeUpdate();
			if (result != 0) {
				sql = "select last_insert_id()";
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					courseId = resultSet.getInt("last_insert_id()");
				}
			}

			fs = new File("C:/Users/Revature1/Downloads/jaf-1_1_1.zip");
			fis = new FileInputStream(fs);
			fileName = fs.getName();
			List<ReferenceArtifacts> referenceArtifactsList = course.getReferenceArtifacts();
			sql = "insert into course_reference_artifacts(type,course_id,name,file,file_name,description) values(?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			for (ReferenceArtifacts list : referenceArtifactsList) {
				preparedStatement.setString(1, list.getType());
				preparedStatement.setInt(2, courseId);
				preparedStatement.setString(3, list.getName());
				preparedStatement.setBlob(4, fis);
				preparedStatement.setString(5, fileName);
				preparedStatement.setString(6, list.getDescription());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			List<ReferenceUrl> referenceUrlList = course.getRefernceUrl();
			sql = "insert into course_reference_urls(type,course_id,name,url,is_tutorial,description) values(?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			for (ReferenceUrl list : referenceUrlList) {
				preparedStatement.setString(1, list.getType());
				preparedStatement.setInt(2, courseId);
				preparedStatement.setString(3, list.getName());
				preparedStatement.setString(4, list.getUrl());
				preparedStatement.setBoolean(5, list.isTutorial());
				preparedStatement.setString(6, list.getDescription());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (BatchUpdateException e) {
			logger.error("Unable to add courses, exception:"+e.getMessage());
			rollBackOperation(connection);
			throw new DBException("Unable to add courses, exception:"+e.getMessage(), e);
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			logger.error("Unable to add courses, exception:"+e.getMessage());
			rollBackOperation(connection);
			throw new DBException("Unable to add course, exception:"+e.getMessage(), e);
		}
		catch (SQLException e) {
			logger.error("Unable to add courses, exception:"+e.getMessage());
			rollBackOperation(connection);
			throw new DBException("Unable to add courses, exception:"+e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Unable to add courses, exception:"+e.getMessage());
			rollBackOperation(connection);
			throw new DBException("Unable to add courses, exception:"+e.getMessage(), e);
		} finally {
			DBUtils.close(connection, preparedStatement, fis);
		}
		System.out.println(result);
		return result;
	}

	public List<Course> viewCourses(String orderBy,String sortBy,int maxRows,int startIndex) throws DBException {
		List<Course> courseList = new ArrayList<Course>();
		try {
			connection = dataSource.getConnection();
			String sql = "select c.id,c.name,level_id,category_id,c.tags,c.slug,c.is_level_over_ride,c.enrollment_point,c.completion_point,c.is_presignup,c.is_loggedin_via_slug,c.description,c.meta_keyword,c.meta_description,c.icon,c.icon_name,c.created_by,c.version,cu.id,cu.name,cu.email from courses as c,course_users as cu where c.created_by=cu.id order by ? LIMIT ? OFFSET ?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderBy);
			preparedStatement.setInt(2, maxRows);
			preparedStatement.setInt(3, startIndex);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Course course = new Course();
				course.setId(resultSet.getInt("c.id"));
				course.setName(resultSet.getString("c.name"));
				Level level = new Level();
				level.setId(resultSet.getInt("level_id"));
				course.setLevel(level);
				Category category = new Category();
				category.setId(resultSet.getInt("category_id"));
				course.setCategory(category);
				course.setTags(resultSet.getString("c.tags"));
				course.setSlug(resultSet.getString("c.slug"));
				course.setLevelOverRide(resultSet.getBoolean("c.is_level_over_ride"));
				course.setEnrollmentPoint(resultSet.getInt("c.enrollment_point"));
				course.setCompletionPoint(resultSet.getInt("c.completion_point"));
				course.setPreSignUp(resultSet.getBoolean("c.is_presignup"));
				course.setLoggedInViaSlug(resultSet.getBoolean("c.is_loggedin_via_slug"));
				course.setDescription(resultSet.getString("c.description"));
				course.setMetaKeyword(resultSet.getString("c.meta_keyword"));
				course.setMetaDescription(resultSet.getString("c.meta_description"));
				// course.setIcon(resultSet.getBlob("c.icon"));
				course.setVersion(resultSet.getInt("c.version"));
				CourseUsers users = new CourseUsers();
				users.setId(resultSet.getInt("cu.id"));
				users.setName(resultSet.getString("cu.name"));
				users.setEmail(resultSet.getString("cu.email"));
				course.setCreatedBy(users);
				courseList.add(course);
			}
		} catch (SQLException e) {
			logger.error("Unable to view courses, exception:"+e.getMessage());
			throw new DBException("Unable to view courses, exception:"+e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Unable to view courses, exception:"+e.getMessage());
			throw new DBException("Unable to view courses, exception:"+e.getMessage(), e);
		} finally {
			DBUtils.close(connection, preparedStatement,resultSet);
		}
		return courseList;
	}

	public Course viewCourseById(int id) throws DBException {
		Course course = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select c.id,c.name,level_id,category_id,c.tags,c.slug,c.is_level_over_ride,c.enrollment_point,c.completion_point,c.is_presignup,c.is_loggedin_via_slug,c.description,c.meta_keyword,c.meta_description,c.icon,c.icon_name,c.created_by,c.version,cu.id,cu.name,cu.email from courses as c,course_users as cu where c.created_by=cu.id and c.id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				course = new Course();
				course.setId(resultSet.getInt("c.id"));
				course.setName(resultSet.getString("c.name"));
				Level level = new Level();
				level.setId(resultSet.getInt("level_id"));
				course.setLevel(level);
				Category category = new Category();
				category.setId(resultSet.getInt("category_id"));
				course.setCategory(category);
				course.setTags(resultSet.getString("c.tags"));
				course.setSlug(resultSet.getString("c.slug"));
				course.setLevelOverRide(resultSet.getBoolean("c.is_level_over_ride"));
				course.setEnrollmentPoint(resultSet.getInt("c.enrollment_point"));
				course.setCompletionPoint(resultSet.getInt("c.completion_point"));
				course.setPreSignUp(resultSet.getBoolean("c.is_presignup"));
				course.setLoggedInViaSlug(resultSet.getBoolean("c.is_loggedin_via_slug"));
				course.setDescription(resultSet.getString("c.description"));
				course.setMetaKeyword(resultSet.getString("c.meta_keyword"));
				course.setMetaDescription(resultSet.getString("c.meta_description"));
				// course.setIcon(resultSet.getBlob("c.icon"));
				course.setVersion(resultSet.getInt("c.version"));
				CourseUsers users = new CourseUsers();
				users.setId(resultSet.getInt("cu.id"));
				users.setName(resultSet.getString("cu.name"));
				users.setEmail(resultSet.getString("cu.email"));
				course.setCreatedBy(users);
				course.setCreatedBy(users);
			}
		} catch (SQLException e) {
			logger.error("Unable to view course by id, exception:"+e.getMessage());
			rollBackOperation(connection);
			throw new DBException("Unable to view course by id, exception:"+e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Unable to view course by id, exception:"+e.getMessage());
			rollBackOperation(connection);
			throw new DBException("Unable to view course by id, exception:"+e.getMessage(), e);
		} finally {
			DBUtils.close(connection, preparedStatement, resultSet);
		}
		return course;
	}


	public int updateCourse(Course course) throws DBException {
		int result = 0;
		String sql = null;
		FileInputStream fis = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			File fs = new File("C:/Users/Revature1/Downloads/rev-logo-2.png");
			fis = new FileInputStream(fs);
			String fileName = fs.getName();
			sql = "update courses set level_id=?,category_id=?,tags=?,slug=?,is_level_over_ride=?,enrollment_point=?,completion_point=?,is_presignup=?,is_loggedin_via_slug=?,description=?,meta_keyword=?,meta_description=?,icon=?,icon_name=?,modified_on=CURRENT_TIMESTAMP,version=version+1 where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, course.getLevel().getId());
			preparedStatement.setInt(2, course.getCategory().getId());
			preparedStatement.setString(3, course.getTags());
			preparedStatement.setString(4, course.getSlug());
			preparedStatement.setBoolean(5, course.isLevelOverRide());
			preparedStatement.setInt(6, course.getEnrollmentPoint());
			preparedStatement.setInt(7, course.getCompletionPoint());
			preparedStatement.setBoolean(8, course.isPreSignUp());
			preparedStatement.setBoolean(9, course.isLoggedInViaSlug());
			preparedStatement.setString(10, course.getDescription());
			preparedStatement.setString(11, course.getMetaKeyword());
			preparedStatement.setString(12, course.getMetaDescription());
			preparedStatement.setBlob(13, fis);
			preparedStatement.setString(14, fileName);
			preparedStatement.setInt(15, course.getId());
			result = preparedStatement.executeUpdate();

			fs = new File("C:/Users/Revature1/Downloads/jaf-1_1_1.zip");
			fis = new FileInputStream(fs);
			fileName = fs.getName();
			List<ReferenceArtifacts> referenceArtifactsList = course.getReferenceArtifacts();
			for (ReferenceArtifacts list : referenceArtifactsList) {

				sql = "update course_reference_artifacts set name=?,file=?,file_name=?,description=? where id=?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, list.getName());
				preparedStatement.setBlob(2, fis);
				preparedStatement.setString(3, fileName);
				preparedStatement.setString(3, list.getDescription());
				preparedStatement.setString(4, fileName);
				preparedStatement.setInt(5, list.getId());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();

			List<ReferenceUrl> referenceUrlList = course.getRefernceUrl();
			for (ReferenceUrl list : referenceUrlList) {
				sql = "update course_reference_urls set name=?,url=?,is_tutorial=?,description=? where id=?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, list.getName());
				preparedStatement.setString(2, list.getUrl());
				preparedStatement.setBoolean(3, list.isTutorial());
				preparedStatement.setString(4, list.getDescription());
				preparedStatement.setInt(5, list.getId());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
		}catch (BatchUpdateException e) {
			logger.error("unable to update course, exception:"+e.getMessage());
			rollBackOperation(connection);
			throw new DBException("unable to update course, exception:"+e.getMessage());

		} catch (SQLException e) {
			logger.error("unable to update course, exception:"+e.getMessage());
			rollBackOperation(connection);
			throw new DBException("unable to update course, exception:"+e.getMessage());

		} catch (Exception e) {
			logger.error("unable to update course, exception:"+e.getMessage());
			rollBackOperation(connection);
			throw new DBException("unable to update course, exception:"+e.getMessage());
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			DBUtils.close(connection, preparedStatement);
		}
		return result;
	}

	
	public boolean deleteCourseById(int id) throws DBException {
		String sql = null;
		int result = 0;
		boolean status = true;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);

			sql = "delete from course_reference_artifacts where course_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate();
			if (result == 0)
				throw new DBException("unable to delete reference artifacts.");

			sql = "delete from course_reference_urls where course_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate();
			if (result == 0)
				throw new DBException("unable to delete reference urls.");

			sql = "delete from courses where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate();
			if (result == 0)
				throw new DBException("unable to delete course");

			connection.commit();
		} catch (SQLException e) {
			status=false;
			logger.error("Unable to delete course by id, exception:"+e.getMessage());
			rollBackOperation(connection);
			throw new DBException("Unable to delete course by id, exception:"+e.getMessage(), e);
		} catch (Exception e) {
			status=false;
			logger.error("Unable to delete course by id, exception:"+e.getMessage());
			rollBackOperation(connection);
			throw new DBException("Unable to delete course by id, exception:"+e.getMessage(), e);
		} finally {
			DBUtils.close(connection, preparedStatement);
		}
		return status;
	}
	
	public void rollBackOperation(Connection connection) throws DBException {
		try {
			connection.rollback();
		} catch (SQLException e) {
			logger.error("Unable to roll back, exception:"+e.getMessage());
			throw new DBException("Unable to roll back, exception:"+e.getMessage(), e);
		}
	}
}
