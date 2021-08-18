import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AssegnaServizio extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AssegnaServizio() {
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
		panel.add(quattro);
		panel.add(fieldquattro);
		panel.add(bottone);
		panel.add(cinque);
		setSize(500,400);
		add(panel);
	}
	
	private void creazioneCampi() {
		uno=new JLabel("Tipo servizio: ");
		fielduno=new JTextField(30);
		due=new JLabel("Nome del ristorante: ");
		fielddue=new JTextField(20);
		tre=new JLabel("Indirizzo del ristorante: ");
		fieldtre=new JTextField(20);
		quattro=new JLabel("Socita esterna: ");
		fieldquattro=new JTextField(20);
		cinque=new JLabel("");
	}
	
	private void creazioneBottone() {
		bottone=new JButton("Invio");
		class Azione implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String aTipoServizio, aNomeRistorante, aIndirizzoRistorante, aSocietaEsterna;
					aTipoServizio=fielduno.getText();
					aNomeRistorante=fielddue.getText();
					aIndirizzoRistorante=fieldtre.getText();
					aSocietaEsterna=fieldquattro.getText();
					cinque.setText(ese.ese7(aTipoServizio, aNomeRistorante, aIndirizzoRistorante, aSocietaEsterna));
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		ActionListener action1=new Azione();
		bottone.addActionListener(action1);
	}
	private Esercizio ese;
	private JLabel uno,due,tre,quattro,cinque;
	private JTextField fielduno,fielddue,fieldtre,fieldquattro;
	private JButton bottone;
}
