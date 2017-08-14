package views;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import models.Database;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddVoiture extends Dialog {

	protected boolean result;
	protected Shell shell;
	private String email;
	private Text modele;
	private Text immat;
	Combo comboMarques;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddVoiture(Shell parent, String email) {
		super(parent, SWT.DIALOG_TRIM);
		setText("Nouvelle voiture");
		this.email = email;
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
		shell.setSize(179, 242);
		shell.setText(getText());
		
		Label lblMarque = new Label(shell, SWT.NONE);
		lblMarque.setBounds(10, 10, 55, 15);
		lblMarque.setText("Marque :");
		
		comboMarques = new Combo(shell, SWT.NONE);
		comboMarques.setBounds(10, 31, 150, 23);
		addMarques();
		
		Label lblModle = new Label(shell, SWT.NONE);
		lblModle.setBounds(10, 60, 55, 15);
		lblModle.setText("Modèle :");
		
		modele = new Text(shell, SWT.BORDER);
		modele.setBounds(10, 81, 150, 23);
		
		Label lblImmatriculation = new Label(shell, SWT.NONE);
		lblImmatriculation.setBounds(10, 110, 150, 15);
		lblImmatriculation.setText("Immatriculation :");
		
		immat = new Text(shell, SWT.BORDER);
		immat.setBounds(10, 131, 150, 23);
		
		Button btnAjouter = new Button(shell, SWT.NONE);
		btnAjouter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (comboMarques.getSelectionIndex() != -1) {
					Database.getInstance().addVoiture(immat.getText(), comboMarques.getText(), modele.getText(), email);
					result = true;
					shell.close();
				} else {
					InfoDialog info = new InfoDialog(shell, "Vous devez sélectionner une marque");
					info.open();
				}
			}
		});
		btnAjouter.setBounds(85, 172, 75, 25);
		btnAjouter.setText("Ajouter");

	}
	
	private void addMarques() {
		ResultSet rs = Database.getInstance().getMarques();
		try {
			while (rs.next()) {	
				String a = rs.getString("marque");
				comboMarques.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
