/*      */ package svm;
/*      */ 
/*      */ import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
/*      */ import java.awt.FileDialog;
/*      */ import java.awt.Font;
import java.awt.GridLayout;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JRadioButton;
/*      */ import javax.swing.JTabbedPane;
/*      */ import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/*      */ 
/*      */ public class svm_frame extends javax.swing.JFrame implements Runnable
/*      */ {
				JTextField browseOpen,browseSave;				
				JButton browseOpenButton,browseSaveButton,processButton;
		
				class TopPanel extends JPanel{
					TopPanel(){
						
						//Border border=BorderFactory.createLineBorder(Color.BLACK);
						
						int eb = 10;
						this.setBorder(BorderFactory.createCompoundBorder(
								BorderFactory.createEtchedBorder(),
								BorderFactory.createEmptyBorder(eb, eb, eb, eb)));
						//new TitledBorder(border,"User Name"));
						browseOpen=new JTextField("",40);
						//	ip=new JTextField("",20);
						Box vertical1 = Box.createHorizontalBox();
						//end.setBackground(new Color(210, 220, 255));
						
						vertical1.add( Box.createHorizontalStrut( 20 ) );
						vertical1.add( browseOpen );
						browseOpenButton=new JButton("    Browse...   ");
						vertical1.add( Box.createHorizontalStrut( 25) );
						vertical1.add(browseOpenButton);						
						
						browseOpenButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent ev){
								JFileChooser fileChooser=new JFileChooser();
						  		
						  		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						  		int result=fileChooser.showOpenDialog(null);
						  		if(result==JFileChooser.CANCEL_OPTION)
						  					return;
						  				
						  				
						  		String path=(fileChooser.getSelectedFile().getAbsolutePath());
						  		browseOpen.setText(path);
							}
							
						});						
						add(vertical1);
					}
				}
				
				class MiddlePanel extends JPanel{
					MiddlePanel(){
						//Border border=BorderFactory.createLineBorder(Color.BLACK);
						int eb = 10;
						this.setBorder(BorderFactory.createCompoundBorder(
								BorderFactory.createEtchedBorder(),
								BorderFactory.createEmptyBorder(eb, eb, eb, eb)));
						//new TitledBorder(border,"User Name"));
						
						//this.setBorder(new TitledBorder(border,"Text Messages"));
						setLayout(new GridLayout(1,1));
						processButton=new JButton("Feature Selection");
						processButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent ev){
								
								try{
									if(browseOpen.getText().equals("") || browseSave.getText().equals("")){
										JOptionPane.showMessageDialog(null,"invalid file names" );
										return;
									}
									
									else{
										
										ArrayList<String> list=FeatureSelectionMetrics.getList(browseOpen.getText());
										FeatureSelectionMetrics.writeToFile(list, browseSave.getText());
										JOptionPane.showMessageDialog(null,"File contents are written" );
									}
								}catch(FileNotFoundException ex){
									ex.printStackTrace();
									JOptionPane.showMessageDialog(null,"invalid file names" );
								}catch(Exception ex){
									ex.printStackTrace();
									JOptionPane.showMessageDialog(null,"invalid file contents" );
								}
							}
							
						});
						add(processButton);
						
					}
				}
				
				class BottomPanel extends JPanel{
					BottomPanel(){
						//Border border=BorderFactory.createLineBorder(Color.BLACK);
						int eb = 10;
						this.setBorder(BorderFactory.createCompoundBorder(
								BorderFactory.createEtchedBorder(),
								BorderFactory.createEmptyBorder(eb, eb, eb, eb)));
						//new TitledBorder(border,"User Name"));
						
						browseSave=new JTextField("output.txt",40);
						
						//	ip=new JTextField("",20);
						Box vertical1 = Box.createHorizontalBox();
						//end.setBackground(new Color(210, 220, 255));
						
						vertical1.add( Box.createHorizontalStrut( 20 ) );
						vertical1.add(browseSave);
						browseSaveButton=new JButton("    Browse...   ");
						vertical1.add( Box.createHorizontalStrut( 25) );
						vertical1.add(browseSaveButton);						
						
						browseSaveButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent ev){
								JFileChooser fileChooser=new JFileChooser();
						  		
						  		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						  		int result=fileChooser.showOpenDialog(null);
						  		if(result==JFileChooser.CANCEL_OPTION)
						  					return;
						  				
						  				
						  		String path=(fileChooser.getSelectedFile().getAbsolutePath());
						  		browseSave.setText(path);
							}
							
						});						
						add(vertical1);
					}
				}
	
			
	
/*      */   JPanel contentPane;
/*   20 */   javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
/*   21 */   javax.swing.JMenu jMenuFile = new javax.swing.JMenu();
/*      */   
/*   23 */   JMenuItem jMenuFileClass = new JMenuItem();
/*   24 */   JMenuItem jMenuFilePred = new JMenuItem();
/*   25 */   JMenuItem jMenuFileExit = new JMenuItem();
/*      */   
/*   27 */   javax.swing.JMenu jMenuHelp = new javax.swing.JMenu();
/*   28 */   JMenuItem jMenuHelpAbout = new JMenuItem();
/*   29 */   JLabel statusBar = new JLabel();
/*      */   
/*   31 */   javax.swing.JMenu jMenuView = new javax.swing.JMenu();
/*   32 */   javax.swing.JMenu jMenuViewGraphic = new javax.swing.JMenu();
/*   33 */   JMenuItem jMenuViewGraphic2d = new JMenuItem();
/*   34 */   javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
/*   35 */   javax.swing.JTextArea jTextArea1 = new javax.swing.JTextArea();
/*   36 */   JTabbedPane jTabbedPane1 = new JTabbedPane();
/*   37 */   JPanel jpanel1 = new JPanel();
/*   38 */   JPanel jpanel2 = new JPanel();
/*   39 */   JPanel jpanel3 = new JPanel();
/*   40 */   JLabel jlabel1 = new JLabel();
/*   41 */   JPanel jpanel4 = new JPanel();
/*   41 */   JPanel end= new JPanel();
			 private JPanel topPanel,middlePanel,bottomPanel;





