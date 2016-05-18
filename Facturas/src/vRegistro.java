
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class vRegistro extends JFrame {
	Statement st = null;
	ResultSet rs = null;
	private JPanel contentPane;
	private JPasswordField txtPass;
	DefaultTableModel dtm = new DefaultTableModel();
	@SuppressWarnings("rawtypes")
	JTextField txtUsuario;
	private JTextField msjInfo;
	public ConexionDB facturas;

	public vRegistro(ArrayList<String> usuarios) {
		setResizable(false);

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

		txtPass = new JPasswordField();
		txtPass.setBounds(175, 141, 86, 20);
		getContentPane().add(txtPass);


		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuarios usuarios= new Usuarios();
				General.getInstance();

				try{
					if(usuarios.existe(txtUsuario.getText(), String.valueOf(txtPass.getPassword())))
					{
						vMaquinaria vm= new vMaquinaria(dtm);
						vm.setVisible(true);
						vRegistro.this.dispose();
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
		btnRegistrar.setBounds(251, 227, 108, 23);
		contentPane.add(btnRegistrar);

		msjInfo = new JTextField("Mensajes");
		msjInfo.setEditable(false);
		msjInfo.setBounds(86, 196, 273, 20);
		contentPane.add(msjInfo);
		msjInfo.setColumns(10);

		ponUsuarios(usuarios);


	}

	public void Mensajes(String msje)
	{
		msjInfo.setText(msje);
	}
	
	

	@SuppressWarnings("unchecked")
	public void ponUsuarios(ArrayList<String> usuarios)
	{System.out.println("usuarios combo");
	//usuario.removeAllItems();
	for(String u:usuarios)
	{
	//	usuario.addItem(u);
	}
	//this.repaint();
	}


}
