<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的订单明细</title>
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
			<div class="title">个人中心  &gt;&gt;  我的订单明细</div>
			<div style="margin-top:5px">
				 <table class="ptable" style="margin-bottom:5px;">
				 	<tr>
					     <td colspan="5" align="left">
					     	订单编号：${orders_no}&nbsp;&nbsp;
    						订单总额：${orders_money}元
					     </td>
					</tr>
					<tr class="head_text">
					     <td width="40" align="center">序号</td>
					     <td width="" align="center">商品名称</td>
					     <td width="" align="center">商品单价</td>
					     <td width="" align="center">商品数量</td>
					     <td width="" align="center">商品总额</td>
					</tr>
				   <c:if test="${ordersDetails!=null && fn:length(ordersDetails)>0}">
				   <c:forEach items="${ordersDetails}" var="ordersDetail" varStatus="status">
				   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
				     <td width="" align="center">${status.index+1}</td>
				     <td width="" align="center">${ordersDetail.goods_name}</td>
				     <td width="" align="center">${ordersDetail.goods_price}</td>
				     <td width="" align="center">${ordersDetail.goods_count}</td>
				     <td width="" align="center">${ordersDetail.goods_money}</td>
				   </tr> 
				   </c:forEach>
				   </c:if>
				   <c:if test="${ordersDetails==null || fn:length(ordersDetails)==0}">
				   <tr>
				     <td height="60" colspan="6" align="center"><b>&lt;不存在订单明细信息&gt;</b></td>
				   </tr>
				   </c:if>
				 </table>
			</div>
			<div class="pages">
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
    var orders_no = '<s:property value="#attr.orders_no"/>';
	function GoPage()
	{
	  var pagenum=document.getElementById("goPage").value;
	  var patten=/^\d+$/;
	  if(!patten.exec(pagenum))
	  {
	    alert("页码必须为大于0的数字");
	    return false;
	  }
	  window.location.href="page_listMyOrdersDetails.action?pageNo="+pagenum+"&orders_no="+orders_no;
	}
	function ChangePage(pagenum)
	{
		window.location.href="page_listMyOrdersDetails.action?pageNo="+pagenum+"&orders_no="+orders_no;
	}
</script>
</body>
</html>