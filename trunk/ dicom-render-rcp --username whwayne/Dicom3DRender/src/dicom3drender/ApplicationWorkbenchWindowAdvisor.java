package dicom3drender;

import java.util.Vector;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
	
	public Vector vimages ;//save images

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(800, 600));
    //    configurer.setShowCoolBar(false);
   //     configurer.setShowStatusLine(false);
        configurer.setTitle("Dicom3DRender");
    }
    
    // set main window to max size 
    public void postWindowOpen() 
    { 
       this.getWindowConfigurer().getWindow().getShell().setMaximized(true); 
      
    } 
}
