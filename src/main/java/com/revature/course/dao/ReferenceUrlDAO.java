package com.revature.course.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.revature.course.model.ReferenceUrl;

@Repository
public interface ReferenceUrlDAO {
	public List<ReferenceUrl> getReferenceUrlById(int id);
}
