<%@page import="doctor.docAssign"%>
<%@page import="doctor.Doctor"%>
<%@page import="doctor.docControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>
<%
    String name = request.getParameter("docName");
    docControl  dc = new docControl(commondb);
    Doctor  doctor =  dc.getDoctorByName(name);
    
    
     Collection<docAssign> arrayDocAssigns =  dc.getDocAssigns(doctor.getDocId());
    Iterator<docAssign>  iterator = arrayDocAssigns.iterator();
    boolean flag = true;
    while(iterator.hasNext()){
       docAssign  dAssign =iterator.next();
       if(flag){
           flag=false;
           out.println("<table><tr><td>日期</td> <td>班次  </td><td>花费 </td>  </tr>");
       }
       out.println("<tr><td>"+dAssign.getDate()+"</td><td>"+dAssign.getClassTh()+"</td><td>"+dAssign.getCost()+"</td> </tr>");
    }
    if(!flag){
       out.println("</table>");
    }else{
       out.println("该医师无病人 ");
    }
    
 %>
