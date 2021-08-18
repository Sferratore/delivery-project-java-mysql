import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Valutazione extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Valutazione() {
		ese=new Esercizio();
		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout());
		creazioneCampi();
		creazioneBottone();
		creazioneMenu();
		panel.add(uno);
		panel.add(fielduno);
		panel.add(due);
		panel.add(fielddue);
		panel.add(tre);
		panel.add(fieldtre);
		panel.add(quattro);
		panel.add(fieldquattro);
		panel.add(sei);
		panel.add(mb);
		panel.add(bottone);
		panel.add(cinque);
		setSize(400,800);
		add(panel);
	}
	
	private void creazioneCampi() {
		uno=new JLabel("Codice rider: ");
		fielduno=new JTextField(20);
		due=new JLabel("Codice cliente: ");
		fielddue=new JTextField(20);
		tre=new JLabel("Data valutazione: ");
		fieldtre=new JTextField(20);
		quattro=new JLabel("Testo: ");
		fieldquattro=new JTextArea(30,30);
		cinque=new JLabel("");
		sei=new JLabel("Voto: ");
	}
	
	private void creazioneBottone() {
		bottone=new JButton("Invio");
		class Azione implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String rider,cliente,data,testo;
					rider=fielduno.getText();
					cliente=fielddue.getText();
					data=fieldtre.getText();
					testo=fieldquattro.getText();
					cinque.setText(ese.ese5(rider, cliente, voto, data, testo));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		ActionListener action1=new Azione();
		bottone.addActionListener(action1);
	}
	
	private void creazioneMenu() {
		JMenuItem i1,i2,i3,i4,i5;
		i1=new JMenuItem("1");
		i2=new JMenuItem("2");
		i3=new JMenuItem("3");
		i4=new JMenuItem("4");
		i5=new JMenuItem("5");
		class Listener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==i1) voto=1;
				if(e.getSource()==i2) voto=2;
				if(e.getSource()==i3) voto=3;
				if(e.getSource()==i4) voto=4;
				if(e.getSource()==i5) voto=5;
				menu.setText(" "+voto+" ");
			}
			
		}
		ActionListener l=new Listener();
		i1.addActionListener(l);
		i2.addActionListener(l);
		i3.addActionListener(l);
		i4.addActionListener(l);
		i5.addActionListener(l);
		menu.add(i1);
		menu.add(i2);
		menu.add(i3);
		menu.add(i4);
		menu.add(i5);
		mb.add(menu);
	}
	private int voto;
	private JMenuBar mb=new JMenuBar();
	private JMenu menu=new JMenu(" - ");
	private Esercizio ese;
	private JLabel uno,due,tre,quattro,cinque,sei;
	private JTextArea fieldquattro;
	private JTextField fielduno,fielddue,fieldtre;
	private JButton bottone;
}
