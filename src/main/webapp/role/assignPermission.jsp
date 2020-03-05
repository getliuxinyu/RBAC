<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${AppPath }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${AppPath }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${AppPath }/css/main.css">
	<link rel="stylesheet" href="${AppPath }/ztree/zTreeStyle.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> ${loginUser.user_name }<span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="${AppPath }/loginout"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item tree-closed" >
						<a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a> 
					</li>
					<li class="list-group-item">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="${AppPath }/user/user" ><i class="glyphicon glyphicon-role"></i> 用户维护</a> 
							</li>
							<li style="height:30px;">
								<a href="${AppPath }/role/role" style="color:red;"><i class="glyphicon glyphicon-king"></i> 角色维护</a> 
							</li>
							<li style="height:30px;">
								<a href="${AppPath}/permission/index"><i class="glyphicon glyphicon-lock"></i> 许可维护</a> 
							</li>
						</ul>
					</li>
					
				</ul>
			</div>
        </div>
       <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">权限分配列表</a></li>
				</ol>
			<div class="panel panel-default">
			  <div class="panel-body">
				<form role="form" class="form-inline" id="assignPermission">
				   <input type="hidden" name="rid" id="rid" value="${rid }">
				   <button class="btn btn-success" id="btnAssign">分配许可</button>
                  	<br><br>
                  <ul id="permissionTree" class="ztree"></ul>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>测试标题1</h4>
				<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>测试标题2</h4>
				<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
   	<script src="${AppPath }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${AppPath }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/script/docs.min.js"></script>
	<script src="${AppPath }/layer/layer.js"></script>
	<script src="${AppPath }/ztree/jquery.ztree.all-3.5.min.js"></script>
	
        <script type="text/javascript">
            var setting = {
        			async: {
        				enable: true,
        				url:"${AppPath }/permission/loadData",
        				autoParam : [ "id", "name=n", "level=lv" ]
        				
        			},
        			
            		check:{
            			enable: true
            		}
        		};

            $(function () {
    		    $(".list-group-item").click(function(){
    			    if ( $(this).find("ul") ) {
    					$(this).toggleClass("tree-closed");
    					if ( $(this).hasClass("tree-closed") ) {
    						$("ul", this).hide("fast");
    					} else {
    						$("ul", this).show("fast");
    					}
    				}
    			});
    		   
    		   
    		    $.fn.zTree.init($("#permissionTree"), setting);
    		    
    		    $("#btnAssign").click(function(){
    		    	//获得树对象
    		    	var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
    		    
    		    	//获得选中的项
    		    	var nodes = treeObj.getCheckedNodes(true);
    		    	//判断是否有选中项
    		    	if(nodes.length==0){
    		    		layer.msg("请选择需要分配的许可！",{ time:1000,icon:5, shift:6}, function(){});
    		    	}else{
    		    		var dataVal="rid=${param.rid}";
    		    		$.each(nodes,function(index,obj){
    		    			dataVal+="&pids="+obj.pid;
    		    		});
    		    		
    		    		
    		    		$.ajax({
				    		url:"${AppPath}/role/assignPermissionDo",
				    		type:"post",
				    		data:dataVal,
				    		
				    		success:function(data){
				    			if(data.flag){
				    				layer.msg("分配许可成功!", {time:1000,icon:6, shift:5}, function(){});
				    				window.location.href="${AppPath }/role/role";
				    			}else{
				    				layer.msg("分配许可失败!", {time:1000,icon:5, shift:5}, function(){});
				    			}
				    		}
				    		
				    	});
    		    	}
    		    });
            }); 
        </script>
  </body>
</html>
