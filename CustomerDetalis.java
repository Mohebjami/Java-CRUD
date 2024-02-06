import javax.swing.*;
import java.awt.Color;
import  java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
class CustomerDetalis
{
  // tbale varibles
  String name,address,contact = null;
  JScrollPane pane = null;
  action ac = new action();
  JFrame frm =null;
  JButton bt = null;
  JButton bt1 = null;
  JButton bt2 = null;
  JButton bt3 = null;
  JButton bt4= null;
  JButton bt6= null;
  JButton bt5= null;
  JTextField tf = null;
  JTextField tf1 = null;
  JTextField tf2 = null;
DefaultTableModel model=null;
  // Connection object
Connection con =null;
ResultSet res = null;
PreparedStatement pst = null;
Statement st = null;
String name1 =null;
String add1 =null;
String cont1 =null;
String getname =null;
String getaddress =null;
String getcontect =null;

// MouseListener me = new MouseListener();
  JTable table =null;
  CustomerDetalis()
  {
     frm = new JFrame("Customer Detalis");
    JPanel pnl = new JPanel();
    Color co = new Color(203,226,240);
    pnl.setBackground(co);
    pnl.setBounds(0,0,1280,30);
    pnl.setLayout(null);
    frm.add(pnl);
                                                                          // JLabel in head JPanel
    JLabel lbpnl = new JLabel("Customer Detalis ");
    lbpnl.setBounds(560,1,300,30);
    lbpnl.setFont(new Font("", Font.BOLD, 18));
    pnl.add(lbpnl);
                                                                            // JLabel
    JLabel lb = new JLabel("Customer Name :");
    lb.setBounds(40,60,130,30);
    lb.setFont(new Font("Calibri",Font.BOLD,17));
    frm.add(lb);
    JLabel lb1 = new JLabel("Customer Address :");
    lb1.setBounds(440,60,180,30);
    lb1.setFont(new Font("Calibri",Font.BOLD,17));
    frm.add(lb1);
    JLabel lb2 = new JLabel("Customer Contact :");
    lb2.setFont(new Font("Calibri",Font.BOLD,17));
    lb2.setBounds(840,60,220,30);
    frm.add(lb2);
                                                                            // JTextFields
    tf = new JTextField();
    tf.setBounds(165,62,250,28);
    tf.setFont(new Font("Calibri",Font.BOLD,17));
    frm.add(tf);
    tf.addActionListener(ac);
    tf.setEnabled(false);
    tf1 = new JTextField();
    tf1.setBounds(580,62,250,28);
    tf1.setFont(new Font("Calibri",Font.BOLD,17));
    frm.add(tf1);
    tf1.addActionListener(ac);
    tf1.setEnabled(false);
    tf2 = new JTextField();
    tf2.setBounds(980,60,250,28);
    tf2.setFont(new Font("Calibri",Font.BOLD,17));
    frm.add(tf2);
    tf2.addActionListener(ac);
    tf2.setEnabled(false);
                                                                                      // JButtons
    bt = new JButton("New");
    bt.setBounds(120,170,110,45);
    bt.setFont(new Font("",Font.BOLD,15));
    frm.add(bt);
    bt.addActionListener(ac);
    bt1 = new JButton("Save");
    bt1.setBounds(120,240,110,45);
    bt1.setFont(new Font("",Font.BOLD,15));
    frm.add(bt1);
    bt1.setEnabled(false);
    bt1.addActionListener(ac);
    bt2= new JButton("Delete");
    bt2.setBounds(120,310,110,45);
    bt2.setFont(new Font("",Font.BOLD,15));
    frm.add(bt2);
    bt2.addActionListener(ac);
    // bt2.setEnabled(false);
    bt3 = new JButton("Reset");
    bt3.setBounds(120,380,110,45);
    bt3.setFont(new Font("",Font.BOLD,15));
    frm.add(bt3);
    bt3.addActionListener(ac);

    bt5 = new JButton("Edit");
    bt5.setBounds(120,450,110,45);
    bt5.setFont(new Font("",Font.BOLD,15));
    frm.add(bt5);
    bt5.addActionListener(ac);
    bt6 = new JButton("Back");
    bt6.setBounds(120,520,110,45);
    bt6.setFont(new Font("",Font.BOLD,15));
    frm.add(bt6);
    bt6.addActionListener(ac);



                                                                            // DefaultTableModel
     model = new DefaultTableModel();
    table = new JTable(model);
    table.addMouseListener(ac );
    pane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    pane.setBounds(320,170,910,470);
    model.addColumn("NAME");
    model.addColumn("ADDRESS");
    model.addColumn("CONTACT");
    pane.setFont(new Font("",Font.BOLD,25));
    model.addRow(new Object[]{name,address,contact});

      try
      {
              Class.forName("com.mysql.cj.jdbc.Driver");
               con = DriverManager.getConnection("jdbc:mysql://localhost:3308/customerdetalis","root","");
              String selectquery = "select name, address, contect from customerdetalis";
               st = con.createStatement();
             res  = st.executeQuery(selectquery);

              while(res.next()!=false)
              {
                // put all the data into the java table
                getname =  res.getString("name");
                getaddress = res.getString("address");
                getcontect =  res.getString("contect");
                if (getname == null) {

                }
                model.addRow(new Object[]{getname,getaddress,getcontect});

              }
        }
        catch(Exception ff)
        {
          System.out.print("Ther is an Exception in one");
        }

    frm.add(pane);
    frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frm.setLayout(null);
    frm.setVisible(true);
  }




