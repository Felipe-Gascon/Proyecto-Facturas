
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;





public class ConexionDB {
	
	static final String CONTROLADOR_MYSQL= "com.mysql.jdbc.Driver";

	//DATOS DE LA BBDD
	private String host;
	private String bbdd;
	private String user;
	private String pass;
	private String url;

	//DATOS POR DEFECTO
	private static final String HOST="localhost";
	private static final String BBDD="corredores";
	private static final String USER="root";
	private static final String PASS="";

	//Conexion
	static Connection conexion;// maneja la conexió

	//Instancia unica
	private static ConexionDB instance = null;

	private ConexionDB(String HOST,String BBDD,String USER,String PASS)
	{
		this.host=HOST;
		this.bbdd=BBDD;
		this.user=USER;
		this.pass=PASS;
		this.url="jdbc:mysql://"+this.host+"/"+this.bbdd;
	}

	//Implementar SingleTon
	public static ConexionDB getInstance(String HOST,String BBDD,String USER,String PASS) 
	{
		if(instance == null)
		{
			instance = new ConexionDB(HOST,BBDD,USER,PASS);
		}
		return instance;
	}

	public static ConexionDB getInstance()
	{
		if(instance == null) 
		{
			instance = new ConexionDB(ConexionDB.HOST,ConexionDB.BBDD,ConexionDB.USER,ConexionDB.PASS);
		}
		return instance;
	}

	public boolean connectDB(){
		try{
			//Lo primero es cargar el controlador MySQL el cual automáticamente se registra
			Class.forName(CONTROLADOR_MYSQL);
			//Conectarnos a la BBDD
			conexion = DriverManager.getConnection(this.url,this.user,this.pass);
		}
		catch( SQLException excepcionSql ) 
		{
			excepcionSql.printStackTrace();
			return false;
		}
		catch( ClassNotFoundException noEncontroClase)
		{
			noEncontroClase.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("static-access")
	public Connection getConexion(){
		return this.conexion;
	}
	
	public static void cierraConexion()
	{
		try
		{
			conexion.close();
		}
		catch(Exception e)
		{
			System.out.println("Error al intentar cerrar la conexion");
		}
		
	}
	
	
}