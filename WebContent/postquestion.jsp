<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%--Importing all the dependent classes--%>
<%@page import="com.vforum.*"%>
<%@page import="java.util.Iterator"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
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
</script>
<style>
.topmenu ul li.active a, .topmenu ul li a:hover {
    text-decoration:none;
    color:red;
   
}
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

<div class="container" style="margin-top:70px;margin-left:200px; padding-left:2px">
	<form  action="AskQuestionController" method = "get">
		
		<h3>Short Description of your Query</h3>
		<textarea style="width:700px;height:70px"name="question_subject" required></textarea></br></br>
		
		<h3>Detailed Description of your Query</h3>
		<textarea style="width:700px;height:200px" name="question_content" required></textarea></br></br>
		
		<input type="submit" value="Post your Question">
		<p><%=session.getAttribute("emp_id")%></p>
	</form>
	
</div>


</body>
</html>
