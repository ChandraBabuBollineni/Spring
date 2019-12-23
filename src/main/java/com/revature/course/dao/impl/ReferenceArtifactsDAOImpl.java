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
import com.revature.course.dao.ReferenceArtifactsDAO;
import com.revature.course.exception.DBException;
import com.revature.course.model.ReferenceArtifacts;

@Repository
public class ReferenceArtifactsDAOImpl implements ReferenceArtifactsDAO {
	private final DataSource dataSource;
	@Autowired
	public ReferenceArtifactsDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet;

	public List<ReferenceArtifacts> viewReferenceArtifactsById(int id) throws DBException {
		List<ReferenceArtifacts> referenceArtifactsList = new ArrayList<ReferenceArtifacts>();
		try {
			connection = dataSource.getConnection();
			String sql = "select * from course_reference_artifacts where course_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ReferenceArtifacts referenceArtifacts = new ReferenceArtifacts();
				referenceArtifacts.setId(resultSet.getInt("id"));
				referenceArtifacts.setName(resultSet.getString("name"));
				referenceArtifacts.setType(resultSet.getString("type"));
				referenceArtifacts.setCourseId(resultSet.getInt("course_id"));
				referenceArtifacts.setFileName(resultSet.getString("file_name"));
				referenceArtifacts.setDescription(resultSet.getString("description"));
				referenceArtifactsList.add(referenceArtifacts);
			}
		} catch (SQLException e) {
			throw new DBException("Unable to add courses, exception:"+e.getMessage(), e);
		} catch (Exception e) {
			throw new DBException("Unable to add courses, exception:"+e.getMessage(), e);
		} finally {
			DBUtils.close(connection, preparedStatement, resultSet);
		}
		return referenceArtifactsList;
	}

}
