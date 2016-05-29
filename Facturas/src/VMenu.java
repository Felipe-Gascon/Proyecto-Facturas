import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



@SuppressWarnings("serial")
public class VMenu extends JFrame {

	private JPanel contentPane;
	private VTratamiento vt;
	private VMaquinaria vm;


	public VMenu(DefaultTableModel dtm) {

		vt= new VTratamiento(this);
		vm= new VMaquinaria(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Calcula Tratamiento");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vt.setVisible(true);
				VMenu.this.dispose();
			}
		});
		btnNewButton.setBounds(104, 40, 208, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Trabajos De Maquinaria");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				vm.setVisible(true);
				VMenu.this.dispose();

			}
		});
		btnNewButton_1.setBounds(104, 119, 208, 23);
		contentPane.add(btnNewButton_1);
	}
}
