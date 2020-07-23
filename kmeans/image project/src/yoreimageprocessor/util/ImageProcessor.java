/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yoreimageprocessor.util;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import yoreimageprocessor.ui.SettingsDialog;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.prefs.Preferences;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import org.imgscalr.Scalr;
import static yoreimageprocessor.ui.SettingsDialog.SHOW_WARNING_MESSAGES;

/**
 *
 * @author Sachindra
 */
public class ImageProcessor {

    private final static int HORIZONTOL_IMAGE = 0;
    private final static int VERTICAL_IMAGE = 1;
    int horizontolImageWidth;
    int verticalImageWidth;
    int resizeToWidth;
    int cropToWidth;
    int cropToHeight;
    float imageQuality;
    File sourceFolder;
    File destinationFolder;
    boolean includeSubFolders;
    boolean showWarningMessages;
    Run run;
    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
        "jpg", "gif", "png", "bmp" // and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

    public ImageProcessor(File sourceFolder, File destinationFolder, Run run) {
        Preferences preferences = Preferences.userRoot().node(SettingsDialog.class.getName());;
        horizontolImageWidth = preferences.getInt(SettingsDialog.HORIZONTOL_IMAGE_WIDTH, 800);
        verticalImageWidth = preferences.getInt(SettingsDialog.VERTICAL_IMAGE_WIDTH, 600);
        resizeToWidth = preferences.getInt(SettingsDialog.RESIZE_TO_WIDTH, 150);
        cropToWidth = preferences.getInt(SettingsDialog.CROP_TO_WIDTH, 125);
        cropToHeight = preferences.getInt(SettingsDialog.CROP_TO_HEIGHT, 91);
        imageQuality = preferences.getFloat(SettingsDialog.IMAGE_QUALITY, 1);
        includeSubFolders = preferences.getBoolean(SettingsDialog.INCLUDE_SUBFOLDERS, true);
        showWarningMessages = preferences.getBoolean(SHOW_WARNING_MESSAGES, true);
        this.sourceFolder = sourceFolder;
        this.destinationFolder = destinationFolder;
        this.run = run;
    }
     public static Comparator<File> fileDateComparator = new Comparator<File>() {

        @Override
        public int compare(File o1, File o2) {
            try {
                Date dateTaken1 = ImageMetadataReader.readMetadata(o1).getOrCreateDirectory(
                            ExifSubIFDDirectory.class).getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                Date dateTaken2 = ImageMetadataReader.readMetadata(o2).getOrCreateDirectory(
                            ExifSubIFDDirectory.class).getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                return dateTaken1.compareTo(dateTaken2);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        }
    };

    private final void processImages(JTextArea imagesFoundTextArea, JProgressBar completedProgressBar) {
        int passCount = 0;
        int failCount = 0;
        Map<String, Integer> imageCounts = new HashMap<String, Integer>();
        ArrayList<File> sourceFileList = getImageFiles();
        Collections.sort(sourceFileList, fileDateComparator);
        SimpleDateFormat folderNameDateFormat = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
        SimpleDateFormat fileNameDateFormat = new SimpleDateFormat("yyyy-EEE-MM-dd-hhmma", Locale.ENGLISH);
        SimpleDateFormat imageCountDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        float stepSize = 100.0f / sourceFileList.size();
        boolean first = true;
        for (final File sourceFile : sourceFileList) {
            if (!run.isRun()) {
                String statusMessage = "\n\n" + (passCount + failCount) + " images processed at " + imageQuality + " quality. (" + passCount + " Completed, " + failCount + " Failed)";
                imagesFoundTextArea.setText(imagesFoundTextArea.getText() + statusMessage);
                JOptionPane.showMessageDialog(null, "Process Cancelled. " + statusMessage);
                return;
            }
            BufferedImage sourceImage = null;

            try {
                Date dateTaken = ImageMetadataReader.readMetadata(sourceFile).getOrCreateDirectory(
                        ExifSubIFDDirectory.class).getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                dateTaken.setSeconds(0);

                String folderName = folderNameDateFormat.format(dateTaken);
                String fileName = fileNameDateFormat.format(dateTaken) + ".jpg";
                String imageCountDate = imageCountDateFormat.format(dateTaken);
                if (imageCounts.containsKey(imageCountDate)) {
                    imageCounts.put(imageCountDate, imageCounts.get(imageCountDate) + 1);
                } else {
                    imageCounts.put(imageCountDate, 1);
                }
                fileName = addImageCount(fileName, imageCounts.get(imageCountDate));
                createDirectories(destinationFolder, folderName);
                sourceImage = ImageIO.read(sourceFile);
                resizeImage(sourceImage, folderName, fileName);
                resizeThumbnailImage(sourceImage, folderName, fileName);
                if (first) {
                    imagesFoundTextArea.setText(imagesFoundTextArea.getText() + sourceFile.getName() + " (" + sourceImage.getWidth() + " X " + sourceImage.getHeight() + ") - " + convertByteCount(sourceFile.length()) + " [" + dateTaken + "]");
                    first = false;
                } else {
                    imagesFoundTextArea.setText(imagesFoundTextArea.getText() + "\n" + sourceFile.getName() + " (" + sourceImage.getWidth() + " X " + sourceImage.getHeight() + ") - " + convertByteCount(sourceFile.length())+ " [" + dateTaken + "]");

                }
                completedProgressBar.setValue(completedProgressBar.getValue() + (int) stepSize);
                passCount++;

            } catch (final Exception e) {
                failCount++;
                if(showWarningMessages) {
                JOptionPane.showMessageDialog(null, "Image file write error\n\n" + sourceFile.getAbsolutePath());
                }
                if (first) {
                    imagesFoundTextArea.setText(imagesFoundTextArea.getText() + "Failed: " + sourceFile.getAbsolutePath());
                    first = false;
                } else {
                    imagesFoundTextArea.setText(imagesFoundTextArea.getText() + "\nFailed: " + sourceFile.getAbsolutePath());
                }
            }
        }
        if(failCount < 1) {
            completedProgressBar.setValue(100);
        }
        String statusMessage = "\n\n" + (passCount + failCount) + " images processed at " + imageQuality + " quality. (" + passCount + " Completed, " + failCount + " Failed)";
        imagesFoundTextArea.setText(imagesFoundTextArea.getText() + statusMessage);
        JOptionPane.showMessageDialog(null, "Process Completed. " + statusMessage);
        run.setRun(false);
    }

    public void process(final JTextArea imagesFoundTextArea, final JProgressBar progressBar, final JButton cancelButton) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                cancelButton.setEnabled(true);
                processImages(imagesFoundTextArea, progressBar);
                cancelButton.setEnabled(false);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void resizeImage(BufferedImage sourceImage, String folderName, String fileName) throws Exception {
        int existingWidth = sourceImage.getWidth();
        int existingHeight = sourceImage.getHeight();
        File destinationFile = new File(new File(destinationFolder, folderName), fileName);

        int imageType = existingHeight < existingWidth ? HORIZONTOL_IMAGE : VERTICAL_IMAGE;

        if (imageType == HORIZONTOL_IMAGE) {
            float aspectRatio = existingHeight / existingWidth;
            int width = horizontolImageWidth;
            int height = Math.round(width * aspectRatio);
            BufferedImage scaledImage = Scalr.resize(sourceImage, width, height);
            //ImageIO.write(scaledImage, "jpg", destinationFile);
            saveImage(destinationFile, scaledImage);
        } else if (imageType == VERTICAL_IMAGE) {
            float aspectRatio = existingHeight / existingWidth;
            int width = verticalImageWidth;
            int height = Math.round(width * aspectRatio);
            BufferedImage scaledImage = Scalr.resize(sourceImage, width, height);
            //ImageIO.write(scaledImage, "jpg", destinationFile);
            saveImage(destinationFile, scaledImage);
        }
    }

    private void resizeThumbnailImage(BufferedImage sourceImage, String folderName, String fileName) throws Exception {
        int existingWidth = sourceImage.getWidth();
        int existingHeight = sourceImage.getHeight();
        File destinationFile = new File(new File(new File(destinationFolder, folderName), "thumb"), fileName);

        float aspectRatio = ((float) existingHeight) / existingWidth;
        int width = resizeToWidth;
        int height = Math.round(width * aspectRatio);
        BufferedImage scaledImage = Scalr.resize(sourceImage, width, height);
        BufferedImage cropImage = Scalr.crop(scaledImage, ((width / 2) - (cropToWidth / 2)), ((height / 2) - (cropToHeight / 2)), cropToWidth, cropToHeight);

        //ImageIO.write(cropImage, "jpg", destinationFile);
        saveImage(destinationFile, cropImage);

    }

    private void createDirectories(File destinationFolder, String dateTaken) {
        File folder = new File(destinationFolder, dateTaken);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File thumbnailImageFolder = new File(folder, "thumb");
        if (!thumbnailImageFolder.exists()) {
            thumbnailImageFolder.mkdir();
        }
    }

    private ArrayList<File> getImageFiles() {

        ArrayList<File> sourceFileList = new ArrayList<File>();
        if (includeSubFolders) {
            listFiles(sourceFolder.getAbsolutePath(), sourceFileList);
        } else {
            File[] fList = sourceFolder.listFiles();
            for (File file : fList) {
                if (file.isFile()) {
                    sourceFileList.add(file);
                }
            }

        }
        return sourceFileList;
    }

    private void listFiles(String directoryName, ArrayList<File> files) {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listFiles(file.getAbsolutePath(), files);
            }
        }
    }

    private static String convertByteCount(long bytes) {
        boolean si = true;
        int unit = si ? 1000 : 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    private String addImageCount(String fileName, int imageCount) {
        StringBuffer fileNameText = new StringBuffer(fileName);
        fileNameText.insert(4, "-" + String.format("%03d", imageCount));
        return fileNameText.toString();
    }

    private void saveImage(File destinationFile, BufferedImage image) throws Exception {
        Iterator<ImageWriter> i = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter jpegWriter = i.next();
        ImageWriteParam param = jpegWriter.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(imageQuality);
        FileImageOutputStream out = new FileImageOutputStream(destinationFile);
        jpegWriter.setOutput(out);
        jpegWriter.write(null, new IIOImage(image, null, null), param);
        jpegWriter.dispose();
        out.close();
    }
}
