package com.revature.course.dao;

import java.sql.SQLException;
import java.util.List;
import com.revature.course.exception.DBException;
import com.revature.course.model.ReferenceArtifacts;

public interface ReferenceArtifactsDAO {
	/**
	 * @param id
	 * @see java.sql.SQLException
	 * @exception SQLException - in case if SQL server is down or unable to perform operation
	 * @throws DBException
	 * @return referenceArtifactsList
	 */
	public List<ReferenceArtifacts> viewReferenceArtifactsById(int id) throws DBException;
}
