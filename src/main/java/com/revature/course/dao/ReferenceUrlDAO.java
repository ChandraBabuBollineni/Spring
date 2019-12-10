package com.revature.course.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.revature.course.dto.ReferenceUrlDTO;

@Repository
public interface ReferenceUrlDAO {
	public List<ReferenceUrlDTO> getReferenceUrlById(int id);
}
