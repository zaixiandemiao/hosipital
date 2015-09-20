package DBbean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import manager.managerBean;

import com.mysql.jdbc.PreparedStatement;
/**
 * ��ȡ���ݿ����ӹ�����
 * @author Administrator
 *
 */
public class dbBean implements Serializable{

	private DataSource dSource=null;
	
	public dbBean() throws NamingException{
		Context ctxContext=new InitialContext();
		dSource=(DataSource)ctxContext.lookup("java:comp/env/jdbc/test");
	}
	/**
	 * �õ����ݿ�����
	 * @return Connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		return dSource.getConnection();
	}
	/**
	 * �ر����Ӷ���
	 * @param conn
	 */
	public void closeConnection(Connection conn) {
		if(conn!=null){
			try{
				conn.close();
				conn=null;
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}
	/**
	 * �ر�Statement����
	 * @param stmt
	 */
	public void closeStatement(Statement stmt) {
		if(stmt!=null){
			try{
				stmt.close();
				stmt=null;
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * �ر�PreparedStatement
	 * @param pstmt
	 */
	public void closePreparedStatement(PreparedStatement pstmt){
		if(pstmt!=null){
			try{
				pstmt.close();
				pstmt=null;
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * �ر�ResultSet����
	 * @param rs
	 */
	public void closeResultSet(ResultSet rs) {
		if(rs!=null){
			try{
				rs.close();
				rs=null;
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}
}
