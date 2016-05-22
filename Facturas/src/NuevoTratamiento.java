import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NuevoTratamiento extends JFrame {

	private JPanel contentPane;
	private Hashtable tratamientos;
	private float superficie;
	private Tratamientos trat;
	private JTextField total;
	private DefaultTableModel dtm2;
	private JComboBox comboTratamiento;
	private JButton Guardar;
	public NuevoTratamiento(DefaultTableModel miDtm, float superficie, JTextField tot) {
		this.dtm2=miDtm;
		this.superficie=superficie;
		this.total=tot;
		this.trat= new Tratamientos();
		this.tratamientos=trat.getTratamientos();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		 comboTratamiento = new JComboBox();
		comboTratamiento.setBounds(10, 93, 367, 20);
		contentPane.add(comboTratamiento);

		JLabel lblNuevoTratamiento = new JLabel("Nuevo Tratamiento");
		lblNuevoTratamiento.setBounds(143, 50, 126, 14);
		contentPane.add(lblNuevoTratamiento);
		
		Guardar = new JButton("Guardar");
		Guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tratamiento = comboTratamiento.getSelectedItem().toString();
				String value= String.valueOf(tratamientos.get(tratamiento));
				float valor = Float.parseFloat(value);
				
				float subtotal = valor*superficie;
				
				Object datos []= new Object[3];
				datos[0]=tratamiento;
				datos[1]=valor;
				datos[2]=subtotal;
				miDtm.addRow(datos);
				NuevoTratamiento.this.dispose();
				acumularTotal();
				
				
			}
		});
		Guardar.setBounds(161, 213, 89, 37);
		contentPane.add(Guardar);
		
		ponTratamientos();
	}
	
	public void ponTratamientos()
	{
		String clave;
		Enumeration<String> claves=tratamientos.keys();
		
		while(claves.hasMoreElements())
		{
			clave= claves.nextElement();
			comboTratamiento.addItem(clave);
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
		NuevoTratamiento.this.total.setText(valorTotal);
	}
}