/*   42 */   JRadioButton jRadioButtonClassif = new JRadioButton();
/*   43 */   JRadioButton jRadioButtonPredict = new JRadioButton();
/*      */   
/*   45 */   int nextflag = 1;
/*   46 */   int backflag = 1;
/*   47 */   JLabel jLabel1 = new JLabel();
/*   48 */   JTextField jTxtDataset = new JTextField();
/*   49 */   JButton jBtnBrowseDataset = new JButton();
/*   50 */   JLabel jLabel2 = new JLabel();
/*   51 */   JLabel jLabel3 = new JLabel();
/*   52 */   JLabel jLabel4 = new JLabel();
/*   53 */   JTextField jTxtModel = new JTextField();
/*   54 */   JButton jBtnBrowseModel = new JButton();
/*   55 */   JLabel jLabel5 = new JLabel();
/*   56 */   JRadioButton jRBtnCSVC = new JRadioButton();
/*   57 */   JRadioButton jRBtnNUSVC = new JRadioButton();
/*   58 */   JRadioButton jRBtnONECLASS = new JRadioButton();
/*   59 */   JRadioButton jRBtnEPS = new JRadioButton();
/*   60 */   JRadioButton jRBtnNUSVR = new JRadioButton();
/*   61 */   JLabel jLabel6 = new JLabel();
/*   62 */   JLabel jLabel7 = new JLabel();
/*   63 */   JLabel jLblCost = new JLabel();
/*   64 */   JTextField jTxtCost = new JTextField();
/*   65 */   JLabel jLblNU = new JLabel();
/*   66 */   JTextField jTxtNU = new JTextField();
/*   67 */   JLabel jLblEPS = new JLabel();
/*   68 */   JTextField jTxtEPS = new JTextField();
/*   69 */   JLabel jLabel8 = new JLabel();
/*   70 */   JLabel jLabel9 = new JLabel();
/*   71 */   JLabel jLabel10 = new JLabel();
/*   72 */   JRadioButton jRBLIN = new JRadioButton();
/*   73 */   JRadioButton jRBPOL = new JRadioButton();
/*   74 */   JRadioButton jRBRAD = new JRadioButton();
/*   75 */   JRadioButton jRBSIG = new JRadioButton();
/*   76 */   JLabel jLabel11 = new JLabel();
/*   77 */   JLabel jLabel12 = new JLabel();
/*   78 */   JLabel jLDEG = new JLabel();
/*   79 */   JTextField jTxtDEG = new JTextField();
/*   80 */   JLabel jLGAM = new JLabel();
/*   81 */   JTextField jTxtGAM = new JTextField();
/*   82 */   JLabel jLCOE = new JLabel();
/*   83 */   JTextField jTxtCOE = new JTextField();
/*   84 */   JLabel jLabel13 = new JLabel();
/*   85 */   JPanel jpanel5 = new JPanel();
/*   86 */   JPanel jpanel6 = new JPanel();
/*   87 */   JTextField jTxtCASH = new JTextField();
/*   88 */   JLabel jLabel15 = new JLabel();
/*   89 */   JTextField jTxtTOL = new JTextField();
/*   90 */   JLabel jLabel18 = new JLabel();
/*   91 */   JButton jBtnRUN = new JButton();
/*   92 */   JLabel jLabel19 = new JLabel();
/*   93 */   JTextField jTxtLOG = new JTextField();
/*   94 */   JButton jBtnLOG = new JButton();
/*   95 */   JLabel jLabel20 = new JLabel();
/*   96 */   String args = "";
/*      */   private svm.libsvm.svm_parameter param;
/*      */   private svm.libsvm.svm_problem prob;
/*      */   private svm.libsvm.svm_model model;
/*      */   private String input_file_name;
/*      */   private String model_file_name;
/*      */   private String error_msg;
/*      */   private int cross_validation;
/*      */   private int nr_fold;
/*  105 */   String svm_type = "0";
/*  106 */   String kernel_type = "2";
/*  107 */   JCheckBox jChkBxShrink = new JCheckBox();
/*  108 */   JCheckBox jChkBxProb = new JCheckBox();
/*      */   int f;
/*  110 */   javax.swing.ImageIcon image1 = new javax.swing.ImageIcon();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Thread t;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public svm_frame()
/*      */   {
	System.out.println("svm frame called");
/*  123 */     enableEvents(64L);
/*      */     try {
/*  125 */       jbInit();
/*      */     }
/*      */     catch (Exception e) {
/*  128 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */   
/*      */   private void jbInit() throws Exception
/*      */   {
/*  134 */     image1 = new javax.swing.ImageIcon(svm_frame.class.getResource("progress.gif"));
/*  135 */     progressbar.setIcon(image1);
/*  136 */     progressbar.setVisible(false);
/*  137 */     contentPane = ((JPanel)getContentPane());
/*  138 */     contentPane.setLayout(null);
/*  139 */     setSize(new java.awt.Dimension(739, 661));
/*  140 */     setTitle("SVM Classifier");
/*  141 */     statusBar.setText(" ");
/*  142 */     statusBar.setBounds(new Rectangle(0, 638, 739, 22));
/*  143 */     jMenuFile.setText("Select");
/*  144 */     jMenuFileClass.setFont(new Font("Arial", 0, 12));
/*  145 */     jMenuFileClass.setActionCommand("Calssification");
/*  146 */     jMenuFileClass.setText("::  Calssification");
/*  147 */     jMenuFileClass.addActionListener(new svm_frame_jMenuFileClass_ActionAdapter(this));
/*  148 */     jMenuFilePred.setFont(new Font("Arial", 0, 12));
/*  149 */     jMenuFilePred.setActionCommand("Prediction");
/*  150 */     jMenuFilePred.setText("::   Prediction");
/*  151 */     jMenuFilePred.addActionListener(new svm_frame_jMenuFilePred_ActionAdapter(this));
/*  152 */     jMenuFileExit.setText("Exit");
/*  153 */     jMenuFileExit.addActionListener(new svm_frame_jMenuFileExit_ActionAdapter(this));
/*      */     
/*  155 */     jMenuView.setText("View");
/*  156 */     jMenuViewGraphic.setText("Graphic");
/*  157 */     jMenuViewGraphic2d.setText("2D Graphic");
/*  158 */     jMenuViewGraphic2d.addActionListener(new svm_frame_jMenuViewGraphic2d_ActionAdapter(this));
/*      */     
/*      */ 
/*  161 */     jMenuHelp.setText("Help");
/*  162 */     jMenuHelpAbout.setText("About SVM Classifier ...");
/*  163 */     jMenuHelpAbout.addActionListener(new svm_frame_jMenuHelpAbout_ActionAdapter(this));
/*  164 */     jScrollPane1.setBounds(new Rectangle(1, 425, 730, 168));
/*  165 */     jTabbedPane1.setBorder(javax.swing.BorderFactory.createLoweredBevelBorder());
/*  166 */     jTabbedPane1.setBounds(new Rectangle(45, 44, 654, 344));
/*  167 */     jpanel1.setLayout(null);
/*  168 */     jlabel1.setFont(new Font("Arial", 0, 12));
/*  169 */     jlabel1.setForeground(Color.blue);
/*  170 */     jlabel1.setText("Select your task:");
/*  171 */     jlabel1.setBounds(new Rectangle(32, 28, 182, 39));
/*  172 */     jpanel4.setLayout(null);
/*  173 */     jpanel3.setLayout(null);
/*  174 */     jpanel2.setLayout(null);


/*  175 */     jRadioButtonClassif.setBackground(new Color(210, 220, 255));
/*  176 */     jRadioButtonClassif.setText("Create new training model (Classification)");
/*  177 */     jRadioButtonClassif.setBounds(new Rectangle(79, 85, 294, 23));
/*  178 */     jRadioButtonClassif.addActionListener(new svm_frame_jRadioButtonClassif_actionAdapter(this));
/*      */     
/*  180 */     jRadioButtonClassif.addActionListener(new svm_frame_jRadioButtonClassif_actionAdapter(this));

/*  182 */     jRadioButtonPredict.setBackground(new Color(210, 220, 255));
/*  183 */     jRadioButtonPredict.setText("Open a classifcation model and apply it to a test data (Prediction)");
/*      */     
/*  185 */     jRadioButtonPredict.setBounds(new Rectangle(79, 123, 466, 23));
/*  186 */     jRadioButtonPredict.addActionListener(new svm_frame_jRadioButtonPredict_actionAdapter(this));
/*      */     
/*  188 */     jpanel2.setBackground(new Color(210, 220, 255));
/*  189 */     jpanel2.setEnabled(true);
/*  190 */     jpanel2.setRequestFocusEnabled(false);
/*  191 */     jpanel2.addComponentListener(new svm_frame_jpanel2_componentAdapter(this));
/*  192 */     jpanel1.addComponentListener(new svm_frame_jpanel1_componentAdapter(this));
/*  193 */     jpanel3.addComponentListener(new svm_frame_jpanel3_componentAdapter(this));
/*  194 */     jpanel4.addComponentListener(new svm_frame_jpanel4_componentAdapter(this));
/*  195 */     jLabel1.setFont(new Font("Arial", 0, 12));
/*  196 */     jLabel1.setText("File type:");
/*  197 */     jLabel1.setBounds(new Rectangle(98, 63, 74, 34));
/*  198 */     jTxtDataset.setFont(new Font("Arial", 0, 12));
/*  199 */     jTxtDataset.setText("");
/*  200 */     jTxtDataset.setBounds(new Rectangle(102, 44, 307, 24));
/*  201 */     jBtnBrowseDataset.setBounds(new Rectangle(427, 43, 119, 25));
/*  202 */     jBtnBrowseDataset.setText("Browse...");
/*  203 */     jBtnBrowseDataset.addActionListener(new svm_frame_jBtnBrowseDataset_actionAdapter(this));
/*      */     
/*  205 */     jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  206 */     jLabel2.setText("");
/*  207 */     jLabel2.setBounds(new Rectangle(81, 33, 482, 64));
/*  208 */     jLabel3.setFont(new Font("Arial", 0, 12));
/*  209 */     jLabel3.setForeground(Color.blue);
/*  210 */     jLabel3.setText("Save model file:");
/*  211 */     jLabel3.setBounds(new Rectangle(81, 184, 138, 29));
/*  212 */     jLabel4.setBounds(new Rectangle(81, 124, 482, 50));
/*  213 */     jLabel4.setText("");
/*  214 */     jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  215 */     jTxtModel.setBounds(new Rectangle(103, 216, 307, 24));
/*  216 */     jTxtModel.setFont(new Font("Arial", 0, 12));
/*  217 */     jTxtModel.setText("model.svm");
/*  218 */     jBtnBrowseModel.setText("Browse...");
/*  219 */     jBtnBrowseModel.addActionListener(new svm_frame_jBtnBrowseModel_actionAdapter(this));
/*      */     
/*  221 */     jBtnBrowseModel.setBounds(new Rectangle(428, 216, 119, 25));
/*  222 */     jLabel5.setFont(new Font("Arial", 0, 12));
/*  223 */     jLabel5.setForeground(Color.blue);
/*  224 */     jLabel5.setText("SVM type:");
/*  225 */     jLabel5.setBounds(new Rectangle(24, 30, 149, 26));
/*  226 */     jRBtnCSVC.setBackground(new Color(210, 220, 255));
/*  227 */     jRBtnCSVC.setText("C-SVC");
/*  228 */     jRBtnCSVC.setBounds(new Rectangle(45, 66, 168, 23));
/*  229 */     jRBtnCSVC.addActionListener(new svm_frame_jRBtnCSVC_actionAdapter(this));
/*  230 */     jRBtnNUSVC.setBackground(new Color(210, 220, 255));
/*  231 */     jRBtnNUSVC.setText("nu-SVC");
/*  232 */     jRBtnNUSVC.setBounds(new Rectangle(45, 160, 175, 23));
/*  233 */     jRBtnNUSVC.addActionListener(new svm_frame_jRBtnNUSVC_actionAdapter(this));
/*  234 */     jRBtnONECLASS.setBackground(new Color(210, 220, 255));
/*  235 */     jRBtnONECLASS.setText("one-class SVM");
/*  236 */     jRBtnONECLASS.setBounds(new Rectangle(45, 113, 187, 23));
/*  237 */     jRBtnONECLASS.addActionListener(new svm_frame_jRBtnONECLASS_actionAdapter(this));
/*  238 */     jRBtnEPS.setBackground(new Color(210, 220, 255));
/*  239 */     jRBtnEPS.setText("epsilon-SVR");
/*  240 */     jRBtnEPS.setBounds(new Rectangle(45, 136, 179, 23));
/*  241 */     jRBtnEPS.addActionListener(new svm_frame_jRBtnEPS_actionAdapter(this));
/*  242 */     jRBtnNUSVR.setBackground(new Color(210, 220, 255));
/*  243 */     jRBtnNUSVR.setText("nu-SVR");
/*  244 */     jRBtnNUSVR.setBounds(new Rectangle(45, 89, 176, 23));
/*  245 */     jRBtnNUSVR.addActionListener(new svm_frame_jRBtnNUSVR_actionAdapter(this));
/*  246 */     jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  247 */     jLabel6.setText("");
/*  248 */     jLabel6.setBounds(new Rectangle(23, 54, 226, 144));
/*  249 */     jLabel7.setFont(new Font("Arial", 0, 12));
/*  250 */     jLabel7.setForeground(Color.blue);
/*  251 */     jLabel7.setText("SVM type parameters:");
/*  252 */     jLabel7.setBounds(new Rectangle(299, 27, 243, 30));
/*  253 */     jLblCost.setText("Cost:");
/*  254 */     jLblCost.setBounds(new Rectangle(322, 76, 42, 25));
/*  255 */     jTxtCost.setText("1");
/*  256 */     jTxtCost.setBounds(new Rectangle(379, 79, 87, 21));
/*  257 */     jLblNU.setEnabled(false);
/*  258 */     jLblNU.setText("nu:");
/*  259 */     jLblNU.setBounds(new Rectangle(322, 110, 27, 22));
/*  260 */     jTxtNU.setBounds(new Rectangle(379, 113, 85, 21));
/*  261 */     jTxtNU.setEnabled(false);
/*  262 */     jTxtNU.setText("0.5");
/*  263 */     jLblEPS.setEnabled(false);
/*  264 */     jLblEPS.setText("epsilon:");
/*  265 */     jLblEPS.setBounds(new Rectangle(322, 141, 67, 31));
/*  266 */     jTxtEPS.setEnabled(false);
/*  267 */     jTxtEPS.setText("0.1");
/*  268 */     jTxtEPS.setBounds(new Rectangle(379, 147, 85, 21));
/*  269 */     jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  270 */     jLabel8.setText("");
/*  271 */     jLabel8.setBounds(new Rectangle(300, 55, 267, 180));
/*  272 */     jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  273 */     jLabel9.setBounds(new Rectangle(36, 62, 562, 121));
/*  274 */     jLabel10.setFont(new Font("Arial", 0, 12));
/*  275 */     jLabel10.setForeground(Color.blue);
/*  276 */     jLabel10.setText("Kernel type:");
/*  277 */     jLabel10.setBounds(new Rectangle(26, 27, 157, 26));
/*  278 */     jRBLIN.setBackground(new Color(210, 220, 255));
/*  279 */     jRBLIN.setText("Linear");
/*  280 */     jRBLIN.setBounds(new Rectangle(40, 58, 185, 33));
/*  281 */     jRBLIN.addActionListener(new svm_frame_jRBLIN_actionAdapter(this));
/*  282 */     jRBPOL.setBackground(new Color(210, 220, 255));
/*  283 */     jRBPOL.setText("Polynomial");
/*  284 */     jRBPOL.setBounds(new Rectangle(40, 90, 185, 33));
/*  285 */     jRBPOL.addActionListener(new svm_frame_jRBPOL_actionAdapter(this));
/*  286 */     jRBRAD.setBackground(new Color(210, 220, 255));
/*  287 */     jRBRAD.setText("Radial Basis");
/*  288 */     jRBRAD.setBounds(new Rectangle(40, 122, 185, 33));
/*  289 */     jRBRAD.addActionListener(new svm_frame_jRBRAD_actionAdapter(this));
/*  290 */     jRBSIG.setBackground(new Color(210, 220, 255));
/*  291 */     jRBSIG.setText("Sigmoid");
/*  292 */     jRBSIG.setBounds(new Rectangle(40, 156, 185, 33));
/*  293 */     jRBSIG.addActionListener(new svm_frame_jRBSIG_actionAdapter(this));
/*  294 */     jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  295 */     jLabel11.setBounds(new Rectangle(27, 50, 274, 162));
/*  296 */     jLabel12.setFont(new Font("Arial", 0, 12));
/*  297 */     jLabel12.setForeground(Color.blue);
/*  298 */     jLabel12.setText("Kernel type parameters:");
/*  299 */     jLabel12.setBounds(new Rectangle(340, 27, 242, 24));
/*  300 */     jLDEG.setText("degree: ");
/*  301 */     jLDEG.setBounds(new Rectangle(382, 67, 75, 28));
/*  302 */     jTxtDEG.setFont(new Font("Arial", 0, 12));
/*  303 */     jTxtDEG.setText("3");
/*  304 */     jTxtDEG.setBounds(new Rectangle(490, 70, 57, 21));
/*  305 */     jLGAM.setText("gamma ( 1/ k )");
/*  306 */     jLGAM.setBounds(new Rectangle(382, 112, 103, 28));
/*  307 */     jTxtGAM.setFont(new Font("Arial", 0, 12));
/*  308 */     jTxtGAM.setText("0.5");
/*  309 */     jTxtGAM.setBounds(new Rectangle(490, 116, 57, 21));
/*  310 */     jLCOE.setText("Coef0");
/*  311 */     jLCOE.setBounds(new Rectangle(382, 156, 72, 28));
/*  312 */     jTxtCOE.setFont(new Font("Arial", 0, 12));
/*  313 */     jTxtCOE.setText("0");
/*  314 */     jTxtCOE.setBounds(new Rectangle(490, 162, 57, 21));
/*  315 */     jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  316 */     jLabel13.setText("");
/*  317 */     jLabel13.setBounds(new Rectangle(340, 50, 252, 162));
/*  318 */     jpanel5.setLayout(null);
/*  319 */     jpanel6.setLayout(null);
/*  320 */     jpanel5.addComponentListener(new svm_frame_jpanel5_componentAdapter(this));
/*  321 */     jpanel6.addComponentListener(new svm_frame_jpanel6_componentAdapter(this));
/*  322 */     jTxtCASH.setFont(new Font("Arial", 1, 12));
/*  323 */     jTxtCASH.setText("256");
/*  324 */     jTxtCASH.setBounds(new Rectangle(280, 56, 66, 22));
/*  325 */     jTxtCASH.addActionListener(new svm_frame_jTxtCASH_actionAdapter(this));
/*  326 */     jLabel15.setText("Termination criteria ");
/*  327 */     jLabel15.setBounds(new Rectangle(84, 85, 184, 29));
/*  328 */     jTxtTOL.setBounds(new Rectangle(280, 92, 66, 23));
/*  329 */     jTxtTOL.setFont(new Font("Arial", 1, 12));
/*  330 */     jTxtTOL.setText("0.001");
/*  331 */     jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  332 */     jLabel18.setBounds(new Rectangle(69, 36, 447, 194));
/*  333 */     jBtnRUN.setBounds(new Rectangle(230, 187, 178, 30));
/*  334 */     jBtnRUN.setFont(new Font("Arial", 0, 12));
/*  335 */     jBtnRUN.setForeground(java.awt.SystemColor.activeCaption);
/*  336 */     jBtnRUN.setText("Run");
/*  337 */     jBtnRUN.addActionListener(new svm_frame_jBtnRUN_actionAdapter(this));
/*  338 */     jLabel19.setText("Save log file:");
/*  339 */     jLabel19.setBounds(new Rectangle(83, 62, 99, 29));
/*  340 */     jTxtLOG.setFont(new Font("Arial", 0, 12));
/*  341 */     jTxtLOG.setText("log.txt");
/*  342 */     jTxtLOG.setBounds(new Rectangle(180, 67, 215, 24));
/*  343 */     jBtnLOG.setBounds(new Rectangle(416, 66, 116, 25));
/*  344 */     jBtnLOG.setText("Browse...");
/*  345 */     jBtnLOG.addActionListener(new svm_frame_jBtnLOG_actionAdapter(this));
/*  346 */     jLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  347 */     jLabel20.setText("");
/*  348 */     jLabel20.setBounds(new Rectangle(70, 42, 504, 75));
/*  349 */     jpanel1.setBackground(new Color(210, 220, 255));
/*  350 */     jpanel3.setBackground(new Color(210, 220, 255));
/*  351 */     jpanel4.setBackground(new Color(210, 220, 255));
/*  352 */     jpanel5.setBackground(new Color(210, 220, 255));
/*  353 */     jpanel6.setBackground(new Color(210, 220, 255));
/*  354 */     jChkBxShrink.setBackground(new Color(210, 220, 255));
/*  355 */     jChkBxShrink.setSelected(true);
/*  356 */     jChkBxShrink.setText("  Use Shrinking Heuristics");
/*  357 */     jChkBxShrink.setBounds(new Rectangle(84, 128, 240, 19));
/*  358 */     jChkBxProb.setBackground(new Color(210, 220, 255));
/*  359 */     jChkBxProb.setText("Probability Estimate Computation");
/*  360 */     jChkBxProb.setBounds(new Rectangle(84, 164, 238, 23));
/*  361 */     jTxtWi.setText("-w1 1 -w-1 1");
/*  362 */     jTxtWi.setBounds(new Rectangle(379, 180, 170, 21));
/*  363 */     jLblWi.setText("Wi:");
/*  364 */     jLblWi.setBounds(new Rectangle(325, 176, 37, 24));
/*  365 */     jpanel21.setBackground(new Color(210, 220, 255));
/*  366 */     jpanel21.setLayout(null);
/*  367 */     jpanel22.setBackground(new Color(210, 220, 255));
/*  368 */     jpanel22.setLayout(null);
/*  369 */     jChkP.setBackground(new Color(210, 220, 255));
/*  370 */     jChkP.setSelected(true);
/*  371 */     jChkP.setText("Use Probability Stimate");
/*  372 */     jChkP.setBounds(new Rectangle(60, 230, 186, 23));
/*  373 */     jBtnRun2.setBounds(new Rectangle(209, 174, 226, 30));
/*  374 */     jBtnRun2.setFont(new Font("Arial", 0, 12));
/*  375 */     jBtnRun2.setForeground(java.awt.SystemColor.activeCaption);
/*  376 */     jBtnRun2.setText("Run");
/*  377 */     jBtnRun2.addActionListener(new svm_frame_jBtnRun2_actionAdapter(this));
/*  378 */     jLabel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  379 */     jLabel22.setBounds(new Rectangle(63, 163, 482, 59));
/*  380 */     jTxtModel2.setFont(new Font("Arial", 0, 12));
/*  381 */     jTxtModel2.setBounds(new Rectangle(81, 180, 307, 24));
/*  382 */     jLabel23.setFont(new Font("Arial", 0, 12));
/*  383 */     jLabel23.setForeground(Color.blue);
/*  384 */     jLabel23.setText("Select Model Data File:");
/*  385 */     jLabel23.setBounds(new Rectangle(65, 136, 141, 38));
/*  386 */     jBtnModel2.setBounds(new Rectangle(403, 179, 119, 25));
/*  387 */     jBtnModel2.setText("Browse...");
/*  388 */     jBtnModel2.addActionListener(new svm_frame_jBtnModel2_actionAdapter(this));
/*  389 */     jBtnTest2.setBounds(new Rectangle(403, 61, 119, 25));
/*  390 */     jBtnTest2.setText("Browse...");
/*  391 */     jBtnTest2.addActionListener(new svm_frame_jBtnTest2_actionAdapter(this));
/*  392 */     jLabel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  393 */     jLabel24.setBounds(new Rectangle(63, 43, 482, 79));
/*  394 */     jTxtTest2.setFont(new Font("Arial", 0, 12));
/*  395 */     jTxtTest2.setBounds(new Rectangle(81, 61, 307, 24));
/*  396 */     jLabel25.setFont(new Font("Arial", 0, 12));
/*  397 */     jLabel25.setForeground(Color.blue);
/*  398 */     jLabel25.setText("Select Test Data File:");
/*  399 */     jLabel25.setBounds(new Rectangle(65, 19, 141, 34));
/*  400 */     jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  401 */     jLabel16.setBounds(new Rectangle(81, 66, 482, 59));
/*  402 */     jTxtOutput2.setFont(new Font("Arial", 0, 12));
/*  403 */     jTxtOutput2.setText("output.txt");
/*  404 */     jTxtOutput2.setBounds(new Rectangle(97, 85, 307, 24));
/*  405 */     jBtnOut2.setBounds(new Rectangle(426, 85, 119, 25));
/*  406 */     jBtnOut2.setText("Browse...");
/*  407 */     jBtnOut2.addActionListener(new svm_frame_jBtnOut2_actionAdapter(this));
/*  408 */     jLabel17.setFont(new Font("Arial", 0, 12));
/*  409 */     jLabel17.setForeground(Color.blue);
/*  410 */     jLabel17.setText("Set Output File:");
/*  411 */     jLabel17.setBounds(new Rectangle(82, 42, 141, 34));
/*  412 */     jBN.setBounds(new Rectangle(563, 280, 73, 23));
/*  413 */     jBN.setEnabled(false);
/*  414 */     jBN.setText("Next");
/*  415 */    // jBN.addActionListener(new svm_frame_jBN_actionAdapter(this));
/*  416 */     jButton1.setBounds(new Rectangle(563, 280, 73, 23));
/*  417 */     jButton1.setText("Next");
/*  418 */     jButton1.addActionListener(new svm_frame_jButton1_actionAdapter(this));
/*  419 */     jButton2.setBounds(new Rectangle(563, 280, 73, 23));
/*  420 */     jButton2.setText("Next");
/*  421 */     jButton2.addActionListener(new svm_frame_jButton2_actionAdapter(this));
/*  422 */     jButton3.setBounds(new Rectangle(563, 280, 73, 23));
/*  423 */     jButton3.setText("Next");
/*  424 */     jButton3.addActionListener(new svm_frame_jButton3_actionAdapter(this));
/*  425 */     jButton4.setBounds(new Rectangle(563, 280, 73, 23));
/*  426 */     jButton4.setText("Next");
/*  427 */     jButton4.addActionListener(new svm_frame_jButton4_actionAdapter(this));
/*  428 */     jButton7.setBounds(new Rectangle(19, 278, 73, 23));
/*  429 */     jButton7.setText("Back");
/*  430 */     jButton7.addActionListener(new svm_frame_jButton7_actionAdapter(this));
/*  431 */     jButton8.setBounds(new Rectangle(563, 280, 73, 23));
/*  432 */     jButton8.setText("Next");
/*  433 */     jButton8.addActionListener(new svm_frame_jButton8_actionAdapter(this));
/*  434 */     jButton9.setBounds(new Rectangle(19, 278, 73, 23));
/*  435 */     jButton9.setText("Back");
/*  436 */     jButton9.addActionListener(new svm_frame_jButton9_actionAdapter(this));
/*  437 */     jButton6.setBounds(new Rectangle(19, 278, 73, 23));
/*  438 */     jButton6.setText("Back");
/*  439 */     jButton6.addActionListener(new svm_frame_jButton6_actionAdapter(this));
/*  440 */     jButton10.setBounds(new Rectangle(19, 278, 73, 23));
/*  441 */     jButton10.setText("Back");
/*  442 */     //jButton10.addActionListener(new svm_frame_jButton10_actionAdapter(this));
/*  443 */     jButton11.setBounds(new Rectangle(19, 278, 73, 23));
/*  444 */     jButton11.setText("Back");
/*  445 */     jButton11.addActionListener(new svm_frame_jButton11_actionAdapter(this));
/*  446 */     jButton12.setBounds(new Rectangle(19, 280, 73, 21));
/*  447 */     jButton12.setText("Back");
/*  448 */     jButton12.addActionListener(new svm_frame_jButton12_actionAdapter(this));
/*  449 */     jButton13.setBounds(new Rectangle(19, 278, 73, 23));
/*  450 */     jButton13.setText("Back");
/*  451 */     jButton13.addActionListener(new svm_frame_jButton13_actionAdapter(this));
/*  452 */     jTextArea1.setFont(new Font("Arial", 0, 12));
/*  453 */     jTextArea1.setEditable(false);
/*  454 */     progressbar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  455 */     progressbar.setBounds(new Rectangle(46, 395, 652, 17));
/*  456 */     jLabel26.setText("Cache size (MB)");
/*  457 */     jLabel26.setBounds(new Rectangle(84, 51, 154, 29));
/*  458 */     jLabel14.setText("n = ");
/*  459 */     jLabel14.setEnabled(false);
/*  460 */     jLabel14.setFont(new Font("Arial", 0, 11));
/*  461 */     jLabel14.setBounds(new Rectangle(347, 126, 43, 28));
/*  462 */     jLabel21.setFont(new Font("Arial", 0, 11));
/*  463 */     jLabel21.setText("n-fold Cross-Validation mode:");
/*  464 */     jLabel21.setBounds(new Rectangle(124, 127, 215, 29));
/*  465 */     jTxtCross.setEnabled(false);
/*  466 */     jTxtCross.setFont(new Font("Arial", 1, 12));
/*  467 */     jTxtCross.setBounds(new Rectangle(376, 131, 66, 22));
/*  468 */     jChBoxCross.setBackground(new Color(210, 220, 255));
/*  469 */     jChBoxCross.setFont(new Font("Arial", 0, 11));
/*  470 */     jChBoxCross.setBounds(new Rectangle(101, 131, 21, 20));
/*  471 */     jChBoxCross.addActionListener(new svm_frame_jChBoxCross_actionAdapter(this));
/*  472 */     jLabel27.setFont(new Font("Arial", 0, 11));
/*  473 */     jLabel27.setText("( Cross validation for model selection )");
/*  474 */     jLabel27.setBounds(new Rectangle(106, 146, 305, 26));
/*  475 */     jMenuHomePage.setText("SVM Classifier Home Page");
/*  476 */     jMenuHomePage.addActionListener(new svm_frame_jMenuHomePage_actionAdapter(this));
/*  477 */     jLabel28.setFont(new Font("Arial", 0, 12));
/*  478 */     jLabel28.setForeground(Color.blue);
/*  479 */     jLabel28.setText("Select Dataset file:");
/*  480 */     jLabel28.setBounds(new Rectangle(81, 8, 127, 34));
/*  481 */     jRBLabeled.setBackground(new Color(210, 220, 255));
/*  482 */     jRBLabeled.setFont(new Font("Arial", 0, 11));
/*  483 */     jRBLabeled.setSelected(true);
/*  484 */     jRBLabeled.setText("Labeled");
/*  485 */     jRBLabeled.setBounds(new Rectangle(168, 70, 98, 23));
/*  486 */     jRBDelim.setBackground(new Color(210, 220, 255));
/*  487 */     jRBDelim.setFont(new Font("Arial", 0, 11));
/*  488 */     jRBDelim.setText("Delimited (Generate Labeled file)");
/*  489 */     jRBDelim.setBounds(new Rectangle(262, 70, 283, 23));
/*  490 */     jRBDelim.addActionListener(new svm_frame_jRBDelim_actionAdapter(this));
/*  491 */     jLabel29.setBorder(javax.swing.BorderFactory.createEtchedBorder());
/*  492 */     jLabel29.setBounds(new Rectangle(81, 205, 482, 50));
/*  493 */     jLabel30.setFont(new Font("Arial", 0, 12));
/*  494 */     jLabel30.setForeground(Color.blue);
/*  495 */     jLabel30.setText("Cross-Validation mode:");
/*  496 */     jLabel30.setBounds(new Rectangle(81, 103, 235, 29));
/*  497 */     jRBDelim2.setBackground(new Color(210, 220, 255));
/*  498 */     jRBDelim2.setFont(new Font("Arial", 0, 11));
/*  499 */     jRBDelim2.setText("Delimited (Generate Labeled file)");
/*  500 */     jRBDelim2.setBounds(new Rectangle(245, 87, 274, 23));
/*  501 */     jRBLabeled2.setBackground(new Color(210, 220, 255));
/*  502 */     jRBLabeled2.setFont(new Font("Arial", 0, 11));
/*  503 */     jRBLabeled2.setSelected(true);
/*  504 */     jRBLabeled2.setText("Labeled");
/*  505 */     jRBLabeled2.setBounds(new Rectangle(149, 87, 98, 23));
/*  506 */     jLabel31.setFont(new Font("Arial", 0, 12));
/*  507 */     jLabel31.setText("File type:");
/*  508 */     jLabel31.setBounds(new Rectangle(80, 84, 74, 26));
/*  509 */     jMenuViewGraphic.add(jMenuViewGraphic2d);
/*  510 */     jMenuFile.add(jMenuFileClass);
/*  511 */     jMenuFile.add(jMenuFilePred);
/*  512 */     jMenuFile.addSeparator();
/*  513 */     jMenuFile.add(jMenuFileExit);
/*  514 */     jMenuView.add(jMenuViewGraphic);
/*  515 */     jMenuHelp.add(jMenuHomePage);
/*  516 */     jMenuHelp.add(jMenuHelpAbout);
/*  517 */     jMenuBar1.add(jMenuFile);
/*  518 */     jMenuBar1.add(jMenuView);
/*  519 */     jMenuBar1.add(jMenuHelp);
/*  520 */     setJMenuBar(jMenuBar1);
/*  521 */     contentPane.add(statusBar, null);
/*  522 */     contentPane.add(jScrollPane1, null);
/*  523 */     contentPane.add(jTabbedPane1, null);
/*  524 */     contentPane.add(progressbar);
/*  525 */     jpanel1.add(jlabel1, null);
/*  526 */     jpanel1.add(jRadioButtonClassif, null);
/*  527 */     jpanel1.add(jRadioButtonPredict, null);
/*  528 */     jpanel1.add(jLabel9, null);
/*  529 */     jpanel1.add(jBN);
/*  530 */     jTabbedPane1.add(jpanel1, " Start ");
/*  531 */     jpanel3.add(jLabel5, null);
/*  532 */     jpanel4.add(jLabel10, null);
/*  533 */     jpanel4.add(jRBLIN, null);
/*  534 */     jScrollPane1.getViewport().add(jTextArea1, null);
/*  535 */     jTabbedPane1.setSelectedComponent(jpanel1);
/*  536 */     javax.swing.ButtonGroup bg1 = new javax.swing.ButtonGroup();
/*  537 */     bg1.add(jRadioButtonClassif);
/*  538 */     bg1.add(jRadioButtonPredict);
/*  539 */     jpanel2.add(jButton1);
/*  540 */     jpanel2.add(jButton10);
/*  541 */     jpanel2.add(jTxtDataset, null);
/*  542 */     jpanel2.add(jLabel1, null);
/*  543 */     jpanel2.add(jBtnBrowseDataset, null);
/*  544 */     jpanel2.add(jTxtModel, null);
/*  545 */     jpanel2.add(jBtnBrowseModel, null);
/*  546 */     jpanel2.add(jLabel28);
/*  547 */     jpanel2.add(jRBLabeled);
/*  548 */     jpanel2.add(jRBDelim);
/*  549 */     jpanel2.add(jLabel29);
/*  550 */     jpanel2.add(jLabel3, null);
/*  551 */     jpanel2.add(jLabel21);
/*  552 */     jpanel2.add(jLabel30);
/*  553 */     jpanel2.add(jLabel4, null);
/*  554 */     jpanel2.add(jChBoxCross);
/*  555 */     jpanel2.add(jLabel27);
/*  556 */     jpanel2.add(jTxtCross);
/*  557 */     jpanel2.add(jLabel14);
/*  558 */     jpanel2.add(jLabel2, null);
/*  559 */     jpanel3.add(jRBtnONECLASS, null);
/*  560 */     jpanel3.add(jRBtnCSVC, null);
/*  561 */     jpanel3.add(jRBtnEPS, null);
/*  562 */     jpanel3.add(jRBtnNUSVC, null);
/*  563 */     jpanel3.add(jRBtnNUSVR, null);
/*  564 */     jpanel3.add(jLabel6, null);
/*  565 */     jRBtnCSVC.setSelected(true);
/*  566 */     javax.swing.ButtonGroup bg2 = new javax.swing.ButtonGroup();
/*  567 */     bg2.add(jRBtnCSVC);
/*  568 */     bg2.add(jRBtnNUSVC);
/*  569 */     bg2.add(jRBtnONECLASS);
/*  570 */     bg2.add(jRBtnEPS);
/*  571 */     bg2.add(jRBtnNUSVR);
/*  572 */     jpanel3.add(jLblCost, null);
/*  573 */     jpanel3.add(jLblNU, null);
/*  574 */     jpanel3.add(jLblEPS, null);
/*  575 */     jpanel3.add(jLblWi);
/*  576 */     jpanel3.add(jLabel8, null);
/*  577 */     jpanel3.add(jButton2);
/*  578 */     jpanel3.add(jButton11);
/*  579 */     jpanel3.add(jTxtCost, null);
/*  580 */     jpanel3.add(jTxtWi);
/*  581 */     jpanel3.add(jTxtEPS, null);
/*  582 */     jpanel3.add(jTxtNU, null);
/*  583 */     jpanel3.add(jLabel7, null);
/*  584 */     jpanel4.add(jRBPOL, null);
/*  585 */     jpanel4.add(jRBRAD, null);
/*  586 */     jpanel4.add(jRBSIG, null);
/*  587 */     jpanel4.add(jLabel11, null);
/*  588 */     jpanel4.add(jLabel12, null);
/*  589 */     jRBRAD.setSelected(true);
/*  590 */     javax.swing.ButtonGroup bg3 = new javax.swing.ButtonGroup();
/*  591 */     bg3.add(jRBLIN);
/*  592 */     bg3.add(jRBPOL);
/*  593 */     bg3.add(jRBRAD);
/*  594 */     bg3.add(jRBSIG);
/*  595 */     jpanel4.add(jLDEG, null);
/*  596 */     jpanel4.add(jLGAM, null);
/*  597 */     jpanel4.add(jLCOE, null);
/*  598 */     jpanel4.add(jLabel13, null);
/*  599 */     jpanel4.add(jButton3);
/*  600 */     jpanel4.add(jButton12);
/*  601 */     jpanel4.add(jTxtGAM, null);
/*  602 */     jpanel4.add(jTxtDEG, null);
/*  603 */     jpanel4.add(jTxtCOE, null);
/*  604 */     jpanel6.add(jTxtLOG, null);
/*  605 */     jpanel6.add(jBtnLOG, null);
/*  606 */     jpanel6.add(jBtnRUN, null);
/*  607 */     jpanel6.add(jLabel20, null);
/*  608 */     jpanel6.add(jButton6);
/*  609 */     jpanel6.add(jLabel19, null);
/*  610 */     jpanel5.add(jLabel15, null);
/*  611 */     jpanel5.add(jChkBxShrink);
/*  612 */     jpanel5.add(jLabel26);
/*  613 */     jpanel5.add(jButton4);
/*  614 */     jpanel5.add(jButton13);
/*  615 */     jpanel5.add(jTxtTOL, null);
/*  616 */     jpanel5.add(jTxtCASH, null);
/*  617 */     jpanel5.add(jChkBxProb);
/*  618 */     jpanel5.add(jLabel18, null);
/*  619 */     jpanel21.add(jBtnTest2);
/*  620 */     jpanel21.add(jTxtTest2);
/*  621 */     jpanel21.add(jLabel25);
/*  622 */     jpanel21.add(jButton8);
/*  623 */     jpanel21.add(jButton7);
/*  624 */     jpanel21.add(jTxtModel2);
/*  625 */     jpanel21.add(jLabel23);
/*  626 */     jpanel21.add(jLabel22);
/*  627 */     jpanel21.add(jBtnModel2);
/*  628 */     jpanel21.add(jChkP);
/*  629 */     jpanel21.add(jRBDelim2);
/*  630 */     jpanel21.add(jLabel31);
/*  631 */     jpanel21.add(jRBLabeled2);
/*  632 */     jpanel21.add(jLabel24);
/*  633 */     jpanel22.add(jBtnRun2);
/*  634 */     jpanel22.add(jTxtOutput2);
/*  635 */     jpanel22.add(jBtnOut2);
/*  636 */     jpanel22.add(jLabel16);
/*  637 */     jpanel22.add(jLabel17);
/*  638 */     jpanel22.add(jButton9);
/*  639 */     jTxtDEG.setEnabled(false);
/*  640 */     jLDEG.setEnabled(false);
/*  641 */     jTxtCOE.setEnabled(false);
/*  642 */     jLCOE.setEnabled(false);
/*  643 */     jpanel21.addComponentListener(new svm_frame_jpanel21_componentAdapter(this));
/*  644 */     jpanel22.addComponentListener(new svm_frame_jpanel22_componentAdapter(this));
/*  645 */     javax.swing.ButtonGroup bgfileformat = new javax.swing.ButtonGroup();
/*  646 */     bgfileformat.add(jRBLabeled);
/*  647 */     bgfileformat.add(jRBDelim);
/*  648 */     javax.swing.ButtonGroup bgfileformat2 = new javax.swing.ButtonGroup();
/*  649 */     bgfileformat2.add(jRBLabeled2);
/*  650 */     bgfileformat2.add(jRBDelim2);

/*  188 */     end.setBackground(new Color(210, 220, 255));
			   end.setLayout(new BorderLayout());

				topPanel=new TopPanel();
				topPanel.setBackground(new Color(210, 220, 255));
				//topPanel.setBorder(arg0);
				middlePanel=new MiddlePanel();
				middlePanel.setBackground(new Color(210, 220, 255));
				bottomPanel=new BottomPanel();
				bottomPanel.setBackground(new Color(210, 220, 255));
				//	add(topPanel,BorderLayout.NORTH);
				//add(middlePanel,BorderLayout.CENTER);
				//add(bottomPanel,BorderLayout.SOUTH);

				Box vertical1 = Box.createVerticalBox();
				
				vertical1.add( Box.createVerticalStrut( 15 ) );
				//vertical1.a
				JLabel l=new JLabel("<html><font color='blue' style='verdana'>Select Dataset File</font></html>");
				l.setFont(new Font("Arial", 0, 12));				     
				l.setPreferredSize(new Dimension(200,10));
				l.setAlignmentX(Component.RIGHT_ALIGNMENT);
				
				vertical1.add(l);
				vertical1.add( Box.createVerticalStrut( 2) );
				vertical1.add(topPanel);
				vertical1.add( Box.createVerticalStrut( 15) );
				
				l=new JLabel("<html><font color='blue' style='verdana'>Feature Selection</font></html>");
				l.setFont(new Font("Arial", 0, 12));				     
				l.setPreferredSize(new Dimension(200,10));
				l.setAlignmentX(Component.RIGHT_ALIGNMENT);
				
				vertical1.add(l);
				vertical1.add( Box.createVerticalStrut( 2) );
				vertical1.add(middlePanel);
				vertical1.add( Box.createVerticalStrut( 20) );
				
				l=new JLabel("<html><font color='blue' style='verdana'>Save Feature Set</font></html>");
				l.setFont(new Font("Arial", 0, 12));				     
				l.setPreferredSize(new Dimension(200,10));
				l.setAlignmentX(Component.RIGHT_ALIGNMENT);
				
				vertical1.add(l);
				vertical1.add( Box.createVerticalStrut( 2) );				
				vertical1.add(bottomPanel);
				
				
				JPanel bttomPP=new JPanel();
				bttomPP.setBorder(new EmptyBorder( 10, 10, 10, 10 ));
				bttomPP.setLayout(new BorderLayout());
				
				JButton featPre=new JButton("    Back   ");
				//featPre.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
				JButton featNext=new JButton("    Next   ");
				//featNext.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
				jBN.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						jTabbedPane1.setSelectedComponent(end);
					}
				});
				
				featPre.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						jTabbedPane1.setSelectedComponent(jpanel1);
					}
				});
				
				featNext.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						jTabbedPane1.setSelectedComponent(jpanel2);
					}
				});				
				
				
				jButton10.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						jTabbedPane1.setSelectedComponent(end);
					}
				});
				
				
				bttomPP.add(featPre,BorderLayout.WEST);
				bttomPP.add(featNext,BorderLayout.EAST);
				
				bttomPP.setBackground(new Color(210, 220, 255));
				end.add(new JLabel("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<br/></html>"),BorderLayout.NORTH);
				
				end.add(vertical1,BorderLayout.CENTER);
				end.add(new JLabel("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</html>"),BorderLayout.EAST);
				end.add(new JLabel("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</html>"),BorderLayout.WEST);
				
				end.add(bttomPP,BorderLayout.SOUTH);
				
				
				System.out.println("end panel created");

