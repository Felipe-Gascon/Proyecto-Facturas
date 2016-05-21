import java.sql.ResultSet;
import java.sql.Statement;


public class Registro {
	
	ConexionDB facturas;
	Statement st = null;
	int rs ;
	int resultado;
	public Registro()
	{
		facturas = ConexionDB.getInstance();
	}
	public int InsertaUsuario(String Nombre, String Pasword) throws Exception
	{
		int res;
	if(facturas.conexion!=null){
			this.st =facturas.getConexion().createStatement();
			String query="insert into usuarios (nombre,clave) values('"+Nombre+"','"+Pasword+"')";
			rs = st.executeUpdate(query);
			resultado=1;
			
	}
		
			
	
		
		return resultado;
	}


}
