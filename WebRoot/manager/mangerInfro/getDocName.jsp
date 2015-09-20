<%@page import="doctor.Doctor"%>
<%@page import="doctor.docControl"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="department.departmentBean"%>
<%@page import="department.departControl"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<%
  docControl dc = new docControl(commondb);
  Collection<Doctor> arrayList=dc.getDoctorsByDepart(request.getParameter("department"));
  out.print("...#");  
  Iterator<Doctor> iterator = arrayList.iterator();
  Set departNames =new HashSet();
  while(iterator.hasNext()){
      Doctor tmp = iterator.next();
      departNames.add(tmp.getDocName()); 
  }
  Iterator it = departNames.iterator();
  while(it.hasNext()){
      out.print(it.next().toString()+"#");
  }
%>
