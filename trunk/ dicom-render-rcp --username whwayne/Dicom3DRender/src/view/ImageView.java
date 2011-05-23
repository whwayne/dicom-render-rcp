
package view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;


/**
 * This ImageView class shows how to use SWTImageCanvas to 
 * manipulate images. 
 * <p>
 * To facilitate the usage, you should setFocus to the canvas
 * at the beginning, and call the dispose at the end.
 * <p>
 * @author Chengdong Li: cli4@uky.edu
 * @see uky.article.imageviewer.SWTImageCanvas
 */

public class ImageView extends ViewPart {
	public static final String ID = "view.ImageView"; //$NON-NLS-1$
	public SWTImageCanvas imageCanvas;
	
	/**
	 * The constructor.
	 */
	public ImageView() {
	}
	
	/**
	 * Create the GUI.
	 * @param frame The Composite handle of parent
	 */
	public void createPartControl(Composite frame) {
		imageCanvas=new SWTImageCanvas(frame);
		
	}

	/**
	 * Called when we must grab focus.
	 * @see org.eclipse.ui.part.ViewPart#setFocus
	 */
	public void setFocus() {
		imageCanvas.setFocus();
	}

	/**
	 * Called when the View is to be disposed
	 */
	public void dispose() {
		imageCanvas.dispose();
		super.dispose();
	}

}