import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



class Work2 extends JFrame{
JButton button ;
JButton mirror;
JButton negative ;
JButton redline;
JButton glass;
JLabel label;
String path1;

public Work2(){
super("Set Picture Into A JLabel Using JFileChooser In Java");
button = new JButton("Browse");
    button.setBounds(300,300,100,40);
negative = new JButton("negative");
  negative.setBounds(200,300,80,40);
mirror = new JButton("mirror");
    mirror.setBounds(100,300,80,40);
0redline = new JButton("redline");
    redline.setBounds(2,300,80,40);
glass = new JButton("glass");
    glass.setBounds(410,300,80,40);
    
    label = new JLabel();
    label.setBounds(10,10,670,250);
add(button);
add(label);
add(mirror);
add(negative);
add(redline);
add(glass);


    button.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        
          JFileChooser file = new JFileChooser();
          file.setCurrentDirectory(new File(System.getProperty("user.home")));
          //filter the files
          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","png");
          file.addChoosableFileFilter(filter);
          int result = file.showSaveDialog(null);
           //if the user click on save in Jfilechooser
          if(result == JFileChooser.APPROVE_OPTION){
              File selectedFile = file.getSelectedFile();
              String path = selectedFile.getAbsolutePath();
path1=path;
        ImageIcon MyImagex = new ImageIcon(path);
        Image imgx = MyImagex.getImage();
        Image newImgx = imgx.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imagex = new ImageIcon(newImgx);
	label.setIcon(imagex);
        
          }
                     else if(result == JFileChooser.CANCEL_OPTION){
              System.out.println("No File Select");
          }
        }
    });

negative.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent eve) {    
if(eve.getSource()==negative)
{
BufferedImage img = null;
    File f = null;
try{
      f = new File(path1);
      img = ImageIO.read(f);
    }catch(IOException e){
      System.out.println(e);
    }
    //get image width and height
    int width = img.getWidth();
    int height = img.getHeight();
    //convert to negative
    for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        int p = img.getRGB(x,y);
        int a = (p>>24)&0xff;
        int r = (p>>16)&0xff;
        int g = (p>>8)&0xff;
        int b = p&0xff;
        //subtract RGB from 255
        r = 255 - r;
        g = 255 - g;
        b = 255 - b;
        //set new RGB value
        p = (a<<24) | (r<<16) | (g<<8) | b;
        img.setRGB(x, y, p);
      }
    }
    try{
      f = new File("s://programs//group//opt.jpg"); //path where image to be stored
      ImageIO.write(img,"jpg",f);
/*ImageIcon icon = new ImageIcon(img);
        label = new JLabel(icon, JLabel.CENTER);
        JOptionPane.showMessageDialog(null, label, "icon", -1);*/

    }catch(IOException ex){
      System.out.println(ex);
    }
}

}});

//this is mirror code



mirror.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent eve1) {    
if(eve1.getSource()==mirror)
{
BufferedImage simg = null;
    //File object
    File f = null;
    //read source image file
    try{
      f = new File(path1);
      simg = ImageIO.read(f);
    }catch(IOException e){
      System.out.println("Error: " + e);
    }
    //get source image dimension
    int width = simg.getWidth();
    int height = simg.getHeight();
    //BufferedImage for mirror image
    BufferedImage mimg = new BufferedImage(width*2, height, BufferedImage.TYPE_INT_ARGB);
    //create mirror image pixel by pixel
    for(int y = 0; y < height; y++){
      for(int lx = 0, rx = width*2 - 1; lx < width; lx++, rx--){
        //lx starts from the left side of the image
        //rx starts from the right side of the image
        //get source pixel value
        int p = simg.getRGB(lx, y);
        //set mirror image pixel value - both left and right
        mimg.setRGB(lx, y, p);
        mimg.setRGB(rx, y, p);
      }
    }
    //save mirror image
    try{
      f = new File("S:\\programs\\group\\Output.jpg"); //path where image to be stored
      ImageIO.write(mimg, "png", f);
    }catch(IOException e){
      System.out.println("Error: " + e);
    }
}

}});

//3rd code

redline.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent eve2) {    
if(eve2.getSource()==redline)
{
BufferedImage img1 = null;
    File f = null;
    try{
      f = new File(path1);
img1 = ImageIO.read(f);
 
    }catch(IOException e){
      System.out.println(e);
    }
    //get image width and height
    int width = img1.getWidth();

int height = img1.getHeight();
System.out.println(height);
    //convert to negative
    for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        int p = img1.getRGB(x,y);
        int a = (p>>24)&0xff;
        int r = (p>>16)&0xff;
        int g = (p>>8)&0xff;
        int b = p&0xff;
       
        //set new RGB value
if(y%5!=0){	
r=r;
g=g;
b=b;
}
else
{
r=230;
g=80;
b=70;
}

p = (a<<24) | (r<<16) | (g<<8) | b;
img1.setRGB(x, y, p);

}    
}
    //write image
    try{
      f = new File("S:\\programs\\group\\final23.jpg"); //path where image to be stored
      ImageIO.write(img1,"jpg",f);
    }catch(IOException e){
      System.out.println(e);
    }

}

}});

//glass filter

glass.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent eve3) {    
if(eve3.getSource()==glass)
{
BufferedImage img = null;
    File f = null;
try{
      f = new File(path1);
      img = ImageIO.read(f);
    }catch(IOException e){
      System.out.println(e);
    }
    //get image width and height
    int width = img.getWidth();
    int height = img.getHeight();
    //convert to negative
    for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        int p = img.getRGB(x,y);
        int a = (p>>24)&0xff;
        int r = (p>>16)&0xff;
        int g = (p>>8)&0xff;
        int b = p&0xff;
        //subtract RGB from 255
        r = 255 - r;
        g = 255 - g;
        b = 255 - b;
        //set new RGB value
        p = (a<<24) | (r<<16) | (g<<8) | b;
        img.setRGB(x, y, p);
      }
    }
    try{
      f = new File("s://programs//group//opt.jpg"); //path where image to be stored
      ImageIO.write(img,"jpg",f);
/*ImageIcon icon = new ImageIcon(img);
        label = new JLabel(icon, JLabel.CENTER);
        JOptionPane.showMessageDialog(null, label, "icon", -1);*/

    }catch(IOException ex){
      System.out.println(ex);
    }
}

}});


    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setSize(800,800);
    setVisible(true);
    }
     
     
    public static void main(String[] args)throws IOException{
        new Work2();
    }
   }