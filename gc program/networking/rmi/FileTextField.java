
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class FileTextField extends JPanel {
    private JTextField _fileNameField;
    private JFileChooser _fileChooser;	// Keeping a reference to a JFileChooser
    // lets us remember the last directory
    // the user looked at
    public FileTextField() {
        super (new GridBagLayout());
        buildBasicGUI();
    }

    public FileTextField(Action thingToDoWithFile) {
        super (new GridBagLayout());
        buildBasicGUI();
        addButton(thingToDoWithFile, 4);
    }

    public FileTextField(String label) {
        super (new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(label));
        buildBasicGUI();
    }

    public FileTextField(Action thingToDoWithFile, String label) {
        super (new GridBagLayout());
        buildBasicGUI();
        setBorder(BorderFactory.createTitledBorder(label));
        addButton(thingToDoWithFile, 4);
    }

    protected void buildBasicGUI() {
        createFileNameField();
        addButton(new BrowseButtonAction(), 3);
    }

    public File getFile() {
        String fileName = _fileNameField.getText();

        if (null != fileName) {
            return new File(fileName);
        }
        return null;
    }

    public FileInputStream getFileInputStream() {
        String fileName = _fileNameField.getText();

        if (null != fileName) {
            try {
                return new FileInputStream(fileName);
            } catch (java.io.FileNotFoundException e) {
            }
        }
        return null;
    }

    public FileOutputStream getFileOutputStream() {
        String fileName = _fileNameField.getText();

        if (null != fileName) {
            try {
                return new FileOutputStream(fileName);
            } catch (java.io.FileNotFoundException e) {
            }
        }
        return null;
    }

    public FileReader getFileReader() {
        String fileName = _fileNameField.getText();

        if (null != fileName) {
            try {
                return new FileReader(fileName);
            } catch (java.io.FileNotFoundException e) {
            }
        }
        return null;
    }

    public FileWriter getFileWriter() {
        String fileName = _fileNameField.getText();

        if (null != fileName) {
            try {
                return new FileWriter(fileName);
            } catch (java.io.IOException e) {
            }
        }
        return null;
    }

    public String getFileName() {
        return _fileNameField.getText();
    }

    private void createFileNameField() {
        _fileNameField = new JTextField();
        GridBagConstraints textFieldPosition = new GridBagConstraints();

        textFieldPosition.gridx = 0;
        textFieldPosition.gridwidth = 3;
        textFieldPosition.gridy = 0;
        textFieldPosition.gridheight = 1;
        textFieldPosition.weightx = 1.0;
        textFieldPosition.fill = GridBagConstraints.HORIZONTAL;
        add(_fileNameField, textFieldPosition);
    }

    private void addButton(Action buttonAction, int location) {
        GridBagConstraints buttonPosition = new GridBagConstraints();

        buttonPosition.gridx = location;
        buttonPosition.gridwidth = 1;
        buttonPosition.gridy = 0;
        buttonPosition.gridheight = 1;
        buttonPosition.weightx = 0.0;
        add(new ActionButton(buttonAction), buttonPosition);
    }

    private class BrowseButtonAction extends AbstractAction {
        public BrowseButtonAction() {
            putValue(Action.NAME, "Select");
            putValue(Action.SHORT_DESCRIPTION, "Select a file from the hard drive.");
        }

        public void actionPerformed(ActionEvent event) {
            if (null == _fileChooser) {
                _fileChooser = new JFileChooser();
            }
            if (JFileChooser.APPROVE_OPTION == _fileChooser.showOpenDialog(FileTextField.this)) {
                _fileNameField.setText((_fileChooser.getSelectedFile()).getAbsolutePath());
            }
        }
    }
}
