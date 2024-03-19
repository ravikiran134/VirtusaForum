package com.vforum.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.vforum.model.Posts;
import com.vforum.service.ForumService;


@WebServlet("/SearchController")
public class SearchController extends HttpServlet 
{
	
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			PropertyConfigurator.configure("log4j.properties");
			Logger logger = Logger.getLogger(SearchController.class);
			
		String search=request.getParameter("search");
		ForumService service = new ForumService();
		List<Posts> resultpost = service.fetchAllPostsByString(search);
		request.setAttribute("homeContent", resultpost);
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
