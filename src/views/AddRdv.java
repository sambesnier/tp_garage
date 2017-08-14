package views;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import models.Database;

public class AddRdv extends Dialog {

	protected boolean result;
	protected Shell shell;
	private Table tableClients;
	private Table tableVoitures;
	private Table tablePrestas;
	private int heure;
	private int jour;
	private int semaine;
	private Label date;
	private Combo comboPrestas;
	private Label totalLabel;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddRdv(Shell parent, int heure, int jour, int semaine) {
		super(parent, SWT.DIALOG_TRIM);
		this.heure = heure;
		this.jour = jour;
		this.semaine = semaine;
		setText("Prise de rdv");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public boolean open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setSize(623, 448);
		shell.setText(getText());
		shell.setLayout(null);
		
		tableClients = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableClients.setBounds(10, 10, 433, 110);
		tableClients.setHeaderVisible(true);
		tableClients.setLinesVisible(true);
		
		tableClients.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				remplirTableVoitures();
			}
		});
		
		TableColumn tblclmnNom = new TableColumn(tableClients, SWT.NONE);
		tblclmnNom.setWidth(100);
		tblclmnNom.setText("Nom");
		
		TableColumn tblclmnPrnom = new TableColumn(tableClients, SWT.NONE);
		tblclmnPrnom.setWidth(100);
		tblclmnPrnom.setText("Prénom");
		
		TableColumn tblclmnEmail = new TableColumn(tableClients, SWT.NONE);
		tblclmnEmail.setWidth(131);
		tblclmnEmail.setText("Email");
		
		TableColumn tblclmnTlphone = new TableColumn(tableClients, SWT.NONE);
		tblclmnTlphone.setWidth(100);
		tblclmnTlphone.setText("Téléphone");
		
		tableVoitures = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableVoitures.setBounds(10, 126, 433, 110);
		tableVoitures.setHeaderVisible(true);
		tableVoitures.setLinesVisible(true);
		
		TableColumn tblclmnMarque = new TableColumn(tableVoitures, SWT.NONE);
		tblclmnMarque.setWidth(149);
		tblclmnMarque.setText("Marque");
		
		TableColumn tblclmnModle = new TableColumn(tableVoitures, SWT.NONE);
		tblclmnModle.setWidth(148);
		tblclmnModle.setText("Modèle");
		
		TableColumn tblclmnImmatriculation = new TableColumn(tableVoitures, SWT.NONE);
		tblclmnImmatriculation.setWidth(131);
		tblclmnImmatriculation.setText("Immatriculation");
		
		tablePrestas = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tablePrestas.setBounds(10, 242, 433, 110);
		tablePrestas.setHeaderVisible(true);
		tablePrestas.setLinesVisible(true);
		
		TableColumn tblclmnPrestation = new TableColumn(tablePrestas, SWT.NONE);
		tblclmnPrestation.setWidth(329);
		tblclmnPrestation.setText("Prestation");
		
		TableColumn tblclmnMontant = new TableColumn(tablePrestas, SWT.NONE);
		tblclmnMontant.setWidth(100);
		tblclmnMontant.setText("Montant");
		
		comboPrestas = new Combo(shell, SWT.NONE);
		comboPrestas.setBounds(449, 242, 158, 23);
		
		Button btnAjouterPrestation = new Button(shell, SWT.NONE);
		btnAjouterPrestation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					tableVoitures.getSelection()[0].getText(2);
					if (comboPrestas.getSelectionIndex() != -1) {
						addPresta(comboPrestas.getText());
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					InfoDialog info = new InfoDialog(shell, "Vous devez sélectionner une voiture");
					info.open();
				}
			}
		});
		btnAjouterPrestation.setBounds(449, 271, 158, 23);
		btnAjouterPrestation.setText("Ajouter prestation");
		
		Button btnSupprimerPrestation = new Button(shell, SWT.NONE);
		btnSupprimerPrestation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					tablePrestas.remove(tablePrestas.getSelectionIndex());
					calculTotal();
				} catch (IllegalArgumentException e2) {
					InfoDialog info = new InfoDialog(shell, "Vous devez sélectionner une prestation");
					info.open();
				}
			}
		});
		btnSupprimerPrestation.setBounds(449, 300, 158, 23);
		btnSupprimerPrestation.setText("Supprimer prestation");
		
		Button btnNouveauClient = new Button(shell, SWT.NONE);
		btnNouveauClient.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddClient ac = new AddClient(shell, 1);
				ac.open();
				if (ac.result == true) {
					remplirTableClients();
				}
			}
		});
		btnNouveauClient.setBounds(449, 10, 158, 23);
		btnNouveauClient.setText("Nouveau client");
		
		Button btnNouvelleVoiture = new Button(shell, SWT.NONE);
		btnNouvelleVoiture.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					AddVoiture av = new AddVoiture(shell, tableClients.getSelection()[0].getText(2));
					av.open();
					if (av.result == true) {
						remplirTableVoitures();
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					InfoDialog info = new InfoDialog(shell, "Vous devez sélectionner un client");
					info.open();
				}
			}
		});
		btnNouvelleVoiture.setBounds(449, 126, 158, 23);
		btnNouvelleVoiture.setText("Nouvelle voiture");
		
		Button btnAddRdv = new Button(shell, SWT.NONE);
		btnAddRdv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (tablePrestas.getItemCount() != 0) {
					String client = tableClients.getSelection()[0].getText(2);
					String immat = tableVoitures.getSelection()[0].getText(2);
					String[] prestas = new String[tablePrestas.getItemCount()];
					for (int i = 0; i < tablePrestas.getItemCount(); i++) {
						prestas[i] = tablePrestas.getItems()[i].getText(0);
					}
					Database.getInstance().AddRdv(client, immat, heure, jour, semaine, prestas);
					result = true;
					shell.close();
				} else {
					InfoDialog info = new InfoDialog(shell, "Vous devez ajouter au moins une prestation");
					info.open();
				}
			}
		});
		btnAddRdv.setBounds(449, 377, 158, 23);
		btnAddRdv.setText("Prendre rdv");
		
		Label lblTotal = new Label(shell, SWT.NONE);
		lblTotal.setBounds(10, 358, 40, 15);
		lblTotal.setText("Total :");
		
		totalLabel = new Label(shell, SWT.NONE);
		totalLabel.setBounds(56, 358, 55, 15);
		
		
		Label lblDate = new Label(shell, SWT.NONE);
		lblDate.setBounds(10, 385, 40, 15);
		lblDate.setText("Date : ");
		
		date = new Label(shell, SWT.NONE);
		date.setBounds(56, 385, 266, 15);
		
		setTodayDate();
		remplirTableClients();
		addPrestation();
	}
	
	private void setTodayDate() {
		String j = null;
		switch (jour) {
		case 2:
			j = "Lundi";
			break;
		case 3:
			j = "Mardi";
			break;
		case 4:
			j = "Mercredi";
			break;
		case 5:
			j = "Jeudi";
			break;
		case 6:
			j = "Vendredi";
			break;

		default:
			break;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR, semaine);        
		cal.set(Calendar.DAY_OF_WEEK, jour);
		
		date.setText(j + " " + sdf.format(cal.getTime()) + " à " + heure + ":00");
	}
	
	private void addPresta(String presta) {
		TableItem item = new TableItem(tablePrestas, SWT.NONE);
		String prix = String.valueOf(Database.getInstance().getPrixPresta(presta));
		item.setText(new String[] {presta, prix});
		calculTotal();
	}
	
	private void remplirTableClients() {
		tableClients.removeAll();
		ResultSet rs = Database.getInstance().getClients();
		try {
			while (rs.next()) {	
				String a = rs.getString("nom");
				String b = rs.getString("prenom");
				String c = rs.getString("email");
				String d = rs.getString("telephone");
				
				TableItem item = new TableItem(tableClients, SWT.NONE);
				item.setText(new String[] {a, b, c, d});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void calculTotal() {
		TableItem[] items = tablePrestas.getItems();
		double prix = 0.0;
		for (int i = 0; i < items.length; i++) {
			prix += Double.parseDouble(items[i].getText(1));
		}
		totalLabel.setText(String.valueOf(prix) + "€");
	}
	
	private void remplirTableVoitures() {
		tableVoitures.removeAll();
		ResultSet rs = Database.getInstance().getVoituresByEmail(tableClients.getSelection()[0].getText(2));
		try {
			while (rs.next()) {
				String a = rs.getString("marque");
				String b = rs.getString("modele");
				String c = rs.getString("immatriculation");
				
				TableItem item = new TableItem(tableVoitures, SWT.NONE);
				item.setText(new String[] {a, b, c});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addPrestation() {
		ResultSet rs = Database.getInstance().getPrestations();
		try {
			while (rs.next()) {	
				String a = rs.getString("prestation");
				comboPrestas.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
