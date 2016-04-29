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

			String sqlInsert = "INSERT INTO Tayte(tayte_id, tayte_nimi, tayte_nimi_en, tayte_hinta) VALUES (?, ?, ?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setInt(1, tayte.getTayte_id());
			stmtInsert.setString(2, tayte.getTayte_nimi());
			stmtInsert.setString(3, tayte.getTayte_nimi_en());
			stmtInsert.setDouble(4, tayte.getTayte_hinta());

			stmtInsert.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}
	
	public ArrayList<Tayte> karsiFantasianPerustaytteet(ArrayList<Tayte> listaKaikista, int fantasiaID){
		PizzaDAO pdao = new PizzaDAO();
		ArrayList<Tayte> fantasianTaytteet = pdao.haePizzanTaytteet(fantasiaID);
		ArrayList<Tayte> karsittavaLista = listaKaikista;
		
		for (int i = 0; i < karsittavaLista.size(); i++) {
			for (int j = 0; j < fantasianTaytteet.size(); j++) {
				if(karsittavaLista.get(i).getTayte_id()==fantasianTaytteet.get(j).getTayte_id()){
					karsittavaLista.remove(i);
				}
			}
			
		}
		return karsittavaLista;
		
		
		
	}
	
	public void updateTayte(Tayte tayte) throws SQLException {
		Connection connection = null;
		
		PreparedStatement stmtUpdate = null;
		
		

		try {
			
			connection = getConnection();
			
			String sqlUpdate = "UPDATE Tayte SET tayte_nimi = ?, tayte_nimi_en=?, tayte_hinta= ? WHERE tayte_id =?";
			stmtUpdate = connection.prepareStatement(sqlUpdate);
			stmtUpdate.setString(1, tayte.getTayte_nimi());
			stmtUpdate.setString(2, tayte.getTayte_nimi_en());
			stmtUpdate.setDouble(3, tayte.getTayte_hinta());
			stmtUpdate.setInt(4, tayte.getTayte_id());
			stmtUpdate.executeUpdate();
			
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtUpdate, connection); 
		}
	}


	public void deleteTayte(Tayte tayte) throws SQLException {
		Connection connection = null;

		PreparedStatement stmtDelete = null;

		try {

			connection = getConnection();

			String sqlDelete = "DELETE FROM Tayte WHERE tayte_id =?";
			stmtDelete = connection.prepareStatement(sqlDelete);
			stmtDelete.setInt(1, tayte.getTayte_id());
			stmtDelete.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtDelete, connection);

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

			String sqlSelect = "SELECT tayte_id, tayte_nimi, tayte_nimi_en, tayte_hinta FROM Tayte;";

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

	private Tayte readTayte(ResultSet rs) {

		try {

			int id = rs.getInt("tayte_id");
			String nimi = rs.getString("tayte_nimi");
			String nimi_en = rs.getString("tayte_nimi_en");
			double hinta = rs.getDouble("tayte_hinta");

			return new Tayte(id, nimi, nimi_en, hinta);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private ArrayList<Tayte> findTayte() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Tayte> taytteet = new ArrayList<Tayte>();
		Tayte tayte = null;
		try {

			conn = getConnection();

			String sqlSelect = "SELECT tayte_id, tayte_nimi, tayte_nimi_en, tayte_hinta FROM Tayte;";

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
	
	public Tayte getTayte(int id){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Tayte tulos = null;
		try {
			
			conn = getConnection();
			ArrayList<Tayte> taytteet = findTayte();
			for (int i = 0; i < taytteet.size(); i++) {
				if(taytteet.get(i).getTayte_id()==id){
					tulos = taytteet.get(i);
				}
			}
			
			return tulos;
		} finally {
			close(rs, stmt, conn); 
		}
		
		
	}
	
	public ArrayList<Tayte> haeLisataytteet(int tilausID, int tilausrivi){
		ArrayList<Tayte> palautettava = new ArrayList<Tayte>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		String sqlSelect;
		
		try {
	
			conn = getConnection();
			
			sqlSelect = "SELECT lt.tayte_id, ta.tayte_nimi, ta.tayte_hinta, ta.tayte_nimi_en FROM LisaTayte lt JOIN Tayte ta ON lt.tayte_id=ta.tayte_id WHERE tilaus_id= ? AND tilaus_rivi=?";
			stmt = conn.prepareStatement(sqlSelect);
			stmt.setInt(1, tilausID);
			stmt.setInt(2, tilausrivi);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				palautettava.add(readTayte(rs));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}

		
		
		return palautettava;
		
	}
	

}
