package view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.Image;


public class XDicomView extends ViewPart {

	public static final String ID = "view.XDicomView"; //$NON-NLS-1$
	
	public IWorkbenchWindow mainframe;

	public DicomViewCanvas canvas;
	

	public XDicomView() {
		
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
	
		
		   canvas = new DicomViewCanvas(parent);
		// canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		   	   	 
		

		createActions();
		initializeToolBar();
		initializeMenu();
		
	}
	

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		
		
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
