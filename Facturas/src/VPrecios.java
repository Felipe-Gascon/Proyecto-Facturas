import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class VPrecios extends JFrame {

	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	JComboBox comboTrabajos;



	@SuppressWarnings("rawtypes")
	Hashtable trabajos;
	DefaultTableModel dtm2;
	VMaquinaria vm ;
	Float superficie;
	Maquinaria ma;
	JTextField total;

	/**
	 * Launch the application.
	 */


	@SuppressWarnings("rawtypes")
	public VPrecios(DefaultTableModel miDTM, float sup,JTextField tot) {
		total=tot;
		dtm2=miDTM;
		superficie=sup;
		ma = new Maquinaria();
		trabajos = ma.getTrabajos();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboTrabajos = new JComboBox();

		comboTrabajos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//  String tarea = comboTrabajos.getSelectedItem().toString();
				// System.out.println(tarea+ "\t "+ trabajos.get(tarea));


			}
		});   
		comboTrabajos.setBounds(10, 63, 331, 20);
		contentPane.add(comboTrabajos);



		JLabel lblTrabajos = new JLabel("Trabajos");
		lblTrabajos.setBounds(10, 33, 161, 20);
		contentPane.add(lblTrabajos);




		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tareas = comboTrabajos.getSelectedItem().toString();
				String value = String.valueOf (trabajos.get(tareas));
				float valor = Float.parseFloat(value);

				float subtotal = valor*VPrecios.this.superficie;
				Object filas[] = new Object[3];
				filas[0]=tareas;
				filas[1]=valor;
				filas[2]=subtotal;

				dtm2.addRow(filas);
				acumularTotal();
				VPrecios.this.dispose();
			}
		});
		btnNewButton.setBounds(118, 219, 89, 31);
		contentPane.add(btnNewButton);
		ponTrabajos();
		//copiarTexto();

	}


	@SuppressWarnings("unchecked")
	public void ponTrabajos( )
	{
		String clave;

		Enumeration<String> claves = trabajos.keys();

		while(claves.hasMoreElements())
		{
			clave = claves.nextElement();
			//System.out.println(clave);
			comboTrabajos.addItem(clave);


		}


	}

	public void acumularTotal() {
		double tota = 0;
		double subtotal;
		for (int i = 0; i < dtm2.getRowCount(); i++) {
			subtotal = Double.parseDouble(dtm2.getValueAt(i, 2).toString());
			tota = tota + subtotal;
			
		}
		String valorTotal=String.valueOf(tota);
		VPrecios.this.total.setText(valorTotal);
	}
}