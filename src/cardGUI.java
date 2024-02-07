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

    static JPanel cardDisplay = new JPanel();

    static JLabel pic = new JLabel();

    public static void populateDeck(){
        File folder = new File("/Users/andy/code/cardDraw/src/PNG-cards-1.3");
        File[] listOfFiles = folder.listFiles();

        for(File file: listOfFiles){
            if(file.isFile()){
                deck.add(file);
            }
        }
    }
    private static void updateImage(JLabel name)
    {
        Image image = null;
        Image scaledImage = null;
        JLabel tempImage;

        image = name;

        // getScaledImage returns an Image that's been resized proportionally to my thumbnail constraints
        scaledImage = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);

        pic = new JLabel(new ImageIcon(scaledImage));
    }

    private static class hitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

                int rand = (int)(Math.random() * deck.size()); // 0 - 51
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
                JLabel pic = new JLabel(new ImageIcon(newing));

                updateImage(pic);
                cardDisplay.add(pic);

                cardDisplay.repaint();
                cardDisplay.revalidate();



            }
        }

    public static void main(String[] args){
        JFrame window = new JFrame("Draw Cards");
        window.setLayout(new GridLayout(2,1));
        populateDeck();

        JPanel buttonGrid = new JPanel();
        buttonGrid.setLayout(new FlowLayout());
        hit.addActionListener(new hitListener());
        buttonGrid.add(hit);

        cardDisplay.setLayout(new FlowLayout());

        window.add(buttonGrid);
        window.add(cardDisplay);
        window.setBounds(500,500, 750 , 750);
        window.setVisible(true);
    }
}