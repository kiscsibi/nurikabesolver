import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Mainwindow extends javax.swing.JFrame {
	private JButton jButtonClose;
	private AbstractAction abstractActionClose;
	private Canvas canvas1;
	private AbstractAction closeAboutAction;
	private JEditorPane jEditorPaneAbout;
	private JButton jButtonAboutOK;
	private JDialog jDialogAbout;
	private JMenuItem jMenuItemAbout;                                                                    
	private AbstractAction AboutAction;
	private JMenu jMenuHelp;
	private JMenu jMenuFile;
	private JMenuBar jMenuBar;
	private CanvasPlayfield Playfield;
	private JButton jButtonStart;
	
	private NurikabeEngine Engine;

	
	/**
	 *  Internal class representing the playfield 
	 */
	private class CanvasPlayfield extends Canvas {

	    public CanvasPlayfield()
	    {
	    }
	    public void paint(Graphics graphics)
	    {
		
		//draw the fields according to their value	    
		for(int w = 0; w < Engine.getWidth(); w++) {
			for(int h = 0; h < Engine.getHeight(); h++) {
			    
			    
			    int width = this.getWidth()/Engine.getWidth();
			    int height = this.getHeight()/Engine.getHeight();
			    
			    int color = Engine.getColor(w,h);
			    switch (color) {
			    case 0:
				graphics.setColor(Color.gray);
				break;
			    case 1:
				graphics.setColor(Color.black);
				break;
			    case 2:
				graphics.setColor(Color.white);
				break;
			    }
			    
			    graphics.fillRect(w*width, h*height, width, height);
			}	
		}
	    }
	}
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Mainwindow inst = new Mainwindow();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Mainwindow() {
		super();
		initEngine();
		initGUI();
	}
	
	private void initEngine() {
	    Engine = new NurikabeEngine(5, 5);
	}
	
	private void initGUI() {
		try {
		    GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
		    getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
			    jMenuBar = new JMenuBar();
			    setJMenuBar(jMenuBar);
			    {
				jMenuFile = new JMenu();
				jMenuBar.add(jMenuFile);
				jMenuFile.setText("File");
			    }
			    {
				jMenuHelp = new JMenu();
				jMenuBar.add(jMenuHelp);
				jMenuHelp.setText("Help");
				{
				    jMenuItemAbout = new JMenuItem();
				    jMenuHelp.add(jMenuItemAbout);
				    jMenuItemAbout.setText("About");
				    jMenuItemAbout.setAction(getAboutAction());
				}
			    }
			}
			{
			    jButtonClose = new JButton();
			    jButtonClose.setText("Close");
			    jButtonClose.setAction(getAbstractActionClose());
			}
			{
			    jButtonStart = new JButton();
			    jButtonStart.setText("Start");
			}
			    thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
			    	.addComponent(getPlayfield(), 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			    	.addGap(0, 11, GroupLayout.PREFERRED_SIZE)
			    	.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    	    .addComponent(jButtonClose, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
			    	    .addComponent(jButtonStart, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
			    	.addContainerGap());
			    thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
			    	.addContainerGap()
			    	.addGroup(thisLayout.createParallelGroup()
			    	    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
			    	        .addComponent(jButtonStart, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
			    	        .addGap(0, 64, Short.MAX_VALUE)
			    	        .addComponent(jButtonClose, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
			    	    .addComponent(getPlayfield(), GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			    	.addContainerGap());
			    thisLayout.linkSize(SwingConstants.VERTICAL, new Component[] {jButtonClose, jButtonStart});
			    thisLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {jButtonClose, jButtonStart});
			pack();
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private AbstractAction getAboutAction() {
	    if(AboutAction == null) {
		AboutAction = new AbstractAction("About", null) {
		    public void actionPerformed(ActionEvent evt) {
			getJDialogAbout().pack();
			getJDialogAbout().setLocationRelativeTo(null);
			getJDialogAbout().setVisible(true);
			
		    }
		};
		AboutAction.putValue(javax.swing.Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl pressed A"));
	    }
	    return AboutAction;
	}
	
	private JDialog getJDialogAbout() {
	    if(jDialogAbout == null) {
		jDialogAbout = new JDialog(this);
		GroupLayout jDialogAboutLayout = new GroupLayout((JComponent)jDialogAbout.getContentPane());
		jDialogAbout.getContentPane().setLayout(jDialogAboutLayout);
		jDialogAboutLayout.setHorizontalGroup(jDialogAboutLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(jDialogAboutLayout.createParallelGroup()
			    .addComponent(getJEditorPane1(), GroupLayout.Alignment.LEADING, 0, 169, Short.MAX_VALUE)
			    .addGroup(GroupLayout.Alignment.LEADING, jDialogAboutLayout.createSequentialGroup()
			        .addGap(0, 49, Short.MAX_VALUE)
			        .addComponent(getJButtonAboutOK(), GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
			        .addGap(53)))
			.addContainerGap());
		jDialogAboutLayout.setVerticalGroup(jDialogAboutLayout.createSequentialGroup()
			.addContainerGap(22, Short.MAX_VALUE)
			.addComponent(getJEditorPane1(), GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 1, GroupLayout.PREFERRED_SIZE)
			.addComponent(getJButtonAboutOK(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addContainerGap());
	    }
	    return jDialogAbout;
	}
	
	private JButton getJButtonAboutOK() {
	    if(jButtonAboutOK == null) {
		jButtonAboutOK = new JButton();
		jButtonAboutOK.setText("OK");
		jButtonAboutOK.setAction(getCloseAboutAction());
	    }
	    return jButtonAboutOK;
	}

	private JEditorPane getJEditorPane1() {
	    if(jEditorPaneAbout == null) {
		jEditorPaneAbout = new JEditorPane();
		jEditorPaneAbout.setText("Nurikabe Solver\n\ndesigned by\n\nPhilo Kemenade\nSebastian Droeppelmann");
		jEditorPaneAbout.setEditable(false);
	    }
	    return jEditorPaneAbout;
	}
	
	private AbstractAction getCloseAboutAction() {
	    if(closeAboutAction == null) {
		closeAboutAction = new AbstractAction("OK", null) {
		    public void actionPerformed(ActionEvent evt) {
			getJDialogAbout().dispose();
		    }
		};
	    }
	    return closeAboutAction;
	}
	
	private CanvasPlayfield getPlayfield() {
	    if(Playfield == null) {
		    Playfield = new CanvasPlayfield();
		    Playfield.setSize(330, 330);
	    }
	    return Playfield;
	}
	
	private Canvas getCanvas1() {
	    if(canvas1 == null) {
		canvas1 = new Canvas();
		canvas1.setSize(330, 330);
	    }
	    return canvas1;
	}
	
	private AbstractAction getAbstractActionClose() {
	    
	    if(abstractActionClose == null) {
		abstractActionClose = new AbstractAction("Close", null) {
		    public void actionPerformed(ActionEvent evt) {
			getMainwindow().dispose();
		    }
		};
	    }
	    return abstractActionClose;
	}

	private Mainwindow getMainwindow() {
	    return this;
	}	
}
