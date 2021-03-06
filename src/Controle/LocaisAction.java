/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Modelo.Consultas;
import Modelo.NumeroRegistro;
import Modelo.NumeroRegistroTableModel;
import Modelo.PromotorVinculado;
import Modelo.PromotorVinculadoTableModel;
import Visao.TelaPrincipal;
import static Visao.TelaPrincipal.COMARCA;
import static Visao.TelaPrincipal.DESCLOCAL;
import static Visao.TelaPrincipal.DESCPROMOTOR;
import static Visao.TelaPrincipal.IDLOCAL;
import static Visao.TelaPrincipal.IDPROMOTOR;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author leandro Carraga as informações dos usuários
 */
public class LocaisAction {

    Statement statement;

    /**
     *
     * @param statement
     */
    public LocaisAction(Statement statement) {
        this.statement = statement;
    }

    /**
     *
     * @param table
     */
    public void buscarNumeroRegistroLocais(JTable table) {
        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<NumeroRegistro> numRegLista = new ArrayList();
        NumeroRegistroTableModel tableModel;
        ResultSet resultSet = null;

        try {
            Consultas consulta = new Consultas();

            consulta.selecionarConsulta(Consultas.REGISTRODISPONIVEL);
            resultSet = statement.executeQuery(consulta.getQuery());

            metaRS = resultSet.getMetaData();
            columnCount = metaRS.getColumnCount();

            while (resultSet.next()) {
                NumeroRegistro numeroRegistro = new NumeroRegistro();
                for (int i = 1; i <= columnCount; i++) {
                    numeroRegistro.setNumReg(resultSet.getInt(i));
                }
                numRegLista.add(numeroRegistro);
            }

            tableModel = new NumeroRegistroTableModel(numRegLista);
            table.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, e);
            }
//            try { if(statement != null) statement.close(); } catch (SQLException e) {Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, e);}
        }
    }

    /**
     *
     * @param table
     * @param local Realiza o procedimento para capiturar o promotores
     * vinculados ao local.
     */
    public void consultarLocalAction(JTable table, String local) {
        // TODO add your handling code here:
        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<PromotorVinculado> promotorVinculadoLista = new ArrayList();
        PromotorVinculadoTableModel tableModel;
        String paramLocal = tratarParamSQL(local);
        ResultSet resultSet = null;

        if (!(paramLocal.equals(""))) {
            try {
                Consultas consulta = new Consultas();
                consulta.selecionarConsulta(Consultas.PROMOTORVINCULADO, paramLocal);
                resultSet = statement.executeQuery(consulta.getQuery());

                metaRS = resultSet.getMetaData();
                columnCount = metaRS.getColumnCount();

                while (resultSet.next()) { //Percorre todas as linhas do resultado da consulta ao banco
                    PromotorVinculado promotorVinculado = new PromotorVinculado(); //Instancia a classe que armazenará os valores de cada registro numa linha
                    for (int i = 1; i <= columnCount; i++) { //Percorre cada registro de uma linha
                        switch (i) { //Verifica o que é cada registro para armazenar no local correto
                            case IDPROMOTOR:
                                promotorVinculado.setIdPromotor(resultSet.getInt(i));
                                break;
                            case DESCPROMOTOR:
                                promotorVinculado.setDescPromotor(resultSet.getString(i));
                                break;
                            case IDLOCAL:
                                promotorVinculado.setIdLocal(resultSet.getInt(i));
                                break;
                            case DESCLOCAL:
                                promotorVinculado.setDescLocal(resultSet.getString(i));
                                break;
                            case COMARCA:
                                promotorVinculado.setDescComarca(resultSet.getString(i));
                                break;
                            default:
                                System.out.println("método consultarLocalAction \n Case: default");
                                break;

                        }
                    }
                    promotorVinculadoLista.add(promotorVinculado); //Adiciona as linhas da tabela no Array promotorVinculadoLista
                }

                tableModel = new PromotorVinculadoTableModel(promotorVinculadoLista);
                table.setModel(tableModel);
                table.getColumnModel().getColumn(0).setPreferredWidth(190);
                table.getColumnModel().getColumn(1).setPreferredWidth(500);
                table.getColumnModel().getColumn(2).setPreferredWidth(170);
                table.getColumnModel().getColumn(3).setPreferredWidth(500);
                table.getColumnModel().getColumn(4).setPreferredWidth(250);

            } catch (SQLException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, e);
                }
//            try { if(statement != null) statement.close(); } catch (SQLException e) {Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, e);}
            }
        }
    }

    private String tratarParamSQL(String parametro) {
        StringBuilder str = new StringBuilder();

        parametro = parametro.trim().replace(" ", "%");

        str.append("%");
        str.append(parametro);
        str.append("%");
        return str.toString();
    }

}
