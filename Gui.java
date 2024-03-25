import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;



public class Gui extends WestminsterShoppingManager implements ActionListener {

    JComboBox comboBox;
    JButton button1;
    DefaultTableModel table1;


    public void gui() {

        JFrame gui = new JFrame("Westminster Shopping Center");
        Demo_handler handler = new Demo_handler();
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        p1.add(new JLabel("Select Product Category:"));

        String[] category = { "All", "Electronics", "Clothing" };
        comboBox = new JComboBox(category);
        comboBox.setBounds(270, 20, 90, 40);
        comboBox.addActionListener(this);
        p1.add(comboBox);

        button1 = new JButton("Shopping Cart");
        button1.addActionListener(handler);
        p1.add(button1);
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER));
        table1 = new DefaultTableModel();
        


        JTable info = new JTable(table1);
        table1.addColumn("Product ID");
        table1.addColumn("Name");
        table1.addColumn("Category");
        table1.addColumn("Price");
        table1.addColumn("Info");

        for (Product p : productList) {
            if (p.getProduct_type().equals("Electronics")) {
                Object[] rowData = {p.getProduct_id(), p.getProductName(), "Electronics", p.getPriceProduct()};
                table1.addRow(rowData);
            } else if (p.getProduct_type().equals("Clothing")) {
                Object[] rowData = {p.getProduct_id(), p.getProductName(), "Clothing", p.getPriceProduct()};
                table1.addRow(rowData);

            }
        }


        p2.add(new JScrollPane(info));


        JPanel p3 = new JPanel(new GridLayout(1, 1, 10, 10));
        JTextField tf1 = new JTextField(productList.toString().replace("[","\n").replace("]","\n"));
        p3.add(tf1);

        JPanel p5 = new JPanel(new BorderLayout());
        p5.add(p2, BorderLayout.NORTH);
        p5.add(p3, BorderLayout.CENTER);

        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JButton button2 = new JButton("ADD ");
        p4.add(button2);


        gui.add(p1, BorderLayout.NORTH);
        gui.add(p5, BorderLayout.CENTER);
        gui.add(p4, BorderLayout.SOUTH);


        gui.setVisible(true);
        gui.setSize(200, 200);

    }





            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() ==comboBox ) {
                    table1.setRowCount(0);

                    String selectedCategory = comboBox.getSelectedItem().toString();

                    for (Product p : productList) {

                        String type;

                        if (selectedCategory.equals("All")) {
                            // Display all products
                            if (p instanceof Electronics) {
                                type = "Electronics";

                            } else if (p instanceof Clothing) {
                                type = "Clothing";
                            } else {
                                type = "unknown";
                            }
                        } else if (selectedCategory.equals("Electronics") && p instanceof Electronics) {

                            type = "Electronics";

                        } else if (selectedCategory.equals("Clothing") && p instanceof Clothing) {

                            type = "Clothing";
                        } else {

                            continue;
                        }
                        Object[] rowData = {p.getProduct_id(), p.getProductName(), type, p.getPriceProduct(), p.getAvailableQuantity()};
                        table1.addRow(rowData);






                    }


                    }
                }
            }


class Demo_handler implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            JFrame frame1 = new JFrame("Shopping Cart");
            JPanel c1 = new JPanel();
            c1.setLayout(new FlowLayout(FlowLayout.CENTER));
            Product[][] data = {};
            String[] Column = {"Product", "Quantity", "Price"};
            JTable jt = new JTable(data, Column);
            c1.add(new JScrollPane(jt));
            ShoppingCart sc=new ShoppingCart();
            JPanel c2 = new JPanel(new GridLayout(1, 2, 5, 5));
            JTextField tf2 = new JTextField();
            c2.add(tf2);
            frame1.add(c1, BorderLayout.NORTH);
            frame1.add(c2, BorderLayout.CENTER);
            frame1.setVisible(true);
            frame1.setSize(200, 200);


        }

    }













