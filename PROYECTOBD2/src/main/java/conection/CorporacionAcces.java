/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author antho
 */
public class CorporacionAcces {

    public static String getHistorico(String PlantaE, Integer IDEmpleadoE) {
        String Planta = PlantaE;
        Integer IDEmpleado = IDEmpleadoE;
        String Nombre = "";
        String Apellido = "";
        String Fecha = "";
        Float MontoPagadoBruto = 0f;
        Float MontoPagadoNeto = 0f;
        try {
            Connection connection = SQLDataBaseConnection.getConnection();
            Statement statement = null;
            statement = connection.createStatement();

            //SELECTSQL STATEMENT
            String HistorEmple = "EXEC HistoricoEmpleado @IDEmpleado =" + IDEmpleado + ", @Planta = " + Planta;
            ResultSet resultSet = statement.executeQuery(HistorEmple);
            resultSet = statement.executeQuery(HistorEmple);

            //Get results from query
            while (resultSet.next()) {
                IDEmpleado = resultSet.getInt("IDEmpleado");
                Nombre = resultSet.getString("Nombre");
                Apellido = resultSet.getString("Apellido");
                Fecha = resultSet.getString("Fecha");
                MontoPagadoBruto = resultSet.getFloat("MontoPagadoBruto");
                MontoPagadoNeto = resultSet.getFloat("MontoPagadoNeto");
            }
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return IDEmpleado + " " + Nombre + " " + Apellido + " " + Fecha + " " + MontoPagadoBruto + " " + MontoPagadoNeto;

    }
    
}
