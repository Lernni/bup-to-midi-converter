import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JProgressBar;

public class Convert implements Runnable {

  File input;
  File output;
  JProgressBar bar;

  String midiHeader = "4D54686400000006000000010060";
  String mtrk = "4D54726B";
  String ende = "FF2F00";

  public Convert(JProgressBar bar, File input, File output) {
    this.input = input;
    this.output = output;
    this.bar = bar;
  }

  @Override
  public void run() {
    // *** Read Input ***
    StringBuilder sbHex = new StringBuilder();
    bar.setIndeterminate(true);

    try {
      int value = 0;
      int i = 0;
      InputStream is = new FileInputStream(input);
      while ((value = is.read()) != -1) {
        i++;
        bar.setString(String.valueOf(i) + " data sets");
        sbHex.append(String.format("%02X", value));
      }
      is.close();
    } catch (IOException e) {
    }

    // *** Analyze Input ***

    String hexaBup = sbHex.toString();
    int count = 0;
    for (int a = 0; a < (hexaBup.length() - mtrk.length()); a++) {
      if (hexaBup.substring(a, mtrk.length() + a).equals(mtrk)) {
        count++;
        String midi = midiHeader + hexaBup.substring(a, hexaBup.indexOf(ende, a) + 6);
        
        // *** Save Output ***
        
        int len = midi.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
          data[i / 2] = (byte) ((Character.digit(midi.charAt(i), 16) << 4) + Character.digit(midi.charAt(i + 1), 16));
        }
        
        try {
          OutputStream os = new FileOutputStream(new File(output.getPath() + "_" + String.valueOf(count) + ".mid"));
          os.write(data);
          os.close();
        } catch (IOException e) {}

        bar.setString("Converted Midi: " + String.valueOf(count));
      }
    }
    try {
      Desktop.getDesktop().open(output.getParentFile());
    } catch (IOException e) {}
    bar.setIndeterminate(false);

  }
}