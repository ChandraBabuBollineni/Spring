package com.revature.course.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.revature.course.dto.ReferenceUrlDTO;

@Service
public interface ReferenceUrlService {
	public List<ReferenceUrlDTO> viewReferenceUrlById(int id);
}
