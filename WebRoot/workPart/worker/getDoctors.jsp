<%@page import="doctor.Doctor"%>
<%@page import="doctor.docControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>
<%
    String depart = request.getParameter("department");
    int title = Integer.valueOf(request.getParameter("title"));
    
    //out.println(depart+"#"+title);
    
    docControl  dc = new docControl(commondb);
    Collection<Doctor> arrayDoctors = dc.getDoctorsByDepart_DocTitle(depart, title);
    Iterator<Doctor> iterator = arrayDoctors.iterator();
    while(iterator.hasNext()){
        Doctor tmpDoctor =iterator.next();
        out.println(tmpDoctor.getDocName()+"#");
    }

 %>
