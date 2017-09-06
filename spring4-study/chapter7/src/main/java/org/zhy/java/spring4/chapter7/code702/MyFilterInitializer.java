package org.zhy.java.spring4.chapter7.code702;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

public class MyFilterInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		Dynamic myFilter = servletContext.addFilter("myFilter", MyFilter.class);
		myFilter.addMappingForUrlPatterns(null, false, "/custom/**");
	}

}
