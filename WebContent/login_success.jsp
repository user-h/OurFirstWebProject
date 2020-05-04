<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.datang.hrb.vo.*" %>
    <%@ page import="java.util.*" %>
    <%
    	User user = (User)session.getAttribute("user");
    	List<User> userList = (ArrayList<User>)session.getAttribute("userList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
登录成功
<p>你好！<%=user.getUsername() %></p>

<table border=1 >
	<tr><td>用户名</td><td>密码</td><td>年龄</td><td>时间戳</td></tr>
	<%
		for(User tempUser:userList){
			%>
			<tr><td><%=tempUser.getUsername() %></td><td><%=tempUser.getPassword() %></td><td><%=tempUser.getAge() %></td><td><%=tempUser.getTs() %></td></tr>
		<%}
	%>
</table>

</body>
</html>