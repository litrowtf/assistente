/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Modelo.ConsultaUsuario;
import Modelo.ConsultaUsuarioTableModel;
import Modelo.Consultas;
import Modelo.InfoGedoc;
import Modelo.ListarInfoGedocTableCellRenderer;
import Modelo.ListarInfoGedocTableModel;
import Modelo.LotacaoUsuarioSIMP;
import Modelo.PermissaoMastiffUsuario;
import Visao.TelaPrincipal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextArea;

/**
 *
 * @author leandro
 */
public class UsuariosAction {

    public static final int LOGINFOCUSLOST = 10;
    public static final int NOMEFOCUSLOST = 11;
    JTextField jTextFieldLogin;
    JTextField jTextFieldNome;
    JTextField jTextFieldMatricula;
    JTextField jTextFieldVinculo;
    JTextField jTextFieldEmail;
    JTextField jTextFieldCpf;
    JTextArea jTextAreaCadMastiff;
    JTextArea jTextAreaLotacaoUsuario;
    JTable jTableConsultaUsuario;
    JTable jTableGEDOC;
    int ultimoFocus;
    int chaveDosInfernos = 0;
    Statement statement;

    public UsuariosAction(JTextField jTextFieldLogin,
            JTextField jTextFieldNome,
            JTextField jTextFieldMatricula,
            JTextField jTextFieldVinculo,
            JTextField jTextFieldEmail,
            JTextField jTextFieldCpf,
            JTable jTableConsultaUsuario,
            JTable jTableGEDOC,
            JTextArea jTextAreaCadMastiff, 
            JTextArea jTextAreaLotacaoUsuario, 
            Statement statement,
            int ultimoFocus) {

        this.jTextFieldLogin = jTextFieldLogin;
        this.jTextFieldNome = jTextFieldNome;
        this.jTextFieldMatricula = jTextFieldMatricula;
        this.jTextFieldVinculo = jTextFieldVinculo;
        this.jTextFieldEmail = jTextFieldEmail;
        this.jTextFieldCpf = jTextFieldCpf;
        this.jTextAreaCadMastiff = jTextAreaCadMastiff;
        this.jTextAreaLotacaoUsuario = jTextAreaLotacaoUsuario;
        this.jTableConsultaUsuario = jTableConsultaUsuario;
        this.jTableGEDOC = jTableGEDOC;
        this.statement = statement;
        this.ultimoFocus = ultimoFocus;        

    }

