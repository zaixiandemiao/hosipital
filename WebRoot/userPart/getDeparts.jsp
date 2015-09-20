<%@page import="department.departmentBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>
<%@ page import="department.departControl" %>
<% request.setCharacterEncoding("GB2312"); %>
<%
   departControl dc=new departControl();
   Collection<departmentBean> resultCollection = dc.getDepartmentBeans();
   Iterator it=resultCollection.iterator();
   while(it.hasNext()){
       
   
   }
    
 %>
