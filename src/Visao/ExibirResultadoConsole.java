/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Conexao.ConexaoOracle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leandro
 */
class ExibirResultadoConsole {

    public void imprimirTela(String query) throws SQLException {
        final ResultSetMetaData metaRS;
        final int columnCount;
        final Map<String, String> fieldsValues = new HashMap<>();

        Connection conexao = ConexaoOracle.ObterConexao();

        Statement statement = conexao.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        metaRS = resultSet.getMetaData();
        columnCount = metaRS.getColumnCount();

        /*if(resultSet.next()){
            System.out.println(resultSet.getString("NUMEROLOCALREGISTRO_DISPONIVEL"));
        }*/
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                final Object value = resultSet.getObject(i);
                fieldsValues.put(metaRS.getColumnName(i), value.toString());
            }
            System.out.println(fieldsValues.entrySet().toString());
            //resultSet.getMetaData().
            //resultSet.getString("NOME");

        }
    }

}
