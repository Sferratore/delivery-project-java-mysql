import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OraData extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OraData() {
		ese=new Esercizio();
		JPanel panel=new JPanel();
		creazioneCampi();
		creazioneBottone();
		panel.add(uno);
		panel.add(fielduno);
		panel.add(due);
		panel.add(fielddue);
		panel.add(tre);
		panel.add(fieldtre);
		panel.add(invio);
		panel.add(quattro);
		add(panel);
		setSize(500,600);
	}

	private void creazioneCampi() {
		uno=new JLabel("Orario in formato (hh:mm:ss): ");
		fielduno=new JTextField(20);
		due=new JLabel("Data in formato (YYYY-MM-DD):");
		fielddue=new JTextField(20);
		tre=new JLabel("Codice");
		fieldtre=new JTextField(20);
		quattro=new JLabel("");
	}
	
	private void creazioneBottone() {
		invio=new JButton("Invio");
		class Azione implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ora,data,codice;
				ora=fielduno.getText();
				data=fielddue.getText();
				codice=fieldtre.getText();
				quattro.setText(ese.ese2(ora,data,codice));

			}
			
		}
		ActionListener azione1=new Azione();
		invio.addActionListener(azione1);
		
	}

	private Esercizio ese;
	private JLabel uno,due,tre,quattro;
	private JTextField fielduno,fielddue,fieldtre;
	private JButton invio;
}

