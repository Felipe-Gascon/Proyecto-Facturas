import java.sql.Statement;


public class Registro {

	private ConexionDB conn;
	public int rs;
	private	int resultado;
	public Registro()
	{
		this.conn = ConexionDB.getInstance();
	}

	@SuppressWarnings("static-access")
	public int insertaUsuario(String Nombre, String Pasword) throws Exception
	{
		Statement st = null;
		if(this.conn.conexion!=null){
			st =this.conn.getConexion().createStatement();
			String query="insert into usuarios (nombre,clave) values('"+Nombre+"','"+Pasword+"')";
			this.rs = st.executeUpdate(query);
			this.resultado=1;

		}




		return resultado;
	}



}
