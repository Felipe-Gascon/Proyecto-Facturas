import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;


public class General {

	private static General instance = null;
	ConexionDB facturas;
	DefaultTableModel dtm;
	private VIngreso vR;

	@SuppressWarnings("unused")
	private VMaquinaria vm;

	private General() 
	{
		facturas=ConexionDB.getInstance("localhost", "facturas", "Felipe", "");

		if(facturas.connectDB()==true)
		{
			System.out.println("Conexion Exitosa");
			muestraVentana();
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
