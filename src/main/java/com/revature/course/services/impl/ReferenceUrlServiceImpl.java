package com.revature.course.services.impl;

import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.course.dao.ReferenceUrlDAO;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.exception.DBException;
import com.revature.course.model.ReferenceUrl;
import com.revature.course.services.ReferenceUrlService;

@Service
public class ReferenceUrlServiceImpl implements ReferenceUrlService {
	private ReferenceUrlDAO referenceUrlRepository;	
	private ModelMapper modelMapper=new ModelMapper();

	@Autowired
	   public ReferenceUrlServiceImpl(ReferenceUrlDAO referenceUrlRepository) {
	      this.referenceUrlRepository = referenceUrlRepository;    
	   }

	public List<ReferenceUrlDTO> viewReferenceUrlById(int id) throws DBException {
		List<ReferenceUrl> referenceUrlList= referenceUrlRepository.getReferenceUrlById(id);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		Type listType = new TypeToken<List<ReferenceUrlDTO>>() {}.getType();
		List<ReferenceUrlDTO> referenceArtifactsDTOList= modelMapper.map(referenceUrlList, listType);
		return referenceArtifactsDTOList;
	}
}