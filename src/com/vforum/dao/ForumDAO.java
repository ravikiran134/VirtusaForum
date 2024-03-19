package com.vforum.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.vforum.helper.ForumHelper;
import com.vforum.model.Answer;
import com.vforum.model.Employee;
import com.vforum.model.Posts;

public class ForumDAO 
{
	ForumHelper helper = new ForumHelper();
	Logger logger = null;
	public boolean registerEmployee(Employee employee )
	{
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger(ForumDAO.class);
		
		Connection con = helper.getConnection();
		try 
		{
			// First check if record already exist
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM employee WHERE emp_id = ?");
			stmt.setInt(1, employee.getEmpId());
			ResultSet rs = stmt.executeQuery();
			
			// If already exist redirect to login page or display message
			if(rs.next())
			{
				//Display account already exist
				//response.sendRedirect("login.jsp");
				//RequestDispatcher rd = request.RequestDispatcher("EmployeeRegistration");
			}
			else// if no record then insert details to table
			{
				String sql = "insert into employee values(?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(sql);
				
				ps.setInt(1, employee.getEmpId());
				ps.setString(2, employee.getFirstName());
				ps.setString(3, employee.getLastName());
				ps.setString(4, employee.getEmail());
				ps.setString(5, employee.getPassword());
				ps.setTimestamp(6, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
				ps.setTimestamp(7, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
				//ps.setString(6, level);
				
				int count = ps.executeUpdate();
				if(count > 0)
				{
					return true;
				}
				else
				{
					//redirect print unsuccesful
				}
				
			}
			con.close();
			
		} catch (SQLException e) {

			logger.fatal(e);
		}
		
		return false;
	}
	
	public boolean validateLogin(int empid, String password)
	{
		Connection con = helper.getConnection();
		try 
		{
			PreparedStatement ps = con.prepareStatement("SELECT emp_id, password FROM employee WHERE emp_id = ? and password = ?");
			ps.setInt(1, empid);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				return true;
			}
			
			con.close();
			
		} catch (SQLException e) {
			
			logger.fatal(e);
		}
		
		return false;
	}

	public List<Posts> fetchAllPostsByDate() {

		ArrayList<Posts> postList = new ArrayList<>();
		Connection con = helper.getConnection();
		PreparedStatement ps;
		ResultSet rs = null;
		try 
		{
			//where datediff(curdate(),date(updated_date)) <= 2
			ps = con.prepareStatement("select * from posts  order by updated_date desc");
			rs = ps.executeQuery();
			while(rs.next())
			{
				postList.add(new Posts(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), new Date(rs.getTimestamp(6).getTime())));
			}
			
			con.close();
		} 
		catch (SQLException e) {

			logger.fatal(e);
		}
		
		return postList;
	}
	
	public Posts getPostById(int postId)
	{
		
		Posts post = null;
		Connection con = helper.getConnection();
		PreparedStatement ps;
		ResultSet rs = null;
		try 
		{
			ps = con.prepareStatement("select * from posts where post_id = ?");
			ps.setInt(1, postId);
			rs = ps.executeQuery();
			while(rs.next())
			{
				post = new Posts(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), new Date(rs.getTimestamp(6).getTime()));
			}
			
			con.close();
		} 
		catch (SQLException e) {

			logger.fatal(e);
		}
		
