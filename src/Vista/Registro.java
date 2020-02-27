package Vista;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import Controlador.Controlador;
import Modelo.Modelo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;

public class Registro extends JFrame {
	private Controlador controller;
	private Modelo model;

	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JTextField txtUser;
	private JPasswordField txtRepeatPassword;
	private JPasswordField txtPassword;
	private JButton btnRegister;
	private JLabel btnGoBack;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblMail;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JLabel lblRepeatPassword;
	private JLabel lblRegister;
	private JLabel logo;
	private JLabel lblBackround;
	private JLabel btnExit;
	private JLabel btnMinimize;
	private JLabel lblWarningPassword;
	private JLabel lblWarningUser;

	private int xx, xy;

	public Registro() {

		setType(Type.POPUP);
		setTitle("Mugga (Registro)");
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registro.class.getResource("/imagen/iconLogo.png")));
		getContentPane().setForeground(UIManager.getColor("Menu.selectionBackground"));
		this.getContentPane().setBackground(Color.BLACK);

		setUndecorated(true);

		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().setLayout(null);

		lblRegister = new JLabel("REGISTRO");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setForeground(Color.LIGHT_GRAY);
		lblRegister.setFont(new Font("Stencil Std", Font.PLAIN, 59));
		lblRegister.setBounds(345, 40, 414, 142);
		getContentPane().add(lblRegister);

		lblName = new JLabel("Nombre:");
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(250, 254, 100, 25);
		getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setBounds(358, 254, 150, 25);
		getContentPane().add(txtName);

		txtName.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Validate();
			}
		});

		lblSurname = new JLabel("Apellidos:");
		lblSurname.setForeground(Color.LIGHT_GRAY);
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSurname.setBounds(250, 290, 100, 25);
		getContentPane().add(lblSurname);

		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSurname.setBounds(358, 291, 150, 23);
		getContentPane().add(txtSurname);

		txtSurname.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Validate();
			}
		});

		lblMail = new JLabel("Correo:");
		lblMail.setForeground(Color.LIGHT_GRAY);
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMail.setBounds(250, 325, 60, 41);
		getContentPane().add(lblMail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setBounds(358, 326, 150, 25);
		getContentPane().add(txtEmail);

		txtEmail.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Validate();
			}
		});

		lblUser = new JLabel("Usuario:");
		lblUser.setForeground(Color.LIGHT_GRAY);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUser.setBounds(555, 254, 100, 25);
		getContentPane().add(lblUser);

		logo = new JLabel("");
		logo.setBounds(30, 30, 125, 125);
		getContentPane().add(logo);
		logo.setIcon(new ImageIcon(Login.class.getResource("/imagen/logo.png")));

		lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setForeground(Color.LIGHT_GRAY);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(555, 290, 100, 25);
		getContentPane().add(lblPassword);

		lblRepeatPassword = new JLabel("<html>Repita <br> Contraseña:</html>");
		lblRepeatPassword.setForeground(Color.LIGHT_GRAY);
		lblRepeatPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRepeatPassword.setBounds(555, 320, 100, 35);
		getContentPane().add(lblRepeatPassword);

		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUser.setBounds(660, 254, 150, 25);
		getContentPane().add(txtUser);

		txtUser.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Validate();
			}
		});

		lblWarningUser = new JLabel("Usuario existente");
		lblWarningUser.setForeground(Color.RED);
		lblWarningUser.setBounds(820, 254, 125, 25);
		lblWarningUser.setVisible(false);
		getContentPane().add(lblWarningUser);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.setBounds(660, 291, 150, 25);
		getContentPane().add(txtPassword);

		txtPassword.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Validate();
			}
		});

		txtRepeatPassword = new JPasswordField();
		txtRepeatPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRepeatPassword.setBounds(660, 326, 150, 25);
		getContentPane().add(txtRepeatPassword);

		txtRepeatPassword.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Validate();
			}
		});

		btnRegister = new JButton("Registrarse");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(txtPassword.getPassword()).equals(String.valueOf(txtRepeatPassword.getPassword()))) {
					controller.register();
					lblWarningPassword.setVisible(false);
				} else {
					txtPassword.setText("");
					txtRepeatPassword.setText("");
					txtPassword.grabFocus();
					lblWarningPassword.setVisible(true);
					lblWarningUser.setVisible(false);
					btnRegister.setEnabled(false);
				}
			}
		});

		/*
		 * Si queremos poner un cursor custom sobre un botón utilizaremos lo siguiente
		 * cambiando la ruta de la imagen a usar (redimensionada), podremos añadirle una
		 * posición y un nombre.
		 * 
		 * btnRegister.setCursor(Toolkit.getDefaultToolkit().createCustomCursor( new
		 * ImageIcon(Registro.class.getResource("/imagen/cursor.png")).getImage(), new
		 * Point(0, 0), "notAllowed cursor"));
		 */

		btnRegister.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (btnRegister.isEnabled()) {
					btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			}
		});
		btnRegister.setEnabled(false);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRegister.setBounds(470, 398, 140, 40);

		getContentPane().add(btnRegister);

		btnGoBack = new JLabel("");
		btnGoBack.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.volverLogin();
			}

			public void mouseEntered(MouseEvent e) {
				btnGoBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		btnGoBack.setBounds(950, 510, 60, 41);
		btnGoBack.setIcon(new ImageIcon(Login.class.getResource("/imagen/backW.png")));
		getContentPane().add(btnGoBack);

		Border bord = BorderFactory.createLineBorder(Color.WHITE);
		btnRegister.setBorder(bord);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRegister.setForeground(Color.WHITE);

		/*
		 * Botones exit y minimizar con sus respectivos listeners para que cumplan su
		 * función y cambie el icono del cursor al estar encima.
		 */

		btnExit = new JLabel("x");
		btnExit.setHorizontalAlignment(SwingConstants.CENTER);
		btnExit.setFont(new Font("SignPainter", Font.BOLD, 50));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBounds(980, 10, 35, 35);
		getContentPane().add(btnExit);
		btnExit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			public void mouseEntered(MouseEvent e) {
				btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});

		btnMinimize = new JLabel("-");
		btnMinimize.setHorizontalAlignment(SwingConstants.CENTER);
		btnMinimize.setFont(new Font("SignPainter", Font.BOLD, 60));
		btnMinimize.setForeground(Color.WHITE);
		btnMinimize.setBounds(950, 12, 35, 35);
		getContentPane().add(btnMinimize);

		btnMinimize.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}

			public void mouseEntered(MouseEvent e) {
				btnMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});

		// Listeners para poder mover la pantalla con el cursor.

		getContentPane().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});

		getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				Registro.this.setLocation(x - xx, y - xy);
			}
		});

		lblWarningPassword = new JLabel("No coinciden las contraseñas");
		lblWarningPassword.setForeground(Color.RED);
		lblWarningPassword.setBounds(624, 360, 185, 25);
		lblWarningPassword.setVisible(false);
		getContentPane().add(lblWarningPassword);

		lblBackround = new JLabel("");
		lblBackround.setForeground(Color.WHITE);
		lblBackround.setBounds(0, 0, 1036, 602);
		lblBackround.setIcon(new ImageIcon(Login.class.getResource("/imagen/background.jpeg")));
		getContentPane().add(lblBackround);

		Border borderPane = BorderFactory.createLineBorder(Color.WHITE, 3);
		this.getRootPane().setBorder(borderPane);
		setBounds(100, 100, 1036, 602);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * Método de validación para comprobar si estan los campos vacios y habilitar el
	 * boton, limpiar campos para limpiar campos al presionar el boton o cambiar de
	 * ventana y avisos de errores al introducir credenciales erroneas.
	 */

	public void Validate() {
		if (!txtUser.getText().trim().equals("") && !String.valueOf(txtPassword.getPassword()).trim().equals("")
				&& !String.valueOf(txtRepeatPassword.getPassword()).trim().equals("")
				&& !txtName.getText().trim().equals("") && !txtSurname.getText().trim().equals("")
				&& !txtEmail.getText().trim().equals("")) {
			btnRegister.setEnabled(true);
		} else
			btnRegister.setEnabled(false);
	}

	public void ClearFields() {
		txtUser.setText("");
		txtPassword.setText("");
		txtRepeatPassword.setText("");
		txtName.setText("");
		txtEmail.setText("");
		txtSurname.setText("");
		btnRegister.setEnabled(false);
	}

	public void disableWarning() {
		lblWarningUser.setVisible(false);
		lblWarningPassword.setVisible(false);
	}

	public void WarningUser() {
		txtUser.setText("");
		txtUser.grabFocus();
		lblWarningUser.setVisible(true);
		btnRegister.setEnabled(false);
	}

	// Getters y Setters

	public String getUser() {
		return txtUser.getText();
	}

	public void setUser(JTextField txtUser) {
		this.txtUser = txtUser;
	}

	public String getPassword() {
		return String.valueOf(txtPassword.getPassword());
	}

	public void setPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public String getRepeatPassword() {
		return String.valueOf(txtRepeatPassword.getPassword());
	}

	public void setRepeatPassword(JPasswordField txtRepeatPassword) {
		this.txtRepeatPassword = txtRepeatPassword;
	}

	public String getName() {
		return txtName.getText();
	}

	public void setName(JTextField txtName) {
		this.txtName = txtName;
	}

	public String getSurname() {
		return txtSurname.getText();
	}

	public void setSurname(JTextField txtSurname) {
		this.txtSurname = txtSurname;
	}

	public String getEmail() {
		return txtEmail.getText();
	}

	public void setEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public Controlador getControlador() {
		return controller;
	}

	public void setControlador(Controlador controller) {
		this.controller = controller;
	}

	public Modelo getModelo() {
		return model;
	}

	public void setModelo(Modelo model) {
		this.model = model;
	}
}