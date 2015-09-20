<%@page import="java.text.DateFormat"%>
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
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String initdate = request.getParameter("datetime");
      Date mydate = sdf.parse(initdate); 

      String timestate="";

     timestate =request.getParameter("timestate");
      
     int inputTitle =Integer.valueOf(request.getParameter("title"));   
     //out.println(inputTitle+"#"+timestate+"#"+(mydate.getDay())); 
     
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
			              out.println("<h2 style=\"display:block;\">在职医师</h2><table><tr><td>医师姓名 </td><td>科室 </td><td>职称 </td><td>候诊号 </td><td>日期 </td><td>班次 </td></tr>");
			          }
			          out.println("<tr><td>"+name+"</td><td>"+department+"</td><td>"+tmpDocTitle+"</td><td>"+(waitNum+1)+"</td><td>"+initdate+"</td><td>"+timestate+"</td></tr>");
			      }
			  }else{
			       if(waitNum<aftLimit){
			       if(flag){
			              flag=false;
			              out.println("<h2 style=\"display:block;\">在职医师</h2><table><tr><td>医师姓名 </td><td>科室 </td><td>职称 </td><td>候诊号 </td><td>日期 </td><td>班次 </td></tr>");
			          }
			           out.println("<tr><td>"+name+"</td><td>"+department+"</td><td>"+tmpDocTitle+"</td><td>"+(waitNum+1)+"</td><td>"+initdate+"</td><td>"+timestate+"</td></tr>");
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
