import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Registro {
	
	ConexionDB facturas;
	Statement st = null;
	ResultSet rs = null;
	
	public Registro()
	{
		facturas = ConexionDB.getInstance();
	}
	public int InsertaUsuario(String Nombre, String Pasword) throws Exception
	{
		int resultado=1;
	
			st = facturas.getConexion().createStatement();
			String query="insert into usuarios (nombre,clave) values('"+Nombre+"','"+Pasword+"')";
			rs = st.executeQuery(query);
		
			
	
		
		return resultado;
	}


}
