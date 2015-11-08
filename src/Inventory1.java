import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inventory1 extends JFrame implements ActionListener {
    //Utility class for displaying the picture
    //If we are going to use a class/method/variable inside that class only, we declare it private in that class
    private class MyPanel extends JPanel {
        ImageIcon image = new ImageIcon("dvd.gif");
        int width = image.getIconWidth();
        int height = image.getIconHeight();
        long angle = 30;
        public MyPanel(){
            super();
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            //g2d.rotate (Math.toRadians(angle), 60+width/2, 60+height/2);
            g2d.drawImage(image.getImage(), 60, 60, this);
            g2d.dispose();
        }
    }//end class MyPanel

    int currentIndex; //Currently displayed Item
    Product[] supplies = new Product[4];
    JLabel itemName ;
    JLabel itemNumber;
    JLabel rating;
    JLabel quantity;
    JLabel price;
    JLabel fee;
    JLabel totalValue;
    JTextField itemNameField = new JTextField(20);
    JTextField itemNumberField = new JTextField(20);
    JTextField ratingField = new JTextField(20);
    JTextField quantityField = new JTextField(20);
    JTextField priceField = new JTextField(20);

    JPanel display;
    JPanel displayHolder;
    JPanel panel;
    public Inventory1() {
        makeTheDataItems();
        setSize(600, 500);
        setTitle("Inventory v1.0");
        //make the panels
        display = new JPanel();
        JPanel other = new JPanel();
        JPanel picture = new MyPanel();
        JPanel buttons = new JPanel();
        JPanel centerPanel = new JPanel();
        displayHolder = new JPanel();
        display.setLayout(new GridLayout(5, 3));
        other.setLayout(new GridLayout(2, 1));
        //make the labels
        itemName = new     JLabel("Name       :");
        itemNumber = new     JLabel("Number       :");
        rating = new     JLabel("Rating      :");
        quantity = new JLabel("Quantity       :");
        price = new     JLabel("Price       :");
        fee = new        JLabel("Fee       :");
        totalValue = new JLabel("Total Value       :");
        //Use the utility method to make the buttons
        JButton first = makeButton("First");
        JButton next = makeButton("Next");
        JButton previous = makeButton("Previous");
        JButton last = makeButton("Last");
        JButton exit = makeButton("Exit");
        //Other buttons
        JButton add = makeButton("Add");

        //Add the labels to the display panel
        display.add(itemName);
        display.add(itemNumber);
        display.add(rating);
        display.add(quantity);
        display.add(price);
        display.add(fee);
        //add the buttons to the buttonPanel
        buttons.add(first);
        buttons.add(previous);
        buttons.add(next);
        buttons.add(last);
        buttons.add(exit);
        //Add the picture panel and display to the centerPanel
        displayHolder.add(display);
        centerPanel.setLayout(new GridLayout(2, 1));
        centerPanel.add(picture);
        centerPanel.add(displayHolder);
        other.add(buttons);
        JPanel forAdd = new JPanel(); // add the other buttons to this panel
        forAdd.add(add);
        other.add(forAdd);

        //Add the panels to the frame
        getContentPane().add(centerPanel, "Center");
        getContentPane().add(other, "South");
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setVisible(true);
    }
    private void makeTheDataItems () {
        Product p1 = new DVDTitle("XMEN-The Last Stand", 001, 200, 12.99, "PG-13");
        Product p2 = new DVDTitle("Harry Potter-The Goblet of Fire", 002, 50, 9.95, "PG-13");
        Product p3 = new DVDTitle("Jackass-Number Two", 003, 100, 19.95, "R");
        Product p4 = new DVDTitle("Pirates of the Caribbean", 004, 75, 9.99, "PG-13");

        supplies[0] = p1;
        supplies[1] = p2;
        supplies[2] = p3;
        supplies[3] = p4;
    }
    //Utility method for creating and dressing buttons
    private JButton makeButton(String label) {
        JButton button = new JButton(label);
        button.setActionCommand(label);
        button.addActionListener(this);
        return button;
    }
    private void addItem() {
        System.out.println("eeeeeeeeee");
        panel = new JPanel();
        JPanel add = new JPanel();
        add.setLayout(new GridLayout(2, 1));
        add.setLayout(new GridLayout(4, 4));
        JButton addIt = makeButton("Add Item");
        JLabel itemName = new JLabel("Name       :");
        //JLabel itemNumber = new JLabel("Number       :");
        JLabel rating = new JLabel("Rating       :");
        JLabel quantity = new JLabel("Quantity    :");
        JLabel price = new JLabel("Price     :");
        add.add(itemName); add.add(itemNameField);
        //add.add(itemNumber); add.add(itemNumberField);
        add.add(rating); add.add(ratingField);
        add.add(quantity); add.add(quantityField);
        add.add(price); add.add(priceField);
        panel.add(add);
        JPanel forAddIt = new JPanel();
        forAddIt.add(addIt);
        panel.add(forAddIt);
        displayHolder.remove(display);
        displayHolder.add(panel);
        //display = panel;
        this.setVisible(true);
    }

    public static void main( String args[]) {
        Inventory1 object = new Inventory1(); //The main method should not have too much code
    } // end main method
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand(); //This retrieves the command that we set for the button
        //Always compare strings using the .equals method and not using ==
        if(command.equals("First")) {
            displayFirst();
        }
        else if(command.equals("Next")) {
            displayNext();
        }
        else if(command.equals("Previous")) {
            displayPrevious();
        }
        else if(command.equals("Last")) {
            displayLast();
        }
        else if(command.equals("Exit")) {
            this.dispose();
            System.exit(0);
        }
        else if(command.equals("Add")) {
            addItem();
        }
        else if(command.equals("Add Item")) {
            addItemToArray();
        }

    }
    private void addItemToArray() {
        Product p = new DVDTitle(itemNameField.getText(), supplies.length -2, Long.parseLong(quantityField.getText()),
                Double.parseDouble(priceField.getText()), ratingField.getText());
        //Extend size of array by one first
        Product[] ps = new Product[supplies.length + 1];
        for(int i = 0; i < ps.length-1; i++) {
            ps[i] = supplies[i];
        }
        ps[supplies.length] = p;
        supplies = ps;
        displayHolder.remove(panel);
        displayHolder.add(display);
        displayLast();
        this.setVisible(false);
        this.setVisible(true);
    }
    //Utility method to ease the typing and reuse code
    //This method reduces the number of lines of our code
    private void displayItemAt(int index) {
        DVDTitle product = (DVDTitle)supplies[index];
        itemName.setText("Item Name: "+ product.getItemName());
        itemNumber.setText("Item Number: "+ product.getItemNumber());
        rating.setText("Rating: "+ product.getRating());
        quantity.setText("Quantity In Stock: "+ product.getStockQuantity());
        price.setText("Item Price: "+ product.getItemPrice());
        totalValue.setText("Total: " + product.calculateInventoryValue());
        fee.setText("Fee :"+product.calculateRestockFee());
        this.setVisible(true);
    }
    public void displayFirst() {
        displayItemAt(0);
        currentIndex = 0;
    }
    public void displayNext() {
        if(currentIndex == supplies.length-1) {
            displayFirst();
            currentIndex = 0;
        }
        else {
            displayItemAt(currentIndex + 1);
            currentIndex++;
        }
    }
    public void displayPrevious() {
        if(currentIndex == 0) {
            displayLast();
            currentIndex = supplies.length-1;
        }
        else {
            displayItemAt(currentIndex - 1);
            currentIndex--;
        }
    }
    public void displayLast() {
        displayItemAt(supplies.length-1);
        currentIndex = supplies.length-1;
    }
}//end class Inventory2.1