
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;


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
	@SuppressWarnings("unused")
	private static String Trabajos = "Trabajos_Maq";
	@SuppressWarnings("unused")
	private static String Coste = "Precios";
	private JTextField textNom;
	private JTextField textSup;
	private JLabel lblAo;
	private JTextField txtYear;
	private final JLabel lblNewLabel = new JLabel("Total");
	private JTextField txtTot;



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
		scrollPane.setBounds(5, 85, 419, 131);
		contentPane.add(scrollPane);
		contentPane.add(btnAadirDatos);

		JButton btnEliminarDatos = new JButton("Eliminar Datos");
		btnEliminarDatos.setBounds(158, 227, 103, 23);
		contentPane.add(btnEliminarDatos);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(316, 227, 108, 23);
		contentPane.add(btnModificar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(5, 11, 47, 23);
		contentPane.add(lblNombre);
		
		textNom = new JTextField();
		textNom.setBounds(60, 12, 86, 20);
		contentPane.add(textNom);
		textNom.setColumns(10);
		
		JLabel lblSuperficie = new JLabel("Superficie");
		lblSuperficie.setBounds(207, 11, 78, 23);
		contentPane.add(lblSuperficie);
		
		textSup = new JTextField();
		textSup.setBounds(286, 12, 86, 20);
		contentPane.add(textSup);
		textSup.setColumns(10);
		
		lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(5, 45, 32, 23);
		contentPane.add(lblAo);
		
		txtYear = new JTextField();
		txtYear.setBounds(60, 45, 86, 20);
		contentPane.add(txtYear);
		txtYear.setColumns(10);
		lblNewLabel.setBounds(217, 45, 47, 20);
		contentPane.add(lblNewLabel);
		
		txtTot = new JTextField();
		txtTot.setBounds(286, 43, 86, 20);
		contentPane.add(txtTot);
		txtTot.setColumns(10);

		for(int column=0;column<Columnas.length;column++)
		{
			//System.out.println("Afegint dades al dtm");
			this.dtm.addColumn(Columnas[column]);
		}
		muestraDatos(dtm);
		
		datos.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent Mouse_evt)
			{
				JTable tabla = (JTable) Mouse_evt.getSource();
				Point point = Mouse_evt.getPoint();
				int row = tabla.rowAtPoint(point);
				if(Mouse_evt.getClickCount()==2)
				{
					String valor;
					valor =  (String) datos.getValueAt(datos.getSelectedRow(), 1);
					System.out.println(valor);
				}
			}
		});

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
