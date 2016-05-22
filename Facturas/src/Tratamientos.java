import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;


public class Tratamientos {

	private ConexionDB con;
	private Statement st = null;
	private ResultSet rs= null;

	public Tratamientos() {
		this.con=ConexionDB.getInstance();
	}


	public Hashtable<String, Float> getTratamientos()
	{
		Hashtable<String, Float> tratamientosYprecios = new Hashtable<String, Float>();

		//preparo la consulta
		try
		{
			this.st=con.getConexion().createStatement();
			this.rs=st.executeQuery("Select Tratamiento, Precios from tratamientos");
		}
		catch(SQLException sq)
		{
			sq.printStackTrace();
		}
		try{
			//recorremos los resultados de la consulta y se almcanenan en un hashTable
			while(rs.next())
			{
				try
				{
					tratamientosYprecios.put(rs.getString("Tratamiento"), rs.getFloat("Precios"));
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			
		}
		catch(SQLException e2)
		{
			e2.printStackTrace();
		}
		return tratamientosYprecios;
	
	}

}
