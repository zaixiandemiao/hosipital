<%@page import="HLcard.healCardControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>

<%
    healCardControl hcc=new healCardControl(commondb);
    /* out.println(request.getParameter("idCard")+" "+request.getParameter("tel")+" "+request.getParameter("Sex")); */
    int flag=hcc.regisHealCard(request.getParameter("userName"), request.getParameter("idCard"), request.getParameter("tel"), request.getParameter("Sex"));
    if(flag>0){
         out.println("申请成功,您的卡号为："+flag);
    }else{
        out.println("申请失败");
    } 
        
   
 %>
