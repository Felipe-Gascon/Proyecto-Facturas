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
public class NuevoTratamiento extends JFrame {

	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private Hashtable tratamientos;
	@SuppressWarnings("unused")
	private float superficie;
	private Tratamientos trat;
	private JTextField total;
	private DefaultTableModel dtm;
	@SuppressWarnings("rawtypes")
	private JComboBox comboTratamiento;
	private JButton Guardar;
	@SuppressWarnings("rawtypes")
	public NuevoTratamiento(DefaultTableModel modelo, float superficie, JTextField tot) {
		this.dtm=modelo;
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
				modelo.addRow(datos);
				NuevoTratamiento.this.dispose();
				acumularTotal();
				
				
			}
		});
		Guardar.setBounds(161, 213, 89, 37);
		contentPane.add(Guardar);
		
		ponTratamientos();
	}
	
	@SuppressWarnings("unchecked")
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
		for (int i = 0; i < dtm.getRowCount(); i++) {
			subtotal = Double.parseDouble(dtm.getValueAt(i, 2).toString());
			tota = tota + subtotal;

		}
		String valorTotal=String.valueOf(tota);
		NuevoTratamiento.this.total.setText(valorTotal);
	}
}
