package com.vforum.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.vforum.helper.ForumHelper;
import com.vforum.service.ForumService;

/**
 * Servlet implementation class EmployeeLogin
 */
@WebServlet("/EmployeeLogin")
public class EmployeeLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	PropertyConfigurator.configure("log4j.properties");
		Logger logger = Logger.getLogger(EmployeeLoginController.class);
		
    	int empid = 0;
    	try
    	{
    		empid = Integer.parseInt(request.getParameter("empid"));
    	}
    	catch(Exception e)
    	{
    		logger.fatal(e);
    	}
		
		String pass = request.getParameter("password");
		String hashedPassword = "";
		
		ForumHelper helper = new ForumHelper();
		// To get hashed password
		hashedPassword = helper.getHashedPassword(pass);
	    
	    ForumService service = new ForumService();
	    if(service.validateLogin(empid,hashedPassword))
	    {

	    	HttpSession session = request.getSession();
	    	
			session.setAttribute("emp_id", empid);
			logger.debug("Login Successfull");
			try
			{
				response.sendRedirect(request.getContextPath() + "/HomePageController");
			}
			catch(Exception e)
			{
				logger.fatal(e);
			}
			
			
			try
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/HomePageController");
			    dispatcher.forward(request, response);
			}
			catch(Exception e)
			{
				logger.fatal(e);
			}
			
	    }
	    else
	    {
	    	logger.debug("Login Not Successful");
			request.setAttribute("message", "username/pasword is wrong");
			try
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
			catch(Exception e)
			{
				logger.fatal(e);
			}
			
	    	
	    }
	}

}
