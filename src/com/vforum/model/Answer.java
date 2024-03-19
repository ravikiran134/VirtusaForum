package com.vforum.model;

import com.vforum.helper.ForumHelper;

public class Answer 
{
	private int ansID;
	private String answerContent;
	private int postId;
	private int empId;
	private String postedDate;
	
	public Answer(int ansId, String answerContent, int postId, int empId, String postedDate) 
	{
		this.ansID = ansId;
		this.answerContent = answerContent;
		this.postId = postId;
		this.empId = empId;
		this.postedDate = postedDate;
	}
	
	public Employee getEmployeeObject()
	{
		return ForumHelper.getEmployeeById(empId);
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public int getAnsId() {
		return ansID;
	}
	public void setAnsId(int ansId) {
		this.ansID = ansId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
}
