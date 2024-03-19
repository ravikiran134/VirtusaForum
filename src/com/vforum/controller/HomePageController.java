package com.vforum.controller;

import java.io.IOException;
import java.util.List;

import com.vforum.model.Posts;
import com.vforum.service.ForumService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Servlet implementation class HomePageController
 */
@WebServlet("/HomePageController")
public class HomePageController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PropertyConfigurator.configure("log4j.properties");
		Logger logger = Logger.getLogger(HomePageController.class);
		
		ForumService service = new ForumService();
		
		service.deleteUnAnsweredPosts();
		
		List<Posts> resultSet = service.fetchAllPostsByDate();
		
		request.setAttribute("homeContent", resultSet);
		//session.setAttribute("emp_id", emp_id);
		try
		{
			request.getRequestDispatcher("/Home.jsp").forward(request, response);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		
	}

}
