
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;


@SuppressWarnings("serial")
public class vRegistro extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPass;
	DefaultTableModel dtm = new DefaultTableModel();
	@SuppressWarnings("rawtypes")
	JComboBox usuario;
	private JTextField msjInfo;

	
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

		
		usuario = new JComboBox();
		usuario.setBounds(175, 63, 86, 20);
		getContentPane().add(usuario);
		
		
		txtPass = new JPasswordField();
		txtPass.setBounds(175, 141, 86, 20);
		getContentPane().add(txtPass);
		
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				
				General.getInstance();
				// esto lo hice comprobacion manual esta mal
				if(usuario.getSelectedItem().toString().equalsIgnoreCase("Felipe")&&txtPass.getText().equals("holahola"))
				{
					vMaquinaria vm= new vMaquinaria(dtm);
					vm.setVisible(true);
					vRegistro.this.dispose();
				}
				else{
					msjInfo.setText("Usuario no Permitido");
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
		usuario.removeAllItems();
		for(String u:usuarios)
		{
			usuario.addItem(u);
		}
		//this.repaint();
	}
	
	
}
