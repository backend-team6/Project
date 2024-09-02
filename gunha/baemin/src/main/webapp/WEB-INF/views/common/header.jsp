<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  String loginId = (String)session.getAttribute("login");
  if(loginId!=null){
%>
  <%=loginId%>님 환영합니다.<br/>
  <a href="<%=request.getContextPath()%>/user/logout">[로그아웃]</a>
<%
  }else{
%>
<a href="<%=request.getContextPath()%>/user/login">[로그인 하러 가기]</a>
<a href="<%=request.getContextPath()%>/user/register">[회원가입]</a>
<%}%>
<hr>

