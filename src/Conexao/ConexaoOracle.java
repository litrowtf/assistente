/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author leandro
 */
public class ConexaoOracle {
      
    public static Connection ObterConexao() throws SQLException{
        Connection conexao = null;
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//192.168.150.241:1521/racmppa.mppa.mp.br","P_LEANDROMALAQUIAS","qwe123QWE!@#");
            
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        
        return conexao;
    }
    
}
