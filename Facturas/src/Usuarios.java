
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Usuarios {
	private	ConexionDB conn;

	//Consultas SQL

	private static String Lista_usuarios="SELECT * FROM usuarios";



	public Usuarios()
	{
		//creamos la conexion con la Base de Datos
		this.conn = ConexionDB.getInstance();

	}

	public boolean existe (String usuario, String password) throws Exception

	{
		Statement instruccion=null;
		ResultSet conjuntoResultados = null;

		 instruccion= this.conn.getConexion().createStatement();
		 conjuntoResultados = instruccion.executeQuery("SELECT COUNT(id) as total FROM usuarios WHERE nombre='"+usuario+"' AND clave='"+password+"'");
		if(conjuntoResultados.next())
		{
			int total = conjuntoResultados.getInt("total");
			if(total==1)
			{
				return true;
			}
		}
		return false;
	}


	public ArrayList<String> getPassword()
	{
		ArrayList<String> pass = new ArrayList<String>();

		//preparo la consulta
		Statement instruccion = null;
		ResultSet conjuntoResultados=null;
		try{
			 instruccion=this.conn.getConexion().createStatement();
		}
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try{
			conjuntoResultados = instruccion.executeQuery("Select clave from usuarios");
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Recorremos los resultados y los almacenamos en un ArrayList
		try {
			while(conjuntoResultados.next())
			{
				try{
					pass.add(conjuntoResultados.getString("clave"));
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return pass;
	}

	//Recojo los usuarios de la BBDD
	public ArrayList<String> getUsuarios()
	{
		ArrayList<String> usuarios = new ArrayList<String>();
		Statement instruccion=null;
		ResultSet conjuntoResultados = null;

		try {
			System.out.println("usuarios bbdd");
			//preparo la consulta
			instruccion = this.conn.getConexion().createStatement();
			conjuntoResultados = instruccion.executeQuery(Lista_usuarios);
			//Recorremos los resultados y los almacenamos en un ArrayList
			while(conjuntoResultados.next())
			{
				usuarios.add(conjuntoResultados.getString("nombre"));

			}
		}catch( SQLException excepcionSql ) {
			excepcionSql.printStackTrace();
		}	
		finally{
			try{
				conjuntoResultados.close();
				instruccion.close();
			}
			catch( SQLException excepcionSql ) 
			{
				excepcionSql.printStackTrace();
			}
		}
		return usuarios;

	}


}
