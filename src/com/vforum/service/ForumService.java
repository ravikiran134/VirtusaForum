package com.vforum.service;

import java.util.List;

import com.vforum.dao.ForumDAO;
import com.vforum.model.Answer;
import com.vforum.model.Employee;
import com.vforum.model.Posts;

public class ForumService 
{
	ForumDAO rdao = new ForumDAO();
	public boolean registerEmployee(Employee employee) {
		
		return rdao.registerEmployee(employee);
	}
	public boolean validateLogin(int empid, String hashedPassword) {
		
		return rdao.validateLogin(empid, hashedPassword);
	}
	public List<Posts> fetchAllPostsByDate() {

		return rdao.fetchAllPostsByDate();
	}
	public Posts getPostById(int postId) {

		return rdao.getPostById(postId);
	}
	public List<Answer> fetchPostAnswers(int postId) {

		return rdao.fetchPostAnswers(postId);
	}
	public boolean postQuestion(String qSubject, String qContent, int empId) {

		return rdao.postQuestion(qSubject, qContent, empId);
	}
	public List<Posts> getPostsByEmpId(int empId) {

		return rdao.getPostsByEmpId(empId);
	}
	public boolean updatePostByPostId(int postId, String qSubject, String qSontent) {

		return rdao.updatePostByPostId(postId, qSubject, qSontent);
	}
	public List<Posts> fetchAllPostsByString(String searchString) {

		return rdao.fetchAllPostsByString(searchString);
	}
	public void deleteUnAnsweredPosts() {
		rdao.deleteUnAnsweredPosts();
		
	}
	
	

}
