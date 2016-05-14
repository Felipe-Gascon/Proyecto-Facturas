
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Usuarios {
	ConexionDB facturas;

	Statement instruccion = null;
	ResultSet conjuntoResultados = null;
	//Consultas SQL
	vRegistro vr ;
	 
	private static String Lista_usuarios="SELECT * FROM usuarios";
	
	

	public Usuarios()
	{
		//creamos la conexion con la Base de Datos
		facturas = ConexionDB.getInstance();

	}
	public ArrayList<String> getContraseña()
	{
		ArrayList<String> contraseñas = new ArrayList<String>();
		
		//preparo la consulta
		try {
			instruccion = facturas.getConexion().createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			conjuntoResultados = instruccion.executeQuery("Select Contraseña from usuarios");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Recorremos los resultados y los almacenamos en un ArrayList
		try {
			while(conjuntoResultados.next())
			{
				try {
					contraseñas.add(conjuntoResultados.getString("Contraseña"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return contraseñas;
		

}


	//Recojo los usuarios de la BBDD
	public ArrayList<String> getUsuarios()
	{
		ArrayList<String> usuarios = new ArrayList<String>();
		try {
			System.out.println("usuarios bbdd");
			//preparo la consulta
			instruccion = facturas.getConexion().createStatement();
			conjuntoResultados = instruccion.executeQuery(Lista_usuarios);
			//Recorremos los resultados y los almacenamos en un ArrayList
			while(conjuntoResultados.next())
			{
				usuarios.add(conjuntoResultados.getString("Nombre"));
				
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
