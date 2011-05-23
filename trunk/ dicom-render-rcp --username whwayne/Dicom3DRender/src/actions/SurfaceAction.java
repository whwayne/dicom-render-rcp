package actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

public class SurfaceAction extends Action  
{ 
  private IWorkbenchWindow window; 
  
	  public SurfaceAction(IWorkbenchWindow window) 
	 { 
	   this.window = window; 
	   this.setText("Suface Rendering"); 
	   ImageDescriptor imgDes =  
	   WorkbenchImages.getImageDescriptor( 
	   IWorkbenchGraphicConstants.IMG_DTOOL_NEW_FASTVIEW); 
	   this.setImageDescriptor(imgDes); 
	 } 
  
	  public void run() 
	  { 
	   //add suface rendering code
	  }
} 