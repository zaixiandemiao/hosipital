<%@page import="department.departmentBean"%>
<%@page import="department.departControl"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<jsp:useBean id="department" scope="page" class="department.departmentBean"></jsp:useBean>
<jsp:setProperty property="*" name="department"/>
<%
    departControl dc = new departControl(commondb);
    department.setName(request.getParameter("Name"));
    if(dc.update(department)){
        out.println("�޸ĳɹ������<a href=\"mangerInfro.html\">����</a>");    
    }else{
        out.println("�޸�ʧ�ܣ�<a href=\"mangerInfro.html\">����</a>");
    }
%>