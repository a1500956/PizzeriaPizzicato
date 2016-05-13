package pizzeria_pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import pizzeria_pizzicato.control.VertailijaTuote;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.dao.DataAccessObject;

public class PizzaDAO extends DataAccessObject {

	//Poistaa pizzan sek‰ siihen liittyv‰t t‰ytteet PizzaTayte taulukosta
	
	public void deletePizza(Pizza pizza) throws SQLException {
		Connection connection = null;

		PreparedStatement stmtDelete = null;
		PreparedStatement stmtDelete2 = null;
		PreparedStatement stmtDelete3 = null;

		try {

			connection = getConnection();
			
			String sqlDelete = "DELETE FROM PizzaTayte WHERE tuote_id =?";
			stmtDelete = connection.prepareStatement(sqlDelete);
			stmtDelete.setInt(1, pizza.getId());
			stmtDelete.executeUpdate();

			String sqlDelete2 = "DELETE FROM Pizza WHERE tuote_id =?";
			stmtDelete2 = connection.prepareStatement(sqlDelete2);
			stmtDelete2.setInt(1, pizza.getId());
			stmtDelete2.executeUpdate();
			
			String sqlDelete3 = "DELETE FROM Tuote WHERE tuote_id =?";
			stmtDelete3 = connection.prepareStatement(sqlDelete3);
			stmtDelete3.setInt(1, pizza.getId());
			stmtDelete3.executeUpdate();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtDelete, connection);
			close(stmtDelete2, connection);
			close(stmtDelete3, connection);

		}
	}

	public void updatePizza(Pizza pizza) throws SQLException {
		Connection connection = null;

		PreparedStatement stmtUpdate = null;
		PreparedStatement stmtUpdate2 = null;

		try {

			connection = getConnection();

			String sqlUpdate = "UPDATE Pizza SET pizza_nakyy = ? WHERE tuote_id =?";
			stmtUpdate = connection.prepareStatement(sqlUpdate);
			stmtUpdate.setDouble(1, pizza.getNakyy());
			stmtUpdate.setInt(2, pizza.getId());
			stmtUpdate.executeUpdate();
			
			String sqlUpdate2 = "UPDATE Tuote SET tuote_nimi = ?, tuote_hinta = ? WHERE tuote_id =?";
			stmtUpdate2 = connection.prepareStatement(sqlUpdate2);
			stmtUpdate2.setString(1, pizza.getNimi());
			stmtUpdate2.setDouble(2, pizza.getHinta());
			stmtUpdate2.setInt(3, pizza.getId());
			stmtUpdate2.executeUpdate();


		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtUpdate, connection);
			close(stmtUpdate2, connection);
		}
	}


	//Lis‰‰ uuden pizzan attribuutteineen 
	
	public void addPizza(Pizza pizza) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		PreparedStatement stmtInsert2 = null;

		try {

			connection = getConnection();


			String sqlInsert = "INSERT INTO Tuote(tuote_nimi, tuote_hinta) VALUES (?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			

			stmtInsert.setString(1, pizza.getNimi());
			stmtInsert.setDouble(2, pizza.getHinta());
			
			stmtInsert.executeUpdate();
			
			String iidee = Integer.toString(getPizzaId(pizza.getNimi()));

			String sqlInsert2 = "INSERT INTO Pizza(tuote_id, pizza_nakyy) VALUES (?, ?)";
			stmtInsert2 = connection.prepareStatement(sqlInsert2);

			stmtInsert2.setString(1, iidee);
			stmtInsert2.setInt(2, pizza.getNakyy());

			stmtInsert2.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
			close(stmtInsert2, connection);
		}
	}

	//Hakee kaikki tietokannassa olevat pizzat
	
	public ArrayList<Pizza> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Pizza> pizzat = new ArrayList<Pizza>();
		Pizza pizza = new Pizza();
		try {

			conn = getConnection();

			String sqlSelect = "SELECT t.tuote_id, t.tuote_nimi, t.tuote_hinta, p.pizza_nakyy FROM Tuote t JOIN Pizza p ON t.tuote_id=p.tuote_id;";

			stmt = conn.prepareStatement(sqlSelect);

			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				pizza = readPizza(rs);
				pizza.addTaytteita(haePizzanTaytteet(pizza.getId()));
				pizzat.add(pizza);
				pizza = null;
			}			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		Collections.sort(pizzat, new VertailijaTuote());
		return pizzat;
	}
	

	private Pizza readPizza(ResultSet rs) {

		try {

			int id = rs.getInt("tuote_id");
			String nimi = rs.getString("tuote_nimi");
			double hinta = rs.getDouble("tuote_hinta");
			int nakyy = rs.getInt("pizza_nakyy");

			return new Pizza(id, nimi, hinta, nakyy);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int getPizzaId(String nimi) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int tulos = 0;
		try {

			conn = getConnection();

			String sqlSelect = "SELECT tuote_id FROM Tuote WHERE tuote_nimi='"
					+ nimi + "';";

			stmt = conn.prepareStatement(sqlSelect);

			rs = stmt.executeQuery(sqlSelect);

			if (rs.next()) {
				tulos = readId(rs);
			}
			return tulos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}

	}

	private int readId(ResultSet rs) {
		try {
			return rs.getInt("tuote_id");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Pizza getPizza(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Pizza tulos = null;
		try {

			conn = getConnection();
			ArrayList<Pizza> pizzat = findAll();
			for (int i = 0; i < pizzat.size(); i++) {
				if (pizzat.get(i).getId() == id) {
					tulos = pizzat.get(i);
				}
			}

			return tulos;
		} finally {
			close(rs, stmt, conn);
		}

	}
	
	public ArrayList<Tayte> haePizzanTaytteet(int id){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlSelect = null;
		ArrayList<Tayte> pizzanTaytteet = new ArrayList<Tayte>();
		Tayte t;
		try {
			conn= getConnection();
			sqlSelect="SELECT ta.tayte_id, ta.tayte_nimi, ta.tayte_hinta, ta.tayte_nimi_en FROM PizzaTayte pt JOIN Tayte ta ON ta.tayte_id = pt.tayte_id WHERE pt.tuote_id = ?;";
			stmt = conn.prepareStatement(sqlSelect);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				t = readTayte(rs);
				pizzanTaytteet.add(t);
			}
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			close(rs, stmt, conn);
		}
		
		return pizzanTaytteet;
		
	}

	public String readNimi(ResultSet rs) {
		try {
			return rs.getString("tuote_nimi");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Tayte readTayte(ResultSet rs) {

		try {

			int tayte = rs.getInt("tayte_id");
			String nimi = rs.getString("tayte_nimi");
			String nimi_en = rs.getString("tayte_nimi_en");

			return new Tayte(tayte, nimi, nimi_en, 0);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
