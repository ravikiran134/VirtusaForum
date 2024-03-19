package com.vforum.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.vforum.model.Posts;
import com.vforum.service.ForumService;

/**
 * Servlet implementation class UpdatePostController
 */
@WebServlet("/PostUpdateHelperController")
public class PostUpdateHelperController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PropertyConfigurator.configure("log4j.properties");
		Logger logger = Logger.getLogger(PostUpdateHelperController.class);
		
		int postId = 0;
		try
		{
			postId = Integer.parseInt(request.getParameter("currentPostId"));
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		
		ForumService service = new ForumService();
		Posts currentPost = service.getPostById(postId);
		request.setAttribute("requestedPost", currentPost);
		try
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/updatepost.jsp");
		    dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
	
	}

}
