<%@page import="doctor.docControl"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<jsp:useBean id="doctor" scope="page" class="doctor.Doctor"></jsp:useBean>
<jsp:setProperty property="*" name="doctor"/>
<%
    docControl dc = new docControl(commondb);
    if(dc.update(doctor)){
        out.println("�޸ĳɹ������<a href=\"mangerInfro.html\">����</a>");    
    }else{
        out.println("�޸�ʧ�ܣ�<a href=\"mangerInfro.html\">����</a>");
    }
%>