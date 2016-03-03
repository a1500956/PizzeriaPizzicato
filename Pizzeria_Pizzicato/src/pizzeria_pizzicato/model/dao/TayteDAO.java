
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

		
		public void deleteTayte(Tayte tayte) throws SQLException {
			Connection connection = null;
			
			PreparedStatement stmtDelete = null;
			
			
			

			try {
				
				connection = getConnection();
				
				String sqlDelete = "DELETE FROM Tayte WHERE Tayte_id =?";
				stmtDelete = connection.prepareStatement(sqlDelete);
				stmtDelete.setInt(1, tayte.getId());
				stmtDelete.executeUpdate();		
				
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(stmtDelete, connection);
				
				
			}
		}

		
		public void updateTayte(Tayte tayte) throws SQLException {
			Connection connection = null;
			
			PreparedStatement stmtUpdate = null;
			
			

			try {
				
				connection = getConnection();
				
				String sqlUpdate = "UPDATE tayte SET nimi = ? WHERE tayte_id =?";
				stmtUpdate = connection.prepareStatement(sqlUpdate);
				stmtUpdate.setInt(2, tayte.getId());
				stmtUpdate.setString(1, tayte.getNimi());
				stmtUpdate.executeUpdate();
				
				
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(stmtUpdate, connection); 
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
