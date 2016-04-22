package pizzeria_pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import pizzeria_pizzicato.control.VertailijaTuote;
import pizzeria_pizzicato.model.Juoma;

public class JuomaDAO extends DataAccessObject {

	
	public void addJuoma(Juoma juoma) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		PreparedStatement stmtInsert2 = null;

		try {

			connection = getConnection();


			String sqlInsert = "INSERT INTO Tuote(tuote_nimi, tuote_hinta) VALUES (?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			

			stmtInsert.setString(1, juoma.getNimi());
			stmtInsert.setDouble(2, juoma.getHinta());
			
			stmtInsert.executeUpdate();
			
			String iidee = Integer.toString(getJuomaId(juoma.getNimi()));

			String sqlInsert2 = "INSERT INTO Juoma(tuote_id, juoma_nakyy, juoma_litrakoko) VALUES (?, ?, ?)";
			stmtInsert2 = connection.prepareStatement(sqlInsert2);

			stmtInsert2.setString(1, iidee);
			stmtInsert2.setInt(2, juoma.getNakyy());
			stmtInsert2.setDouble(3, juoma.getLitrakoko());

			stmtInsert2.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
			close(stmtInsert2, connection);
		}
	}
	
	public int getJuomaId(String nimi) {
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
	
	
	public ArrayList<Juoma> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Juoma> juomat = new ArrayList<Juoma>();
		Juoma juoma = new Juoma();
		int edellinenJuomaId = 0;
		try {

			conn = getConnection();

			String sqlSelect = "SELECT t.tuote_id, t.tuote_nimi, j.juoma_litrakoko, t.tuote_hinta, j.juoma_nakyy FROM Tuote t INNER JOIN Juoma j ON t.tuote_id=j.tuote_id;";

			stmt = conn.prepareStatement(sqlSelect);

			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				if(rs.getInt("tuote_id") != edellinenJuomaId ){
					juoma = readJuoma(rs);
					juomat.add(juoma);
					edellinenJuomaId = juoma.getId();
				}
			}			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		Collections.sort(juomat, new VertailijaTuote());
		return juomat;
	}
	
	public Juoma readJuoma(ResultSet rs) {

		try {

			int id = rs.getInt("tuote_id");
			String nimi = rs.getString("tuote_nimi");
			double hinta = rs.getDouble("tuote_hinta");
			double litrakoko = rs.getDouble("juoma_litrakoko");
			int nakyy = rs.getInt("juoma_nakyy");

			return new Juoma(id, nimi, hinta, litrakoko, nakyy);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deleteJuoma(Juoma juoma) throws SQLException {
		Connection connection = null;

		PreparedStatement stmtDelete = null;
		PreparedStatement stmtDelete2 = null;
		PreparedStatement stmtDelete3 = null;

		try {

			connection = getConnection();
			

			String sqlDelete2 = "DELETE FROM Juoma WHERE tuote_id =?";
			stmtDelete2 = connection.prepareStatement(sqlDelete2);
			stmtDelete2.setInt(1, juoma.getId());
			stmtDelete2.executeUpdate();
			
			String sqlDelete3 = "DELETE FROM Tuote WHERE tuote_id =?";
			stmtDelete3 = connection.prepareStatement(sqlDelete3);
			stmtDelete3.setInt(1, juoma.getId());
			stmtDelete3.executeUpdate();
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtDelete, connection);
			close(stmtDelete2, connection);
			close(stmtDelete3, connection);

		}
	}
	
	public void updateJuoma(Juoma juoma) throws SQLException {
		Connection connection = null;

		PreparedStatement stmtUpdate = null;
		PreparedStatement stmtUpdate2 = null;

		try {

			connection = getConnection();

			String sqlUpdate = "UPDATE Juoma SET juoma_nakyy = ?, juoma_litrakoko = ? WHERE tuote_id =?";
			stmtUpdate = connection.prepareStatement(sqlUpdate);
			stmtUpdate.setDouble(1, juoma.getNakyy());
			stmtUpdate.setDouble(2, juoma.getLitrakoko());
			stmtUpdate.setInt(3, juoma.getId());
			stmtUpdate.executeUpdate();
			
			String sqlUpdate2 = "UPDATE Tuote SET tuote_nimi = ?, tuote_hinta = ? WHERE tuote_id =?";
			stmtUpdate2 = connection.prepareStatement(sqlUpdate2);
			stmtUpdate2.setString(1, juoma.getNimi());
			stmtUpdate2.setDouble(2, juoma.getHinta());
			stmtUpdate2.setInt(3, juoma.getId());
			stmtUpdate2.executeUpdate();


		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtUpdate, connection);
			close(stmtUpdate2, connection);
		}
	}
	
	public Juoma getJuoma(int id){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Juoma tulos = null;
		try {

			conn = getConnection();
			ArrayList<Juoma> juomat = findAll();
			for (int i = 0; i < juomat.size(); i++) {
				if (juomat.get(i).getId() == id) {
					tulos = juomat.get(i);
				}
			}

			return tulos;
		} finally {
			close(rs, stmt, conn);
		}
		
		
	}
}
