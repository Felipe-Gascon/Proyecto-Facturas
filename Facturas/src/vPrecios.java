import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class vPrecios extends JFrame {

	private JPanel contentPane;
	JComboBox comboTrabajos;
	Maquinaria maquinaria = new Maquinaria();
	
	
	Hashtable trabajos;
	DefaultTableModel dtm2s = new DefaultTableModel();
	vMaquinaria vm ;

	/**
	 * Launch the application.
	 */


	public vPrecios(DefaultTableModel miDTM) {
		trabajos = maquinaria.getTrabajos();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		if (dtm2s==null){
			miDTM=new DefaultTableModel();
		}else{
			miDTM=dtm2s;
		}
		comboTrabajos = new JComboBox();

		comboTrabajos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//  String tarea = comboTrabajos.getSelectedItem().toString();
				// System.out.println(tarea+ "\t "+ trabajos.get(tarea));


			}
		});   
		comboTrabajos.setBounds(10, 63, 331, 20);
		contentPane.add(comboTrabajos);



		JLabel lblTrabajos = new JLabel("Trabajos");
		lblTrabajos.setBounds(10, 33, 161, 20);
		contentPane.add(lblTrabajos);




		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tareas = comboTrabajos.getSelectedItem().toString();
				String value = String.valueOf(trabajos.get(tareas));

			 
				System.out.println(value);


			//	String t = vm.txt;
			//	System.out.println(t);


				vPrecios.this.dispose();
			}
		});
		btnNewButton.setBounds(118, 219, 89, 31);
		contentPane.add(btnNewButton);
		ponTrabajos();
		//copiarTexto();
	}


	public void ponTrabajos( )
	{
		String clave;

		Enumeration<String> claves = trabajos.keys();

		while(claves.hasMoreElements())
		{
			clave = claves.nextElement();
			System.out.println(clave);
			comboTrabajos.addItem(clave);


		}

	
	}
	
	/*public void copiarTexto()
	{
		
		System.out.println(vm.obtenerJtxt());
	}*/
}