  class action implements ActionListener,MouseListener

  {
    int ind = 0;
    String name = null;
    public  void mouseClicked(MouseEvent me){
      ind = table.getSelectedRow();
      name = model.getValueAt(ind,0).toString();


    }
     public void mousePressed(MouseEvent me){}
     public void mouseReleased(MouseEvent me){}
     public void mouseEntered(MouseEvent me){}
     public void mouseExited(MouseEvent me){}




    Connection con =null;
    ResultSet res = null;
    PreparedStatement pst = null;
    Statement st = null;
    String name1 =null;
    String add1 =null;
    String cont1 =null;
    String getname =null;
    String getaddress =null;
    String getcontect =null;



      public void actionPerformed(ActionEvent a)
      {



  			try
  			{
          Class.forName("com.mysql.cj.jdbc.Driver");
         con = DriverManager.getConnection("jdbc:mysql://localhost:3308/customerdetalis","root","");

  				if(con!=null)
  				{
                        Object obj = a.getSource();


                      if (a.getActionCommand().equals("New"))
                        {
                                bt1.setEnabled(true);
                                tf.setEnabled(true);
                                tf1.setEnabled(true);
                                tf2.setEnabled(true);
                        }   //newButton
                        else if (a.getActionCommand().equals("Save"))
                        {
                          try
                                {
                                            // name1 == name of TextField tf or CustomerName
                                            // add1 == this is  the name of TextField Address or tf 2

                                            name1 =tf.getText();
                                            add1 =tf1.getText();
                                            cont1 =tf2.getText();
                                            model.addRow(new Object[]{name1,add1,cont1});
                                                                                    // this is my query for insert data to database
                            								String query ="insert into customerdetalis values(?,?,?)";
                            								pst = con.prepareStatement(query);
                            								pst.setString(1,name1);
                            								pst.setString(2,add1);
                            								pst.setString(3,cont1);

                            								if(tf.getText().equals("") && tf1.getText().equals("") && tf2.getText().equals(""))
                            								{
                            									// if JTextField == null

                            								   JOptionPane.showMessageDialog(null,"Sorry, no data is inserted....");
                            									String query1 ="DELETE FROM customerdetalis WHERE name =''";
                            								}
                            								else
                            								{
                            								  int  rs = pst.executeUpdate();
                            								  if( rs== 0)
                            									System.out.print("Sorry, no data is inserted....");
                            								  else
                                              {
                                              tf.setText("");
                                              tf1.setText("");
                                              tf2.setText("");
                            									JOptionPane.showMessageDialog(null,"your data successfully inserted");
                                              }
                            								}
                                     }     //try
                                     catch(SQLException f)
                                     {
                                       System.out.println("SQLException");
                                     }
  						                       catch(Exception b)
  						                        {
  							                            System.out.println("Exception");
  					                         }
                          }
                        else if (a.getActionCommand().equals("Delete"))
                        {
                          int x =JOptionPane.showConfirmDialog(frm,"Are you sure");
                          if(x ==0){
                            String query2 = " delete from customerdetalis where name =?";
                            pst = con.prepareStatement(query2);
                              pst.setString(1,name);
                              int res = pst.executeUpdate();
                              if (res != 0) {
                                model.removeRow(ind);
                                JOptionPane.showMessageDialog(frm,"Your data is deleted");
                              }
                            }
                        }
                        else if (a.getActionCommand().equals("Edit"))
                       {
                         String nameobj = model.getValueAt(ind,0).toString();
                         String addobj = model.getValueAt(ind,1).toString();
                         String contactobj = model.getValueAt(ind,2).toString();
                         tf.setText(nameobj);
                         tf1.setText(addobj);
                         tf2.setText(contactobj);
                         model.removeRow(ind);
                         tf.setEnabled(true);
                         tf1.setEnabled(true);
                         tf2.setEnabled(true);
                          int edit = JOptionPane.showConfirmDialog(frm,"Are you sure for editing?");
                          if (edit == 0)
                          {
                            String delete2 = " delete from customerdetalis where name =?";
                            pst =con.prepareStatement(delete2);
                            pst.setString(1,name);
                            int res = pst.executeUpdate();
                            if (res != 0) {
                              model.removeRow(ind);
                            }
                            if(tf.getText().equals("") && tf1.getText().equals("") && tf2.getText().equals(""))
                            {
                              // if JTextField == null

                               JOptionPane.showMessageDialog(null,"Sorry, no data is inserted....");
                              String query1 ="DELETE FROM customerdetalis WHERE name =''";
                            }
                          }
                        }
                        else if (a.getActionCommand().equals("Reset"))
                         {
                             int ok =  JOptionPane.showConfirmDialog(null,"Are you sure delete all your data ?");
                             String reset = " delete from customerdetalis";
                             pst = con.prepareStatement(reset);
                             int res = pst.executeUpdate();
                              if (res != 0) {
                                if (ok == 0) {
                                  model.setRowCount(0);
                                  model.getDataVector(). removeAllElements();
                                }


                              }
                        }
                        else if (a.getActionCommand().equals("Back"))
                       {
                       
                        }
            }
            else
            {
            System.out.println("Sorry, you are not connected");
            }
          }

  			catch(ClassNotFoundException found)
  			{
  			System.out.println("Sorry the class is not there");
  			}
  			catch(SQLException sqlexp)
  			{
  			System.out.println("there is some SQLException");
  			}
      }
  }






}  //main class
