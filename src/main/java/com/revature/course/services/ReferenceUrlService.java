package com.revature.course.services;

import java.util.List;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.exception.DBException;

public interface ReferenceUrlService {
	/**
	 * @param id
	 * @see com.revature.course.dto.ReferenceUrlDTO
	 * @see com.revature.course.exception.DBException
	 * @throws DBException in case of SQLException in case SQL server issues
	 * @return ReferenceUrlDTO List
	 */
	public List<ReferenceUrlDTO> viewReferenceUrlById(int id) throws DBException;
}
