/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author antho
 */
public class CorporacionAcces {

    public static List<String> getHistorico(String PlantaE, Integer IDEmpleadoE) {
        List<String> resultados = new ArrayList<>();
        String Planta = PlantaE;
        Integer IDEmpleado = IDEmpleadoE;
        String Nombre = "";
        String Apellidos = "";
        String Fecha = "";
        Float MontoPagadoBruto = 0f;
        Float MontoPagadoNeto = 0f;
        try {
            Connection connection = SQLDataBaseConnection.getConnection();
            if (connection != null){
                Statement statement = null;
                statement = connection.createStatement();

                //SELECTSQL STATEMENT
                String HistorEmple = "EXEC [dbo].[HistoricoEmpleado] @IDEmpleado =" + IDEmpleado + ", @Planta = " + Planta;
                ResultSet resultSet = statement.executeQuery(HistorEmple);

                //Get results from query
                while (resultSet.next()) {
                    IDEmpleado = resultSet.getInt("IdEmpleado");
                    Nombre = resultSet.getString("Nombre");
                    Apellidos = resultSet.getString("Apellidos");
                    Fecha = resultSet.getString("FechaPago");
                    MontoPagadoBruto = resultSet.getFloat("MontoPagadoBruto");
                    MontoPagadoNeto = resultSet.getFloat("MontoPagadoNeto");
                    
                    String datosEmpleados = IDEmpleado + " " + Nombre + " " + Apellidos + " " + Fecha + " " + MontoPagadoBruto + " " + MontoPagadoNeto;
                    resultados.add(datosEmpleados);
                }
                connection.close();
            }else{
                System.out.println("La conexion a la base de datos es Nula");
            }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        return resultados;
    }
    
    public static String getMontoPagado (String fechaInicio, String fechaFinal){
        String monto = "";
        try{
             Connection connection = SQLDataBaseConnection.getConnection();
             
             String selectSQL = "EXEC MontoPagadoTiempo ?,?";
             PreparedStatement statement = connection.prepareStatement(selectSQL);
             
             statement.setString(1, fechaInicio);
             statement.setString(2, fechaFinal);
             
              ResultSet resultSet = statement.executeQuery(); //un result set es una tabla
              
              while(resultSet.next()){
                  monto += resultSet.getDouble(1);
              }
              
              connection.close();
             
        } catch (SQLException exc){
            exc.printStackTrace();
        }
        System.out.println(monto);
        return monto;
    }
    
    public static String getSalarioObligaciones (String fechaInicio, String fechaFinal){
        String str = "";
        
        try{
            Connection connection = SQLDataBaseConnection.getConnection();
             
             String selectSQL = "EXEC montoNetoObligacionesTiempo ?,?";
             PreparedStatement statement = connection.prepareStatement(selectSQL);
             
             statement.setString(1, fechaInicio);
             statement.setString(2, fechaFinal);
             
             ResultSet resultSet = statement.executeQuery(); //un result set es una tabla
             
             while(resultSet.next()){
                  str += resultSet.getFloat(1) + "\t" +
                         resultSet.getFloat(2);
              }
             
             connection.close();
        }catch (SQLException exc){
            exc.printStackTrace();
        }
        
        System.out.println(str);
        return str;
    }
    
    public static String getSalarioBrutoPlanta (){
        String str = "";
        
        try{
            Connection connection = SQLDataBaseConnection.getConnection();
             
            String selectSQL = "EXEC SalariosBrutosPlantas ";
            PreparedStatement statement = connection.prepareStatement(selectSQL);
            
            ResultSet resultSet = statement.executeQuery(); //un result set es una tabla
            
            while(resultSet.next()){
                  str += resultSet.getString(1) + "\t"+
                          resultSet.getInt(2) + "\t" +
                          resultSet.getFloat(3) + "\t" +
                          resultSet.getFloat(4) + "\t" + "\n";
              }
             connection.close();
        }catch (SQLException exc){
            exc.printStackTrace();
        }
        
        System.out.println(str);
        return str;
    }
    
    public static String getAguinaldoEmpleado (Integer idEmpleado, String planta){
        String str = "";
        try{
            Connection connection = SQLDataBaseConnection.getConnection();
             
            String selectSQL = "EXEC CalcularAguinaldoEmpleado ?,?";
            PreparedStatement statement = connection.prepareStatement(selectSQL);
            
            statement.setInt(1, idEmpleado);
            statement.setString(2, planta);
            
            ResultSet resultSet = statement.executeQuery(); //un result set es una tabla
            
            while(resultSet.next()){
                  str += resultSet.getString(1) + "\t" +
                         resultSet.getString(2) + "\t" +
                         resultSet.getString(3) + "\t" +
                         resultSet.getFloat(4) + "\t" + "\n";
              }
            
            connection.close();
        } catch (SQLException exc){
            exc.printStackTrace();
        }
        
        System.out.println(str);
        return str;
    }
    
    public static String getEmpleadosMejores (String fechaInicio, String fechaFinal){
        String str = "";
        try{
            Connection connection = SQLDataBaseConnection.getConnection();
             
            String selectSQL = "EXEC empleadosMejorPagados ?,?";
            PreparedStatement statement = connection.prepareStatement(selectSQL);
            
            statement.setString(1, fechaInicio);
            statement.setString(2, fechaFinal);
            
            ResultSet resultSet = statement.executeQuery(); //un result set es una tabla
            
            while(resultSet.next()){
                  str += resultSet.getString(1) + "\t" +
                         resultSet.getString(2) + "\t" +
                         resultSet.getString(3) + "\t" +
                         resultSet.getFloat(4) + "\t" + "\n";
              }
            
            connection.close();
        } catch (SQLException exc){
            exc.printStackTrace();
        }
        
        System.out.println(str);
        return str;
    }
    
}
