import java.awt.Font;
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
public class VTratamiento extends JFrame {

	private JPanel contentPane;
	private JTextField textNom;
	private JTextField textEdad;
	private JTextField textSup;
	private JTextField textTot;
	private JTable tratamientos;
	private DefaultTableModel dtm;
	private String col[]={"Tratamiento","Precios","Subtotal"};
	private float superficie;
	private NuevoTratamiento nt ;
	private JScrollPane scrollPane;
	@SuppressWarnings("unused")
	private VMenu vm;

	public VTratamiento(VMenu vm) {

		this.dtm=new DefaultTableModel();



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 68, 14);
		contentPane.add(lblNombre);

		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(10, 49, 46, 14);
		contentPane.add(lblAo);

		this.tratamientos= new JTable(dtm);

		scrollPane = new JScrollPane(tratamientos);
		scrollPane.setBounds(5, 85, 419, 131);
		contentPane.add(scrollPane);



		textNom = new JTextField();
		textNom.setBounds(66, 8, 86, 20);
		contentPane.add(textNom);
		textNom.setColumns(10);

		textEdad = new JTextField();
		textEdad.setBounds(66, 46, 86, 20);
		contentPane.add(textEdad);
		textEdad.setColumns(10);

		JLabel lblSuperficie = new JLabel("Superficie");
		lblSuperficie.setBounds(231, 11, 68, 14);
		contentPane.add(lblSuperficie);

		textSup = new JTextField();
		textSup.setBounds(309, 8, 86, 20);
		contentPane.add(textSup);
		textSup.setColumns(10);

		textTot = new JTextField();
		textTot.setEditable(false);
		textTot.setBounds(309, 46, 86, 20);
		contentPane.add(textTot);
		textTot.setColumns(10);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotal.setBounds(231, 49, 46, 14);
		contentPane.add(lblTotal);

		JButton btnNuevoTrat = new JButton("Nuevo Trat");
		btnNuevoTrat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(textSup.getText().trim().length()==0)
				{
					JOptionPane.showMessageDialog(null,"Ingrese Superficie");
				}
				else
				{
					superficie=Float.parseFloat(textSup.getText());
					nt= new NuevoTratamiento(dtm, superficie, textTot);

					nt.setVisible(true);

				}
			}
		});
		btnNuevoTrat.setBounds(306, 246, 89, 23);
		contentPane.add(btnNuevoTrat);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vm.setVisible(true);
				VTratamiento.this.dispose();
			}
		});
		btnAtras.setBounds(10, 246, 89, 23);
		contentPane.add(btnAtras);

		JButton btnEliminaFila = new JButton("Elimina Fila");
		btnEliminaFila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tratamientos.getRowCount()==0){
					JOptionPane.showMessageDialog(null, "La tabla Esta Vacia");
				}
				else
				{
					dtm.removeRow(tratamientos.getSelectedRow());
					textTot.setText("0");
				}
			}
		});
		btnEliminaFila.setBounds(133, 246, 118, 23);
		contentPane.add(btnEliminaFila);

		for(int columna=0;columna<this.col.length;columna++)
		{
			this.dtm.addColumn(this.col[columna]);
		}
	}
}
