<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


function serch()
{
   document.info.action="Admin_listUsers.action";
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的用户！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delUsers.action?ids="+ids;
       document.info.submit();
    }
    
}
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listUsers.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listUsers.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
<span id="MainTitle" style="color:white">用户管理&gt;&gt;用户查询</span>
</div>
<form name="info" id="info" action="Admin_listUsers.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">用户列表</td>
    <td width="" align="right">
            用户名：
      <input type="text" id="user_name" name="user_name" value="${paramsUser.user_name}" class="inputstyle"/>&nbsp;
            姓名：
      <input type="text" id="real_name" name="real_name" value="${paramsUser.real_name}" class="inputstyle"/>&nbsp;
         <!--  会员类型：
      <select id="member_type_id" name="member_type_id" class="selectstyle" style="width:155px;">
      	<option value="0">请选择</option>
      	<c:forEach items="${memberTypes}" var="memberType">
      	<option value="${memberType.member_type_id}" ${memberType.member_type_id==paramsUser.member_type_id?'selected':''}>${memberType.member_type_name}</option>
      	</c:forEach>
      </select>&nbsp;-->   
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="" align="center">用户名</td>
     <td width="" align="center">姓名</td>
     <td width="" align="center">性别</td>
     <td width="" align="center">昵称</td>
     <td width="" align="center">用户地址</td>
     <td width="" align="center">用户邮箱</td>
     <td width="" align="center">联系电话</td>
    <!--   <td width="" align="center">会员积分</td>
     <td width="" align="center">会员类型</td>
     <td width="" align="center">注册时间</td>
     <td width="" align="center">操作</td>-->
   </tr>
   <c:if test="${users!=null && fn:length(users)>0}">
   <c:forEach items="${users}" var="user" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><input type="checkbox" name="chkid" value="${user.user_id}" style="vertical-align:text-bottom;"/></td>
     <td width="" align="center">${status.index+1+paramsUser.start}</td>
     <td width="" align="center">${user.user_name}</td>
     <td width="" align="center">${user.real_name}</td>
     <td width="" align="center">${user.user_sexDesc}</td>
     <td width="" align="center">${user.nick_name}&nbsp;</td>
     <td width="" align="center">${user.user_address}&nbsp;</td>
     <td width="" align="center">${user.user_mail}&nbsp;</td>
     <td width="" align="center">${user.user_phone}&nbsp;</td>
    <!--   <td width="" align="center">${user.user_credit}&nbsp;</td>
     <td width="" align="center">${user.member_type_name}&nbsp;</td>
     <td width="" align="center">${fn:substring(user.reg_date,0,10)}</td>  
     -->
   <!--    <td width="" align="center">
       <img src="images/edit.png"/>&nbsp; 
       
      <a href="Admin_editUser.action?user_id=${user.user_id}">编辑</a>
     </td>--> 
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${users==null || fn:length(users)==0}">
   <tr>
     <td height="60" colspan="14" align="center"><b>&lt;不存在用户信息&gt;</b></td>
   </tr>
   </c:if>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>