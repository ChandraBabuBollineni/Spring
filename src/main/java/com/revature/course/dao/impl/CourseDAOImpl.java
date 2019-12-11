package com.revature.course.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.revature.course.configuration.DBUtils;
import com.revature.course.dao.CourseDAO;
import com.revature.course.dto.CategoryDTO;
import com.revature.course.dto.CourseDTO;
import com.revature.course.dto.LevelDTO;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.exception.DBException;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.dto.UsersDTO;

@Repository
public class CourseDAOImpl implements CourseDAO {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public int addCourse(CourseDTO courseDTO) {
		int courseId = 0;
		int result = 0;
		String sql = null;
		FileInputStream fis = null;
		try {
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			File fs = new File("C:/Users/Revature1/Downloads/rev-logo-2.png");
			fis = new FileInputStream(fs);
			String fileName = fs.getName();
			sql = "insert into courses (name,level_id,category_id,tags,slug,is_level_over_ride,enrollment_point,completion_point,is_presignup,is_loggedin_via_slug,description,meta_keyword,meta_description,icon,icon_name,created_by) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, courseDTO.getName());
			preparedStatement.setInt(2, courseDTO.getLevel().getId());
			preparedStatement.setInt(3, courseDTO.getCategory().getId());
			preparedStatement.setString(4, courseDTO.getTags());
			preparedStatement.setString(5, courseDTO.getSlug());
			preparedStatement.setBoolean(6, courseDTO.isLevelOverRide());
			preparedStatement.setInt(7, courseDTO.getEnrollmentPoint());
			preparedStatement.setInt(8, courseDTO.getCompletionPoint());
			preparedStatement.setBoolean(9, courseDTO.isPreSignUp());
			preparedStatement.setBoolean(10, courseDTO.isLoggedInViaSlug());
			preparedStatement.setString(11, courseDTO.getDescription());
			preparedStatement.setString(12, courseDTO.getMetaKeyword());
			preparedStatement.setString(13, courseDTO.getMetaDescription());
			preparedStatement.setBlob(14, fis);
			preparedStatement.setString(15, fileName);
			preparedStatement.setInt(16, courseDTO.getCreatedBy().getId());
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
			List<ReferenceArtifactsDTO> referenceArtifactsDTOList = courseDTO.getReferenceArtifactsId();
			sql = "insert into course_reference_artifacts(type,course_id,name,file,file_name,description) values(?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			for (ReferenceArtifactsDTO list : referenceArtifactsDTOList) {
				preparedStatement.setString(1, list.getType());
				preparedStatement.setInt(2, courseId);
				preparedStatement.setString(3, list.getName());
				preparedStatement.setBlob(4, fis);
				preparedStatement.setString(5, fileName);
				preparedStatement.setString(6, list.getDescription());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			List<ReferenceUrlDTO> referenceUrlDTOList = courseDTO.getRefernceUrlId();
			sql = "insert into course_reference_urls(type,course_id,name,url,is_tutorial,description) values(?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			for (ReferenceUrlDTO list : referenceUrlDTOList) {
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
			rollBackOperation(connection);
			throw new DBException("Unable to add courses", e);
		} catch (SQLException e) {
			rollBackOperation(connection);
			throw new DBException("Unable to add courses", e);
		} catch (Exception e) {
			rollBackOperation(connection);
			throw new DBException("Unable to add courses", e);
		} finally {
			DBUtils.close(connection, preparedStatement, fis);
		}
		return result;
	}

