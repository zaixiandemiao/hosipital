<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<%@ page import="userPart.userControl" %>
<% request.setCharacterEncoding("GB2312"); %>
<jsp:useBean id="user" scope="page" class="userPart.User"></jsp:useBean>
<jsp:setProperty name="user" property="*"></jsp:setProperty>
<%
   userControl uc=new userControl(commondb);
   if(uc.addUser(user))
      out.println("注册成功，点击<a href=\"../home.html\">进入</a>主界面");
   else{
      out.println("注册失败，请<a href=\"signOut.html\">重新注册</a>");
   }
 %>
