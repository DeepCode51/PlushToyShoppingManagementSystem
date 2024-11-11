<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
 #infoField,#loginField{
 	line-height:35px;
 }
 
#leftContent{
	float:left;
	width:280px;
	overflow:hidden;
}
#rightContent{
	float:right;
	width:673px;
	overflow:hidden;
}

#left_type{
	width:calc(100% - 2px);
 	border:1px solid #dc851b;
 	margin-top:10px;
 	min-height:140px;
 	max-height:none;
}
#left_type .goods_type{
	width:95%;
	margin:0 auto;
 	margin-top:5px;
 	margin-bottom:5px;
	height:30px;
 	line-height:30px;
 	text-align:center;
 	border:1px solid #dc851b;
 	background-color:pink;
 	border-radius:5px;
 	font-size:16px;
}
#left_type .goods_type2{
	width:95%;
	margin:0 auto;
 	margin-top:5px;
	height:30px;
 	line-height:30px;
 	text-align:center;
 	backgrond-color:#dc851b;
 	color:white;
 	border-radius:5px;
 	font-size:16px;
}

#left_price{
	width:calc(100% - 2px);
 	border:1px solid #dc851b;
 	margin-top:10px;
 	min-height:160px;
 	line-height:30px;
 	font-size:14px;
 	text-align:center;
	overflow:hidden;
}

#topSearch{
	width:calc(100% - 12px);
 	border:1px solid #dc851b;
 	height:50px;
 	line-height:50px;
 	text-align:left;
 	padding-left:10px;
	overflow:hidden;
}
#topResult{
	width:100%;
 	margin-top:10px;
	overflow:hidden;
}
.btnstyle2{
	CURSOR: pointer;
	COLOR: white;
	height:25px;
	PADDING-TOP: 2px;
	background-color:#dc851b;
	BORDER:1px solid #dc851b;
	BORDER-radius:5px;
	vertical-align:text-bottom;
	
}
.products{
	min-height:220px;
	max-height:none;
	overflow:hidden;
}
.product{
	float:left;
	width:155px;
	height:160px;
	margin-top:5px;
	padding:5px;
	overflow:hidden;
}
.productPic{
	width:155px;
	height:120px;
	line-height:120px;
	text-align:center;
	overflow:hidden;
}
.productPic img{
	width:120px;
	height:120px;
	border:0px;
}
.productText{
	width:120px;
	text-align:left;
	padding-left:30px;
	line-height:25px;
	color:#A88D5A;
	overflow:hidden;
}
.productText .title{
	color:black;
	font-weight:bold;
}
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	<div id="leftContent">
		<div id="left_type" style="margin-top:0px">
			<div class="titleBg">商品类目</div>
			<c:if test="${goodsTypes!=null &&  fn:length(goodsTypes)>0}">
	   	    <c:forEach items="${goodsTypes}" var="goodsType">
	   	    <div class="${paramsGoods.goods_type_id==goodsType.goods_type_id?'goods_type2':'goods_type'}">
	   	    	<a href="page_index2.action?goods_type_id=${goodsType.goods_type_id}"> ${goodsType.goods_type_name}</a>
	   	    </div>
	   	    </c:forEach>
	   	    </c:if>
		</div>
		
		<div id="left_price">
		  <div class="titleBg" style="text-align:left;overflow:auto">公告栏</div>
		  ${info.info_content}
		</div>
    </div>
    
	<div id="rightContent">
	    <div id="topSearch">
	        <form name="infoTop" id="infoTop" action="page_index2.action" method="post">
	 		<input type="hidden" id="pageNo" name="pageNo" value="${paperUtil.pageNo}"/>
	 		<input type="hidden" id="goods_priceMin" name="goods_priceMin" value="${paramsGoods.goods_priceMin}"/>
	 		<input type="hidden" id="goods_priceMax" name="goods_priceMax" value="${paramsGoods.goods_priceMax}"/>
	 		
	    	<input type="text" id="goods_name" name="goods_name" value="${paramsGoods.goods_name}" class="inputstyle" style="width:300px;height:25px;border-radius:5px;margin-top:10px;" placeHolder="输入关键字搜索"/>
	    	<input type="button" id="searchBtn1" class="btnstyle2" value="搜 索" style="width:80px;height:30px;"/>
	    	</form>
	    </div>
	    <div id="topResult">
		    <div id="info" style="width:330px;float:left">
				<div class="titleBg">搜索结果</div>
				<div class="info_con" style="width:660px;height:590px;">
					<div class="products">
					<c:if test="${goodss!=null && fn:length(goodss)>0}">
   					<c:forEach items="${goodss}" var="goods" varStatus="status">
					<div class="product">
						<div class="productPic"><a href="page_queryGoods.action?goods_id=${goods.goods_id}"><img src="images/goodss/${goods.goods_pic}" /></a></div>
						<div class="productText"><span class="title">${goods.goods_name}</span></div>
						<div style="text-align:center">商品单价：￥${goods.goods_price2}</div>
					</div>
					</c:forEach>
					</c:if> 
					</div>
				<!--  
					 产品分页 
					<jsp:include page="page.jsp"></jsp:include>
				    产品分页 
				    -->	
		  
				</div>
			</div>
	    
	    </div>
    </div>

</div>
 
<jsp:include page="bottom.jsp"></jsp:include>
<script type="text/javascript">
//实现验证码点击刷新
	function reloadcode(){
		var verify=document.getElementById('safecode');
		verify.setAttribute('src','Random.jsp?'+Math.random());
	}
</script>	
</body>
