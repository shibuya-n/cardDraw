import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

public class cardGUI {
    static JButton hit = new JButton("HIT");
    static ArrayList<File> deck = new ArrayList(); // 52 cards

    public static void populateDeck(){
        File folder = new File("PNG-cards-1.3");
        File[] listOfFiles = folder.listFiles();

        for(File file: listOfFiles){
            if(file.isFile()){
                deck.add(file);
            }
        }
    }

    private static class hitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
                int rand = (int)(Math.random() * deck.size()); // 0 - 52
                File obj = deck.remove(rand);

                BufferedImage myPicture = null;
                try {
                    myPicture = ImageIO.read(obj);
                }
                catch (java.io.IOException ioe){
                    ioe.printStackTrace();
                }
                ImageIcon imageIcon = new ImageIcon(myPicture);
                Image image = imageIcon.getImage();
                Image newing = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                JLabel picLabel = new JLabel(new ImageIcon(newing));



            }
        }

    public static void main(String[] args){
        JFrame window = new JFrame("Draw Cards");
        window.setLayout(new GridLayout(2,1));

        JPanel buttonGrid = new JPanel();
        buttonGrid.setLayout(new FlowLayout());
        hit.addActionListener(new hitListener());
        buttonGrid.add(hit);

        JPanel cardDisplay = new JPanel();
        cardDisplay.setLayout(new FlowLayout());

        window.add(buttonGrid);
        window.add(cardDisplay);
        window.setBounds(500,500, 750 , 500);
        window.setVisible(true);
    }
}