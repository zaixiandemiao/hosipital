<%@page import="doctor.Doctor"%>
<%@page import="doctor.docControl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ include file="/common.jsp" %>
<% request.setCharacterEncoding("GB2312"); %>
<%
  docControl dc = new docControl(commondb);
  Collection<Doctor> arrayList=dc.getDoctors();
  if(!arrayList.isEmpty()){
       out.println("<h1>ҽ����</h1>");
       out.println("<span>ҽ������</span><span>������������</span><span>������������</span><span>ְ�� </span><span>���� </span>");
  }else{
       out.println("<h1>��Ϊ�� </h1>");
  }  
  Iterator<Doctor> iterator = arrayList.iterator();
  while(iterator.hasNext()){
      Doctor tmpBean =iterator.next();
      out.println("<form method=\"post\" action=\"changeDoc.jsp\">"+"<input type=\"hidden\" name=\"docId\" value=\""+tmpBean.getDocId()+"\"/><input type=\"text\" name=\"docName\" value=\""+tmpBean.getDocName()+"\""+"/><input type=\"text\" name=\"morLimit"+"\"value=\""+tmpBean.getMorLimit()+"\"/>"+"<input type=\"text\" name=\"aftLimit\" value=\""+tmpBean.getAftLimit()+"\"/>"
        +"<input type=\"text\" name=\"docTitle\" value=\""+tmpBean.getDocTitle()+"\"/>"+"<input type=\"text\" name=\"departName\" value=\""+tmpBean.getDepartName()+"\"/>"+"<input type=\"submit\" value=\"ȷ���޸�\" /></form>");   
  }
  out.println("���һ��ҽ��  <br>");
  out.println("<form method=\"post\" action=\"addDoc.jsp\">"+"<input type=\"text\" name=\"docName\" value=\"\""+" /><input type=\"text\" name=\"morLimit"+"\"value=\"\"/>"+"<input type=\"text\" name=\"aftLimit\" value=\"\" />"
        +"<input type=\"text\" name=\"docTitle\" value=\"\"/>"+"<input type=\"text\" name=\"departName\" value=\"\"/>"+" <input type=\"submit\" value=\"ȷ����� \" /></form>");   
  
%>