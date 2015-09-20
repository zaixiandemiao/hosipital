<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="doctor.Doctor"%>
<%@page import="doctor.docWork"%>
<%@page import="doctor.docControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>

<%     
      docControl dc = new docControl(commondb); 
      Date mydate = new Date();
      String timestate="";
      if(mydate.getHours()<12){
         timestate = "morning";
      }else{
         timestate = "afternoon";
      }
     int inputTitle =Integer.valueOf(request.getParameter("title"));   
     Collection<Doctor> docOnWork = dc.getDoctorsByDepart_DocTitle(request.getParameter("department"),inputTitle);  
     boolean flag = true;
     Iterator<Doctor> it= docOnWork.iterator();
      while(it.hasNext()){
          Doctor tmpBean = it.next();
          int docId =tmpBean.getDocId();
		  String name =tmpBean.getDocName();
		  int morLimit =tmpBean.getMorLimit();
		  int aftLimit =tmpBean.getAftLimit();
		  int tmpDocTitle = tmpBean.getDocTitle();
		  String department =tmpBean.getDepartName();
		  docWork dw =dc.getDocWork(name);
		  if(dw.onWork(mydate.getDay(),timestate)){   //如果该医师在职
			  int waitNum = dc.waitNum(docId,timestate);
			  if(timestate.equals("morning")){
			      if(waitNum<morLimit){
			          if(flag){
			              flag=false;
			              out.println("<h2 style=\"display:block;\">在职医师</h2><table><tr><td>医师姓名 </td><td>科室 </td><td>职称 </td><td>候诊号 </td></tr>");
			          }
			          out.println("<tr><td>"+name+"</td><td>"+department+"</td><td>"+tmpDocTitle+"</td><td>"+(waitNum+1)+"</td></tr>");
			      }
			  }else{
			       if(waitNum<aftLimit){
			       if(flag){
			              flag=false;
			              out.println("<h2 style=\"display:block;\">在职医师</h2><table><tr><td>医师姓名 </td><td>科室 </td><td>职称 </td><td>候诊号 </td></tr>");
			          }
			           out.println("<tr><td>"+name+"</td><td>"+department+"</td><td>"+tmpDocTitle+"</td><td>"+(waitNum+1)+"</td></tr>");
			       }
			  }  
			}
      }
      if(!flag){
          out.println("</table>");
      }else{
          out.println("无在职医师 ");
      }
 %>