import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class VMenu extends JFrame {

	private JPanel contentPane;
	private VTratamiento vt;
	private VMaquinaria vm;
	private DefaultTableModel eldtm;
	
	public VMenu(DefaultTableModel midtm) {
		eldtm=midtm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Calcula Tratamiento");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vt= new VTratamiento(eldtm);
				vt.setVisible(true);
				VMenu.this.dispose();
			}
		});
		btnNewButton.setBounds(104, 40, 208, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Trabajos De Maquinaria");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vm = new VMaquinaria(eldtm);
				vm.setVisible(true);
				VMenu.this.dispose();
				
			}
		});
		btnNewButton_1.setBounds(104, 119, 208, 23);
		contentPane.add(btnNewButton_1);
	}
}
