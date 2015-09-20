<%@page import="doctor.docControl"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<jsp:useBean id="doctor" scope="page" class="doctor.Doctor"></jsp:useBean>
<jsp:setProperty property="*" name="doctor"/>
<%
    docControl dc = new docControl(commondb);
    if(dc.update(doctor)){
        out.println("修改成功，点击<a href=\"mangerInfro.html\">返回</a>");    
    }else{
        out.println("修改失败，<a href=\"mangerInfro.html\">返回</a>");
    }
%>