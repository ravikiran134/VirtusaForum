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

<script>

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

window.setTimeout(function() {
    $(".alert").fadeTo(500, 0).slideUp(500, function(){
        $(this).remove(); 
    });
}, 4000);

</script>
<style>
<%@include file="navstyle.jsp" %>
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
<p align="center" class="alert" role="alert" style = "color:green">${requestScope.message }</p>

<%@page import="java.util.ArrayList"%>
	<%--Importing all the dependent classes--%>
	<%@page import="com.vforum.*"%>
	<%@page import="com.vforum.model.*"%>
	<%@page import="java.util.Iterator"%>
	<% 
		if (request.getAttribute("myPostsContent") != null)
		{
			ArrayList<Posts> searchResult = (ArrayList) request.getAttribute("myPostsContent");
			if (searchResult.size() == 0 || searchResult == null) {
	%>
	<a href="/home.jsp">
		<button type="button" class="btn btn-danger" style="margin: 20px; padding: 2px; align-content: stretch;">No
			Results to Display Click Here to search again !</button>
	</a>
	<%

      
		} else {
				Iterator<Posts> iterator = searchResult.iterator(); 

				while (iterator.hasNext()) 
				{
					Posts currentPost = iterator.next();
					//System.out.println(session.getAttribute("flag"));
					
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
								<br>
								<br>
								<h6 class="card-subtitle mb-2 text-muted">
								Posted on :
								<%=currentPost.getUpdatedDate().toString()%></h6>
							</div>
							<div class="col-md-8">
								<h4 class="card-title">
								Subject: <%=currentPost.getPostSub()%></h4>
							</div>
							<div class="col-md-1"> 
								<form action="PostController" method="get">
									<input type=hidden name=currentPostId
									value=<%=currentPost.getPostId()%>> <input
									class="btn btn-outline-success my-2 my-sm-0" style="margin-top: 5px;" type=submit
									value='View Post'><a><%=currentPost.getPostId()%></a>
									
									<p><%=session.getAttribute("emp_id")%></p>
								</form>
							</div>
							<div class="col-md-1"> 
								<form action="PostUpdateHelperController" method="post">
									<input type=hidden name=currentPostId
									value=<%=currentPost.getPostId()%>> <input
									class="btn btn-outline-success my-2 my-sm-0" style="margin-top: 5px;" type=submit
									value='Update Post'><a><%=currentPost.getPostId()%></a>

									<p><%=session.getAttribute("emp_id")%></p>
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