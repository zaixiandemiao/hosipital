<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<%@ page import="userPart.userControl" %>
<% request.setCharacterEncoding("GB2312"); %>
<jsp:useBean id="user" scope="page" class="userPart.User"></jsp:useBean>
<jsp:setProperty name="user" property="*"></jsp:setProperty>
<%
   userControl uc=new userControl(commondb);
   if(uc.addUser(user))
      out.println("ע��ɹ������<a href=\"../home.html\">����</a>������");
   else{
      out.println("ע��ʧ�ܣ���<a href=\"signOut.html\">����ע��</a>");
   }
 %>
