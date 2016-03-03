
	package pizzeria_pizzicato.model.dao;

	import java.sql.Connection;


	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import pizzeria_pizzicato.model.Tayte;
	import pizzeria_pizzicato.model.dao.DataAccessObject;




	public class TayteDAO extends DataAccessObject {
		
		
		public void addTayte(Tayte tayte) throws SQLException {
			
			Connection connection = null;
			PreparedStatement stmtInsert = null;
		
			try {
				
				connection = getConnection();
				
				String sqlInsert = "INSERT INTO Tayte(tayte_id, tayte_nimi) VALUES (?, ?)";
				stmtInsert = connection.prepareStatement(sqlInsert);
				stmtInsert.setInt(1, tayte.getTayte_id());
				stmtInsert.setString(2, tayte.getTayte_nimi());
				
				
				
				stmtInsert.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(stmtInsert, connection); 
			}
		}

		
		
		
		public ArrayList<Tayte> findAll() {	
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Tayte> taytteet = new ArrayList<Tayte>();
			Tayte tayte = null; 
			try {
				
				conn = getConnection();
				
				String sqlSelect = "SELECT tayte_id, tayte_nimi FROM Tayte;";
			
				stmt = conn.prepareStatement(sqlSelect);
				
				rs = stmt.executeQuery(sqlSelect);
			
				while (rs.next()) {
					tayte = readTayte(rs);
				
					taytteet.add(tayte);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(rs, stmt, conn); 
			}
		
			return taytteet;
		}
		
		public Tayte readTayte(ResultSet rs) {
			
			
			
			
			try {
				
				int id = rs.getInt("id");
				String nimi = rs.getString("nimi");
								
						
				
				return new Tayte(id, nimi);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		public ArrayList<Tayte> findTayte() {	
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Tayte> taytteet = new ArrayList<Tayte>();
			Tayte tayte = null; 
			try {
				
				conn = getConnection();
				
				String sqlSelect = "SELECT tayte_id, tayte_nimi FROM Tayte WHERE;";
			
				stmt = conn.prepareStatement(sqlSelect);
				
				rs = stmt.executeQuery(sqlSelect);
			
				while (rs.next()) {
					tayte = readTayte(rs);
				
					taytteet.add(tayte);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(rs, stmt, conn); 
			}
		
			return taytteet;
		}

		
	}
