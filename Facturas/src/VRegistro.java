import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JPasswordField textPass;
	private Object nuevoUsuario[]= new Object[2];

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public VRegistro() throws Exception
		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(10, 23, 74, 14);
			contentPane.add(lblNombre);
			
			textNombre = new JTextField();
			textNombre.setBounds(75, 20, 123, 20);
			contentPane.add(textNombre);
			textNombre.setColumns(10);
			
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(7, 101, 58, 14);
			contentPane.add(lblPassword);
			
			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Registro registro = new Registro();
					General.getInstance();
					
					String Nombre = textNombre.getText();
					String Pasword=textPass.getText();
					try {
						registro.InsertaUsuario(Nombre, Pasword);
						JOptionPane.showMessageDialog(null,"Usuario Registrado");

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnRegistrar.setBounds(252, 214, 89, 23);
			contentPane.add(btnRegistrar);
			
			JButton btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VIngreso vi = new VIngreso(null);
					vi.setVisible(true);
					VRegistro.this.dispose();
				
				}
			});
			btnAtras.setBounds(48, 214, 89, 23);
			contentPane.add(btnAtras);
			
			textPass = new JPasswordField();
			textPass.setBounds(78, 98, 120, 20);
			contentPane.add(textPass);
		
	} 
	
	
	
}