    public void consultarUsuario(String paramConsulta, int campoConsultado) {

        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<ConsultaUsuario> consultaUsuarioLista = new ArrayList();
        paramConsulta = tratarParamSQL(paramConsulta);

        if (!(paramConsulta.equals(""))) {
            try {
                Consultas consulta = new Consultas();
                // A PARTIR DAQUI, DEVE TRATAR QUAL CONSULTA SERÁ FEITA NO BANCO BASEADO 
                // NO CAMPO QUE FOI INSERIDO O VALOR
                consulta.selecionarConsulta(Consultas.CONSULTARUSUARIO, paramConsulta, campoConsultado);
                ResultSet resultSet = statement.executeQuery(consulta.getQuery());

                metaRS = resultSet.getMetaData();
                columnCount = metaRS.getColumnCount();

                while (resultSet.next()) { //Percorre todas as linhas do resultado da consulta ao banco
                    ConsultaUsuario consultaUsuario = new ConsultaUsuario(); //Instancia a classe que armazenará os valores de cada registro numa linha
                    for (int i = 1; i <= columnCount; i++) { //Percorre cada registro de uma linha
                        switch (i) { //Verifica o que é cada registro para armazenar no local correto
                            case 1:
                                consultaUsuario.setLogin(resultSet.getString(i));
                                break;
                            case 2:
                                consultaUsuario.setNome(resultSet.getString(i));
                                break;
                            case 3:
                                consultaUsuario.setMatricula(resultSet.getString(i));
                                break;
                            case 4:
                                consultaUsuario.setVinculo(resultSet.getString(i));
                                break;
                            case 5:
                                consultaUsuario.setEmail(resultSet.getString(i));
                                break;
                            case 6:
                                consultaUsuario.setCpf(resultSet.getString(i));
                                break;
                        }
                    }
                    consultaUsuarioLista.add(consultaUsuario); //Adiciona as linhas da tabela no Array promotorVinculadoLista
                }

                ConsultaUsuarioTableModel tableModel = new ConsultaUsuarioTableModel(consultaUsuarioLista);
                jTableConsultaUsuario.setModel(tableModel);

                //configurar largura da coluna
                jTableConsultaUsuario.getColumnModel().getColumn(0).setPreferredWidth(200);
                jTableConsultaUsuario.getColumnModel().getColumn(1).setPreferredWidth(500);
                jTableConsultaUsuario.getColumnModel().getColumn(2).setPreferredWidth(170);
                jTableConsultaUsuario.getColumnModel().getColumn(3).setPreferredWidth(190);
                jTableConsultaUsuario.getColumnModel().getColumn(4).setPreferredWidth(500);
                jTableConsultaUsuario.getColumnModel().getColumn(5).setPreferredWidth(250);

                if (jTableConsultaUsuario.getRowCount() > 0) { //Verificar se ha registro na tabela
                    jTableConsultaUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //seta propriedade para selecionar apenas uma linha                    
                    jTableConsultaUsuario.setRowSelectionInterval(0, 0);//selecionar a primeira linha
                    atualizarCamposTabUsuario(jTableConsultaUsuario.getSelectedRow(), campoConsultado);
                    jTableConsultaUsuario.requestFocus();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void listarPermissaoUsuarioMastiff(String login) {
        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<PermissaoMastiffUsuario> listaPermissao = new ArrayList();
        try {
            Consultas consulta = new Consultas();

            consulta.consultarUsuarioPermissaoMastiff(login);
            ResultSet resultSet = statement.executeQuery(consulta.getQuery());

            metaRS = resultSet.getMetaData();
            columnCount = metaRS.getColumnCount();

            while (resultSet.next()) {
                PermissaoMastiffUsuario permissaoMastiffUsuario = new PermissaoMastiffUsuario();
                for (int i = 1; i <= columnCount; i++) {
                    permissaoMastiffUsuario.setPermissaoMastiff(resultSet.getString(i));
                }
                listaPermissao.add(permissaoMastiffUsuario);
            }
            
            //Mostrar permissões Mastiff
            StringBuilder saidaString = new StringBuilder();
            listaPermissao.forEach((txt) -> {
                saidaString.append(txt.getPermissaoMastiff());
                saidaString.append("\n");
            });
            jTextAreaCadMastiff.setText(saidaString.toString());
            jTextAreaCadMastiff.moveCaretPosition(0); //mover o cursor para o início
           
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarLotacaoUsuarioSIMP(String login) {
        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<LotacaoUsuarioSIMP> listaLotacao = new ArrayList();

        try {
            Consultas consulta = new Consultas();

            consulta.consultarUsuarioLotacao(login);
            ResultSet resultSet = statement.executeQuery(consulta.getQuery());

            metaRS = resultSet.getMetaData();
            columnCount = metaRS.getColumnCount();

            while (resultSet.next()) {
                LotacaoUsuarioSIMP lotacaoUsuarioSIMP = new LotacaoUsuarioSIMP();
                for (int i = 1; i <= columnCount; i++) {
                    lotacaoUsuarioSIMP.setLotacao(resultSet.getString(i));
                }
                listaLotacao.add(lotacaoUsuarioSIMP);
            }
            
            //Mostrar lotação usuário
            StringBuilder saidaString = new StringBuilder();
            listaLotacao.forEach((txt) -> {
                saidaString.append(txt.getLotacao());
                saidaString.append("\n");
            });
            jTextAreaLotacaoUsuario.setText(saidaString.toString());
            jTextAreaLotacaoUsuario.moveCaretPosition(0); //mover o cursor para o início
           
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarInfoGedoc(String login) {

        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<InfoGedoc> listaInfoGedoc = new ArrayList();
        if (!(login.equals(""))) {
            try {
                Consultas consulta = new Consultas();
                // A PARTIR DAQUI, DEVE TRATAR QUAL CONSULTA SERÁ FEITA NO BANCO BASEADO 
                // NO CAMPO QUE FOI INSERIDO O VALOR
                consulta.consultarUsuarioGedoc(login);
                ResultSet resultSet = statement.executeQuery(consulta.getQuery());

                metaRS = resultSet.getMetaData();
                columnCount = metaRS.getColumnCount();

                while (resultSet.next()) { //Percorre todas as linhas do resultado da consulta ao banco
                    InfoGedoc infoGedoc = new InfoGedoc(); //Instancia a classe que armazenará os valores de cada registro numa linha
                    for (int i = 1; i <= columnCount; i++) { //Percorre cada registro de uma linha
                        switch (i) { //Verifica o que é cada registro para armazenar no local correto
                            case 1:
                                infoGedoc.setIdUsuarioGedoc(resultSet.getInt(i));
                                break;
                            case 2:
                                infoGedoc.setIdLotacao(resultSet.getInt(i));
                                break;
                            case 3:
                                infoGedoc.setDescLotacao(resultSet.getString(i));
                                break;
                            case 4:
                                infoGedoc.setAutorizaDespacho(resultSet.getBoolean(i));
                                break;
                            case 5:
                                infoGedoc.setDistribuir(resultSet.getBoolean(i));
                                break;
                        }
                    }
                    listaInfoGedoc.add(infoGedoc); //Adiciona as linhas da tabela no Array promotorVinculadoLista
                }

                ListarInfoGedocTableModel tableModel = new ListarInfoGedocTableModel(listaInfoGedoc);
                jTableGEDOC.setModel(tableModel);
                jTableGEDOC.setDefaultRenderer(Object.class, new ListarInfoGedocTableCellRenderer());
//                jTableGEDOC.setRowSelectionAllowed(true);;
                //configurar largura da coluna
                jTableGEDOC.getColumnModel().getColumn(0).setPreferredWidth(170);
                jTableGEDOC.getColumnModel().getColumn(1).setPreferredWidth(200);
                jTableGEDOC.getColumnModel().getColumn(2).setPreferredWidth(500);
                jTableGEDOC.getColumnModel().getColumn(3).setPreferredWidth(140);
                jTableGEDOC.getColumnModel().getColumn(4).setPreferredWidth(140);
                
            } catch (SQLException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //USAR ESTE MÉTODO PARA ATUALIZAR TODOS OS CAMPOS A TELA
    public void atualizarCamposTabUsuario(int linha, int ultimoFocus) {
        switch (ultimoFocus) {
            case LOGINFOCUSLOST: {
                jTextFieldNome.setText(jTableConsultaUsuario.getValueAt(linha, 1).toString());
                jTextFieldMatricula.setText(jTableConsultaUsuario.getValueAt(linha, 2).toString());
                jTextFieldVinculo.setText(jTableConsultaUsuario.getValueAt(linha, 3).toString());
                jTextFieldEmail.setText(jTableConsultaUsuario.getValueAt(linha, 4).toString());
                jTextFieldCpf.setText(jTableConsultaUsuario.getValueAt(linha, 5).toString());
            }
            break;
            case NOMEFOCUSLOST: {
                jTextFieldLogin.setText(jTableConsultaUsuario.getValueAt(linha, 0).toString());
                jTextFieldMatricula.setText(jTableConsultaUsuario.getValueAt(linha, 2).toString());
                jTextFieldVinculo.setText(jTableConsultaUsuario.getValueAt(linha, 3).toString());
                jTextFieldEmail.setText(jTableConsultaUsuario.getValueAt(linha, 4).toString());
                jTextFieldCpf.setText(jTableConsultaUsuario.getValueAt(linha, 5).toString());
            }
            break;
            default:{
                jTextFieldLogin.setText(jTableConsultaUsuario.getValueAt(linha, 0).toString());
                jTextFieldNome.setText(jTableConsultaUsuario.getValueAt(linha, 1).toString());
                jTextFieldMatricula.setText(jTableConsultaUsuario.getValueAt(linha, 2).toString());
                jTextFieldVinculo.setText(jTableConsultaUsuario.getValueAt(linha, 3).toString());
                jTextFieldEmail.setText(jTableConsultaUsuario.getValueAt(linha, 4).toString());
                jTextFieldCpf.setText(jTableConsultaUsuario.getValueAt(linha, 5).toString());
            }
        }
        
        listarPermissaoUsuarioMastiff(jTableConsultaUsuario.getValueAt(linha, 0).toString());
        listarLotacaoUsuarioSIMP(jTableConsultaUsuario.getValueAt(linha, 0).toString());
        listarInfoGedoc(jTableConsultaUsuario.getValueAt(linha, 0).toString());

    }

    private String tratarParamSQL(String parametro) {
        StringBuilder str = new StringBuilder();

        parametro = parametro.trim().replace(" ", "%");

        str.append("%");
        str.append(parametro);
        str.append("%");
        return str.toString();
    }
    
    //Evento disparar ação ao selecionar as linhas da tabela
    private void tableListSelectionListener() {
        jTableConsultaUsuario.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
            }
        });
    }

}
