<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息管理系统</title>
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
	      height: 540px;
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
      .login-main .button {
        height: 54px;
        color: #fff;
        background-color: #3f8fc8;
        border: none;
        cursor: pointer;
      }
      .help {
        display: inline-flex;
        justify-content: flex-end;
        width: 460px;
        }
        .help a{        
          cursor: pointer;
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
        <p class="title">找回密码</p>
        <form method="post" action="reset_password.do"   >
        <input type="text" placeholder="请输入用户名"name="username" ><br />
        <input type="number" placeholder="请输入手机号"name="pho" ><br />
        <input type="password" placeholder="请输入新密码"name="newpassword" ><br />
        <input type="submit" class="button" value="提交">
        </form>
        <p class="help">
          <a href="login.jsp" class="help-r">返回登录</span>
        </p>
      </div>
    </div>
    
</body>
</html>