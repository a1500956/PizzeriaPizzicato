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

			String sqlInsert = "INSERT INTO Tilaus(tilaus_osoite, kayttaja_id) VALUES (?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setString(1, Tilaus.getOsoite());
			stmtInsert.setInt(2, Tilaus.getKayttajaID());

			stmtInsert.executeUpdate();
			
			//Valmistellaan tilattujen tuotteiden lisäys
			String sqlInsert2 = "INSERT INTO TilattuTuote(tilaus_id, tuote_id, lkm) VALUES (?,?,?)";
			
			//Käytetään tilauksen ID-luvun löytävää metodia löytämään viimeisin kyseessä olevan käyttäjän kyseiseen osoitteeseen tekemä tilaus
			int tilauksenID = haeTilauksenID(Tilaus.getOsoite(), Tilaus.getKayttajaID());
			
			//Luodaan kopio listasta, jottei mitään vahingossa kadotettaisi
			ArrayList<Tuote> kasittelykopio = tuotelista;;
			
			for (int i = 0; i < kasittelykopio.size(); i++) {
				int lukumaara = 0;
				Tuote TX = kasittelykopio.get(i);
				
				//Tämä looppi käy läpi listan, laskien saman
				for (int j = 0; j < kasittelykopio.size(); j++) {
					if(TX.getId()==kasittelykopio.get(j).getId()){
						lukumaara++;
						//Poistetaan turhat kopiot, joiden olemassaolo on jo laskettu. Tämän takia me käytämme kopiota, lapset, ettei pahin tapahtuisi.
						kasittelykopio.remove(j);
					}
					
					stmtInsert = connection.prepareStatement(sqlInsert2);
					//Tässä käytämme aiemmin esillekaivamaamme uusimman ID:n lukua
					stmtInsert.setInt(tilauksenID, 1);
					stmtInsert.setInt(TX.getId(), 2);
					stmtInsert.setInt(lukumaara, 3);
					stmtInsert.executeUpdate();	
				}
				
					
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


	public void deleteTilaus(Tilaus Tilaus) throws SQLException {
		Connection connection = null;

		PreparedStatement stmtDelete = null;

		try {

			connection = getConnection();

			String sqlDelete = "DELETE FROM Tilaus WHERE Tilaus_id =?";
			stmtDelete = connection.prepareStatement(sqlDelete);
			stmtDelete.setInt(1, Tilaus.getId());
			stmtDelete.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtDelete, connection);

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
			Timestamp nimi = rs.getTimestamp("tilaus_aika");
			String osoite = rs.getString("tilaus_osoite");
			int statusID = rs.getInt("status_id");
			int kayttajaID = rs.getInt("kayttaja_id");

			return new Tilaus(id, nimi, osoite, statusID, kayttajaID);
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
		PreparedStatement stmt = null;
		int tulos = 0;
		try {

			conn = getConnection();

			String sqlSelect = "SELECT tilaus_id FROM Tilaus WHERE tilaus_osoite = ? AND kayttaja_id = ?";
			stmt = conn.prepareStatement(sqlSelect);
			stmt.setString(1, osoite);
			stmt.setInt(2, KID);
			
			ResultSet rs = stmt.executeQuery(sqlSelect);
			
			int verrokki = 0;
			int tutkittavaID = 0;
			
			while (rs.next()) {
				tutkittavaID = rs.getInt("tilaus_id");
				if(tutkittavaID>=verrokki){
					tulos=tutkittavaID;
				}
				verrokki = tutkittavaID;
				
			}	

			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmt, conn);
		}

		return tulos;
	}
	

	

}
