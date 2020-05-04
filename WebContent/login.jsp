<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息管理系统</title>
<script type="text/javascript">
    	function flushImg(){
    		//获得验证码图片对象
    		var imgCodeObject = document.getElementsByClassName("code")[0];
    		imgCodeObject.src="getImgCode.do?t="+new Date().getTime();
    	}
    
    </script>
<style>
*{margin: 0; padding: 0;}
#login {
  height: 100vh;
  background: linear-gradient(#f2f9fd, #b0dcec);
  position: relative;
  }
  .login-bg {
  width: -webkit-fill-available;
  width: -moz-fill-available;
  width: -moz-available; /* FireFox目前这个生效 */
  position: absolute;
  bottom: 0;
  width: fill-available;
  height: 428px;
  background: url("./images/login-bg.png") no-repeat;
  background-size: cover;
  }
  .login-box {
    position: fixed;
    top: 10%;
    left: 0px;
    right: 0px;
    z-index: 999;
    width: 620px;
    margin: 0 auto 0;
    }
    .login-box .login-title {
      text-align: center;
      margin-bottom: 50px;
      }
      .login-title span {
        font-size: 32px;
        color: #2b96e5;
        letter-spacing: 0;
        line-height: 32px;
      }
    .login-main {
      width: 460px;
	      padding: 0 80px;
	      height: 535px;
	      background: #ffffff;
	      box-shadow: 0 15px 30px 0 rgba(111, 169, 228, 0.33);
	      border-radius: 10px;
	      text-align: center;
      }
     .login-main .title {
        font-size: 24px;
        color: #303030;
        letter-spacing: 0;
        line-height: 24px;
        padding: 50px 0;
      }
      .login-main input {
         margin-bottom: 30px;
         width: 460px;
         height: 64px;
         border-radius: 6px;
	    padding: 10px;
	    box-sizing: border-box;
	    font-size: 16px;
	    border: 1px solid #999;
	    outline: none;
      }
      .login-main .input-code{
      	width: 278px;
      	height: 60px;
      }
      .login-main .code{
      	vertical-align: middle;
      	margin-left:20px;
      }
      .login-main .flush{
      		vertical-align: middle;
      		cursor: pointer;
      }
      .login-main .button {
        height: 54px;
        color: #fff;
        background-color: #3f8fc8;
        border: none;
        cursor: pointer;
      }
      .help {
        display: inline-flex;
        justify-content: space-between;
        width: 460px;
        }
        .help a{        
          font-size: 18px;
          cursor: pointer;
          line-height: 18px;
          text-decoration: none;
        }
        .help a:hover{
          text-decoration: underline;
        }
       .help  .help-l {
          color: #7a7f85;
        }
       .help  .help-r {
          color: #7a7f85;
        }
</style>
</head>
<body>
	<div id="login" >
    <div class="login-bg"></div>
    <div class="login-box">
      <div class="login-title">
        <span>学生信息管理系统</span>
      </div>
      <div class="login-main">
        <p class="title">登录</p>
        <form method="post" action="login.do">
        <input type="text" name="username" placeholder="请输入用户名" ><br />
        <input type="password" name="password"  placeholder="请输入密码" ><br />
        <input type="text" class="input-code" placeholder="请输入验证码" maxlength="4" name="code"/><img class="code" onclick="flushImg()" src="getImgCode.do" /> <img class="flush" onclick="flushImg()" src="./images/flush.jpg" /> 
        <input type="submit" class="button" value="登录">
        </form>
        <p class="help">
          <a href="reset_password.jsp" class="help-l">忘记密码</span>
          <a href="register.jsp" class="help-r">注册</span>
        </p>
      </div>
    </div>
    
</body>
</html>