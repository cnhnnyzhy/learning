package org.zhy.java.spring4.chapter3.code306;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

public class ProfileCondition implements Condition {

	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		if(context.getEnvironment() != null){
			MultiValueMap<String, Object> attrs = metadata.getAllAnnotationAttributes(Profile.class.getName());
			if(attrs != null){
				for(Object value : attrs.get("value")){
					if(context.getEnvironment().acceptsProfiles((String[])value)){
						return true;
					}
				}
				return false;
			}
		}
		return true;
	}

}
