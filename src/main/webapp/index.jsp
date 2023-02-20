<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

	response.sendRedirect("login.do");

%>

<!-- 뷰에서 .do라고 쓴다!!
1. 아주 작은 데이터라도 보내려고 하면 컨트롤러를 거쳐야 한다.
2. 필터 적용이 안 되기 때문에 .do라고 써야한다.
 -->