<%@page import="department.departControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>
<jsp:useBean id="department" scope="page" class="department.departmentBean"></jsp:useBean>
<jsp:setProperty property="*" name="department"/>
<%
   departControl  dc = new departControl(commondb);
   department.setName(request.getParameter("Name"));
   if(dc.insertDepart(department)){
       out.println("添加成功，点击<a href=\"mangerInfro.html\">返回</a>");    
    }else{
        out.println("添加失败，<a href=\"mangerInfro.html\">返回</a>");
    }

 %>
