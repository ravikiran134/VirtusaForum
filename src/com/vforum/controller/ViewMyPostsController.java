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

import com.vforum.model.Posts;
import com.vforum.service.ForumService;

/**
 * Servlet implementation class ViewMyPostsController
 */
@WebServlet("/ViewMyPostsController")
public class ViewMyPostsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
    	PropertyConfigurator.configure("log4j.properties");
		Logger logger = Logger.getLogger(ViewMyPostsController.class);
    	
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		HttpSession session = request.getSession(false);

		if(session.getAttribute("emp_id")==null)
		{
			try
			{
				response.sendRedirect("login.jsp");
			}
			catch(Exception e)
			{
				logger.fatal(e);
			}
			
		}
		else
		{
			ForumService service = new ForumService();
			
			List<Posts> resultlist = service.getPostsByEmpId((int) session.getAttribute("emp_id"));
			
			request.setAttribute("myPostsContent",resultlist);
			request.setAttribute("message", request.getAttribute("message"));
			try
			{
				request.getRequestDispatcher("myposts.jsp").forward(request, response);
			}
			catch(Exception e)
			{
				logger.fatal(e);
			}
			
		}	
	}

}
