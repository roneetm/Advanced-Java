package dao;

import model.Login;
import model.Party;
import service.DBConnection;
import java.sql.*;

public class RegistrationDao {

       public static boolean saveUser(Party party, Login login) throws SQLException {
           try (Connection connection = DBConnection.getConnection()) {

               // Checking if user is already registered.
               String email = login.getEmail();
               String password = login.getPassword();

               // Getting email and password from the database
               String checkingEmail = "select userLoginId from UserLogin";
               Statement statement1 = connection.createStatement();
               ResultSet resultSet = statement1.executeQuery(checkingEmail);

               boolean flag = true;
               while (resultSet.next()) {
                   if (resultSet.getString("userLoginId").equalsIgnoreCase(email)) {
                       //If email is found then flag will be true
                       flag = false;
                       return false;
                   }
               }// If no email is found then control flow will continue to registration process

               /*
                *  if Flag is False then we will continue with registration
                */
               if (flag) {
                   // Getting Max partyId from Database
                   Statement statement = connection.createStatement();
                   ResultSet result = statement.executeQuery("select MAX(partyId) as partyId from Party");

                   // Storing max value in variable Id
                   int partyId = -1;
                   if (result != null) {
                       while (result.next()) {
                           partyId = (result.getInt("partyId")) + 1;
                       }
                   }
                   statement.close(); // Closing Statement

                   // Query for inserting values into Party table
                   String query = "INSERT INTO `Data_Modelling`.`Party` " +
                           "(`partyId`, `firstName`, `lastName`, `city`, `zip`, `state`, `country`, `phone`) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                   PreparedStatement preparedStatement = connection.prepareStatement(query);
                   preparedStatement.setInt(1, partyId);
                   preparedStatement.setString(2, party.getFirstName());
                   preparedStatement.setString(3, party.getLastName());
                   preparedStatement.setString(4, party.getCity());
                   preparedStatement.setInt(5, party.getZip());
                   preparedStatement.setString(6, party.getState());
                   preparedStatement.setString(7, party.getCountry());
                   preparedStatement.setString(8, party.getPhone());

                   // Query for inserting values into UserLogin table
                   String loginQuery = "INSERT INTO `Data_Modelling`.`UserLogin` " +
                           "(`userLoginId`, `password`, `partyId`) " +
                           "VALUES (?, ?, ?)";

                   PreparedStatement preparedStatement1 = connection.prepareStatement(loginQuery);
                   preparedStatement1.setString(1, email);
                   preparedStatement1.setString(2, password);
                   preparedStatement1.setInt(3, partyId);

                   // Setting values in Party Table
                   preparedStatement.executeUpdate();
                   preparedStatement.close();

                   // Inserting values in UserLogin table
                   preparedStatement1.executeUpdate();
                   preparedStatement1.close(); // Closing Prepared Statement
               }

           } catch (SQLException | ClassNotFoundException sqlException) {
               sqlException.getMessage();
           } catch (Exception e) {
               e.printStackTrace();
           }
            return true;
    }
}