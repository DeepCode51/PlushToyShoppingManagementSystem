<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${goods!=null && goods.goods_id!=0?'编辑':'添加'}商品信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script charset="utf-8" src="editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="editor2/lang/zh-CN.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
	 var num = /^\d+(\.\d+)?$/;
	 var num2 = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		editor.sync();
		if($("#goods_name").val()==''){
			alert('商品名称不能为空');
			return;
		}
		if($("#goods_type_id").val()=='0'){
			alert('商品类目不能为空');
			return;
		}
		if($("#goods_pic").val()==''){
			alert('商品图片不能为空');
			return;
		}
		//if($("#paramsGoods\\.goods_flag").val()=='0'){
			//alert('商品状态不能为空');
			//return;
		//}
		if(!num.exec($("#goods_price1").val())){
			alert('原价必须为数字');
			return;
		}
		if(!num.exec($("#goods_price2").val())){
			alert('当前价格必须为数字');
			return;
		}
		if(Number($("#goods_price1").val()) > Number($("#goods_price2").val())){
			$("#goods_flag").val(2);
		}else{
			$("#goods_flag").val(1);
		}
		if(!num2.exec($("#goods_count").val())){
			alert('库存数量必须为数字');
			return;
		}
		if($("#noticeContent").val()==''){
			alert('商品详情不能为空');
			return;
		}
		$("#goods_id").val(0);
		$("#info").attr('action','Admin_addGoods.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		editor.sync();
		if($("#goods_name").val()==''){
			alert('商品名称不能为空');
			return;
		}
		if($("#goods_type_id").val()=='0'){
			alert('商品类目不能为空');
			return;
		}
		if($("#goods_pic").val()==''){
			alert('商品图片不能为空');
			return;
		}
		//if($("#paramsGoods\\.goods_flag").val()=='0'){
			//alert('商品状态不能为空');
			//return;
		//}
		if(!num.exec($("#goods_price1").val())){
			alert('原价必须为数字');
			return;
		}
		if(!num.exec($("#goods_price2").val())){
			alert('当前价格必须为数字');
			return;
		}
		if(Number($("#goods_price1").val()) > Number($("#goods_price2").val())){
			$("#goods_flag").val(2);
		}else{
			$("#goods_flag").val(1);
		}
		if(!num2.exec($("#goods_count").val())){
			alert('库存数量必须为数字');
			return;
		}
		if($("#noticeContent").val()==''){
			alert('商品介绍不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveGoods.action').submit();
			 
	});
	 
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
<span id="MainTitle" style="color:white">商品管理&gt;&gt;${goods!=null && goods.goods_id!=0?'编辑':'添加'}商品信息</span>
</div>
<form id="info" name="info" action="Admin_addGoods.action" method="post">   
<input type="hidden" id="goods_id" name="goods_id" value="${goods.goods_id}" /> 
<input type="hidden" name="goods_pic" id="goods_pic" value='${goods.goods_pic}'/>
<input type="hidden" name="goods_flag" id="goods_flag" value='1'/>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">${goods!=null && goods.goods_id!=0?'编辑':'添加'}商品信息</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td width="150" align="right" style="padding-right:5px"> 商品名称：</td>
          <td width="*">
          	<input type="text" name="goods_name" id="goods_name" value="${goods.goods_name}" cssStyle="width:300px"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">商品类目：</td>
          <td>
          	 <select id="goods_type_id" name="goods_type_id" class="selectstyle" style="width:155px;">
		      	<option value="0">请选择</option>
		      	<c:forEach items="${goodsTypes}" var="goodsType">
		      	<option value="${goodsType.goods_type_id}" ${goodsType.goods_type_id==goods.goods_type_id?'selected':''}>${goodsType.goods_type_name}</option>
		      	</c:forEach>
		      </select>
          </td>
        </tr> 
        <tr>
		  <td align="right" style="padding-right:5px">商品图片：</td>
		  <td align="left" colspan="3">
		    <img id="userImg" src="../images/goodss/${goods.goods_pic}" width="70" height="80" style="border:0px;"/>
	        &nbsp;
	        <span id="op" style="display:none">
	        <img src="images/loading004.gif"  height="80" /></span> 
	      </td>
	    </tr>
	     <tr>
		  <td align="right" style="padding-right:5px"></td>
	      <td align="left" colspan="3">
	          <iframe name="uploadPage" src="uploadImg1.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	    </tr>
        <tr>
          <td align="right" style="padding-right:5px"> 商品原价：</td>
          <td >
          	<input type="text" name="goods_price1" id="goods_price1" value="${goods.goods_price1}"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">当前价格：</td>
          <td >
          	<input type="text" name="goods_price2" id="goods_price2" value="${goods.goods_price2}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"> 库存数量：</td>
          <td width="65%">
          	<input type="text" name="goods_count" id="goods_count" value="${goods.goods_count}"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">商品介绍：</td>
          <td >
          	<textarea style="width:500px;height:300px" name="goods_desc" id="noticeContent">${goods.goods_desc}</textarea>
          </td>
        </tr>
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<c:if test="${goods!=null && goods.goods_id!=0}">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</c:if>
          	<c:if test="${goods==null || goods.goods_id==0}">
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</c:if>
           
          &nbsp;<label style="color:red">${tip}</label> 
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
<script>        
KindEditor.ready(function(K) {
	window.editor = K.create('#noticeContent',{
		width : '95%',
		items:[
			'fullscreen','|','justifyleft', 'justifycenter', 'justifyright','justifyfull',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline','anchor', 'link', 'unlink', '|', 'image'
		],
		uploadJson : 'editor2/jsp/upload_json.jsp',
        fileManagerJson : 'editor2/jsp/file_manager_json.jsp',
        allowFileManager : true

	});
});
	   
</script>
</body>
</html>