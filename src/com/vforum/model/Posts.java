package com.vforum.model;

import java.util.Date;

import com.vforum.helper.ForumHelper;

public class Posts {
	
	private int postId;
	private String postSub;
	private String postContent;
	private Date postDate;
	//private Employee employeeObj;
	private int empId;
	
	public Posts(int postId, String postSub, String postContent, int empId, Date postDate) {
		super();
		// System.out.println("YEH WALA CONSTRUCTOR");
		this.postSub = postSub;
		this.postContent = postContent;
		this.postId = postId;
		this.postDate = postDate;
		this.empId = empId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostSub() {
		return postSub;
	}
	public void setPostSub(String postSub) {
		this.postSub = postSub;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public Date getUpdatedDate() {
		return postDate;
	}
	public void setUpdatedDate(Date postDate) {
		this.postDate = postDate;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Employee getEmployeeObject()
	{
		return ForumHelper.getEmployeeById(empId);
	}

}
