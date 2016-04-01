
	package pizzeria_pizzicato.model.dao;

	import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.dao.DataAccessObject;

	public class PizzaTayteDAO extends DataAccessObject {
		
		public void addTayteToPizza(int tayteId, int pizzaId) throws SQLException {
			Connection connection = null;
			PreparedStatement stmtInsert = null;
		
			try {
				
				connection = getConnection();
				
					String sqlInsert = "INSERT INTO PizzaTayte(tuote_id, tayte_id) VALUES ("+pizzaId+","+tayteId+")";
					stmtInsert = connection.prepareStatement(sqlInsert);
					stmtInsert.executeUpdate();
					
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(stmtInsert, connection); 
			}
		}
		
		public ArrayList<Tayte> haePizzanTaytteet(int PID) {	
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Tayte> pizzanTaytteet = new ArrayList<Tayte>();
			Tayte pTayte = new Tayte();
			try {
				
				conn = getConnection();
				
				String sqlSelect = "SELECT tayte_id FROM PizzaTayte WHERE tuote_id="+PID+";";
			
				stmt = conn.prepareStatement(sqlSelect);
				
				rs = stmt.executeQuery(sqlSelect);
			
				while (rs.next()) {
					pTayte = readTayte(rs);
				
					pizzanTaytteet.add(pTayte);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(rs, stmt, conn); 
			}
		
			return pizzanTaytteet;
		}
		
			public Tayte readTayte(ResultSet rs) {
			
			
			
			
			try {
				
				TayteDAO TDAO = new TayteDAO();
				
				return TDAO.getTayte(rs.getInt("tayte_id"));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		public void poistaPizzaTaytelistalta(int pizzaID){
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				
				conn = getConnection();
				
				
				String sqlSelect = "DELETE FROM PizzaTayte WHERE tuote_id ='"+pizzaID+"';";
				stmt = conn.prepareStatement(sqlSelect);
				
				rs = stmt.executeQuery(sqlSelect);
			
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				close(rs, stmt, conn); 
			}
		}

		
	}
