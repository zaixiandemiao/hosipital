<%@page import="doctor.docControl"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<jsp:useBean id="docWork" scope="page" class="doctor.docWork"></jsp:useBean>
<jsp:setProperty property="*" name="docWork"/>
<%
    docControl dc = new docControl(commondb);
    if(dc.changeDocWork(docWork)){
        out.println("�޸ĳɹ������<a href=\"mangerInfro.html\">����</a>");    
    }else{
        out.println("�޸�ʧ�ܣ�<a href=\"mangerInfro.html\">����</a>");
    }
%>