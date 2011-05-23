package actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

public class AboutAction extends Action  
{ 
  private IWorkbenchWindow window; 
  
	  public AboutAction(IWorkbenchWindow window) 
	 { 
	   this.window = window; 
	   this.setText("About Dicom3Drender"); 
	   ImageDescriptor imgDes =  
	   WorkbenchImages.getImageDescriptor( 
	   IWorkbenchGraphicConstants.IMG_DTOOL_NEW_FASTVIEW); 
	   this.setImageDescriptor(imgDes); 
	 } 
  
	  public void run() 
	  { 
	   MessageBox mb = new MessageBox(window.getShell(), SWT.OK); 
	   mb.setMessage("Dicom3DRender!"); 
	   mb.setText("About"); 
	   mb.open(); 
	  }
} 