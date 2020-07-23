package shivanij;

/*    */ 
/*    */ 
/*    */ import java.awt.Dimension;


import svm.SplashScreen;
import svm.svm_frame;

/*    */ 
/*    */ public class svm_application
/*    */ {
/*  7 */   boolean packFrame = false;
/*    */   
/*    */   public svm_application()
/*    */   {
/* 11 */     SplashScreen splash = new SplashScreen(3000);
/* 12 */     splash.showSplash();
/*    */     
/* 14 */     svm_frame frame = new svm_frame();
/*    */     
/*    */ 
/* 17 */     if (packFrame) {
/* 18 */       frame.pack();
/*    */     }
/*    */     else {
/* 21 */       frame.validate();
/*    */     }
/*    */     
/* 24 */     Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
/* 25 */     Dimension frameSize = frame.getSize();
/* 26 */     frame.setVisible(true);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */     try
/*    */     {
/* 43 */       javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
/*    */     }
/*    */     catch (Exception e) {
/* 46 */       e.printStackTrace();
/*    */     }
/* 48 */     new svm_application();
/*    */   }
/*    */ }

/* Location:           E:\old laptop\d drive\gc personal\softwares\java\svm.jar
 * Qualified Name:     svm.svm_application
 * Java Class Version: 1.2 (46.0)
 * JD-Core Version:    0.7.1
 */