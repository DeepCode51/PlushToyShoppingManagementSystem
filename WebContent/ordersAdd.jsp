<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>提交订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/reg.css">
<link rel="stylesheet" type="text/css" href="css/info.css">
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
	<div id="list">
		 <div class="nav">当前位置: 主页 > 提交订单 </div>
		 <div class="list_info">
		 	 <form name="info" id="info" style="width:100%;min-height:500px" action="LoginRegSystem.action" method="post">
		     <input type="hidden" name="user_id" value="${userFront.user_id}"/>
		     <input type="hidden" name="real_name" value="${userFront.real_name}"/>
		     <input type="hidden" name="real_money" value="${real_money}"/>
		     <input type="hidden" name="user_discard" value="${user_discard}"/>
			 <table class="regTable">
			 	<tr class="theme">
					<td width="" align="center">商品名称</td>
				    <td width="" align="center">商品单价</td>
				    <td width="" align="center">商品数量</td>
				    <td width="" align="center">商品总额</td>
				</tr>
			 	<c:if test="${ordersDetails!=null && fn:length(ordersDetails)>0}">
   				<c:forEach items="${ordersDetails}" var="ordersDetail" varStatus="status">
				<tr>
					<td width="" align="center">${ordersDetail.goods_name}</td>
					<td width="" align="center">￥${ordersDetail.goods_price}</td>
					<td width="" align="center">${ordersDetail.goods_count}</td>
					<td width="" align="center">￥${ordersDetail.goods_money}</td>
			    </tr> 
			    </c:forEach>
			    <tr>
					<td colspan="4" align="right" style="padding-right:50px;font-weight:bold">
						订单总额：￥${orders_money}&nbsp;&nbsp;			
					</td>
			    </tr> 
			    <tr>
					<td align="right"><span style="color:red"></span> 联系电话：</td>
					<td align="left" colspan="3">
						<input type="text" name="user_phone" id="user_phone" value="${userFront.user_phone}" style="width:200px;" class="inputstyle"/>
					</td>
			    </tr> 
			    <tr>
					<td align="right"><span style="color:red"></span> 联系地址：</td>
					<td align="left" colspan="3">
						<input type="text" name="user_address" id="user_address" value="${userFront.user_address}" style="width:200px;" class="inputstyle"/>
					</td>
			    </tr> 
			    <tr>
					<td align="right">备注：</td>
					<td align="left" colspan="3">
						<input type="text" name="orders_note" id="orders_note" value="" style="width:200px;" class="inputstyle"/>
					</td>
			    </tr> 
			    <tr class="theme">
			        <td colspan="4" align="left" style="padding-left:10px;font-weight:bold">
						请选择支付方式：
					</td>
			    </tr>
			    <tr class="theme">
			        <td colspan="4" align="left">
						<div style="width:260px;height:69px;line-height:69px;background-image:url('images/zhifu.jpg')">
							<input type="radio" name="bank" checked="checked" />　　　　　　　　　
							<input type="radio" name="bank" />　　　　　　　　　　
							<!-- <input type="radio" name="bank" />　-->
						</div>
					</td>
			    </tr>
			    <!--  
			     <tr>
					<td colspan="4" align="left">
						<div style="width:680px;margin:0 auto;height:150px;line-height:37px;background-image:url('images/bank.jpg')">
							<input type="radio" name="bank" />　　　　　　　　　
							<input type="radio" name="bank" />　　　　　　　　　　
							<input type="radio" name="bank" />　　　　　　　　
							<input type="radio" name="bank" />　　　　　　　　　
							<input type="radio" name="bank" />　　
							<br/>
							<input type="radio" name="bank" />　　　　　　　　　
							<input type="radio" name="bank" />　　　　　　　　　　
							<input type="radio" name="bank" />　　　　　　　　
							<input type="radio" name="bank" />　　　　　　　　　
							<input type="radio" name="bank" />　　
							<br/>
							<input type="radio" name="bank" />　　　　　　　　　
							<input type="radio" name="bank" />　　　　　　　　　　
							<input type="radio" name="bank" />　　　　　　　　
							<input type="radio" name="bank" />　　　　　　　　　
							<input type="radio" name="bank" />　　
							<br/>
							<input type="radio" name="bank" />　　　　　　　　　
							<input type="radio" name="bank" />　　
						</div>
					</td>
			    </tr>
			    -->
			    <tr>
			    	<td align="right"></td>
					<td align="left" colspan="3">
						<input type="button" id="addBtn" class="btnstyle" value="确认下单"/>
						
					</td>
				</tr>
				</c:if>
			   <c:if test="${ordersDetails==null && fn:length(ordersDetails)==0}">
			   <tr>
			     <td height="60" colspan="4" align="center"><b>&lt;不存在订单信息&gt;</b></td>
			   </tr>
			   </c:if>
		 	 </table>
		 </div>
	</div>
	
	 
	<div id="Picture"></div>
	
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	$(document).ready(function(){
		var addBtn = $("#addBtn");
		//提交订单
		addBtn.bind("click",function(){
			if($("#user_phone").val()==''){
				alert("联系电话不能为空");
				return;
			}
			if($("#user_address").val()==''){
				alert("联系地址不能为空");
				return;
			}
			var aQuery = $("#info").serialize();
			$.post('page_addOrders.action',aQuery,
					function(responseObj) {
						if(responseObj.success) {
							alert('支付成功！');
							window.location.href="page_listMyOrderss.action";
						}else  if(responseObj.err.msg){
							alert('失败：'+responseObj.err.msg);
						}else{
							alert('失败：服务器异常！');
						}
					},'json');
		});


	});
</script>
</body>
</html>