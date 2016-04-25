package pizzeria_pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import pizzeria_pizzicato.control.VertailijaTuote;
import pizzeria_pizzicato.model.Pizza;
import pizzeria_pizzicato.model.TilattuTuote;
import pizzeria_pizzicato.model.Tuote;
import pizzeria_pizzicato.model.dao.DataAccessObject;

public class TuoteDAO extends DataAccessObject{
	
	
	
	public ArrayList<Tuote> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Tuote> tuotteet = new ArrayList<Tuote>();
		Tuote tuote = new Tuote();
		try {

			conn = getConnection();

			String sqlSelect = "SELECT * FROM Tuote";

			stmt = conn.prepareStatement(sqlSelect);

			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
					tuote = readTuote(rs);
					tuotteet.add(tuote);
			}			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		Collections.sort(tuotteet, new VertailijaTuote());
		return tuotteet;
	}
	
	public Tuote haeTuoteIDnAvulla(int TID) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Tuote tuote = new Tuote();
		try {

			conn = getConnection();
			String sqlSelect = "SELECT tuote_id, tuote_nimi, tuote_hinta FROM Tuote WHERE tuote_id= "+TID+";";
			stmt = conn.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
					tuote = readTuote(rs);
			}			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		return tuote;
	}
	
	public ArrayList<TilattuTuote> haeTilatutTuotteet(int TilausID) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<TilattuTuote> tilaukset = new ArrayList<TilattuTuote>();
		
		try{
		
		conn = getConnection();
		String sqlSelect = "SELECT tilaus_id, tuote_id, tilaus_rivi, tuote_hinta, lkm, valkosipuli, oregano FROM TilattuTuote WHERE tilaus_id = ?;";
		stmt = conn.prepareStatement(sqlSelect);
		stmt.setInt(1, TilausID);
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			TilattuTuote tilattuTuote = readTilatutTuotteet(rs);
			tilaukset.add(tilattuTuote);
		}
		
		return tilaukset;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		
		
	}
	
	
	
	
	public TilattuTuote readTilatutTuotteet(ResultSet rs) {
		
		try {
			
				
			int tilausrivi = rs.getInt("tilaus_rivi");
			int lkm = rs.getInt("lkm");
			int oregano = rs.getInt("oregano");
			int valkosipuli = rs.getInt("valkosipuli");
			double hinta = rs.getDouble("tuote_hinta");
			int TID = rs.getInt("tuote_id");
			
			Tuote tuote = haeTuoteIDnAvulla(TID);

			return new TilattuTuote(tilausrivi, lkm, oregano, valkosipuli, tuote ,hinta);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public Tuote readTuote(ResultSet rs) {

		try {

			int id = rs.getInt("tuote_id");
			String nimi = rs.getString("tuote_nimi");
			double hinta = rs.getDouble("tuote_hinta");

			return new Tuote(id,nimi,hinta);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}