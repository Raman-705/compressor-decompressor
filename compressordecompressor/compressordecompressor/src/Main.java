import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class decompressor{
    public void method(File file) throws IOException{
        String fileDirectory =file.getParent();
        FileInputStream fis=new FileInputStream(file);
        GZIPInputStream gzip=new GZIPInputStream(fis);
        FileOutputStream fos=new FileOutputStream(fileDirectory+"/Decompressed file");
        byte[] buffer=new byte[1024];
        int len;
        while((len=gzip.read(buffer))!=-1){
         fos.write(buffer,0,len);
        }
        gzip.close();
        fos.close();
        fis.close();

    }

    public static void main(String[] args) {
        File path=new File("C:");
        method(path);
    }
}

public class compressor{
    public static void method(File file) throws IOException{
        String fileDirectory=file.getParent();
        FileInputStream fis=new FileInputStream(file);
        FileOutputStream fos=new FileOutputStream(fileDirectory+"/CompressedFile.gz");
        GZIPOutputStream gzip=new GZIPOutputStream(fos);
        byte buffer[]=new byte[1024];
        int len;
         while(len=fis.read(buffer)!=-1){
             gzip.write(buffer,0,len);
         }
         gzip.close();
         fos.close();
         fis.close();

    }

    public static void main(String[] args) throws IOException{
      File path=new File("C:");
      method(path);
    }
}

public class AppFrame extends JFrame implements ActionListener{

    JButton compressButton;
     JButton decompressButton;

    AppFrame(){
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    compressButton= new JButton("Select");
    compressButton.setBounds(20,100,200,30);
    compressButton.addActionListener(this);
    decompressButton=new JButton("Select file to decompress");
       decompressButton.setBounds(250,100,200,30);
        decompressButton.addActionListener(this);
       this.add(compressButton);
       this.add(decompressButton);
       this.setSize(1000,500);
       this.getContentPane().setBackground(Color.BLUE);
       this.setVisible(true);

    }




    @Override
    public void actionPerformed(ActionEvent e){
  if(e.getSource()==compressButton){
      JFileChooser fileChooser=new JFileChooser();
      int response =fileChooser.showSaveDialog(null);
      if(response==JFileChooser.APPROVE_OPTION){
          File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
          System.out.print(file);
          try{
              compressor.method(file);
          }
          catch (Exception ee){
              JOptionPane.showMessageDialog(null,ee.toString());
          }

      }
  }
  if(e.getSource()==decompressButton){
      JFileChooser fileChooser=new JFileChooser();
      int response =fileChooser.showSaveDialog(null);
      if(response==JFileChooser.APPROVE_OPTION){
          File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
          System.out.print(file);
          try{
              decompressor.method(file);
          }
          catch (Exception ee){
              JOptionPane.showMessageDialog(null,ee.toString());
          }

      }
  }
    }
}
public class Main {
    public static void main(String[] args) {
  AppFrame frame=new AppFrame();
    }
}