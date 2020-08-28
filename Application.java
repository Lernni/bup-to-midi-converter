import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Application extends JFrame {

  private static final long serialVersionUID = 1L;

  handler h = new handler();
  FileDialog fd = new FileDialog(this);
  JTextField bupPathField = new JTextField(20);
  JTextField midiPathField = new JTextField(20);
  JButton convertButton = new JButton("Convert");
  JButton bupPathBtn = new JButton("...");
  JButton midiPathBtn = new JButton("...");
  JPanel panel = new JPanel();
  JPanel textFieldPanel = new JPanel();
  JPanel buttonPanel = new JPanel();
  JProgressBar bar = new JProgressBar();

  String[] fdDir = { "C:\\", "C:\\" };
  File inputFile;
  File outputFile;

  public Application() {
    JLabel bupPathLabel = new JLabel("Input:", JLabel.TRAILING);
    JLabel midiPathLabel = new JLabel("Output:", JLabel.TRAILING);
    bupPathBtn.addActionListener(h);
    midiPathBtn.addActionListener(h);

    textFieldPanel.setLayout(new SpringLayout());
    bupPathLabel.setLabelFor(bupPathField);
    midiPathLabel.setLabelFor(midiPathField);
    textFieldPanel.add(bupPathLabel);
    textFieldPanel.add(bupPathField);
    textFieldPanel.add(bupPathBtn);
    textFieldPanel.add(midiPathLabel);
    textFieldPanel.add(midiPathField);
    textFieldPanel.add(midiPathBtn);
    SpringUtilities.makeCompactGrid(textFieldPanel, 2, 3, 5, 5, 5, 5);

    convertButton.addActionListener(h);

    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(convertButton);

    bar.setPreferredSize(new Dimension(0, 20));
    bar.setStringPainted(true);
    bar.setString("");

    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    panel.add(textFieldPanel);
    panel.add(bar);
    panel.add(buttonPanel);

    this.setTitle("BUP-Converter");
    this.setContentPane(panel);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        System.exit(0);
      }
    });
  }

  public void chooseFile(boolean inorout) {
    fd.setTitle(inorout ? "Choose an input file..." : "Choose a destination dir and file name beginning...");
    fd.setDirectory(fdDir[inorout ? 0 : 1]);
    fd.setFile("");
    fd.setVisible(true);
    String path = fd.getDirectory();
    String fileName = fd.getFile();
    String extension = "";
    try {
      extension = fileName.substring(fileName.lastIndexOf(".") + 1);
    } catch (Exception ex) {
    }

    if (fileName != null) {
      if (inorout) { // Input
        if (extension.toLowerCase().equals("bup")) {
          inputFile = new File(path + "\\" + fileName);
          outputFile = new File(path + "\\" + fileName.substring(0, fileName.lastIndexOf(".")));
          bupPathField.setText(inputFile.getAbsolutePath());
          midiPathField.setText(outputFile.getAbsolutePath());
        } else {
          JOptionPane.showMessageDialog(this, "You can only choose '.bup'-Files!");
        }

        fdDir[0] = fd.getDirectory();
      } else { // Output
        if (!fileName.contains(".")) {
          outputFile = new File(path + "\\" + fileName);
          midiPathField.setText(outputFile.getAbsolutePath());
        } else {
          JOptionPane.showMessageDialog(this, "You can only choose directories!");
        }

        fdDir[1] = fd.getDirectory();
      }
    }
  }

  public class handler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == bupPathBtn || e.getSource() == midiPathBtn) {
        chooseFile(e.getSource() == bupPathBtn);
      }
      if (e.getSource() == convertButton) {
        if (inputFile.exists() && !(outputFile.isDirectory()) && outputFile.getParentFile().exists()) {
          new Convert(bar, inputFile, outputFile).execute();
        }
      }
    }
  }
}