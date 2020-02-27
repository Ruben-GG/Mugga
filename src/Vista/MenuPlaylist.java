package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.Border;

import Controlador.Controlador;
import Modelo.Modelo;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.TextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;

public class MenuPlaylist extends JFrame {
	private Controlador controlador;
	private Modelo modelo;

	private JPanel panelUsrPlaylist;
	private JPanel panelPrincipal;
	private JPanel panelReproductor;
	private JPanel panelMusicAct;
	private JPanel panelPublicidad;
	private JPanel panelRecomendados;

	private JSeparator separatorRecomendados;

	private TextArea textArea;
	private JTextField txtNombre;
	private JTextField txtBuscador;

	private JList list;
	private JScrollPane scrollPlaylist;

	private JLabel lblTtuloActual;
	private JLabel lblArtistaActual;
	private JLabel lblPublicidad;
	private JLabel lblRecomendados;
	private JLabel lblArtistaTtulo;
	private JLabel icn1;
	private JLabel icn2;
	private JLabel lblArtistaTitulo2;
	private JLabel icn3;
	private JLabel lblArtistaTitulo3;
	private JLabel icn4;
	private JLabel lblArtistaTitulo4;
	private JLabel icn5;
	private JLabel lblArtistaTitulo5;
	private JLabel lblBackground;
	private JLabel btnExit;
	private JLabel btnMinimize;
	private JLabel imgSong;
	private JLabel lblPlaylist;
	private JLabel lblIconoUser;
	private JLabel lblDescripcion;
	private JLabel lblNombre;
	private final JLabel logo;

	private JButton btnLogOut;
	private JButton btnCrearPlaylist;
	private JButton btnBuscar;
	private JButton btnPlay;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnRepeat;
	private JButton btnRandom;
	private JButton btnCrear;
	private JButton btnVolver;

	private int xx, xy;

