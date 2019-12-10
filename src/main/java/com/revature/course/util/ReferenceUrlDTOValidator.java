package com.revature.course.util;

import java.util.List;
import javax.validation.ValidationException;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.exception.ServiceException;
import com.revature.course.exception.ValidatorException;

public class ReferenceUrlDTOValidator {
	public void referenceUrlDTOValidator(ReferenceUrlDTO referenceUrlDTO) throws ValidatorException {
		if (referenceUrlDTO.getName() == null)
			throw new ValidatorException("Invalid referenceurl name");
		if (referenceUrlDTO.getType() == null)
			throw new ValidationException("Invalid referenceurl Type");
		if (referenceUrlDTO.getUrl() == null)
			throw new ValidatorException("Invalid reference URL");
	}

	public void referenceUrlDTOListValidator(List<ReferenceUrlDTO> referenceUrlDTOList)
			throws ServiceException, ValidatorException {
		if (referenceUrlDTOList == null || referenceUrlDTOList.isEmpty()) {
			throw new ValidatorException("Course content is required.");
		}
		for (ReferenceUrlDTO referenceUrlDTO : referenceUrlDTOList) {
			referenceUrlDTOValidator(referenceUrlDTO);
		}
	}

}
