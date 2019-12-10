package com.revature.course.dao.impl;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.course.model.ReferenceArtifacts;
import com.revature.course.model.ReferenceUrl;

@Transactional
@Repository
public interface ReferenceUrlRepository extends JpaRepository<ReferenceUrl, Integer> {

	@Query(value = "insert into refernce_url(type,name,url,tutorial,description) values(:type,:name,:url,:tutorial,:description)", nativeQuery = true)
	ReferenceArtifacts addRefereneceArtifact(@Param("type") String type,@Param("name") String name,@Param("url") String url,@Param("tutorial") boolean tutorial,@Param("description") String description);

	@Query(value = "select id,type,name,url,tutorial,description from reference_artifacts", nativeQuery = true)
	List<ReferenceArtifacts> getReferenceArtifacts();
	
	@Query(value = "select id,type,name,url,tutorial,description from reference_artifacts where id=:id", nativeQuery = true)
	ReferenceArtifacts getReferenceArtifactsById(@Param("id") int id);

}
