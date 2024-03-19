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
<title>VForum</title>

<%@include file="cdn.jsp" %>
<script>
//Function to check Whether both passwords 
// is same or not. 
function checkValues(form) { 
	answer_content = form.answer_content.value; 
    //password2 = form.confirm_password.value; 

    // If password not entered 
    if (answer_content == '') 
        alert ("Please enter Answer"); 
} 

window.setTimeout(function() {
    $(".alert").fadeTo(500, 0).slideUp(500, function(){
        $(this).remove(); 
    });
}, 4000);
</script>

<style>
<%@include file="navstyle.jsp" %>
.boxed {
  border: 1px solid green ;
}
textarea {
  width: 600px;
  height: 200px;
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


<%@page import="java.util.ArrayList"%>
	<%--Importing all the dependent classes--%>
	<%@page import="com.vforum.*"%>
	<%@page import="com.vforum.model.*"%>
	<%@page import="java.util.Iterator"%>
	<%
		Posts post = (Posts) request.getAttribute("requestedPost");
		ArrayList<Answer> answerList = (ArrayList) request.getAttribute("answers");
		
 	%>
 
<div class="container" style="margin-top:10px;margin-left:100px; padding-left:2px;">
<br>
<br>
  <p align="center" class="alert" role="alert" style = "color:green">${requestScope.message }</p>
  <b><h3 class="card-title"><%=post.getPostSub()%></h3></b>
  
</div>
<hr>
<div class="container" style="margin-top:0px;margin-left:100px; padding-left:2px;">
<p ><%=post.getPostContent()%></p>
</div>

<hr>
<div class="container" style="margin-top:0px;margin-left:100px; padding-left:2px;">
	<h4>Replies</h4>
</div>
<hr>
<%
		// Iterating through subjectList

		if (request.getAttribute("answers") != null) // Null check for the object
		{
			Iterator<Answer> iterator = answerList.iterator();
			
			while (iterator.hasNext())
			{
				Answer currentAnswer = iterator.next();
				
%>
				
				<div class="container" style="margin-top:0px;margin-left:100px;">
					
					<p><%=currentAnswer.getAnswerContent()%></p>
				</div>
				
				<div class="container" style=" width:280px;margin-top:0px;margin-left:1000px;background-color: #eff0f1;">
  					<div style=" margin-top:0px;margin-left:0px; padding-left:2px">
  					<%
									if(currentAnswer.getEmployeeObject() == null)
									{	
								%>
										<span class="badge badge-primary" style="font-size: 15px;">Admin</span>
								<%
									}
								
									else
									{
								%>
								
										<span class="badge badge-primary" style="font-size: 15px;"><%=currentAnswer.getEmployeeObject().getFirstName() %></span>
								<%
									}
								%>
  					<p style="margin-left:0px" >Answered on :<%=currentAnswer.getPostedDate().toString()%></p></div>
  					
				</div>
				<hr>
	<%
			}
		}
	%>

<div class="container" style="margin-top:0px;margin-left:150px; padding-left:2px">
	<form  action="AnswerController" method = "post" onsubmit="return checkValues();">
		<input type= "hidden" name = "post_id" value = "<%=request.getAttribute("post_id")%>"/>
		<textarea name="answer_content" required></textarea></br></br>
		<input class="btn btn-outline-success my-2 my-sm-0" type="submit" value="Post your Answer"/>
		<p><%=session.getAttribute("emp_id")%></p>
	</form>
</div>
<br>
</body>
</html>
