import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Esercizio {
	
	public Esercizio() {
		cn=connection();
	}

	private String rimApostrofi(String s) {
		String[] parti=s.split("'");
		String res="";
		for(int i=0;i<parti.length;i++) {
			if(parti.length>1&&i!=parti.length-1) parti[i]=parti[i]+"\\'";
			res=res+parti[i];
		}
		return res;
	}
	
	public Connection connection() {
		Connection cn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Deliveries","root","Eros1234");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Connessione al db fallita");
		}
		return cn;
	}
	
	public String ese1(String cod,String nome,String indirizzo,String codiceC,String data,String desc,String acq,String orarioPres,float costo,int scelta) throws Exception,SceltaSbagliataException,NumberFormatException{
		String res="",sql="";
		int rs;
		if(scelta!=1&&scelta!=2) {
			throw new SceltaSbagliataException();
		}
		if(scelta==1) {
			sql="INSERT INTO Ordine(Rider,NomeRistorante,IndirizzoRistorante,Cliente,DataOrdine,Costo,Descrizione,Acquisto,StatoConsegna,OrarioConsegnaPresunto) VALUES("
					+"'"+cod+"','"+rimApostrofi(nome)+"','"+rimApostrofi(indirizzo)+"','"+codiceC+"','"+data+"','"+costo+"','"+rimApostrofi(desc)+"','"+rimApostrofi(acq)+"','Ordinato','"+orarioPres+"');";
		}
		else if(scelta==2) {
			sql="INSERT INTO Ordine(Dipendente,NomeRistorante,IndirizzoRistorante,Cliente,DataOrdine,Costo,Descrizione,Acquisto,StatoConsegna,OrarioConsegnaPresunto) VALUES("
					+"'"+cod+"','"+rimApostrofi(nome)+"','"+rimApostrofi(indirizzo)+"','"+codiceC+"','"+data+"','"+costo+"','"+rimApostrofi(desc)+"','"+rimApostrofi(acq)+"','Ordinato','"+orarioPres+"');";
		}
		Statement st;
		st=cn.createStatement();
		rs=st.executeUpdate(sql);
		res="("+rs+") rows affected";
		return res;
	}
	
	public  String ese2(String ora,String data,String codice) {
		String res="";
		try {
			String sql="UPDATE Ordine SET StatoConsegna='Consegnato',OrarioConsegnaEffettivo='"+ora+"' WHERE Codice='"+codice+"' AND "
					+ "DataOrdine='"+data+"';";
			int rs;
			Statement st;
			st=cn.createStatement();
			rs=st.executeUpdate(sql);
			res="("+rs+") rows affected\n";
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
		}
		return res;
	}
	public String ese3(String nome,String indirizzo) {
		String res="";
		try {
			String sql="select DisponibilitaOrdini from Ristorante where Nome='"+rimApostrofi(nome)+"' and Indirizzo='"+rimApostrofi(indirizzo)+"'";
			ResultSet rs;
			Statement st;
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				if(rs.getBoolean("DisponibilitaOrdini")==true) res="Disponibile";
				else res="Non disponibile";
			}
			if(res.equals("")) JOptionPane.showMessageDialog(null, "Nessun ristorante trovato a questo nome e indirizzo");;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
		}
		return res;
	}
	
	public String ese4() {
		String res="";
		try {
			String sql="select Nome,Indirizzo from Ristorante where DisponibilitaOrdini=true";
			ResultSet rs;
			Statement st;
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				res=res+"Nome= "+rs.getString("Nome")+", Indirizzo= "+rs.getString("Indirizzo")+"\n";
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
		}
		return res;
	}
	
	public String ese5(String aRider, String aCliente, int aVoto, String aDataValutazione, String aTesto) throws ClassNotFoundException{
		  String res="";
        try{
            Statement query = cn.createStatement();
            int rs;
            rs=query.executeUpdate("INSERT INTO Valutazione (Rider, Cliente, Voto, DataValutazione, Testo) VALUES (\"" + aRider + "\", \"" + aCliente + "\", " + aVoto + ", \"" + aDataValutazione + "\" ,\"" + aTesto + "\"" + ");");
            res="("+rs+") rows affected";
        }
        catch(Exception exception){
        	JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
        }
        return res;
    }
	
	public String ese6() throws ClassNotFoundException{
        String res="";
        try{
            Statement query = cn.createStatement();
            ResultSet result = query.executeQuery("SELECT Codice, NumeroOrdini FROM Cliente");
            while (result.next()){
                res=res+result.getString("Codice") + "   " + result.getInt("NumeroOrdini") + "\n";
            }
        }
        catch(Exception exception){
        	JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
        }
        return res;
    }




    public String ese7(String aTipoServizio, String aNomeRistorante, String aIndirizzoRistorante, String aSocietaEsterna) throws ClassNotFoundException, SQLException{
        String res="";
    	try{
            Statement query = cn.createStatement();
            int rs;
            rs=query.executeUpdate("INSERT INTO Affidamento VALUES (\"" + aTipoServizio + "\", \"" + aNomeRistorante + "\", \"" + aIndirizzoRistorante + "\", \"" + aSocietaEsterna + "\");");
            res="("+rs+") rows affected";
        }
        catch(Exception exception){
        	JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
        }
        return res;
    }




    public String ese8(String aCodice, String aNomeRistorante, String aIndirizzoRistorante, String aDataPresaServizio, String aTipoContratto) throws ClassNotFoundException{
        String res="";
    	try{
        	Statement query = cn.createStatement();
            int rs;
            rs=query.executeUpdate("INSERT INTO Assunzione Values (\"" + aCodice + "\", \"" + aNomeRistorante + "\", \"" + aIndirizzoRistorante + "\", \"" + aDataPresaServizio + "\", \"" + aTipoContratto + "\");"); 
            res="("+rs+") rows affected";
    	}
        catch(Exception exception){
        	JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
        }
        return res;
    }



    public String ese9() throws ClassNotFoundException{
    	String res="";
        try{
            Statement query = cn.createStatement();
            ResultSet result = query.executeQuery("SELECT NomeRistorante, IndirizzoRistorante FROM Affidamento WHERE NomeRistorante <> (SELECT NomeRistorante FROM Affidamento WHERE SocEsterna <> null OR SocEsterna <> \"Food Delivery\") AND IndirizzoRistorante <> (SELECT NomeRistorante FROM Affidamento WHERE SocEsterna <> null OR SocEsterna <> \"Food Delivery\"); ");
            while (result.next()){
                res=res+result.getString("NomeRistorante") + "   " + result.getString("IndirizzoRistorante") + "\n";
            }
        }
        catch(Exception exception){
        	JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
        }
        return res;
    }

    public String ese10() throws ClassNotFoundException{
    	String res="";
        try{
            Statement query = cn.createStatement();
            ResultSet result = query.executeQuery("select * from Ordine O where O.Rider IS NOT NULL AND O.Rider NOT IN(\n"
            		+ "Select V.Rider from Valutazione V);");
            while (result.next()){
                res=res+result.getInt("Codice") + "   " + result.getString("Rider") + "   " + result.getString("NomeRistorante") + "   " + result.getString("IndirizzoRistorante") + "   " + result.getString("Cliente") + "   " + result.getString("DataOrdine") + "   " + result.getFloat("Costo") + "   " + result.getString("Descrizione") + "   " + result.getString("Acquisto") + "   " + result.getString("StatoConsegna") + "   " + result.getString("OrarioConsegnaPresunto") + "   " + result.getString("OrarioConsegnaEffettivo") + "\n";
            }
        }
        catch(Exception exception){
        	JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
        }
        return res;
    }
	
	public String ese11(String codice,String data) {
		String res="";
		try {
			String sql="DELETE FROM Ordine WHERE Codice='"+codice+"' AND DataOrdine='"+data+"' AND StatoConsegna<>'Consegnato';";
			int rs;
			Statement st;
			st=cn.createStatement();
			rs=st.executeUpdate(sql);
			res="("+rs+")"+" rows affected";
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
		}
		return res;
	}
	
	public String ese12() {
		String res="";
		try {
			String sql="SELECT Rider.Nome AS NomeRider, Dipendente.Nome AS NomeDipendente\n"
					+ "FROM Ordine LEFT JOIN Rider ON Ordine.rider = Rider.codice LEFT JOIN Dipendente ON Ordine.dipendente = Dipendente.codice\n"
					+ "WHERE Ordine.Cliente = (Select Codice FROM Cliente Where Nome = 'Giuseppe Verdi');";
			ResultSet rs;
			Statement st;
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString("NomeDipendente")==null) res=res+"Rider: "+rs.getString("NomeRider")+"\n";
				if(rs.getString("NomeRider")==null) res=res+"Dipendente: "+rs.getString("NomeDipendente")+"\n";
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
		}
		return res;
	}
	
	public String ese13() {
		String res="";
		try {
			String sql="SELECT R.Nome,R.Indirizzo,O.Codice,O.DataOrdine FROM Ordine O JOIN Ristorante R ON O.NomeRistorante=R.Nome AND O.IndirizzoRistorante=R.Indirizzo WHERE StatoConsegna<>'Consegnato';";
			ResultSet rs;
			Statement st;
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				res=res+"[Nome]: "+rs.getString(1)+" [Indirizzo]: "+rs.getString(2)+"\t->\t[Codice]: "+rs.getString(3)+" [Data]: "+rs.getDate(4)+"\n";
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
		}
		return res;
	}
	public String ese14() {
		String res="";
		try {
			String sql="SELECT R.Nome, AVG(V.Voto) FROM Rider R JOIN Valutazione V on R.Codice=V.Rider GROUP BY R.Codice";
			ResultSet rs;
			Statement st;
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				res=res+"Nome= "+rs.getString("R.Nome")+" Valutazione media= "+rs.getFloat("AVG(V.Voto)")+"\n";
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
		}
		return res;
	}
	
	public String ese15() {
		String res="";
		try {
			String sql="SELECT C.Nome, V.Voto FROM Cliente C JOIN Valutazione V on C.Codice=V.Cliente "
					+ "WHERE V.Voto<"
					+ "(SELECT AVG(W.Voto) FROM Valutazione W WHERE W.Rider=V.Rider);";
			ResultSet rs;
			Statement st;
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				res=res+"Nome= "+rs.getString("C.Nome")+" Valutazione= "+rs.getInt("V.Voto")+"\n";
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Dati inseriti non validi");
		}
		return res;
	}
	
	private Connection cn;
}
