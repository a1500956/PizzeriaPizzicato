
	package pizzeria_pizzicato.model.dao;

	import java.sql.Connection;


	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzeria_pizzicato.model.PizzaTayte;
import pizzeria_pizzicato.model.dao.DataAccessObject;




	public class PizzaTayteDAO extends DataAccessObject {
		
		
		
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
		
		public ArrayList<PizzaTayte> haePizza() {	
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
