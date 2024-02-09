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

    // keeps track of drawn cards (in order to remove at each draw)
    static ArrayList<JLabel> cardsDrawn = new ArrayList<>();
    static JPanel cardDisplay = new JPanel();

    public static void populateDeck(){
        File folder = new File("src/PNG-cards-1.3");
        File[] listOfFiles = folder.listFiles();

        for(File file: listOfFiles){
            if(file.isFile()){
                deck.add(file);
            }
        }
    }


    private static class hitListener implements ActionListener{

        BufferedImage picToPrint = null;
        @Override
        public void actionPerformed(ActionEvent e) {

                //Remove any previously drawn cards
                if(cardsDrawn.size() > 0){
                    cardDisplay.remove(cardsDrawn.remove(0));
                }
                int rand = (int)(Math.random() * deck.size()); // 0 - 51
                deck.remove(rand);

                try {
                    picToPrint = ImageIO.read(deck.get(rand));
                }
                catch (java.io.IOException ioe){
                    ioe.printStackTrace();
                }

                ImageIcon imageIcon = new ImageIcon(picToPrint);
                Image image = imageIcon.getImage();
                Image newPic = image.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
                JLabel pic = new JLabel(new ImageIcon(newPic));
                cardDisplay.add(pic);

                // adds JLabel to an ArrayList to keep reference to
                cardsDrawn.add(pic);

                cardDisplay.revalidate();
                cardDisplay.repaint();




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