package com.revature.course.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.revature.course.configuration.DBUtils;
import com.revature.course.dao.ReferenceUrlDAO;
import com.revature.course.exception.DBException;
import com.revature.course.model.ReferenceUrl;

@Repository
public class ReferenceUrlDAOImpl implements ReferenceUrlDAO {
	private final DataSource dataSource;
	@Autowired
	public ReferenceUrlDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet;
	
	public List<ReferenceUrl> getReferenceUrlById(int id) throws DBException {
		List<ReferenceUrl> referenceUrlList = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			String sql = "select * from course_reference_urls where course_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ReferenceUrl referenceUrl = new ReferenceUrl();
				referenceUrl.setId(resultSet.getInt("id"));
				referenceUrl.setName(resultSet.getString("name"));
				referenceUrl.setType(resultSet.getString("type"));
				referenceUrl.setUrl(resultSet.getString("url"));
				referenceUrl.setCourseId(resultSet.getInt("course_id"));
				referenceUrl.setTutorial(resultSet.getBoolean("is_tutorial"));
				referenceUrl.setDescription(resultSet.getString("description"));
				referenceUrlList.add(referenceUrl);
			}
		}  catch (SQLException e) {
			throw new DBException("Unable to add courses, exception:"+e.getMessage(), e);
		} catch (Exception e) {
			throw new DBException("Unable to add courses, exception:"+e.getMessage(), e);
		} finally {
			DBUtils.close(connection, preparedStatement, resultSet);
		}
		return referenceUrlList;
	}
}
