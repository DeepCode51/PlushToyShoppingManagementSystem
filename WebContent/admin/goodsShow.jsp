<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品信息</title>
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
   document.info.action="Admin_listGoodss.action";
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
       alert("请至少选择一个要删除的商品！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delGoodss.action?ids="+ids;
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
  document.info.action="Admin_listGoodss.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listGoodss.action";
  document.info.submit();
}
$(document).ready(function(){
	 
});
</script>
</head>
<body>
<div class="pageTitle">

	<span id="MainTitle" style="color:white">商品管理&gt;&gt;商品查询</span>
</div>
<form name="info" id="info" action="Admin_listGoodss.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${paperUtil.pageNo}"/>
<input type="hidden" name="goods_kind" id="goods_kind" value="1"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">商品列表</td>
    <td width="" align="right">
            商品名称：
      <input type="text" id="goods_name" name="goods_name" value="${paramsGoods.goods_name}" class="inputstyle" Style=""/>&nbsp;
            商品类目：
      <select id="goods_type_id" name="goods_type_id" class="selectstyle" style="width:155px;">
      	<option value="0">请选择</option>
      	<c:forEach items="${goodsTypes}" var="goodsType">
      	<option value="${goodsType.goods_type_id}" ${goodsType.goods_type_id==paramsGoods.goods_type_id?'selected':''}>${goodsType.goods_type_name}</option>
      	</c:forEach>
      </select>&nbsp;
          <!--  商品状态：
      <select id="goods_flag" name="goods_flag" class="selectstyle" style="width:155px;">
      	<option value="0">请选择</option>
      	<option value="1" ${1==paramsGoods.goods_flag?'selected':''}>正常</option>
      	<option value="2" ${2==paramsGoods.goods_flag?'selected':''}>促销</option>
      </select>&nbsp; -->
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
     <td width="" align="center">商品名称</td>
     <td width="" align="center">商品类目</td>
     <td width="" align="center">商品原价</td>
     <td width="" align="center">当前价格</td>
   <!--  <td width="" align="center">商品状态</td> --> 
     <td width="" align="center">库存数量</td>
     <td width="" align="center">添加时间</td>
     <td width="" align="center">操作</td>
   </tr>
   <c:if test="${goodss!=null && fn:length(goodss)>0}">
   <c:forEach items="${goodss}" var="goods" varStatus="status">
   <tr class="list0" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><input type="checkbox" name="chkid" value="${goods.goods_id}" style="vertical-align:text-bottom;"/></td>
     <td width="" align="center">${status.index+1+paramsGoods.start}</td>
     <td width="" align="center">${goods.goods_name}</td>
     <td width="" align="center">${goods.goods_type_name}</td>
     <td width="" align="center">${goods.goods_price1}</td>
     <td width="" align="center">${goods.goods_price2}</td>
   <!--    <td width="" align="center">${goods.goods_flagDesc}</td>-->
     <td width="" align="center">${goods.goods_count}</td>
     <td width="" align="center">${fn:substring(goods.goods_date,0,10)}</td>
     <td width="" align="center">
       <a href="Admin_queryGoods.action?goods_id=${goods.goods_id}">查看</a>&nbsp;
       <a href="Admin_editGoods.action?goods_id=${goods.goods_id}">编辑</a>
     </td>
   </tr> 
   </c:forEach>
   </c:if>
   <c:if test="${goodss==null || fn:length(goodss)==0}">
   <tr>
     <td height="60" colspan="12" align="center"><b>&lt;不存在商品信息&gt;</b></td>
   </tr>
   </c:if>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>