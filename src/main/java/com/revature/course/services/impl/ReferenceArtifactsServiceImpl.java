package com.revature.course.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.course.dao.ReferenceArtifactsDAO;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.exception.ServiceException;
import com.revature.course.services.ReferenceArtifactsService;

@Service
public class ReferenceArtifactsServiceImpl implements ReferenceArtifactsService{
	@Autowired
	private ReferenceArtifactsDAO referenceArtifactsRepository;

	public List<ReferenceArtifactsDTO> viewReferenceArtifactsById(int id) throws ServiceException {
		return referenceArtifactsRepository.viewReferenceArtifactsById(id);
	}
}
