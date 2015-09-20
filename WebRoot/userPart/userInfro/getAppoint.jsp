<%@page import="java.text.SimpleDateFormat"%>
<%@page import="userPart.userControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>
<%
     userControl uc = new userControl(commondb);
     java.util.Date test =new java.util.Date();
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 String sDate = sdf.format(test);
	 
	 Collection<userAppoint> userAppoints =  uc.getUserAppoints(session.getAttribute("user").toString(),sDate);
     Iterator<userAppoint> iterator = userAppoints.iterator();
     
     while(iterator.hasNext()){
          userAppoint  ua = iterator.next();
          out.println("<li><span>"+ua.getDatetime()+"</span><span>"+ua.getClassTh()+"</span><span>"+ua.getDepart()+"</span><span>"+ua.getDocTitle()+"</span>"
          +"<span>"+ua.getWaitNum()+"</span><span>"+ua.getMoney()+"</span></li>");
     }
   
 %>
