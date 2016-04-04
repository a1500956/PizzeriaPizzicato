package pizzeria_pizzicato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	      String kayttaja_ktunnus = rs.getString("kayttaja_ktunnus");
	      String kayttaja_enimi = rs.getString("kayttaja_enimi");
	      String kayttaja_snimi = rs.getString("kayttaja_snimi");
	      String kayttaja_osoite = rs.getString("kayttaja_osoite");
	      String kayttaja_sposti = rs.getString("kayttaja_sposti");
	      String kayttaja_salasana = rs.getString("kayttaja_salasana");
	      int ryhma_id = rs.getInt("ryhma_id");
	      String kayttaja_puhnro = rs.getString("kayttaja_puhnro");
	      
	      
	      Kayttaja kayttaja = new Kayttaja();
	      kayttaja.setKayttaja_id(kayttaja_id);
	      kayttaja.setKayttaja_ktunnus(kayttaja_ktunnus);
	      kayttaja.setKayttaja_enimi(kayttaja_enimi);
	      kayttaja.setKayttaja_snimi(kayttaja_snimi);
	      kayttaja.setKayttaja_osoite(kayttaja_osoite);
	      kayttaja.setKayttaja_sposti(kayttaja_sposti);
	      kayttaja.setKayttaja_salasana(kayttaja_salasana);
	      kayttaja.setRyhma_id(ryhma_id);
	      kayttaja.setKayttaja_puhnro(kayttaja_puhnro);
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
	         String sql = "select * from Kayttaja where kayttaja_id=?";
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
	   
	   public Kayttaja findByKayttajaTunnus(String kayttaja_ktunnus)
	   {
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "select * from Kayttaja where kayttaja_ktunnus=?";
	         statement = connection.prepareStatement(sql);
	         statement.setString(1, kayttaja_ktunnus);
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
	   
	   public ArrayList<Kayttaja> findAll() 
	   {
	      ArrayList<Kayttaja> kayttajat = new ArrayList<Kayttaja>();
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
	         String sql = "update Kayttaja set " + "kayttaja_salasana=?, ryhma_id=? where kayttaja_id=?";
	         statement = connection.prepareStatement(sql);
	         statement.setString(1, kayttaja.getKayttaja_salasana());
	         statement.setInt(2, kayttaja.getRyhma_id());
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
	      int kayttaja_id = 0;
	      int ryhma_id = 0;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "insert into Kayttajat " + "(kayttaja_id, kayttaja_ktunnus, kayttaja_enimi, kayttaja_snimi, kayttaja_salasana, kryhma_id, kayttaja_sposti, kayttaja_osoite) "

	               + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	         statement = connection.prepareStatement(sql);
	         statement.setInt(1, kayttaja_id);
	         statement.setString(2, kayttaja.getKayttaja_ktunnus());
	         statement.setString(3, kayttaja.getKayttaja_enimi());
	         statement.setString(4, kayttaja.getKayttaja_snimi());
	         statement.setString(5, kayttaja.getKayttaja_puhnro());
	         statement.setString(6, kayttaja.getKayttaja_salasana());
	         statement.setInt(7, ryhma_id);
	         statement.setString(8, kayttaja.getKayttaja_sposti());
	         statement.setString(9, kayttaja.getKayttaja_osoite());
	         statement.executeUpdate();
	      } catch (SQLException e)
	      {
	         throw new RuntimeException(e);
	      } finally
	      {
	         close(statement, connection);
	      }
	   }
	   
	   public Kayttaja login(String kayttaja_ktunnus, String kayttaja_salasana)
	   {
	      ResultSet rs = null;
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "SELECT * from Kayttaja where kayttaja_ktunnus=? AND kayttaja_salasana=?";
	         statement = connection.prepareStatement(sql);
	         statement.setString(1, kayttaja_ktunnus);
	         statement.setString(2, kayttaja_salasana);
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
	   
	   public void delete(Kayttaja kayttaja)
	   {
	      PreparedStatement statement = null;
	      Connection connection = null;
	      try
	      {
	         connection = getConnection();
	         String sql = "delete from Kayttaja where kayttaja_id=?";
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