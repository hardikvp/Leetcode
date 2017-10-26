import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.FileReader;
import java.io.IOException;
/**
 * Code for Swing GUI for Student Directory list.
 * @author hardikvp
 */
public class DirectoryDriver extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    /**
     * Text field for first Name.
     */
    private JTextField firstName;
    /**
     * Text field for Last Name.
     */
    private JTextField lastName;
    /**
     * Text field for Andrew id.
     */
    private JTextField andrewid;
    /**
     * Text field for Andrew ID.
     */
    private JTextField andrewID;
    /**
     * Text field for Phone Number.
     */
    private JTextField phoneNumber;
    /**
     * Text field for Search.
     */
    private JTextField searchkey;
    /**
     * Text field for results.
     */
    private JTextArea results;
    /**
     * Buttons for all fields.
     */
    private JButton add, delete, byAndrewID, byFirstName, byLastName;
    /**
     * Constructor Directory Driver taking no parameters.
     */
    public DirectoryDriver() {
        JFrame window = new JFrame("Student Directory");
        window.setSize(650, 550);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        Font newFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);

        JLabel a = new JLabel("Add a new student                "
                + "                                             "
                + "                                             "
                + "                                                 ");
        a.setFont(newFont);
        panel.add(a);

        JLabel label1 = new JLabel("First Name :");
        panel.add(label1);
        firstName = new JTextField(4);
        panel.add(firstName);

        JLabel label2 = new JLabel("Last Name :");
        panel.add(label2);
        lastName = new JTextField(4);
        panel.add(lastName);

        JLabel label3 = new JLabel("AndrewID :");
        panel.add(label3);
        andrewid = new JTextField(4);
        panel.add(andrewid);

        JLabel label4 = new JLabel("Phone Number :");
        panel.add(label4);
        phoneNumber = new JTextField(4);
        panel.add(phoneNumber);

        add = new JButton("Add");
        add.addActionListener(this);
        panel.add(add);

        JLabel b = new JLabel("Delete a student             "
                + "                                         "
                + "                                         "
                + "                                         "
                + "                        ");
        b.setFont(newFont);
        panel.add(b);

        JLabel label11 = new JLabel("Andrew ID :");
        panel.add(label11);
        andrewID = new JTextField(8);
        panel.add(andrewID);
        delete = new JButton("Delete");
        delete.addActionListener(this);
        panel.add(delete);
        JLabel label22 = new JLabel("                     "
                + "                                       "
                + "                                       "
                + "             ");
        panel.add(label22);

        JLabel c = new JLabel("Search student(s)            "
                + "                                         "
                + "                                         "
                + "                                                              ");
        c.setFont(newFont);
        panel.add(c);
        JLabel label23 = new JLabel("Search Key");
        panel.add(label23);
        searchkey = new JTextField(14);
        searchkey.addActionListener(this);
        panel.add(searchkey);

        byAndrewID = new JButton("By AndrewID");
        byAndrewID.addActionListener(this);
        panel.add(byAndrewID);

        byFirstName = new JButton("By First Name");
        byFirstName.addActionListener(this);
        panel.add(byFirstName);

        byLastName = new JButton("By Last Name");
        byLastName.addActionListener(this);
        panel.add(byLastName);

        JLabel e = new JLabel("Results                      "
                + "                                         "
                + "                                         "
                + "                                                               ");
        e.setFont(newFont);
        panel.add(e);
        results = new JTextArea(15, 50);
        JScrollPane scroller = new JScrollPane(results);
        panel.add(scroller);

        window.setContentPane(panel);
        window.setVisible(true);
        }
    /**
     * Method to add actions to all the buttons, text fields and text areas in the Directory GUI.
     */
    private static Directory d = new Directory();
    /**
     *
     * @param event performs all Action Event.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == add) {
            if ((firstName.getText().isEmpty())) {
                    results.append("First Name Missing!!\n");
                    } else if (lastName.getText().isEmpty()) {
                        results.append("Last Name missing!!\n");
                        } else if (andrewid.getText().isEmpty()) {
                            results.append("Andrew ID missing!!\n");
                            } else if (d.searchByAndrewId(andrewid.getText()) != null) {
                                results.setText("AndrewID already exists!\n");
                                }
            if (!firstName.getText().isEmpty() && !lastName.getText().isEmpty() &&  !andrewid.getText().isEmpty()) {
                Student first = new Student(andrewid.getText());
                first.setFirstName(firstName.getText());
                first.setLastName(lastName.getText());
                if (!(phoneNumber.getText().isEmpty())) {
                    first.setPhoneNumber(phoneNumber.getText());
                }
                d.addStudent(first);
                results.append("New Entry has been Added\n");
                firstName.setText("");
                lastName.setText("");
                andrewid.setText("");
                phoneNumber.setText("");
                return;
                }
        }
        if (event.getSource() == delete) {
            if (andrewID.getText().isEmpty()) {
                results.append("Data Missing!\n");
            } else {
                d.searchByAndrewId(andrewID.getText());
                if (d.searchByAndrewId(andrewID.getText()) == null) {
                    results.append(("The following AndrewID does not exist\n"));
                } else {
                    results.append("Student Deleted\n");
                    results.append(d.searchByAndrewId(andrewID.getText()).toString());
                    d.deleteStudent(andrewID.getText());
                    andrewID.setText("");
                    return;
                    }
                }
            }
        if (event.getSource() == searchkey) {
            if (searchkey.getText().isEmpty()) {
                results.append("No field entry made!\n");
            } else {
                 if (d.searchByAndrewId(searchkey.getText()) == null) {
                     results.append(("Andrew ID not found\n"));
                 } else {
                     String s = d.searchByAndrewId(searchkey.getText()).toString();
                     results.append(s + "\n");
                     searchkey.setText("");
                     return;
                 }
            }
        }
        if (event.getSource() == byFirstName) {
            if (searchkey.getText().isEmpty()) {
                results.append("No field Entry Made!\n");
            }
            if (!(searchkey.getText().isEmpty())) {
                if (d.searchByFirstName(searchkey.getText()).isEmpty()) {
                     results.append(("No matches for the following First Name search: " + searchkey.getText() + "\n"));
                 } else {
                     for (int x = 0; x < (d.searchByFirstName(searchkey.getText())).size(); x++) {
                         String a = d.searchByFirstName(searchkey.getText()).get(x).toString();
                         results.append(a + "\n");
                         }
                     searchkey.setText("");
                     return;
                 }
            }
        }
        if (event.getSource() == byLastName) {
            if (searchkey.getText().isEmpty()) {
                results.append("No field Entry Made!\n");
            }
            if (!(searchkey.getText().isEmpty())) {
                 if (d.searchByLastName(searchkey.getText()).isEmpty()) {
                     results.append(("No matches for the following Last Name search: " + searchkey.getText() + "\n"));
                 } else {
                     for (int x = 0; x < (d.searchByLastName(searchkey.getText())).size(); x++) {
                         String b = d.searchByLastName(searchkey.getText()).get(x).toString();
                         results.append(b + "\n");
                         }
                     searchkey.setText("");
                     return;
                 }
            }
        }
    }
    /**
     * Main Method.
     * @param args Command line input.
     * @throws IOException exception catching.
     */
    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
            FileReader fr = new FileReader(args[0]);
            CSVReader c = new CSVReader(fr);
            boolean eof = false;
            int count = 0;

            while (!eof) {
                if (count == 0) {
                    count += 1;
                } else {
                    String[] values = c.readCSVLine();
                    if (values == null) {
                        eof = true;
                    } else {
                        Student s = new Student(values[2]);
                        s.setFirstName(values[0]);
                        s.setLastName(values[1]);
                        s.setPhoneNumber(values[3]);
                        d.addStudent(s);
                    }
                }
            }
            c.close();
        }
        // instantiation
        new DirectoryDriver();
    }
}
