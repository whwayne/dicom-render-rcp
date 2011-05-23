package actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

public class VolumeAction extends Action  
{ 
  private IWorkbenchWindow window; 
  
	  public VolumeAction(IWorkbenchWindow window) 
	 { 
	   this.window = window; 
	   this.setText("Volume Rendering"); 
	   ImageDescriptor imgDes =  
	   WorkbenchImages.getImageDescriptor( 
	   IWorkbenchGraphicConstants.IMG_DTOOL_NEW_FASTVIEW); 
	   this.setImageDescriptor(imgDes); 
	 } 
  
	  public void run() 
	  { 
	   //add volume rendering code
	  }
} 