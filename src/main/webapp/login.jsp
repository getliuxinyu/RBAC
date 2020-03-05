<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/login.css">
	
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.html" style="font-size:32px;">平台</a></div>
        </div>
      </div>
    </nav>

	
    <div class="container">
      <form class="form-signin" role="form" id="loginForm" action="loginJsp" method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-user"></i>用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" name="user_name" id="uname" placeholder="请输入用户账户">
			<span class="glyphicon glyphicon-user form-control-feedback" id="username"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" name="user_pwd" id="upwd" placeholder="请输入用户密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" >登录</a>
      </form>
        
      
      
    </div>
    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="layer/layer.js"></script>
    
    
    <script>
    function dologin() {
        var uname=$("#uname").val();
        if(uname==""){
        	layer.msg("用户名不能为空！",{time:1000,icon:0,shfit:5},function(){});
        	return;
        }
        var upwd=$("#upwd").val();
        if(upwd==""){
        	layer.msg("密码不能为空！",{time:1000,icon:0,shfit:5},function(){});
			return;
        }
       $("#loginForm").submit();
    }
    
    //ajax
  	 $.ajax({
    	cache:false,
    	url:"loginAjax",
    	type:"post",
    	data:$("loginForm").serialize(),
    	async:false,
    	error:function(){
    		alert("登陆失败");
    	},
    	success:function(data){
    		if(data.flag){
    			//所有页面跳转都交由controller来控制
    			window.location.href="main";
    		}else{
    			layer.msg("用户名密码错误！",{time:1000,icon:1,shfit:5},function(){});
    		}
    	}
    	
    });
    
    </script>
  </body>
</html>