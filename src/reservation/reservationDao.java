package reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.rmi.CORBA.Tie;



public class reservationDao {
	private Connection connection;
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String user="kim";
	String pwd="1111";
	
	public reservationDao() {
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			this.connection=DriverManager.getConnection(url,user,pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public void insert(reservationDto reservationDto) {

		String sql="insert into reservation values(?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,1);
			preparedStatement.setString(2, reservationDto.getEmail());
			preparedStatement.setString(3, reservationDto.getName());
			preparedStatement.setInt(4,reservationDto.getTime());
			preparedStatement.setTimestamp(5, reservationDto.getCreated());
			preparedStatement.setTimestamp(6, reservationDto.getrDate());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
	}
	public List<reservationDto> findAllByDate(Timestamp timestamp) {
		String sql="select * from reservation where rDate=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, timestamp);
			ResultSet resultSet=preparedStatement.executeQuery();
			List<reservationDto>aList=new ArrayList<reservationDto>();
			while(resultSet.next()) {
				reservationDto dto=new reservationDto();
				dto.setId(resultSet.getInt("id"));
				dto.setEmail(resultSet.getString("email"));
				dto.setName(resultSet.getString("name"));
				dto.setCreated(resultSet.getTimestamp("created"));
				dto.setTime(resultSet.getInt("time"));
				dto.setrDate(resultSet.getTimestamp("rDate"));
				aList.add(dto);
			}
			return aList;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		return null;
	}
	public List<reservationDto> findAllRerservation() {
		String sql="select * from reservation";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			List<reservationDto>aList=new ArrayList<reservationDto>();
			while(resultSet.next()) {
				reservationDto dto=new reservationDto();
				dto.setId(resultSet.getInt("id"));
				dto.setEmail(resultSet.getString("email"));
				dto.setName(resultSet.getString("name"));
				dto.setCreated(resultSet.getTimestamp("created"));
				dto.setTime(resultSet.getInt("time"));
				dto.setrDate(resultSet.getTimestamp("rDate"));
				aList.add(dto);
			}
			return aList;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		return null;
	}
}
