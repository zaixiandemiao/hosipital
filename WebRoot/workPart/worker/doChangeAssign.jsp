<%@page import="doctor.docWork"%>
<%@page import="doctor.Doctor"%>
<%@page import="doctor.docControl"%>
<%@page import="assign.assignControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>
<%
    int hcid,title;
    String depart;
    hcid=Integer.valueOf(request.getParameter("hcid"));
    title=Integer.valueOf(request.getParameter("title"));
    depart=request.getParameter("department");
    
    //out.println(hcid+" "+depart+" "+title);
    assignControl  assControl = new assignControl(commondb);
    Collection<assignBean> assignBeans =  assControl.getAssignBeansByHCid(hcid);
    if(assignBeans.size()<=0){
      out.println("当前无挂号");
    }else{
         out.println("挂号信息");
    }
    boolean flag = true;
    Iterator<assignBean> it=assignBeans.iterator();
    while(it.hasNext()){
        assignBean tmpAssignBean =it.next();
        if(flag){
            out.println("<table><tr><td>科室名 </td><td>医师职称 </td><td>候诊号 </td><td>花费 </td></tr>");
        }
        out.println("<tr><td>"+tmpAssignBean.getDepartName()+"</td><td>"+tmpAssignBean.getDocTitle()+"</td><td>"+tmpAssignBean.getWaitNum()+"</td><td>"+tmpAssignBean.getTreat()+"</td><td></tr>");
   
    }
    if(!flag){
        out.println("</table><br>");
    }
    /* out.println("在职医师信息 ");
    docControl dc = new docControl(commondb); 
      Date mydate = new Date();
      String timestate="";
      if( mydate.getHours()<12){
         timestate = "morning";
      }else{
         timestate = "afternoon";
      }
     int inputTitle =Integer.valueOf(request.getParameter("title"));   
     Collection<Doctor> docOnWork = dc.getDoctorsByDepart_DocTitle(request.getParameter("department"),inputTitle);  
     boolean flag1 = true;
     Iterator<Doctor> iterator= docOnWork.iterator();
      while(iterator.hasNext()){
          Doctor tmpBean = iterator.next();
          int docId =tmpBean.getDocId();
		  String name =tmpBean.getDocName();
		  int morLimit =tmpBean.getMorLimit();
		  int aftLimit =tmpBean.getAftLimit();
		  int tmpDocTitle = tmpBean.getDocTitle();
		  String department =tmpBean.getDepartName();
		  docWork dw =dc.getDocWork(name);
		  if(dw.onWork(mydate.getDay()-5,timestate)){   //如果该医师在职
			  int waitNum = dc.waitNum(docId,timestate);
			  if(timestate.equals("morning")){
			      if(waitNum<morLimit){
			          if(flag1){
			              flag1=false;
			              out.println("<table><tr><td>医师姓名 </td><td>科室 </td><td>职称 </td><td>候诊号 </td></tr>");
			          }
			          out.println("<tr><td>"+name+"</td><td>"+department+"</td><td>"+tmpDocTitle+"</td><td>"+(waitNum+1)+"</td></tr>");
			      }
			  }else{
			       if(waitNum<aftLimit){
			       if(flag1){
			              flag1=false;
			              out.println("<table><tr><td>医师姓名 </td><td>科室 </td><td>职称 </td><td>候诊号 </td></tr>");
			          }
			           out.println("<tr><td>"+name+"</td><td>"+department+"</td><td>"+tmpDocTitle+"</td><td>"+(waitNum+1)+"</td></tr>");
			       }
			  }  
			}
      }
      if(!flag1){
          out.println("</table>");
      }else{
          out.println("无在职医师 ");
      }
 */
 %>
