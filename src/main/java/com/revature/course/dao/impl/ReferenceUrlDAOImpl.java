package com.revature.course.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.revature.course.configuration.DBUtils;
import com.revature.course.dao.ReferenceUrlDAO;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.exception.DBException;

@Repository
public class ReferenceUrlDAOImpl implements ReferenceUrlDAO {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet;

	public List<ReferenceUrlDTO> getReferenceUrlById(int id) {
		List<ReferenceUrlDTO> referenceUrlList = new ArrayList<>();
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from course_reference_urls where course_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ReferenceUrlDTO referenceUrlDTO = new ReferenceUrlDTO();
				referenceUrlDTO.setId(resultSet.getInt("id"));
				referenceUrlDTO.setName(resultSet.getString("name"));
				referenceUrlDTO.setType(resultSet.getString("type"));
				referenceUrlDTO.setUrl(resultSet.getString("url"));
				referenceUrlDTO.setCourseId(resultSet.getInt("course_id"));
				referenceUrlDTO.setTutorial(resultSet.getBoolean("is_tutorial"));
				referenceUrlDTO.setDescription(resultSet.getString("description"));
				referenceUrlList.add(referenceUrlDTO);
			}
		} catch (Exception e) {
			throw new DBException("unable to get reference artifacts", e);
		}
		return referenceUrlList;
	}
}
