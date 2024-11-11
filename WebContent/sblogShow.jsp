<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的留言信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	
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
<div id="middle">
	 <div id="product_menu">
	 	 <jsp:include page="leftMenu.jsp"></jsp:include>
	 </div>
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  我的留言信息</div>
			<div style="margin-top:5px">
				 <table class="ptable" style="margin-bottom:5px;">
					<tr class="head_text">
					     <td width="40" align="center">编号</td>
					   <!--    <td width="200" align="left">商品名称</td>-->
					     <td width="*" align="left">留言内容</td>
					</tr>
					<c:if test="${sblogs!=null &&  fn:length(sblogs)>0}">
   					<c:forEach items="${sblogs}" var="sblog" varStatus="status">
					<tr> 
				     <td width="" align="center">${status.index+1+paramsSblog.start}</td>
				   <!--  <td width="" align="left">${sblog.goods_name}</td>-->  
				     <td width="" align="left" style="line-height:22px">
				        <img src="images/time.gif" valign="middle"/> ${sblog.sblog_date}
				        <br/>${sblog.sblog_contentShow}
				        <c:if test="${sblog.replys!=null &&  fn:length(sblog.replys)>0}">
				 		<c:forEach items="${sblog.replys}" var="sblogReply" varStatus="status2">
						<div class="reply">
							 <div class="user" style="color:red">管理员回复：</div>
							 <div class="reply_con">
								${sblogReply.reply_contentShow}
							 </div>
							 <div class="reply_time" style="color:#ccc">回复时间：${sblogReply.reply_date}</div>
						</div>
						</c:forEach>
				 		</c:if>
				     </td>
				   </tr> 
				   </c:forEach>
				   </c:if>
				   <c:if test="${sblogs==null ||  fn:length(sblogs)==0}">
				   <tr>
				     <td height="60" colspan="12" align="center"><b>&lt;不存在留言信息&gt;</b></td>
				   </tr>
				   </c:if>
				 </table>
			</div>
			<div class="pages">
				<jsp:include page="page.jsp"></jsp:include>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	function GoPage()
	{
	  var pagenum=document.getElementById("goPage").value;
	  var patten=/^\d+$/;
	  if(!patten.exec(pagenum))
	  {
	    alert("页码必须为大于0的数字");
	    return false;
	  }
	  window.location.href="page_listMySblogs.action?pageNo="+pagenum;
	}
	function ChangePage(pagenum)
	{
		window.location.href="page_listMySblogs.action?pageNo="+pagenum;
	}
	
	$(document).ready(function(){
	});
	 
</script>
</body>
</html>