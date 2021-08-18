import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CodiceData extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CodiceData() {
		ese=new Esercizio();
		JPanel panel=new JPanel();
		creazioneCampi();
		creazioneBottone();
		panel.add(due);
		panel.add(fielddue);
		panel.add(tre);
		panel.add(fieldtre);
		panel.add(invio);
		panel.add(uno);
		add(panel);
		setSize(500,600);
	}

	private void creazioneCampi() {
		due=new JLabel("Data in formato (YYYY-MM-DD):");
		fielddue=new JTextField(20);
		tre=new JLabel("Codice");
		fieldtre=new JTextField(20);
		uno=new JLabel("");
	}
	
	private void creazioneBottone() {
		invio=new JButton("Invio");
		class Azione implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String data,codice;
				data=fielddue.getText();
				codice=fieldtre.getText();
				uno.setText(ese.ese11(codice, data));
			}
			
		}
		ActionListener azione1=new Azione();
		invio.addActionListener(azione1);
		
	}

	private Esercizio ese;
	private JLabel uno,due,tre;
	private JTextField fielddue,fieldtre;
	private JButton invio;
}
