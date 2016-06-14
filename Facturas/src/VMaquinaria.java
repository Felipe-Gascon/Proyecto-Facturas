
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.text.NumberFormat;

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
import javax.swing.text.NumberFormatter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JFormattedTextField;


@SuppressWarnings("serial")
public class VMaquinaria extends JFrame {
	public static String supe;
	private JPanel contentPane;
	private DefaultTableModel dtm;
	private String columnas[]={"Trabajos_Maq","Precios","Subtotal"};
	//Object[] trabajosTabla = new Object[columnas.length];
	private JTable trabajos;
	private JScrollPane scrollPane;

	private float superficie;
	private JFormattedTextField textSup;
	private JFormattedTextField textNom;
	private JLabel lblAo;
	private JFormattedTextField txtYear; 
	private final JLabel lblNewLabel = new JLabel("Total");
	private JTextField txtTot;
	private VPrecios vp;
	@SuppressWarnings("unused")
	private VMenu vm;


	public VMaquinaria( VMenu vm) {
		this.dtm=new DefaultTableModel();
		this.vm=vm;
		setResizable(false);
		//System.out.println("constructor sense parametres");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);


		this.trabajos= new JTable(dtm);
		for(int column=0;column<columnas.length;column++)
		{
			//System.out.println("Afegint dades al dtm");
			this.dtm.addColumn(this.columnas[column]);
		}



		JButton btnAadirtrabajos = new JButton("A\u00F1adir trabajos");
		btnAadirtrabajos.setBounds(118, 227, 133, 23);
		btnAadirtrabajos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//obtenerJtxt();

				if(textSup.getText().trim().length()==0){

					JOptionPane.showMessageDialog(null,"Ingrese Superficie");
				}
				else
				{

					superficie=Float.parseFloat(textSup.getText());
					vp=new VPrecios(dtm, superficie,txtTot);

					vp.setVisible(true);

				}
				//	getSuperficie();


			}
		});
		contentPane.setLayout(null);

		scrollPane = new JScrollPane(trabajos);
		scrollPane.setBounds(5, 85, 419, 131);
		contentPane.add(scrollPane);
		contentPane.add(btnAadirtrabajos);

		JButton btnEliminartrabajos = new JButton("Eliminar trabajos");
		btnEliminartrabajos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(trabajos.getRowCount()==0){
					JOptionPane.showMessageDialog(null, "La tabla Esta Vacia");
				}
				else
				{
					dtm.removeRow(trabajos.getSelectedRow());
					txtTot.setText("0");
				}

			}
		});
		btnEliminartrabajos.setBounds(261, 227, 143, 23);
		contentPane.add(btnEliminartrabajos);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
		lblNombre.setBounds(5, 11, 56, 23);
		contentPane.add(lblNombre);

		JLabel lblSuperficie = new JLabel("Superficie");
		lblSuperficie.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
		lblSuperficie.setBounds(207, 11, 78, 23);
		contentPane.add(lblSuperficie);

		textSup = new  JFormattedTextField(formatter);
		textSup.setBounds(286, 12, 86, 20);
		contentPane.add(textSup);
		textSup.setColumns(10);

		lblAo = new JLabel("A\u00F1o");
		lblAo.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
		lblAo.setBounds(5, 45, 32, 23);
		contentPane.add(lblAo);

		txtYear = new  JFormattedTextField( formatter ) ;
		txtYear.setBounds(60, 45, 86, 20);
		contentPane.add(txtYear);
		txtYear.setColumns(10);
		lblNewLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
		lblNewLabel.setBounds(217, 45, 47, 20);
		contentPane.add(lblNewLabel);

		txtTot = new JTextField();
		txtTot.setEditable(false);
		txtTot.setBounds(286, 43, 86, 20);
		contentPane.add(txtTot);
		txtTot.setColumns(10);
		txtTot.setText("0");

		textNom = new JFormattedTextField(new String());
		textNom.setBounds(60, 13, 86, 20);
		contentPane.add(textNom);
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vm.setVisible(true);
				VMaquinaria.this.dispose();
			}
		});
		btnAtras.setBounds(5, 227, 89, 23);
		contentPane.add(btnAtras);

		JButton btnNewButton = new JButton("Exportar PDF");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(textSup.getText().trim().length()==0 || textNom.getText().trim().length()==0 
						||txtYear.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "Rellena todos los Campos");
				}
				else{
					exportaPDF();
				}


			}
		});
		btnNewButton.setBounds(118, 261, 133, 23);
		contentPane.add(btnNewButton);

		JLabel lblHa = new JLabel("ha");
		lblHa.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		lblHa.setBounds(378, 15, 46, 14);
		contentPane.add(lblHa);

		JLabel label = new JLabel("\u20AC");
		label.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		label.setBounds(378, 49, 46, 14);
		contentPane.add(label);

	}
	@SuppressWarnings("unused")
	public void exportaPDF() 
	{
		try{
			String nom= textNom.getText();
			String dia = txtYear.getText();
			String superf = textSup.getText();
			String total = txtTot.getText();
			Image img,publicidad;

			Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Felipe\\Desktop\\Trabajos Maquinaria.pdf"));
			doc.open();

			Font letra = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
			Paragraph parrafo = new Paragraph();
			parrafo.setFont(letra);
			//imagen
			img = Image.getInstance("C:\\Users\\Felipe\\Desktop\\imagen.jpg");
			img.setAlignment(Element.ALIGN_RIGHT);
			img.scalePercent(60f);

			publicidad = Image.getInstance("C:\\Users\\Felipe\\Desktop\\Imagen2.jpg");
			publicidad.setAlignment(Element.ALIGN_CENTER);
			publicidad.scalePercent(40f);
			doc.add(img);

			parrafo.add("---------------------------------------------------------"+"\n");
			parrafo.add("|    FACTURAS CULTIVOS SRA    |"+"\n");
			parrafo.add("---------------------------------------------------------"+"\n");


			parrafo.add("Nombre del Cliente: "+nom+"\n");
			parrafo.add("Año de Facturacion: "+dia+"\n");
			parrafo.add("Superficie del Terreno: "+superf+"ha"+"\n");
			parrafo.add("Total importe: "+total+"€"+"\n");
			doc.add(parrafo);

			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));

			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));




			// Imprimimimos el contenido de la tabla

			PdfPTable tablaMaquinaria = new PdfPTable(trabajos.getColumnCount());

			//añadimos la cabecera de las columnas
			Font tableHeader = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
			for(int i=0;i<trabajos.getColumnCount();i++)
			{
				//tablaMaquinaria.addCell(trabajos.getColumnName(i));	
				Paragraph header = new Paragraph();

				header.setFont(tableHeader);


				header.add(trabajos.getColumnName(i));

				tablaMaquinaria.addCell(header);

			}

			//introducimos los conceptos

			for(int i=0;i<trabajos.getRowCount();i++)
			{
				for(int j=0;j<trabajos.getColumnCount();j++)
				{
					tablaMaquinaria.addCell(trabajos.getModel().getValueAt(i, j).toString());

				}
			}

			doc.add(tablaMaquinaria);
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));

			/// Ja tenim la taula de tractaments impresa al pdf


			//Editamos El pie de pagina

			Font estilo = new  Font();

			estilo.setColor(BaseColor.GRAY);
			estilo.setStyle(Font.UNDERLINE);
			estilo.setStyle(Font.BOLD);

			Paragraph piePagina = new Paragraph();
			piePagina.setLeading(0, 37);

			piePagina.setAlignment(Element.ALIGN_CENTER);
			piePagina.add("-CULTIVOS Y TRATAMIENTOS SRA  Â®  2016-");
			piePagina.add(publicidad);
			doc.add(piePagina);
			doc.close();

			JOptionPane.showMessageDialog(null,"Generado PDF Exitosamente.");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
}

