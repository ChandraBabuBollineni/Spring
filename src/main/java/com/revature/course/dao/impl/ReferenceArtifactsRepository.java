package com.revature.course.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.revature.course.model.ReferenceArtifacts;

@Transactional
@Repository
public interface ReferenceArtifactsRepository extends JpaRepository<ReferenceArtifacts, String> {
	@Query(value = "insert into reference_artifacts(type,file,description) values(:type,:file,:description)", nativeQuery = true)
	ReferenceArtifacts addRefereneceArtifact(@Param("type") String type,@Param("file") byte[] file,@Param("description") String description);

	@Query(value = "select id,type,file,description from reference_artifacts", nativeQuery = true)
	List<ReferenceArtifacts> viewReferenceArtifacts();
	
	@Query(value = "select id,type,file,description from reference_artifacts where id=:id", nativeQuery = true)
	ReferenceArtifacts viewReferenceArtifactsById(@Param("id") int id);
}
