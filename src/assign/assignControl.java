package assign;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import DBbean.dbBean;
import department.departmentBean;
import doctor.docAssign;
import doctor.docControl;

public class assignControl {

    private dbBean db=null;
	
	public assignControl() {
		// TODO Auto-generated constructor stub
	}

	public assignControl(dbBean d){
		db=d;
	}
	/**
	 * ͨ��id���ұ���
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public assignBean getAssignBeanById(int id) throws SQLException{
		Connection conn=null;
		PreparedStatement  stmt=null;
		ResultSet rs=null;
		
		try{
			conn=db.getConnection();
			stmt=conn.prepareStatement("select * from assignment where AssignId = ?"); 
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs.next()){
				assignBean mbBean=new assignBean(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getDate(8));
				return mbBean;
			}
			return null;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	/**
	 *  ���ر������а��ŵ�bean
	 * @return managerBean����
	 * @throws SQLException
	 */
	public Collection<assignBean> getAssignBeans() throws SQLException{
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		ArrayList<assignBean> assignBeans=new ArrayList<assignBean>();
		
		try{
			conn=db.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from assignment");
			while(rs.next()){
				assignBean mbBean=new assignBean(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getDate(8));
				assignBeans.add(mbBean);
			}
			return assignBeans;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	
	public Collection<assignBean> getAssignBeansByHCid(int  id)throws SQLException{
		Connection conn=null;
		PreparedStatement  stmt=null;
		ResultSet rs=null;
		ArrayList<assignBean> assignBeans=new ArrayList<assignBean>();
		try{
			conn=db.getConnection();
			stmt=conn.prepareStatement("select a.* from assignment a,healcardAssign b where b.healcardId = ? AND a.ClassTh = ? AND a.datetime = ? AND a.AssignId = b.assignId ");
			stmt.setInt(1, id);
			java.util.Date test =new java.util.Date();
			String classTh = "";
			if(test.getHours()<12){
				classTh="morning";
			}else{
				classTh="afternoon";
			}
			stmt.setString(2, classTh);
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String sDate = sdf.format(test);
		    java.sql.Date mydate = java.sql.Date.valueOf(sDate);
			stmt.setDate(3, mydate);
			rs=stmt.executeQuery();
			assignBean mbBean=null;
			while(rs.next()){
				mbBean=new assignBean(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getDate(8));
				assignBeans.add(mbBean);
			}
			return assignBeans;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * ͨ��ҽ��ID����Σ���õ�ǰ������а���
	 * @param user
	 * @return  ��user��Ӧ��mangerBean
	 * @throws SQLException
	 */
	public Collection<assignBean> getAssignBeansByDoc(int  id,String clth)throws SQLException{
		Connection conn=null;
		PreparedStatement  stmt=null;
		ResultSet rs=null;
		ArrayList<assignBean> assignBeans=new ArrayList<assignBean>();
		try{
			conn=db.getConnection();
			stmt=conn.prepareStatement("select * from assignment where DocId = ? AND ClassTh = ? AND datetime = ?");
			stmt.setInt(1, id);
			stmt.setString(2, clth);
			java.util.Date test =new java.util.Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String sDate = sdf.format(test);
		    java.sql.Date mydate = java.sql.Date.valueOf(sDate);
			stmt.setDate(3, mydate);
			rs=stmt.executeQuery();
			assignBean mbBean=null;
			while(rs.next()){
				mbBean=new assignBean(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getDate(8));
				assignBeans.add(mbBean);
			}
			return assignBeans;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * �޸Ĺ���ԱȨ��
	 * @param man
	 * @throws SQLException
	 */
	public boolean update(assignBean man)throws SQLException {
		if(!changeRegistorAssign(man))
		    return false;
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("update assignment set Treat = ?,ClassTh = ?,waitNum = ?,DepartName = ?,DocTitle = ?,DocId = ?,datetime = ? where AssignId = ?");
			pstmt.setDouble(1, man.getTreat());
			pstmt.setString(2, man.getClassTh());
			pstmt.setInt(3, man.getWaitNum());
			pstmt.setString(4, man.getDepartName());
			pstmt.setInt(5, man.getDocTitle());
			pstmt.setInt(6, man.getDocId());
			pstmt.setDate(7, man.getDateTime());
			pstmt.setInt(8, man.getAssignId());
			if(pstmt.executeUpdate()>0){
				docAssign  docAss = new docAssign(man.getClassTh(),man.getDateTime(),man.getDocId(),man.getAssignId(),man.getTreat());
				if(changeDocAssign(docAss))   //�޸���Ӧ��ҽ���Ű���б���
				      return true;
				else{
					return false;
				}
			}
			else{
			     return false;
		    }
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * ���޸İ��ű�֮ǰ����
	 * @param man
	 * @return
	 * @throws SQLException
	 */
	public boolean changeRegistorAssign(assignBean man) throws SQLException{
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("update registorassign set cost = ?,ClassTh = ?,DateTime = ? where assignId = ? ");
			pstmt.setDouble(1, man.getTreat());
			pstmt.setString(2, man.getClassTh());
			
			pstmt.setDate(3, man.getDateTime());
			pstmt.setInt(4, man.getAssignId());
			if(pstmt.executeUpdate()>0){
				      return true;
			}
			else{
			     return false;
		    }
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * ����ҽ�����Ű�����޸ĹҺ�֮ǰ����
	 * @param man
	 * @return
	 * @throws SQLException
	 */
	private boolean changeDocAssign(docAssign man) throws SQLException{
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("update docassign set ClassTh = ?,DateTime = ?,DocId = ?,cost = ?  where AssignId = ?");
			pstmt.setString(1, man.getClassTh());
			pstmt.setDate(2, man.getDate());
			pstmt.setInt(3, man.getDocId());
			pstmt.setDouble(4, man.getCost());
			pstmt.setInt(5, man.getAssignId());
			if(pstmt.executeUpdate()>0){
				 return true;
			}
			else{
			     return false;
		    }
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * ���һ������
	 * @param man
	 * @return
	 * @throws SQLException
	 */
	public int addAssign(assignBean man) throws SQLException{
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("insert into assignment VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, man.getAssignId());
			pstmt.setDouble(2, man.getTreat());
			pstmt.setString(3, man.getClassTh());
			pstmt.setInt(4, man.getWaitNum());
			pstmt.setString(5, man.getDepartName());
			pstmt.setInt(6, man.getDocTitle());
			pstmt.setInt(7, man.getDocId());
			pstmt.setDate(8, man.getDateTime());
			if(pstmt.executeUpdate()>0){
				  docControl dControl =new docControl(this.db);
				  docAssign dAssign =new docAssign(man.getClassTh(), man.getDocId(),man.getAssignId(),man.getTreat());
				  if(dControl.addDocAssign(dAssign))   //���Ӧ��ҽ�����в�����Ϣ
				        return man.getAssignId();
				  else {
					  return -1;
				}
			}
			else{
			     return -1;
		    }
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	public int addAssign(assignBean man,String datetime) throws SQLException{
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("insert into assignment VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, man.getAssignId());
			pstmt.setDouble(2, man.getTreat());
			pstmt.setString(3, man.getClassTh());
			pstmt.setInt(4, man.getWaitNum());
			pstmt.setString(5, man.getDepartName());
			pstmt.setInt(6, man.getDocTitle());
			pstmt.setInt(7, man.getDocId());
			pstmt.setDate(8, man.getDateTime());
			if(pstmt.executeUpdate()>0){
				  docControl dControl =new docControl(this.db);
				  java.sql.Date mydate = java.sql.Date.valueOf(datetime);
				  docAssign dAssign =new docAssign(man.getClassTh(),mydate,man.getDocId(),man.getAssignId(),man.getTreat());
				  if(dControl.addDocAssign(dAssign))   //���Ӧ��ҽ�����в�����Ϣ
				        return man.getAssignId();
				  else {
					  return -1;
				}
			}
			else{
			     return -1;
		    }
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * �������һ������,������Ҫ�Ĳ���
	 * @param treat  �շ�
	 * @param classTh  ���
	 * @param waitNum  �����
	 * @param departName  ����
	 * @param docTitle  ҽʦְ��
	 * @param docId  ҽʦID
	 * @return
	 * @throws SQLException 
	 */
	public int assignAman(double treat,String classTh,int waitNum,String departName,int docTitle,int docId) throws SQLException{
		int num = calculateTotal();
		num++;
		java.util.Date test =new java.util.Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String sDate = sdf.format(test);
	    java.sql.Date mydate = java.sql.Date.valueOf(sDate);
	    assignBean addedBean = new assignBean(num, treat, classTh, waitNum, departName, docTitle, docId, mydate);
	    return addAssign(addedBean);
	}
	/**
	 * �û�����ԤԼ���һ������,������Ҫ�Ĳ���
	 * @param treat  �շ�
	 * @param classTh  ���
	 * @param waitNum  �����
	 * @param departName  ����
	 * @param docTitle  ҽʦְ��
	 * @param docId  ҽʦID
	 * @param datetime �����ַ���
	 * @return
	 * @throws SQLException 
	 */
	public int assignAman(double treat,String classTh,int waitNum,String departName,int docTitle,int docId,String datetime) throws SQLException{
		int num = calculateTotal();
		num++;
	    java.sql.Date mydate = java.sql.Date.valueOf(datetime);
	    assignBean addedBean = new assignBean(num, treat, classTh, waitNum, departName, docTitle, docId, mydate);
	    return addAssign(addedBean,datetime);
	}
	/**
	 * �ж��û�һ����ԤԼ���Ƿ��������
	 * @param username
	 * @param datetime
	 * @return
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public boolean canAssignLevel0(String idcard,String datetime) throws SQLException, ParseException{
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date date =sdf.parse(datetime);
			  Calendar c = Calendar.getInstance();
			  c.setTime(date);
			  c.add(Calendar.MONTH, 0);
		     c.set(Calendar.DAY_OF_MONTH,1);//����Ϊ1��,��ǰ���ڼ�Ϊ���µ�һ�� 
		     String first = sdf.format(c.getTime());
		     c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		     String end = sdf.format(c.getTime());
			pstmt=conn.prepareStatement("select count(*) from userassign a,assignment b where a.IDcard = ? AND a.assignid = b.AssignId  AND b.datetime between ? and ?");
			pstmt.setString(1, idcard);
			pstmt.setString(2, first);
			pstmt.setString(3, end);
			rs = pstmt.executeQuery();
			if(rs.next()){
				int count = rs.getInt(1);
				  if(count>=3)   //���Ӧ��ҽ�����в�����Ϣ
				        return false;
				  else {
					  return true;
				}
			}
			else{
			     return true;
		    }
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * �жϸ��û���ָ������ ԤԼ�����Ƿ񳬹�����
	 * @param username
	 * @param datetime
	 * @return
	 * @throws SQLException
	 */
	public boolean canAssignLevel1(String idcard,String datetime) throws SQLException{
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("select count(*) from userassign a,assignment b where a.IDcard = ? AND a.assignid = b.AssignId AND b.datetime = ? ");
			pstmt.setString(1, idcard);
			pstmt.setString(2,datetime);
			rs = pstmt.executeQuery();
			if(rs.next()){
				int count = rs.getInt(1);
				  if(count>=2)   //���Ӧ��ҽ�����в�����Ϣ
				        return false;
				  else {
					  return true;
				}
			}
			else{
			     return true;
		    }
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * �ڵ���ֻ����ͬһ����ԤԼһ��
	 * @param username
	 * @param depart
	 * @param datetime
	 * @return
	 * @throws SQLException
	 */
	public boolean canAssignLevel2(String idcard,String depart,String datetime) throws SQLException{
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("select count(*) from userassign a,assignment b where a.IDcard = ? AND a.assignid = b.AssignId  AND b.datetime = ? AND b.DepartName = ?");
			pstmt.setString(1, idcard);
			pstmt.setString(2,datetime);
			pstmt.setString(3, depart);
			rs = pstmt.executeQuery();
			if(rs.next()){
				int count = rs.getInt(1);
				  if(count>=1)   //���Ӧ��ҽ�����в�����Ϣ
				        return false;
				  else {
					  return true;
				}
			}
			else{
			     return true;
		    }
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * ��õ�ǰ���а��ŵ��ܸ�������1�õ��¼ӱ���ID
	 * @return
	 * @throws SQLException
	 */
	public int calculateTotal() throws SQLException{
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		
		try{
			conn=db.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select MAX(AssignId) from assignment");
			if(rs.next()){
				int total = rs.getInt(1);
				return total;
			}
			return 0;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * ������йҺ���Ա�ķ��ú� ��Ϣ
	 * @param registor
	 * @param ClassTh
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public Collection<registorCounter> getRegistorCounters(String ClassTh,Date date) throws SQLException{
		Connection conn=null;
		PreparedStatement  stmt=null;
		ResultSet rs=null;
		ArrayList<registorCounter> arrayCounters = new ArrayList<registorCounter>();
		try{
			conn=db.getConnection();
			stmt=conn.prepareStatement("select a.registorName,sum(Treat) from registorassign a,assignment b where  b.ClassTh = ? AND b.datetime = ? AND a.assignId = b.AssignId group by a.registorName");
			stmt.setString(1, ClassTh);
			stmt.setDate(2, date);
			rs=stmt.executeQuery();
			registorCounter  rCounter =null;
			while(rs.next()){
				rCounter = new registorCounter(rs.getString(1),rs.getDouble(2));
				arrayCounters.add(rCounter);
			}
			return arrayCounters;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
		
	}
	
	public Collection<DocCounter> getDocCounters(String ClassTh,Date date) throws SQLException{
		Connection conn=null;
		PreparedStatement  stmt=null;
		ResultSet rs=null;
		ArrayList<DocCounter> arrayCounters = new ArrayList<DocCounter>();
		try{
			conn=db.getConnection();
			stmt=conn.prepareStatement("select b.DepartName,b.DocTitle,a.DocName,sum(Treat) from doctor a,assignment b where a.DocId =b.DocId AND b.ClassTh = ? AND b.datetime = ? group by a.DocName");
			stmt.setString(1, ClassTh);
			stmt.setDate(2, date);
			rs=stmt.executeQuery();
			DocCounter  rCounter =null;
			while(rs.next()){
				rCounter = new DocCounter(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getDouble(4));
				arrayCounters.add(rCounter);
			}
			return arrayCounters;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
		
	}
	
}
