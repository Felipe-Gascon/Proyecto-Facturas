import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;


public class Maquinaria {
	ConexionDB facturas;

	Statement st = null;
	ResultSet rs = null;

	public Maquinaria() {
		this.facturas = ConexionDB.getInstance();

	}

	public Hashtable<String,Float> getTrabajos()
	{
	     Hashtable<String,Float> tareasMaquinariaXPrecios=new Hashtable<String, Float>();
		
		//preparo la consulta
		try{
			this.st=facturas.getConexion().createStatement();
			this.rs=st.executeQuery("Select Trabajos_Maq, Precios from maquinaria");

		}
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try	{
			while(rs.next())
			{
				try{
					tareasMaquinariaXPrecios.put(rs.getString("Trabajos_Maq"),rs.getFloat("Precios"));
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}

		}
		catch(SQLException e)
		{
  			e.printStackTrace();
		}
		return tareasMaquinariaXPrecios;
	}
	
	public ArrayList<String> getPrecios()
	{
		ArrayList<String> precios = new ArrayList<String>();
		//preparo la consulta
		try{
			this.st=facturas.getConexion().createStatement();
			this.rs=st.executeQuery("Select Precios from maquinaria");

		}
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try	{
			while(rs.next())
			{
				try{
					precios.add(rs.getString("Precios"));
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return precios;
	}

}

