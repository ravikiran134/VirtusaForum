package com.vforum.controller;

import java.io.IOException;
import java.util.List;

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
import com.vforum.model.Posts;
import com.vforum.model.Answer;

/**
 * Servlet implementation class AnswerController
 */
@WebServlet("/AnswerController")
public class AnswerController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PropertyConfigurator.configure("log4j.properties");
		Logger logger = Logger.getLogger(AnswerController.class);
		
		int postId = 0;
		HttpSession session = request.getSession(false);
		try
		{
			postId = Integer.parseInt(request.getParameter("post_id"));
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		
		int empid = (int) session.getAttribute("emp_id");
		
		String answer = request.getParameter("answer_content");
		
		ForumDAO forumdao = new ForumDAO();
		Posts post = forumdao.getPostById(postId);
		boolean result = forumdao.postAnswer(postId, empid, answer);
		
		if(result)
		{
			
			//session.setAttribute("emp_id", empid);
			
			request.setAttribute("message", "Your answer Added succesfully");
			request.setAttribute("requestedPost", post);
			
			List<Answer> answerList = forumdao.fetchPostAnswers(postId);
			request.setAttribute("answers", answerList);
			
			try
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("post.jsp");
				dispatcher.forward(request, response);
			}
			catch(Exception e)
			{
				logger.fatal(e);
			}
			
		}
	}

}
