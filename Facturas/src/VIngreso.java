import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class VIngreso extends JFrame {
	Statement st = null;
	ResultSet rs = null;
	private JPanel contentPane;
	public JPasswordField txtPass;
	private DefaultTableModel dtm;
	private JTextField txtUsuario;
	private JTextField msjInfo;
	public ConexionDB facturas;
	private VMenu vm;

	public VIngreso(ArrayList<String> usuarios) {
		setResizable(false);
		dtm = new DefaultTableModel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsuario.setBounds(175, 21, 139, 14);
		contentPane.add(lblUsuario);


		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContrasea.setBounds(175, 102, 127, 14);
		contentPane.add(lblContrasea);


		txtUsuario = new JTextField();
		txtUsuario.setBounds(175, 63, 86, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setText("Felipe");
		
		txtPass = new JPasswordField();
		txtPass.setBounds(175, 141, 86, 20);
		getContentPane().add(txtPass);
		txtPass.setText("holahola");


		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuarios usuarios= new Usuarios();
				General.getInstance();

				try{
					if(usuarios.existe(txtUsuario.getText(), String.valueOf(txtPass.getPassword())))
					{
						vm= new VMenu(dtm);
						vm.setVisible(true);
						VIngreso.this.dispose();
						
					}
					else{
						msjInfo.setText("Usuario no Permitido");
					}					
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}


			}
		});
		btnEntrar.setBounds(54, 227, 89, 23);
		contentPane.add(btnEntrar);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					VRegistro vr = new VRegistro();
					vr.setVisible(true);
					VIngreso.this.dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnRegistrar.setBounds(251, 227, 108, 23);
		contentPane.add(btnRegistrar);

		msjInfo = new JTextField("Mensajes");
		msjInfo.setEditable(false);
		msjInfo.setBounds(86, 196, 273, 20);
		contentPane.add(msjInfo);
		msjInfo.setColumns(10);





	}

	public void Mensajes(String msje)
	{
		msjInfo.setText(msje);
	}
	
	

}