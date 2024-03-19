package com.vforum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PropertyConfigurator.configure("log4j.properties");
		Logger logger = Logger.getLogger(LogoutController.class);
		
		HttpSession session = request.getSession();
		session.removeAttribute("emp_id");
		session.invalidate();
		try
		{
			response.sendRedirect("login.jsp");
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		
	}

	

}
