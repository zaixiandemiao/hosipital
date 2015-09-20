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
    String datetime = request.getParameter("datetime");
    String ClassTh = request.getParameter("timestate");
    docControl  dc = new docControl(commondb);
    Doctor  doctor =  dc.getDoctorByName(name);
    
    
     Collection<docAssign> arrayDocAssigns =  dc.getDocAssigns(doctor.getDocId(),datetime,ClassTh);
    Iterator<docAssign>  iterator = arrayDocAssigns.iterator();
    boolean flag = true;
    while(iterator.hasNext()){
       docAssign  dAssign =iterator.next();
       if(flag){
           flag=false;
           out.println("<h2>"+name+"医生 的排班表 </h2><table><tr><td>日期</td> <td>班次  </td><td>花费 </td>  </tr>");
       }
       out.println("<tr><td>"+dAssign.getDate()+"</td><td>"+dAssign.getClassTh()+"</td><td>"+dAssign.getCost()+"</td> </tr>");
    }
    if(!flag){
       out.println("</table>");
    }else{
       out.println("该医师无病人 ");
    }
    
 %>
