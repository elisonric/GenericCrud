package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Connector {
	
	static String driver = "org.postgresql.Driver";
	static String user = "postgres";
	static String senha = "postgres";
	static String url = "jdbc:postgresql://127.0.0.1:5432/teste";
	static Connection con;
	
	 private Connector() {
		 try{
			 Class.forName(driver);
			 con = (Connection) DriverManager.getConnection(url, user, senha);
		 } catch (ClassNotFoundException ex) {
			 System.err.print(ex.getMessage());
		 } catch (SQLException e) {
			 System.err.print(e.getMessage());
		 }
	 }
	 
	 public static Connection getConexao() {
		 if (Objects.nonNull(con)) {
			 return con;
		 } else {
			@SuppressWarnings("unused")
			Connector connector = new Connector();
			 return Connector.con;
		 }
		 
	 }
}
