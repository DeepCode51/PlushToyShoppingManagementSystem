<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品销售统计信息</title>
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
   document.info.action="Admin_listOrdersDetailsSum.action";
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
  document.info.action="Admin_listOrdersDetailsSum.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listOrdersDetailsSum.action";
  document.info.submit();
}
$(document).ready(function(){
	 
});
</script>
</head>
<body>
<div class="pageTitle">
<span id="MainTitle" style="color:white">商品销售统计&gt;&gt;商品销售统计查询</span>
</div>
<form name="info" id="info" action="Admin_listOrdersDetailsSum.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">商品销售统计列表</td>
    <td width="" align="right">
            商品名称：
      <input type="text" id="goods_name" name="goods_name" value="${paramsOrdersDetail.goods_name}" class="inputstyle" Style=""/>&nbsp;
              统计时间：
      <input type="text" id="orders_date_min" name="orders_date_min" value="${paramsOrdersDetail.orders_date_min}" class="inputstyle" Style=""
      		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
      		&nbsp;-&nbsp;
      <input type="text" id="orders_date_max" name="orders_date_max" value="${paramsOrdersDetail.orders_date_max}" class="inputstyle" Style=""
      		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center">序号</td>
     <td width="" align="center">商品名称</td>
     <td width="" align="center">销售数量</td>
     <td width="" align="center">销售总额</td>
   </tr>
   <c:if test="${ordersDetails!=null && fn:length(ordersDetails)>0}">
   <c:forEach items="${ordersDetails}" var="goods" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center">${status.index+1+paramsOrdersDetail.start}</td>
     <td width="" align="center">${goods.goods_name}</td>
     <td width="" align="center">${goods.goods_count}</td>
     <td width="" align="center">${goods.goods_money}</td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${ordersDetails==null || fn:length(ordersDetails)==0}">
   <tr>
     <td height="60" colspan="12" align="center"><b>&lt;不存在商品销售统计信息&gt;</b></td>
   </tr>
   </c:if>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>