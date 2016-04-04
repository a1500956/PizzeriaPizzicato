package pizzeria_pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.Tayte;
import pizzeria_pizzicato.model.dao.DataAccessObject;

public class PizzaDAO extends DataAccessObject {

	//Poistaa pizzan sek‰ siihen liittyv‰t t‰ytteet PizzaTayte taulukosta
	
	public void deletePizza(Pizza pizza) throws SQLException {
		Connection connection = null;

		PreparedStatement stmtDelete = null;

		try {

			connection = getConnection();
			
			String sqlDelete = "DELETE FROM PizzaTayte WHERE tuote_id =?";
			stmtDelete = connection.prepareStatement(sqlDelete);
			stmtDelete.setInt(1, pizza.getId());
			stmtDelete.executeUpdate();

			sqlDelete = "DELETE FROM Pizza WHERE tuote_id =?";
			stmtDelete = connection.prepareStatement(sqlDelete);
			stmtDelete.setInt(1, pizza.getId());
			stmtDelete.executeUpdate();
			
			sqlDelete = "DELETE FROM Tuote WHERE tuote_id =?";
			stmtDelete = connection.prepareStatement(sqlDelete);
			stmtDelete.setInt(1, pizza.getId());
			stmtDelete.executeUpdate();
			
			
			
			
			
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtDelete, connection);

		}
	}

	public void updatePizza(Pizza pizza) throws SQLException {
		Connection connection = null;

		PreparedStatement stmtUpdate = null;

		try {

			connection = getConnection();

			String sqlUpdate = "UPDATE Pizza SET pizza_nakyy = ? WHERE tuote_id =?";
			stmtUpdate = connection.prepareStatement(sqlUpdate);
			stmtUpdate.setDouble(1, pizza.getNakyy());
			stmtUpdate.setInt(2, pizza.getId());
			stmtUpdate.executeUpdate();
			
			sqlUpdate = "UPDATE Tuote SET tuote_nimi = ?, tuote_hinta = ? WHERE tuote_id =?";
			stmtUpdate = connection.prepareStatement(sqlUpdate);
			stmtUpdate.setString(1, pizza.getNimi());
			stmtUpdate.setDouble(2, pizza.getHinta());
			stmtUpdate.setInt(3, pizza.getId());
			stmtUpdate.executeUpdate();


		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtUpdate, connection);
		}
	}


	//Lis‰‰ uuden pizzan attribuutteineen 
	
	public void addPizza(Pizza pizza) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {

			connection = getConnection();


			String sqlInsert = "INSERT INTO Tuote(tuote_nimi, tuote_hinta) VALUES (?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);

			stmtInsert.setString(1, pizza.getNimi());
			stmtInsert.setDouble(2, pizza.getHinta());
			
			stmtInsert.executeUpdate();
			
			String iidee = Integer.toString(getPizzaId(pizza.getNimi()));

			String sqlInsert2 = "INSERT INTO Pizza(tuote_id, pizza_nakyy) VALUES (?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert2);

			stmtInsert.setString(1, iidee);
			stmtInsert.setInt(2, pizza.getNakyy());

			stmtInsert.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}

	//Hakee kaikki tietokannassa olevat pizzat
	
	public ArrayList<Pizza> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Tayte tayte = new Tayte();
		ArrayList<Pizza> pizzat = new ArrayList<Pizza>();
		Pizza pizza = new Pizza();
		int edellinenPizzaId = 0;
		try {

			conn = getConnection();

			String sqlSelect = "SELECT y.tuote_nimi, p.tuote_id, t.tayte_id, y.tuote_hinta, p.pizza_nakyy, x.tayte_nimi, x.tayte_nimi_en FROM Tuote y JOIN Pizza p ON p.tuote_id = y.tuote_id JOIN PizzaTayte t ON t.tuote_id = p.tuote_id JOIN Tayte x ON x.tayte_id = t.tayte_id ORDER BY p.tuote_id;";

			stmt = conn.prepareStatement(sqlSelect);

			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				if(rs.getInt("tuote_id") != edellinenPizzaId ){
					pizza = readPizza(rs);
					pizzat.add(pizza);
					edellinenPizzaId = pizza.getId();
				}
				tayte = readTayte(rs); //luodaan t‰yteolio
				pizza.addTayte(tayte);//lis‰‰ t‰yte pizzan t‰ytelistaan
			}			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}

		return pizzat;
	}

	public Pizza readPizza(ResultSet rs) {

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

	public int readId(ResultSet rs) {
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

	public String readNimi(ResultSet rs) {
		try {
			return rs.getString("tuote_nimi");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Tayte readTayte(ResultSet rs) {

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