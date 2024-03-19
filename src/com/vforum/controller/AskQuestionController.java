package com.vforum.controller;

import java.io.IOException;
import java.util.List;

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
import com.vforum.service.ForumService;

/**
 * Servlet implementation class AskQuestionController
 */
@WebServlet("/AskQuestionController")
public class AskQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PropertyConfigurator.configure("log4j.properties");
		Logger logger = Logger.getLogger(AskQuestionController.class);
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		HttpSession session = request.getSession(false);
		String 	qSubject=request.getParameter("question_subject");
		String qContent=request.getParameter("question_content");
		
		ForumService service = new ForumService();
		if(service.postQuestion(qSubject,qContent,(int) session.getAttribute("emp_id")))
		{
			
			ForumDAO fda = new ForumDAO();
			List<Posts> resultSet = fda.fetchAllPostsByDate();
			
			request.setAttribute("homeContent", resultSet);
			try
			{
				request.getRequestDispatcher("/Home.jsp").forward(request, response);
			}
			catch(Exception e)
			{
				logger.fatal(e);
			}
		}
		else
		{
			logger.debug("Question Not Posted");
		}
	}

}
