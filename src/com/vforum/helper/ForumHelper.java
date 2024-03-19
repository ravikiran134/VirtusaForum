package com.vforum.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.vforum.model.Employee;

public class ForumHelper 
{
	static Logger logger = null;
	public static void createLogger()
	{
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger(ForumHelper.class);
	}
	
	public Connection getConnection()
	{
		
		
		String url = "jdbc:mysql://localhost:3306/vforum";
		String username = "root";
		String pass = "ravI1@";
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,pass);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return con;
	}
	public String getHashedPassword(String pass)
	{
		String hashedPassword;
		// write a function for hashing the password
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e)
		{

			logger.fatal(e);
		}
		byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
				    
		hashedPassword = DatatypeConverter.printHexBinary(hash); 
		
		return hashedPassword;
	}
	public static Employee getEmployeeById(int empId) 
	{

		ForumHelper helper = new ForumHelper();
		Employee employee = null;
		Connection con = helper.getConnection();
		final PreparedStatement stmt;
		final ResultSet rs;
		
		try 
		{
			
			stmt = con.prepareStatement("select * from employee where emp_id = ?");

			stmt.setInt(1, empId);

			rs = stmt.executeQuery();

			// rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(6),
						rs.getString(5), rs.getString(4));
			} 

		} catch (Exception e) {

			logger.fatal(e);
		}
//		System.out.println("Get Emploeee Exit with Emp || " + employee.toString());

		return employee;
	}	
}

