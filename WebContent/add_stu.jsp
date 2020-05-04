<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息管理系统</title>
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	background-color: #f3f5f7;
}

#header { //
	width: fill-available可以让元素的宽度表现为默认的block水平元素的尺寸表现 实际上盒子是display: inline-block;
	width: -webkit-fill-available;
	width: -moz-fill-available;
	width: -moz-available; /* FireFox目前这个生效 */
	width: fill-available;
	height: 60px;
	background: #35373a;
	position: fixed;
	top: 0;
	left: 0;
	z-index: 999;
	//
	顶部菜单栏选中样式
}

.lab-header {
	width: 1200px;
	margin: 0 auto;
	height: 60px;
	background: #35373a;
	line-height: 60px;
	color: #ffffff;
	overflow: hidden;
}

.title {
	display: inline-block;
	font-size: 18px;
}

.lab-menu {
	width: 890px;
	margin-left: 25px;
	display: inline-flex;
	justify-content: flex-start;
}

li {
	color: #fff;
	cursor: pointer;
	height: 60px;
	box-sizing: border-box;
	text-align: center;
	margin-left: 30px;
	list-style: none;
}

.lab-menu li a {
	color: #fff;
	text-decoration: none;
}

.active::after {
	content: "";
	display: block;
	width: 22px;
	height: 4px;
	margin: -15px auto 0;
	border-radius: 2px;
	background-color: #00abf1;
}

.exit {
	padding-right: 5px;
	margin-left: 40px;
	font-size: 14px;
	color: #fff;
	height: 60px;
	text-decoration: none;
}

.addstu {
	height: 34px;
	background: #2b96e5;
	color: #fff;
	font-size: 14px;
	line-height: 10px;
	display: block;
	border-radius: 4px;
	border: none;
	padding: 12px;
	cursor: pointer;
	margin-bottom: 20px;
}

.main {
	position: relative;
	background: white;
	margin: 80px auto 0;
	border-radius: 6px;
	width: 980px;
	border: 1px solid #fff;
	overflow: hidden;
	padding: 20px;
}

form {
	margin-top: 15px;
}

.main span {
	width: 100px;
	text-align: right;
	font-size: 18px;
	display: inline-block;
	margin: 20px;
}

.main input {
	width: 217px;
	height: 34px;
	border-raduis: 6px;
}

.main .save {
	width: 80px;
	height: 34px;
	background: #2b96e5;
	color: #fff;
	font-size: 16px;
	line-height: 10px;
	border-radius: 4px;
	border: none;
	padding: 10px 15px;
	cursor: pointer;
	display: inline-block;
	margin: 20px 0 0 180px;
}
.right{
            width:450px;
            color:#ff0000;
        }
</style>
    <script>
        function $(elementId) {
            return document.getElementById(elementId).value;
        }
        function divId(elementId) {
            return document.getElementById(elementId);
        }
        /*用户名验证*/
        function checkUser() {
            var user = $("user");
            var userId = divId("user_prompt");
            userId.innerHTML = "";
            var reg = /^\d{8,}$/;
            if (reg.test(user) == false) {
                userId.innerHTML = "学号不正确";
                return false;
            }
            return true;
        }

        /*验证邮箱*/
        function checkEmail() {
            var email = $("email");
            var email_prompt = divId("email_prompt");
            email_prompt.innerHTML = "";
            var reg = /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
            if (reg.test(email) == false) {
                email_prompt.innerHTML = "Email格式不正确，例如web@sohu.com";
                return false;
            }
            return true;
        }
        /*验证手机号码*/
        function checkMobile() {
            var mobile = $("mobile");
            var mobileId = divId("mobile_prompt");
            var regMobile = /^1\d{10}$/;
            if (regMobile.test(mobile) == false) {
                mobileId.innerHTML = "手机号码不正确，请重新输入";
                return false;
            }
            mobileId.innerHTML = "";
            return true;
        }
</script>
</head>
<body>
	<div id="header">
		<div class="lab-header">
			<span class="title">学生信息管理系统</span>
			<ul class="lab-menu">
				<li class="menu-home"><a href="student_list.jsp">学生管理</a></li>
				<li class="menu-home"><a href="class_list.jsp">班级管理</a></li>
			</ul>
			<span><%=username%></span><a href="login.jsp" class="exit" >退出</a>
		</div>
	</div>
	<div class="main">
		<a href="student_list.jsp">返回学生列表页</a>
		<form action="add_stu.do" method="post">
			<!-- sno,sname,ssex,bname,professional,email,pho,sbirh -->
			<table>
				<tr>
					<td><span>学员学号：</span></td>
					<td><input type="text" name="sno" maxlength="8" id="user" onblur="checkUser()"/></td>
					<td><div id="user_prompt" class="right">用户名为8个字符</div></td>
				</tr>
				<tr>
					<td><span>姓名：</span></td>
					<td><input type="text" name="sname" maxlength="5"/></td>

				</tr>
				<tr><!-- style="font-size:25px;width:100%;height:30px;margin-bottom:15px" -->
					<td><span>性别：</span></td>
					<td><input type="radio" name="ssex" value="男" style="width:15px;height:15px"/>
            			<label for="sexMale" >男&nbsp;&nbsp;&nbsp;&nbsp;  </label>
						<input type="radio" name="ssex" value="女" style="width:15px;height:15px"/>
            			<label for="sexFemale">女</label></td>
				</tr>
				<tr>
					<td><span>班级名称：</span></td>
					<td><input type="text"  name="bname" /></td>
					<td></td>
				</tr>
				<tr>
					<td><span>专业名称：</span></td>
					<td><input type="text" name="professional" /></td>
					<td></td>
				</tr>
				<tr>
					<td><span>邮箱：</span></td>
					<td><input type="text" name="email" id="email" onblur="checkEmail()"/></td>
					<td><div id="email_prompt" class="right"></td>
				</tr>
				<tr>
					<td><span>手机号：</span></td>
					<td><input type="text" name="pho" maxlength="11" id="mobile" onblur="checkMobile()"/></td>
					<td><div id="mobile_prompt" class="right"></div></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="save" type="submit" value="保存" /></td>
				</tr>
			</table>
			 
		</form>
	</div>
	<script>
		var test = window.location.href;
		if ((test.substring(test.lastIndexOf('/') + 1, test.indexOf(".jsp"))) == "add_stu") {
			var stu = document.getElementsByClassName("menu-home");
			stu[0].className += " active";
		}
	</script>
</body>
</html>