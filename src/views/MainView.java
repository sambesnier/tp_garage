package views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import swing2swt.layout.BoxLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import models.Database;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainView {

	protected Shell shlGarage;
	private TabFolder tabFolder;
	private TabItem tbtmClients;
	private Composite composite;
	private Composite composite_1;
	private Composite composite_2;
	private Composite composite_3;
	private Table tableClients;
	private Composite composite_4;
	private Button btnNouveauClient;
	private Table tableVoitures;
	private Composite composite_5;
	private Button btnNouvelleVoiture;
	private Table tableFactures;
	private Composite composite_6;
	private Button btnVisualiserFacture;
	private TableColumn tblclmnNom;
	private TableColumn tblclmnPrenom;
	private TableColumn tblclmnEmail;
	private TableColumn tblclmnTelephone;
	private TableColumn tblclmnMarque;
	private TableColumn tblclmnModele;
	private TableColumn tblclmnImmatriculation;
	private TableColumn tblclmnDate;
	private TableColumn tblclmnMontant;
	private TableColumn tblclmnStatus;
	private TabItem tbtmPlanning;
	private Composite composite_7;
	private Composite composite_8;
	private Button wkPrev;
	private Button wkNext;
	private Composite composite_9;
	private Composite lundi;
	private Composite mardi;
	private Composite mercredi;
	private Composite jeudi;
	private Composite vendredi;
	private Composite composite_10;
	private Label lblSemaine;
	private Button lundi8;
	private Button lundi9;
	private Button lundi10;
	private Button lundi11;
	private Button lundi12;
	private Button lundi14;
	private Button lundi15;
	private Button lundi16;
	private Button lundi17;
	private Button lundi18;
	private Button mardi8;
	private Button mardi9;
	private Button mardi10;
	private Button mardi11;
	private Button mardi12;
	private Button mardi14;
	private Button mardi15;
	private Button mardi16;
	private Button mardi17;
	private Button mardi18;
	private Button mercredi8;
	private Button mercredi9;
	private Button mercredi10;
	private Button mercredi11;
	private Button mercredi12;
	private Button mercredi14;
	private Button mercredi15;
	private Button mercredi16;
	private Button mercredi17;
	private Button mercredi18;
	private Button jeudi8;
	private Button jeudi9;
	private Button jeudi10;
	private Button jeudi11;
	private Button jeudi12;
	private Button jeudi14;
	private Button jeudi15;
	private Button jeudi16;
	private Button jeudi17;
	private Button jeudi18;
	private Button vendredi8;
	private Button vendredi9;
	private Button vendredi10;
	private Button vendredi11;
	private Button vendredi12;
	private Button vendredi14;
	private Button vendredi15;
	private Button vendredi16;
	private Button vendredi17;
	private Button vendredi18;
	private Label wkNumber;
	private int wk;

	private Calendar cal = Calendar.getInstance();

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlGarage.open();
		shlGarage.layout();
		while (!shlGarage.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlGarage = new Shell();
		shlGarage.setSize(643, 429);
		shlGarage.setText("Garage");
		shlGarage.setLayout(new FillLayout(SWT.HORIZONTAL));

		tabFolder = new TabFolder(shlGarage, SWT.NONE);

		tbtmPlanning = new TabItem(tabFolder, SWT.NONE);
		tbtmPlanning.setText("Planning");

		composite_7 = new Composite(tabFolder, SWT.NONE);
		tbtmPlanning.setControl(composite_7);
		composite_7.setLayout(new FillLayout(SWT.HORIZONTAL));

		composite_8 = new Composite(composite_7, SWT.NONE);
		composite_8.setLayout(new BorderLayout(0, 0));

		wkPrev = new Button(composite_8, SWT.NONE);
		wkPrev.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int wkN = Integer.parseInt(wkNumber.getText()) - 1;
				wkNumber.setText(String.valueOf(wkN));
				wk = wkN;
				setPlanning();
			}
		});
		wkPrev.setLayoutData(BorderLayout.WEST);
		wkPrev.setText("<");

		wkNext = new Button(composite_8, SWT.NONE);
		wkNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int wkN = Integer.parseInt(wkNumber.getText()) + 1;
				wkNumber.setText(String.valueOf(wkN));
				wk = wkN;
				setPlanning();
			}
		});
		wkNext.setLayoutData(BorderLayout.EAST);
		wkNext.setText(">");

		composite_9 = new Composite(composite_8, SWT.NONE);
		composite_9.setLayoutData(BorderLayout.CENTER);
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));

		lundi = new Composite(composite_9, SWT.NONE);
		lundi.setLayout(new FillLayout(SWT.VERTICAL));

		lundi8 = new Button(lundi, SWT.NONE);
		lundi8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 8, 2, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		lundi8.setText("8:00");

		lundi9 = new Button(lundi, SWT.NONE);
		lundi9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 9, 2, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		lundi9.setText("9:00");

		lundi10 = new Button(lundi, SWT.NONE);
		lundi10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 10, 2, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		lundi10.setText("10:00");

		lundi11 = new Button(lundi, SWT.NONE);
		lundi11.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 11, 2, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		lundi11.setText("11:00");

		lundi12 = new Button(lundi, SWT.NONE);
		lundi12.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 12, 2, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		lundi12.setText("12:00");

		lundi14 = new Button(lundi, SWT.NONE);
		lundi14.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 14, 2, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		lundi14.setText("14:00");

		lundi15 = new Button(lundi, SWT.NONE);
		lundi15.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 15, 2, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		lundi15.setText("15:00");

		lundi16 = new Button(lundi, SWT.NONE);
		lundi16.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 16, 2, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		lundi16.setText("16:00");

		lundi17 = new Button(lundi, SWT.NONE);
		lundi17.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 17, 2, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		lundi17.setText("17:00");

		lundi18 = new Button(lundi, SWT.NONE);
		lundi18.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 18, 2, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		lundi18.setText("18:00");

		mardi = new Composite(composite_9, SWT.NONE);
		mardi.setLayout(new FillLayout(SWT.VERTICAL));

		mardi8 = new Button(mardi, SWT.NONE);
		mardi8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 8, 3, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mardi8.setText("8:00");

		mardi9 = new Button(mardi, SWT.NONE);
		mardi9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 9, 3, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mardi9.setText("9:00");

		mardi10 = new Button(mardi, SWT.NONE);
		mardi10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 10, 3, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mardi10.setText("10:00");

		mardi11 = new Button(mardi, SWT.NONE);
		mardi11.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 11, 3, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mardi11.setText("11:00");

		mardi12 = new Button(mardi, SWT.NONE);
		mardi12.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 12, 3, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mardi12.setText("12:00");

		mardi14 = new Button(mardi, SWT.NONE);
		mardi14.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 14, 3, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mardi14.setText("14:00");

		mardi15 = new Button(mardi, SWT.NONE);
		mardi15.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 15, 3, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mardi15.setText("15:00");

		mardi16 = new Button(mardi, SWT.NONE);
		mardi16.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 16, 3, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mardi16.setText("16:00");

		mardi17 = new Button(mardi, SWT.NONE);
		mardi17.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 17, 3, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mardi17.setText("17:00");

		mardi18 = new Button(mardi, SWT.NONE);
		mardi18.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 18, 3, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mardi18.setText("18:00");
		
		mercredi = new Composite(composite_9, SWT.NONE);
		mercredi.setLayout(new FillLayout(SWT.VERTICAL));
		
		mercredi8 = new Button(mercredi, SWT.NONE);
		mercredi8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 8, 4, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mercredi8.setText("8:00");

		mercredi9 = new Button(mercredi, SWT.NONE);
		mercredi9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 9, 4, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mercredi9.setText("9:00");

		mercredi10 = new Button(mercredi, SWT.NONE);
		mercredi10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 10, 4, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mercredi10.setText("10:00");

		mercredi11 = new Button(mercredi, SWT.NONE);
		mercredi11.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 11, 4, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mercredi11.setText("11:00");

		mercredi12 = new Button(mercredi, SWT.NONE);
		mercredi12.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 12, 4, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mercredi12.setText("12:00");

		mercredi14 = new Button(mercredi, SWT.NONE);
		mercredi14.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 14, 4, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mercredi14.setText("14:00");

		mercredi15 = new Button(mercredi, SWT.NONE);
		mercredi15.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 15, 4, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mercredi15.setText("15:00");

		mercredi16 = new Button(mercredi, SWT.NONE);
		mercredi16.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 16, 4, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mercredi16.setText("16:00");

		mercredi17 = new Button(mercredi, SWT.NONE);
		mercredi17.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 17, 4, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mercredi17.setText("17:00");

		mercredi18 = new Button(mercredi, SWT.NONE);
		mercredi18.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 18, 4, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		mercredi18.setText("18:00");

		jeudi = new Composite(composite_9, SWT.NONE);
		jeudi.setLayout(new FillLayout(SWT.VERTICAL));

		jeudi8 = new Button(jeudi, SWT.NONE);
		jeudi8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 8, 5, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		jeudi8.setText("8:00");

		jeudi9 = new Button(jeudi, SWT.NONE);
		jeudi9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 9, 5, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		jeudi9.setText("9:00");

		jeudi10 = new Button(jeudi, SWT.NONE);
		jeudi10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 10, 5, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		jeudi10.setText("10:00");

		jeudi11 = new Button(jeudi, SWT.NONE);
		jeudi11.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 11, 5, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		jeudi11.setText("11:00");

		jeudi12 = new Button(jeudi, SWT.NONE);
		jeudi12.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 12, 5, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		jeudi12.setText("12:00");

		jeudi14 = new Button(jeudi, SWT.NONE);
		jeudi14.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 14, 5, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		jeudi14.setText("14:00");

		jeudi15 = new Button(jeudi, SWT.NONE);
		jeudi15.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 15, 5, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		jeudi15.setText("15:00");

		jeudi16 = new Button(jeudi, SWT.NONE);
		jeudi16.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 16, 5, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		jeudi16.setText("16:00");

		jeudi17 = new Button(jeudi, SWT.NONE);
		jeudi17.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 17, 5, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		jeudi17.setText("17:00");

		jeudi18 = new Button(jeudi, SWT.NONE);
		jeudi18.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 18, 5, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		jeudi18.setText("18:00");

		vendredi = new Composite(composite_9, SWT.NONE);
		vendredi.setLayout(new FillLayout(SWT.VERTICAL));

		vendredi8 = new Button(vendredi, SWT.NONE);
		vendredi8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 8, 6, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		vendredi8.setText("8:00");

		vendredi9 = new Button(vendredi, SWT.NONE);
		vendredi9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 9, 6, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		vendredi9.setText("9:00");

		vendredi10 = new Button(vendredi, SWT.NONE);
		vendredi10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 10, 6, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		vendredi10.setText("10:00");

		vendredi11 = new Button(vendredi, SWT.NONE);
		vendredi11.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 11, 6, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		vendredi11.setText("11:00");

		vendredi12 = new Button(vendredi, SWT.NONE);
		vendredi12.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 12, 6, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		vendredi12.setText("12:00");

		vendredi14 = new Button(vendredi, SWT.NONE);
		vendredi14.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 14, 6, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		vendredi14.setText("14:00");

		vendredi15 = new Button(vendredi, SWT.NONE);
		vendredi15.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 15, 6, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		vendredi15.setText("15:00");

		vendredi16 = new Button(vendredi, SWT.NONE);
		vendredi16.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 16, 6, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		vendredi16.setText("16:00");

		vendredi17 = new Button(vendredi, SWT.NONE);
		vendredi17.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 17, 6, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		vendredi17.setText("17:00");

		vendredi18 = new Button(vendredi, SWT.NONE);
		vendredi18.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddRdv ar = new AddRdv(shlGarage, 18, 6, wk);
				ar.open();
				if (ar.result == true) {
					setPlanning();
				}
			}
		});
		vendredi18.setText("18:00");

		composite_10 = new Composite(composite_8, SWT.NONE);
		composite_10.setLayoutData(BorderLayout.NORTH);
		composite_10.setLayout(new FillLayout(SWT.HORIZONTAL));

		lblSemaine = new Label(composite_10, SWT.NONE);
		lblSemaine.setAlignment(SWT.RIGHT);
		lblSemaine.setText("Semaine :");

		wkNumber = new Label(composite_10, SWT.NONE);
		wkNumber.setText("15");

		tbtmClients = new TabItem(tabFolder, SWT.NONE);
		tbtmClients.setText("Clients");

		composite = new Composite(tabFolder, SWT.NONE);
		tbtmClients.setControl(composite);
		composite.setLayout(new FillLayout(SWT.VERTICAL));

		composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));

		tableClients = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_tableClients = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tableClients.widthHint = 453;
		tableClients.setLayoutData(gd_tableClients);
		tableClients.setHeaderVisible(true);
		tableClients.setLinesVisible(true);
		tableClients.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				remplirTableVoitures();
				remplirTableFactures();
			}
		});

		tblclmnNom = new TableColumn(tableClients, SWT.NONE);
		tblclmnNom.setText("Nom");

		tblclmnPrenom = new TableColumn(tableClients, SWT.NONE);
		tblclmnPrenom.setText("Prenom");

		tblclmnEmail = new TableColumn(tableClients, SWT.NONE);
		tblclmnEmail.setText("Email");

		tblclmnTelephone = new TableColumn(tableClients, SWT.NONE);
		tblclmnTelephone.setText("Telephone");

		composite_4 = new Composite(composite_1, SWT.NONE);
		FillLayout fl_composite_4 = new FillLayout(SWT.VERTICAL);
		fl_composite_4.spacing = 10;
		composite_4.setLayout(fl_composite_4);
		GridData gd_composite_4 = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_composite_4.heightHint = 36;
		gd_composite_4.widthHint = 120;
		composite_4.setLayoutData(gd_composite_4);

		btnNouveauClient = new Button(composite_4, SWT.NONE);
		btnNouveauClient.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddClient ac = new AddClient(shlGarage, 1);
				ac.open();
				if (ac.result == true) {
					remplirTableClients();
				}
			}
		});
		btnNouveauClient.setText("Nouveau client");

		composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new GridLayout(2, false));

		tableVoitures = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_tableVoitures = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tableVoitures.widthHint = 467;
		tableVoitures.setLayoutData(gd_tableVoitures);
		tableVoitures.setHeaderVisible(true);
		tableVoitures.setLinesVisible(true);
		tableVoitures.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				remplirTableFacturesByCar();
			}
		});

		tblclmnMarque = new TableColumn(tableVoitures, SWT.NONE);
		tblclmnMarque.setWidth(127);
		tblclmnMarque.setText("Marque");

		tblclmnModele = new TableColumn(tableVoitures, SWT.NONE);
		tblclmnModele.setWidth(131);
		tblclmnModele.setText("Modele");

		tblclmnImmatriculation = new TableColumn(tableVoitures, SWT.NONE);
		tblclmnImmatriculation.setWidth(127);
		tblclmnImmatriculation.setText("Immatriculation");

		composite_5 = new Composite(composite_2, SWT.NONE);
		FillLayout fl_composite_5 = new FillLayout(SWT.VERTICAL);
		fl_composite_5.spacing = 10;
		composite_5.setLayout(fl_composite_5);
		GridData gd_composite_5 = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_composite_5.heightHint = 39;
		gd_composite_5.widthHint = 120;
		composite_5.setLayoutData(gd_composite_5);

		btnNouvelleVoiture = new Button(composite_5, SWT.NONE);
		btnNouvelleVoiture.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					AddVoiture av = new AddVoiture(shlGarage, tableClients.getSelection()[0].getText(2));
					av.open();
					if (av.result == true) {
						remplirTableVoitures();
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					InfoDialog info = new InfoDialog(shlGarage, "Vous devez sélectionner un client");
					info.open();
				}
			}
		});
		btnNouvelleVoiture.setText("Nouvelle voiture");

		composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setLayout(new GridLayout(2, false));

		tableFactures = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_tableFactures = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tableFactures.widthHint = 501;
		tableFactures.setLayoutData(gd_tableFactures);
		tableFactures.setHeaderVisible(true);
		tableFactures.setLinesVisible(true);

		tblclmnDate = new TableColumn(tableFactures, SWT.NONE);
		tblclmnDate.setWidth(181);
		tblclmnDate.setText("Date");

		tblclmnMontant = new TableColumn(tableFactures, SWT.NONE);
		tblclmnMontant.setWidth(197);
		tblclmnMontant.setText("Montant");

		tblclmnStatus = new TableColumn(tableFactures, SWT.NONE);
		tblclmnStatus.setWidth(100);
		tblclmnStatus.setText("Status");

		composite_6 = new Composite(composite_3, SWT.NONE);
		RowLayout rl_composite_6 = new RowLayout(SWT.HORIZONTAL);
		composite_6.setLayout(rl_composite_6);
		GridData gd_composite_6 = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_composite_6.heightHint = 64;
		gd_composite_6.widthHint = 120;
		composite_6.setLayoutData(gd_composite_6);

		btnVisualiserFacture = new Button(composite_6, SWT.NONE);
		btnVisualiserFacture.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
				ResultSet rs = Database.getInstance().getFacture(
						tableFactures.getSelection()[0].getText(0), 
						tableClients.getSelection()[0].getText(2));
				ViewFacture vf = new ViewFacture(shlGarage, rs);
				vf.open();
				} catch (ArrayIndexOutOfBoundsException e2) {
					InfoDialog info = new InfoDialog(shlGarage, "Vous devez sélectionner une facture");
					info.open();
				}
			}
		});
		btnVisualiserFacture.setLayoutData(new RowData(112, 42));
		btnVisualiserFacture.setText("Visualiser facture");

		wkNumber.setText(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));
		wk = cal.get(Calendar.WEEK_OF_YEAR);

		composite_1.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				tableClientsResizing();
			}
		});

		composite_2.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				tableVoituresResizing();
			}
		});

		composite_3.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				tableFacturesResizing();
			}
		});

		remplirTableClients();
		setPlanning();
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
				item.setText(new String[] { a, b, c, d });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				item.setText(new String[] { a, b, c });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void remplirTableFactures() {
		tableFactures.removeAll();
		ResultSet rs = Database.getInstance().getFacturesByClient(tableClients.getSelection()[0].getText(2));
		try {
			while (rs.next()) {
				String a = rs.getString("created_at");
				String b = String.valueOf(rs.getDouble("montant"));
				String c = String.valueOf(rs.getInt("status"));

				TableItem item = new TableItem(tableFactures, SWT.NONE);
				item.setText(new String[] { a, b, c });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void remplirTableFacturesByCar() {
		tableFactures.removeAll();
		ResultSet rs = Database.getInstance().getFacturesByCar(tableVoitures.getSelection()[0].getText(2));
		try {
			while (rs.next()) {
				String a = rs.getString("created_at");
				String b = String.valueOf(rs.getDouble("montant"));
				String c = String.valueOf(rs.getInt("status"));

				if (a != null) {
					TableItem item = new TableItem(tableFactures, SWT.NONE);
					item.setText(new String[] { a, b, c });
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tableClientsResizing() {
		Rectangle area = composite_1.getClientArea();
		Point preferredSize = tableClients.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		int width = area.width - 2 * tableClients.getBorderWidth() - 120;
		if (preferredSize.y > area.height + tableClients.getHeaderHeight()) {
			Point vBarSize = tableClients.getVerticalBar().getSize();
			width -= vBarSize.x;
		}
		Point oldSize = tableClients.getSize();
		if (oldSize.x > area.width) {
			tblclmnNom.setWidth(width / 4);
			tblclmnPrenom.setWidth(width / 4);
			tblclmnEmail.setWidth(width / 4);
			tblclmnTelephone.setWidth(width / 4);
			tableClients.setSize(area.width, area.height);
		} else {
			tableClients.setSize(area.width, area.height);
			tblclmnNom.setWidth(width / 4);
			tblclmnPrenom.setWidth(width / 4);
			tblclmnEmail.setWidth(width / 4);
			tblclmnTelephone.setWidth(width / 4);
		}
	}

	private void tableVoituresResizing() {
		Rectangle area = composite_2.getClientArea();
		Point preferredSize = tableVoitures.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		int width = area.width - 2 * tableVoitures.getBorderWidth() - 120;
		if (preferredSize.y > area.height + tableVoitures.getHeaderHeight()) {
			Point vBarSize = tableVoitures.getVerticalBar().getSize();
			width -= vBarSize.x;
		}
		Point oldSize = tableVoitures.getSize();
		if (oldSize.x > area.width) {
			tblclmnMarque.setWidth(width / 3);
			tblclmnModele.setWidth(width / 3);
			tblclmnImmatriculation.setWidth(width / 3);
			tableVoitures.setSize(area.width, area.height);
		} else {
			tableVoitures.setSize(area.width, area.height);
			tblclmnMarque.setWidth(width / 3);
			tblclmnModele.setWidth(width / 3);
			tblclmnImmatriculation.setWidth(width / 3);
		}
	}

	private void tableFacturesResizing() {
		Rectangle area = composite_3.getClientArea();
		Point preferredSize = tableFactures.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		int width = area.width - 2 * tableFactures.getBorderWidth() - 120;
		if (preferredSize.y > area.height + tableFactures.getHeaderHeight()) {
			Point vBarSize = tableFactures.getVerticalBar().getSize();
			width -= vBarSize.x;
		}
		Point oldSize = tableFactures.getSize();
		if (oldSize.x > area.width) {
			tblclmnDate.setWidth(width / 3);
			tblclmnMontant.setWidth(width / 3);
			tblclmnStatus.setWidth(width / 3);
			tableFactures.setSize(area.width, area.height);
		} else {
			tableFactures.setSize(area.width, area.height);
			tblclmnDate.setWidth(width / 3);
			tblclmnMontant.setWidth(width / 3);
			tblclmnStatus.setWidth(width / 3);
		}
	}

	private void setPlanning() {
		resetPlanning();
		ResultSet rs = Database.getInstance().getRdvParSemaine(wk);

		try {
			while (rs.next()) {
				int heure = rs.getInt("heure");
				int jour = rs.getInt("jour");

				switch (jour) {
				case 2:
					setRdvLundi(heure);
					break;
				case 3:
					setRdvMardi(heure);
					break;
				case 4:
					setRdvMercredi(heure);
					break;
				case 5:
					setRdvJeudi(heure);
					break;
				case 6:
					setRdvVendredi(heure);
					break;

				default:
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void resetPlanning() {
		lundi8.setEnabled(true); lundi8.setText("8:00");
		lundi9.setEnabled(true); lundi9.setText("9:00");
		lundi10.setEnabled(true); lundi10.setText("10:00");
		lundi11.setEnabled(true); lundi11.setText("11:00");
		lundi12.setEnabled(true); lundi12.setText("12:00");
		lundi14.setEnabled(true); lundi14.setText("14:00");
		lundi15.setEnabled(true); lundi15.setText("15:00");
		lundi16.setEnabled(true); lundi16.setText("16:00");
		lundi17.setEnabled(true); lundi17.setText("17:00");
		lundi18.setEnabled(true); lundi18.setText("18:00");
		
		mardi8.setEnabled(true); mardi8.setText("8:00");
		mardi9.setEnabled(true); mardi9.setText("9:00");
		mardi10.setEnabled(true); mardi10.setText("10:00");
		mardi11.setEnabled(true); mardi11.setText("11:00");
		mardi12.setEnabled(true); mardi12.setText("12:00");
		mardi14.setEnabled(true); mardi14.setText("14:00");
		mardi15.setEnabled(true); mardi15.setText("15:00");
		mardi16.setEnabled(true); mardi16.setText("16:00");
		mardi17.setEnabled(true); mardi17.setText("17:00");
		mardi18.setEnabled(true); mardi18.setText("18:00");
		
		mercredi8.setEnabled(true); mercredi8.setText("8:00");
		mercredi9.setEnabled(true); mercredi9.setText("9:00");
		mercredi10.setEnabled(true); mercredi10.setText("10:00");
		mercredi11.setEnabled(true); mercredi11.setText("11:00");
		mercredi12.setEnabled(true); mercredi12.setText("12:00");
		mercredi14.setEnabled(true); mercredi14.setText("14:00");
		mercredi15.setEnabled(true); mercredi15.setText("15:00");
		mercredi16.setEnabled(true); mercredi16.setText("16:00");
		mercredi17.setEnabled(true); mercredi17.setText("17:00");
		mercredi18.setEnabled(true); mercredi18.setText("18:00");
		
		jeudi8.setEnabled(true); jeudi8.setText("8:00");
		jeudi9.setEnabled(true); jeudi9.setText("9:00");
		jeudi10.setEnabled(true); jeudi10.setText("10:00");
		jeudi11.setEnabled(true); jeudi11.setText("11:00");
		jeudi12.setEnabled(true); jeudi12.setText("12:00");
		jeudi14.setEnabled(true); jeudi14.setText("14:00");
		jeudi15.setEnabled(true); jeudi15.setText("15:00");
		jeudi16.setEnabled(true); jeudi16.setText("16:00");
		jeudi17.setEnabled(true); jeudi17.setText("17:00");
		jeudi18.setEnabled(true); jeudi18.setText("18:00");
		
		vendredi8.setEnabled(true); vendredi8.setText("8:00");
		vendredi9.setEnabled(true); vendredi9.setText("9:00");
		vendredi10.setEnabled(true); vendredi10.setText("10:00");
		vendredi11.setEnabled(true); vendredi11.setText("11:00");
		vendredi12.setEnabled(true); vendredi12.setText("12:00");
		vendredi14.setEnabled(true); vendredi14.setText("14:00");
		vendredi15.setEnabled(true); vendredi15.setText("15:00");
		vendredi16.setEnabled(true); vendredi16.setText("16:00");
		vendredi17.setEnabled(true); vendredi17.setText("17:00");
		vendredi18.setEnabled(true); vendredi18.setText("18:00");
	}

	private void setRdvVendredi(int heure) {
		switch (heure) {
		case 8:
			vendredi8.setText("Occupé");
			vendredi8.setEnabled(false);
			break;
		case 9:
			vendredi9.setText("Occupé");
			vendredi9.setEnabled(false);
			break;
		case 10:
			vendredi10.setText("Occupé");
			vendredi10.setEnabled(false);
			break;
		case 11:
			vendredi11.setText("Occupé");
			vendredi11.setEnabled(false);
			break;
		case 12:
			vendredi12.setText("Occupé");
			vendredi12.setEnabled(false);
			break;
		case 14:
			vendredi14.setText("Occupé");
			vendredi14.setEnabled(false);
			break;
		case 15:
			vendredi15.setText("Occupé");
			vendredi15.setEnabled(false);
			break;
		case 16:
			vendredi16.setText("Occupé");
			vendredi16.setEnabled(false);
			break;
		case 17:
			vendredi17.setText("Occupé");
			vendredi17.setEnabled(false);
			break;
		case 18:
			vendredi18.setText("Occupé");
			vendredi18.setEnabled(false);
			break;

		default:
			break;
		}
	}

	private void setRdvJeudi(int heure) {
		switch (heure) {
		case 8:
			jeudi8.setText("Occupé");
			jeudi8.setEnabled(false);
			break;
		case 9:
			jeudi9.setText("Occupé");
			jeudi9.setEnabled(false);
			break;
		case 10:
			jeudi10.setText("Occupé");
			jeudi10.setEnabled(false);
			break;
		case 11:
			jeudi11.setText("Occupé");
			jeudi11.setEnabled(false);
			break;
		case 12:
			jeudi12.setText("Occupé");
			jeudi12.setEnabled(false);
			break;
		case 14:
			jeudi14.setText("Occupé");
			jeudi14.setEnabled(false);
			break;
		case 15:
			jeudi15.setText("Occupé");
			jeudi15.setEnabled(false);
			break;
		case 16:
			jeudi16.setText("Occupé");
			jeudi16.setEnabled(false);
			break;
		case 17:
			jeudi17.setText("Occupé");
			jeudi17.setEnabled(false);
			break;
		case 18:
			jeudi18.setText("Occupé");
			jeudi18.setEnabled(false);
			break;

		default:
			break;
		}
	}

	private void setRdvMercredi(int heure) {
		switch (heure) {
		case 8:
			mercredi8.setText("Occupé");
			mercredi8.setEnabled(false);
			break;
		case 9:
			mercredi9.setText("Occupé");
			mercredi9.setEnabled(false);
			break;
		case 10:
			mercredi10.setText("Occupé");
			mercredi10.setEnabled(false);
			break;
		case 11:
			mercredi11.setText("Occupé");
			mercredi11.setEnabled(false);
			break;
		case 12:
			mercredi12.setText("Occupé");
			mercredi12.setEnabled(false);
			break;
		case 14:
			mercredi14.setText("Occupé");
			mercredi14.setEnabled(false);
			break;
		case 15:
			mercredi15.setText("Occupé");
			mercredi15.setEnabled(false);
			break;
		case 16:
			mercredi16.setText("Occupé");
			mercredi16.setEnabled(false);
			break;
		case 17:
			mercredi17.setText("Occupé");
			mercredi17.setEnabled(false);
			break;
		case 18:
			mercredi18.setText("Occupé");
			mercredi18.setEnabled(false);
			break;

		default:
			break;
		}
	}

	private void setRdvMardi(int heure) {
		switch (heure) {
		case 8:
			mardi8.setText("Occupé");
			mardi8.setEnabled(false);
			break;
		case 9:
			mardi9.setText("Occupé");
			mardi9.setEnabled(false);
			break;
		case 10:
			mardi10.setText("Occupé");
			mardi10.setEnabled(false);
			break;
		case 11:
			mardi11.setText("Occupé");
			mardi11.setEnabled(false);
			break;
		case 12:
			mardi12.setText("Occupé");
			mardi12.setEnabled(false);
			break;
		case 14:
			mardi14.setText("Occupé");
			mardi14.setEnabled(false);
			break;
		case 15:
			mardi15.setText("Occupé");
			mardi15.setEnabled(false);
			break;
		case 16:
			mardi16.setText("Occupé");
			mardi16.setEnabled(false);
			break;
		case 17:
			mardi17.setText("Occupé");
			mardi17.setEnabled(false);
			break;
		case 18:
			mardi18.setText("Occupé");
			mardi18.setEnabled(false);
			break;

		default:
			break;
		}
	}

	private void setRdvLundi(int heure) {
		switch (heure) {
		case 8:
			lundi8.setText("Occupé");
			lundi8.setEnabled(false);
			break;
		case 9:
			lundi9.setText("Occupé");
			lundi9.setEnabled(false);
			break;
		case 10:
			lundi10.setText("Occupé");
			lundi10.setEnabled(false);
			break;
		case 11:
			lundi11.setText("Occupé");
			lundi11.setEnabled(false);
			break;
		case 12:
			lundi12.setText("Occupé");
			lundi12.setEnabled(false);
			break;
		case 14:
			lundi14.setText("Occupé");
			lundi14.setEnabled(false);
			break;
		case 15:
			lundi15.setText("Occupé");
			lundi15.setEnabled(false);
			break;
		case 16:
			lundi16.setText("Occupé");
			lundi16.setEnabled(false);
			break;
		case 17:
			lundi17.setText("Occupé");
			lundi17.setEnabled(false);
			break;
		case 18:
			lundi18.setText("Occupé");
			lundi18.setEnabled(false);
			break;

		default:
			break;
		}
	}

}
