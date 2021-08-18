import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Assunzione extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Assunzione() {
		ese=new Esercizio();
		JPanel panel=new JPanel();
		creazioneCampi();
		creazioneBottone();
		panel.add(l1);
		panel.add(f1);
		panel.add(l2);
		panel.add(f2);
		panel.add(l3);
		panel.add(f3);
		panel.add(l4);
		panel.add(f4);
		panel.add(l5);
		panel.add(f5);
		panel.add(bottone);
		panel.add(l6);
		add(panel);
		setSize(600,400);
	}
	
	private void creazioneCampi() {
		l1=new JLabel("Codice dipendente:");
		l2=new JLabel("Nome del ristorante: ");
		l3=new JLabel("Indirizzo del ristorante: ");
		l4=new JLabel("Data presa di servizio: ");
		l5=new JLabel("Tipo di contratto");
		l6=new JLabel("");
		f1=new JTextField(30);
		f2=new JTextField(30);
		f3=new JTextField(30);
		f4=new JTextField(30);
		f5=new JTextField(30);
		
	}
	private void creazioneBottone() {
		bottone=new JButton("Invio");
		class Invio implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String codice,nome,indirizzo,data,tipo;
				codice=f1.getText();
				nome=f2.getText();
				indirizzo=f3.getText();
				data=f4.getText();
				tipo=f5.getText();
				try {
					l6.setText(ese.ese8(codice, nome, indirizzo, data, tipo));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
		}
		ActionListener a1=new Invio();
		bottone.addActionListener(a1);
	}
	//String aCodice, String aNomeRistorante, String aIndirizzoRistorante, String aDataPresaServizio, String aTipoContratto
	private Esercizio ese;
	private JLabel l1,l2,l3,l4,l5,l6;
	private JTextField f1,f2,f3,f4,f5;
	private JButton bottone;
}
