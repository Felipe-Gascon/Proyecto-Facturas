import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;


public class General {

	private static General instance = null;
	private ConexionDB conn;
	public DefaultTableModel dtm;
	private VIngreso vR;
	private General() 
	{
		this.conn=ConexionDB.getInstance("localhost", "facturas", "Felipe", "");

		if(this.conn.connectDB()==true)
		{
			System.out.println("Conexion Exitosa");
			this.muestraVentana();
		}
		else
		{
			System.out.println("Error en la Conexion");
		}

	}

	//Implementar SingleTon
	public static General getInstance()
	{
		if (instance==null)
		{
			instance=new General();

		}
		return instance; 
	}

	//Lanzar vista Pirincipal
	public void muestraVentana()
	{
		Usuarios usu=new Usuarios();
		ArrayList<String> usuarios = usu.getUsuarios();
		
		vR = new VIngreso(usuarios);
		vR.setVisible(true);
	}
	
	
}
