import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
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


@SuppressWarnings("serial")
public class VTratamiento extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField textNom;
	private JFormattedTextField textYear;
	private JFormattedTextField textSup;
	private JTextField textTot;
	private JTable tratamientos;
	private DefaultTableModel dtm;
	private String col[]={"Tratamiento","Precios","Subtotal"};
	private float superficie;
	private NuevoTratamiento nt ;
	private JLabel lblSuperficie;
	private JScrollPane scrollPane;
	private JButton btnNuevoTrat, btnAtras, btnEliminaFila,btnPdf ;
	@SuppressWarnings("unused")
	private VMenu vm;

	JFileChooser seleccionarArchivo;
	JEditorPane editor;
	private JLabel label;

	public VTratamiento(VMenu vm) {

		this.dtm=new DefaultTableModel();

		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
		lblNombre.setBounds(10, 11, 68, 14);
		contentPane.add(lblNombre);

		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
		lblAo.setBounds(10, 49, 46, 14);
		contentPane.add(lblAo);

		this.tratamientos= new JTable(dtm);

		scrollPane = new JScrollPane(tratamientos);
		scrollPane.setBounds(5, 85, 419, 131);
		contentPane.add(scrollPane);



		textNom = new JFormattedTextField(new String());;
		textNom.setBounds(66, 8, 86, 20);
		contentPane.add(textNom);
		textNom.setColumns(10);

		textYear = new JFormattedTextField(formatter);;
		textYear.setBounds(66, 46, 86, 20);
		contentPane.add(textYear);
		textYear.setColumns(10);

		lblSuperficie = new JLabel("Superficie");
		lblSuperficie.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
		lblSuperficie.setBounds(198, 10, 68, 14);
		contentPane.add(lblSuperficie);

		textSup = new JFormattedTextField(formatter);;
		textSup.setBounds(277, 8, 86, 20);
		contentPane.add(textSup);
		textSup.setColumns(10);

		textTot = new JTextField();
		textTot.setEditable(false);
		textTot.setBounds(277, 46, 86, 20);
		contentPane.add(textTot);
		textTot.setColumns(10);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));

		lblTotal.setBounds(212, 47, 46, 14);
		contentPane.add(lblTotal);

		btnNuevoTrat = new JButton("Nuevo Tratamiento");
		btnNuevoTrat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(textSup.getText().trim().length()==0)
				{
					JOptionPane.showMessageDialog(null,"Ingrese datos Correctos");
				}
				else
				{
					superficie=Float.parseFloat(textSup.getText());
					nt= new NuevoTratamiento(dtm, superficie, textTot);
					nt.setVisible(true);

				}
			}
		});
		btnNuevoTrat.setBounds(277, 246, 140, 23);
		contentPane.add(btnNuevoTrat);

		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vm.setVisible(true);
				VTratamiento.this.dispose();
			}
		});
		btnAtras.setBounds(10, 246, 89, 23);
		contentPane.add(btnAtras);

		btnEliminaFila = new JButton("Elimina Fila");
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

		btnPdf = new JButton("Exporta PDF");
		btnPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if(textSup.getText().trim().length()==0 || textNom.getText().trim().length()==0 
						||textYear.getText().trim().length()==0 )
				{
					JOptionPane.showMessageDialog(null, "Rellena todos los Campos");
				}
				else{
					exportaPDF();
				}
				

			}
		});

		btnPdf.setBounds(63, 281, 236, 23);
		contentPane.add(btnPdf);
		
		JLabel lblHa = new JLabel("ha");
		lblHa.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		lblHa.setBounds(371, 11, 46, 14);
		contentPane.add(lblHa);
		
		label = new JLabel("\u20AC");
		label.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		label.setBounds(371, 50, 46, 14);
		contentPane.add(label);

		for(int columna=0;columna<this.col.length;columna++)
		{

			this.dtm.addColumn(this.col[columna]);
		}

	}



	@SuppressWarnings({ "unused", "static-access" })
	public void exportaPDF() 
	{
		try{
			String nom= textNom.getText();
			String dia = textYear.getText();
			String superf = textSup.getText();
			String total = textTot.getText();
			Image img,publicidad;

			Document doc = new Document(PageSize.A4, 50, 50, 50, 50);

			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Felipe\\Desktop\\Tratamientos.pdf"));
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

			PdfPTable tablaTratamientos = new PdfPTable(tratamientos.getColumnCount());

			Font tableHeader = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
			//añadimos la cabecera de las columnas
			for(int i=0;i<tratamientos.getColumnCount();i++){
				Paragraph header = new Paragraph();

				header.setFont(tableHeader);


				header.add(tratamientos.getColumnName(i));

				tablaTratamientos.addCell(header);

			}

			//introducimos los conceptos

			for(int i=0;i<tratamientos.getRowCount();i++){
				for(int j=0;j<tratamientos.getColumnCount();j++){
					tablaTratamientos.addCell(tratamientos.getModel().getValueAt(i, j).toString());
				}
			}

			doc.add(tablaTratamientos);
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			/// Ja tenim la taula de tractaments impresa al pdf
			
			//Editamos El pie de pagina
			
			Font estilo = new  Font();

			estilo.setColor(BaseColor.GRAY);
			estilo.setStyle(estilo.UNDERLINE);
			estilo.setStyle(estilo.BOLD);

			Paragraph piePagina = new Paragraph();

			piePagina.setAlignment(piePagina.ALIGN_CENTER);
			piePagina.add("-CULTIVOS Y TRATAMIENTOS SRA®  2016-");
			
			doc.add(piePagina);
			doc.add(publicidad);

			doc.close();
			JOptionPane.showMessageDialog(null,"Generado PDF Exitosamente.");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
}

