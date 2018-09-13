/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Modelo.Consultas;
import Modelo.ProtocoloErroLE;
import Modelo.ListarProtocoloErroLEListModel;
import Visao.TelaPrincipal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author leandro
 */
public class ProtocoloAction {

    private Statement statement;
    private StringBuilder naoGerouSQL;

    public ProtocoloAction(Statement statement) {
        this.statement = statement;
    }

    
    /**
     * Buscar protocolos no banco
     * @param list
     * @param numeroRegistro
     *  - Se numeroRegistro for nulo, busa todos os protocolos com erro de Local Externo 
     * @param mostrarProtApen 
     */
    public void buscarProtocolosErroLE(JList list, String numeroRegistro, boolean mostrarProtApen) {
        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<ProtocoloErroLE> litaProtocolos = new ArrayList();
        ListarProtocoloErroLEListModel listModel;

        try {
            Consultas consulta = new Consultas();

            consulta.consultarProtocolosErroLE(numeroRegistro, mostrarProtApen);
            ResultSet resultSet = statement.executeQuery(consulta.getQuery());
            
            metaRS = resultSet.getMetaData();
            columnCount = metaRS.getColumnCount();

            while (resultSet.next()) {
                ProtocoloErroLE protocoloErroLE = new ProtocoloErroLE();
                for (int i = 1; i <= columnCount; i++) {
                    protocoloErroLE.setNumeroRegistroErroLE(resultSet.getString(i));
                }
                litaProtocolos.add(protocoloErroLE);
            }
            
            listModel = new ListarProtocoloErroLEListModel(litaProtocolos);
            list.setModel(listModel);

        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gerarMensagemSQl(JTextArea jTextAreaGeraSQL, ArrayList<String> listaNumeroRegistro) {
        ResultSetMetaData metaRS;
        int columnCount;
        StringBuilder sb = new StringBuilder();
        naoGerouSQL = new StringBuilder();
        boolean flagGerouSQL;
        int countPulaLinha = 0;

        try {
            Consultas consulta = new Consultas();
            sb.append("Solicito alteração, na base de produção, dos seguintes registros: \n \n \n");
            
            for (String lnr : listaNumeroRegistro) {
                flagGerouSQL = false;
                consulta.gerarMensagemSQL(lnr);
                ResultSet resultSet = statement.executeQuery(consulta.getQuery());

                metaRS = resultSet.getMetaData();
                columnCount = metaRS.getColumnCount();
                while (resultSet.next()) {
                    flagGerouSQL = true; //a chave é verdadeira quando gera o SQL
                    for (int i = 1; i <= columnCount; i++) {
//                        protErroMensagemSQL.setMensageSQL(resultSet.getString(i));
                          sb.append("--Protocolo: ");
                          sb.append(lnr);
                          sb.append("\n");
                          sb.append(resultSet.getString(i));
                          sb.append(";\n \n");
                    }
//                    listaProtErroMensagemSQL.add(protErroMensagemSQL);
                }
                if(!flagGerouSQL){
                    naoGerouSQL.append(lnr);
                    naoGerouSQL.append(", ");
                    if(countPulaLinha == 4){
                        naoGerouSQL.append("\n");
                        countPulaLinha = 0;
                    }
                    countPulaLinha++;
                }
            }
            
            
            jTextAreaGeraSQL.setText(sb.toString());

        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String protocolosNaoGerados() {
        try{
            return naoGerouSQL.toString();
        } catch (NullPointerException e){
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, e);
        }
        return "Erro ao registrar protocolos sem SQL";
    }
}
