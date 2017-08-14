package views;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class ViewFacture extends Dialog {

	protected Object result;
	protected Shell shell;
	private ResultSet rs;
	private Label nom;
	private Label prenom;
	private Label email;
	private Label telephone;
	private Label marque;
	private Label modele;
	private Label immat;
	private Label date;
	private Label montant;
	private List listPrestas;
	private Label lblAdresse;
	private Label adresse;
	private Label lblCodePostal;
	private Label codePostal;
	private Label lblVille;
	private Label ville;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ViewFacture(Shell parent, ResultSet r) {
		super(parent, SWT.DIALOG_TRIM);
		rs = r;
		setText("Facture");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
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
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 472);
		shell.setText(getText());
		
		listPrestas = new List(shell, SWT.BORDER);
		listPrestas.setBounds(10, 280, 240, 153);
		
		Label lblNom = new Label(shell, SWT.NONE);
		lblNom.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNom.setBounds(10, 10, 55, 15);
		lblNom.setText("Nom : ");
		
		nom = new Label(shell, SWT.NONE);
		nom.setBounds(10, 31, 187, 15);
		
		Label lblPrnom = new Label(shell, SWT.NONE);
		lblPrnom.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblPrnom.setBounds(10, 52, 55, 15);
		lblPrnom.setText("Prénom :");
		
		prenom = new Label(shell, SWT.NONE);
		prenom.setBounds(10, 74, 187, 15);
		
		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblEmail.setBounds(10, 95, 55, 15);
		lblEmail.setText("Email :");
		
		email = new Label(shell, SWT.NONE);
		email.setBounds(10, 116, 187, 15);
		
		Label lblTlphone = new Label(shell, SWT.NONE);
		lblTlphone.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblTlphone.setBounds(10, 137, 80, 15);
		lblTlphone.setText("Téléphone :");
		
		telephone = new Label(shell, SWT.NONE);
		telephone.setBounds(10, 158, 187, 15);
		
		Label lblMarque = new Label(shell, SWT.NONE);
		lblMarque.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblMarque.setBounds(10, 179, 55, 15);
		lblMarque.setText("Marque :");
		
		marque = new Label(shell, SWT.NONE);
		marque.setBounds(10, 200, 187, 15);
		
		Label lblModle = new Label(shell, SWT.NONE);
		lblModle.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblModle.setBounds(10, 221, 55, 15);
		lblModle.setText("Modèle :");
		
		modele = new Label(shell, SWT.NONE);
		modele.setBounds(10, 242, 187, 15);
		
		Label lblImmatriculation = new Label(shell, SWT.NONE);
		lblImmatriculation.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblImmatriculation.setBounds(252, 179, 145, 15);
		lblImmatriculation.setText("Immatriculation :");
		
		immat = new Label(shell, SWT.NONE);
		immat.setBounds(252, 200, 182, 15);
		
		Label lblDate = new Label(shell, SWT.NONE);
		lblDate.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblDate.setBounds(256, 335, 55, 15);
		lblDate.setText("Date :");
		
		date = new Label(shell, SWT.NONE);
		date.setBounds(256, 356, 182, 15);
		
		Label lblMontant = new Label(shell, SWT.NONE);
		lblMontant.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblMontant.setBounds(256, 280, 69, 15);
		lblMontant.setText("Montant :");
		
		montant = new Label(shell, SWT.NONE);
		montant.setBounds(266, 301, 70, 15);
		
		lblAdresse = new Label(shell, SWT.NONE);
		lblAdresse.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblAdresse.setBounds(252, 10, 55, 15);
		lblAdresse.setText("Adresse :");
		
		adresse = new Label(shell, SWT.NONE);
		adresse.setBounds(252, 31, 182, 15);
		
		lblCodePostal = new Label(shell, SWT.NONE);
		lblCodePostal.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblCodePostal.setBounds(252, 52, 91, 15);
		lblCodePostal.setText("Code postal :");
		
		codePostal = new Label(shell, SWT.NONE);
		codePostal.setBounds(252, 74, 182, 15);
		
		lblVille = new Label(shell, SWT.NONE);
		lblVille.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblVille.setBounds(252, 95, 55, 15);
		lblVille.setText("Ville :");
		
		ville = new Label(shell, SWT.NONE);
		ville.setBounds(252, 116, 182, 15);
		
		setFacture();
	}
	
	private void setFacture() {
		try {
			while (rs.next()) {
				nom.setText(rs.getString("nom"));
				prenom.setText(rs.getString("prenom"));
				email.setText(rs.getString("email"));
				telephone.setText(rs.getString("telephone"));
				marque.setText(rs.getString("marque"));
				modele.setText(rs.getString("modele"));
				immat.setText(rs.getString("immatriculation"));
				date.setText(rs.getString("created_at"));
				montant.setText(rs.getString("montant") + "€");
				listPrestas.add(rs.getString("prestation"));
				adresse.setText(rs.getString("adresse"));
				codePostal.setText(rs.getString("code_postal"));
				ville.setText(rs.getString("ville"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
