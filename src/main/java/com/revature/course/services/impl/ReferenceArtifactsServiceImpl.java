package com.revature.course.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.course.dao.ReferenceArtifactsDAO;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.exception.DBException;
import com.revature.course.exception.ServiceException;
import com.revature.course.model.ReferenceArtifacts;
import com.revature.course.services.ReferenceArtifactsService;

@Service
public class ReferenceArtifactsServiceImpl implements ReferenceArtifactsService{
	private ReferenceArtifactsDAO referenceArtifactsRepository;
	private ModelMapper modelMapper=new ModelMapper();
	
	@Autowired
	   public ReferenceArtifactsServiceImpl(ReferenceArtifactsDAO referenceArtifactsRepository) {
	      this.referenceArtifactsRepository = referenceArtifactsRepository;    
	   }

	public List<ReferenceArtifactsDTO> viewReferenceArtifactsById(int id) throws ServiceException, DBException {
		List<ReferenceArtifacts> referenceArtifactsList= referenceArtifactsRepository.viewReferenceArtifactsById(id);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		Type listType = new TypeToken<List<ReferenceArtifactsDTO>>() {}.getType();
		List<ReferenceArtifactsDTO> referenceArtifactsDTOList= modelMapper.map(referenceArtifactsList, listType);
		return referenceArtifactsDTOList;
	}
}
