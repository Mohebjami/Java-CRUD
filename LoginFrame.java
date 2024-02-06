import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

class LoginFrame
{
    JTextField tf1=null;
    JPasswordField tf2 = null;
    JFrame frm =null;
    LoginFrame()
    {
    action ac = new action();
     frm = new JFrame("Login page");
    JLabel txt = new JLabel("MOHEB DRUG STORE");
    txt.setFont(new Font("Segoe Print", Font.BOLD, 18));
    txt .setBounds(370,10,290,50);
    frm.add(txt);
    JLabel lb1  = new JLabel("User Name:");
    lb1.setBounds(350,80,100,20);
    lb1.setFont(new Font("Segoe Print", Font.BOLD, 15));

    JLabel lb2  = new JLabel("Pass Word:");
    lb2.setBounds(350,160,100,30);
    lb2.setFont(new Font("Segoe Print", Font.BOLD, 15));

    tf1 = new JTextField();
    tf1.setBounds(350,110,280,35);
    tf1.setFont(new Font("Segoe Print", Font.BOLD, 15));

    tf2 =new JPasswordField();
    tf2.setFont(new Font("", Font.BOLD, 15));
    tf2.setBounds(350,190,280,35);


    JButton bt1 = new JButton("Save");
    bt1.setBounds(380,260,90,40);
    bt1.setFont(new Font("", Font.BOLD, 15));
    bt1.addActionListener(ac);

    JButton bt2 = new JButton("Cancel");
    bt2.setBounds(500,260,90,40);
    bt2.setFont(new Font("", Font.BOLD, 15));
    bt2.addActionListener(ac);

          // add image in JFrame:
    JLabel lb4 = new JLabel("");
    Image img = new ImageIcon(this.getClass().getResource("img.jpg")).getImage();
    lb4.setIcon(new ImageIcon(img));
    lb4.setBounds(20,50,300,260);
    frm.getContentPane().add(lb4);

    frm.add(lb1);
    frm.add(bt1);
    frm.add(tf1);
    frm.add(lb2);
    frm.add(bt2);
    frm.add(tf2);
    frm.setBounds(300,180,700,400);
    frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frm.setLayout(null);
    frm.setVisible(true);
  }

class action implements ActionListener
{
  Connection con =null;
  ResultSet rs = null;
  PreparedStatement pst = null;
  String pass =null;
  String name=null;
  public void actionPerformed(ActionEvent a)
  {
    if (a.getActionCommand().equals("Save"))
    {
                 frm.setVisible(false);
                 CustomerDetalis fu = new CustomerDetalis();
            try{
              Class.forName("com.mysql.cj.jdbc.Driver");
              con = DriverManager.getConnection("jdbc:mysql://localhost:3308/LoginFrame","root","");

              if (con!=null)
              {
                name = tf1.getText();
                 pass = tf2.getText();

                            // if there isn't data its login

                 if(tf1.getText().equals(""))
                  {
                    JOptionPane.showMessageDialog(null,"Invalid password. try agen");
                  }
              else
                {
                String query ="select * from LoginFrame where Username = ? and password=?";
                try{
                    pst= con.prepareStatement(query);
                    pst.setString(1,name);
                    pst.setString(2,pass);
                    rs=pst.executeQuery();

                   if (rs.next()) {
                            frm.setVisible(false);
                          CustomerDetalis mp  =new CustomerDetalis();
                   }
                   else
                   {
                     JOptionPane.showMessageDialog(null,"Invalid password. try agen");
                    }
                }
                catch (Exception e) {
                  System.out.print("finaly catch");
                }
              }

            }


            
            }
            catch(ClassNotFoundException found)
            {
              System.out.println("Sorry the class is not there");
              JOptionPane.showMessageDialog(null,"Sorry the class is not there");
            }
           catch(SQLException sqlexp)
            {
                // System.out.println("there is some SQLException");
                JOptionPane.showMessageDialog(null,"there is some SQLException");
            }
            catch (Exception e) {
              System.out.print("s");
            }
                                                  // Dont accept con
                finally
                {
                    try{
                      con.close();
                        }
                        catch(SQLException sqlexp2)
                        {
                          System.out.println("Some probelm in closing");
                        }
                        catch (Exception e) {
                          JOptionPane.showMessageDialog(null,"Exception");
                        }
                }
      }
      else if(a.getActionCommand().equals("Cancel"))
      {
        System.exit(0);
      }
    }
  }
}
