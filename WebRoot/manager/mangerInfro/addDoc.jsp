<%@page import="doctor.docControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>
<jsp:useBean id="doctor" scope="page" class="doctor.Doctor"></jsp:useBean>
<jsp:setProperty property="*" name="doctor"/>
<%
    docControl dc = new docControl(commondb);
    if(dc.insertDoctro(doctor)){
         out.println("添加成功，点击<a href=\"mangerInfro.html\">返回</a>");    
    }else{
        out.println("添加失败，<a href=\"mangerInfro.html\">返回</a>");
    }
    
%>