/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  664 */     java.io.PrintStream consoleStream = new java.io.PrintStream(new java.io.OutputStream()
/*      */     {
/*      */       public void write(int b) {}
/*      */       
/*      */       public void write(byte[] b, int off, int len) {
/*  669 */         jTextArea1.append(new String(b, off, len));
/*      */       }
/*  671 */     });
/*  672 */     System.setOut(consoleStream);
/*  673 */     System.setErr(consoleStream);
/*      */   }
/*      */   
/*      */ 
/*      */   public void jMenuFileExit_actionPerformed(ActionEvent e)
/*      */   {
/*  679 */     int exit = javax.swing.JOptionPane.showConfirmDialog(this, "Exit SVM Classifier?", "Exit SVM Classifier", 2, 2);
/*      */     
/*  681 */     if (exit == 0) {
/*  682 */       System.exit(0);
/*      */     }
/*      */   }
/*      */   
/*      */   public void jMenuFileClass_actionPerformed(ActionEvent e)
/*      */   {
			   System.out.println("hi there");
/*  688 */     jRadioButtonClassif.setSelected(true);
/*  689 */     jBN.setEnabled(true);
/*  690 */     f = 1;
/*  691 */     jTabbedPane1.remove(jpanel21);
/*  692 */     jTabbedPane1.remove(jpanel22);
			   jTabbedPane1.add(end, "Feature Selection");
/*  693 */     jTabbedPane1.add(jpanel2, "Dataset and model Selection");
/*  694 */     jTabbedPane1.add(jpanel3, "SVM type");
/*  695 */     jTabbedPane1.add(jpanel4, "Kernel type");
/*  696 */     jTabbedPane1.add(jpanel5, "Set Variables");
/*  697 */     jTabbedPane1.add(jpanel6, " Run ");
/*  698 */     jTabbedPane1.setSelectedComponent(jpanel1);
/*  699 */     jTextArea1.setText("");
/*      */   }
/*      */   
/*      */   public void jMenuFilePred_actionPerformed(ActionEvent e)
/*      */   {
/*  704 */     jRadioButtonPredict.setSelected(true);
/*  705 */     jBN.setEnabled(true);
/*  706 */     f = 2;
			   jTabbedPane1.remove(end);
/*  707 */     jTabbedPane1.remove(jpanel2);
/*  708 */     jTabbedPane1.remove(jpanel3);
/*  709 */     jTabbedPane1.remove(jpanel4);
/*  710 */     jTabbedPane1.remove(jpanel5);
/*  711 */     jTabbedPane1.remove(jpanel6);
/*  712 */     jTabbedPane1.add(jpanel21, "Test and Model Selection");
/*  713 */     jTabbedPane1.add(jpanel22, "Run");
/*  714 */     jTabbedPane1.setSelectedComponent(jpanel1);
/*  715 */     jTextArea1.setText("");
/*      */   }
/*      */   
/*      */ 
/*      */   public void jMenuViewGraphic2d_actionPerformed(ActionEvent e)
/*      */   {
/*  721 */     AppletFrame appFrame = new AppletFrame("svm_2D", new svm_applet(), 700, 450);
/*      */     
/*  723 */     java.awt.Dimension appScreenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
/*  724 */     java.awt.Dimension appFrameSize = appFrame.getSize();
/*  725 */     
/*      */ 
/*  734 */     appFrame.setVisible(true);
/*  735 */     appFrame.show();
/*      */   }
/*      */   
/*      */ 
/*      */   public void jMenuHelpAbout_actionPerformed(ActionEvent e)
/*      */   {
/*  741 */     svm_frame_AboutBox dlg = new svm_frame_AboutBox(this);
/*  742 */     java.awt.Dimension dlgSize = dlg.getPreferredSize();
/*  743 */     java.awt.Dimension frmSize = getSize();
/*  744 */     java.awt.Point loc = getLocation();
/*  745 */    

			


/*      */     
/*  747 */     dlg.setModal(true);
/*  748 */     dlg.pack();
/*  749 */     dlg.show();
/*      */   }
/*      */   
/*      */   class svm_frame_jMenuViewGraphic2d_ActionAdapter implements java.awt.event.ActionListener
/*      */   {
/*      */     svm_frame adaptee;
/*      */     
/*      */     svm_frame_jMenuViewGraphic2d_ActionAdapter(svm_frame adaptee) {
/*  757 */       this.adaptee = adaptee;
/*      */     }
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/*  761 */       adaptee.jMenuViewGraphic2d_actionPerformed(e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void processWindowEvent(java.awt.event.WindowEvent e)
/*      */   {
/*  768 */     if (e.getID() == 201)
/*      */     {
/*  770 */       int exit = javax.swing.JOptionPane.showConfirmDialog(this, "Exit SVM Classifier?", "Exit SVM Classifier", 2, 2);
/*      */       
/*  772 */       if (exit == 0) {
/*  773 */         System.exit(0);
/*      */       }
/*      */     }
/*      */     else {
/*  777 */       super.processWindowEvent(e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void jButtonNext_actionPerformed(ActionEvent e)
/*      */   {
/*  789 */     switch (nextflag) {
/*      */     case 1: 
/*  791 */       jTabbedPane1.setSelectedComponent(jpanel2);
/*  792 */       nextflag = 2;
/*  793 */       backflag = 2;
/*  794 */       break;
/*      */     
/*      */     case 2: 
/*  797 */       jTabbedPane1.setSelectedComponent(jpanel3);
/*  798 */       nextflag = 3;
/*  799 */       backflag = 3;
/*  800 */       break;
/*      */     
/*      */     case 3: 
/*  803 */       jTabbedPane1.setSelectedComponent(jpanel4);
/*  804 */       nextflag = 4;
/*  805 */       backflag = 4;
/*  806 */       break;
/*      */     
/*      */     case 4: 
/*  809 */       jTabbedPane1.setSelectedComponent(jpanel5);
/*  810 */       nextflag = 5;
/*  811 */       backflag = 5;
/*  812 */       break;
/*      */     
/*      */     case 5: 
/*  815 */       jTabbedPane1.setSelectedComponent(jpanel6);
/*  816 */       nextflag = 6;
/*  817 */       backflag = 6;
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */   void jButtonBack_actionPerformed(ActionEvent e)
/*      */   {
/*  824 */     switch (backflag) {
/*      */     case 2: 
/*  826 */       jTabbedPane1.setSelectedComponent(jpanel1);
/*  827 */       nextflag = 1;
/*  828 */       backflag = 1;
/*  829 */       break;
/*      */     
/*      */     case 3: 
/*  832 */       jTabbedPane1.setSelectedComponent(jpanel2);
/*  833 */       nextflag = 2;
/*  834 */       backflag = 2;
/*  835 */       break;
/*      */     
/*      */     case 4: 
/*  838 */       jTabbedPane1.setSelectedComponent(jpanel3);
/*  839 */       nextflag = 3;
/*  840 */       backflag = 3;
/*  841 */       break;
/*      */     
/*      */     case 5: 
/*  844 */       jTabbedPane1.setSelectedComponent(jpanel4);
/*  845 */       nextflag = 4;
/*  846 */       backflag = 4;
/*  847 */       break;
/*      */     
/*      */     case 6: 
/*  850 */       jTabbedPane1.setSelectedComponent(jpanel5);
/*  851 */       nextflag = 5;
/*  852 */       backflag = 5;
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */   void jpanel1_componentShown(java.awt.event.ComponentEvent e)
/*      */   {
/*  859 */     nextflag = 1;
/*  860 */     backflag = 1;
/*      */   }
/*      */   
/*      */   void jpanel2_componentShown(java.awt.event.ComponentEvent e) {
/*  864 */     nextflag = 2;
/*  865 */     backflag = 2;
/*      */   }
/*      */   
/*      */   void jpanel3_componentShown(java.awt.event.ComponentEvent e) {
/*  869 */     nextflag = 3;
/*  870 */     backflag = 3;
/*      */   }
/*      */   
/*      */   void jpanel4_componentShown(java.awt.event.ComponentEvent e) {
/*  874 */     nextflag = 4;
/*  875 */     backflag = 4;
/*      */   }
/*      */   
/*      */   void jpanel5_componentShown(java.awt.event.ComponentEvent e) {
/*  879 */     nextflag = 5;
/*  880 */     backflag = 5;
/*      */   }
/*      */   
/*      */   void jpanel6_componentShown(java.awt.event.ComponentEvent e) {
/*  884 */     nextflag = 6;
/*  885 */     backflag = 6;
/*      */   }
/*      */   
/*      */   void jRadioButtonClassif_actionPerformed(ActionEvent e) {
/*  889 */     progressbar.setVisible(false);
/*  890 */     jBN.setEnabled(true);
/*  891 */     f = 1;
/*  892 */     jTabbedPane1.remove(jpanel21);
/*  893 */     jTabbedPane1.remove(jpanel22);
			   jTabbedPane1.add(end, "Feature Selection");
/*  894 */     jTabbedPane1.add(jpanel2, "Dataset and model Selection");
/*  895 */     jTabbedPane1.add(jpanel3, "SVM type");
/*  896 */     jTabbedPane1.add(jpanel4, "Kernel type");
/*  897 */     jTabbedPane1.add(jpanel5, "Set Variables");
/*  898 */     jTabbedPane1.add(jpanel6, " Run ");
/*  899 */     jTabbedPane1.setSelectedComponent(jpanel1);
/*  900 */     jTextArea1.setText("");
/*      */   }
/*      */   
/*      */   void jRadioButtonPredict_actionPerformed(ActionEvent e) {
/*  904 */     progressbar.setVisible(false);
/*  905 */     jBN.setEnabled(true);
/*  906 */     f = 2;
			   jTabbedPane1.remove(end);
/*  907 */     jTabbedPane1.remove(jpanel2);
/*  908 */     jTabbedPane1.remove(jpanel3);
/*  909 */     jTabbedPane1.remove(jpanel4);
/*  910 */     jTabbedPane1.remove(jpanel5);
/*  911 */     jTabbedPane1.remove(jpanel6);
/*  912 */     jTabbedPane1.add(jpanel21, "Test and Model Selection");
/*  913 */     jTabbedPane1.add(jpanel22, "Run");
/*  914 */     jTabbedPane1.setSelectedComponent(jpanel1);
/*  915 */     jTextArea1.setText("");
/*      */   }
/*      */   
/*      */   void jBtnNext0_actionPerformed(ActionEvent e)
/*      */   {
/*  920 */     nextflag = 2;
/*  921 */     backflag = 2;
/*  922 */     jTabbedPane1.setSelectedComponent(jpanel2);
/*      */   }
/*      */   
/*      */   void jBtnBrowseDataset_actionPerformed(ActionEvent e) {
/*  926 */     FileDialog dialog = new FileDialog(new java.awt.Frame(), "Load Dataset", 0);
/*      */     
/*  928 */     dialog.setVisible(true);
/*  929 */     String filename = dialog.getDirectory() + dialog.getFile();
/*  930 */     String str = jTxtDataset.getText();
/*  931 */     jTxtDataset.setText(filename);
/*  932 */     if (jTxtDataset.getText().equalsIgnoreCase("nullnull")) {
/*  933 */       jTxtDataset.setText(str);
/*      */     }
/*      */   }
/*      */   
/*      */   void jBtnBrowseModel_actionPerformed(ActionEvent e) {
/*  938 */     FileDialog dialog = new FileDialog(new java.awt.Frame(), "Save Model", 1);
/*      */     
/*  940 */     dialog.setFile("model.svm");
/*  941 */     dialog.setVisible(true);
/*  942 */     String filename = dialog.getDirectory() + dialog.getFile();
/*  943 */     String str = jTxtModel.getText();
/*  944 */     jTxtModel.setText(filename);
/*  945 */     if (jTxtModel.getText().equalsIgnoreCase("nullnull")) {
/*  946 */       jTxtModel.setText(str);
/*      */     }
/*      */   }
/*      */   
/*      */   void jRBtnCSVC_actionPerformed(ActionEvent e) {
/*  951 */     jTxtCost.setEnabled(true);
/*  952 */     jLblCost.setEnabled(true);
/*  953 */     jTxtWi.setEnabled(true);
/*  954 */     jLblWi.setEnabled(true);
/*  955 */     jTxtNU.setEnabled(false);
/*  956 */     jLblNU.setEnabled(false);
/*  957 */     jTxtEPS.setEnabled(false);
/*  958 */     jLblEPS.setEnabled(false);
/*  959 */     svm_type = "0";
/*      */   }
/*      */   
/*      */   void jRBtnNUSVR_actionPerformed(ActionEvent e) {
/*  963 */     jTxtCost.setEnabled(true);
/*  964 */     jLblCost.setEnabled(true);
/*  965 */     jTxtWi.setEnabled(true);
/*  966 */     jLblWi.setEnabled(true);
/*  967 */     jTxtNU.setEnabled(true);
/*  968 */     jLblNU.setEnabled(true);
/*  969 */     jTxtEPS.setEnabled(false);
/*  970 */     jLblEPS.setEnabled(false);
/*  971 */     svm_type = "4";
/*      */   }
/*      */   
/*      */   void jRBtnONECLASS_actionPerformed(ActionEvent e)
/*      */   {
/*  976 */     jTxtCost.setEnabled(false);
/*  977 */     jLblCost.setEnabled(false);
/*  978 */     jTxtWi.setEnabled(false);
/*  979 */     jLblWi.setEnabled(false);
/*  980 */     jTxtNU.setEnabled(true);
/*  981 */     jLblNU.setEnabled(true);
/*  982 */     jTxtEPS.setEnabled(false);
/*  983 */     jLblEPS.setEnabled(false);
/*  984 */     svm_type = "2";
/*      */   }
/*      */   
/*      */   void jRBtnEPS_actionPerformed(ActionEvent e) {
/*  988 */     jTxtCost.setEnabled(true);
/*  989 */     jLblCost.setEnabled(true);
/*  990 */     jTxtWi.setEnabled(true);
/*  991 */     jLblWi.setEnabled(true);
/*  992 */     jTxtNU.setEnabled(false);
/*  993 */     jLblNU.setEnabled(false);
/*  994 */     jTxtEPS.setEnabled(true);
/*  995 */     jLblEPS.setEnabled(true);
/*  996 */     svm_type = "3";
/*      */   }
/*      */   
/*      */   void jRBtnNUSVC_actionPerformed(ActionEvent e) {
/* 1000 */     jTxtCost.setEnabled(false);
/* 1001 */     jLblCost.setEnabled(false);
/* 1002 */     jTxtWi.setEnabled(false);
/* 1003 */     jLblWi.setEnabled(false);
/* 1004 */     jTxtNU.setEnabled(true);
/* 1005 */     jLblNU.setEnabled(true);
/* 1006 */     jTxtEPS.setEnabled(false);
/* 1007 */     jLblEPS.setEnabled(false);
/* 1008 */     svm_type = "1";
/*      */   }
/*      */   
/*      */   void jRBLIN_actionPerformed(ActionEvent e) {
/* 1012 */     jTxtDEG.setEnabled(false);
/* 1013 */     jLDEG.setEnabled(false);
/* 1014 */     jTxtGAM.setEnabled(false);
/* 1015 */     jLGAM.setEnabled(false);
/* 1016 */     jTxtCOE.setEnabled(false);
/* 1017 */     jLCOE.setEnabled(false);
/* 1018 */     kernel_type = "0";
/*      */   }
/*      */   
/*      */   void jRBPOL_actionPerformed(ActionEvent e) {
/* 1022 */     jTxtDEG.setEnabled(true);
/* 1023 */     jLDEG.setEnabled(true);
/* 1024 */     jTxtGAM.setEnabled(true);
/* 1025 */     jLGAM.setEnabled(true);
/* 1026 */     jTxtCOE.setEnabled(true);
/* 1027 */     jLCOE.setEnabled(true);
/* 1028 */     kernel_type = "1";
/*      */   }
/*      */   
/*      */   void jRBRAD_actionPerformed(ActionEvent e) {
/* 1032 */     jTxtDEG.setEnabled(false);
/* 1033 */     jLDEG.setEnabled(false);
/* 1034 */     jTxtGAM.setEnabled(true);
/* 1035 */     jLGAM.setEnabled(true);
/* 1036 */     jTxtCOE.setEnabled(false);
/* 1037 */     jLCOE.setEnabled(false);
/* 1038 */     kernel_type = "2";
/*      */   }
/*      */   
/*      */   void jRBSIG_actionPerformed(ActionEvent e) {
/* 1042 */     jTxtDEG.setEnabled(false);
/* 1043 */     jLDEG.setEnabled(false);
/* 1044 */     jTxtGAM.setEnabled(true);
/* 1045 */     jLGAM.setEnabled(true);
/* 1046 */     jTxtCOE.setEnabled(true);
/* 1047 */     jLCOE.setEnabled(true);
/* 1048 */     kernel_type = "3";
/*      */   }
/*      */   
/*      */   void jBtnLOG_actionPerformed(ActionEvent e) {
/* 1052 */     FileDialog dialog = new FileDialog(new java.awt.Frame(), "Save Log", 1);
/* 1053 */     dialog.setFile("log.txt");
/* 1054 */     dialog.setVisible(true);
/* 1055 */     String filename = dialog.getDirectory() + dialog.getFile();
/* 1056 */     String str = jTxtLOG.getText();
/* 1057 */     jTxtLOG.setText(filename);
/* 1058 */     if (jTxtLOG.getText().equalsIgnoreCase("nullnull")) {
/* 1059 */       jTxtLOG.setText(str);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void jBtnRUN_actionPerformed(ActionEvent e)
/*      */     throws java.io.IOException
/*      */   {
/* 1068 */     if (jRBDelim.isSelected()) {
/* 1069 */       Transpose transpose = new Transpose();
/* 1070 */       jTxtDataset.setText(transpose.tranposeFile(jTxtDataset.getText()));
/* 1071 */       jRBLabeled.setSelected(true);
/*      */     }
/*      */     
/*      */ 
/* 1075 */     progressbar.setVisible(true);
/* 1076 */     t = new Thread(this);
/* 1077 */     t.start();
/* 1078 */     jTextArea1.setText("Processing.... \nPlease wait\n");
/*      */   }
/*      */   
/*      */   public void jBtnRun2_actionPerformed(ActionEvent e)
/*      */   {
/* 1083 */     if (jRBDelim2.isSelected()) {
/* 1084 */       Transpose transpose = new Transpose();
/* 1085 */       jTxtTest2.setText(transpose.tranposeFile(jTxtTest2.getText()));
/* 1086 */       jRBLabeled2.setSelected(true);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1091 */     progressbar.setVisible(true);
/* 1092 */     t = new Thread(this);
/* 1093 */     t.start();
/* 1094 */     jTextArea1.setText("Processing.... \nPlease wait\n");
/*      */   }
/*      */   
/*      */ 
/* 1098 */   static String[] stateMessages = { "Processing.... \nPlease wait\n", "Processing.... \nPlease wait\n" };
/*      */   
/*      */ 
/*      */ 
/* 1102 */   JTextField jTxtWi = new JTextField();
/* 1103 */   JLabel jLblWi = new JLabel();
/* 1104 */   JPanel jpanel21 = new JPanel();
/* 1105 */   JPanel jpanel22 = new JPanel();
/* 1106 */   JCheckBox jChkP = new JCheckBox();
/* 1107 */   JButton jBtnRun2 = new JButton();
/* 1108 */   JLabel jLabel22 = new JLabel();
/* 1109 */   JTextField jTxtModel2 = new JTextField();
/* 1110 */   JLabel jLabel23 = new JLabel();
/* 1111 */   JButton jBtnModel2 = new JButton();
/* 1112 */   JButton jBtnTest2 = new JButton();
/* 1113 */   JLabel jLabel24 = new JLabel();
/* 1114 */   JTextField jTxtTest2 = new JTextField();
/* 1115 */   JLabel jLabel25 = new JLabel();
/* 1116 */   JLabel jLabel16 = new JLabel();
/* 1117 */   JTextField jTxtOutput2 = new JTextField();
/* 1118 */   JButton jBtnOut2 = new JButton();
/* 1119 */   JLabel jLabel17 = new JLabel();
/* 1120 */   JButton jBN = new JButton();
/* 1121 */   JButton jButton1 = new JButton();
/* 1122 */   JButton jButton2 = new JButton();
/* 1123 */   JButton jButton3 = new JButton();
/* 1124 */   JButton jButton4 = new JButton();
/* 1125 */   JButton jButton7 = new JButton();
/* 1126 */   JButton jButton8 = new JButton();
/* 1127 */   JButton jButton9 = new JButton();
/* 1128 */   JButton jButton6 = new JButton();
/* 1129 */   JButton jButton10 = new JButton();
/* 1130 */   JButton jButton11 = new JButton();
/* 1131 */   JButton jButton12 = new JButton();
/* 1132 */   JButton jButton13 = new JButton();
/* 1133 */   JLabel progressbar = new JLabel();
/* 1134 */   JLabel jLabel26 = new JLabel();
/* 1135 */   JLabel jLabel14 = new JLabel();
/* 1136 */   JLabel jLabel21 = new JLabel();
/* 1137 */   JTextField jTxtCross = new JTextField();
/* 1138 */   JCheckBox jChBoxCross = new JCheckBox();
/* 1139 */   JLabel jLabel27 = new JLabel();
/* 1140 */   JMenuItem jMenuHomePage = new JMenuItem();
/* 1141 */   JLabel jLabel28 = new JLabel();
/* 1142 */   JRadioButton jRBLabeled = new JRadioButton();
/* 1143 */   JRadioButton jRBDelim = new JRadioButton();
/* 1144 */   JLabel jLabel29 = new JLabel();
/* 1145 */   JLabel jLabel30 = new JLabel();
/* 1146 */   JRadioButton jRBDelim2 = new JRadioButton();
/* 1147 */   JRadioButton jRBLabeled2 = new JRadioButton();
/* 1148 */   JLabel jLabel31 = new JLabel();
/*      */   
/* 1150 */   public void run() { if (jRadioButtonPredict.isSelected())
/*      */     {
/* 1152 */       for (int i = 0; i < stateMessages.length; i++) {
/* 1153 */         jTextArea1.setText(stateMessages[i]);
/*      */         try {
/* 1155 */           Thread.sleep(2000L);
/*      */         }
/*      */         catch (InterruptedException ie) {}
/* 1158 */         if (Thread.currentThread().isInterrupted()) {
/* 1159 */           return;
/*      */         }
/*      */       }
/*      */       
/* 1163 */       jTextArea1.setText("Processing.... \nPlease wait\n");
/* 1164 */       String b = "0";
/* 1165 */       if (jChkP.isSelected()) {
/* 1166 */         b = "1";
/*      */       }
/*      */       else {
/* 1169 */         b = "0";
/*      */       }
/* 1171 */       String[] argv2 = { "-b", b, jTxtTest2.getText(), jTxtModel2.getText(), jTxtOutput2.getText() };
/*      */       
/*      */ 
/*      */ 
/* 1175 */       svm_predict t2 = new svm_predict();
/*      */       try {
/* 1177 */         t2.run(argv2);
/* 1178 */         jTextArea1.setText("\nDone\n\n" + jTextArea1.getText().substring(28));
/* 1179 */         progressbar.setVisible(false);
/*      */       }
/*      */       catch (java.io.IOException ex) {
/* 1182 */         progressbar.setVisible(false);
/*      */       }
/*      */     }
/*      */     
/* 1186 */     if (jRadioButtonClassif.isSelected())
/*      */     {
/* 1188 */       for (int i = 0; i < stateMessages.length; i++) {
/* 1189 */         jTextArea1.setText(stateMessages[i]);
/*      */         try {
/* 1191 */           Thread.sleep(2000L);
/*      */         }
/*      */         catch (InterruptedException ie) {}
/* 1194 */         if (Thread.currentThread().isInterrupted()) {
/* 1195 */           return;
/*      */         }
/*      */       }
/*      */       
/* 1199 */       String shrink = "1";
/* 1200 */       if (jChkBxShrink.isSelected()) {
/* 1201 */         shrink = "1";
/*      */       }
/*      */       else {
/* 1204 */         shrink = "0";
/*      */       }
/*      */       
/* 1207 */       String probability = "0";
/* 1208 */       if (jChkBxProb.isSelected()) {
/* 1209 */         probability = "1";
/*      */       }
/*      */       else {
/* 1212 */         probability = "0";
/*      */       }
/*      */       
/* 1215 */       String v = jTxtCross.getText();
/* 1216 */       String degree = jTxtDEG.getText();
/* 1217 */       String gamma = jTxtGAM.getText();
/* 1218 */       String coef0 = jTxtCOE.getText();
/* 1219 */       String cost = jTxtCost.getText();
/* 1220 */       String nu = jTxtNU.getText();
/* 1221 */       String epsilon = jTxtEPS.getText();
/* 1222 */       String cache = jTxtCASH.getText();
/* 1223 */       String termination = jTxtTOL.getText();
/* 1224 */       String wi = "\"" + jTxtWi.getText() + "\"";
/*      */       
/*      */ 
/* 1227 */       wi = wi.replaceAll(" ", "\",\"");
/* 1228 */       String[] w = { "-w1", "1", "-w-1", "1" };
/*      */       
/* 1230 */       String[] argv = { "-s", svm_type, "-t", kernel_type, "-d", degree, "-g", gamma, "-r", coef0, "-c", cost, "-n", nu, "-p", epsilon, "-m", cache, "-e", termination, "-h", shrink, "-b", probability, jTxtDataset.getText(), jTxtModel.getText() };
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1235 */       String[] argvCross = { "-s", svm_type, "-t", kernel_type, "-d", degree, "-g", gamma, "-r", coef0, "-c", cost, "-n", nu, "-p", epsilon, "-m", cache, "-e", termination, "-h", shrink, "-b", probability, "-v", v, jTxtDataset.getText() };
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1240 */       svm_train t = new svm_train();
/*      */       
/*      */       try
/*      */       {
/* 1244 */         if (jChBoxCross.isSelected()) {
/* 1245 */           t.test(argvCross);
/*      */         }
/*      */         else {
/* 1248 */           t.test(argv);
/*      */         }
/*      */         
/* 1251 */         save_log(jTxtLOG.getText(), jTextArea1.getText());
/* 1252 */         jTextArea1.setText("\nDone\n\n" + jTextArea1.getText().substring(33));
/* 1253 */         progressbar.setVisible(false);
/*      */       }
/*      */       catch (java.io.IOException ex) {
/* 1256 */         progressbar.setVisible(false);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static void save_log(String logFileName, String str3) throws java.io.IOException
/*      */   {
/* 1263 */     java.io.DataOutputStream fp = new java.io.DataOutputStream(new java.io.FileOutputStream(logFileName));
/* 1264 */     fp.writeBytes(str3);
/* 1265 */     fp.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public void jTxtCASH_actionPerformed(ActionEvent e) {}
/*      */   
/*      */   public void jBtnN2_actionPerformed(ActionEvent e)
/*      */   {
/* 1273 */     jTabbedPane1.setSelectedComponent(jpanel21);
/*      */   }
/*      */   
/*      */   public void jBtnN3_actionPerformed(ActionEvent e) {
/* 1277 */     jTabbedPane1.setSelectedComponent(jpanel22);
/*      */   }
/*      */   
/*      */   public void jBtnB2_actionPerformed(ActionEvent e) {
/* 1281 */     jTabbedPane1.setSelectedComponent(jpanel1);
/*      */   }
/*      */   
/*      */   public void jBtnB3_actionPerformed(ActionEvent e) {
/* 1285 */     jTabbedPane1.setSelectedComponent(jpanel21);
/*      */   }
/*      */   
/*      */ 
/*      */   void jpanel21_componentShown(java.awt.event.ComponentEvent e) {}
/*      */   
/*      */   void jpanel22_componentShown(java.awt.event.ComponentEvent e) {}
/*      */   
/*      */   public void jBtnTest2_actionPerformed(ActionEvent e)
/*      */   {
/* 1295 */     FileDialog dialog = new FileDialog(new java.awt.Frame(), "Load Test Data", 0);
/*      */     
/* 1297 */     dialog.setVisible(true);
/* 1298 */     String filename = dialog.getDirectory() + dialog.getFile();
/* 1299 */     String str = jTxtTest2.getText();
/* 1300 */     jTxtTest2.setText(filename);
/* 1301 */     if (jTxtTest2.getText().equalsIgnoreCase("nullnull")) {
/* 1302 */       jTxtTest2.setText(str);
/*      */     }
/*      */   }
/*      */   
/*      */   public void jBtnModel2_actionPerformed(ActionEvent e)
/*      */   {
/* 1308 */     FileDialog dialog = new FileDialog(new java.awt.Frame(), "Load Model", 0);
/*      */     
/* 1310 */     dialog.setVisible(true);
/* 1311 */     String filename = dialog.getDirectory() + dialog.getFile();
/* 1312 */     String str = jTxtModel2.getText();
/* 1313 */     jTxtModel2.setText(filename);
/* 1314 */     if (jTxtModel2.getText().equalsIgnoreCase("nullnull")) {
/* 1315 */       jTxtModel2.setText(str);
/*      */     }
/*      */   }
/*      */   
/*      */   public void jBN_actionPerformed(ActionEvent e)
/*      */   {
/* 1321 */     if (f == 1) {
/* 1322 */       jTabbedPane1.setSelectedComponent(jpanel2);
/*      */     }
/* 1324 */     if (f == 2) {
/* 1325 */       jTabbedPane1.setSelectedComponent(jpanel21);
/*      */     }
/*      */   }
/*      */   
/*      */   public void jButton1_actionPerformed(ActionEvent e) {
/* 1330 */     jTabbedPane1.setSelectedComponent(jpanel3);
/*      */   }
/*      */   
/*      */   public void jButton10_actionPerformed(ActionEvent e) {
/* 1334 */     jTabbedPane1.setSelectedComponent(jpanel1);
/*      */   }
/*      */   
/*      */   public void jButton2_actionPerformed(ActionEvent e) {
/* 1338 */     jTabbedPane1.setSelectedComponent(jpanel4);
/*      */   }
/*      */   
/*      */   public void jButton11_actionPerformed(ActionEvent e) {
/* 1342 */     jTabbedPane1.setSelectedComponent(jpanel2);
/*      */   }
/*      */   
/*      */   public void jButton3_actionPerformed(ActionEvent e) {
/* 1346 */     jTabbedPane1.setSelectedComponent(jpanel5);
/*      */   }
/*      */   
/*      */   public void jButton12_actionPerformed(ActionEvent e) {
/* 1350 */     jTabbedPane1.setSelectedComponent(jpanel3);
/*      */   }
/*      */   
/*      */   public void jButton4_actionPerformed(ActionEvent e) {
/* 1354 */     jTabbedPane1.setSelectedComponent(jpanel6);
/*      */   }
/*      */   
/*      */   public void jButton13_actionPerformed(ActionEvent e) {
/* 1358 */     jTabbedPane1.setSelectedComponent(jpanel4);
/*      */   }
/*      */   
/*      */   public void jButton6_actionPerformed(ActionEvent e) {
/* 1362 */     jTabbedPane1.setSelectedComponent(jpanel5);
/*      */   }
/*      */   
/*      */   public void jButton8_actionPerformed(ActionEvent e) {
/* 1366 */     jTabbedPane1.setSelectedComponent(jpanel22);
/*      */   }
/*      */   
/*      */   public void jButton7_actionPerformed(ActionEvent e) {
/* 1370 */     jTabbedPane1.setSelectedComponent(jpanel1);
/*      */   }
/*      */   
/*      */   public void jButton9_actionPerformed(ActionEvent e) {
/* 1374 */     jTabbedPane1.setSelectedComponent(jpanel21);
/*      */   }
/*      */   
/*      */   public void jBtnOut2_actionPerformed(ActionEvent e) {
/* 1378 */     FileDialog dialog = new FileDialog(new java.awt.Frame(), "Save Output", 1);
/*      */     
/* 1380 */     dialog.setFile("output.txt");
/* 1381 */     dialog.setVisible(true);
/* 1382 */     String filename = dialog.getDirectory() + dialog.getFile();
/* 1383 */     String str = jTxtOutput2.getText();
/* 1384 */     jTxtOutput2.setText(filename);
/* 1385 */     if (jTxtOutput2.getText().equalsIgnoreCase("nullnull")) {
/* 1386 */       jTxtOutput2.setText(str);
/*      */     }
/*      */   }
/*      */   
/*      */   public void jChBoxCross_actionPerformed(ActionEvent e) {
/* 1391 */     if (jChBoxCross.isSelected()) {
/* 1392 */       jBtnBrowseModel.setEnabled(false);
/* 1393 */       jTxtModel.setText("");
/* 1394 */       jTxtModel.setEnabled(false);
/* 1395 */       jLabel3.setEnabled(false);
/* 1396 */       jLabel14.setEnabled(true);
/* 1397 */       jTxtCross.setEnabled(true);
/* 1398 */       jTxtCross.setText("10");
/*      */     }
/*      */     else {
/* 1401 */       jBtnBrowseModel.setEnabled(true);
/* 1402 */       jTxtModel.setText("model.svm");
/* 1403 */       jTxtModel.setEnabled(true);
/* 1404 */       jLabel3.setEnabled(true);
/* 1405 */       jLabel14.setEnabled(false);
/* 1406 */       jTxtCross.setText("");
/* 1407 */       jTxtCross.setEnabled(false);
/*      */     }
/*      */   }
/*      */   
/*      */   public void jToggleButton1_actionPerformed(ActionEvent e) {
/* 1412 */     BrowserControl.displayURL("http://ocean.otr.usm.edu/~w547174/");
/*      */   }
/*      */   
/*      */ 
/*      */   public void jMenuHomePage_actionPerformed(ActionEvent e)
/*      */   {
/* 1418 */     BrowserControl.displayURL("http://mfgn.usm.edu/ebl/svm/");
/*      */   }
/*      */   
/*      */   public void jRBDelim_actionPerformed(ActionEvent e) {}
/*      */ }

/* Location:           E:\old laptop\d drive\gc personal\softwares\java\svm.jar
 * Qualified Name:     svm.svm_frame
 * Java Class Version: 1.2 (46.0)
 * JD-Core Version:    0.7.1
 */