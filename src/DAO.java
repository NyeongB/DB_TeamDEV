
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




public class DAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "hr";
	String upw = "hr";
	
	private static DAO instance = new DAO();
	
	private DAO() {
	}
	
	public static DAO getInstance(){
		return instance;
	}
	public String getMember(String id) {
		String nickname = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from userT where id = ?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);//1번쨰 ? 들어가는 값을 셋함  
			set = pstmt.executeQuery();
			 
			 if(set.next()) { // 한줄씩 넘어가면서 결과를 뽑아옴 
				 nickname=set.getString("Nickname"); //검색한 결과값에서부터 특정 칼럼값을 가져옴 
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return nickname;
		
	}
	private Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(driver);
			connection =  DriverManager.getConnection(url, uid, upw);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return connection;
	}
}
