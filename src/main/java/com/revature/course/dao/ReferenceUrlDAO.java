package com.revature.course.dao;

import java.sql.SQLException;
import java.util.List;
import com.revature.course.exception.DBException;
import com.revature.course.model.ReferenceUrl;


public interface ReferenceUrlDAO {
	/**
	 * @param id
	 * @see java.sql.SQLException
	 * @exception SQLException - in case if SQL server is down or unable to perform operation
	 * @throws DBException
	 * @return referenceUrlList
	 */
	public List<ReferenceUrl> getReferenceUrlById(int id) throws DBException;
}
