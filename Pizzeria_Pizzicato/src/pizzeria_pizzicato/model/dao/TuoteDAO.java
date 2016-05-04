package pizzeria_pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import pizzeria_pizzicato.control.VertailijaTuote;
import pizzeria_pizzicato.model.TilattuTuote;
import pizzeria_pizzicato.model.Tuote;
import pizzeria_pizzicato.model.Tayte;
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
		PreparedStatement stmtSelect = null;
		ResultSet rs = null;
		Tuote tuote = new Tuote();
		try {

			conn = getConnection();
			String sqlSelect = "SELECT tuote_id, tuote_nimi, tuote_hinta FROM Tuote WHERE tuote_id = ?;";
			stmtSelect = conn.prepareStatement(sqlSelect);
			stmtSelect.setInt(1, TID);
			rs = stmtSelect.executeQuery();

			while (rs.next()) {
					tuote = readTuote(rs);
			}			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmtSelect, conn);
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
		String sqlSelect = "SELECT tilaus_id, tuote_id, tilaus_rivi, tuote_hinta, lkm, valkosipuli, oregano, status FROM TilattuTuote WHERE tilaus_id = ?;";
		stmt = conn.prepareStatement(sqlSelect);
		stmt.setInt(1, TilausID);
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			TilattuTuote tilattuTuote = readTilatutTuotteet(rs, TilausID);
			tilaukset.add(tilattuTuote);
		}
		
		return tilaukset;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		
		
	}
	
	public TilattuTuote readTilatutTuotteet(ResultSet rs, int tilaus_id) {
		
		try {
			
			ArrayList<Tayte> lisataytteita = new ArrayList<Tayte>();
			int tuoteId = rs.getInt("tuote_id");
			int tilausId = rs.getInt("tilaus_id");
			int tilausrivi = rs.getInt("tilaus_rivi");
			int lkm = rs.getInt("lkm");
			int oregano = rs.getInt("oregano");
			int valkosipuli = rs.getInt("valkosipuli");
			double hinta = rs.getDouble("tuote_hinta");
			int status = rs.getInt("status");
			int TID = rs.getInt("tuote_id");
			
			Tuote tuote = haeTuoteIDnAvulla(TID);
			
			if(pizzaVaiJuoma(TID)){
				TayteDAO TDAO = new TayteDAO();
				lisataytteita.addAll(TDAO.haeLisataytteet(tilaus_id, tilausrivi));
			}

			return new TilattuTuote(tuoteId, tilausId, tilausrivi, lkm, oregano, valkosipuli, tuote , hinta, status, lisataytteita);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
public boolean pizzaVaiJuoma(int tuoteID) {
		
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Tuote> pizzalista = new ArrayList<Tuote>();
			boolean palaute = false;
	
		try {
			//hakee, lukee ja pizzat pizzat
			conn = getConnection();
			String sqlSelect = "SELECT tu.tuote_id, tu.tuote_nimi, tu.tuote_hinta FROM Tuote tu JOIN Pizza p ON p.tuote_id = tu.tuote_id;";
			stmt = conn.prepareStatement(sqlSelect);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Tuote asd = readTuote(rs);	
				pizzalista.add(asd);
			}
			
			//vertaa id-lukuja
			for (int i = 0; i < pizzalista.size(); i++) {
				if(pizzalista.get(i).getId()==tuoteID){
					palaute=true;
				}
			}
			
			return palaute;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			close(stmt, conn);
		}
	}
	
	
	
	private Tuote readTuote(ResultSet rs) {

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
