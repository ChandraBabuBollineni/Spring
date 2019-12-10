package com.revature.course.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.revature.course.dto.ReferenceArtifactsDTO;

@Repository
public interface ReferenceArtifactsDAO {
	public List<ReferenceArtifactsDTO> viewReferenceArtifactsById(int id);
}
