import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class vPrecios extends JFrame {

	private JPanel contentPane;
	JComboBox comboTrabajos;
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public vPrecios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboTrabajos = new JComboBox();
		comboTrabajos.setBounds(10, 63, 161, 20);
		contentPane.add(comboTrabajos);
		
		JLabel lblTrabajos = new JLabel("Trabajos");
		lblTrabajos.setBounds(10, 33, 161, 20);
		contentPane.add(lblTrabajos);
		
		JLabel lblPrecios = new JLabel("Precios");
		lblPrecios.setBounds(247, 33, 161, 20);
		contentPane.add(lblPrecios);
		
		JComboBox comboPrecio = new JComboBox();
		comboPrecio.setBounds(247, 63, 161, 20);
		contentPane.add(comboPrecio);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(118, 219, 89, 31);
		contentPane.add(btnNewButton);
		//ponTrabajos(TareasMaquinariaXPrecios);
	}
	
	
	public void ponTrabajos( Hashtable<String,Float> TareasMaquinariaXPrecios)
	{
		Object[] claves = TareasMaquinariaXPrecios.keySet().toArray();
		
		for(int i=0;i<claves.length;i++){
			comboTrabajos.addItem(claves[i].toString());
		}
		
		
	}
}
