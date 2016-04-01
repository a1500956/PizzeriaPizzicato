package pizzeria_pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import pizzeria_pizzicato.model.Kayttaja;

public class KayttajaDAO extends DataAccessObject {

	private static KayttajaDAO instance = new KayttajaDAO();

	   public static KayttajaDAO getInstance() {
	      return instance;
	   }

	   private Kayttaja read(ResultSet rs) throws SQLException
	   {
	      int kayttaja_id = new Integer(rs.getInt("kayttaja_id"));
	      //int kayttaja_id = rs.getInt("kayttaja_id");
	      String kayttaja_nimi = rs.getString("knimi");
	      String kayttaja_enimi = rs.getString("enimi");
	      String kayttaja_snimi = rs.getString("snimi");
	      String kayttaja_salasana = rs.getString("salasana");
	      int kayttajaryhma_id = rs.getInt("kryhma_id");
	      int puhnro = rs.getInt("puhnro");
	      
	      
	      Kayttaja kayttaja = new Kayttaja();
	      kayttaja.setKayttaja_id(kayttaja_id);
	      kayttaja.setKayttaja_nimi(kayttaja_nimi);
	      kayttaja.setKayttaja_enimi(kayttaja_enimi);
	      kayttaja.setKayttaja_snimi(kayttaja_snimi);
	      kayttaja.setKayttaja_salasana(kayttaja_salasana);
	      kayttaja.setKayttajaryhma_id(kayttajaryhma_id);
	      kayttaja.setPuhnro(puhnro);
	      return kayttaja;
	   }
	 
	   public Kayttaja find(Kayttaja kayttaja)
	   {
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from Kayttajat where kayttaja_id=?";
	         statement = connection.prepareStatement(sql);
	         statement.setInt(1, kayttaja.getKayttaja_id());
	         rs = statement.executeQuery();
	         if (!rs.next())
	         {
	            return null;
	         }
	         return read(rs);
	      }
	      catch (SQLException e)
	      {
	         throw new RuntimeException(e);
	      }
	      finally
	      {
	         close(rs, statement, connection);
	      }
	   }
	   
	   public Kayttaja findByKayttajanimi(String kayttaja_nimi)
	   {
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from Kayttajat where knimi=?";
	         statement = connection.prepareStatement(sql);
	         statement.setString(1, kayttaja_nimi);
	         rs = statement.executeQuery();
	         if (!rs.next())
	         {
	            return null;
	         }
	         return read(rs);
	      }
	      catch (SQLException e)
	      {
	         throw new RuntimeException(e);
	      }
	      finally
	      {
	         close(rs, statement, connection);
	      }
	   }
	   
	   public List<Kayttaja> findAll() 
	   {
	      LinkedList<Kayttaja> kayttajat = new LinkedList<Kayttaja>();
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from Kayttaja order by kayttaja_id";
	         statement = connection.prepareStatement(sql);
	         rs = statement.executeQuery();
	         while (rs.next())
	         {
	            Kayttaja kayttaja = read(rs);
	            kayttajat.add(kayttaja);
	         }
	         return kayttajat;
	      }
	      catch (SQLException e)
	      {
	         throw new RuntimeException(e);
	      }
	      finally
	      {
	         close(rs, statement, connection);
	      }
	   }

	   public void update(Kayttaja kayttaja)
	   {
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "update Kayttajat set " + "salasana=?, KRyhma_id=? where kayttaja_id=?";
	         statement = connection.prepareStatement(sql);
	         statement.setString(1, kayttaja.getKayttaja_salasana());
	         statement.setInt(2, kayttaja.getKayttajaryhma_id());
	         statement.setInt(3, kayttaja.getKayttaja_id());
	         statement.executeUpdate();
	      } catch (SQLException e)
	      {
	         throw new RuntimeException(e);
	      } finally
	      {
	         close(statement, connection);
	      }
	   }
	   
	   public void create(Kayttaja kayttaja)
	   {
	      int id = 0;
	      int kryhma_id = 0;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "insert into Kayttajat " + "(kayttaja_id, knimi, enimi, snimi, salasana, kryhma_id, puhnro, osoite) "

	               + "values (?, ?, ?, ?, ?, ?, ?, ?)";
	         statement = connection.prepareStatement(sql);
	         statement.setInt(1, id);
	         statement.setString(2, kayttaja.getKayttaja_nimi());
	         statement.setString(3, kayttaja.getKayttaja_enimi());
	         statement.setString(4, kayttaja.getKayttaja_snimi());
	         statement.setString(5, kayttaja.getKayttaja_salasana());
	         statement.setInt(6, kryhma_id);
	         statement.setInt(7, kayttaja.getPuhnro());
	         statement.setString(8, kayttaja.getOsoite());
	         statement.executeUpdate();
	      } catch (SQLException e)
	      {
	         throw new RuntimeException(e);
	      } finally
	      {
	         close(statement, connection);
	      }
	   }
	   
	   public void delete(Kayttaja kayttaja)
	   {
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "delete from Kayttajat where kayttaja_id=?";
	         statement = connection.prepareStatement(sql);
	         int kayttaja_id = kayttaja.getKayttaja_id();
	         statement.setInt(1, kayttaja_id);
	         statement.executeUpdate();
	      } catch (SQLException e)
	      {
	         throw new RuntimeException(e);
	      } finally
	      {
	         close(statement, connection);
	      }
	   }
	}