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
 * 获取数据库连接工具类
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
	 * 得到数据库连接
	 * @return Connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		return dSource.getConnection();
	}
	/**
	 * 关闭连接对象
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
	 * 关闭Statement对象
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
	 * 关闭PreparedStatement
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
	 * 关闭ResultSet对象
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
