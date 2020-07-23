package imageselection;

import inpaint.ImageInpaint;





import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main extends JFrame
  implements Runnable
{
  public Entry entry;
  protected Image entryImage;
  JScrollPane pictureScrollPane;
  File outputFile;
  String fileExtension;
  ImageInpaint Inpainter;
  Thread inpaintThread = null;
  private Boolean fastInpaint = Boolean.valueOf(false);
  private JButton jButton1;
  private JButton jButton2;
  private JButton jButton3;
  private JButton jButton4;
  private JButton jButton6;
  private JButton jButton7;
  private JFrame jFrame5;
  private JFrame jFrame6;
  private JFrame jFrame7;
  private JToolBar jToolBar1;

  Main()
  {
    this.Inpainter = new ImageInpaint(this);
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    try    
    {
    	File f=new File("E:/old laptop/d drive/kmeans/impainting/imageselection/Images/defaultImage.png");
      Image i = ImageIO.read(f);
      setIconImage(i);
    } catch (IOException ex) {
      Logger.getLogger(Entry.class.getName()).log(Level.SEVERE, null, ex);
    }

    setDefaultCloseOperation(3);
    initComponents();
    try
    {
    	File f=new File("E:/old laptop/d drive/kmeans/impainting/imageselection/Images/defaultImage.png");
      this.entryImage = ImageIO.read(f);
    } catch (IOException ex) {
      Logger.getLogger(Entry.class.getName()).log(Level.SEVERE, null, ex);
    }

    setTitle("Hierarchical Super-Resolution-Based Inpainting");
    
    getContentPane().setLayout(null);
    setSize(500, 500);

	  getContentPane().setBackground(Color.gray);//new java.awt.Color(0,111,1));
    this.entry = new Entry(this.entryImage);

    this.entry.setPreferredSize(new Dimension(this.entryImage.getWidth(this), this.entryImage.getHeight(this)));

    getContentPane().add(this.entry);
    this.entry.initImage();
    this.pictureScrollPane = new JScrollPane();
    getContentPane().add(this.pictureScrollPane);
    int w = Math.min(this.entryImage.getWidth(this) + 3, getContentPane().getWidth());
    int h = Math.min(this.entryImage.getHeight(this) + 3, getContentPane().getHeight() - 50);
    if (h == this.entryImage.getHeight(this) + 3)
      this.pictureScrollPane.setBounds((getContentPane().getWidth() - w) / 2, (getContentPane().getHeight() - h) / 2, w, h);
    else {
      this.pictureScrollPane.setBounds((getContentPane().getWidth() - w) / 2, (getContentPane().getHeight() - h) / 2, w, h + 25);
    }

    this.pictureScrollPane.setAlignmentY(0.5F);
    this.pictureScrollPane.setVerticalScrollBarPolicy(20);
    this.pictureScrollPane.setHorizontalScrollBarPolicy(30);
    this.pictureScrollPane.setViewportView(this.entry);

    addComponentListener(new ComponentAdapter()
    {
      public void componentResized(ComponentEvent e) {
        int w = Math.min(Main.this.entryImage.getWidth(Main.this.entry) + 3, Main.this.getContentPane().getWidth());
        int h = Math.min(Main.this.entryImage.getHeight(Main.this.entry) + 3, Main.this.getContentPane().getHeight() - 50);
        if (h == Main.this.entryImage.getHeight(Main.this.entry) + 3)
          Main.this.pictureScrollPane.setBounds((Main.this.getContentPane().getWidth() - w) / 2, (Main.this.getContentPane().getHeight() - h) / 2, w, h);
        else {
          Main.this.pictureScrollPane.setBounds((Main.this.getContentPane().getWidth() - w) / 2, (Main.this.getContentPane().getHeight() - h) / 2, w, h + 25);
        }

        Main.this.pictureScrollPane.setViewportView(Main.this.entry);
      }
    });
  }

  public static void main(String[] args)
  {
    new Main().show();
  }

  private void initComponents() {
    JFrame jFrame1 = new JFrame();
    JFrame jFrame2 = new JFrame();
    JFrame jFrame3 = new JFrame();
    JFrame jFrame4 = new JFrame();

    JMenuBar jMenuBar1 = new JMenuBar();

    JMenu jMenu1 = new JMenu();
    JMenu jMenu2 = new JMenu();
    JMenu jMenu3 = new JMenu();
    JMenu jMenu4 = new JMenu();
    JMenu jMenu5 = new JMenu();

    JMenuItem jMenuItem1 = new JMenuItem();
    JMenuItem jMenuItem2 = new JMenuItem();
    JMenuItem jMenuItem3 = new JMenuItem();
    JMenuItem jMenuItem4 = new JMenuItem();
    JMenuItem jMenuItem5 = new JMenuItem();
    JMenuItem jMenuItem6 = new JMenuItem();
    JMenuItem jMenuItem7 = new JMenuItem();
    JMenuItem jMenuItem8 = new JMenuItem();
    JMenuItem jMenuItem9 = new JMenuItem();
    JMenuItem jMenuItem10=new JMenuItem();
    JMenuItem jMenuItem11=new JMenuItem();
    JMenuItem jMenuItem12=new JMenuItem();
    JMenuItem jMenuItem13=new JMenuItem();

    GroupLayout jFrame1Layout = new GroupLayout(jFrame1.getContentPane());
    jFrame1.getContentPane().setLayout(jFrame1Layout);
     // jFrame1.setBackground(new java.awt.Color(25,231,186));
    jFrame1Layout.setHorizontalGroup(jFrame1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));

    jFrame1Layout.setVerticalGroup(jFrame1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));

    GroupLayout jFrame2Layout = new GroupLayout(jFrame2.getContentPane());
    jFrame2.getContentPane().setLayout(jFrame2Layout);
    jFrame2Layout.setHorizontalGroup(jFrame2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));

    jFrame2Layout.setVerticalGroup(jFrame2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));

    GroupLayout jFrame3Layout = new GroupLayout(jFrame3.getContentPane());
    jFrame3.getContentPane().setLayout(jFrame3Layout);
    jFrame3Layout.setHorizontalGroup(jFrame3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));

    jFrame3Layout.setVerticalGroup(jFrame3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));

    GroupLayout jFrame4Layout = new GroupLayout(jFrame3.getContentPane());
    jFrame4.getContentPane().setLayout(jFrame4Layout);
    jFrame4Layout.setHorizontalGroup(jFrame4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));

    jFrame4Layout.setVerticalGroup(jFrame4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));

    setDefaultCloseOperation(3);

    jMenu1.setText("File");

    jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(79, 2));
    //jMenuItem1.setIcon(new ImageIcon(getClass().getResource("imageselection/Images/open.png")));
    jMenuItem1.setText("Open Image");
    jMenu1.add(jMenuItem1);
     jMenu1.setBackground(new java.awt.Color(25,231,186));
    jMenuItem1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem1ActionPerformed(evt);
      }
    });
    jMenuItem2.setAccelerator(KeyStroke.getKeyStroke(83, 2));
    //jMenuItem2.setIcon(new ImageIcon(getClass().getResource("E:/old laptop/d drive/kmeans/impainting/imageselection/Images/save.png")));
    jMenuItem2.setText("Save");
    jMenu1.add(jMenuItem2);
     jMenu2.setBackground(new java.awt.Color(25,231,186));
    jMenuItem2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem2ActionPerformed(evt);
      }
    });
    jMenuItem7.setAccelerator(KeyStroke.getKeyStroke(83, 10));
    //jMenuItem7.setIcon(new ImageIcon(getClass().getResource("E:/old laptop/d drive/kmeans/impainting/imageselection/Images/saveAs.png")));
    jMenuItem7.setText("Save As");
    jMenu3.setBackground(new java.awt.Color(25,231,186));
    jMenu1.add(jMenuItem7);
     
    jMenuItem7.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem7ActionPerformed(evt);
      }
    });
    jMenuItem3.setAccelerator(KeyStroke.getKeyStroke(115, 8));
    //jMenuItem3.setIcon(new ImageIcon(getClass().getResource("E:/old laptop/d drive/kmeans/impainting/imageselection/Images/exit.png")));
    jMenuItem3.setText("Exit");
    jMenu1.add(jMenuItem3);
    jMenuItem3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem3ActionPerformed(evt);
      }
    });
    jMenuBar1.add(jMenu1);
 jMenuBar1.setBackground(new java.awt.Color(25,231,186));
    jMenu2.setText("Edit");

    jMenuItem4.setAccelerator(KeyStroke.getKeyStroke(90, 2));
    //jMenuItem4.setIcon(new ImageIcon(getClass().getResource("E:/old laptop/d drive/kmeans/impainting/imageselection/Images/undo.png")));
    jMenuItem4.setText("Undo");
    jMenu2.add(jMenuItem4);
    jMenuItem4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem4ActionPerformed(evt);
      }
    });
    jMenuItem5.setAccelerator(KeyStroke.getKeyStroke(89, 2));
   // jMenuItem5.setIcon(new ImageIcon(getClass().getResource("E:/old laptop/d drive/kmeans/impainting/imageselection/Images/redo.png")));
    jMenuItem5.setText("Redo");
    jMenu2.add(jMenuItem5);
    jMenuItem5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem5ActionPerformed(evt);
      }
    });
    jMenuBar1.add(jMenu2);

    jMenu3.setText("Inpaint");

    jMenuItem8.setAccelerator(KeyStroke.getKeyStroke(82, 2));

    jMenuItem8.setText("Run");
    jMenu3.add(jMenuItem8);
    jMenuItem8.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem8ActionPerformed(evt);
      }
    });
    jMenuItem9.setAccelerator(KeyStroke.getKeyStroke(70, 2));

    jMenuItem9.setText("Fast Inpaint");
    jMenu3.add(jMenuItem9);
    jMenuItem9.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem9ActionPerformed(evt);
      }
    });
    jMenu4.setText("");

    jMenuItem6.setAccelerator(KeyStroke.getKeyStroke(112, 0));
    jMenuItem6.setText("");
    jMenu4.add(jMenuItem6);
    jMenu4.setBackground(new java.awt.Color(25,231,186));
    jMenuItem6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem6ActionPerformed(evt);
      }
    });
    
    jMenu5.setText("Zoom");
    jMenuItem10.setText("Zoom in X");
    jMenu5.add(jMenuItem10);
    jMenu5.setBackground(new java.awt.Color(25,231,186));
    jMenuItem10.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem10ActionPerformed(evt);
      }
    });
    
    
    jMenuItem11.setText("Zoom out X");
    jMenu5.add(jMenuItem11);
    jMenu5.setBackground(new java.awt.Color(25,231,186));
    jMenuItem11.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem11ActionPerformed(evt);
      }
    });
    
    jMenuItem12.setText("Zoom in Y");
    jMenu5.add(jMenuItem12);
    jMenu5.setBackground(new java.awt.Color(25,231,186));
    jMenuItem12.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem12ActionPerformed(evt);
      }
    });
    
    
    jMenuItem13.setText("Zoom out Y");
    jMenu5.add(jMenuItem13);
    jMenu5.setBackground(new java.awt.Color(25,231,186));
    jMenuItem13.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem13ActionPerformed(evt);
      }
    });
    
    
    jMenuBar1.add(jMenu3);
    jMenuBar1.add(jMenu5);

    jMenuBar1.add(jMenu4);

    setJMenuBar(jMenuBar1);

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 279, 32767));

    this.jFrame5 = new JFrame();
    this.jFrame6 = new JFrame();
    this.jFrame7 = new JFrame();
    this.jToolBar1 = new JToolBar();
    this.jButton1 = new JButton();
    this.jButton2 = new JButton();
    this.jButton3 = new JButton();
    this.jButton4 = new JButton();
    this.jButton6 = new JButton();
    this.jButton7 = new JButton();

    GroupLayout jFrame5Layout = new GroupLayout(this.jFrame5.getContentPane());
   
    this.jFrame5.getContentPane().setLayout(jFrame5Layout);
   // jFrame5.setBackground(new java.awt.Color(25,231,186));
    jFrame5Layout.setHorizontalGroup(jFrame5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));

    jFrame5Layout.setVerticalGroup(jFrame5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));

    GroupLayout jFrame6Layout = new GroupLayout(this.jFrame6.getContentPane());
    this.jFrame6.getContentPane().setLayout(jFrame6Layout);
    jFrame6Layout.setHorizontalGroup(jFrame6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));

    jFrame6Layout.setVerticalGroup(jFrame6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));

    GroupLayout jFrame7Layout = new GroupLayout(this.jFrame7.getContentPane());
    this.jFrame7.getContentPane().setLayout(jFrame7Layout);
    jFrame7Layout.setHorizontalGroup(jFrame7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));

    jFrame7Layout.setVerticalGroup(jFrame7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));

    setDefaultCloseOperation(3);
