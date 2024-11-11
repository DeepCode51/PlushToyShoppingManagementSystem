<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<div id="top">
<img alt="a" src="images/top.png">
</div>
<div id="navMenu">
	<ul>
	  <li style="background-image:none"><a href="page_index.action">  首 页</a></li>
	  <li><a href="page_goods.action">商品浏览</a></li>
	  <c:if test="${userFront!=null}">
      <!--  <li><a href="page_goodsHobby.action">商品推荐</a></li>  -->
      </c:if>
	  <li><a href="page_sblog.action">留言板</a></li>
	  <c:if test="${userFront==null}">
      <li><a href="login.jsp">登录</a></li>  
      <li><a href="reg.jsp">注册</a></li>  
      </c:if>
	  <c:if test="${userFront!=null}">
	  <li><a href="page_listCard.action">购物车</a></li>
	  <li><a href='page_myInfo.action'>我的信息</a></li>
      <li><a id="loginOutBtn" href="javascript:void(0)">退出登录</a></li>  
	  </c:if>
	</ul>
</div>
<script type="text/javascript" src="js/login.js"></script>
<script language="javascript" type="text/javascript">
$(function(){
	var EHeight = document.documentElement.clientHeight;
	var BHeight = document.body.clientHeight;
	var Height1 = Math.max(EHeight,BHeight);
	var ESHeight = document.documentElement.scrollHeight;
	var Height2 = Math.min(BHeight,ESHeight);
	var bottomM = Math.max(Height1 - Height2,5);
	var middlebg = $(".middlebg");
	if(middlebg && middlebg.length>0){
		middlebg.height(middlebg.height() + bottomM - 10);
	}else{
		$("#bottom").css("margin-top", bottomM);
	}
});
</script>
