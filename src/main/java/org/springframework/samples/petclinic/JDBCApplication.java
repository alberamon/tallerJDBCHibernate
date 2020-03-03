package org.springframework.samples.petclinic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;

public class JDBCApplication {

	public static void main(String[] args) {
		System.out.println("-------- Test de conexión con MySQL ------------");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No encuentro el driver en el Classpath");
			e.printStackTrace();
			return;
		}

		System.out.println("Driver instalado y funcionando");
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic","root", "root");
			if (connection != null)
				System.out.println("Conexión establecida");
			
			/*
			//1. Obtener todos los clientes de la clínica y mostrar sus datos por pantalla.
			
			statement = connection.createStatement();
			
			String sql = "SELECT * FROM OWNERS";
			
			ResultSet resultados = statement.executeQuery(sql);
			
			System.out.println("---------------------------");
			
			while(resultados.next()) {
				System.out.println("Id cliente: " + resultados.getInt("id"));
				System.out.println("Nombre cliente: " + resultados.getString("first_name"));
				System.out.println("Apellidos cliente: " + resultados.getString("last_name"));
				System.out.println("Dirección cliente: " + resultados.getString("address"));
				System.out.println("Ciudad cliente: " + resultados.getString("city"));
				System.out.println("Teléfono cliente: " + resultados.getString("telephone"));
				System.out.println("---------------------------");
			}
			
			statement.close();
			*/
			
			
			/*
			//2. Insertarnos a nosotros como nuevo propietario de una mascota.
			
			statement = connection.createStatement();
			
			String consulta = "SELECT COUNT(*) FROM OWNERS WHERE first_name = 'Carlos Alberto' AND last_name = 'Ramón Valverde'";
			ResultSet resultadosConsulta = statement.executeQuery(consulta);
	
			
			if(resultadosConsulta == null) {
				String sql = "INSERT INTO OWNERS (first_name, last_name, address, city, telephone) VALUES ('Carlos Alberto', 'Ramón Valverde', 'Calle Creta', 'Granada', '600949360')";
				
				int filasModificadas = -1;
				
				filasModificadas = statement.executeUpdate(sql);
				
				if(filasModificadas != -1) {
					System.out.println("Se han modificado "  + filasModificadas + " filas.");
				} else {
					System.out.println("No se ha modificado ninguna fila");
				}
			} else {
				String sqlDelete = "DELETE FROM OWNERS WHERE first_name = 'Carlos Alberto' AND last_name = 'Ramón Valverde'";
				
				int filasModificadas = -1;
				filasModificadas = statement.executeUpdate(sqlDelete);
				
				if(filasModificadas != -1) {
					String sql = "INSERT INTO OWNERS (first_name, last_name, address, city, telephone) VALUES ('Carlos Alberto', 'Ramón Valverde', 'Calle Creta', 'Granada', '600949360')";
					
					int filasAñadidas = statement.executeUpdate(sql);
					System.out.println("Se han eliminado "  + filasModificadas + " filas.");
					System.out.println("Se ha añadido " + filasAñadidas + " filas.");
				} else {
					System.out.println("No se ha eliminado ninguna fila");
				}
				
			}
			
			statement.close();*/
			
			
			/*
			//3. Crear una variable de tipo String y buscar todos los dueños que coincidadn en nombre o apellido.
			
			String sql = "SELECT * FROM OWNERS WHERE first_name LIKE ? or last_name LIKE ?";
			String busqueda = "%"+"da"+"%";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, busqueda);
			preparedStatement.setString(2, busqueda);
			
			ResultSet resultados = preparedStatement.executeQuery();
			
			while(resultados.next()) {
				System.out.println("Id cliente: " + resultados.getInt("id"));
				System.out.println("Nombre cliente: " + resultados.getString("first_name"));
				System.out.println("Apellidos cliente: " + resultados.getString("last_name"));
				System.out.println("Dirección cliente: " + resultados.getString("address"));
				System.out.println("Ciudad cliente: " + resultados.getString("city"));
				System.out.println("Teléfono cliente: " + resultados.getString("telephone"));
				System.out.println("---------------------------");
			}
			
			preparedStatement.close();*/
			
			
			/*
			//4. Crear a partir de las variables nombre, apellido, direccion, ciudad y telefono (todas de tipo String), un nuevo Owner.
			
			String[] valores = new String[]{"Alberto", "Ramón", "Calle Creta", "Granada", "600949360"};
			
			String sql = "INSERT INTO OWNERS (first_name, last_name, address, city, telephone) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			for(int i = 0; i<valores.length; i++) {
				preparedStatement.setString(i+1, valores[i]);
			}
			
			int filasModificadas = preparedStatement.executeUpdate();
			
			System.out.println("Se han modificado " + filasModificadas + " fila.");
			
			preparedStatement.close();*/
			
			
			/*
			//Reto 30 minutos
			
			Owner propietario = new Owner();
			propietario.setFirstName("Carlos Alberto");
			propietario.setLastName("Ramón Valverde");
			propietario.setAddress("Calle Creta");
			propietario.setCity("Granada");
			propietario.setTelephone("600949360");
			
			String sql = "INSERT INTO OWNERS (first_name, last_name, address, city, telephone) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, propietario.getFirstName());
			preparedStatement.setString(2, propietario.getLastName());
			preparedStatement.setString(3, propietario.getAddress());
			preparedStatement.setString(4, propietario.getCity());
			preparedStatement.setString(5, propietario.getTelephone());
			
			int filasModificadas = preparedStatement.executeUpdate();
			
			System.out.println("Se han añadido " + filasModificadas + " filas");
			
			
			String sqlConsulta = "SELECT * FROM OWNERS WHERE first_name LIKE ? or last_name LIKE ?";
			preparedStatement = connection.prepareStatement(sqlConsulta);
			preparedStatement.setString(1, propietario.getFirstName());
			preparedStatement.setString(2, propietario.getLastName());
			ResultSet resultadoConsulta = preparedStatement.executeQuery();
			resultadoConsulta.next();
			int idPropietario = resultadoConsulta.getInt("id");
			
			Pet mascota = new Pet();
			mascota.setBirthDate(java.sql.Date.valueOf("2017-01-23"));
			mascota.setName("Tyrion");
			
			String sqlPet = "INSERT INTO PETS (birth_date, name, owner_id, type_id) VALUES (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sqlPet);
			preparedStatement.setDate(1, new java.sql.Date(mascota.getBirthDate().getTime()));
			preparedStatement.setString(2, mascota.getName());
			preparedStatement.setInt(3, idPropietario);
			preparedStatement.setInt(4, 2);
			
			filasModificadas = preparedStatement.executeUpdate();
			System.out.println("Se han modificado " + filasModificadas + " filas");
			

			preparedStatement.close();*/
		    
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		} finally {
			try {
				if(statement != null)
					connection.close();
			} catch (SQLException se) {
		    	  
		    }
		    try {
		        if(connection != null)
		            connection.close();
		    } catch (SQLException se) {
		         	se.printStackTrace();
		    }
		}
	}

}
