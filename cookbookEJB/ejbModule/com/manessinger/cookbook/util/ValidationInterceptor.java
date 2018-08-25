package com.manessinger.cookbook.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import com.manessinger.cookbook.exception.ConstraintProperty;
import com.manessinger.cookbook.exception.ValidationException;
import com.manessinger.cookbook.exception.ViolationDetail;

import javax.validation.ConstraintViolation;

@Interceptor
public class ValidationInterceptor {

	// the factory is expensive but thread-safe
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		Validator v = factory.getValidator();
		
		//for all parameters
		for(Object p : ctx.getParameters()) {
			//validate parameter
			Set<ConstraintViolation<Object>> violations = v.validate(p, Default.class);
			if (!violations.isEmpty()) {
				//validation failed, gather details and throw an exception
				List<ViolationDetail> details = new ArrayList<ViolationDetail>();
				
				for(ConstraintViolation<Object> violation : violations) {
					ViolationDetail d = new ViolationDetail();
					//path to violated constraint
					d.setAttributedItem(violation.getPropertyPath().toString());
					//what type of constraint, e.g. Min or Pattern
					d.setConstraintName(
							violation.getConstraintDescriptor().getAnnotation()
							.annotationType().getSimpleName());
					//construct list of constraint properties
					Map<String, Object> violationAttributes =
							violation.getConstraintDescriptor().getAttributes();
					List<ConstraintProperty> properties = new ArrayList<ConstraintProperty>();
					for(String propertyName : violationAttributes.keySet()) {
						 if (propertyName.matches("^(message|payload|groups|flags)$")) {
							// skip unwanted property
							continue;
						 }
						 ConstraintProperty  a = new ConstraintProperty();
						 a.setName(propertyName);
						 a.setValue(violationAttributes.get(propertyName).toString());
						 properties.add(a);
					}
					d.setConstraintProperties(properties);
					//the value that violated the constraint
					Object invalidValue = violation.getInvalidValue();
					d.setInvalidItemValue(invalidValue != null ? invalidValue.toString() : "null");
					
					details.add(d);
				}
				throw new ValidationException(details);
			}
		}
		return ctx.proceed();
	}
	
}
