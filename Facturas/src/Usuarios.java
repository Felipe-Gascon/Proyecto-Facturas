
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Usuarios {
	ConexionDB facturas;

	Statement instruccion = null;
	ResultSet conjuntoResultados = null;
	//Consultas SQL
	private static String Lista_usuarios="SELECT * FROM usuarios";

	public Usuarios()
	{
		//creamos la conexion con la Base de Datos
		facturas = ConexionDB.getInstance();
		
	}

	//Recojo los usuarios de la BBDD
	public ArrayList<String> getUsuarios()
	{
		int i=0;
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
				System.out.println(usuarios.get(i)); i++;
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
