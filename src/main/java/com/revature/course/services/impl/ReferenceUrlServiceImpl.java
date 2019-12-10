package com.revature.course.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.course.dao.ReferenceUrlDAO;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.services.ReferenceUrlService;

@Service
public class ReferenceUrlServiceImpl implements ReferenceUrlService {
	@Autowired
	private ReferenceUrlDAO referenceUrlRepository;

	public List<ReferenceUrlDTO> viewReferenceUrlById(int id) {
		return referenceUrlRepository.getReferenceUrlById(id);
	}
}