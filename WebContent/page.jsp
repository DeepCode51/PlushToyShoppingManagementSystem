<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
		 <div id="page">
			共 ${paperUtil.totalCount} 条，第 ${paperUtil.pageCount==0?0:paperUtil.pageNo}/${paperUtil.pageCount} 页
			<c:if test="${paperUtil.pageNo > 1 }">
				&nbsp;　<a href="javascript:ChangePage('1');" class="alinkstyle">首页</a>&nbsp;&nbsp;<a href="javascript:ChangePage('${paperUtil.pageNo-1}');" class="alinkstyle">前页</a>
			</c:if>
			<c:if test="${paperUtil.pageNo <= 1 }">
				&nbsp;　首页
			    &nbsp;前页
			</c:if>
			<c:if test="${paperUtil.pageNo < paperUtil.pageCount}">
		        <a href="javascript:ChangePage('${paperUtil.pageNo+1}');" class="alinkstyle">后页</a>&nbsp;&nbsp;<a href="javascript:ChangePage('${paperUtil.pageCount}');" class="alinkstyle">末页</a>
		    </c:if> 
		    <c:if test="${paperUtil.pageNo >= paperUtil.pageCount}">
				&nbsp;后页
			    &nbsp;末页
			</c:if>
	        &nbsp;　<input type="text" name="goPage" id="goPage" class="inputpagestyle" />
	        &nbsp;<input type="button" value="GO" onclick="GoPage();" class="btnstyle"/>
		 </div>
	 