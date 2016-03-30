package pizzeria_pizzicato.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.dao.DataAccessObject;

public class PizzaDAO extends DataAccessObject {

	//Poistaa pizzan sek‰ siihen liittyv‰t t‰ytteet PizzaTayte taulukosta
	
	public void deletePizza(Pizza pizza) throws SQLException {
		Connection connection = null;

		PreparedStatement stmtDelete = null;
		PreparedStatement stmtDeletet = null;

		try {

			connection = getConnection();

			String sqlDelete = "DELETE FROM Pizza WHERE Pizza_id =?";
			stmtDelete = connection.prepareStatement(sqlDelete);
			stmtDelete.setInt(1, pizza.getId());
			stmtDelete.executeUpdate();

			String sqlDeletet = "DELETE FROM PizzaTayte WHERE Pizza_id =?";
			stmtDeletet = connection.prepareStatement(sqlDeletet);
			stmtDeletet.setInt(1, pizza.getId());
			;
			stmtDeletet.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtDelete, connection);
			close(stmtDeletet, connection);

		}
	}

	public void updatePizza(Pizza pizza) throws SQLException {
		Connection connection = null;

		PreparedStatement stmtUpdate = null;

		try {

			connection = getConnection();

			String sqlUpdate = "UPDATE Pizza SET pizza_nimi = ?, pizza_hinta= ?, nakyy = ? WHERE pizza_id =?";
			stmtUpdate = connection.prepareStatement(sqlUpdate);
			stmtUpdate.setString(1, pizza.getNimi());
			stmtUpdate.setDouble(2, pizza.getHinta());
			stmtUpdate.setDouble(3, pizza.getNakyy());
			stmtUpdate.setInt(4, pizza.getId());
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

			String sqlInsert = "INSERT INTO Pizza(pizza_nimi, pizza_hinta, nakyy) VALUES (?, ?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setString(1, pizza.getNimi());
			stmtInsert.setDouble(2, pizza.getHinta());
			stmtInsert.setInt(3, pizza.getNakyy());

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
		ArrayList<Pizza> pizzat = new ArrayList<Pizza>();
		Pizza pizza = null;
		try {

			conn = getConnection();

			String sqlSelect = "SELECT pizza_id, pizza_nimi, pizza_hinta, nakyy FROM Pizza;";

			stmt = conn.prepareStatement(sqlSelect);

			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				pizza = readPizza(rs);

				pizzat.add(pizza);
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

			int id = rs.getInt("pizza_id");
			String nimi = rs.getString("pizza_nimi");
			double hinta = rs.getDouble("pizza_hinta");
			int nakyy = rs.getInt("nakyy");

			return new Pizza(id, nimi, hinta, nakyy);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int getPizzaId(String nimi) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int tulos = -1;
		try {

			conn = getConnection();

			String sqlSelect = "SELECT pizza_id FROM Pizza WHERE pizza_nimi='"
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
			return rs.getInt("pizza_id");
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
			return rs.getString("pizza_nimi");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}