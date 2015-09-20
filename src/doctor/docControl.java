package doctor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import DBbean.dbBean;
import department.departmentBean;

public class docControl implements Serializable {
	 private dbBean db=null;
		
		public docControl() {
			// TODO Auto-generated constructor stub
		}

		public docControl(dbBean d){
			db=d;
		}
		/**
		 * ͨ�����ֻ��Doctor
		 * @param name
		 * @return
		 * @throws SQLException
		 */
		public Doctor getDoctorByName(String name) throws SQLException{
			Connection conn=null;
			PreparedStatement  stmt=null;
			ResultSet rs=null;
			try{
				conn=db.getConnection();
				stmt=conn.prepareStatement("select * from doctor where DocName = ?");
				stmt.setString(1,name);
				rs=stmt.executeQuery();
				Doctor mbBean=null;
				if(rs.next()){
				   mbBean=new Doctor(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6));
			}
				return mbBean;
			}finally{
				db.closeResultSet(rs);
				db.closeStatement(stmt);
				db.closeConnection(conn);
			}
		}
		/**
		 *  ���ر�������ҽʦ��bean
		 * @return managerBean����
		 * @throws SQLException
		 */
		public Collection<Doctor> getDoctors() throws SQLException{
			Connection conn=null;
			Statement  stmt=null;
			ResultSet rs=null;
			ArrayList<Doctor> doctors=new ArrayList<Doctor>();
			
			try{
				conn=db.getConnection();
				stmt=conn.createStatement();
				rs=stmt.executeQuery("select * from doctor");
				while(rs.next()){
					Doctor mbBean=new Doctor(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6));
					doctors.add(mbBean);
				}
				return doctors;
			}finally{
				db.closeResultSet(rs);
				db.closeStatement(stmt);
				db.closeConnection(conn);
			}
		}
		/**
		 * ����ʱ���Σ�����ҽʦID
		 * ��ø�ҽʦĿǰ�ð�εĵȺ�����
		 * @param docId
		 * @return
		 * @throws SQLException
		 */
		public int waitNum(int docId,String claTh) throws SQLException{
			Connection conn=null;
			PreparedStatement  stmt=null;
			ResultSet rs=null;
			try{
				conn=db.getConnection();
				stmt=conn.prepareStatement("select count(*) from docassign where DocId = ? AND ClassTh = ? AND DateTime = ?");
				stmt.setInt(1, docId);
				stmt.setString(2, claTh);
				java.util.Date test =new java.util.Date();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String sDate = sdf.format(test);
			    java.sql.Date mydate = java.sql.Date.valueOf(sDate);
				stmt.setDate(3, mydate);
				rs=stmt.executeQuery();
				int num = 0;
				if(rs.next()){
				     num=rs.getInt(1);
				}
				return num;
			}finally{
				db.closeResultSet(rs);
				db.closeStatement(stmt);
				db.closeConnection(conn);
			}
		}
		
		/**
		 * ͨ����������ְ�� ���ҽʦ
		 * @param depart
		 * @param docTitle
		 * @return
		 * @throws SQLException
		 */
		public Collection<Doctor> getDoctorsByDepart_DocTitle(String depart,int docTitle) throws SQLException{
			Connection conn=null;
			PreparedStatement  stmt=null;
			ResultSet rs=null;
			ArrayList<Doctor> doctors=new ArrayList<Doctor>();
			
			try{
				conn=db.getConnection();
				stmt=conn.prepareStatement("select * from doctor where DepartName = ? AND DocTitle = ?");
				stmt.setString(1,depart);
				stmt.setInt(2, docTitle);
				rs=stmt.executeQuery();
				while(rs.next()){
					Doctor doctor = new Doctor(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getString(6));
					doctors.add(doctor);
				}
				return doctors;
			}finally{
				db.closeResultSet(rs);
				db.closeStatement(stmt);
				db.closeConnection(conn);
			}
		}
		/**
		 * ͨ������������ҽʦ
		 * @param departmentName
		 * @return
		 * @throws SQLException
		 */
		public Collection<Doctor> getDoctorsByDepart(String departmentName) throws SQLException{
			Connection conn=null;
			Statement  stmt=null;
			ResultSet rs=null;
			ArrayList<Doctor> doctors=new ArrayList<Doctor>();
			
			try{
				conn=db.getConnection();
				stmt=conn.createStatement();
				rs=stmt.executeQuery("select * from doctor where `doctor`.DepartName = \""+departmentName+"\"");
				while(rs.next()){
					Doctor mbBean=new Doctor(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6));
					doctors.add(mbBean);
				}
				return doctors;
			}finally{
				db.closeResultSet(rs);
				db.closeStatement(stmt);
				db.closeConnection(conn);
			}
		}
		
		/**
		 * ͨ�������������в��ң�����mangerBean
		 * @param user
		 * @return  ��user��Ӧ��mangerBean
		 * @throws SQLException
		 */
		public Doctor getDoctor(int id)throws SQLException{
			Connection conn=null;
			PreparedStatement  stmt=null;
			ResultSet rs=null;
			try{
				conn=db.getConnection();
				stmt=conn.prepareStatement("select * from doctor where DocId = ?");
				stmt.setInt(1, id);
				rs=stmt.executeQuery();
				Doctor mbBean=null;
				if(rs.next()){
				   mbBean=new Doctor(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6));
			}
				return mbBean;
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
		public boolean update(Doctor man)throws SQLException {
			Connection conn=null;
			PreparedStatement  pstmt=null;
			ResultSet rs=null;
			try{
				conn=db.getConnection();
				pstmt=conn.prepareStatement("update doctor set DocName = ?,MorLimit = ?,AftLimit = ?,DocTitle = ?,DepartName = ? where DocId = ?");
				pstmt.setString(1, man.getDocName());
				pstmt.setInt(2, man.getMorLimit());
				pstmt.setInt(3, man.getAftLimit());
				pstmt.setInt(4, man.getDocTitle());
				pstmt.setString(5, man.getDepartName());
				pstmt.setInt(6, man.getDocId());
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
		 * ����һ��ҽ��
		 * @param man
		 * @return
		 * @throws SQLException
		 */
		public boolean insertDoctro(Doctor man)throws SQLException {
			Connection conn=null;
			PreparedStatement  pstmt=null;
			ResultSet rs=null;
			try{
				conn=db.getConnection();
				pstmt=conn.prepareStatement("insert into doctor values(?,?,?,?,?,?)");
				int num = getNewDocId();  
				pstmt.setInt(1, num);
				pstmt.setString(2, man.getDocName());
				pstmt.setInt(3, man.getMorLimit());
				pstmt.setInt(4, man.getAftLimit());
				pstmt.setInt(5, man.getDocTitle());
				pstmt.setString(6, man.getDepartName());
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
		 * ���һ���µ�docID
		 * @return
		 * @throws SQLException
		 */
		public int getNewDocId() throws SQLException{
			Connection conn=null;
			Statement  pstmt=null;
			ResultSet rs=null;
			try{
				conn=db.getConnection();
				pstmt=conn.createStatement();
				rs = pstmt.executeQuery("select MAX(DocId) from doctor ");
				if(rs.next()){
					  int num = rs.getInt(1);
					  num++;
					  return num;
				}
				else{
				    	  throw new SQLException();
				      }
			}finally{
				db.closeResultSet(rs);
				db.closeStatement(pstmt);
				db.closeConnection(conn);
			}
		}
		/**
		 * ͨ��ҽ���������һ��ҽ���Ű��
		 * @param id
		 * @return
		 * @throws SQLException
		 */
		public docWork getDocWork(String name)throws SQLException{
			Connection conn=null;
			PreparedStatement  stmt=null;
			ResultSet rs=null;
			try{
				conn=db.getConnection();
				stmt=conn.prepareStatement("select * from docwork where DocName = ?");
				stmt.setString(1,name);
				rs=stmt.executeQuery();
				docWork mbBean=null;
				if(rs.next()){
				   mbBean=new docWork(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
					
			}
				return mbBean;
			}finally{
				db.closeResultSet(rs);
				db.closeStatement(stmt);
				db.closeConnection(conn);
			}
		}
		
		/**
		 * �������ҽ���İ��ű�
		 * @return
		 * @throws SQLException
		 */
		public Collection<docWork> getDocWorks() throws SQLException{
			Connection conn=null;
			Statement  stmt=null;
			ResultSet rs=null;
			ArrayList<docWork> doctors=new ArrayList<docWork>();
			
			try{
				conn=db.getConnection();
				stmt=conn.createStatement();
				rs=stmt.executeQuery("select * from docwork");
				while(rs.next()){
					docWork mbBean=new docWork(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
					doctors.add(mbBean);
				}
				return doctors;
			}finally{
				db.closeResultSet(rs);
				db.closeStatement(stmt);
				db.closeConnection(conn);
			}
		}
		
		public boolean changeDocWork(docWork man)throws SQLException {
			Connection conn=null;
			PreparedStatement  pstmt=null;
			ResultSet rs=null;
			try{
				conn=db.getConnection();
				pstmt=conn.prepareStatement("update docwork set DocName = ?,Mon = ?,Tues = ?,Wed = ?,Thurs = ?,Fri = ? where DocId = ?");
				pstmt.setString(1, man.getDocName());
				pstmt.setString(2, man.getMon());
				pstmt.setString(3, man.getTues());
				pstmt.setString(4, man.getWed());
				pstmt.setString(5, man.getThurs());
				pstmt.setString(6, man.getFri());
				pstmt.setInt(7, man.getId());
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
		 * ��ҽ���ĹҺ��б��в������,�˷�����������assign���в������֮�����
		 * @param da
		 * @return
		 * @throws SQLException
		 */
		public boolean addDocAssign(docAssign da) throws SQLException{
			Connection conn=null;
			PreparedStatement  pstmt=null;
			ResultSet rs=null;
			try{
				conn=db.getConnection();
				pstmt=conn.prepareStatement("insert into docassign(ClassTh,DateTime,DocId,AssignId,cost) VALUES(?,?,?,?,?)");
				pstmt.setString(1, da.getClassTh());
				pstmt.setDate(2, da.getDate());
				pstmt.setInt(3, da.getDocId());
				pstmt.setInt(4, da.getAssignId());
				pstmt.setDouble(5, da.getCost());
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
		 * ����docId��ѯ���յ���� ��ҽ�����Ű��
		 * @param docId
		 * @return
		 * @throws SQLException
		 */
		public Collection<docAssign> getDocAssigns(int docId) throws SQLException{
			Connection conn=null;
			PreparedStatement  stmt=null;
			ResultSet rs=null;
			ArrayList<docAssign> arrayList = new ArrayList<docAssign>();
			try{
				conn=db.getConnection();
				stmt=conn.prepareStatement("select * from docassign where DocId = ? AND ClassTh = ? AND DateTime = ?");
				stmt.setInt(1, docId);
				java.util.Date test =new java.util.Date();
				String classTh="";
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
				docAssign mbBean=null;
				while(rs.next()){
				   mbBean=new docAssign(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getInt(4),rs.getDouble(6));
				   arrayList.add(mbBean);
			  }
				return arrayList;
			}finally{
				db.closeResultSet(rs);
				db.closeStatement(stmt);
				db.closeConnection(conn);
			}
		}
		/**
		 * ����ҽ��ID������ ��  ���  ��ѯҽ���Ű��
		 * @param docId
		 * @param datetime
		 * @param ClassTh
		 * @return
		 * @throws SQLException
		 */
		public Collection<docAssign> getDocAssigns(int docId,String datetime,String ClassTh) throws SQLException{
			Connection conn=null;
			PreparedStatement  stmt=null;
			ResultSet rs=null;
			ArrayList<docAssign> arrayList = new ArrayList<docAssign>();
			try{
				conn=db.getConnection();
				stmt=conn.prepareStatement("select * from docassign where DocId = ? AND ClassTh = ? AND DateTime = ?");
				stmt.setInt(1, docId);
				java.util.Date test =new java.util.Date();
				
				stmt.setString(2, ClassTh);
			    java.sql.Date mydate = java.sql.Date.valueOf(datetime);
			    stmt.setDate(3, mydate);
				rs=stmt.executeQuery();
				docAssign mbBean=null;
				while(rs.next()){
				   mbBean=new docAssign(rs.getString(1),rs.getDate(2),rs.getInt(3),rs.getInt(4),rs.getDouble(6));
				   arrayList.add(mbBean);
			  }
				return arrayList;
			}finally{
				db.closeResultSet(rs);
				db.closeStatement(stmt);
				db.closeConnection(conn);
			}
		}

}
