package org.zhy.java.spring4.chapter7.code704;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

public class MyServletInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		DispatcherServlet ds = new DispatcherServlet();
		Dynamic registration = servletContext.addServlet("appServlet", ds);
		registration.addMapping("/");
		registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads", 2097152, 4194304, 0));
	}

}
