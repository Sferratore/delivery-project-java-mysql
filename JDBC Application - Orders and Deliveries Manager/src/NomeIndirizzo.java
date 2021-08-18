import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NomeIndirizzo extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NomeIndirizzo() {
		ese=new Esercizio();
		JPanel panel=new JPanel();
		creazioneCampi();
		creazioneBottone();
		panel.add(uno);
		panel.add(fielduno);
		panel.add(due);
		panel.add(fielddue);
		panel.add(invio);
		panel.add(tre);
		add(panel);
		setSize(350,400);
	}

	private void creazioneCampi() {
		uno=new JLabel("Nome: ");
		fielduno=new JTextField(20);
		due=new JLabel("Indirizzo:");
		fielddue=new JTextField(20);
		tre=new JLabel("");
		
	}
	
	private void creazioneBottone() {
		invio=new JButton("Invio");
		class Azione implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nome,indirizzo;
				nome=fielduno.getText();
				indirizzo=fielddue.getText();
				tre.setText(ese.ese3(nome, indirizzo));
			}
			
		}
		ActionListener azione1=new Azione();
		invio.addActionListener(azione1);
		
	}

	private Esercizio ese;
	private JLabel uno,due,tre;
	private JTextField fielduno,fielddue;
	private JButton invio;
}
