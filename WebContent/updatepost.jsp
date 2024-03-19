<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%--Importing all the dependent classes--%>
<%@page import="com.vforum.*"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.vforum.model.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
<title>VForum</title>
<%@include file="cdn.jsp" %> 
<head>
<style>
<%@include file="navstyle.jsp" %>
.boxed {
  border: 1px solid green ;
}
textarea {
  width: 700px;
  height: 100px;
}
</style>
</head>
<body>
<%
	
	if(session.getAttribute("emp_id")==null)
	{
		response.sendRedirect("login.jsp");
	}
%>
<%@include file="navbar.jsp" %>
<% 
		
		Posts currentPost = (Posts)request.getAttribute("requestedPost");
		
			
%>



<div class="container" style="margin-top:70px;margin-left:200px; padding-left:2px">
	<form  action="PostUpdateController" method = "get">
		
		<input type= "hidden" name = "post_id" value = <%=currentPost.getPostId()%>>
		<h3>Short Description of your Query</h3>
		<textarea style="width:700px;height:70px"name="question_subject" required><%=currentPost.getPostSub()%></textarea></br></br>
		<h3>Detailed Description of your Query</h3>
		<textarea style="width:700px;height:200px" name="question_content" required><%=currentPost.getPostContent()%></textarea></br></br>
		<input type="submit" value="Post your Question">
		<p><%=session.getAttribute("emp_id")%></p>
		
	</form>
	
</div>


</body>
</html>