	public MenuPlaylist() {

		setType(Type.POPUP);
		setTitle("Menu Principal (Playlist)");
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPlaylist.class.getResource("/imagen/iconLogo.png")));
		getContentPane().setForeground(UIManager.getColor("Menu.selectionBackground"));
		this.getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(null);

		setUndecorated(true);

		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().setLayout(null);

		Border border = BorderFactory.createLineBorder(Color.WHITE);

		panelUsrPlaylist = new JPanel();
		panelUsrPlaylist.setBounds(16, 175, 134, 344);
		getContentPane().add(panelUsrPlaylist);
		panelUsrPlaylist.setLayout(null);

		btnCrearPlaylist = new JButton("Crear");
		btnCrearPlaylist.setBounds(26, 398, 77, 29);
		panelUsrPlaylist.add(btnCrearPlaylist);

		btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.LogOutPlaylist();
			}
		});
		btnLogOut.setBounds(27, 97, 80, 30);
		btnLogOut.setBackground(Color.BLACK);
		btnLogOut.setBorder(border);
		btnLogOut.setForeground(Color.WHITE);
		panelUsrPlaylist.add(btnLogOut);

		lblIconoUser = new JLabel("");
		lblIconoUser.setIcon(new ImageIcon(MenuPlaylist.class.getResource("/imagen/usr.png")));
		lblIconoUser.setBounds(29, 6, 80, 80);
		panelUsrPlaylist.add(lblIconoUser);

		list = new JList();
		list.setBounds(6, 138, 120, 161);
		panelUsrPlaylist.add(list);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "Playlist1", "Playlist2", "Playlist3", "Playlist4", "Playlist5",
					"Playlist6", "Playlist7", "Playlist8", "Playlist9", "Playlist10" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});

		scrollPlaylist = new JScrollPane();
		scrollPlaylist.setBounds(6, 138, 122, 163);
		panelUsrPlaylist.add(scrollPlaylist);

		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(162, 86, 688, 433);
		getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);

		lblPlaylist = new JLabel("Crear Playlist");
		lblPlaylist.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaylist.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPlaylist.setForeground(Color.WHITE);
		lblPlaylist.setBounds(300, 10, 183, 28);
		panelPrincipal.add(lblPlaylist);

		lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(200, 90, 61, 16);
		panelPrincipal.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setToolTipText("");
		txtNombre.setBounds(200, 109, 130, 26);
		panelPrincipal.add(txtNombre);

		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setBounds(200, 142, 89, 16);
		panelPrincipal.add(lblDescripcion);

		btnCrear = new JButton("Crear");
		btnCrear.setBounds(250, 312, 70, 29);
		btnCrear.setBackground(Color.BLACK);
		btnCrear.setBorder(border);
		btnCrear.setForeground(Color.WHITE);
		panelPrincipal.add(btnCrear);

		textArea = new TextArea();
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(200, 167, 190, 130);
		panelPrincipal.add(textArea);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.volverPrincipal();
			}
		});
		btnVolver.setBounds(612, 398, 70, 29);
		panelPrincipal.add(btnVolver);
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setBorder(border);
		btnVolver.setForeground(Color.WHITE);

		panelRecomendados = new JPanel();
		panelRecomendados.setBounds(876, 227, 143, 292);
		getContentPane().add(panelRecomendados);
		panelRecomendados.setLayout(null);

		lblRecomendados = new JLabel("Recomendados");
		lblRecomendados.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRecomendados.setForeground(Color.WHITE);
		lblRecomendados.setBounds(25, 6, 100, 16);
		panelRecomendados.add(lblRecomendados);
		lblRecomendados.setForeground(Color.WHITE);

		separatorRecomendados = new JSeparator();
		separatorRecomendados.setBounds(3, 25, 138, 12);
		panelRecomendados.add(separatorRecomendados);

		icn1 = new JLabel("");
		icn1.setIcon(new ImageIcon("/Users/ruben/Downloads/pngocean.com.png"));
		icn1.setBounds(10, 41, 20, 20);
		panelRecomendados.add(icn1);

		icn2 = new JLabel("");
		icn2.setIcon(new ImageIcon("/Users/ruben/Downloads/pngocean.com.png"));
		icn2.setBounds(11, 70, 20, 20);
		panelRecomendados.add(icn2);

		icn3 = new JLabel("");
		icn3.setIcon(new ImageIcon("/Users/ruben/Downloads/pngocean.com.png"));
		icn3.setBounds(10, 102, 20, 20);
		panelRecomendados.add(icn3);

		icn4 = new JLabel("");
		icn4.setIcon(new ImageIcon("/Users/ruben/Downloads/pngocean.com.png"));
		icn4.setBounds(10, 134, 20, 20);
		panelRecomendados.add(icn4);

		icn5 = new JLabel("");
		icn5.setIcon(new ImageIcon("/Users/ruben/Downloads/pngocean.com.png"));
		icn5.setBounds(10, 166, 20, 20);
		panelRecomendados.add(icn5);

		lblArtistaTtulo = new JLabel("Artista - Titulo");
		lblArtistaTtulo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblArtistaTtulo.setForeground(Color.WHITE);
		lblArtistaTtulo.setBounds(39, 45, 79, 16);
		panelRecomendados.add(lblArtistaTtulo);

		lblArtistaTitulo2 = new JLabel("Artista - Titulo");
		lblArtistaTitulo2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblArtistaTitulo2.setForeground(Color.WHITE);
		lblArtistaTitulo2.setBounds(39, 74, 79, 16);
		panelRecomendados.add(lblArtistaTitulo2);

		lblArtistaTitulo3 = new JLabel("Artista - Titulo");
		lblArtistaTitulo3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblArtistaTitulo3.setForeground(Color.WHITE);
		lblArtistaTitulo3.setBounds(39, 106, 79, 16);
		panelRecomendados.add(lblArtistaTitulo3);

		lblArtistaTitulo4 = new JLabel("Artista - Titulo");
		lblArtistaTitulo4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblArtistaTitulo4.setForeground(Color.WHITE);
		lblArtistaTitulo4.setBounds(39, 138, 79, 16);
		panelRecomendados.add(lblArtistaTitulo4);

		lblArtistaTitulo5 = new JLabel("Artista - Titulo");
		lblArtistaTitulo5.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblArtistaTitulo5.setForeground(Color.WHITE);
		lblArtistaTitulo5.setBounds(39, 170, 79, 16);
		panelRecomendados.add(lblArtistaTitulo5);

		// Panel del reproductor, falta implementar la reproduccion de música.

		panelReproductor = new JPanel();
		panelReproductor.setBounds(350, 531, 344, 32);
		panelReproductor.setBackground(Color.WHITE);
		getContentPane().add(panelReproductor);
		panelReproductor.setLayout(null);

		btnPlay = new JButton(new ImageIcon(MenuPlaylist.class.getResource("/imagen/play.png")));
		btnPlay.setBackground(Color.DARK_GRAY);
		btnPlay.setBounds(162, 6, 20, 20);
		panelReproductor.add(btnPlay);

		btnPrev = new JButton(new ImageIcon(MenuPlaylist.class.getResource("/imagen/previous.png")));
		btnPrev.setBounds(112, 6, 20, 20);
		panelReproductor.add(btnPrev);

		btnNext = new JButton(new ImageIcon(MenuPlaylist.class.getResource("/imagen/next.png")));
		btnNext.setBounds(212, 6, 20, 20);
		panelReproductor.add(btnNext);

		btnRepeat = new JButton(new ImageIcon(MenuPlaylist.class.getResource("/imagen/repeat.png")));
		btnRepeat.setBounds(262, 6, 20, 20);
		panelReproductor.add(btnRepeat);

		btnRandom = new JButton(new ImageIcon(MenuPlaylist.class.getResource("/imagen/random.png")));
		btnRandom.setBounds(62, 6, 20, 20);
		panelReproductor.add(btnRandom);

		panelMusicAct = new JPanel();
		panelMusicAct.setBounds(200, 531, 80, 31);
		getContentPane().add(panelMusicAct);
		panelMusicAct.setLayout(null);

		imgSong = new JLabel("");
		imgSong.setBounds(10, 3, 25, 25);
		imgSong.setIcon(new ImageIcon(MenuPlaylist.class.getResource("/imagen/song.jpg")));

		lblTtuloActual = new JLabel("Titulo");
		lblTtuloActual.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblTtuloActual.setForeground(Color.WHITE);
		lblTtuloActual.setBounds(50, 2, 25, 16);
		panelMusicAct.add(lblTtuloActual);

		lblArtistaActual = new JLabel("Artista");
		lblArtistaActual.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblArtistaActual.setForeground(Color.WHITE);
		lblArtistaActual.setBounds(50, 14, 25, 16);
		panelMusicAct.add(lblArtistaActual);
		panelPublicidad = new JPanel();
		panelPublicidad.setBounds(900, 110, 100, 100);
		getContentPane().add(panelPublicidad);
		panelPublicidad.setLayout(null);

		lblPublicidad = new JLabel("");
		lblPublicidad.setBounds(0, 0, 100, 100);
		lblPublicidad.setIcon(new ImageIcon(Login.class.getResource("/imagen/showhunt.png")));
		panelPublicidad.add(lblPublicidad);

		txtBuscador = new JTextField();
		txtBuscador.setToolTipText("");
		txtBuscador.setBounds(874, 60, 149, 29);
		getContentPane().add(txtBuscador);

		txtBuscador.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Validate();
			}
		});

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearFields();
			}
		});

		btnBuscar.setBackground(Color.BLACK);
		btnBuscar.setBounds(793, 61, 78, 29);
		getContentPane().add(btnBuscar);
		btnBuscar.setBorder(border);
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setEnabled(false);

		logo = new JLabel("");
		logo.setBounds(30, 30, 125, 125);
		getContentPane().add(logo);
		logo.setIcon(new ImageIcon(Login.class.getResource("/imagen/logo2.png")));

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
				MenuPlaylist.this.setLocation(x - xx, y - xy);
			}
		});

		// Métodos para hacer los paneles transparentes.
		panelPrincipal.setOpaque(true);
		panelPrincipal.setBackground(new Color(0, 0, 0, 0));
		panelReproductor.setOpaque(true);
		panelReproductor.setBackground(new Color(0, 0, 0, 0));
		panelMusicAct.setOpaque(true);
		panelMusicAct.setBackground(new Color(0, 0, 0, 0));
		panelRecomendados.setOpaque(true);
		panelRecomendados.setBackground(new Color(0, 0, 0, 0));
		panelPublicidad.setOpaque(true);
		panelPublicidad.setBackground(new Color(0, 0, 0, 0));
		panelUsrPlaylist.setOpaque(true);
		panelUsrPlaylist.setBackground(new Color(0, 0, 0, 0));

		lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 1036, 602);
		lblBackground.setIcon(new ImageIcon(Login.class.getResource("/imagen/bg.jpg")));
		getContentPane().add(lblBackground);

		Border borderPane = BorderFactory.createLineBorder(Color.WHITE, 3);
		this.getRootPane().setBorder(borderPane);

		setBounds(100, 100, 1036, 602);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * Métodos para comprobar que los campos esten vacios habilitando el boton y
	 * limpiar campos.
	 */
	private void Validate() {
		if (!txtBuscador.getText().trim().equals("")) {
			btnBuscar.setEnabled(true);
		} else
			btnBuscar.setEnabled(false);
	}

	public void ClearFields() {
		txtBuscador.setText("");
		txtNombre.setText("");
		textArea.setText("");
		btnBuscar.setEnabled(false);
	}

	// Getters y Setters

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
}