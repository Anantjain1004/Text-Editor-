import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class SimpleTextEditor implements ActionListener {

    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File; //menubar headings
    JMenu Edit;
    JMenu Close;
    //file
    JMenuItem NewFile;
    JMenuItem OpenFile;
    JMenuItem SaveFile;
    JMenuItem PrintFile;

    //edit
    JMenuItem Cut;
    JMenuItem Copy;
    JMenuItem Paste;

    //Close
    JMenuItem CloseEditor;

    SimpleTextEditor(){
        //creating the frame
        frame = new JFrame("Simple Text Editor");
        frame.setBounds(0,0,800,1000);   //setting frame size

        //Initializing text area
        jTextArea = new JTextArea("Welcome to the editor");

        //Initializing menuBar
        jMenuBar = new JMenuBar();

        //Initializing menubar elements
        Close = new JMenu("Close");
        Edit = new JMenu("Edit");
        File = new JMenu("File");
        //adding elements to menubar
        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);

        // Initializing for file
        NewFile = new JMenuItem("New");
        NewFile.addActionListener(this);
        OpenFile = new JMenuItem("Open");
        OpenFile.addActionListener(this);
        SaveFile = new JMenuItem("Save");
        SaveFile.addActionListener(this);
        PrintFile = new JMenuItem("Print");
        PrintFile.addActionListener(this);


        //Initializing for edit
        Cut = new JMenuItem("Cut");
        Cut.addActionListener(this);
        Copy = new JMenuItem("Copy");
        Copy.addActionListener(this);
        Paste = new JMenuItem("Paste");
        Paste.addActionListener(this);

        // Initializing for Close
        CloseEditor = new JMenuItem("Close Editor");
        CloseEditor.addActionListener(this);




        //adding menuItems for file
        File.add(NewFile); //adding menuItems
        File.add(OpenFile);
        File.add(SaveFile);
        File.add(PrintFile);

        //adding menuItems for edit
        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);

        //adding menuItems for edit
         Close.add(CloseEditor);

         //adding menubar
        frame.setJMenuBar(jMenuBar);
        frame.add(jTextArea);  //adding textarea to the frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//to terminate the programme
        frame.setVisible(true); //making it visible
    }
    public static void main(String[] args) {
        SimpleTextEditor editor = new SimpleTextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();//here we are getting these strings and on the basis of this we initiate our programme
        if (s.equals("Copy")) {
            jTextArea.copy();//here we are copying
        } else if (s.equals("Cut")) {
            jTextArea.cut();//here we are cutting the text
        } else if (s.equals("Paste")) {
            jTextArea.paste();//here we are pasting the text
        } else if (s.equals("Print")) {
            //we are using try and catch so that if printer not available then it will show error.
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        } else if (s.equals("New")) {
            jTextArea.setText("");//here we are creating new file by deleting all the previous data
        } else if (s.equals("Close Editor")) {
//        frame.setVisible(false);
            System.exit(1);//here we are exiting from the code
        }else if(s.equals("Open")) //with this function we will open file
        {
            JFileChooser jFileChooser=new JFileChooser("C:");//we are choosing the file from directory

            int ans=jFileChooser.showOpenDialog(null); // here we are getting two options either of selecting or cancelling
            if(ans==JFileChooser.APPROVE_OPTION){ //if we open the file then-->
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());//getting the path of file
                String s1="",s2=""; //we will copy the data of file in these strings
                try {
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(file));//reading the file with this
                    s2=bufferedReader.readLine(); //reading the first line
                    while((s1=bufferedReader.readLine())!=null) //if more than one line we will read other lines
                    {
                        s2+=s1+"\n";//copying data of s1 inn s2..
                    }
                    jTextArea.setText(s2); //pasting the data inn s2
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if(s.equals("Save"))
        {
            JFileChooser jFileChooser=new JFileChooser("C:");//we are choosing the file from directory
            int ans=jFileChooser.showOpenDialog(null);// here we are getting two options either of selecting or cancelling
            if(ans==jFileChooser.APPROVE_OPTION)//if we open the file then-->
            {
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());//getting the path of selected file
                BufferedWriter writer=null; //initialising writer with null
                try {
                    writer=new BufferedWriter(new FileWriter(file,false));//getting the access of file
                    writer.write((jTextArea.getText()));//here we are writing the text
                    writer.flush();//finishing the writer
                    writer.close();//closing the writer
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}