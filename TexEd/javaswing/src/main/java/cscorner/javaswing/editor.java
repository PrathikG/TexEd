/*
SAVE AS editor.java
 */
package cscorner.javaswing;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class editor {

    JTextArea t;
    JSpinner fontSizeSpinner;
    JLabel fontLabel;
    JFrame f;
    JMenuBar menubar;
    JScrollPane scrollPane;
    JComboBox fontBox;
    JButton fontColorButton;
    JRadioButton nightButton, dayButton;
    JToggleButton toggleButton;
    JMenu file, info;
    JMenuItem saveicon, Edit, about, New, save, exit,open, cut, copy, paste, selectall;

//----- Constructor
    editor() {

//----- Creating  a FRAME
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Tex Ed");
        f.setSize(1000, 596);               //frame of size 1000 in width and 596 in height is created
        f.setResizable(false);
        f.setLayout(new FlowLayout());
        f.setVisible(true);

//----- Text component JTEXTAREA
        t = new JTextArea();              //A JTextArea is a multi-line area that displays plain text                                                                      
        t.setLineWrap(true);             //text goes to next line after certain limit
        t.setWrapStyleWord(true);//Sets the style of wrapping used if the text area is wrapping lines. If set to true the lines will be wrapped at word boundaries (whitespace)
        //if they are too long to fit within the allocated width.
        //If set to false, the lines will be wrapped at character boundaries. By default this property is false.
        t.setFont(new Font("Arial", Font.PLAIN, 20)); // Default text font

//-----THEMES -- Radio Button:radio button is used to choose one option from multiple options
        nightButton = new JRadioButton("Dark");
        dayButton = new JRadioButton("Light");
        dayButton.setSelected(true);
        ButtonGroup themes = new ButtonGroup();
        themes.add(nightButton);
        themes.add(dayButton);

//-----T O G G L E BUTTON:it is two-states button.
        toggleButton = new JToggleButton("BOLD");   //ToggleButtons created with name BOLD

//-----JSCROLLPANE provides a scrollable view of a component.
        scrollPane = new JScrollPane(t);
        scrollPane.setPreferredSize(new Dimension(980, 490));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);   //sets a vertical scrollbar
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED); //sets a horizontal scrollbar

//----- Creating a MENUBAR
        menubar = new JMenuBar();           //JMenuBar is an implementation of menu bar

        file = new JMenu("File");
        New = new JMenuItem("New");
        save = new JMenuItem("Save");
        open = new JMenuItem("Open");       //file menu
        exit = new JMenuItem("Exit");

        file.add(New);              //To add menu items and submenus to a JMenu , we use the add(New)method.
        file.add(save);    
        file.add(open);        //a new menu item "save" is added to main menu "file"
        file.add(exit);           // a new menu item "exit" is added to main menu "file"

//----- Create submenu for menu
        info = new JMenu("Info");
        about = new JMenuItem("About");
        info.add(about);

        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JTextField txt = new JTextField("This editor is created by Uvais,Prathik,Krithik and sachit "
                        + "as a part of their miniproject ");
                txt.setEnabled(false);
                txt.setDisabledTextColor(Color.black);
                JOptionPane.showMessageDialog(f, txt);
            }
        });

// -----Create a submenu for menu
        Edit = new JMenu("Edit");

// -----Create menu items
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectall = new JMenuItem("Select all");

        Edit.add(cut);                //a new menu item "cut " is added to menu field "Edit"
        Edit.add(copy);               //a new menu item"copy" is added to menu field "Edit"    
        Edit.add(paste);             //a new menu item "paste" is added to menu field "Edit"
        Edit.add(selectall);        //a new menu item "selectall" is added to menu field"Edit"

        menubar.add(file);
        menubar.add(Edit);
        menubar.add(info);

//-----adding PNG Logos to menuItems
        save.setIcon(new ImageIcon("D:\\s.png"));
        New.setIcon(new ImageIcon("D:\\n.png"));
        exit.setIcon(new ImageIcon("D:\\e.png"));
        open.setIcon(new ImageIcon("D:\\o.png"));

//-----JLABEL for font size spinner
        fontLabel = new JLabel("Font: ");

//-----FONT SIZE SPINNER
        fontSizeSpinner = new JSpinner();                          //creating font size spinner
        fontSizeSpinner.setPreferredSize(new Dimension(50, 25));  //setting default dimensions
        fontSizeSpinner.setValue(20);                             //default font size
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) { //passing an anonymous change listnr  & import
                //add unimplemented method
                t.setFont(new Font(t.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue()));
            }               //keeping font styling same//for 2nd argument seperate it with coma and set to plain
            //then getting size of the current size of the spinner then cast it as int
        });                 //finally adding to frame b4 scroolpane

//-----COLOR JBUTTON
        fontColorButton = new JButton("Color");         // adding color button with the help of JButton
        fontColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == fontColorButton) {
                    JColorChooser colorChooser = new JColorChooser();//creating new instance of jcolorChoser
                    //add imports
                    Color color = colorChooser.showDialog(null, "Choose a color", Color.black);
                    //creating a dialogBox--no parent componet(IDE does this) so set to null
                    // --Then give title-- then initial color(almost all of this is done by IDE auto)
                    t.setForeground(color); //changing current FontColor
                }
            }
        });

//-----font style using JCOMBOBOX
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        //getting all fonts available in Java and assigns to our array string named FONTS
        fontBox = new JComboBox(fonts);//passing array of fonts within the constructor of JComboBox
        fontBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == fontBox) {
                    t.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, t.getFont().getSize()));
                    //passing new font--only changing fontFamily--gets the item whatever we set fontbox to--
                    //-then cast it as string--nxt arg set to Plain--keep font size same as what it was previously
                    //so for 3rd arg well get current size(font) of text area
                    //this will maintain same font size
                }
            }
        });

        fontBox.setSelectedItem("Arial");  //default Font

