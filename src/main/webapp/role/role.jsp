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
					<i class="glyphicon glyphicon-role"></i> ${loginrole.role_name }<span class="caret"></span>
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
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;" id="roleForm">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="text" id="searchValue" name="searchVal" placeholder="请输入查询条件" >
    </div>
  </div>
  <button type="button" id="btnSearch" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" onclick="delRoles()" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${AppPath }/role/save'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
<form id="delForm">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" id="checkAll"></th>
                  <th>名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              
              	<tbody id="tableContent">
              
              
             
               
               
              	</tbody>
             
			  <tfoot>
			  
			  
			      <tr >
				     <td colspan="6" align="center">
						<ul class="pagination"  id="page">
						</ul>
					 </td>
				 </tr>  

			  
			  
			  </tfoot>
            </table>
           </form>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${AppPath }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${AppPath }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${AppPath }/script/docs.min.js"></script>
	<script src="${AppPath }/layer/layer.js"></script>
        <script type="text/javascript">
        var searchFlag=false;
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
			    $("#btnSearch").click(function(){
			    	var searchValue=$("#searchValue").val();
			    	if(searchValue!=""){
			    		searchFlag=true;
			    	}
			    	 goPage(1);
			    });
			    
			    goPage(1);
			    
			    //each函数(遍历的集合,function(循环遍历集合的下标位置,循环结果对象){})
			    $("#checkAll").click(function(){
			    	var flag=$(this).prop("checked");
			    	$.each($("#tableContent :checkbox"),function(index,obj){
			    		obj.checked=flag;
			    	});
			    });
            });
            
            $("tbody .btn-success").click(function(){
                window.location.href = "assignRole.html";
            });
            $("tbody .btn-primary").click(function(){
                window.location.href = "edit.html";
            });
            
         
          
		    
            
            function goPage(currentPage){
            	var jsonData={"currentPage":currentPage};
            	if(searchFlag){
    				jsonData.searchVal=$("#searchValue").val();        		
            	}
            	
            	$.ajax({
            		cache:false,
            		url:"${AppPath }/role/roleByPage",
            		type:"post",
            		data:jsonData,
            		async:true,
            		error:function(){
            			alert("查询失败");
            		},
            		success:function(data){
            			
            			if(data.flag){
            				
             				var tableStr="";
             			
            				$.each(data.pageInfo.list,function(i,role){
            					  tableStr+="<tr>";
            					  tableStr+="<td>"+(i+1)+"</td>";
            					  tableStr+="<td><input type='checkbox' name='rids' value="+role.roleid+"></td>";
            	                  tableStr+="<td>"+role.rolename+"</td>";
            	                  tableStr+="<td>";
            					  tableStr+="<button type='button' onclick='assignPermission("+role.roleid+")' class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></button>";
            					  tableStr+="<button type='button'  onclick='updateRole("+role.roleid+")' class='btn btn-primary btn-xs'><i class=' glyphicon glyphicon-pencil'></i></button>";
            					  tableStr+="<button type='button'  onclick='deleteRole("+role.roleid+")' class='btn btn-danger btn-xs'><i class=' glyphicon glyphicon-remove'></i></button>";
            					  tableStr+="</td>";
            					  tableStr+="</tr>";
            					
            				});
            					  $("#tableContent").html(tableStr);
            					  
            					
            					  
            					  var pageStr="";
            					  
            					  pageStr+="<li class='"+(data.pageInfo.currentPage==1?'disabled':'')+"'><a href='#' onclick='goPage("+((data.pageInfo.currentPage-1)<=1?1:data.pageInfo.currentPage-1)+")'>上一页</a></li>";
            					  for (var i = 1; i <= data.pageInfo.totalPage; i++) {
									if(i==data.pageInfo.currentPage){
	            					  pageStr+="<li class='active'><a href='#' onclick='goPage("+(i)+")'>"+i+" <span class='sr-only'>(current)</span></a></li>";
									}else{
										
	            					  pageStr+="<li><a href='#' onclick='goPage("+(i)+")'>"+i+"</a></li>";
									}
								 }
            					  pageStr+="<li class='"+(data.pageInfo.currentPage==data.pageInfo.totalPage?'disabled':'')+"'><a href='#' onclick='goPage("+((data.pageInfo.currentPage+1)>=data.pageInfo.totalPage?data.pageInfo.totalPage:data.pageInfo.currentPage+1)+")'>下一页</a></li>";
            					  
            					  $("#page").html(pageStr);
            			}else{
            				layer.msg("查询失败!", {time:1000, icon:0, shift:5}, function(){});
            			}
            				
            		}
            	});
            }
            
            
            function updateRole(rid){
            	window.location.href="${AppPath}/role/roleUpdForm?rid="+rid;
            }
            
            function assignPermission(rid){
            	window.location.href="${AppPath}/role/assignPermission?rid="+rid;
            }
            
            function deleteRole(rid){
            	layer.confirm("是否删除该角色!", {icon:3,title:'提示'}, function(cindex){
					layer.close(cindex);
					$.ajax({
						url:"${AppPath}/role/deleteRole",
						type:"post",
						data:{"rid":rid},
						success:function(data){
							if(data.flag){
	            				layer.msg("删除成功!", {time:1000, icon:1, shift:6}, function(){});
	            				window.location.href="${AppPath}/role/role";	
							}else{
	            				layer.msg("删除失败!", {time:1000, icon:0, shift:5}, function(){});
							}
						}

					});
			
    			});
            }
            
            
            function delRoles(){
            	layer.confirm("是否删除角色!", {icon:3,title:'提示'}, function(cindex){
					layer.close(cindex);
					$.ajax({
						url:"${AppPath}/role/deleteRoles",
						type:"post",
						data:$("#delForm").serialize(),
						success:function(data){
							if(data.flag){
	            				layer.msg("删除成功!", {time:1000, icon:1, shift:6}, function(){});
	            				window.location.href="${AppPath}/role/role";	
							}else{
	            				layer.msg("删除失败!", {time:1000, icon:0, shift:5}, function(){});
							}
						}

					});
			
    			});
            }
        </script>
  </body>
</html>
