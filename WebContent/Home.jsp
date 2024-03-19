<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
<script src="JavaScript/jquery-1.10.2.js" type="text/javascript"></script>
<title>VForum</title>

<%@include file="cdn.jsp" %>
<input type="hidden" id="refreshed" value="no">
 <script type="text/javascript">
  
 $(function() {
     // this will get the full URL at the address bar
     var url = window.location.href;

     // passes on every "a" tag
     $(".topmenu a").each(function() {
         // checks if its the same on the address bar
         if (url == (this.href)) {
             $(this).closest("li").addClass("active");
             //for making parent of submenu active
            $(this).closest("li").parent().parent().addClass("active");
         }
     });
 });        
 
	
 	onload=function()
 	{
    	var e=document.getElementById("refreshed");
    	if(e.value=="no")e.value="yes";
        else{e.value="no";location.reload();}
     }
    </script>
    
<style>

<%@include file="navstyle.jsp" %>


</style>
</head>
<body>
<%@ page session="true" %>
<%
	
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	if(session.getAttribute("emp_id")==null)
	{
		response.sendRedirect("login.jsp");
	}
%>

<%@include file="navbar.jsp" %>

<div class="container" style="margin-top:20px; background-color:lightblue">
  
  <div class="row">
    <div class="col-md-2">
      <h4>Author</h4>
    </div>
    <div class="col-md-8"> 
    	<h4>Topics</h4>
    </div>
    <div class="col-md-2"> 
     	
    </div>
  </div>
</div>

<%@page import="java.util.ArrayList"%>
	<%--Importing all the dependent classes--%>
	<%@page import="com.vforum.*"%>
	<%@page import="com.vforum.model.*"%>
	<%@page import="java.util.Iterator"%>
	<% 
		if (request.getAttribute("homeContent") != null)
		{
			ArrayList<Posts> searchResult = (ArrayList) request.getAttribute("homeContent");
			if (searchResult.size() == 0 || searchResult == null) {
	%>
	<a href="/home.jsp">
		<button type="button" class="btn btn-danger" style="margin: 20px; padding: 2px; align-content: stretch;">No
			Results to Display Click Here to search again !</button>
	</a>
	<%

      
		} else {
				Iterator<Posts> iterator = searchResult.iterator(); 

				while (iterator.hasNext()) // iterate through all the data until the last record
				{
					Posts currentPost = iterator.next();
					System.out.println(session.getAttribute("emp_id"));
					
	%>
					
					<div class="container" style="margin-top:10px">
  
						<div class="row">
							<div class="col-md-2">
								<%
									if(currentPost.getEmployeeObject() == null)
									{	
								%>
										<span class="badge badge-primary" style="font-size: 15px;">Admin</span>
								<%
									}
								
									else
									{
								%>
								
										<span class="badge badge-primary" style="font-size: 15px;"><%=currentPost.getEmployeeObject().getFirstName() %></span>
								<%
									}
								%>
								<h6>
								Employee ID :
								<%=currentPost.getEmpId()%></h6>
								<h6 class="card-subtitle mb-2 text-muted">
								Posted on :
								<%=currentPost.getUpdatedDate().toString()%></h6>
							</div>
							<div class="col-md-8">
								<h4 class="card-title">
								Subject: <%=currentPost.getPostSub()%></h4>
							</div>
							<div class="col-md-2"> 
								<form action="PostController" method="get">
									<input type=hidden name=emp_id value = <%= session.getAttribute("emp_id") %>><p><%= session.getAttribute("emp_id") %></p>
									<input type=hidden name=currentPostId
									value=<%=currentPost.getPostId()%>> <input
									class="btn btn-outline-success my-2 my-sm-0" style="margin-top: 5px;" type=submit
									value='View Post'><a><%=currentPost.getPostId()%></a>
								</form>
							</div>
						</div>
					</div>
					<hr>
					<%
				}
		}
	}
					%>
</body>