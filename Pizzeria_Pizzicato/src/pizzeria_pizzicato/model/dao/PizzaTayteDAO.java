
	package pizzeria_pizzicato.model.dao;

	import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.PizzaTayte;
import pizzeria_pizzicato.model.dao.DataAccessObject;




	public class PizzaTayteDAO extends DataAccessObject {
		
		public void addTayteToPizza(Tayte tayte) throws SQLException {
			Connection connection = null;
			PreparedStatement stmtInsert = null;
		
			try {
				
				connection = getConnection();
				
				String sqlInsert = "INSERT INTO PizzaTayte(pId, tId) VALUES (last_insert_id(), ?)";
				stmtInsert = connection.prepareStatement(sqlInsert);			
				stmtInsert.setInt(2, tayte.getTayte_id());
				
				
				
				stmtInsert.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(stmtInsert, connection); 
			}
		}
		
		
		public ArrayList<PizzaTayte> findAll() {	
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<PizzaTayte> pTaytteet = new ArrayList<PizzaTayte>();
			PizzaTayte pTayte = new PizzaTayte();
			try {
				
				conn = getConnection();
				
				String sqlSelect = "SELECT pizza_id, tayte_id FROM PizzaTayte;";
			
				stmt = conn.prepareStatement(sqlSelect);
				
				rs = stmt.executeQuery(sqlSelect);
			
				while (rs.next()) {
					pTayte = readTayte(rs);
				
					pTaytteet.add(pTayte);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(rs, stmt, conn); 
			}
		
			return pTaytteet;
		}
		
		public PizzaTayte readTayte(ResultSet rs) {
			
			
			
			
			try {
				
				int id = rs.getInt("pizza_id");
				int tayte = rs.getInt("tayte_id");
				
				return new PizzaTayte(id, tayte);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		
	}
