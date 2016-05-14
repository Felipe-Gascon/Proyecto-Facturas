import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Maquinaria {
	ConexionDB facturas;

	Statement instruccion = null;
	ResultSet conjuntoResultados = null;

	public Maquinaria() {
		facturas = ConexionDB.getInstance();
	
	}
	
	public ArrayList<String> getDatos()
	{
		ArrayList<String>  contraseñas = new ArrayList<String>();
		
		return contraseñas;
	}

}
