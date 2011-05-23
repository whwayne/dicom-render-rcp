package actions;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.part.ViewPart;



import tools.ImageToBufferConverter;
import tools.Snippet156;
import tools.Tools;
import view.DicomViewCanvas;
import view.ImageView;
import view.ThreeView;
import view.XDicomView;
import vtk.vtkDICOMImageReader;
import vtk.vtkImageActor;
import vtk.vtkImageCast;
import vtk.vtkRenderWindow;
import vtk.vtkRenderer;



import DicomDecoder.DicomReader;



public class OpenDicomAction extends Action  
{ 
  private static final String ID = "actions.OpenDicomAction";
	
  private IWorkbenchWindow window; 
  
  private Image[] swtImage = null;;
  
  private ImageData [] smallImage  = null;
  


  
	  
	  public OpenDicomAction(IWorkbenchWindow window) 
	 { 
	   this.window = window; 
	   
	   this.setText("&Open file from DIR..."); 
	   setId(ID); 
	   ImageDescriptor imgDes =  
	   WorkbenchImages.getImageDescriptor( 
	   IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART); 
	   this.setImageDescriptor(imgDes); 
	 } 
	
	  public void run() 
	  {  
		  
		  DataInputStream  in ;
		  String [] info ;  //image info
		  final java.awt.Image [] images  ; //save image

		  
		  
		  //add open dicom files from dir  
		  
		  FileDialog fd = new FileDialog(window.getShell(), SWT.OPEN); 
		  fd.setText("Open"); 
	      fd.setFilterPath("D:/"); 
	      String[] filterExt = { "*.dcm", "*.doc", ".rtf", "*.*" }; //add more filter after     
	      fd.setFilterExtensions(filterExt); 
	      final String selected = fd.open();   		
			
	      
	      if(selected != null){ 
    	  URL url = null;		
		  try{
			  url = new URL("File:" + selected);
		  }catch (MalformedURLException e){
			  e.printStackTrace();
		  }
			  
	  try {    		  
		    URLConnection u = url.openConnection();
		    in = new DataInputStream(u.getInputStream()) ;
		    int size = u.getContentLength() ;
			
			byte[] array = new byte[size];
			int bytes_read = 0;
			
			while(bytes_read < size){	
						bytes_read += in.read(array, bytes_read, size - bytes_read);
			}//end while
			in.close();
			
            DicomReader dR = new DicomReader(array);
		   	images  =   dR.getImages();
			info    =   dR.getInfos();
			
			
			
			
					    
			    
			  
			
			
			
			
			final IViewPart viewPart = PlatformUI.getWorkbench()
		                         .getActiveWorkbenchWindow().getActivePage().findView("view.XDicomView");
			
		//	final DicomViewCanvas dc = ((XDicomView)viewPart).canvas;
		     DicomViewCanvas dc = ((XDicomView)viewPart).canvas;
			
		
			/*dc.addPaintListener(new PaintListener() {
			      public void paintControl(PaintEvent e) {
			    	  
			    	  int canvasWidth  = dc.getBounds().width;
			    	  int canvasHeight  = dc.getBounds().height;
			    	  
			    	  
			    	  for (int i = 0 ; i < images.length; i++) {
							 BufferedImage  bi = ImageToBufferConverter.toBufferedImage(images[i]);
							 ImageData id  = Snippet156.convertToSWT(bi);
							 Image image = new Image(window.getShell().getDisplay(),id);
							 
						//	 e.gc.drawImage(image,0,0);
							 e.gc.drawImage(image, 0, 0, canvasWidth, canvasHeight, 0, 0, canvasWidth, canvasHeight);
							 
						
						     
						  //  dc.setImage(id);
						    
						    bi=null;  
						  }//end for 
			    	  
			    	  
			    	  
			    	  
			    	  
			        
			      }
			    });*/
			
		     ImageData[] id = null;
			
			 for (int i = 0 ; i < images.length; i++) {
				 BufferedImage  bi = ImageToBufferConverter.toBufferedImage(images[i]);
				 id[i] = Snippet156.convertToSWT(bi);
			    
			    bi=null;  
			  }//end for 
			 
			 dc.setImages(id);
			  
			
			  
			  
			   
			
		    

		    
		    
		          
		    
		  
              
            
           
      	
			
			
			
			
			
			  
	   } catch (IOException e) {
		  tools.Tools.debug("exception "+ e);
		  //add messagebox
		  MessageBox errorBox = new MessageBox(window.getShell(),SWT.ICON_ERROR);
		  errorBox.setText("Error");
		  errorBox.setMessage("Fail to open dicom files!" + selected);
		  errorBox.open();
	  }// end of try-catch
	      
			
			             
}//end if 
	     
	      
	      
		        
	      
	     
	      
	    	  
			   
				   
			    
	         


		  
		  
	  }//end run
	  
	  
	  
	  
	   
	  
} // end action


