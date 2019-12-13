package com.revature.course.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.revature.course.model.ReferenceArtifacts;

@Repository
public interface ReferenceArtifactsDAO {
	public List<ReferenceArtifacts> viewReferenceArtifactsById(int id);
}
