package dicom3drender;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;

import actions.*;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private OpenDicomAction    openAction;
	private VolumeAction  volumeAction;
	private SurfaceAction surfaceAction;

	
	private IWorkbenchAction iExitAction; 
	private IWorkbenchAction iAboutAction; 
	private IWorkbenchAction iSaveAsAction; 
	private IWorkbenchAction iSaveAction; 

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	
    	super.makeActions(window); 
    	
	   iExitAction = ActionFactory.QUIT.create(window); 
	   register(iExitAction); 
	   iSaveAction = ActionFactory.SAVE.create(window); 
	   register(iSaveAction); 
	   iAboutAction = ActionFactory.ABOUT.create(window); 
	   register(iAboutAction); 
	   iSaveAsAction = ActionFactory.SAVE_AS.create(window); 
	   register(iSaveAsAction); 
    	   
    	
    	// add action for operation from package actions
        openAction  = new OpenDicomAction(window);
        register(openAction);
        
        volumeAction     = new VolumeAction(window);        
        surfaceAction    = new SurfaceAction(window);        

        
       
        
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	
    	MenuManager fileMenu = new MenuManager("&File", 
    		    IWorkbenchActionConstants.M_FILE); 
    	menuBar.add(fileMenu); 
    	
    	
    	// File Menu 
    	fileMenu.add(openAction); 
    	fileMenu.add(new Separator()); 
    	fileMenu.add(iSaveAction); 
    	fileMenu.add(iSaveAsAction); 
    	fileMenu.add(new Separator()); 
    	fileMenu.add(iExitAction); 
  
  	
    	//3D-Render Menu
        MenuManager threeMenu = new MenuManager("3D-&Rendering", "");	   	
        menuBar.add(threeMenu);
    	threeMenu.add(volumeAction);
    	threeMenu.add(surfaceAction);
    	
        MenuManager helpMenu = new MenuManager("&Help", 
        	    IWorkbenchActionConstants.M_HELP); 
        menuBar.add(helpMenu); 
        
        // Help Menu 
        helpMenu.add(iAboutAction);
    	 
    }
    
    // link the action to tool bar
    protected void fillCoolBar(ICoolBarManager coolBar) 
    { 
    	// This will add a new toolbar to the application
		IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
		coolBar.add(new ToolBarContributionItem(toolbar, "main")); 

        // Add the entry to the toolbar
 //       toolbar.add(openAction);     
 //       toolbar.add(iSaveAction); 
         toolbar.add(iExitAction); 
    } 
    
}
