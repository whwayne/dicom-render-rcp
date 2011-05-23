package dicom3drender;

import view.*;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		
		
		//close the editor area
		layout.setEditorAreaVisible(false); 
		
		// add threeView to right
		layout.addStandaloneView(ThreeView.ID, true, 
		          IPageLayout.RIGHT, 0.22f, layout.getEditorArea()); 
		layout.getViewLayout(ThreeView.ID).setCloseable(false);
	
		
		//set view location, add XDicomView to top
		layout.addStandaloneView(XDicomView.ID, true, 
		          IPageLayout.TOP, 0.33f, layout.getEditorArea()); 
		//delete the closed button
		layout.getViewLayout(XDicomView.ID).setCloseable(false);
				
				
		//add YDicomView to bottom
		layout.addStandaloneView(ZDicomView.ID, true, 
		          IPageLayout.BOTTOM, 0.51f, layout.getEditorArea()); 
		layout.getViewLayout(ZDicomView.ID).setCloseable(false);
		
		
		//add YDicomView to left
		layout.addStandaloneView(YDicomView.ID, true, 
		          IPageLayout.LEFT, .35f, layout.getEditorArea()); 
		layout.getViewLayout(YDicomView.ID).setCloseable(false);
		
		
		//set view location, add ImagedicomView to top
//		layout.addStandaloneView(ImageView.ID, true, 
//		          IPageLayout.TOP, 0.33f, layout.getEditorArea()); 
		//delete the closed button
//		layout.getViewLayout(ImageView.ID).setCloseable(false);
		
		
		
			
		
		
		
		
		
		
		
		
		
		
		
	}
}
