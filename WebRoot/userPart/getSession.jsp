<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
    String name = request.getSession().getAttribute("user").toString();
    if(name.equals("")){
        out.println("no");
    }else{
        out.println(name);
    }
 %>
