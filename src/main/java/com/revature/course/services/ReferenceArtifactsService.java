package com.revature.course.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.exception.ServiceException;

@Service
public interface ReferenceArtifactsService {
	public List<ReferenceArtifactsDTO> viewReferenceArtifactsById(int id) throws ServiceException;
}
