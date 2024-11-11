<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言板</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/product.css">
<link rel="stylesheet" type="text/css" href="css/message.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/clipboard.min.js"></script>
<script charset="utf-8" src="admin/editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="admin/editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript">
 $(document).ready(function(){
	 var user_id = "${userFront.user_id}";
	 $("#addBtn").bind("click",function(){
		editor.sync();
		if(user_id==''){
			alert('请先登录后在进行留言！')
			return;
		}
		if($("#noticeContent").val()==''){
			alert('留言内容不能为空！')
			return;
		}
		var aQuery = $("#infoSblog").serialize();  
		$.post('page_addSblog.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('感谢您的留言！');
						 window.location.reload();
					}else  if(responseObj.err.msg){
						 alert('留言失败：'+responseObj.err.msg);
					}else{
						 alert('留言失败：服务器异常！');
					}	
		},'json');
	});

	$("#replyBtn").bind("click",function(){
		editor2.sync();
		if(user_id==''){
			alert('请先登录后在进行回复！')
			return;
		}
		if($("#noticeContent2").val()==''){
			alert('回复内容不能为空！')
			return;
		}
		var aQuery = $("#infoSblogReply").serialize();  
		$.post('page_addSblogReply.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('回复成功！');
						 window.location.reload();
					}else  if(responseObj.err.msg){
						 alert('回复失败：'+responseObj.err.msg);
					}else{
						 alert('回复失败：服务器异常！');
					}	
		},'json');
	});
	$("#cancelReply").bind("click",function(){
		$("#addSblogReply").hide();
		$("#addSblog").fadeIn();
	});

}); 
function initForm(sblog_id,sblog_user){
	$("#addSblog").hide();
	$("#addSblogReply").fadeIn();
	$("#sblog_id").val(sblog_id);
	$("#replyUser").html(sblog_user);
}
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
 
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middleBg">
	<!--  检索介绍 -->
	 <div id="product_info">
			<div class="productShow">
					<div class="typehr"></div>
					<div class="title">
						用户留言
					</div>
					<div class="typehr"></div>
					
					<!-- 发布留言开始 -->
					 <div id="addSblog" style="width:95%;margin:0 auto;">
					 <form name="infoSblog" id="infoSblog" action="page_addSblog.action" method="post">
					 <input type="hidden" name="user_id" id="user_id" value="${userFront.user_id}"/>
					 <table class="reply_add">
						<tr>
							<td class="theme" colspan="2">发表留言：</td>
						</tr>
						<tr>
							<td align="left" colspan="2" style="padding-left:10px">
								<textarea name="sblog_content" id="noticeContent" style="width:600px;height:150px" class="inputstyle"></textarea>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2" style="padding-left:10px">
								<input type="button" id="addBtn" class="btnstyle" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" id="resetBtn" class="btnstyle" value="清空"/>
							</td>
						</tr>
					 </table>
					 </form>
					 </div>
					 <a name="link"></a>
					 <!-- 发布留言结束 -->
					 
					 <!-- 回复留言开始 -->
					 <div id="addSblogReply" style="width:95%;margin:0 auto;display:none">
					 <form name="infoSblogReply" id="infoSblogReply" action="page_addSblogReply.action" method="post">
					 <input type="hidden" name="sblog_id" id="sblog_id" value="0"/>
					 <input type="hidden" name="user_id" id="user_id" value="${userFront.user_id}"/>
					 <table class="reply_add">
						<tr>
							<td class="theme" colspan="2">回复 <span id="replyUser" style="color:red"></span>：</td>
						</tr>
						<tr>
							<td align="left" colspan="2" style="padding-left:10px">
								<textarea name="reply_content" id="noticeContent2" style="width:600px;height:150px" class="inputstyle"></textarea>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2" style="padding-left:10px">
								<input type="button" id="replyBtn" class="btnstyle" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" id="cancelReply" class="btnstyle" value="取消"/>
							</td>
						</tr>
					 </table>
					 </form>
					 </div>
					 <!-- 回复留言结束 -->
					
					<!-- 留言信息开始 -->
					 <div id="lyxx" style="width:95%;max-height:500px;overflow:auto;">
					 <c:if test="${sblogs!=null && fn:length(sblogs)>0}">
				   	 <c:forEach items="${sblogs}" var="sblog" varStatus="status">
					 <div class="messages2" style="width:790px;margin-left:10px;margin-top:5px">
			 			<div class="messages_left" style="width:165px">
							<div class="nickName" style="">&nbsp;&nbsp;${sblog.user_name}</div>
							<div class="headphoto"><img class="circle" src="images/head/touxiang.jpg"/></div>
							<div class="stuNo"></div>
						</div>
						<div class="messages_right" style="min-height:100px;width:620px;">
							<div class="time">
								<!--<img src="images/time.gif" valign="middle"/> -->
								留言时间：
								${fn:substring(sblog.sblog_date,0,19)}
								&nbsp;&nbsp;&nbsp;&nbsp;
								<!--<img src="images/quote.gif" valign="middle"/> -->
								<a href="#link" onclick="initForm('${sblog.sblog_id}','${sblog.user_name}')">回复</a>
							</div>
							<div class="sblog_title">
							     ${sblog.sblog_contentShow}
							</div>
							<c:if test="${sblog.replys != null && fn:length(sblog.replys) >0}">
					 		<c:forEach items="${sblog.replys}" var="sblogReply">
							<div class="reply">
								 <div class="user">${sblogReply.user_id==0?'管理员':sblogReply.user_name} 回复：</div>
								 <div class="reply_con">
								 	${sblogReply.reply_contentShow}
								 </div>
								 <div class="reply_time">回复时间：${sblogReply.reply_date}</div>
							</div>
							<hr/>
							</c:forEach>
					 		</c:if>
						</div>
					 </div>
					 </c:forEach>
					 </c:if>
					</div>
					 <!-- 留言信息结束 -->
					 
					 <!--  产品分页 -->
					<jsp:include page="page.jsp"></jsp:include>
				    <!--  产品分页 -->
			</div>

			 
			
	 </div>
	 <!--  产品检索 -->
	 
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script type="text/javascript">
KindEditor.ready(function(K) {
	window.editor = K.create('#noticeContent',{
		width : '95%',
		items:[
			'fullscreen','|','justifyleft', 'justifycenter', 'justifyright','justifyfull',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline','anchor', 'link', 'unlink','|','image'
		],
		uploadJson : 'admin/editor2/jsp/upload_json.jsp',//上传图片接口
        fileManagerJson : 'admin/editor2/jsp/file_manager_json.jsp',
        allowFileManager : true

	});
	window.editor2 = K.create('#noticeContent2',{
		width : '95%',
		items:[
			'fullscreen','|','justifyleft', 'justifycenter', 'justifyright','justifyfull',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline','anchor', 'link', 'unlink','|','image'
		],
		uploadJson : 'admin/editor2/jsp/upload_json.jsp',
        fileManagerJson : 'admin/editor2/jsp/file_manager_json.jsp',
        allowFileManager : true

	});
});
</script>
</body>
</html>