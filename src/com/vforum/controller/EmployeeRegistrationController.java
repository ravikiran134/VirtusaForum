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

import com.vforum.helper.ForumHelper;
import com.vforum.model.Employee;
import com.vforum.service.ForumService;


/**
 * Servlet implementation class EmployeeRegistration
 */
@WebServlet("/EmployeeRegistration")
public class EmployeeRegistrationController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PropertyConfigurator.configure("log4j.properties");
		Logger logger = Logger.getLogger(EmployeeRegistrationController.class);
		
		int empid = 0;
		try
		{
			empid = Integer.parseInt(request.getParameter("empid"));
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		
		String fName = request.getParameter("first_name");
		String lName = request.getParameter("last_name");
		String designation = request.getParameter("designation");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String cpass = request.getParameter("confirm_password");
		String hashedPassword = "";
		if(pass.equals(cpass))
		{
			ForumHelper helper = new ForumHelper();
			// To get hashed password
			hashedPassword = helper.getHashedPassword(pass);
		}
		
		Employee employee = new Employee(empid, fName, lName, designation, email, hashedPassword);
		ForumService service = new ForumService();
		if(service.registerEmployee(employee))
		{
			
			logger.fatal("Registration Successful");
			HttpSession session = request.getSession();
			session.setAttribute("emp_id", empid);
			try 
			{
				response.sendRedirect(request.getContextPath() + "/HomePageController");
			}
			catch(Exception e)
			{
				logger.fatal(e);
			}
			
		}
		else
		{
			try 
			{
				response.sendRedirect("registration.jsp");
			}
			catch(Exception e)
			{
				logger.fatal(e);
			}
		}
	}

}
