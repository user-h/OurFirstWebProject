<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String message = (String) session.getAttribute("message");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息管理系统</title>
<style>
   body{
   	background-color: #f6f6f6;
   }
	.main{
		 position: fixed;
	    top: 20%;
	    left: 0px;
	    right: 0px;
	    z-index: 999;
	    width: max-content;
	    margin: 0 auto 0;
	}
	.rabbit{
	 	width: 300px;
        height: 300px;
	}
	.text{
		font-size: 22px;
	}
</style>
</head>
<body>
	<div class="main">
		<img class="rabbit" src="./images/ai.jpg" />
		<span class="text"><%=message %><a href="javascript:history.go(-1);">请返回</a></span>
	</div>
</body>
</html>