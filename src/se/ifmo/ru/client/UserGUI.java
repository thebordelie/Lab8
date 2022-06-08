package se.ifmo.ru.client;

import se.ifmo.ru.data.*;
import se.ifmo.ru.exception.ElementMustNotBeEmptyException;
import se.ifmo.ru.resources.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class UserGUI {
    private CommandManager commandManager;
    private JFrame jFrame;
    private Container container;
    private String locale;
    private String titleOfInteractive;
    private String add;
    private String countLessThanRefundable;
    private String addIfMin;
    private String clear;
    private String executeScript;
    private String groupCountingGroup;
    private String head;
    private String help;
    private String info;
    private String remove;
    private String removeByAnyVenue;
    private String removeHead;
    private String show;
    private String update;
    private String make;
    private long id;
    private String userLogin;

    public UserGUI(JFrame jFrame, CommandManager commandManager, long id, String login) {
        this.id = id;
        this.commandManager = commandManager;
        this.jFrame = jFrame;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - 500, dimension.height / 2 - 350, 1000, 700);
        container = jFrame.getContentPane();
        userLogin = login;

    }

    public void createTable(Object[][] rows, JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        String columns[] = {"id", "name", "x_coordinates", "y_coordinates", "day", "month", "year", "price", "refundable", "tickettype",
                "nameofvenue", "capacity", "street", "zipCode", "xLoc", "yLoc", "zLoc", "nameloc"};
        TableModel model = new DefaultTableModel(rows, columns) {
            public boolean isCellEditable(int row, int col) {
                return true;
            }

            public void setValueAt(Object value, int row, int col) {
                commandManager.getCommandRegister().getCommands().get("update").setArg(Long.parseLong(String.valueOf(rows[row][0])));
                commandManager.getCommandRegister().getCommands().get("update").setUpdateColumn(columns[col]);
                commandManager.getCommandRegister().getCommands().get("update").setValue(String.valueOf(value));
                String text = commandManager.processingCommand("update");
                rows[row][col] = value;
                fireTableCellUpdated(row, col);
                if (!text.equals("Элемент найден и обновлён")) {
                    JOptionPane.showMessageDialog(null, "" +
                            text, "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    createTable(rows, frame);
                };


            }

            public Class getColumnClass(int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };

        JTable table = new JTable(model);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
                model);
        table.setRowSorter(sorter);
        JScrollPane pane = new JScrollPane(table);
        frame.add(pane);
        frame.setBounds(550, 150, 800, 570);
        frame.setVisible(true);
    }

    public void interactiveMode() {
        jFrame.getContentPane().removeAll();
        jFrame.repaint();
        jFrame.setTitle(titleOfInteractive);
        jFrame.setLayout(new BorderLayout());
        container=jFrame.getContentPane();
        JButton createTable = new JButton("Октрыть таблицу");
        createTable.addActionListener(e -> {
            SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
                @Override
                protected String doInBackground() throws Exception {

                    String text =  commandManager.processingCommand("show");

                    return text;
                }
                protected void done() {
                    String status;
                    try {
                        JFrame frame = new JFrame("Таблица");
                        status = get();
                        String[] row = status.split("\n");
                        Object[][] rowsTable = new Object[row.length][];
                        for (int i = 0; i < rowsTable.length; i++) {
                            rowsTable[i] = row[i].split(",");
                        }
                        createTable(rowsTable, frame);

                    } catch (InterruptedException e) {
                        // This is thrown if the thread's interrupted.
                    } catch (ExecutionException e) {

                    }
                }

            };
            worker.execute();




        });
        JLabel userLogin = new JLabel("User: " + this.userLogin);
        JButton buttonAdd = new JButton(add);
        buttonAdd.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(add);
            JButton addButton = createButtonAdd(dialog, "add");
            //dialog.add(addButton);


        });
        JButton buttonAddIfMin = new JButton(addIfMin);
        buttonAddIfMin.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(addIfMin);
            JButton addButton = createButtonAdd(dialog, "add_if_min");
            //dialog.add(addButton);
        });
        JButton buttonCountLessThanRefundable = new JButton(countLessThanRefundable);
        buttonCountLessThanRefundable.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(countLessThanRefundable);
            createDefaultMenu(dialog, "count_less_than_refundable", createArgLabel(dialog, countLessThanRefundable, "refundable"));

        });
        JButton buttonClear = new JButton(clear);
        buttonClear.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(clear);
            createDefaultMenu(dialog, "clear", null);
        });
        JButton buttonExecuteScript = new JButton(executeScript);
        buttonExecuteScript.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(executeScript);
            createDefaultMenu(dialog, "execute_script", null);
        });
        JButton buttonGroupCountingGroup = new JButton(groupCountingGroup);
        buttonGroupCountingGroup.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(groupCountingGroup);
            createDefaultMenu(dialog, "group_counting_by_type", null);
        });
        JButton buttonHead = new JButton(head);
        buttonHead.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(head);
            createDefaultMenu(dialog, "head", null);
        });
        JButton buttonHelp = new JButton(help);
        buttonHelp.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(help);
            createDefaultMenu(dialog, "help", null);
        });
        JButton buttonInfo = new JButton(info);
        buttonInfo.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(info);
            createDefaultMenu(dialog, "info", null);
        });
        JButton buttonRemove = new JButton(remove);
        buttonRemove.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(remove);
            createDefaultMenu(dialog, "remove_by_id", createArgLabel(dialog, remove, "id"));

        });
        JButton buttonRemoveByAnyVenue = new JButton(removeByAnyVenue);
        buttonRemoveByAnyVenue.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(removeByAnyVenue);
            //createDefaultMenu(dialog,"remove_any_by_venue");
        });
        JButton buttonRemoveHead = new JButton(removeHead);
        buttonRemoveHead.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(removeHead);
            createDefaultMenu(dialog, "remove_head", null);
        });
        JButton buttonShow = new JButton(show);
        buttonShow.addActionListener(e -> {
            createAnimation();
        });
        JButton buttonUpdate = new JButton(update);
        buttonUpdate.addActionListener(e -> {
            JDialog dialog = createDefaultDialogMenu(update);
            createButtonAdd(dialog, "update");
        });
        JButton[] buttons = new JButton[]{buttonAdd, buttonAddIfMin, buttonCountLessThanRefundable, buttonClear, buttonExecuteScript,
                buttonGroupCountingGroup, buttonHead, buttonHelp, buttonInfo, buttonRemove, buttonRemoveByAnyVenue, buttonRemoveHead, buttonShow, buttonUpdate};
        JPanel jPanel=new JPanel(new GridLayout(17,1,10,10));
        for (JButton button : buttons) {
            jPanel.add(button);
        }
        JButton changeLocale = new JButton(locale);
        changeLocale.addActionListener(e -> createButtonLocal());
        jPanel.add(userLogin);
        jPanel.add(changeLocale);
        JPanel jPanel1=new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(jPanel);
        container.add(jPanel1);
        JPanel jPanel2 =new JPanel(new GridLayout(1,1,10,10));
        jPanel2.add(createTable);
        JPanel jPanel3 =new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jPanel3.add(jPanel2);
        container.add(jPanel3,BorderLayout.EAST);
        jFrame.setVisible(true);
    }

    public void createDefaultMenu(JDialog dialog, String nameOfCommand, JTextField jTextField) {

        JLabel jLabel = new JLabel();
        dialog.setLayout(null);
        jLabel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLUE, 4, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        jLabel.setBounds(30, 30, 430, 300);
        JButton jButton = new JButton(make);
        if (jTextField != null) {
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        commandManager.getCommandRegister().getCommands().get(nameOfCommand).setArg(Long.parseLong(jTextField.getText()));
                        String text = commandManager.processingCommand(nameOfCommand);
                        text = text.replaceAll("\n", "<br>");
                        jLabel.setVerticalAlignment(JLabel.CENTER);
                        jLabel.setHorizontalAlignment(JLabel.CENTER);
                        jLabel.setText("<html><center>" + text + "<html><center>");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Неверно введены данные", "oh nooo", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } else {
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
                        @Override
                        protected String doInBackground() throws Exception {
                            String text = commandManager.processingCommand(nameOfCommand);
                            text = text.replaceAll("\n", "<br>");
                            return text;
                        }
                        protected void done() {
                            String status;
                            try {
                                status = get();
                                jLabel.setVerticalAlignment(JLabel.CENTER);
                                jLabel.setHorizontalAlignment(JLabel.CENTER);
                                jLabel.setText("<html><center>" + status + "<html><center>");
                            } catch (InterruptedException e) {
                                // This is thrown if the thread's interrupted.
                            } catch (ExecutionException e) {

                            }
                        }

                    };
                    worker.execute();
//                    String text = commandManager.processingCommand(nameOfCommand);
//                    text = text.replaceAll("\n", "<br>");
//
//                    jLabel.setVerticalAlignment(JLabel.CENTER);
//                    jLabel.setHorizontalAlignment(JLabel.CENTER);
//                    jLabel.setText("<html><center>" + text + "<html><center>");
                }
            });
        }

        jButton.setBounds(200, 450, 100, 30);
        dialog.add(jButton);
        dialog.add(jLabel);
        dialog.setVisible(true);
    }

    public JButton createButtonAdd(JDialog dialog, String nameOfCommand) {
        JButton jButton = new JButton();
        dialog.setLayout(new BorderLayout());
        String[] names = new String[]{"name", "x", "y", "price", "refundable", "type", "name Of Venue", "capacity", "street", "zipCode", "xTown", "yTown", "zTown", "name Of Town"};
        ArrayList<JTextField> fields = new ArrayList<>();
        JPanel jPanel=new JPanel(new GridLayout(15,2,10,10));
        for (String name : names) {
            JLabel jLabel = new JLabel(name);
            JTextField jTextField = new JTextField();
            jPanel.add(jLabel);
            jPanel.add(jTextField);
            fields.add(jTextField);


        }
        JButton addButton = new JButton(make);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    id += 1;
                    Ticket ticket = new Ticket(id, fields.get(0).getText(), new Coordinates(Float.parseFloat(fields.get(1).getText()), Long.parseLong(fields.get(2).getText())),
                            LocalDate.now(), Float.parseFloat(fields.get(3).getText()), Boolean.parseBoolean(fields.get(4).getText()),
                            TicketType.valueOf(fields.get(5).getText()), new Venue(id, fields.get(6).getText(), Integer.parseInt(fields.get(7).getText()),
                            new Address(fields.get(8).getText(), fields.get(9).getText(),
                                    new Location(Long.parseLong(fields.get(10).getText()), Float.parseFloat(fields.get(11).getText()),
                                            Long.parseLong(fields.get(12).getText()), fields.get(13).getText()))));
                    ticket.setLogin(userLogin);
                    commandManager.getCommandRegister().getCommands().get(nameOfCommand).setTicket(ticket);
                    JOptionPane.showMessageDialog(null, commandManager.processingCommand(nameOfCommand), "Результат", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Неверно введены данные", "oh nooo", JOptionPane.ERROR_MESSAGE);
                    id--;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Неверно введены данные", "oh nooo", JOptionPane.ERROR_MESSAGE);
                    id--;
                }

            }
        });
        jPanel.add(addButton);
        JPanel jPanel1=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel1.add(jPanel);
        dialog.add(jPanel1);
        dialog.setVisible(true);
        return jButton;
    }

    public JDialog createDefaultDialogMenu(String name) {
        JDialog dialog = new JDialog(jFrame);
        dialog.setBounds(jFrame.getWidth() / 2 + 150, jFrame.getHeight() / 2 - 350, 500, 700);
        dialog.setTitle(name);
        dialog.setVisible(true);
        return dialog;
    }

    public JTextField createArgLabel(JDialog jDialog, String nameOfCommand, String refundable) {
        JLabel jLabel = new JLabel(refundable);
        jLabel.setVerticalAlignment(JLabel.CENTER);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        JTextField jTextField = new JTextField();
        jLabel.setBounds(100, 400, 100, 30);
        jTextField.setBounds(200, 400, 100, 30);
        jDialog.add(jLabel);
        jDialog.add(jTextField);
        return jTextField;

    }

    public void setLocale(Resource resource) {
        titleOfInteractive = resource.getContents().get("titleOfInteractive");
        add = resource.getContents().get("add");
        addIfMin = resource.getContents().get("addIfMin");
        countLessThanRefundable = resource.getContents().get("countLessThanRefundable");
        clear = resource.getContents().get("clear");
        groupCountingGroup = resource.getContents().get("groupCountingGroup");
        executeScript = resource.getContents().get("executeScript");
        head = resource.getContents().get("head");
        help = resource.getContents().get("help");
        info = resource.getContents().get("info");
        remove = resource.getContents().get("remove");
        removeByAnyVenue = resource.getContents().get("removeByAnyVenue");
        removeHead = resource.getContents().get("removeHead");
        show = resource.getContents().get("show");
        update = resource.getContents().get("update");
        locale = resource.getContents().get("locale");
        make = resource.getContents().get("make");
    }

    public void createButtonLocal() {
        JDialog dialog = new JDialog(jFrame);
        dialog.setBounds(jFrame.getWidth() / 2 + 150, jFrame.getHeight() / 2, 250, 300);
        dialog.setLayout(null);
        dialog.setTitle(locale);
        JButton jButtonRus = new JButton("Русский язык");
        jButtonRus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLocale(new Resource_ru());
                interactiveMode();
                dialog.setTitle(locale);
            }
        });
        JButton jButtonGe = new JButton("Deutsche Sprache");
        jButtonGe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLocale(new Resource_ge());
                interactiveMode();
                dialog.setTitle(locale);
            }
        });
        JButton jButtonEs = new JButton("Español");
        jButtonEs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLocale(new Resource_Es());
                interactiveMode();
                dialog.setTitle(locale);
            }
        });
        JButton jButtonLat = new JButton("Latviešu valoda");
        jButtonLat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLocale(new Resource_lat());
                interactiveMode();
                dialog.setTitle(locale);
            }
        });
        dialog.setLayout(new BorderLayout());
        JPanel jPanel=new JPanel(new GridLayout(4,1,10,20));
        jPanel.add(jButtonRus);
        jPanel.add(jButtonGe);
        jPanel.add(jButtonEs);
        jPanel.add(jButtonLat);
        dialog.add(jPanel);
        dialog.setVisible(true);
    }

    public void createAnimation() {
        JFrame graph = new JFrame();
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                String text = commandManager.processingCommand("show");
                return text;
            }
            protected void done() {
                String status;
                try {
                    status = get();
                    String[] row = status.split("\n");
                    System.out.println(status);
                    Object[][] rowsTable = new Object[row.length][];
                    for (int i=0;i<rowsTable.length;i++){
                        rowsTable[i]=row[i].split(",");
                    }
                    graph.setTitle("Все билеты");
                    graph.setLayout(new FlowLayout(FlowLayout.CENTER));
                    for (int i = 0; i < rowsTable.length; i++) {
                        JButton jButton=new JButton("Билет№\n"+String.valueOf(rowsTable[i][0]));
                        if(rowsTable[i][18].equals(userLogin)){
                            jButton.setBackground(Color.GREEN);
                        }
                        else jButton.setBackground(Color.PINK);


                        jButton.setForeground(Color.red);
                        Object[] col1=rowsTable[i];
                        jButton.addActionListener(e->{
                            Object[][] col={col1};
                            createTable(col,new JFrame());
                        });
                        graph.getContentPane().add(jButton);

                    }

                    graph.setSize(500, 500);
                    graph.setVisible(true);
                } catch (InterruptedException e) {
                    // This is thrown if the thread's interrupted.
                } catch (ExecutionException e) {

                }
            }

        };
        worker.execute();


    }

}
