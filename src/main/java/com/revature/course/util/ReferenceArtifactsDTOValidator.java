package com.revature.course.util;

import java.util.List;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.exception.ValidatorException;

public class ReferenceArtifactsDTOValidator {
/**
 * 
 * @param referenceArtifactsDTO
 * @throws ValidatorException - in case of invalid details
 * @see com.revature.course.dto.ReferenceArtifactsDTO
 * @see com.revature.course.exception.ValidatorException
 */
	public void referenceArtifactsDTOValidator(ReferenceArtifactsDTO referenceArtifactsDTO) throws ValidatorException {
		if (referenceArtifactsDTO.getName() == null)
			throw new ValidatorException("Invalid reference artifact name");
		if (referenceArtifactsDTO.getType() == null)
			throw new ValidatorException("Invalid reference artifact Type");
	}
/**
 * 
 * @param referenceArtifactsDTO List
 * @throws ValidatorException - in case of invalid details
 * @see com.revature.course.dto.ReferenceArtifactsDTO
 * @see com.revature.course.exception.ValidatorException
 */
	public void referenceArtifactsDTOListValidator(List<ReferenceArtifactsDTO> referenceArtifactsDTOList)
			throws ValidatorException {
		if (referenceArtifactsDTOList == null || referenceArtifactsDTOList.isEmpty()) {
			throw new ValidatorException("Course content is required.");
		}

		for (ReferenceArtifactsDTO ReferenceArtifactsDTO : referenceArtifactsDTOList) {
			referenceArtifactsDTOValidator(ReferenceArtifactsDTO);
		}
	}

}
