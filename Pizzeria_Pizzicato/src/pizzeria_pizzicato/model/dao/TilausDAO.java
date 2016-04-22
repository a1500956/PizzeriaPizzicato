package pizzeria_pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;

import pizzeria_pizzicato.model.Kayttaja;
import pizzeria_pizzicato.model.Ostoskori;
import pizzeria_pizzicato.model.TilattuTuote;
import pizzeria_pizzicato.model.Tilaus;
import pizzeria_pizzicato.model.Tuote;
import pizzeria_pizzicato.model.dao.DataAccessObject;

public class TilausDAO extends DataAccessObject {

	public void addTilaus(Tilaus Tilaus, Ostoskori ostoskori) throws SQLException {

		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {

			connection = getConnection();

			String sqlInsert = "INSERT INTO Tilaus(tilaus_osoite, kayttaja_id, tilaus_puhnro) VALUES (?, ?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setString(1, Tilaus.getOsoite());
			stmtInsert.setInt(2, Tilaus.getKayttaja().getKayttaja_id());
			stmtInsert.setString(3, Tilaus.getPuhnro());
			

			stmtInsert.executeUpdate();
			
			//Valmistellaan tilattujen tuotteiden lisäys
			String sqlInsert2 = "INSERT INTO TilattuTuote(tilaus_id, tuote_id, tilaus_rivi, tuote_hinta, lkm, valkosipuli, oregano) VALUES (?,?,?,?,?,?,?)";
			//Käytetään tilauksen ID-luvun löytävää metodia löytämään viimeisin kyseessä olevan käyttäjän kyseiseen osoitteeseen tekemä tilaus
			int tilauksenID = haeTilauksenID(Tilaus.getOsoite(), Tilaus.getKayttaja().getKayttaja_id());
			
			TilattuTuote TX;
			
			for (int i = 0; i < ostoskori.getKoko(); i++) {
					TX = ostoskori.getTuote(i);
					stmtInsert = connection.prepareStatement(sqlInsert2);
					//Tässä käytämme aiemmin esillekaivamaamme uusimman ID:n lukua
					stmtInsert.setInt(1, tilauksenID);
					stmtInsert.setInt(2, TX.getTuote().getId());
					stmtInsert.setInt(3, i);
					stmtInsert.setDouble(4, TX.getTuote().getHinta());
					stmtInsert.setDouble(5, TX.getLkm());
					stmtInsert.setInt(6, TX.getvSipuli());
					stmtInsert.setInt(7, TX.getOregano());
					stmtInsert.executeUpdate();	
				}
				
					
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}
	
	public void updateTilaus(Tilaus Tilaus) throws SQLException {
		Connection connection = null;
		
		PreparedStatement stmtUpdate = null;
		
		

		try {
			
			connection = getConnection();
			
			String sqlUpdate = "UPDATE Tilaus SET tilaus_osoite = ?, status_id=? WHERE tilaus_id =?";
			stmtUpdate = connection.prepareStatement(sqlUpdate);
			stmtUpdate.setString(1, Tilaus.getOsoite());
			stmtUpdate.setInt(2, Tilaus.getStatusID());
			

			stmtUpdate.executeUpdate();
				
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtUpdate, connection); 
		}
	}
	public void updateTilausStatus(Tilaus Tilaus) throws SQLException {
		Connection connection = null;
		
		PreparedStatement stmtUpdate = null;
		
		

		try {
			
			connection = getConnection();
			
			String sqlUpdate = "UPDATE Tilaus SET tilaus_osoite = ?, status_id=? WHERE tilaus_id =?";
			stmtUpdate = connection.prepareStatement(sqlUpdate);
			stmtUpdate.setString(1, Tilaus.getOsoite());
			stmtUpdate.setInt(2, Tilaus.getStatusID());
			

			stmtUpdate.executeUpdate();
				
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtUpdate, connection); 
		}
	}
	
	
	//Päivittää tilauksen statuksen kokilta.
	
	public void updateTilausOk(int tilaus_id) throws SQLException {
		Connection connection = null;
		
		PreparedStatement stmtUpdate = null;
		
		int tRivi = tilaus_id;
		int sId = 3;

		try {
			
			connection = getConnection();
			
			String sqlUpdate = "UPDATE Tilaus SET status_id =? WHERE tilaus_id =?";
			stmtUpdate = connection.prepareStatement(sqlUpdate);
			stmtUpdate.setInt(1, sId);
			stmtUpdate.setInt(2, tRivi);
			
			

			stmtUpdate.executeUpdate();
				
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtUpdate, connection); 
		}
	}

	
	//Tämä toiminto asettaa tilauksen 'peruutettu'-tilaan arkistointia varten.
	public void peruutaTilaus(int tilausID) throws SQLException {

		Connection connection = null;		
		PreparedStatement stmtUpdate = null;

		try {

				connection = getConnection();
			
				//Status nro '400' viittaa 'Tilaus Peruttu'-tilaan
				String sqlUpdate = "UPDATE Tilaus SET status_id= 400 WHERE tilaus_id =?";
				stmtUpdate = connection.prepareStatement(sqlUpdate);
				stmtUpdate.setInt(1, tilausID);
				stmtUpdate.executeUpdate();
				
					
			}catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
			close(stmtUpdate, connection);
		}
	}


	//Tämä poistaa tilauksen. Pysyvästi. Käytä harkiten!
	public void deleteTilaus(Tilaus Tilaus) throws SQLException {
		Connection connection = null;

		PreparedStatement stmtDelete = null;

		try {

			connection = getConnection();

			String sqlDelete = "DELETE FROM Tilaus WHERE Tilaus_id =?";
			stmtDelete = connection.prepareStatement(sqlDelete);
			stmtDelete.setInt(1, Tilaus.getId());
			stmtDelete.executeUpdate();
			
			sqlDelete = "DELETE FROM TilattuTuote WHERE Tilaus_id =?";
			stmtDelete = connection.prepareStatement(sqlDelete);
			stmtDelete.setInt(1, Tilaus.getId());
			stmtDelete.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtDelete, connection);

		}
	}
	
	public ArrayList<Tilaus> haeAktiivisetTilaukset() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>();
		Tilaus tilaus = null;
		
		try{
		
		conn = getConnection();
		String sqlSelect = "SELECT ti.tilaus_id AS tilaus_id, ti.tilaus_aika, ti.tilaus_osoite, ti.tilaus_puhnro, ti.status_id, s.status_nimi, k.kayttaja_ktunnus FROM Tilaus ti JOIN Status s ON ti.status_id=s.status_id JOIN Kayttaja k ON ti.kayttaja_id=k.kayttaja_id;";
		stmt = conn.prepareStatement(sqlSelect);
		rs = stmt.executeQuery(sqlSelect);
		
		while (rs.next()) {
			tilaus = readTilaus(rs);
			tilaukset.add(tilaus);
		}
		
		return tilaukset;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		
		
	}

	public ArrayList<Tilaus> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Tilaus> tilaukset = new ArrayList<Tilaus>();
		Tilaus Tilaus = null;
		try {

			conn = getConnection();

			String sqlSelect = "SELECT ti.tilaus_id, ti.tilaus_aika, ti.tilaus_osoite, ti.tilaus_puhnro, ti.status_id, s.status_nimi, k.kayttaja_ktunnus FROM Tilaus ti JOIN Status s ON ti.status_id=s.status_id JOIN Kayttaja k ON ti.kayttaja_id=k.kayttaja_id;";

			stmt = conn.prepareStatement(sqlSelect);

			rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) {
				Tilaus = readTilaus(rs);
				tilaukset.add(Tilaus);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}

		return tilaukset;
	}

	public Tilaus readTilaus(ResultSet rs) {

		try {
			KayttajaDAO KDAO = new KayttajaDAO();
			TuoteDAO TuDAO = new TuoteDAO();
			int id = rs.getInt("tilaus_id");
			Timestamp aika = rs.getTimestamp("tilaus_aika");
			String osoite = rs.getString("tilaus_osoite");
			String puhnro = rs.getString("tilaus_puhnro");
			int statusID= rs.getInt("status_id");
			String statusNimi= rs.getString("status_nimi");
			String kayttajaTunnus = rs.getString("kayttaja_ktunnus");	
			
			Kayttaja kayttaja = KDAO.findByKayttajaTunnus(kayttajaTunnus);
			ArrayList<TilattuTuote> tilatutTuotteet = TuDAO.haeTilatutTuotteet(id);
			
			

			return new Tilaus(id, aika, osoite, puhnro, statusID, statusNimi, kayttaja, tilatutTuotteet);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public Tilaus haeTilaus(int ID) {

		Connection conn = null;
		PreparedStatement stmt = null;
		Tilaus tulos = null;
		try {

			conn = getConnection();

			String sqlSelect = "SELECT tilaus_aika, tilaus_osoite, status_id, kayttaja_id FROM Tilaus WHERE tilaus_id=?;";
			stmt = conn.prepareStatement(sqlSelect);
			stmt.setInt(1, ID);

			tulos = readTilaus(stmt.executeQuery(sqlSelect));

			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt, conn);
		}

		return tulos;
	}
	
	public int haeTilauksenID(String osoite, int KID) {

		Connection conn = null;
		PreparedStatement stmtSelect = null;
		ResultSet rs = null;
		int tulos = 0;
		try {

			conn = getConnection();

			String sqlSelect = "SELECT MAX(tilaus_id) AS MaxTilaus FROM Tilaus WHERE kayttaja_id="+KID+";";
			stmtSelect = conn.prepareStatement(sqlSelect);
			//stmtSelect.setInt(1, KID);
			
			rs = stmtSelect.executeQuery(sqlSelect);

			if (rs.next()) {
			tulos = rs.getInt("MaxTilaus");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtSelect, conn);
		}

		return tulos;
	}
	
	public ArrayList<TilattuTuote> haeTilatutTuotteetIDnPerusteella(int TID) {
		Connection conn = null;
		PreparedStatement stmtSelect = null;
		ResultSet rs = null;
		ArrayList<TilattuTuote> tilatutTuotteet = new ArrayList<TilattuTuote>();
		TilattuTuote tilattuTuote;
		TuoteDAO TuDAO = new TuoteDAO();
		
		try {

			conn = getConnection();

			String sqlSelect = "SELECT tilaus_id, tuote_id, tilaus_rivi, tuote_hinta, lkm, valkosipuli, oregano FROM TilattuTuote WHERE Tilaus_id = ?";
			stmtSelect = conn.prepareStatement(sqlSelect);
			stmtSelect.setInt(1, TID);
			
			rs = stmtSelect.executeQuery(sqlSelect);

			if (rs.next()) {
			tilattuTuote = TuDAO.readTilatutTuotteet(rs);
			tilatutTuotteet.add(tilattuTuote);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtSelect, conn);
		}

		return tilatutTuotteet;
		
	}
	

	

}
