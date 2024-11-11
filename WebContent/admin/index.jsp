<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script language="javascript" type="text/javascript" src="js/index.js"></script>
<script language="javascript" type="text/javascript"> 
var admin = "${admin.user_id}";
if(admin==null||admin=='')
{
  window.location.href="login.jsp";
} 
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>

<body onload="setIframeWH()" style="margin:0px; padding:0px; background:#1078B0;">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td colspan="5" height="101">
		<iframe width="100%" style="margin:0px; padding:0px;" height="101" scrolling="no" src="top.jsp" frameborder="0"></iframe>
	</td>
  </tr>

  <tr>
    <td width="5"></td>
    <td width="190" height="*">
		<iframe name="LeftMenu" id="LeftMenu" width="100%" style="margin:0px; padding:0px;" height="100%" scrolling="no" src="left.jsp" frameborder="0"></iframe>
	</td>
    <td width="5"></td>
    <td style="background:#fff;">
		<iframe id="MainFrame" name="MainFrame" width="100%" style="margin:0px; padding:0px;" height="100%" scrolling="yes" src="main.jsp" frameborder="0"></iframe>
	</td>
    <td width="5"></td>
  </tr>

  <tr>
    <td colspan="5" height="5"></td>
  </tr>
</table>
</body>
</html>