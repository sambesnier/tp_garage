package views;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class InfoDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private String msg;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public InfoDialog(Shell parent, String message) {
		super(parent, SWT.DIALOG_TRIM);
		setText("Infos");
		msg = message;
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
		shell.setSize(309, 125);
		shell.setText(getText());
		
		Label message = new Label(shell, SWT.NONE);
		message.setBounds(24, 10, 256, 38);
		message.setText(msg);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnNewButton.setBounds(119, 61, 75, 25);
		btnNewButton.setText("Ok");

	}

}
