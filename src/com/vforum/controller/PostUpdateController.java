package com.vforum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.vforum.service.ForumService;

/**
 * Servlet implementation class PostUpdateController
 */
@WebServlet("/PostUpdateController")
public class PostUpdateController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PropertyConfigurator.configure("log4j.properties");
		Logger logger = Logger.getLogger(PostUpdateController.class);
		
		String 	qSubject=request.getParameter("question_subject");
		String qContent=request.getParameter("question_content");
		
		int postId = 0;
		try
		{
			postId=Integer.parseInt(request.getParameter("post_id"));
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		
		ForumService service = new ForumService();
		
		if(service.updatePostByPostId(postId, qSubject, qContent))
		{
			request.setAttribute("message", "Post Content Updated Successfully!!");
			try
			{
				request.getRequestDispatcher("/ViewMyPostsController").forward(request, response);
			}
			catch(Exception e)
			{
				logger.fatal(e);
			}
			
		}
		else
			logger.debug("Post Update Unsuccessfull");
	}

}
