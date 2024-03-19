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

import com.vforum.dao.ForumDAO;


@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			PropertyConfigurator.configure("log4j.properties");
			Logger logger = Logger.getLogger(AdminController.class);
			
			int adminid = 0;
		
			try {
				adminid = Integer.parseInt(request.getParameter("empid"));
			}
			catch(Exception e)
			{
				logger.fatal(e);
			}
			
			String password = request.getParameter("password");
			
		    ForumDAO ldao = new ForumDAO();
		   
		    if(ldao.adminLogin(adminid,password))
		    {
		    	
		    	HttpSession session = request.getSession();
		    	logger.info("Login Sucessfull");
		    	session.setAttribute("emp_id", adminid);
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
		    	logger.info("Login Not Sucessfull");
				request.setAttribute("message", "username/pasword is wrong");
				try
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("adminlogin.jsp");
					dispatcher.forward(request, response);
				}
				catch(Exception e)
				{
					logger.fatal(e);
				}
				
		    	
		    }
		}

		
		
		
		
	}


