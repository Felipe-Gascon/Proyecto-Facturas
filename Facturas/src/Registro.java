import java.sql.Statement;


public class Registro {

	ConexionDB conn;
	Statement st = null;
	int rs ;
	int resultado;
	public Registro()
	{
		this.conn = ConexionDB.getInstance();
	}
	
	@SuppressWarnings("static-access")
	public int InsertaUsuario(String Nombre, String Pasword) throws Exception
	{
		
		if(conn.conexion!=null){
			this.st =this.conn.getConexion().createStatement();
			String query="insert into usuarios (nombre,clave) values('"+Nombre+"','"+Pasword+"')";
			this.rs = this.st.executeUpdate(query);
			this.resultado=1;

		}




		return resultado;
	}


}
