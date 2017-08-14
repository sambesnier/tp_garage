package views;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import models.Database;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddClient extends Dialog {

	protected boolean result;
	protected Shell shell;
	private Text nom;
	private Text prenom;
	private Text email;
	private Text adresse;
	private Text codePostal;
	private Text ville;
	private Text telephone;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddClient(Shell parent, int style) {
		super(parent, SWT.DIALOG_TRIM);
		setText("Nouveau client");
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
		shell = new Shell(getParent(), getStyle());
		shell.setSize(226, 422);
		shell.setText(getText());
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		
		Label lblNom = new Label(composite, SWT.NONE);
		lblNom.setBounds(10, 10, 55, 15);
		lblNom.setText("Nom :");
		
		nom = new Text(composite, SWT.BORDER);
		nom.setBounds(10, 31, 200, 21);
		
		Label lblPrnom = new Label(composite, SWT.NONE);
		lblPrnom.setBounds(10, 58, 55, 15);
		lblPrnom.setText("Prénom :");
		
		prenom = new Text(composite, SWT.BORDER);
		prenom.setBounds(10, 79, 200, 21);
		
		Label lblEmail = new Label(composite, SWT.NONE);
		lblEmail.setBounds(10, 106, 55, 15);
		lblEmail.setText("Email :");
		
		email = new Text(composite, SWT.BORDER);
		email.setBounds(10, 127, 200, 21);
		
		Label lblAdresse = new Label(composite, SWT.NONE);
		lblAdresse.setBounds(10, 202, 55, 15);
		lblAdresse.setText("Adresse :");
		
		adresse = new Text(composite, SWT.BORDER);
		adresse.setBounds(10, 223, 200, 21);
		
		Label lblCodePostal = new Label(composite, SWT.NONE);
		lblCodePostal.setBounds(10, 250, 81, 15);
		lblCodePostal.setText("Code postal :");
		
		codePostal = new Text(composite, SWT.BORDER);
		codePostal.setBounds(10, 271, 200, 21);
		
		Label lblVille = new Label(composite, SWT.NONE);
		lblVille.setBounds(10, 298, 55, 15);
		lblVille.setText("Ville :");
		
		ville = new Text(composite, SWT.BORDER);
		ville.setBounds(10, 319, 200, 21);
		
		Button btnAjouter = new Button(composite, SWT.NONE);
		btnAjouter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Database.getInstance().addClient(
						nom.getText(), 
						prenom.getText(), 
						email.getText(), 
						telephone.getText(), 
						adresse.getText(), 
						codePostal.getText(), 
						ville.getText());
				result = true;
				shell.close();
				
			}
		});
		btnAjouter.setBounds(135, 355, 75, 25);
		btnAjouter.setText("Ajouter");
		
		Label lblTlphone = new Label(composite, SWT.NONE);
		lblTlphone.setBounds(10, 154, 81, 15);
		lblTlphone.setText("Téléphone :");
		
		telephone = new Text(composite, SWT.BORDER);
		telephone.setBounds(10, 175, 200, 21);

	}
}
