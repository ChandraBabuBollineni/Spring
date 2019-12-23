package com.revature.course.services;

import java.util.List;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.exception.DBException;
import com.revature.course.exception.ServiceException;


public interface ReferenceArtifactsService {
	/**
	 * @param id
	 * @see com.revature.course.dto.ReferenceArtifactsDTO
	 * @see com.revature.course.exception.DBException
	 * @see com.revature.course.exception.ServiceException
	 * @throws DBException in case of SQLException in case SQL server issues
	 * @throws ServiceException in case exception occurs in Service Layer
	 * @return ReferenceArtifactsDTO List
	 */
	public List<ReferenceArtifactsDTO> viewReferenceArtifactsById(int id) throws ServiceException,DBException;
}
