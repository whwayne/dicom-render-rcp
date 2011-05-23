package actions;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

import dicom3drender.Activator;
import tools.FileInput;
import view.SWTImageCanvas;
import view.ImageView;



/**
 * @author whwayne
 *
 */
public class OpenAction extends Action implements IViewActionDelegate{

	private static final String ID = "action.openaction";
	
	/* pointer to image view */	
	public ImageView view=null;
	/** Action id of this delegate */
	public String id;
	private Image sourceImage; /* original image */
	
	private IWorkbenchWindow window;
	
	 public OpenAction(IWorkbenchWindow window) 
	 { 
	   this.window = window; 
	   
	   this.setText("&Open file from DIR..."); 
	   setId(ID); 

	   ImageDescriptor imgDes =  
	   WorkbenchImages.getImageDescriptor( 
	   IWorkbenchGraphicConstants.IMG_DTOOL_NEW_FASTVIEW); 
	   this.setImageDescriptor(imgDes); 
	 } 
	
	
	
	public void run(IAction action) {
		
		/*FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN);
		String path = dialog.open();
		SWTImageCanvas imageCanvas=view.imageCanvas;
		if(path != null){
		//	IWorkbenchPage page = window.getActivePage();
		//	FileInput input = new FileInput(path);
		
			
			
		}*/
		
		String id=action.getId();
		SWTImageCanvas imageCanvas=view.imageCanvas;
		if(id.equals("OpenAction")){
			imageCanvas.onFileOpen();
			return;
		}

	}

	@Override
	public void init(IViewPart viewPart) {
		if(viewPart instanceof ImageView){
			this.view=(ImageView)viewPart;
		}
	}



	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub
		
	}
}
