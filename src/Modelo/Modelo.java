package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.table.DefaultTableModel;

import Vista.Login;
import Vista.MenuAdminMusica;
import Vista.MenuAdminUsuarios;
import Vista.MenuPlaylist;
import Vista.MenuPrincipal;
import Vista.Registro;

public class Modelo {

	private Login vistaLogin;
	private Registro vistaRegistro;
	private MenuAdminUsuarios vistaAdminUsuario;
	private MenuAdminMusica vistaAdminMusica;
	private MenuPrincipal vistaPrincipal;
	private MenuPlaylist vistaPlaylist;
	private String usr;
	private String pwd;
	private String url;
	private String driver;
	private String sqlTablaUsers;
	private String sqlTablaMusic;
	private String sqlTablaMusicUser;
	private Connection conexion;

	private DefaultTableModel TablaUser;
	private DefaultTableModel TablaMusic;
	private DefaultTableModel TablaMusicUser;
	private PreparedStatement sentencia;

	private ResultSet resultado;

	public Modelo() {

		// Creacion de la conexion con la base de datos

		Properties propiedades = new Properties();
		InputStream entrada = null;
		sqlTablaUsers = "Select * from users";
		sqlTablaMusic = "Select * from music";
		sqlTablaMusicUser = "Select * from music";

		try {
			File miFichero = new File("Config/config.ini");

			if (miFichero.exists()) {
				entrada = new FileInputStream(miFichero);

				propiedades.load(entrada);

				driver = propiedades.getProperty("driver");
				usr = propiedades.getProperty("usr");
				pwd = propiedades.getProperty("pwd");
				url = propiedades.getProperty("url");

				Class.forName(driver);
				conexion = DriverManager.getConnection(url, usr, pwd);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		cargarTablaUser();
		cargarTablaMusic();
		cargarTablaMusicUser();
	}

	/**
	 * Método para solicitar login a la aplicación. Se pasan como parámetros usuario
	 * y contarseña para comprobar en la query.
	 * 
	 * Devuelve un valor integer y según que valor nos permitirá hacer una cosa u
	 * otra.
	 * 
	 * @param user
	 * @param pass
	 * @return 0 usuario/contraseña incorrectos, 1 credenciales correctas rol
	 *         usuario, 2 credenciales correctas rol admin.
	 */

	public int consultaLogin(String user, String pass) {
		int aux = 0;
		String sql = "SELECT * FROM users WHERE USR = ? AND PWD = ?";
		try {

			// createStatement -- solo para pruebas, es muy inseguro
			// prepareStatement -- mejor opcion
			// prepareCall -- PL/SQL

			sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, user);
			sentencia.setString(2, pass);

			resultado = sentencia.executeQuery();

			while (resultado.next()) {
				String usuario = resultado.getString(1);
				String password = resultado.getString(2);
				String rol = resultado.getString(3);
				if (!user.equals(usuario) || !pass.equals(password)) {
					aux = 0;
				} else if (user.equals(usuario) && pass.equals(password) && rol.equals("user")) {
					aux = 1;
				} else {
					aux = 2;
				}
			}
		} catch (SQLException e) {
			System.out.println("Error realizando consulta.");
		}
		return aux;
	}

	/**
	 * Método para comprobar si un usuario existe o no al realizar un registro.
	 * Devuelve un valor booleano en función de la query.
	 * 
	 * @param user
	 * @return true si existe el usuario, false si no existe el usuario.
	 */
	public boolean consultaRegister(String user) {
		boolean aux = false;
		String sql = "SELECT USR FROM users WHERE USR = ?";
		try {

			sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, user);

			resultado = sentencia.executeQuery();

			while (resultado.next()) {
				String usuario = resultado.getString(1);
				if (user.equals(usuario)) {
					aux = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Error realizando consulta.");
		}
		return aux;
	}

	/**
	 * Método para registrarse en la aplicación como usuario mediante una query para
	 * insertar los valores introducidos en la pantalla registro en la base de
	 * datos, comprobando previamente los campos mediante el método consultaRegister
	 * por si hubiera algún inconveniente.
	 * 
	 * @param user
	 * @param pwd
	 * @param name
	 * @param surname
	 * @param email
	 */
	public void Register(String user, String pwd, String name, String surname, String email) {
		String sql = "INSERT INTO users (USR, PWD, ROL, Name, Surname, Email) VALUES (?,?,'user',?,?,?)";
		try {

			sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, user);
			sentencia.setString(2, pwd);
			sentencia.setString(3, name);
			sentencia.setString(4, surname);
			sentencia.setString(5, email);

			sentencia.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error realizando consulta.");
		}
	}

	/*
	 * Método para cargar los datos de la tabla usuario mediante metadatos y una
	 * query.
	 */

	private void cargarTablaUser() {
		int numColumnas = getNumColumnas(sqlTablaUsers);
		int numFilas = getNumFilas(sqlTablaUsers);

		String[] cabecera = new String[numColumnas];

		Object[][] contenido = new Object[numFilas][numColumnas];
		try {
			sentencia = conexion.prepareStatement(sqlTablaUsers);
			ResultSet rset = sentencia.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			for (int i = 0; i < numColumnas; i++) {
				cabecera[i] = rsmd.getColumnName(i + 1);
			}
			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenido[fila][col - 1] = rset.getString(col);
				}
				fila++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TablaUser = new DefaultTableModel(contenido, cabecera);
	}

	/*
	 * Método para cargar los datos de la tabla musica mediante metadatos y una
	 * query.
	 */

	private void cargarTablaMusic() {
		int numColumnas = getNumColumnas(sqlTablaMusic);
		int numFilas = getNumFilas(sqlTablaMusic);

		String[] cabecera = new String[numColumnas];

		Object[][] contenido = new Object[numFilas][numColumnas];
		try {
			sentencia = conexion.prepareStatement(sqlTablaMusic);
			ResultSet rset = sentencia.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			for (int i = 0; i < numColumnas; i++) {
				cabecera[i] = rsmd.getColumnName(i + 1);
			}
			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenido[fila][col - 1] = rset.getString(col);
				}
				fila++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TablaMusic = new DefaultTableModel(contenido, cabecera);
	}

	/*
	 * Método para cargar los datos de la tabla usuarios en el panel de
	 * administrador mediante metadatos y una query.
	 */

	private void cargarTablaMusicUser() {
		int numColumnas = getNumColumnas(sqlTablaMusicUser);
		int numFilas = getNumFilas(sqlTablaMusicUser);

		String[] cabecera = new String[numColumnas];

		Object[][] contenido = new Object[numFilas][numColumnas];
		try {
			sentencia = conexion.prepareStatement(sqlTablaMusicUser);
			ResultSet rset = sentencia.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			for (int i = 0; i < numColumnas; i++) {
				cabecera[i] = rsmd.getColumnName(i + 1);
			}
			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenido[fila][col - 1] = rset.getString(col);
				}
				fila++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TablaMusicUser = new DefaultTableModel(contenido, cabecera);
	}

	// Métodos para obtener el numero de columnas y filas de una tabla con
	// metadatos.

	private int getNumColumnas(String sql) {
		int num = 0;
		try {
			sentencia = conexion.prepareStatement(sql);
			ResultSet rset = sentencia.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			num = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	private int getNumFilas(String sql) {
		int numFilas = 0;
		try {
			sentencia = conexion.prepareStatement(sql);
			ResultSet rset = sentencia.executeQuery();
			while (rset.next())
				numFilas++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numFilas;
	}

	/*
	 * Metodo usado para añadir musica mediante un INSERT.
	 */
	public void AñadirMusica(int id, String name, String artist, String duration, int id_gender, String ref_mp3,
			String ref_img) {
		String sql = "INSERT INTO music (`ID`, `Name`, `Artist`, `Duration`, `ID_Gender`, `Ref_Mp3`, `Ref_Img`) VALUES (?,?,?,?,?,?,?)";
		try {

			sentencia = conexion.prepareStatement(sql);

			sentencia.setInt(1, id);
			sentencia.setString(2, name);
			sentencia.setString(3, artist);
			sentencia.setString(4, duration);
			sentencia.setInt(5, id_gender);
			sentencia.setString(6, ref_mp3);
			sentencia.setString(7, ref_img);

			sentencia.executeUpdate();
			cargarTablaMusic();
			cargarTablaMusicUser();
		} catch (SQLException e) {
			System.out.println("Error insertando datos.");
		}
	}

	/*
	 * Metodo para eliminar musica de la base de datos mediante DELETE.
	 */
	public void EliminarMusica(int id) {
		String sql = "DELETE FROM `music` WHERE `music`.`ID` = ?";
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, id);
			sentencia.executeUpdate();
			cargarTablaMusic();
			cargarTablaMusicUser();
		} catch (SQLException e) {
			System.out.println("Error eliminando datos.");
		}

	}

	/*
	 * Metodo para modificar musica en la base de datos.
	 */
	public void ModificarMusica(int idModificar, int id, String name, String artist, String duration, int id_gender,
			String ref_mp3, String ref_img) {
		String sql = "UPDATE `music` SET `ID` = ?, `Name` = ?, `Artist` = ?,`Duration` = ?, `ID_Gender` = ?, `Ref_Mp3` = ?, `Ref_Img` = ? WHERE `music`.`ID` = ?";

		try {

			sentencia = conexion.prepareStatement(sql);

			sentencia.setInt(1, id);
			sentencia.setString(2, name);
			sentencia.setString(3, artist);
			sentencia.setString(4, duration);
			sentencia.setInt(5, id_gender);
			sentencia.setString(6, ref_mp3);
			sentencia.setString(7, ref_img);
			sentencia.setInt(8, idModificar);

			sentencia.executeUpdate();
			cargarTablaMusic();
			cargarTablaMusicUser();
		} catch (SQLException e) {
			System.out.println("Error modificando datos.");
		}
	}

	/*
	 * Metodo para borrar usuarios de la base de datos.
	 */
	public void EliminarUsuario(String user) {
		String sql = "DELETE FROM `users` WHERE `users`.`USR` = ?";
		try {
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, user);
			sentencia.executeUpdate();
			cargarTablaUser();
			;
		} catch (SQLException e) {
			System.out.println("Error eliminando datos.");
		}
	}

	// Getters y Setters

	public DefaultTableModel getTablaUser() {
		return TablaUser;
	}

	public DefaultTableModel getTablaMusic() {
		return TablaMusic;
	}

	public DefaultTableModel getTablaMusicUser() {
		return TablaMusicUser;
	}

	public void setVistaLogin(Login vistaLogin) {
		this.vistaLogin = vistaLogin;
	}

	public void setVistaRegistro(Registro vistaRegistro) {
		this.vistaRegistro = vistaRegistro;
	}

	public void setVistaAdminUsuario(MenuAdminUsuarios vistaAdminUsuario) {
		this.vistaAdminUsuario = vistaAdminUsuario;
	}

	public void setVistaAdminMusica(MenuAdminMusica vistaAdminMusica) {
		this.vistaAdminMusica = vistaAdminMusica;
	}

	public void setVistaPrincipal(MenuPrincipal vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;
	}

	public void setVistaPlaylist(MenuPlaylist vistaPlaylist) {
		this.vistaPlaylist = vistaPlaylist;
	}
}