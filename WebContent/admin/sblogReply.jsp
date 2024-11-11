<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回复留言信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script charset="utf-8" src="editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 $("#editBtn").bind('click',function(){
		editor.sync();
		if($("#noticeContent").val()==''){
			alert('回复内容不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveSblogReply.action').submit();
			 
	});
	
});
KindEditor.ready(function(K) {
	window.editor = K.create('#noticeContent',{
		width : '95%',
		items:[
			'fullscreen','|','justifyleft', 'justifycenter', 'justifyright','justifyfull',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline','anchor', 'link', 'unlink'
		],
		uploadJson : 'admin/editor2/jsp/upload_json.jsp',
        fileManagerJson : 'admin/editor2/jsp/file_manager_json.jsp',
        allowFileManager : true

	});
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
<span id="MainTitle" style="color:white">留言信息管理&gt;&gt;回复留言信息</span>
</div>
<form id="info" name="info" action="Admin_saveSblogReply.action" method="post">   
<input type="hidden" id="sblog_id" name="sblog_id" value="${sblog.sblog_id}" /> 
<input type="hidden" id="user_id" name="user_id" value="0" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">回复留言信息</TD>
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
          <td width="150" align="right" style="padding-right:5px"> 留言人：</td>
          <td width="*">
          	${sblog.user_name}
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"> 留言内容：</td>
          <td>
          	${sblog.sblog_contentShow}
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"> 留言时间：</td>
          <td>
          	${sblog.sblog_date}
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"> 回复信息：</td>
          <td>
          	<c:if test="${sblog.replys!=null &&  fn:length(sblog.replys)>0}">
   	 		<c:forEach items="${sblog.replys}" var="sblogReply" varStatus="status">
   	 		    <span style="color:red">${sblogReply.user_id==0?'管理员':sblogReply.user_name}</span> 回复：
   	 			<br/>${sblogReply.reply_contentShow}
   	 			<br/><span style="color:#666">回复时间：${sblogReply.reply_date}</span>
   	 			<br/>
   	 		</c:forEach>
   	 		</c:if>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"> 回复内容：</td>
          <td>
          	<textarea name="reply_content" id="noticeContent" style="width:500px;height:150px"></textarea>
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
          	<input type="button" id="editBtn" Class="btnstyle" value="回 复"/> 
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>