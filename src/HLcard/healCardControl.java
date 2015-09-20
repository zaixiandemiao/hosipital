package HLcard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import assign.assignBean;

import DBbean.dbBean;

public class healCardControl {
    private dbBean db=null;
	
	public healCardControl() {
		// TODO Auto-generated constructor stub
	}

	public healCardControl(dbBean d){
		db=d;
	}
	/**
	 *  返回表中所有管理员的bean
	 * @return managerBean集合
	 * @throws SQLException
	 */
	public Collection<healCard> getHealCards() throws SQLException{
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		ArrayList<healCard> registorBeans=new ArrayList<healCard>();
		
		try{
			conn=db.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from healcard");
			while(rs.next()){
				healCard mbBean=new healCard(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				registorBeans.add(mbBean);
			}
			return registorBeans;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
	
	/**
	 * 通过主键，到表中查找，返回mangerBean
	 * @param user
	 * @return  该user对应的mangerBean
	 * @throws SQLException
	 */
	public healCard getHealCard(int id)throws SQLException{
		Connection conn=null;
		PreparedStatement  stmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			stmt=conn.prepareStatement("select * from healcard where HCid = ?");
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			healCard mbBean=null;
			if(rs.next()){
			   mbBean=new healCard(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				
			}
			return mbBean;
		}finally{
			db.closeResultSet(rs);
			db.closeStatement(stmt);
			db.closeConnection(conn);
		}
	}
    /**
     * 添加一个诊疗卡,内部方法	
     * @param man
     * @return
     */
	private int addHealCard(healCard man){
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("insert into healcard VALUES(?,?,?,?,?)");
			pstmt.setInt(1,man.getHcId());
			pstmt.setString(2,man.getUsername());
			pstmt.setString(3,man.getIdCard());
			pstmt.setString(4,man.getTel());
			pstmt.setString(5,man.getSex());
			if(pstmt.executeUpdate()>0)
				return man.getHcId();
			else {
				return -1;
			}
		}catch(SQLException ex){
			return -1;
		}
		finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	
	/**
	 * JSP调用，申请一个诊疗卡
	 * @param username
	 * @param idcard
	 * @param tel
	 * @param sex
	 * @return
	 */
	public int regisHealCard(String username,String idcard,String tel,String sex){
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("select MAX(HCid) from healcard");
			rs = pstmt.executeQuery();
			int count=0;
			if(rs.next())
				 count= rs.getInt(1);
			count++;
			healCard newHealCard = new healCard(count,username,idcard,tel,sex);
			return addHealCard(newHealCard);
		}catch(SQLException ex){
			return -1;
		}
		finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * 添加一个healcardAssign记录
	 * @param hca
	 * @return
	 */
	public boolean addHealCardAssign(healcardAssign hca){
		Connection conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs=null;
		try{
			conn=db.getConnection();
			pstmt=conn.prepareStatement("insert into healcardAssign(healcardId,assignId) VALUES(?,?)");
			pstmt.setInt(1,hca.getHealcardId());
			pstmt.setInt(2, hca.getAssignId());
			if(pstmt.executeUpdate()>0)
				return true;
			else {
				return false;
			}
		}catch(SQLException ex){
			return false;
		}
		finally{
			db.closeResultSet(rs);
			db.closeStatement(pstmt);
			db.closeConnection(conn);
		}
	}
	/**
	 * 外部调用
	 * @param hcid
	 * @param assignId
	 * @return
	 */
	public boolean insertHealCardAssign(int hcid,int assignId){
		healcardAssign hcAssign =new healcardAssign(hcid,assignId);
		return addHealCardAssign(hcAssign);
	}
	
	
}
