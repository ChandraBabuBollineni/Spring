package com.revature.course.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.revature.course.model.Course;

@Transactional
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	@Query(value = "select course (name,level,category_id,tags,slug,level_over_ride,enrollment_point,completion_point,presignup,loggedin_via_slug,dashboard,description,meta_keyword,meta_description,icon from course order by id", nativeQuery = true)
	List<Course> viewCourses();

	@Modifying
	@Query(value ="insert into course (name,level_id,category_id,tags,slug,level_over_ride,enrollment_point,completion_point,presignup,loggedin_via_slug,dashboard,description,meta_keyword,meta_description,icon,reference_artifacts_id,refernce_url_id) values (:name,:level,:categoryId,:tags,:slug,:levelOverRide,:enrollmentPoint,:completionPoint,:presignup,:loggedinViaSlug,:dashboard,:description,:metaKeyword,:metaDescription,:icon,:referenceArtifactsId,:refernceUrlId)", nativeQuery = true)
	int addCourse(@Param("name") String name, @Param("level") int level, @Param("category") int categoryId,@Param("tags") String tags, @Param("slug") String slug, @Param("levelOverRide") boolean levelOverRide,@Param("enrollmentPoint") int enrollmentPoint,@Param("completionPoint") int completionPoint,@Param("presignup") boolean presignup,@Param("loggedinViaSlug") boolean loggedinViaSlug,@Param("dashboard") boolean dashboard,@Param("metaKeyword") String metaKeyword,@Param("metaDescription") String metaDescription,@Param("icon") byte[] icon,@Param("referenceArtifactsId") int referenceArtifactsId,@Param("refernceUrlId") int refernceUrlId);
	
	@Query(value = "select name,level,category_id,tags,slug,level_over_ride,enrollment_point,completion_point,presignup,loggedin_via_slug,dashboard,description,meta_keyword,meta_description,icon from course where id=:id order by id", nativeQuery = true)
	Course viewCourseById(@Param("id") int id);
	
}