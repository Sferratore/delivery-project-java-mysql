import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Scelta  extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Scelta() {
		p1=new JPanel();
		p2=new JPanel();
		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		JMenuBar mb=new JMenuBar();
		JMenu menu=new JMenu("Seleziona l'esercizio");
		JMenuItem i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14,i15;
		menu.add(i1=new JMenuItem(new String("1: Registrazione di un ordine".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i2=new JMenuItem(new String("2: Consegna di un ordine".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i3=new JMenuItem(new String("3: Verifica della possibilita di effettuare un ordine ad un determinato ristorante".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i4=new JMenuItem(new String("4: Visualizzazione dei ristoranti disponibili all accettazione di un ordine".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i5=new JMenuItem(new String("5: Valutazione di un rider".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i6=new JMenuItem(new String("6: Visualizzazione per ogni cliente del numero di ordini effettuati".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i7=new JMenuItem(new String("7: Abilitazione dell affidamento ad una societa di un servizio di delivery (gia presente)".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i8=new JMenuItem(new String("8: Assunzione di un nuovo dipendente per la consegna degli ordini".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i9=new JMenuItem(new String("9: Ristoranti che impiegano dipendenti propri per la consegna o che si affidano ai servizi della societa 'Food Delivery'".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i10=new JMenuItem(new String("10: Visualizzazione degli ordini consegnati da Rider ancora non valutati".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i11=new JMenuItem(new String("11: Cancellazione di un ordine ancora non consegnato".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i12=new JMenuItem(new String("12: Persone (nome, cognome) che abbiano consegnato ordini a Giuseppe Verdi nell ultima settimana".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i13=new JMenuItem(new String("13: Dati dei ristoranti, incluso la coda di ordini attuale".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i14=new JMenuItem(new String("14: Dati dei rider, incluso lo score medio ottenuto nelle valutazioni da parte dei clienti".getBytes(), StandardCharsets.UTF_8)));
		menu.add(i15=new JMenuItem(new String("15: Clienti che nell ultima settimana abbiano effettuato almeno una valutazione inferiore allo score medio di un rider".getBytes(), StandardCharsets.UTF_8)));
		class Chiamafunz1 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new RegistraOrdine();
				f.setVisible(true);
			}
			
		}
		class Chiamafunz2 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new OraData();
				f.setVisible(true);
			}
			
		}
		class Chiamafunz3 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new NomeIndirizzo();
				f.setVisible(true);
			}
			
		}
		class Chiamafunz4 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new JFrame();
				JTextArea area=new JTextArea();
				area.setText(ese.ese4());
				area.setEditable(false);
				f.add(area);
				f.setSize(400,400);
				f.setVisible(true);
			}
			
		}
		class Chiamafunz5 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new Valutazione();
				f.setVisible(true);
			}
			
		}
		class Chiamafunz6 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new JFrame();
				JTextArea area=new JTextArea();
				try {
					area.setText(ese.ese6());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				area.setEditable(false);
				f.add(area);
				f.setSize(400,400);
				f.setVisible(true);
			}
			
		}
		class Chiamafunz7 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new AssegnaServizio();
				f.setVisible(true);
			}
			
		}
		class Chiamafunz8 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new Assunzione();
				f.setVisible(true);
			}
			
		}
		class Chiamafunz9 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new JFrame();
				JTextArea area=new JTextArea();
				try {
					area.setText(ese.ese9());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				area.setEditable(false);
				f.add(area);
				f.setSize(400,400);
				f.setVisible(true);
			}
			
		}
		class Chiamafunz10 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event){
				JFrame f=new JFrame();
				JTextArea area=new JTextArea();
				try {
					area.setText(ese.ese10());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				area.setEditable(false);
				f.add(area);
				f.setSize(1200,800);
				f.setVisible(true);
			}
			
		}
		class Chiamafunz11 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new CodiceData();
				f.setVisible(true);
			}
			
		}
		class Chiamafunz12 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new JFrame();
				JTextArea area=new JTextArea();
				area.setText(ese.ese12());
				area.setEditable(false);
				f.add(area);
				f.setSize(600,400);
				f.setVisible(true);
			}
			
		}
		class Chiamafunz13 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new JFrame();
				JTextArea area=new JTextArea();
				area.setText(ese.ese13());
				area.setEditable(false);
				f.add(area);
				f.setSize(800,400);
				f.setVisible(true);
			}
			
		}
		class Chiamafunz14 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new JFrame();
				JTextArea area=new JTextArea();
				area.setText(ese.ese14());
				area.setEditable(false);
				f.add(area);
				f.setSize(400,400);
				f.setVisible(true);
			}
			
		}
		class Chiamafunz15 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame f=new JFrame();
				JTextArea area=new JTextArea();
				area.setText(ese.ese15());
				area.setEditable(false);
				f.add(area);
				f.setSize(400,400);
				f.setVisible(true);
			}
			
		}
		ActionListener action1=new Chiamafunz1();
		ActionListener action2=new Chiamafunz2();
		ActionListener action3=new Chiamafunz3();
		ActionListener action4=new Chiamafunz4();
		ActionListener action5=new Chiamafunz5();
		ActionListener action6=new Chiamafunz6();
		ActionListener action7=new Chiamafunz7();
		ActionListener action8=new Chiamafunz8();
		ActionListener action9=new Chiamafunz9();
		ActionListener action10=new Chiamafunz10();
		ActionListener action11=new Chiamafunz11();
		ActionListener action12=new Chiamafunz12();
		ActionListener action13=new Chiamafunz13();
		ActionListener action14=new Chiamafunz14();
		ActionListener action15=new Chiamafunz15();
		i1.addActionListener(action1);
		i2.addActionListener(action2);
		i3.addActionListener(action3);
		i4.addActionListener(action4);
		i5.addActionListener(action5);
		i6.addActionListener(action6);
		i7.addActionListener(action7);
		i8.addActionListener(action8);
		i9.addActionListener(action9);
		i10.addActionListener(action10);
		i11.addActionListener(action11);
		i12.addActionListener(action12);
		i13.addActionListener(action13);
		i14.addActionListener(action14);
		i15.addActionListener(action15);
		mb.add(menu);
		setJMenuBar(mb);
		l=new JLabel("Luigi Allocca & Francesco Paolo Sferratore");
		l.setFont(new Font("Verdana",Font.BOLD,24));
		p1.add(mb);
		p1.setBackground(Color.CYAN);
		p2.add(l);
		p2.setBackground(Color.CYAN);
		add(p1);
		add(p2);
		setSize(600,400);
	}
	
	private JLabel l;
	private JPanel p1,p2;
	private Esercizio ese=new Esercizio();
}
