<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单明细信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
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
   document.info.action="Admin_listOrdersDetails.action";
   document.info.submit();
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
  document.info.action="Admin_listOrdersDetails.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listOrdersDetails.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	<span id="MainTitle" style="color:white">订单管理&gt;&gt;订单明细查询</span>
</div>
<form name="info" id="info" action="Admin_listOrdersDetails.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<input type="hidden" name="orders_no" value='${orders_no}'/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="" colspan="2">
    	订单编号：${orders_no}&nbsp;&nbsp;
    	订单总额：${orders_money}元
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
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
</form> 
</body>
</html>