import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Maquinaria {
	ConexionDB facturas;

	Statement st = null;
	ResultSet rs = null;

	public Maquinaria() {
		facturas = ConexionDB.getInstance();
	
	}
	
	public ResultSet consulta()
	{
		Statement st= null;
		ResultSet rs= null;
		
		//realizamos la consulta 
		
		
			try {
				st = facturas.getConexion().createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				rs= st.executeQuery("Select * from maquinaria");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//devolvemos el resultado
			return rs;
		
	}
	
	

}
