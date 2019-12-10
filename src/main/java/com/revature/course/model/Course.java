package com.revature.course.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "level_id")
	private Level level;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	@Column(name = "tags")
	private String tags;
	@Column(name = "slug")
	private String slug;
	@Column(name = "level_over_ride")
	private boolean levelOverRide;
	
	@Column(name = "enrollment_point")
	private int enrollmentPoint;
	@Column(name = "completion_point")
	private int completionPoint;
	
	@Column(name = "presignup")
	private boolean preSignUp;
	
	@Column(name = "loggedin_via_slug")
	private boolean loggedInViaSlug;
	
	@Column(name = "dashboard")
	private boolean dashboard;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "meta_keyword")
	private String metaKeyword;
	
	@Column(name = "meta_description")
	private String metaDescription;
	
	@Column(name = "icon")
	private byte[] icon;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reference_artifacts_id")
	private ReferenceArtifacts referenceArtifactsId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "refernce_url_id")
	private ReferenceUrl refernceUrlId;
	
}
