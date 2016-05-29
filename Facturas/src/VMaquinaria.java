
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class VMaquinaria extends JFrame {
	public static String supe;
	private JPanel contentPane;
	private DefaultTableModel dtm;
	private String columnas[]={"Trabajos_Maq","Precios","Subtotal"};
	//Object[] datosTabla = new Object[columnas.length];
	private JTable datos;
	private JScrollPane scrollPane;

	private float superficie;
	private JTextField textNom;
	private JTextField textSup;
	private JLabel lblAo;
	private JTextField txtYear; 
	private final JLabel lblNewLabel = new JLabel("Total");
	private JTextField txtTot;
	private VPrecios vp;
	@SuppressWarnings("unused")
	private VMenu vm;


	public VMaquinaria( VMenu vm) {
		this.dtm=new DefaultTableModel();
		this.vm=vm;
		setResizable(false);
		//System.out.println("constructor sense parametres");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);




		this.datos= new JTable(dtm);
		for(int column=0;column<columnas.length;column++)
		{
			//System.out.println("Afegint dades al dtm");
			this.dtm.addColumn(this.columnas[column]);
		}



		JButton btnAadirDatos = new JButton("A\u00F1adir Datos");
		btnAadirDatos.setBounds(118, 227, 103, 23);
		btnAadirDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//obtenerJtxt();

				if(textSup.getText().trim().length()==0){

					JOptionPane.showMessageDialog(null,"Ingrese Superficie");
				}
				else
				{

					superficie=Float.parseFloat(textSup.getText());
					vp=new VPrecios(dtm, superficie,txtTot);

					vp.setVisible(true);

				}
				//	getSuperficie();


			}
		});
		contentPane.setLayout(null);

		scrollPane = new JScrollPane(datos);
		scrollPane.setBounds(5, 85, 419, 131);
		contentPane.add(scrollPane);
		contentPane.add(btnAadirDatos);

		JButton btnEliminarDatos = new JButton("Eliminar Datos");
		btnEliminarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(datos.getRowCount()==0){
					JOptionPane.showMessageDialog(null, "La tabla Esta Vacia");
				}
				else
				{
					dtm.removeRow(datos.getSelectedRow());
					txtTot.setText("0");
				}

			}
		});
		btnEliminarDatos.setBounds(261, 227, 103, 23);
		contentPane.add(btnEliminarDatos);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(5, 11, 56, 23);
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
		txtTot.setEditable(false);
		txtTot.setBounds(286, 43, 86, 20);
		contentPane.add(txtTot);
		txtTot.setColumns(10);
		txtTot.setText("0");

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vm.setVisible(true);
				VMaquinaria.this.dispose();



			}
		});
		btnAtras.setBounds(5, 227, 89, 23);
		contentPane.add(btnAtras);





	}
}
