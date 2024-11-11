<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	 
	 $("#saveBtn").bind('click',function(){
		if($("#user_passOld").val()==''){
			alert('原密码不能为空');
			return;
		}
		if($("#user_pass").val()=='' || $("#user_rpass").val()==''){
			alert('新密码和确认密码不能为空');
			return;
		}
		if($("#user_pass").val() != $("#user_rpass").val()){
			alert('两次输入密码不一致');
			return;
		}
		$("#info").submit();
		 
	 });
	
});	 
	
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
<!-- 	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp; -->
	<span id="MainTitle" style="color:white">个人信息中心&gt;&gt;修改密码</span>
</div>
<form id="info" name="info" action="Admin_saveAdminPass.action" method="post">    
<input type="hidden" name="user_id" value="${admin.user_id}"/>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">修改个人密码</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td align="right" style="padding-right:5px"><font color="red"></font> 输入原密码：</td>
          <td>
            <input type="password" id="user_passOld" name="user_passOld"  />
          </td>
          <td align="right" style="padding-right:5px"></td>
          <td>
             
          </td> 
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red"></font> 输入新密码：</td>
          <td>
            <input type="password" id="user_pass" name="user_pass"  />
          </td>
          <td align="right" style="padding-right:5px"></td>
          <td>
             
          </td> 
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red"></font> 确认新密码：</td>
          <td>
            <input type="password" id="user_rpass" name="user_rpass"  />
          </td>
          <td align="right" style="padding-right:5px"></td>
          <td>
             
          </td> 
        </tr>
         
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
            <input type="button" id="saveBtn" Class="btnstyle" value="修 改"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>