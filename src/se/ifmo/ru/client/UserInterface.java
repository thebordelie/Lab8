package se.ifmo.ru.client;

import se.ifmo.ru.resources.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserInterface {
    private CommandManager commandManager;
    private JFrame jFrame;
    private Container container;
    private Authorization authorization;
    private String title;
    private String auth;
    private String reg;
    private String login;
    private String userPassword;
    private String userPassword1;
    private String reset;
    private String showUserPassword;
    private String back;
    private String result;
    private String done;
    private String err;
    private String locale;
    private UserGUI userGUI;
    private Dimension dimension;
    public UserInterface(Authorization authorization,CommandManager commandManager) {
        this.authorization = authorization;
        this.commandManager=commandManager;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        dimension= toolkit.getScreenSize();
    }

    public void createWindow() {
        jFrame = new JFrame() {
        };
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options={"Да","Нет"};
                int n=JOptionPane.showOptionDialog(e.getWindow(),"Закрыть окно?","Подтверждение",
                        JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                if(n==0){
                    e.getWindow().setVisible(true);
                    commandManager.processingCommand("exit");
                    System.exit(0);
                }
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
        jFrame.setBounds(dimension.width / 2 - 200, dimension.height / 2 - 100, 400, 200);
        ImageIcon image = new ImageIcon(UserInterface.class.getResource("itmo.png"));
        jFrame.setIconImage(image.getImage());
    }

    public void createHomeScreen() {
        jFrame.setBounds(dimension.width / 2 - 215, dimension.height / 2 - 100, 430, 200);
        jFrame.getContentPane().removeAll();
        jFrame.repaint();
        jFrame.setTitle(title);
        container = jFrame.getContentPane();
        jFrame.setLayout(new BorderLayout());
        JButton changeLocale=new JButton(locale);
        changeLocale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createButtonLocal("home");
            }
        });
        JButton authorization = new JButton(auth);
        authorization.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAuthorizationForm();
            }
        });
        JButton registration = new JButton(reg);
        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRegistrationForm();
            }
        });
        JPanel jPanel=new JPanel(new GridLayout(1,3,10,10));
        jPanel.add(authorization);
        jPanel.add(changeLocale);
        jPanel.add(registration);
        JPanel jPanel1=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel1.add(jPanel);

        container.add(jPanel1);
        jFrame.setVisible(true);

    }
    public void createButtonLocal(String nameOfMethod){
        JDialog dialog=new JDialog(jFrame);
        dialog.setBounds(jFrame.getWidth()/2,jFrame.getHeight()+100,250,300);
        dialog.setTitle(locale);
        JButton jButtonRus=new JButton("Русский язык");
        jButtonRus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLocale(new Resource_ru());
                createAgainWindow(nameOfMethod);
                dialog.setTitle(locale);
            }
        });
        JButton jButtonGe=new JButton("Deutsche Sprache");
        jButtonGe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLocale(new Resource_ge());
                createAgainWindow(nameOfMethod);
                dialog.setTitle(locale);
            }
        });
        JButton jButtonEs=new JButton("Español");
        jButtonEs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLocale(new Resource_Es());
                createAgainWindow(nameOfMethod);
                dialog.setTitle(locale);
            }
        });
        JButton jButtonLat=new JButton("Latviešu valoda");
        jButtonLat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLocale(new Resource_lat());
                createAgainWindow(nameOfMethod);
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
    public void createAgainWindow(String nameOfMethod){
        switch (nameOfMethod){
            case "home":
                createHomeScreen();
                break;
            case "reg":
                createRegistrationForm();
                break;
            case "log":
                createAuthorizationForm();
                break;

        }
    }
    public void createAuthorizationForm(){
        jFrame.setBounds(dimension.width / 2 - 215, dimension.height / 2 - 100, 430, 400);
        jFrame.getContentPane().removeAll();
        jFrame.repaint();

        jFrame.setLayout(new BorderLayout());
        container = jFrame.getContentPane();
        JButton changeLocale=new JButton(locale);
        changeLocale.addActionListener(e -> createButtonLocal("log"));
        jFrame.setTitle(auth);
        JLabel userLabel = new JLabel(login);
        JLabel password = new JLabel(userPassword);
        JTextField userLogin = new JTextField();
        JPasswordField userPassword = new JPasswordField();
        userPassword.setEchoChar('*');
        JButton loginButton = new JButton(auth);
        loginButton.addActionListener(e -> {
            String line = authorization.authorizationToDataBase(userLogin.getText(), userPassword.getText());
            if (line.equals("Авторизация прошла успешно")) {
                JOptionPane.showMessageDialog(jFrame, done, result, JOptionPane.INFORMATION_MESSAGE);
                userGUI=new UserGUI(jFrame,commandManager,authorization.getId(),userLogin.getText());
                userGUI.setLocale(new Resource_ru());
                userGUI.interactiveMode();
            } else JOptionPane.showMessageDialog(jFrame, err, result, JOptionPane.ERROR_MESSAGE);
        });
        JButton resetButton = new JButton(reset);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLogin.setText("");
                userPassword.setText("");
            }
        });
        JCheckBox showPassword = new JCheckBox(showUserPassword);
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    userPassword.setEchoChar((char) 0);
                } else userPassword.setEchoChar('*');
            }
        });
        JButton back = new JButton(this.back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createHomeScreen();
            }
        });
        JLabel label=new JLabel();
        JPanel jPanel=new JPanel(new GridLayout(7,2,30,30));
        jPanel.add(userLabel);
        jPanel.add(userLogin);
        jPanel.add(password);
        jPanel.add(userPassword);
        jPanel.add(label);
        jPanel.add(showPassword);
        jPanel.add(loginButton);
        jPanel.add(resetButton);
        jPanel.add(new Label());
        jPanel.add(back);
        jPanel.add(changeLocale);
        JPanel jPanel1=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel1.add(jPanel);
        container.add(jPanel1);
        jFrame.setVisible(true);
    }
    public void createRegistrationForm() {
        jFrame.setBounds(dimension.width / 2 - 215, dimension.height / 2 - 100, 430, 400);
        jFrame.getContentPane().removeAll();
        jFrame.repaint();
        JButton changeLocale=new JButton(locale);
        changeLocale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createButtonLocal("reg");
            }
        });
        jFrame.setLayout(new BorderLayout());
        jFrame.setTitle(reg);
        JLabel userLabel = new JLabel(login);
        JLabel password = new JLabel(userPassword);
        JLabel password1 = new JLabel(userPassword1);
        JButton resetButton = new JButton(reset);
        JTextField userLogin = new JTextField();
        JPasswordField userPassword = new JPasswordField();
        JPasswordField userPassword1 = new JPasswordField();
        userPassword.setEchoChar('*');
        userPassword1.setEchoChar('*');
        JCheckBox showPassword = new JCheckBox(showUserPassword);
        JButton registration = new JButton(reg);
        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String line = authorization.registrationToDataBase(userLogin.getText(), userPassword.getText(), userPassword1.getText());
                if (line.equals("Регистрация прошла успешно")) {
                    JOptionPane.showMessageDialog(jFrame, done, result, JOptionPane.INFORMATION_MESSAGE);
                } else JOptionPane.showMessageDialog(jFrame, err, result, JOptionPane.ERROR_MESSAGE);
            }
        });
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    userPassword.setEchoChar((char) 0);
                    userPassword1.setEchoChar((char) 0);
                } else {
                    userPassword.setEchoChar('*');
                    userPassword1.setEchoChar('*');
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLogin.setText("");
                userPassword.setText("");
                userPassword1.setText("");
            }
        });
        JButton back = new JButton(this.back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createHomeScreen();
            }
        });
        JLabel label=new JLabel();
        JPanel jPanel=new JPanel(new GridLayout(7,2,30,30));
        jPanel.add(userLabel);
        jPanel.add(userLogin);
        jPanel.add(password);
        jPanel.add(userPassword);
        jPanel.add(password1);
        jPanel.add(userPassword1);
        jPanel.add(label);
        jPanel.add(showPassword);
        jPanel.add(registration);
        jPanel.add(resetButton);
        jPanel.add(new Label());
        jPanel.add(back);
        jPanel.add(changeLocale);
        JPanel jPanel1=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel1.add(jPanel);
        container.add(jPanel1);
        jFrame.setVisible(true);
    }
    public void setLocale(Resource resource){
        title=resource.getContents().get("title");
        auth=resource.getContents().get("auth");
        reg=resource.getContents().get("reg");
        login=resource.getContents().get("login");
        userPassword1=resource.getContents().get("userPassword1");
        userPassword=resource.getContents().get("userPassword");
        reset=resource.getContents().get("reset");
        showUserPassword=resource.getContents().get("showUserPassword");
        back=resource.getContents().get("back");
        result=resource.getContents().get("result");
        done=resource.getContents().get("done");
        err=resource.getContents().get("err");
        locale=resource.getContents().get("locale");
    }
}
