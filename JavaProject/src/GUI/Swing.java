package GUI;

import Functionality.TaskProcessing;
import Structure.Movies;
import com.sun.javafx.tk.*;
import com.sun.javafx.tk.Toolkit;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import static Functionality.TaskProcessing.test;
import static Structure.GeneratedMovies.movies;

/**
 * Created by Kristian on 09/11/2016.
 */
public class Swing implements ActionListener {
    private static JFrame mainFrame;
    private JPanel fieldPanel, listPanel, outputPanel;
    private JTextField txtIndex,txtTitle, txtGenre, txtCast, txtDirector,txtDuration,txtDate;
    private JTextArea txtOutput;
    private JList moviesJList;
    private final int NOT_SELECTED = -1;
    private int selectIndex = NOT_SELECTED;

    public Swing() {
        mainFrame = new JFrame("Data Collection for Movies");
        mainFrame.setSize(800, 400);

        mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(mainFrame,
                        "Are you sure? Exit?", "Exit Window", JOptionPane.YES_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);

                if (confirm == 0) {
                    mainFrame.dispose();
                    System.exit(0);
                }
            }
        };

        mainFrame.addWindowListener(l);

        Dimension di = mainFrame.getToolkit().getScreenSize();
        mainFrame.setLocation(di.width / 2 - mainFrame.getWidth() / 2, di.height / 2 - mainFrame.getHeight() / 2);

        mainFrame.setJMenuBar(createMenu());

        JPanel content = (JPanel) mainFrame.getContentPane();
        content.setLayout(new GridLayout(1, 2, 0, 0));
        content.setBackground(Color.GREEN);

        prepareJList();

        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(2,1,0,0));
        listPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        JScrollPane scrollPane = new JScrollPane(createMovieList());

        listPanel.add(scrollPane);
        listPanel.add(createOutputPanel());

        content.add(listPanel);


        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(0,1,5,5));
        TitledBorder title;
        title = BorderFactory.createTitledBorder("Movie's Details");
        fieldPanel.setBorder(title);

        fieldPanel.add(createFieldPanel(),BorderLayout.CENTER);
        fieldPanel.add(createButtonPanel(),BorderLayout.SOUTH);

        content.add(fieldPanel);

        mainFrame.setVisible(true);


    }

    private JPanel createOutputPanel(){

        outputPanel = new JPanel();
        txtOutput = new JTextArea();
        txtOutput.setText("Future Improvement Segment");
        outputPanel.add(txtOutput);
        //txtOutput.setEditable(false);

        return outputPanel;
    }

    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.GRAY);
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.addActionListener(this);

        JMenuItem fileSaveItem = new JMenuItem("Save");
        fileSaveItem.addActionListener(this);
        fileMenu.add(fileSaveItem);

        JMenuItem fileLoaditem = new JMenuItem("Load");
        fileLoaditem.addActionListener(this);
        fileMenu.add(fileLoaditem);

        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setMnemonic(KeyEvent.VK_N);
        newMenuItem.addActionListener(this);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.addActionListener(this);



        fileMenu.add(newMenuItem);
        fileMenu.add(exitMenuItem);


        JMenu optionMenu = new JMenu("Option");
        optionMenu.setMnemonic(KeyEvent.VK_O);
        optionMenu.addActionListener(this);

        JMenuItem searchMenuItem = new JMenuItem("Search");
        searchMenuItem.setMnemonic(KeyEvent.VK_S);
        searchMenuItem.addActionListener(this);

        JMenuItem refreshMenuItem = new JMenuItem("Refresh");
        refreshMenuItem.setMnemonic(KeyEvent.VK_L);
        refreshMenuItem.addActionListener(this);

        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.setMnemonic(KeyEvent.VK_C);
        clearMenuItem.addActionListener(this);

        optionMenu.add(searchMenuItem);
        optionMenu.add(refreshMenuItem);
        optionMenu.add(clearMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(optionMenu);

        return menuBar;

    }

    private JPanel createFieldPanel(){
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0,2,5,5));

        JLabel lblIndex = new JLabel("Index");
        inputPanel.add(lblIndex);
        txtIndex = new JTextField();
        inputPanel.add(txtIndex);
        txtIndex.setEditable(false);

        JLabel lblMovieTitle = new JLabel("Title:");
        inputPanel.add(lblMovieTitle);
        txtTitle = new JTextField();
        inputPanel.add(txtTitle);
        txtTitle.setEditable(false);

        JLabel lblMovieGenre = new JLabel("Genre");
        inputPanel.add(lblMovieGenre);
        txtGenre = new JTextField();
        inputPanel.add(txtGenre);
        txtGenre.setEditable(false);

        JLabel lblMovieCast = new JLabel("Cast");
        inputPanel.add(lblMovieCast);
        txtCast = new JTextField();
        inputPanel.add(txtCast);
        txtCast.setEditable(false);

        JLabel lblMovieDirector = new JLabel("Director");
        inputPanel.add(lblMovieDirector);
        txtDirector = new JTextField();
        inputPanel.add(txtDirector);
        txtDirector.setEditable(false);

        JLabel lblMovieDuration = new JLabel("Duration");
        inputPanel.add(lblMovieDuration);
        txtDuration = new JTextField();
        inputPanel.add(txtDuration);
        txtDuration.setEditable(false);

        JLabel lblMovieReleaseDate = new JLabel("Release Date");
        inputPanel.add(lblMovieReleaseDate);
        txtDate = new JTextField();
        inputPanel.add(txtDate);
        txtDate.setEditable(false);

        return inputPanel;
    }

    private java.util.List<String> getMovieDetails(){
        java.util.List<String> info = new ArrayList<>();

        info.add(txtIndex.getText());
        info.add(txtTitle.getText());
        info.add(txtGenre.getText());
        info.add(txtCast.getText());
        info.add(txtDirector.getText());
        info.add(txtDuration.getText());
        info.add(txtDate.getText());

        for (int i =0; i<info.size(); i++)
            if (info.get(i).isEmpty())
            {
                JOptionPane.showMessageDialog(mainFrame, "Please fill in all the fields");
                return null;
            }

        return info;
    }

    private void setMovieField(int index){
        Movies temp = movies.get(index);
        txtIndex.setText(temp.getMovieIndex());
        txtTitle.setText(temp.getMovieTitle());
        txtGenre.setText(temp.getMovieGenre());
        txtCast.setText(temp.getMovieCast());
        txtDirector.setText(temp.getMovieDirector());
        txtDuration.setText(temp.getMovieDuration());
        txtDate.setText(temp.getMovieReleaseDate());
        // txtDate.setText(temp.getMovieReleaseDate());

    }

    private void setFieldsUneditable(){
        txtIndex.setEditable(false);
        txtTitle.setEditable(false);
        txtGenre.setEditable(false);
        txtCast.setEditable(false);
        txtDirector.setEditable(false);
        txtDuration.setEditable(false);
        txtDate.setEditable(false);
    }

    private void prepareJList() {
        moviesJList = new JList<>();
        moviesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        moviesJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!moviesJList.getValueIsAdjusting()) {
                    if (!moviesJList.isSelectionEmpty()) {
                        selectIndex = moviesJList.getSelectedIndex();
                        if (selectIndex > NOT_SELECTED)
                            setMovieField(selectIndex);
                    }
                }
            }
        });
    }

    private JList<Movies> createMovieList(){
        DefaultListModel<Movies> listModel = new DefaultListModel<>();
        if (movies.size() > 0)
            for(Movies e : movies)
                listModel.addElement(e);

        moviesJList.setModel(listModel);
        return moviesJList;
    }
    private JList<Movies> createMovieList(java.util.List<Movies> test){
        DefaultListModel<Movies> listModel = new DefaultListModel<>();
        if (test.size() > 0)
            for(Movies e : test)
                listModel.addElement(e);

        moviesJList.setModel(listModel);
        return moviesJList;
    }

    private JPanel createButtonPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(0,1,5,5));

        JButton btnSave = new JButton("Save");
        btnPanel.add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectIndex == NOT_SELECTED) {
                    JOptionPane.showMessageDialog(mainFrame,"The following movie has been added to the collection:" + "\n" + TaskProcessing.createNewMovies(getMovieDetails()));
                    setFieldsUneditable();
                }
                else
                    //JOptionPane.showMessageDialog(mainFrame, TaskProcessing.editEmployee(selectIndex, getEmployeeInfo()));
                    selectIndex = NOT_SELECTED;
                clearField();
                TaskProcessing.refreshMovieList();


                createMovieList();
            }
        });

        JButton btnRemove = new JButton("Remove");

        btnPanel.add(btnRemove);

            btnRemove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                        Movies temp = movies.get(selectIndex);

                        TaskProcessing.removeMovies(selectIndex);
                        TaskProcessing.refreshMovieList();

                        createMovieList();


                }
            });
        return btnPanel;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if ("File".equals(action)){

        }

        else if("New".equals(action)){
            clearField();
            selectIndex = NOT_SELECTED;
            moviesJList.clearSelection();

            txtDate.setEditable(true);
            txtIndex.setEditable(true);
            txtTitle.setEditable(true);
            txtDirector.setEditable(true);
            txtCast.setEditable(true);
            txtDuration.setEditable(true);
            txtGenre.setEditable(true);
        }

        else if("Exit".equals(action)){
            mainFrame.dispose();
            System.exit(0);
        }

        else if ("Option".equals(action)){

        }

        else if ("Search".equals(action)){
            String st = JOptionPane.showInputDialog(mainFrame,"Enter string to search");
            try{
                TaskProcessing.searchByFirstName(st);
                createMovieList(test);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error: "+ ex);
            }
        }

        else if ("Save".equals(action)){
            TaskProcessing.fileSave();
            clearField();
            JOptionPane.showMessageDialog(mainFrame, "The file has saved successfully");
        }

        else if ("Load".equals(action)){
            try {
                movies.clear();
                TaskProcessing.fileLoad();
                clearField();
                createMovieList();
                JOptionPane.showMessageDialog(mainFrame,"The load is successful");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        else if("Refresh".equals(action)){
            createMovieList();
            clearField();
        }

        else if("Clear".equals(action)){
            clearField();
            setFieldsUneditable();
        }

    }

    private void clearField() {
        txtIndex.setText("");
        txtDate.setText("");
        txtCast.setText("");
        txtDirector.setText("");
        txtDuration.setText("");
        txtGenre.setText("");
        txtTitle.setText("");
    }
}