	public List<CourseDTO> viewCourses(String orderBy,String sortBy,int maxRows,int startIndex) {
		List<CourseDTO> courseList = new ArrayList<CourseDTO>();
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from courses as c,course_users as cu where c.created_by=cu.id order by ? desc LIMIT ? OFFSET ?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderBy);
			preparedStatement.setInt(2, maxRows);
			preparedStatement.setInt(3, startIndex);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				CourseDTO course = new CourseDTO();
				course.setId(resultSet.getInt("c.id"));
				course.setName(resultSet.getString("c.name"));
				LevelDTO level = new LevelDTO();
				level.setId(resultSet.getInt("level_id"));
				course.setLevel(level);
				CategoryDTO category = new CategoryDTO();
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
				UsersDTO usersDTO = new UsersDTO();
				usersDTO.setId(resultSet.getInt("cu.id"));
				usersDTO.setName(resultSet.getString("cu.name"));
				usersDTO.setEmail(resultSet.getString("cu.email"));
				course.setCreatedBy(usersDTO);
				courseList.add(course);
			}
		} catch (Exception e) {
			throw new DBException("Unable to view courses", e);
		} finally {
			DBUtils.close(connection, preparedStatement, resultSet);
		}
		return courseList;
	}

	public CourseDTO viewCourseById(int id) {
		CourseDTO course = null;
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from courses as c,course_users as cu where c.created_by=cu.id and c.id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				course = new CourseDTO();
				course.setId(resultSet.getInt("c.id"));
				course.setName(resultSet.getString("c.name"));
				LevelDTO level = new LevelDTO();
				level.setId(resultSet.getInt("level_id"));
				course.setLevel(level);
				CategoryDTO category = new CategoryDTO();
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
				UsersDTO usersDTO = new UsersDTO();
				usersDTO.setId(resultSet.getInt("cu.id"));
				usersDTO.setName(resultSet.getString("cu.name"));
				usersDTO.setEmail(resultSet.getString("cu.email"));
				course.setCreatedBy(usersDTO);
				course.setCreatedBy(usersDTO);
			}
		} catch (Exception e) {
			throw new DBException("Unable to view courses", e);
		} finally {
			DBUtils.close(connection, preparedStatement, resultSet);
		}
		return course;
	}

	@Override
	public int updateCourse(CourseDTO courseDTO) {
		int result = 0;
		String sql = null;
		FileInputStream fis = null;
		try {
			connection = DBUtils.getConnection();
			connection.setAutoCommit(false);
			File fs = new File("C:/Users/Revature1/Downloads/rev-logo-2.png");
			fis = new FileInputStream(fs);
			String fileName = fs.getName();
			sql = "update courses set level_id=?,category_id=?,tags=?,slug=?,is_level_over_ride=?,enrollment_point=?,completion_point=?,is_presignup=?,is_loggedin_via_slug=?,description=?,meta_keyword=?,meta_description=?,icon=?,icon_name=?,modified_on=CURRENT_TIMESTAMP,version=version+1 where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, courseDTO.getLevel().getId());
			preparedStatement.setInt(2, courseDTO.getCategory().getId());
			preparedStatement.setString(3, courseDTO.getTags());
			preparedStatement.setString(4, courseDTO.getSlug());
			preparedStatement.setBoolean(5, courseDTO.isLevelOverRide());
			preparedStatement.setInt(6, courseDTO.getEnrollmentPoint());
			preparedStatement.setInt(7, courseDTO.getCompletionPoint());
			preparedStatement.setBoolean(8, courseDTO.isPreSignUp());
			preparedStatement.setBoolean(9, courseDTO.isLoggedInViaSlug());
			preparedStatement.setString(10, courseDTO.getDescription());
			preparedStatement.setString(11, courseDTO.getMetaKeyword());
			preparedStatement.setString(12, courseDTO.getMetaDescription());
			preparedStatement.setBlob(13, fis);
			preparedStatement.setString(14, fileName);
			preparedStatement.setInt(15, courseDTO.getId());
			result = preparedStatement.executeUpdate();

			fs = new File("C:/Users/Revature1/Downloads/jaf-1_1_1.zip");
			fis = new FileInputStream(fs);
			fileName = fs.getName();
			List<ReferenceArtifactsDTO> referenceArtifactsDTOList = courseDTO.getReferenceArtifactsId();
			for (ReferenceArtifactsDTO list : referenceArtifactsDTOList) {

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

			List<ReferenceUrlDTO> referenceUrlDTOList = courseDTO.getRefernceUrlId();
			for (ReferenceUrlDTO list : referenceUrlDTOList) {
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
		} catch (SQLException e) {
			rollBackOperation(connection);
			e.printStackTrace();
			throw new DBException("unable to update course.");

		} catch (Exception e) {
			rollBackOperation(connection);
			e.printStackTrace();
			throw new DBException("unable to update course.");
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

	@Override
	public boolean deleteCourseById(int id) {
		String sql = null;
		int result = 0;
		boolean status = true;
		try {
			connection = DBUtils.getConnection();
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
				throw new DBException("unable to delete course.");

			connection.commit();
		} catch (Exception e) {
			status = false;
			rollBackOperation(connection);
			throw new DBException("Unable to delete course", e);
		} finally {
			DBUtils.close(connection, preparedStatement);
		}
		return status;
	}

	public void rollBackOperation(Connection connection) {
		try {
			connection.rollback();
		} catch (SQLException e) {
			throw new DBException("Unable to add course", e);
		}
	}

	@Override
	public boolean checkName(String name) {
		boolean status = false;
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from courses where name=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				status = true;
			}
		} catch (SQLException e) {
			throw new DBException("Unable to check courses name", e);
		} finally {
			DBUtils.close(connection, preparedStatement, resultSet);
		}
		return status;
	}

}
