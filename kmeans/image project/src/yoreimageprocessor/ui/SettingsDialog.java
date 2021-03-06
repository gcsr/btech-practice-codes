/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yoreimageprocessor.ui;

import java.util.prefs.Preferences;
import javax.swing.JOptionPane;

/**
 *
 * @author Sachindra
 */
public class SettingsDialog extends javax.swing.JDialog {
    public final static String HORIZONTOL_IMAGE_WIDTH = "HORIZONTOL_IMAGE_WIDTH";
    public final static String VERTICAL_IMAGE_WIDTH = "VERTICAL_IMAGE_WIDTH";
    public final static String RESIZE_TO_WIDTH = "RESIZE_TO_WIDTH";
   public final static String CROP_TO_WIDTH = "CROP_TO_WIDTH ";
    public final static String CROP_TO_HEIGHT = "CROP_TO_HEIGHT";
    public final static String IMAGE_QUALITY = "IMAGE_QUALITY";
    public final static String INCLUDE_SUBFOLDERS = "INCLUDE_SUBFOLDERS";
    public final static String SHOW_WARNING_MESSAGES = "SHOW_WARNING_MESSAGES";
    private Preferences preferences;
    /**
     * Creates new form SettingsDialog
     */
    public SettingsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        preferences = Preferences.userRoot().node(this.getClass().getName());
        initializeData();
    }
    
    private void initializeData() {
        horizontalImageWidthSpinner.setValue(preferences.getInt(HORIZONTOL_IMAGE_WIDTH, 800));
        verticalImageWidthSpinner.setValue(preferences.getInt(VERTICAL_IMAGE_WIDTH, 600));
        resizeToWidthSpinner.setValue(preferences.getInt(RESIZE_TO_WIDTH, 150));
        cropToWidthSpinner.setValue(preferences.getInt(CROP_TO_WIDTH, 125));
        cropToHeightSpinner.setValue(preferences.getInt(CROP_TO_HEIGHT, 91));
        imageQualitySpinner.setValue(preferences.getFloat(IMAGE_QUALITY, 1));
        includeSubFoldersCheckBox.setSelected(preferences.getBoolean(INCLUDE_SUBFOLDERS, true));
        showWarningCheckBox.setSelected(preferences.getBoolean(SHOW_WARNING_MESSAGES, true));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resultImageSizeLabel = new javax.swing.JLabel();
        horizontalImageWidthLabel = new javax.swing.JLabel();
        horizontalImageWidthSpinner = new javax.swing.JSpinner();
        verticalImageWidthLabel = new javax.swing.JLabel();
        verticalImageWidthSpinner = new javax.swing.JSpinner();
        thumbnailImageSizeLabel = new javax.swing.JLabel();
        thumbnailImageResizeToLabel = new javax.swing.JLabel();
        thumbnailImageCropToLabel = new javax.swing.JLabel();
        resizeToWidthSpinner = new javax.swing.JSpinner();
        cropToWidthSpinner = new javax.swing.JSpinner();
        cropToHeightSpinner = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        imageQualityLabel = new javax.swing.JLabel();
        imageQualitySpinner = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();
        includeSubFoldersCheckBox = new javax.swing.JCheckBox();
        showWarningCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Settings");

        resultImageSizeLabel.setText("Resut Image Size");

        horizontalImageWidthLabel.setText("Horizontal Image Width:");

        horizontalImageWidthSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10000, 1));

        verticalImageWidthLabel.setText("Vertical Image Width:");

        verticalImageWidthSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10000, 1));

        thumbnailImageSizeLabel.setText("Thumbnail Image Size");

        thumbnailImageResizeToLabel.setText("Resize to Width:");

        thumbnailImageCropToLabel.setText("Crop to Width:");

        resizeToWidthSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10000, 1));

        cropToWidthSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10000, 1));

        cropToHeightSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10000, 1));

        jLabel9.setText("Height:");

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, saveButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        imageQualityLabel.setText("Image Quality:");

        imageQualitySpinner.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(1.0f), Float.valueOf(0.1f)));

        includeSubFoldersCheckBox.setSelected(true);
        includeSubFoldersCheckBox.setText("Include Sub Folders");

        showWarningCheckBox.setText("Show warning messages during image processing");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(imageQualityLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(includeSubFoldersCheckBox)
                    .addComponent(imageQualitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showWarningCheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(horizontalImageWidthLabel)
                                    .addComponent(verticalImageWidthLabel)
                                    .addComponent(thumbnailImageResizeToLabel)
                                    .addComponent(thumbnailImageCropToLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cropToWidthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cropToHeightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(resultImageSizeLabel)
                            .addComponent(thumbnailImageSizeLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(resizeToWidthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(horizontalImageWidthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(verticalImageWidthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cropToWidthSpinner, imageQualitySpinner});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultImageSizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(horizontalImageWidthLabel)
                    .addComponent(horizontalImageWidthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verticalImageWidthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verticalImageWidthLabel))
                .addGap(18, 18, 18)
                .addComponent(thumbnailImageSizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thumbnailImageResizeToLabel)
                    .addComponent(resizeToWidthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cropToWidthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thumbnailImageCropToLabel)
                    .addComponent(jLabel9)
                    .addComponent(cropToHeightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imageQualitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageQualityLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(includeSubFoldersCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(showWarningCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        int horizontolImageWidth = (Integer) horizontalImageWidthSpinner.getValue();
        int verticalImageWidth = (Integer) verticalImageWidthSpinner.getValue();
        int resizeToWidth = (Integer) resizeToWidthSpinner.getValue();
        int cropToWidth = (Integer) cropToWidthSpinner.getValue();
        int cropToHeight  = (Integer) cropToHeightSpinner.getValue();
        float imageQualtiy = (Float) imageQualitySpinner.getValue();
        boolean includeSubFolders = (Boolean) includeSubFoldersCheckBox.isSelected();
        boolean showWarningMessages = (Boolean) showWarningCheckBox.isSelected();
        
        if(horizontolImageWidth < 1 || verticalImageWidth < 1 || resizeToWidth < 1 || cropToHeight < 1 || cropToWidth < 1) {
            JOptionPane.showMessageDialog(this, "Image size values should be greater than zero");
            return;
        }
        
        preferences.putInt(HORIZONTOL_IMAGE_WIDTH, horizontolImageWidth);
        preferences.putInt(VERTICAL_IMAGE_WIDTH, verticalImageWidth);
        preferences.putInt(RESIZE_TO_WIDTH, resizeToWidth);
        preferences.putInt(CROP_TO_WIDTH, cropToWidth);
        preferences.putInt(CROP_TO_HEIGHT, cropToHeight);
        preferences.putFloat(IMAGE_QUALITY, imageQualtiy);
        preferences.putBoolean(INCLUDE_SUBFOLDERS, includeSubFolders);
        preferences.putBoolean(SHOW_WARNING_MESSAGES, showWarningMessages);
        dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SettingsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingsDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SettingsDialog dialog = new SettingsDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JSpinner cropToHeightSpinner;
    private javax.swing.JSpinner cropToWidthSpinner;
    private javax.swing.JLabel horizontalImageWidthLabel;
    private javax.swing.JSpinner horizontalImageWidthSpinner;
    private javax.swing.JLabel imageQualityLabel;
    private javax.swing.JSpinner imageQualitySpinner;
    private javax.swing.JCheckBox includeSubFoldersCheckBox;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner resizeToWidthSpinner;
    private javax.swing.JLabel resultImageSizeLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JCheckBox showWarningCheckBox;
    private javax.swing.JLabel thumbnailImageCropToLabel;
    private javax.swing.JLabel thumbnailImageResizeToLabel;
    private javax.swing.JLabel thumbnailImageSizeLabel;
    private javax.swing.JLabel verticalImageWidthLabel;
    private javax.swing.JSpinner verticalImageWidthSpinner;
    // End of variables declaration//GEN-END:variables
}
