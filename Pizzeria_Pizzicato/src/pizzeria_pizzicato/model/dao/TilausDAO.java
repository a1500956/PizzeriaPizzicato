package pizzeria_pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;

import pizzeria_pizzicato.model.Tilaus;
import pizzeria_pizzicato.model.Tuote;
import pizzeria_pizzicato.model.dao.DataAccessObject;

public class TilausDAO extends DataAccessObject {

	public void addTilaus(Tilaus Tilaus, ArrayList<Tuote> tuotelista) throws SQLException {

		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {

			connection = getConnection();

			String sqlInsert = "INSERT INTO Tilaus(tilaus_osoite, kayttaja_id, tilaus_puhnro) VALUES (?, ?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setString(1, Tilaus.getOsoite());
			stmtInsert.setInt(2, Tilaus.getKayttajaID());
			stmtInsert.setString(3, Tilaus.getPuhnro());
			

			stmtInsert.executeUpdate();
			
			//Valmistellaan tilattujen tuotteiden lisäys
			String sqlInsert2 = "INSERT INTO TilattuTuote(tilaus_id, tuote_id, lkm) VALUES (?,?,?)";
			//Käytetään tilauksen ID-luvun löytävää metodia löytämään viimeisin kyseessä olevan käyttäjän kyseiseen osoitteeseen tekemä tilaus
			int tilauksenID = haeTilauksenID(Tilaus.getOsoite(), Tilaus.getKayttajaID());
			
			//Luodaan kopio listasta, jottei mitään vahingossa kadotettaisi
			Tuote TX;
			
			for (int i = 0; i < tuotelista.size(); i++) {
					TX = tuotelista.get(i);
					stmtInsert = connection.prepareStatement(sqlInsert2);
					//Tässä käytämme aiemmin esillekaivamaamme uusimman ID:n lukua
					stmtInsert.setInt(1, tilauksenID);
					stmtInsert.setInt(2, TX.getId());
					stmtInsert.setInt(3, TX.getLkm());
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
		String sqlSelect = "SELECT ti.tilaus_id, ti.tilaus_aika, s.status_nimi, tu.tuote_nimi, tt.lkm, ti.tilaus_osoite, k.kayttaja_ktunnus, ti.tilaus_puhnro FROM TilattuTuote tt JOIN Tilaus ti ON ti.tilaus_id=tt.tilaus_id JOIN Kayttaja k ON ti.kayttaja_id=k.kayttaja_id JOIN Tuote tu ON tt.tuote_id=tu.tuote_id JOIN Status s ON ti.status_id=s.status_id WHERE s.status_id <= 5 ORDER BY tilaus_id;";
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

			String sqlSelect = "SELECT tilaus_id, tilaus_aika, tilaus_osoite, status_id, kayttaja_id FROM Tilaus;";

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

			int id = rs.getInt("tilaus_id");
			Timestamp aika = rs.getTimestamp("tilaus_aika");
			String osoite = rs.getString("tilaus_osoite");
			String puhnro = rs.getString("tilaus_puhnro");
			int statusID = 0;
			int kayttajaID = 0;
			String statusNimi= rs.getString("status_nimi");
			String tuoteNimi = rs.getString("tuote_nimi");
			int lkm = rs.getInt("lkm");
			String kayttajaTunnus = rs.getString("kayttaja_ktunnus");
			
			

			return new Tilaus(id, aika, osoite, puhnro, statusID, kayttajaID, statusNimi, tuoteNimi, lkm, kayttajaTunnus);
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
	

	

}