//-----Adding contols to Jframe    
        f.setJMenuBar(menubar);
        f.add(nightButton);             //adding nightButton to JFrame
        f.add(dayButton);               //adding dayButton to JFrame
        f.add(toggleButton);            //adding toggleButton to JFrame
        f.add(fontLabel);               //adding fontLabel to JFrame
        f.add(fontSizeSpinner);         //adding fontSizeSpinner to JFrame
        f.add(fontColorButton);         //adding fontColorButton to JFrame
        f.add(fontBox);                 //adding fontBox to JFrame
        f.add(scrollPane);              //adding scrollPane to JFrame
        f.setVisible(true);             //makes frame visible
        
        
 //action listener for close       
         f.addWindowListener(new WindowListener()
                {
                    @Override
                    public void windowOpened(WindowEvent e) {
                         
                    }

                    @Override
                    public void windowClosing(WindowEvent e) {
                       int confirmExit = JOptionPane.showConfirmDialog(f,"Changes made may not be saved","Exit",JOptionPane.YES_NO_OPTION);
                         
                if (confirmExit == JOptionPane.YES_OPTION)
                    f.dispose();
                else if (confirmExit == JOptionPane.NO_OPTION)
                    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
           
                   
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                     
                    }

                    @Override
                    public void windowIconified(WindowEvent e) {
                       
                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {
                       
                    }

                    @Override
                    public void windowActivated(WindowEvent e) {
                     
                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {
                     
                    }
                   
                });

//-----action Listener for CUT option
        cut.addActionListener(new ActionListener() {           //registers cut component with the listener
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cut) {                     //Returns the object on which the event occurred
                    t.cut();
                }
            }
            // action listnr is an "interface", needs an obj of actn listnr interface , not a class          
            //we need to use our class which implements actn lstnr
            //expects something of the type actionlstnr but in our project v gave anonymous inner class
        });
//-----action Listener for COPY option//inner class
        copy.addActionListener(new ActionListener() {         //registers copy component with the listener
            public void actionPerformed(ActionEvent e) {//its the method thats called/executed when
                //someone presses a button
                if (e.getSource() == copy) {
                    t.copy();
                }
            }
        });
//-----action Listener for PASTE option
        paste.addActionListener(new ActionListener() {// add unimplemented method which is actnprfrmd
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == paste) {
                    t.paste();
                }
            }
        });
//-----action Listener for SELECTALL option
        selectall.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == selectall) {
                    t.selectAll();           //selects all the text in textArea
                }
            }
        });

//-----Action Listener for New menu
        New.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == New) {
                    t.setText("");             //cleares the contents of textArea and sets to Blank
                }
            }
        });

//-----Action Listener for EXIT
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exit) {
                    System.exit(0);              //when exit command is selected TexEd editor closes
                }
            }
        });

//----Action Listener for Dark Theme JRadioButton
        nightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == nightButton) {
                    t.setBackground(Color.DARK_GRAY);                 //sets textArea color to Dark Gray
                    t.setForeground(Color.WHITE);                     //sets text color to white
                }
            }
        });
//-----action Listener for Light mode JRadioButton
        dayButton.addActionListener(new ActionListener() {            //Adding ActionListener to Lightmode RadioButton
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == dayButton) {
                    t.setBackground(Color.WHITE);                     //sets textArea color to White
                    t.setForeground(Color.DARK_GRAY);                 //sets text color to Dark gray
                }
            }
        });
//-----adding ActionListener for JToggleButton
        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == toggleButton) {
                    if (toggleButton.isSelected()) {                    //when togglebutton is pressed once i.e selected
                        t.setFont(t.getFont().deriveFont(Font.BOLD));   //sets text font to BOLD
                    } else {                                           //when togglebutton is pressed again i.e deselected
                        t.setFont(t.getFont().deriveFont(Font.PLAIN)); //sets text font to PLAIN
                    }//does not change the font
                }//Creates a new Font object by replicating the current Font object and applying a new size to it.
            }
        });
        //----------adding action listener for OPEN
      //***********
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource()==open) {
					   JFileChooser fileChooser = new JFileChooser();
					   fileChooser.setCurrentDirectory(new File("."));
					   FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
					   fileChooser.setFileFilter(filter);
   
					   int response = fileChooser.showOpenDialog(null);
   
					   if(response == JFileChooser.APPROVE_OPTION) {
						   File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
						   Scanner fileIn = null;
    
						   try {
							   fileIn = new Scanner(file);
							   if(file.isFile()) {
								   while(fileIn.hasNextLine()) {
									   String line = fileIn.nextLine()+"\n";
									   t.append(line);
								   }
							   }
						   } catch (FileNotFoundException e1) {
     
							   e1.printStackTrace();
						   }
						   finally {
							   fileIn.close();
						   }
					   }
                 }
            }
                
            });
//-----adding ActionListener for SAVE
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == save) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));

                    int response = fileChooser.showSaveDialog(null);

                    if (response == JFileChooser.APPROVE_OPTION) {
                        File file;
                        PrintWriter fileOut = null;

                        file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                        try {
                            fileOut = new PrintWriter(file);
                            fileOut.println(t.getText());
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } finally {
                            fileOut.close();
                        }
                    }
                }
            }
        });
        
        
        
        
        
        
        
        
           
    }
}

    
