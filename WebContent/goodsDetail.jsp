<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情展示</title>
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
	 //点击预定
	 var user_id = "${userFront.user_id}";
	 var goods_id = "${goods.goods_id}";
	 var num = /^\d+$/;
	 $("#addCard").bind('click',function(){
		 if(user_id==''){
			 alert('请先登录');
			 return;
		 }
		 if(!num.exec($("#goods_count").val())){
			 alert('购买数量必须为数字');
			 return;
		 }
		 if(Number($("#goods_count").val()) > Number($("#goods_count_real").val())){
			 alert('购买数量大于库存数量');
			 return;
		 }
		 $("#info").submit();
	 });

	 var url = "page_queryGoods.action?goods_id="+goods_id;
	$("#addCollect").bind('click',function(){
		 if(user_id==''){
			 alert('请先登录');
			 return;
		 }
		var aQuery = {
			'user_id':user_id,
		    'goods_id':goods_id
		};  
		$.post('page_addCollect.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('收藏成功！');
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	 });

}); 
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
	<!--  产品检索介绍 -->
	 <div id="product_info">
			<div class="productShow">
					<div class="title">${goods.goods_type_name}</div>
					<div class="typehr"></div>
					<div class="pictext">
							<div class="pic"><img src="images/goodss/${goods.goods_pic}" width="250px" height="250px"/></div>
							<div class="text">
									<form name="info" id="info" action="page_addCard.action" method="post">
									<input type="hidden" name="goods_id" id="goods_id" value="${goods.goods_id}"/>
									<input type="hidden" name="goods_name" id="goods_name" value="${goods.goods_name}"/>
									<input type="hidden" name="goods_price" id="goods_price" value="${goods.goods_price2}"/>
									<input type="hidden" id="goods_count_real" value="${goods.goods_count}"/>
									<div class="textTop" style="height:180px;line-height:30px;">
											商品名称：<span style="color:black">${goods.goods_name}</span>
											<br/>商品单价：<c:if test="${goods.goods_flag==2}"><span style="text-decoration: line-through;">￥${goods.goods_price1} </span></c:if><span style="color:black">￥${goods.goods_price2}</span></s:else>
											<br/>上架时间：<span style="color:black">${goods.goods_date}</span>
											<br/>库存数量：<span style="color:black">${goods.goods_count}</span>
											<!-- <br/>商品评分：<span style="color:black">${goods.evaluate_score} 分</span> -->
										 <br/>购买数量：<input type="text" id="goods_count" name="goods_count" value="1" style="width:80px"/> 
									</div>
									<div class="textBtn">
										<img id="addCard" src="images/addCard.png" width="120" style="border:none;cursor:pointer;vertical-align:middle"/>&nbsp;&nbsp;
										<img id="addCollect" src="images/addCollect.png" width="120" style="border:none;cursor:pointer;vertical-align:middle" />
									</div>
									</form>
							</div>
					</div>
					<div class="typehr"></div>
					<div class="title">商品详情介绍</div>
					<div class="typehr"></div>
					<div class="intro">
						${goods.goods_descShow}
					</div>
					
					<div class="typehr"></div>
				  	<div class="title">
						用户评价
					</div>
					<div class="typehr"></div>
					
					 <!-- 评价信息开始 -->
					 
					 <div id="pjxx" style="width:95%;max-height:500px;overflow:auto;margin-bottom:10px">
					 <c:if test="${evaluates!=null && fn:length(evaluates)>0}">
					 <c:forEach items="${evaluates}" var="sblog">
					 <div class="messages2" style="width:790px;margin-left:10px;margin-top:5px">
			 			<div class="messages_left" style="width:165px">
							<div class="nickName">${sblog.nick_name}</div>
							<div class="headphoto"><img class="circle" src="images/head/touxiang.jpg"/></div>
						</div>
				
						<div class="messages_right" style="min-height:100px;width:620px;">
							<div class="time">
								评价等级：${sblog.evaluate_levelDesc}（${sblog.evaluate_level}分） &nbsp;&nbsp;&nbsp;&nbsp;
								<%--<img src="images/time.gif" valign="middle"/>--%>
								评价时间：${fn:substring(sblog.evaluate_date,0,19)}　
							</div>
				
							<div class="sblog_title">
								 ${sblog.evaluate_content}
							</div>
							
						</div>
					 </div>
					 </c:forEach>
   					 </c:if>
					</div>
					  
					<!-- 评价信息结束 -->
			</div>

			 
			
	 </div>
	 <!--  产品检索 -->
	 
</div>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>