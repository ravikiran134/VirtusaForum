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

import com.vforum.model.Answer;
import com.vforum.model.Posts;
import com.vforum.service.ForumService;

/**
 * Servlet implementation class PostController
 */
@WebServlet("/PostController")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	PropertyConfigurator.configure("log4j.properties");
		Logger logger = Logger.getLogger(PostController.class);
    	
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		
		String postId = request.getParameter("currentPostId");

		ForumService service = new ForumService();
		Posts post = null;
		try
		{
			post = service.getPostById(Integer.parseInt(postId));
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		
		
		List<Answer> answerList = null;
		try
		{
			answerList = service.fetchPostAnswers(Integer.parseInt(postId));
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		
		request.setAttribute("answers", answerList);
		
		request.setAttribute("requestedPost", post);
		request.setAttribute("post_id", postId);
		
		// Need to writ the answer query to fetch all the answers for this post
		try
		{
			request.getRequestDispatcher("post.jsp").forward(request, response);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		
	}

	

}