		return post;
	}

	public boolean postAnswer(int postId, int empid, String answer) 
	{
		Connection con = helper.getConnection();
		PreparedStatement ps;
		int rs;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    Date date = new Date();  
		
		try 
		{
			ps = con.prepareStatement("insert into answers values(0,?,?,?,?,?)");
			ps.setString(1, answer);
			ps.setInt(2, postId);
			ps.setInt(3, empid);
			ps.setString(4, formatter.format(date));
			ps.setString(5, formatter.format(date));
			
			rs = ps.executeUpdate();
			if(rs > 0)
			{
				return true;
			}
			else
			{
				logger.debug("Answer adding failed");
				return false;
			}
		} catch (SQLException e) {

			logger.fatal(e);
		}
		return false;
		
		
	}

	public List<Answer> fetchPostAnswers(int postId) 
	{
		Connection con = helper.getConnection();
		PreparedStatement ps;
		ResultSet rs = null;
		ArrayList<Answer> answerLists = new ArrayList<>();
		
		try 
		{
			ps = con.prepareStatement("select * from answers where post_id = ?");
			ps.setInt(1, postId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				answerLists.add(new Answer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
		} catch (SQLException e) {

			logger.fatal(e);
		}
		return answerLists;
	}
	
	public List<Posts> getPostsByEmpId(int empId) 
	{
		ArrayList<Posts> postList = new ArrayList<>();
		Connection con = helper.getConnection();
		PreparedStatement ps;
		ResultSet rs = null;
		try 
		{
			ps = con.prepareStatement("select * from posts where emp_id=? order by updated_date desc");
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			while(rs.next())
			{
				postList.add(new Posts(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), new Date(rs.getTimestamp(6).getTime())));
			}
			
			con.close();
		} 
		catch (SQLException e) 
		{

			logger.fatal(e);
		}
		
		return postList;
	}
	
	public boolean postQuestion(String qSubject, String qContent, int empId)
	{
		Connection con = helper.getConnection();
		PreparedStatement ps;
		int rs=0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
	    Date date = new Date(); 
		try 
		{
			ps=con.prepareStatement("insert into posts values(0,?,?,?,?,?)");
			ps.setString(1, qSubject);
			ps.setString(2, qContent);
			ps.setInt(3, empId);
			ps.setString(4,formatter.format(date));
			ps.setString(5,formatter.format(date));
			
			rs=ps.executeUpdate();
			if(rs>0) 
			{
				return true;
			}
		
			
		} catch (SQLException e) {
			
			logger.fatal(e);
		}
		return false;
	}

	public boolean updatePostByPostId(int postId, String qSubject, String qContent)
	{
		Connection con = helper.getConnection();
		PreparedStatement ps;
		int rs=0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
	    Date date = new Date(); 
	    boolean response = false;
		
	    try 
		{
			ps=con.prepareStatement("UPDATE posts\r\n" + 
					"SET\r\n" + 
					"    post_subject = ?, post_content = ?, updated_date = ?\r\n" + 
					"WHERE\r\n" + 
					"    post_id = ?;");
			ps.setString(1, qContent);
			ps.setString(2, qSubject);
			ps.setString(3,formatter.format(date));
			ps.setInt(4, postId);
			
			rs=ps.executeUpdate();
			
			if(rs>0) 
			{
				response = true;
			}
		
			
		} catch (SQLException e) {

			logger.fatal(e);
		}
		return response;
	}

	public List<Posts> fetchAllPostsByString(String searchString) {

		ArrayList<Posts> postList = new ArrayList<>();
		Connection con = helper.getConnection();
		PreparedStatement ps;
		ResultSet rs = null;
		try 
		{
			//where datediff(curdate(),date(updated_date)) <= 2
			ps = con.prepareStatement("select * from posts where MATCH(post_subject) AGAINST(?)");
			ps.setString(1, searchString);
			rs = ps.executeQuery();
			while(rs.next())
			{
				postList.add(new Posts(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), new Date(rs.getTimestamp(6).getTime())));
			}
			
			con.close();
		} 
		catch (SQLException e) {

			logger.fatal(e);
		}
		
		return postList;
	}
	
	public boolean adminLogin(int adminid, String password)
	{
		Connection con = helper.getConnection();
		try 
		{
			PreparedStatement ps = con.prepareStatement("SELECT admin_id, password FROM admin WHERE admin_id = ? and password = ?");
			
			ps.setInt(1, adminid);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				return true;
			}
			
			con.close();
			
		} catch (SQLException e) {
			
			logger.fatal(e);
		}
		
		return false;
	}

	public void deleteUnAnsweredPosts() 
	{
		ForumDAO fda = new ForumDAO();
		ArrayList<Integer> pid = fda .getAllPostIDs();
		ArrayList<Integer> answeredPid = fda .getAllAnsweredPostIDs();
		ArrayList<Integer> unAnsweredPid = new ArrayList();
		int flag;
		
		for(int i = 0; i < pid.size(); i++)
		{
			flag = 0;
			for(int j = 0; j < answeredPid.size(); j++)
			{
				if(pid.get(i).equals(answeredPid.get(j)))
				{
					flag = 1;
					break;	
				}
			}
			if(flag == 0)
			{
				unAnsweredPid.add(pid.get(i));
				fda.deleteUnAnsweredPost(pid.get(i));
			}
		}
	}

	private void deleteUnAnsweredPost(int postId)
	{
		
		Connection con = helper.getConnection();
		int copyRs = 0;
		ResultSet rsDays = null;
		try 
		{
			
			PreparedStatement stmt = con.prepareStatement("SELECT curdate()-date(updated_date) from posts where post_id = ?");
			stmt.setInt(1, postId);
			rsDays = stmt.executeQuery();
			if(rsDays.next())
			{
				
				if(rsDays.getInt(1) > 2)
				{
					
					PreparedStatement copyStmt = con.prepareStatement("INSERT INTO backup_posts SELECT * from posts where post_id = ?");
					copyStmt.setInt(1, postId);
					copyRs = copyStmt.executeUpdate();
				}
				if(copyRs > 0)
				{
					PreparedStatement deleteStmt = con.prepareStatement("Delete FROM posts WHERE post_id = ?");
					deleteStmt.setInt(1, postId);
					deleteStmt.executeUpdate();
				}
			}
				
			
		}	
		catch(Exception e)
		{
			logger.fatal(e);
		}
		
	}

	private ArrayList<Integer> getAllAnsweredPostIDs() {
		ArrayList<Integer> aPid = new ArrayList();
		Connection con = helper.getConnection();
		try 
		{
			
			PreparedStatement stmt = con.prepareStatement("SELECT post_id FROM answers");
			ResultSet rs = stmt.executeQuery();
			
			// If already exist redirect to login page or display message
			while(rs.next())
			{
				aPid.add(rs.getInt(1));
				//System.out.println(rs.getInt(1));
			}
		}	
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return aPid;
	}

	private ArrayList<Integer> getAllPostIDs() {
		
		ArrayList<Integer> pId = new ArrayList();
		Connection con = helper.getConnection();
		try 
		{
			
			PreparedStatement stmt = con.prepareStatement("SELECT post_id FROM posts");
			ResultSet rs = stmt.executeQuery();
			
			// If already exist redirect to login page or display message
			while(rs.next())
			{
				pId.add(rs.getInt(1));
				//System.out.println(rs.getInt(1));
			}
		}	
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return pId;
	}
	
	
}
