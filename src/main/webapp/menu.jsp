<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul style="padding-left:0px;" class="list-group">
	<li class="list-group-item tree-closed" >
			<a href="${AppPath }${root.url}"><i class="glyphicon glyphicon-dashboard"></i>${root.name }</a> 
		</li>
	<c:forEach items="${root.children }" var="permission">
	<c:if test="${empty root.children }">
		<li class="list-group-item tree-closed" >
			<a href="${AppPath }${permission.url}"><i class="glyphicon glyphicon-dashboard"></i>${permission.name }</a> 
		</li>
	</c:if>
	<c:if test="${not empty root.children }">
	<li class="list-group-item tree-closed">
		<span><i class="glyphicon glyphicon glyphicon-tasks"></i> ${permission.name } <span class="badge" style="float:right">${permission.children.size() }</span></span> 
		<ul style="margin-top:10px;display:none;">
			<c:forEach items="${permission.children }" var="child">
				<li style="height:30px;">
					<a href="${AppPath }${child.url}"><i class="glyphicon glyphicon-user"></i> ${child.name }</a> 
				</li>
			</c:forEach>
		</ul>
	</li>
	</c:if>
	</c:forEach>
</ul>