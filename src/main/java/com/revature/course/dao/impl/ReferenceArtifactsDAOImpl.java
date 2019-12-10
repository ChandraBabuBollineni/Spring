package com.revature.course.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.revature.course.configuration.DBUtils;
import com.revature.course.dao.ReferenceArtifactsDAO;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.exception.DBException;

@Repository
public class ReferenceArtifactsDAOImpl implements ReferenceArtifactsDAO {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet;

	@Override
	public List<ReferenceArtifactsDTO> viewReferenceArtifactsById(int id) {
		List<ReferenceArtifactsDTO> referenceArtifactsList = new ArrayList<ReferenceArtifactsDTO>();
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from course_reference_artifacts where course_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ReferenceArtifactsDTO referenceArtifactsDTO = new ReferenceArtifactsDTO();
				referenceArtifactsDTO.setId(resultSet.getInt("id"));
				referenceArtifactsDTO.setName(resultSet.getString("name"));
				referenceArtifactsDTO.setType(resultSet.getString("type"));
				referenceArtifactsDTO.setCourseId(resultSet.getInt("course_id"));
				referenceArtifactsDTO.setFileName(resultSet.getString("file_name"));
				referenceArtifactsDTO.setDescription(resultSet.getString("description"));
				referenceArtifactsList.add(referenceArtifactsDTO);
			}
		} catch (Exception e) {
			throw new DBException("unable to get reference artifacts", e);
		}
		return referenceArtifactsList;
	}

}
