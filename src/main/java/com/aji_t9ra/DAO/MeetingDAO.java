package com.aji_t9ra.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.aji_t9ra.Models.*;
public class MeetingDAO extends AbstractDAO{
	
	public MeetingDAO() {
		super();
	}
	
	public int addMeeting(int id, int meeting_id, String link) throws SQLException {
		 String query = "INSERT INTO `aji_t9radb`.`new_meeting` (`user_id`,`id`,`link`) VALUES (?, ?,?)";
	        PreparedStatement ps = this.getPrepareStatement(query); 
	        ps.setInt(1,id);
	        ps.setInt(2,meeting_id);
	        ps.setString(3, link);
	        ResultSet rs  = ps.executeQuery();
	        return 1;
	}
	
	public List<Meeting> getAllMeetings(int id) throws SQLException {
		List<Meeting> listMeeting = new ArrayList();
		 String query = "select * from `aji_t9radb`.`new_meeting` where user_id = ? ";
	        PreparedStatement ps = this.getPrepareStatement(query); 
	        ps.setInt(1,id);
	        ResultSet rs  = ps.executeQuery();
	        while (rs.next()) {
				Meeting m = new Meeting();
				m.setId(rs.getInt("id"));
				m.setUserId(rs.getInt("user_id"));
				m.setLink(rs.getString("link"));
				listMeeting.add(m);
			}
			return listMeeting;
	}

}
