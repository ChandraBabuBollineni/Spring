package com.revature.course.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.revature.course.configuration.DBUtils;
import com.revature.course.dao.ReferenceArtifactsDAO;
import com.revature.course.exception.DBException;
import com.revature.course.model.ReferenceArtifacts;

@Repository
public class ReferenceArtifactsDAOImpl implements ReferenceArtifactsDAO {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet;

	@Override
	public List<ReferenceArtifacts> viewReferenceArtifactsById(int id) {
		List<ReferenceArtifacts> referenceArtifactsList = new ArrayList<ReferenceArtifacts>();
		try {
			connection = DBUtils.getConnection();
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
		} catch (Exception e) {
			throw new DBException("unable to get reference artifacts", e);
		}
		return referenceArtifactsList;
	}

}
