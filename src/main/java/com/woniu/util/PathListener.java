package com.woniu.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PathListener implements ServletContextListener {
	
	
	
	public PathListener() {
		
		// TODO Auto-generated constructor stub
	}



	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		sce.getServletContext().setAttribute("AppPath", sce.getServletContext().getContextPath());
	}
	
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
