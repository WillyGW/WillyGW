<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<%@ include file="include/header.jspf" %>
</head>
<body style="padding: 15px">
	<table style= "width: 100%; border: 0px;">
		<tr>
			<td valign="top">
			<%@ include file="exam_form.jspf" %>			
			</td>			
			<td valign="top">
			<%@ include file="exam_list.jspf" %>
			</td>
		</tr>
	</table>
	

</body>
<%@ include file="include/footer.jspf" %>
</html>