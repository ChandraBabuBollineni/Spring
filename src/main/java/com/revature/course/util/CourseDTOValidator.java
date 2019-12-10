package com.revature.course.util;

import com.revature.course.dto.CourseDTO;
import com.revature.course.exception.ValidatorException;
public class CourseDTOValidator {

	public void courseDtoValidator(CourseDTO courseDTO) throws ValidatorException
{
		if(courseDTO==null)
		{
			throw new ValidatorException("Invalid course details.");
		}
String o = "";
if(courseDTO.getName()==null || courseDTO.getName().equals(o))
{	throw new ValidatorException("Invalid Name.");}

if(courseDTO.getLevel()==null || courseDTO.getLevel().getId()==0)
  throw new ValidatorException("Invalid Level Id.");

if(courseDTO.getCategory()==null || courseDTO.getCategory().getId()==0)
throw new ValidatorException("Invalid Category ID.");
if(courseDTO.getTags()==null || courseDTO.equals(o))
	throw new ValidatorException("Invalid tags.");
if(courseDTO.getSlug()==null || courseDTO.equals(o))
	throw new ValidatorException("Invalid slug.");
if(courseDTO.getEnrollmentPoint()<=0 || courseDTO.getEnrollmentPoint()>9999)
	throw new ValidatorException("Invalid Enrollment Point.");
if(courseDTO.getCompletionPoint()<=0 || courseDTO.getCompletionPoint()>9999)
	throw new ValidatorException("Invalid Completion Point.");
if(courseDTO.getMetaKeyword()==null || courseDTO.equals(o))
	throw new ValidatorException("Invalid Meta Keyword.");
if(courseDTO.getCreatedBy()==null || courseDTO.getCreatedBy().getId()==0 )
	throw new ValidatorException("Invalid User ID in created by.");
if(courseDTO.getIconName()==null)
throw new ValidatorException("please upload icon.");
else
{
String format=getFileExtension(courseDTO.getIconName());
if(!(format.equals("jpg") || format.equals("png") || format.equals("jpeg")))
	throw new ValidatorException("Invalid icon file format.");
}
if(courseDTO.getTags()==null) 
throw new ValidatorException("Invalid Tags.");



}
	private static String getFileExtension(String file) {
        if(file.lastIndexOf(".") != -1 && file.lastIndexOf(".") != 0)
        return file.substring(file.lastIndexOf(".")+1);
        else return "";
    }	
}
