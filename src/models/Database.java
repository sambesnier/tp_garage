package models;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

public class Database {
	
	private static Database INSTANCE = null;
	
	private Connection _connexion;
	
	private Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion();
		} catch (ClassNotFoundException e) {
			System.out.println("Classe non trouv�e");
		}
	}
	
	public static Database getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Database();
		}
		return INSTANCE;
	}
	
	private void connexion() {
		String url = "jdbc:mysql://localhost:3306/tp_garage";
		String utilisateur = "root";
		String motDePasse = "";
		try {
		    set_connexion((Connection) DriverManager.getConnection( url, utilisateur, motDePasse ));
		    /* Ici, nous placerons nos requêtes vers la BDD */
		    /* ... */
		    System.out.println("connexion réussie");

		} catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
		}
	}

	private Connection get_connexion() {
		return _connexion;
	}

	private void set_connexion(Connection _connexion) {
		this._connexion = _connexion;
	}
	
	public void addClient(
			String nom, 
			String prenom,
			String email,
			String telephone,
			String adresse,
			String codePostal,
			String ville) {
		String insertClient = "INSERT INTO clients (nom,prenom,email,telephone) VALUES (?,?,?,?)";
		String insertAdresse = "INSERT INTO adresses (adresse, code_postal, ville, client) VALUES (?,?,?,?)";
		try {
			_connexion.setAutoCommit(false);
			PreparedStatement prst = _connexion.prepareStatement(insertClient);
			prst.setString(1, nom);
			prst.setString(2, prenom);
			prst.setString(3, email);
			prst.setString(4, telephone);
			prst.execute();
			
			PreparedStatement prst2 = _connexion.prepareStatement(insertAdresse);
			prst2.setString(1, adresse);
			prst2.setString(2, codePostal);
			prst2.setString(3, ville);
			prst2.setString(4, email);
			prst2.execute();
			
			_connexion.commit();
			
			prst.close();
			prst2.close();
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				_connexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public ResultSet getClients() {		
		Statement st;
		ResultSet rs = null;
		try {
			st = _connexion.createStatement();
			String sql = "SELECT c.nom, c.prenom, c.email, c.telephone\r\n" + 
					"FROM clients as c";
			rs = st.executeQuery(sql);
			
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void deleteClient(String email) {
		try {
			String delAdresse = "DELETE from adresses WHERE client = ?";
			String delClient = "DELETE from clients WHERE email = ?";
			
			_connexion.setAutoCommit(false);
			
			PreparedStatement prst2 = _connexion.prepareStatement(delAdresse);
			prst2.setString(1, email);
			prst2.execute();
			
			PreparedStatement prst3 = _connexion.prepareStatement(delClient);
			prst3.setString(1, email);
			prst3.execute();
			
			_connexion.commit();

			prst2.close();
			prst3.close();
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				_connexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void updateClient(
			String nom, 
			String prenom,
			String email,
			int numVoie,
			String voie,
			int codePostal,
			String ville,
			String newEmail) {
		String clientId = "SELECT id_client from clients WHERE email = ?";
		String updateClient = "UPDATE clients SET nom = ?, prenom = ?, email = ? WHERE email = ?";
		String updateAdresse = "UPDATE adresses SET num_voie = ?, voie = ?, code_postal = ?, ville = ? WHERE client = ?";
		try {
			_connexion.setAutoCommit(false);
			PreparedStatement prst = _connexion.prepareStatement(updateClient);
			prst.setString(1, nom);
			prst.setString(2, prenom);
			prst.setString(3, newEmail);
			prst.setString(4, email);
			prst.execute();

			PreparedStatement prstId = _connexion.prepareStatement(clientId);
			prstId.setString(1, email);
			prstId.execute();
			ResultSet rs = prstId.executeQuery();
			rs.next();
			long id = rs.getLong("id_client");
			
			PreparedStatement prst2 = _connexion.prepareStatement(updateAdresse);
			prst2.setInt(1, numVoie);
			prst2.setString(2, voie);
			prst2.setInt(3, codePostal);
			prst2.setString(4, ville);
			prst2.setLong(5, id);
			prst2.execute();
			
			_connexion.commit();
			
			prst.close();
			prst2.close();
			prstId.close();
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				_connexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public ResultSet getMarques() {		
		Statement st;
		ResultSet rs = null;
		try {
			st = _connexion.createStatement();
			String sql = "SELECT marque FROM marques";
			rs = st.executeQuery(sql);
			
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet getPrestations() {		
		Statement st;
		ResultSet rs = null;
		try {
			st = _connexion.createStatement();
			String sql = "SELECT prestation FROM prestations";
			rs = st.executeQuery(sql);
			
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public double getPrixPresta(String presta) {
		double prix = 0.0;
		String getPrix = "SELECT montant FROM prestations WHERE prestation = ?";
		PreparedStatement prst;
		try {
			prst = _connexion.prepareStatement(getPrix);
			prst.setString(1, presta);
			ResultSet rs = prst.executeQuery();
			rs.next();
			prix = rs.getDouble("montant");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prix;
	}
	
	public void AddRdv(String client, String immat, int heure, int jour, int semaine, String[] prestas) {
		String insertFacture = "INSERT INTO factures (client, voiture, status, montant) VALUES (?,?,?,?)";
		String facturePrestas = "INSERT INTO factures_prestations (facture_id, prestation) VALUES (?,?)";
		String insertRdv = "INSERT INTO rdv (facture_id, heure, jour, semaine) VALUES (?,?,?,?)";
		String getMontant = "SELECT montant FROM prestations WHERE prestation = ?";
		
		try {
			_connexion.setAutoCommit(false);
			
			double montant = 0.0;
			for (int i = 0; i < prestas.length; i++) {
				PreparedStatement prst4 = _connexion.prepareStatement(getMontant);
				prst4.setString(1, prestas[i]);
				ResultSet rs = prst4.executeQuery();
				rs.next();
				montant += rs.getDouble("montant");
			}
			
			PreparedStatement prst = _connexion.prepareStatement(insertFacture, Statement.RETURN_GENERATED_KEYS);
			prst.setString(1, client);
			prst.setString(2, immat);
			prst.setInt(3, 0);
			prst.setDouble(4, montant);
			prst.execute();
			ResultSet rs = prst.getGeneratedKeys();
			long id = 0;
			if (rs.next()) {
				id = rs.getLong(1);
			}
			
			for (int i = 0; i < prestas.length; i++) {
				PreparedStatement prst3 = _connexion.prepareStatement(facturePrestas);
				prst3.setLong(1, id);
				prst3.setString(2, prestas[i]);
				prst3.execute();
			}
			
			PreparedStatement prst2 = _connexion.prepareStatement(insertRdv);
			prst2.setLong(1, id);
			prst2.setInt(2, heure);
			prst2.setInt(3, jour);
			prst2.setInt(4, semaine);			
			prst2.execute();
			
			_connexion.commit();
			
			prst.close();
			prst2.close();
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				_connexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public ResultSet getFacturesByClient(String client) {
		ResultSet rs = null;
		String getFactures = "SELECT f.created_at, f.status, f.montant\r\n" + 
				"FROM factures as f\r\n" +
				"WHERE f.client = ?";
		try {
			PreparedStatement prst = _connexion.prepareStatement(getFactures);
			prst.setString(1, client);
			rs = prst.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet getFacturesByCar(String immat) {
		ResultSet rs = null;
		String getFactures = "SELECT f.created_at, f.status, f.montant\r\n" + 
				"FROM factures as f\r\n" +
				"WHERE f.voiture = ?";
		try {
			PreparedStatement prst = _connexion.prepareStatement(getFactures);
			prst.setString(1, immat);
			rs = prst.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet getFacture(String date, String client) {
		ResultSet rs = null;
		String getFacture = "SELECT f.created_at, f.montant, c.nom, c.prenom, c.telephone, c.email, v.marque, v.modele, v.immatriculation, p.prestation, a.adresse, a.code_postal, a.ville\r\n" + 
				"FROM factures as f\r\n" + 
				"INNER JOIN voitures as v\r\n" + 
				"INNER JOIN clients as c\r\n" + 
				"INNER JOIN prestations as p\r\n" + 
				"INNER JOIN factures_prestations as fp\r\n" + 
				"INNER JOIN adresses as a\r\n" +
				"WHERE f.client = ?\r\n" + 
				"AND f.voiture = v.immatriculation\r\n" + 
				"AND f.created_at = ?\r\n" + 
				"AND fp.facture_id = f.id_facture\r\n" + 
				"AND a.client = f.client\r\n" +
				"AND fp.prestation = p.prestation";
		
		try {
			PreparedStatement prst = _connexion.prepareStatement(getFacture);
			prst.setString(1, client);
			prst.setString(2, date);
			rs = prst.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void addVoiture(String immat, String marque, String modele, String client) {
		String insertVoiture = "INSERT INTO voitures (immatriculation, marque, modele, client) VALUES (?,?,?,?)";
		
		PreparedStatement prst;
		try {
			prst = _connexion.prepareStatement(insertVoiture);
			prst.setString(1, immat);
			prst.setString(2, marque);
			prst.setString(3, modele);
			prst.setString(4, client);
			prst.execute();
			prst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public ResultSet getVoituresByEmail(String email) {
		String getVoitures = "SELECT marque, modele, immatriculation FROM voitures WHERE client = ?";
		ResultSet rs = null;
		
		try {
			PreparedStatement prst = _connexion.prepareStatement(getVoitures);
			prst.setString(1, email);
			rs = prst.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet getRdvParSemaine(int semaine) {
		ResultSet rs = null;
		String getRdvs = "SELECT heure, jour FROM rdv WHERE semaine = ?";
		
		try {
			PreparedStatement prst = _connexion.prepareStatement(getRdvs);
			prst.setInt(1, semaine);
			rs = prst.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void deleteVoitures(String immat) {
		try {
			String delVoiture = "DELETE from voitures WHERE immatriculation = ?";
			
			PreparedStatement prst = _connexion.prepareStatement(delVoiture);
			prst.setString(1, immat);
			prst.execute();

			prst.close();
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				_connexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
