
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class vMaquinaria extends JFrame {

	private JPanel contentPane;
	DefaultTableModel dtm=null;
	String Columnas[]={"Trabajos_Maq","Precios","Subtotal"};
	Object[] datosTabla = new Object[Columnas.length];
	JTable datos;
	JScrollPane scrollPane;
	Maquinaria ma = new Maquinaria();
	ConexionDB facturas;
	private static String Trabajos = "Trabajos_Maq";
	private static String Coste = "Precios";



	public vMaquinaria(DefaultTableModel dtm2) {
		setResizable(false);
		//System.out.println("constructor sense parametres");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		if (dtm2==null){
			dtm=new DefaultTableModel();
		}else{
			dtm=dtm2;
		}

		JButton btnAadirDatos = new JButton("A\u00F1adir Datos");
		btnAadirDatos.setBounds(5, 227, 103, 23);
		btnAadirDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.setLayout(null);
		datos= new JTable(dtm);

		scrollPane = new JScrollPane(datos);
		scrollPane.setBounds(5, 60, 419, 156);
		contentPane.add(scrollPane);
		contentPane.add(btnAadirDatos);

		JButton btnEliminarDatos = new JButton("Eliminar Datos");
		btnEliminarDatos.setBounds(158, 227, 103, 23);
		contentPane.add(btnEliminarDatos);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(316, 227, 108, 23);
		contentPane.add(btnModificar);

		for(int column=0;column<Columnas.length;column++)
		{
			//System.out.println("Afegint dades al dtm");
			this.dtm.addColumn(Columnas[column]);
		}
		muestraDatos(dtm);

	}

	public void muestraDatos(DefaultTableModel miDTM)
	{
		ResultSet rs = ma.consulta();

		if (rs!=null)
		{
			try
			{
				while(rs.next())
				{
					Object filas[] = new Object[2];
					filas[0] = rs.getString("Trabajos_Maq");
					filas[1] = rs.getString("Precios");
					miDTM.addRow(filas);
				}
			}
			catch (SQLException e) {
				// 
				e.printStackTrace();
			}
			finally
			{
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}


}
