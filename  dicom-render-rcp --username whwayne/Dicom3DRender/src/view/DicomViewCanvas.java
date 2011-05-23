package view;

import java.awt.geom.AffineTransform;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.wb.swt.SWTResourceManager;


public class DicomViewCanvas extends Canvas {

    protected Point origin = new Point(0, 0);
    protected Image image = null;
    protected ImageData[] imageDatas;
    protected Image[] images;
    protected int current;

//    private int repeatCount;
    private ScrollBar hBar;
    private ScrollBar vBar;
    private Color bg;
    private Display display;
//    private ImageItem [] imageItem;

    public DicomViewCanvas(Composite parent) {
    	
    	//creat superclass with colored , otherwise view need repaint
        super(parent, SWT.COLOR_WIDGET_FOREGROUND| SWT.NO_REDRAW_RESIZE | SWT.V_SCROLL
                | SWT.H_SCROLL);
     
        setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
        
        

        hBar = getHorizontalBar();
        vBar = getVerticalBar();
        bg =   getBackground();
        display = getDisplay();
        addListeners();
    }

    public void setImage(ImageData[] imageData) {
       
    //	checkWidget();

     //   stopAnimationTimer();
    	for(int i=0; i<imageData.length; i++){
    		this.image = new Image(display, imageData[i]);
		    this.image = new Image(null, imageData[i]);
		    this.imageDatas[i] = null;
		    this.images = null;
	//	    redraw();
     	}
    		
    		
    		
		    	//this.image = new Image(display, imageData);
		      //  this.image = new Image(null, imageData);
		     //   this.imageDatas = null;
		      //  this.images = null;
		      //  redraw();
    }
    
    

    /**
     * @param repeatCount 0 forever
     */

    public void setImages(ImageData[] imageDatas) {
        checkWidget();

        this.image = null;
        this.imageDatas = imageDatas;
        redraw();
    }
    

    @Override
    public Point computeSize(int wHint, int hHint, boolean changed) {
        checkWidget();

//        Image image = getCurrentImage();
        if (image != null) {
            Rectangle rect = image.getBounds();
            Rectangle trim = computeTrim(0, 0, rect.width, rect.height);
            return new Point(trim.width, trim.height);
        }

        return new Point(wHint, hHint);
    }

    @Override
    public void dispose() {
        if (image != null)
            image.dispose();

        if (images != null)
            for (int i = 0; i < images.length; i++)
                images[i].dispose();

        super.dispose();
    }

    public void paint(Event e) {
        ImageData[] imaged = getCurrentImagedata();
        if (imaged == null)
            return;
         
        
        GC gc = e.gc;
        int x = 0;
        int y = 0;
       
        for(int i=0; i<imaged.length; i++){
        	images[i] = new Image(display,imaged[i]);
        	gc.drawImage(images[i],x,y);
        	x += images[i].getBounds().height;
        	gc.setBackground(bg);
        	
        }
        
        
        /*

        GC gc = e.gc;
        gc.drawImage(image, origin.x, origin.y);

        gc.setBackground(bg);
        Rectangle rect = image.getBounds();
        Rectangle client = getClientArea();
        int marginWidth = client.width - rect.width;
        if (marginWidth > 0) {
            gc.fillRectangle(rect.width, 0, marginWidth, client.height);
        }
        int marginHeight = client.height - rect.height;
        if (marginHeight > 0) {
            gc.fillRectangle(0, rect.height, client.width, marginHeight);
        }
        */
    }

    void addListeners() {
        hBar.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event arg0) {
                hscroll();
            }
        });
        vBar.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event arg0) {
                vscroll();
            }
        });
        addListener(SWT.Resize, new Listener() {
            public void handleEvent(Event e) {
                resize();
            }
        });
        addListener(SWT.Paint, new Listener() {
            public void handleEvent(Event e) {
                paint(e);
            }
        });
    }

    void hscroll() {
//        Image image = getCurrentImage();
        if (image != null) {
            int hSelection = hBar.getSelection();
            int destX = -hSelection - origin.x;
            Rectangle rect = image.getBounds();
            scroll(destX, 0, 0, 0, rect.width, rect.height, false);
            origin.x = -hSelection;
        }
    }

    void vscroll() {
//        Image image = getCurrentImage();
        if (image != null) {
            int vSelection = vBar.getSelection();
            int destY = -vSelection - origin.y;
            Rectangle rect = image.getBounds();
            scroll(0, destY, 0, 0, rect.width, rect.height, false);
            origin.y = -vSelection;
        }
    }

    void resize() {
 //       Image image = getCurrentImage();
        if (image == null)
            return;

        Rectangle rect = image.getBounds();
        Rectangle client = getClientArea();
        hBar.setMaximum(rect.width);
        vBar.setMaximum(rect.height);
        hBar.setThumb(Math.min(rect.width, client.width));
        vBar.setThumb(Math.min(rect.height, client.height));
        int hPage = rect.width - client.width;
        int vPage = rect.height - client.height;
        int hSelection = hBar.getSelection();
        int vSelection = vBar.getSelection();
        if (hSelection >= hPage) {
            if (hPage <= 0)
                hSelection = 0;
            origin.x = -hSelection;
        }
        if (vSelection >= vPage) {
            if (vPage <= 0)
                vSelection = 0;
            origin.y = -vSelection;
        }
        redraw();
    }
/*
    void convertImageDatasToImages() {
        images = new Image[imageDatas.length];

        // Step 1: Determine the size of the resulting images.
        int width = imageDatas[0].width;
        int height = imageDatas[0].height;

        // Step 2: Construct each image.
        int transition = SWT.DM_FILL_BACKGROUND;
        for (int i = 0; i < imageDatas.length; i++) {
            ImageData id = imageDatas[i];
            images[i] = new Image(display, width, height);
            GC gc = new GC(images[i]);

            // Do the transition from the previous image.
            switch (transition) {
            case SWT.DM_FILL_NONE:
            case SWT.DM_UNSPECIFIED:
                // Start from last image.
                gc.drawImage(images[i - 1], 0, 0);
                break;
            case SWT.DM_FILL_PREVIOUS:
                // Start from second last image.
                gc.drawImage(images[i - 2], 0, 0);
                break;
            default:
                // DM_FILL_BACKGROUND or anything else,
                // just fill with default background.
                gc.setBackground(bg);
                gc.fillRectangle(0, 0, width, height);
                break;
            }

            // Draw the current image and clean up.
            Image img = new Image(display, id);
            gc.drawImage(img, 0, 0, id.width, id.height, id.x, id.y, id.width,
                    id.height);
            img.dispose();
            gc.dispose();

            // Compute the next transition.
            // Special case: Can't do DM_FILL_PREVIOUS on the
            // second image since there is no "second last"
            // image to use.
            transition = id.disposalMethod;
            if (i == 0 && transition == SWT.DM_FILL_PREVIOUS)
                transition = SWT.DM_FILL_NONE;
        }
    }
  */
    ImageData[] getCurrentImagedata() {
       
        return imageDatas;
    }
/*
    void startAnimationTimer() {
        if (images == null || images.length < 2)
            return;

        final int delay = imageDatas[current].delayTime * 10;
        display.timerExec(delay, animationTimer = new Runnable() {
            public void run() {
                if (isDisposed())
                    return;

                current = (current + 1) % images.length;
                redraw();

                if (current + 1 == images.length && repeatCount != 0
                        && --repeatCount <= 0)
                    return;
                display.timerExec(delay, this);
            }
        });
    }
    
    void stopAnimationTimer() {
        if (animationTimer != null)
            display.timerExec(-1, animationTimer);
    }
    */
}