<%@page import="userPart.userControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>
<%
   String username =  request.getSession().getAttribute("user").toString();
   userControl  uc = new  userControl(commondb);
   User user =  uc.getUserBean(username);
   String realName = user.getRealName();
   String idCard = user.getIdCard();
   String tel = user.getTel();
   out.println(realName+"#"+idCard+"#"+tel); 
 %>