jToolBar1.setBackground(new java.awt.Color(0,111,1));
    this.jToolBar1.setRollover(true);

   // this.jButton1.setIcon(new ImageIcon(getClass().getResource("/imageselection/Images/open.png")));
    //this.jButton1.setFocusable(false);
   // this.jButton1.setHorizontalTextPosition(0);
   // this.jButton1.setVerticalTextPosition(3);
   // this.jToolBar1.add(this.jButton1);
    this.jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem1ActionPerformed(evt);
      }
    });
   // this.jButton2.setIcon(new ImageIcon(getClass().getResource("/imageselection/Images/save.png")));
   // this.jButton2.setFocusable(false);
   // this.jButton2.setHorizontalTextPosition(0);
   // this.jButton2.setVerticalTextPosition(3);
   // this.jToolBar1.add(this.jButton2);
    this.jButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem2ActionPerformed(evt);
      }
    });
   // this.jButton3.setIcon(new ImageIcon(getClass().getResource("/imageselection/Images/undo.png")));
    //this.jButton3.setFocusable(false);
   // this.jButton3.setHorizontalTextPosition(0);
   // this.jButton3.setVerticalTextPosition(3);
   // this.jToolBar1.add(this.jButton3);
    this.jButton3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem4ActionPerformed(evt);
      }
    });
    //this.jButton4.setIcon(new ImageIcon(getClass().getResource("/imageselection/Images/redo.png")));
   // this.jButton4.setFocusable(false);
   // this.jButton4.setHorizontalTextPosition(0);
   // this.jButton4.setVerticalTextPosition(3);
   // this.jToolBar1.add(this.jButton4);
    this.jButton4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem5ActionPerformed(evt);
      }
    });
   // this.jButton6.setIcon(new ImageIcon(getClass().getResource("/imageselection/Images/play-button.png")));
   // this.jButton6.setFocusable(false);
   // this.jButton6.setHorizontalTextPosition(0);
   // this.jButton6.setVerticalTextPosition(3);
   // this.jToolBar1.add(this.jButton6);
    this.jButton6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.jMenuItem9ActionPerformed(evt);
      }
    });
    //this.jButton7.setIcon(new ImageIcon(getClass().getResource("/imageselection/Images/pause-button.png")));
   // this.jButton7.setFocusable(false);
    //this.jButton7.setHorizontalTextPosition(0);
   // this.jButton7.setVerticalTextPosition(3);
   // this.jToolBar1.add(this.jButton7);
    this.jButton7.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Main.this.PauseActionPerformed(evt);
      }
    });
    GroupLayout layout1 = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout1);
    layout1.setHorizontalGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jToolBar1, -1, 400, 32767));

    layout1.setVerticalGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout1.createSequentialGroup().addComponent(this.jToolBar1, -2, 25, -2).addContainerGap(275, 32767)));

    pack();
  }

  private void jMenuItem1ActionPerformed(ActionEvent evt)
  {
    if (!this.entry.isDisabled.booleanValue())
    {
      JFileChooser _fileChooser = new JFileChooser();
      int retval = _fileChooser.showOpenDialog(this);

      String[] okFileExtensions = { "jpg", "png", "gif", "bmp", "jpeg" };

      if (retval == 0)
        try {
          File file = _fileChooser.getSelectedFile();
          Boolean flag = Boolean.valueOf(false);
          for (String extension : okFileExtensions) {
            if (file.getName().toLowerCase().endsWith(extension)) {
              this.outputFile = file;
              this.fileExtension = extension;
              flag = Boolean.valueOf(true);
            }
          }
          if (!flag.booleanValue()) {
            JOptionPane.showMessageDialog(this, "Please choose a jpg, jpeg, png, bmp or gif file only.", "Error", 0);
            return;
          }

          this.entry.SavedImages.clear();
          this.entry.RedoImages.clear();
          BufferedImage selectedImage = ImageIO.read(file);
          Image tmg = createImage(selectedImage.getWidth(this), selectedImage.getHeight(this));
          Graphics tg = tmg.getGraphics();
          tg.drawImage(selectedImage, 0, 0, null);
          this.entry.SavedImages.push(selectedImage);
          this.entryImage = tmg;
          this.entry.showImage(this.entryImage);
          this.entry.setPreferredSize(new Dimension(this.entryImage.getWidth(this), this.entryImage.getHeight(this)));
          int w = Math.min(this.entryImage.getWidth(this) + 3, getContentPane().getWidth());
          int h = Math.min(this.entryImage.getHeight(this) + 3, getContentPane().getHeight());
          this.pictureScrollPane.setBounds((getContentPane().getWidth() - w) / 2, (getContentPane().getHeight() - h) / 2, w, h);
          this.pictureScrollPane.setViewportView(this.entry);
        } catch (IOException ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }
  
  private void updateImage(BufferedImage selectedImage){
	  Image tmg = createImage(selectedImage.getWidth(this), selectedImage.getHeight(this));
	  Graphics tg = tmg.getGraphics();
      tg.drawImage(selectedImage, 0, 0, null);
      this.entry.SavedImages.push(selectedImage);
      this.entryImage = tmg;
      this.entry.showImage(this.entryImage);
      this.entry.setPreferredSize(new Dimension(this.entryImage.getWidth(this), this.entryImage.getHeight(this)));
      int w = Math.min(this.entryImage.getWidth(this) + 3, getContentPane().getWidth());
      int h = Math.min(this.entryImage.getHeight(this) + 3, getContentPane().getHeight());
      this.pictureScrollPane.setBounds((getContentPane().getWidth() - w) / 2, (getContentPane().getHeight() - h) / 2, w, h);
      this.pictureScrollPane.setViewportView(this.entry);
  }

  private void jMenuItem4ActionPerformed(ActionEvent evt)
  {
    Boolean flag = Boolean.valueOf(false);

    if ((!this.entry.isDisabled.booleanValue()) && (this.entry.SavedImages.size() > 1)) {
      if (this.entry.getPressed().booleanValue()) {
        this.entry.entryReset();
        flag = Boolean.valueOf(true);
      }
      this.entry.RedoImages.push(this.entry.SavedImages.pop());
      Image tmg = createImage(((Image)this.entry.SavedImages.peek()).getWidth(this), ((Image)this.entry.SavedImages.peek()).getHeight(this));
      Graphics tg = tmg.getGraphics();
      tg.drawImage((Image)this.entry.SavedImages.peek(), 0, 0, null);
      this.entry.showImage(tmg);

      this.entry.setPreferredSize(new Dimension(this.entryImage.getWidth(this), this.entryImage.getHeight(this)));
      int w = Math.min(this.entryImage.getWidth(this) + 3, getContentPane().getWidth());
      int h = Math.min(this.entryImage.getHeight(this) + 3, getContentPane().getHeight());
      this.pictureScrollPane.setBounds((getContentPane().getWidth() - w) / 2, (getContentPane().getHeight() - h) / 2, w, h);
      this.pictureScrollPane.setViewportView(this.entry);

      if (flag.booleanValue())
        jMenuItem5ActionPerformed(evt);
    }
  }

  private void jMenuItem5ActionPerformed(ActionEvent evt)
  {
    if ((!this.entry.isDisabled.booleanValue()) && (this.entry.RedoImages.size() > 0))
    {
      Image tmg = createImage(((Image)this.entry.RedoImages.peek()).getWidth(this), ((Image)this.entry.RedoImages.peek()).getHeight(this));
      Graphics tg = tmg.getGraphics();
      tg.drawImage((Image)this.entry.RedoImages.peek(), 0, 0, null);
      this.entry.showImage(tmg);
      this.entry.SavedImages.push(this.entry.RedoImages.pop());

      this.entry.setPreferredSize(new Dimension(this.entryImage.getWidth(this), this.entryImage.getHeight(this)));
      int w = Math.min(this.entryImage.getWidth(this) + 3, getContentPane().getWidth());
      int h = Math.min(this.entryImage.getHeight(this) + 3, getContentPane().getHeight());
      this.pictureScrollPane.setBounds((getContentPane().getWidth() - w) / 2, (getContentPane().getHeight() - h) / 2, w, h);
      this.pictureScrollPane.setViewportView(this.entry);
    }
  }

  private void jMenuItem6ActionPerformed(ActionEvent evt) {
   // new Help().show();
  }

  private void jMenuItem2ActionPerformed(ActionEvent evt)
  {
    if (this.outputFile == null) {
      System.err.println("Error!! No file to save");
      return;
    }
    try
    {
      BufferedImage bi = (BufferedImage)this.entry.getImage();
      ImageIO.write(bi, this.fileExtension, this.outputFile);
    } catch (IOException e) {
      System.err.println("Error!! File not saved");
    }
  }

  private void jMenuItem7ActionPerformed(ActionEvent evt)
  {
    JFileChooser _fileChooser = new JFileChooser();
    int retval = _fileChooser.showSaveDialog(this);

    String[] okFileExtensions = { "jpg", "png", "gif", "bmp", "jpeg" };

    if (retval == 0) {
      File file = _fileChooser.getSelectedFile();
      Boolean flag = Boolean.valueOf(false);
      for (String extension : okFileExtensions) {
        if (file.getName().toLowerCase().endsWith(extension)) {
          if (this.outputFile == null) {
            System.err.println("Error!! No file to save");
            return;
          }
          try {
            this.outputFile = file;
            this.fileExtension = extension;
            BufferedImage bi = (BufferedImage)this.entry.getImage();
            ImageIO.write(bi, this.fileExtension, this.outputFile);
            System.out.println("Saved");
          } catch (IOException e) {
            System.err.println("Error!! File not saved");
          }

          flag = Boolean.valueOf(true);
        }
      }
      if (!flag.booleanValue()) {
        JOptionPane.showMessageDialog(this, "Please choose a jpg, jpeg, png, bmp or gif file only.", "Error", 0);
        return;
      }
    }
  }

  private void jMenuItem8ActionPerformed(ActionEvent evt)
  {
    this.Inpainter.halt = Boolean.valueOf(false);
    this.Inpainter.completed = Boolean.valueOf(false);
    if (this.inpaintThread == null) {
      this.entry.setDisabled();
      this.inpaintThread = new Thread(this);
      this.inpaintThread.start();
    }
  }
  
  private void jMenuItem10ActionPerformed(ActionEvent evt){
	  BufferedImage bf=(BufferedImage)this.entryImage;
	  
	  zoom(bf.getWidth()+10,bf.getHeight(),bf);
  }
  private void jMenuItem11ActionPerformed(ActionEvent evt){
	  BufferedImage bf=(BufferedImage)this.entryImage;
	  zoom(bf.getWidth()-10,bf.getHeight(),bf);
  }
  private void jMenuItem12ActionPerformed(ActionEvent evt){
	  BufferedImage bf=(BufferedImage)this.entryImage;
	  zoom(bf.getWidth(),bf.getHeight()+10,bf);
  }
  private void jMenuItem13ActionPerformed(ActionEvent evt){
	  BufferedImage bf=(BufferedImage)this.entryImage;
	  zoom(bf.getWidth(),bf.getHeight()-10,bf);
  }
  
  private void zoom(int newWidth,int newHeight,BufferedImage image){
	  BufferedImage resizedImage = new BufferedImage(newWidth , newHeight, image.getType());
	  Graphics2D g = resizedImage.createGraphics();
	  g.drawImage(image, 0, 0, newWidth , newHeight , null);
	  g.dispose();
	  this.entryImage=resizedImage;
	  updateImage(resizedImage);
  }


  private void jMenuItem9ActionPerformed(ActionEvent evt)
  {
    this.fastInpaint = Boolean.valueOf(true);
    this.Inpainter.halt = Boolean.valueOf(false);
    this.Inpainter.completed = Boolean.valueOf(false);
    if (this.inpaintThread == null) {
      this.entry.setDisabled();
      this.inpaintThread = new Thread(this);
      this.inpaintThread.start();
    }
  }

  private void PauseActionPerformed(ActionEvent evt) {
    if (this.inpaintThread != null)
      this.Inpainter.halt = Boolean.valueOf(true);
  }

  private void jMenuItem3ActionPerformed(ActionEvent evt)
  {
    System.exit(0);
  }

  public void run()
  {
	  BufferedImage image=(BufferedImage)this.entry.getImage();
	  try{
		  BufferedImage image2=Compression.getCompressedImage(image);
		  new DisplayFrame(image2);
		  zoomAndDisplay(image2);
	  }catch(Exception ex){
		  ex.printStackTrace();
	  }
    this.Inpainter.init(image, (BufferedImage)this.entry.getImage(), this.fastInpaint);
  }
  
  private void zoomAndDisplay(BufferedImage image){
	  
	  int newHeight=0;
	  int newWidth=0;
	  int imageHeight=image.getHeight();
	  int imageWidth=image.getWidth();
	  
	  for(int i=-3;i<3;i++){
		  
		  newWidth=imageWidth+10*i;
		  newHeight=imageHeight+10*i;		 
		BufferedImage resizedImage = new BufferedImage(newWidth , newHeight, image.getType());	  
	  	Graphics2D g = resizedImage.createGraphics();
	  	g.drawImage(image, 0, 0, newWidth , newHeight , null);
	  	g.dispose();
	  	new DisplayFrame(resizedImage);
	  }
	  
  }

  public void updateStats(BufferedImage toShow)
  {
    UpdateStats stats = new UpdateStats();
    stats.toShow = toShow;
    try {
      SwingUtilities.invokeAndWait(stats);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "Error: " + e, "Training", 0);
    }

    if (this.Inpainter.completed.booleanValue())
    {
      JOptionPane.showMessageDialog(this, "                      Inpainting is completed.", "Inpainting", -1);
    }

    if ((this.Inpainter.completed.booleanValue()) || (this.Inpainter.halt.booleanValue())) {
      System.out.println("Inpainting completed or halted");
      this.inpaintThread = null;
      Image tmg = createImage(toShow.getWidth(this), toShow.getHeight(this));
      Graphics tg = tmg.getGraphics();
      tg.drawImage(toShow, 0, 0, null);
      this.entry.SavedImages.push(tmg);
      this.entry.setEnabled();
      this.entry.RedoImages.clear();
      this.fastInpaint = Boolean.valueOf(false);
    }
  }

  public class UpdateStats implements Runnable {
    BufferedImage toShow;

    public UpdateStats() {
    }

    public void run() { Main.this.entry.showImage(this.toShow); 
    }

  }

  class SymAction
    implements ActionListener
  {
    SymAction()
    {
    }

    public void actionPerformed(ActionEvent event)
    {
      Object object = event.getSource();
    }
  }
}