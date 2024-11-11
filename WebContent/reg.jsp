<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<title>用户注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	 
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
 .regTable td{
 	height:40px;
 }
 .regTable input{
 	height:30px;
 }
 .middlebg{
 	background-image:url('images/login_bg.jpg');
 	background-repeat:no-repeat;
 	background-size:100% 100%;
 }
 .loginDiv{
 	width:440px;min-height:240px;line-height:40px;overflow:hidden;
 	margin-left:calc(50% - 5px);
 	margin-top: 40px;
 	margin-bottom:20px;
 	padding:10px;
 	font-weight:bold;
    box-shadow: 0 4px 8px 20px rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    background-color: rgba(255, 255, 255, .4);
    /*设置负值，为要定位子盒子的一半高度*/ 
    border: solid #666 1px;
    border-radius: 8px 8px 8px 8px;
    filter:alpha(Opacity=80);
    -moz-opacity:0.8;
    opacity: 0.8; 
 }
.btn-info{
    font-size:18px!important;
    CURSOR: pointer;
	COLOR: white;
	width: 188px;
	HEIGHT: 40px!important;
	background-repeat: repeat-x;
	border: 1px solid #E46157;
	background-color:#E46157;
	vertical-align:text-bottom;
	border-radius: 8px;
	margin-left:60px!important;
 }
 .btn-info:hover{
	background-color:#EF6157;
 }
 .loginDiv input{
 	margin-left:30px;
 	margin-bottom:10px;
    vertical-align:middle;
    height:30px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"><jsp:param name="menu" value="login" /></jsp:include>
<div id="middle" class="middlebg">
	<FORM id="info" name="info"  method="post" action="LoginRegSystem.action">
	 <div class="loginDiv">
	 	 	<br/><INPUT type="text" placeholder="请输入用户名 必填" id="user_name" name="user_name" style="WIDTH: 160px" autocomplete="off">
			　<INPUT type="password" placeholder="请输入密码 必填"  id="user_pass" name="user_pass" value="" style="WIDTH: 160px"  autocomplete="new-password">
			 <br/><INPUT type="password" placeholder="再输入密码 必填"  id="user_rpass" name="user_rpass" value="" style="WIDTH: 160px"  autocomplete="new-password"> 
			　<INPUT type="text" placeholder="请输入姓名 必填" id="real_name" name="real_name" style="WIDTH: 160px">
			<input type="radio" name="user_sex" checked="checked" value="1" />&nbsp;&nbsp;&nbsp;&nbsp;
					男
						<input type="radio" name="user_sex" value="2" />
					&nbsp;&nbsp;&nbsp;&nbsp;	女&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <br/><INPUT type="text" placeholder="请输入昵称" id="nick_name" name="nick_name" style="WIDTH: 160px">
			　<INPUT type="text" placeholder="请输入电话 必填" id="user_phone" name="user_phone" style="WIDTH: 160px">
			<br/><INPUT type="text" placeholder="请输入地址" id="user_address" name="user_address" style="WIDTH: 160px">
			　<INPUT type="text" placeholder="请输入邮箱" id="user_mail" name="user_mail" style="WIDTH: 160px">
			<!--  <br/><INPUT type="text"  placeholder="请输入验证码" id="random" name="random" style="WIDTH: 110px" />
				 <img src="Random.jsp" height="30" width="66px" style="cursor:pointer;vertical-align:middle" title="换一张" id="safecode" border="0" onClick="reloadcode()"/>
				 -->
			<br/>　　　　　<input type="button" id="addBtn" class="btn-info" value="注　册"/> 
	 </div>
	 </FORM>
		 
</div>
<script language="javascript" type="text/javascript">
//实现验证码点击刷新
//function reloadcode(){
	//var verify=document.getElementById('safecode');
	//verify.setAttribute('src','Random.jsp?'+Math.random());
//}
$(document).ready(function(){
	var addBtn = $("#addBtn");
	var user_name = $("#user_name");
	var user_pass = $("#user_pass");
	var user_rpass = $("#user_rpass");
	var real_name = $("#real_name");
	//var nick_name = $("#nick_name");
	var user_phone = $("#user_phone");
	//var random = $("#random");
	
	var name=/^[a-z][a-z0-9_]{3,15}$/;
    var pass=/^[a-zA-Z0-9]{3,15}$/;
    var num= /^\d+$/;
	addBtn.bind("click",function(){
		
		if(user_name.val()==''||user_pass.val()==''){
			alert("用户名或密码不能为空");
			return;
		}
		/*
		if(!name.exec(user_name.val())){
			window.alert("用户名必须为字母开头 、字母数字4-16位！");
			return false;
		}
		if(!pass.exec(user_pass.val())){
			window.alert("密码必须为字母数字4-16位！");
			return false;
		}
		*/
		
		if(user_pass.val()!=user_rpass.val()){
			alert("两次输入密码不一致");
			return;
		}
		if(real_name.val()==''){
			alert("姓名不能为空");
			return;
		}
	//	if(nick_name.val()==''){
	//		alert("昵称不能为空");
		//	return;
		//}
		if(user_phone.val()==''){
			alert("联系电话不能为空");
			return;
		}
		//if(random.val()==''){
			//alert("验证码不能为空");
			//return;
		//}
		var aQuery = $("#info").serialize();  
		$.post('LoginRegSystem.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('注册成功！');
						 window.location.href="page_index.action";
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
});
</script>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>