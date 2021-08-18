import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

public class RegistraOrdine extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RegistraOrdine() {
		ese=new Esercizio();
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		p5=new JPanel();
		p6=new JPanel();
		p7=new JPanel();
		p8=new JPanel();
		p9=new JPanel();
		p10=new JPanel();
		p11=new JPanel();
		listener=new Esegui();
		creaRadio();
		creaCampi();
		creaBottone();
		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		add(b1);
		add(b2);
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(p6);
		add(p7);
		add(p8);
		add(p9);
		add(p10);
		add(p11);
		setSize(700,600);
	}

	public void creaRadio() {
		menu=new ButtonGroup();
		b1=new JRadioButtonMenuItem("Rider");
		b2=new JRadioButtonMenuItem("Dipendente");
		b1.addActionListener(listener);
		b2.addActionListener(listener);
		menu.add(b1);
		menu.add(b2);
	}
	
	public void creaCampi() {
		l1=new JLabel("Codice di chi consegna: ");
		t1=new JTextField(20);
		l2=new JLabel("Nome del ristorante: ");
		t2=new JTextField(20);
		l3=new JLabel("Indirizzo del ristorante: ");
		t3=new JTextField(20);
		l4=new JLabel("Codice del cliente: ");
		t4=new JTextField(20);
		l5=new JLabel("Data YYYY-MM-DD: ");
		t5=new JTextField(20);
		l6=new JLabel("Descrizione: ");
		t6=new JTextField(40);
		l7=new JLabel("Acquisto: ");
		t7=new JTextField(40);
		l8=new JLabel("Orario presunto di consegna (HH:MM:SS): ");
		t8=new JTextField(20);
		l9=new JLabel("Costo: ");
		t9=new JTextField(20);
		l10=new JLabel("");
		p1.add(l1);
		p1.add(t1);
		p2.add(l2);
		p2.add(t2);
		p3.add(l3);
		p3.add(t3);
		p4.add(l4);
		p4.add(t4);
		p5.add(l5);
		p5.add(t5);
		p6.add(l6);
		p6.add(t6);
		p7.add(l7);
		p7.add(t7);
		p8.add(l8);
		p8.add(t8);
		p9.add(l9);
		p9.add(t9);
		p11.add(l10);
	}
	
	public void creaBottone() {
		bottone=new JButton("Invio");
		bottone.addActionListener(listener);
		p10.add(bottone);
	}
	
	class Esegui implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==b1) scelta=1;
			if(e.getSource()==b2) scelta=2;
			if(e.getSource()==bottone) {
				String cod, nome, indirizzo, codiceC, data, desc, acq, orarioPres;
				float costo;
				cod=t1.getText();
				nome=t2.getText();
				indirizzo=t3.getText();
				codiceC=t4.getText();
				data=t5.getText();
				desc=t6.getText();
				acq=t7.getText();
				orarioPres=t8.getText();
				try {
				costo=Float.parseFloat(t9.getText());
					try {	
						l10.setText(ese.ese1(cod, nome, indirizzo, codiceC, data, desc, acq, orarioPres, costo, scelta));
					}catch(SceltaSbagliataException e1) {
						JOptionPane.showMessageDialog(null,"Scegli se consegna un rider o un dipendente");
					}
					catch(Exception e2) {
						JOptionPane.showMessageDialog(null,"Dati inseriti non validi");
					}
				}catch(NumberFormatException e3) {
					JOptionPane.showMessageDialog(null,"Dati inseriti non validi");
				}
				
			}
		}
		
	}
	private JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11;
	private Esercizio ese;
	private int scelta;
	private JButton bottone;
	private JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	private JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9;
	private JRadioButtonMenuItem b1,b2;
	private ButtonGroup menu;
	private ActionListener listener;
}
