/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Modelo.ConsultaUsuario;
import Modelo.ConsultaUsuarioTableModel;
import Modelo.Consultas;
import Modelo.CoordenadoriaUsuarioSIMP;
import Modelo.InfoGedoc;
import Modelo.ListarInfoGedocTableCellRenderer;
import Modelo.ListarInfoGedocTableModel;
import Modelo.LotacaoUsuarioSIMP;
import Modelo.PermissaoMastiffUsuario;
import Modelo.RegraMastiffUsuario;
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
    private JTextField jTextFieldLogin;
    private JTextField jTextFieldNome;
    private JTextField jTextFieldMatricula;
    private JTextField jTextFieldVinculo;
    private JTextField jTextFieldEmail;
    private JTextField jTextFieldCpf;
    private JTextArea jTextAreaCadMastiff;
    private JTextArea jTextAreaRegraMastiff;
    private JTextArea jTextAreaLotacaoUsuario;
    private JTextArea jTextAreaCadCoordenador;
    private JTable jTableConsultaUsuario;
    private JTable jTableGEDOC;
//    private int ultimoFocus;
    private int count1 = 0, 
            count2 = 0, 
            count3 = 0, 
            count4 = 0,
            count5 = 0,
            count6 = 0,
            count7 = 0,
            count8 = 0,
            count9 = 0,
            totalObjInstanciados = 0;
    private Statement statement;
    private Consultas consulta;

    /**
     * 
     * @param jTextFieldLogin
     * @param jTextFieldNome
     * @param jTextFieldMatricula
     * @param jTextFieldVinculo
     * @param jTextFieldEmail
     * @param jTextFieldCpf
     * @param jTableConsultaUsuario
     * @param jTableGEDOC
     * @param jTextAreaCadMastiff
     * @param jTextAreaRegraMastiff
     * @param jTextAreaLotacaoUsuario
     * @param jTextAreaCadCoordenador
     * @param statement
     * @param ultimoFocus 
     */
    
    public UsuariosAction(JTextField jTextFieldLogin,
            JTextField jTextFieldNome,
            JTextField jTextFieldMatricula,
            JTextField jTextFieldVinculo,
            JTextField jTextFieldEmail,
            JTextField jTextFieldCpf,
            JTable jTableConsultaUsuario,
            JTable jTableGEDOC,
            JTextArea jTextAreaCadMastiff, 
            JTextArea jTextAreaRegraMastiff, 
            JTextArea jTextAreaLotacaoUsuario, 
            JTextArea jTextAreaCadCoordenador, 
            Statement statement,
            int ultimoFocus) {
        System.out.println("Atribuindo valores às variàveis do construtor UsuariosAction: " + count1++ +" - total instanciado: " +totalObjInstanciados++);
        this.jTextFieldLogin = jTextFieldLogin;
        this.jTextFieldNome = jTextFieldNome;
        this.jTextFieldMatricula = jTextFieldMatricula;
        this.jTextFieldVinculo = jTextFieldVinculo;
        this.jTextFieldEmail = jTextFieldEmail;
        this.jTextFieldCpf = jTextFieldCpf;
        this.jTextAreaCadMastiff = jTextAreaCadMastiff;
        this.jTextAreaRegraMastiff = jTextAreaRegraMastiff;
        this.jTextAreaLotacaoUsuario = jTextAreaLotacaoUsuario;
        this.jTextAreaCadCoordenador = jTextAreaCadCoordenador;
        this.jTableConsultaUsuario = jTableConsultaUsuario;
        this.jTableGEDOC = jTableGEDOC;
        this.statement = statement;
//        this.ultimoFocus = ultimoFocus;
        

            consulta = new Consultas();
            
    }

    
    
    /**
     * Consulta usuário.
     * @param paramConsulta
     * @param campoConsultado 
     */
    
    public void consultarUsuarioAction(String paramConsulta, int campoConsultado) {
        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<ConsultaUsuario> consultaUsuarioLista = new ArrayList();
        paramConsulta = tratarParamSQL(paramConsulta);
        
        if (!(paramConsulta.equals(""))) {
            try {
//                Consulta consulta = new Consultas();
                // A PARTIR DAQUI, DEVE TRATAR QUAL CONSULTA SERÁ FEITA NO BANCO BASEADO 
                // NO CAMPO QUE FOI INSERIDO O VALOR
                consulta.selecionarConsulta(Consultas.CONSULTARUSUARIO, paramConsulta, campoConsultado);
                ResultSet resultSet = statement.executeQuery(consulta.getQuery());

                metaRS = resultSet.getMetaData();
                columnCount = metaRS.getColumnCount();

                while (resultSet.next()) { //Percorre todas as linhas do resultado da consulta ao banco
                    System.out.println("Quantas vezes ConsultaUsuario() foi instanciado: " + count2++ +" - total instanciado: " +totalObjInstanciados++);
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
                            default:
                                System.out.println("método consultarUsuarioAction \n Case: default");
                                break;
                        }
                    }
                    consultaUsuarioLista.add(consultaUsuario); //Adiciona as linhas da tabela no Array promotorVinculadoLista
                }
                System.out.println("Quantas vezes ConsultaUsuarioTableModel foi instanciado: " + count3++ +" - total instanciado: " +totalObjInstanciados++);
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
//                    atualizarCamposTabUsuario(jTableConsultaUsuario.getSelectedRow(), campoConsultado);
                    jTableConsultaUsuario.requestFocus();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**Permissão Mastiff
     * Consular permissões do usuário no Mastiff.
     * @param login 
     */
    public void listarPermissaoUsuarioMastiff(String login) {
        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<PermissaoMastiffUsuario> listaPermissao = new ArrayList();
        try {
//            System.out.println("Quantas vezes Consultas() foi instanciado: " + count3++ +" - total instanciado: " +totalObjInstanciados++);
//            Consulta consulta = new Consultas();

            consulta.consultarUsuarioPermissaoMastiff(login);
            ResultSet resultSet = statement.executeQuery(consulta.getQuery());

            metaRS = resultSet.getMetaData();
            columnCount = metaRS.getColumnCount();

            while (resultSet.next()) {
//                System.out.println("Quantas vezes PermissaoMastiffUsuario() foi instanciado: " + count4++ +" - total instanciado: " +totalObjInstanciados++);
                PermissaoMastiffUsuario permissaoMastiffUsuario = new PermissaoMastiffUsuario();
                for (int i = 1; i <= columnCount; i++) {
                    permissaoMastiffUsuario.setPermissaoMastiff(resultSet.getString(i));
                }
                listaPermissao.add(permissaoMastiffUsuario);
            }
            
            //Mostrar permissões Mastiff
//            System.out.println("Quantas vezes StringBuilder() foi instanciado: " + count5++ +" - total instanciado: " +totalObjInstanciados++);
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
    
    /**Permissão Mastiff
     * Consular permissões do usuário no Mastiff.
     * @param login 
     */
    public void listarRegraUsuarioMastiff(String login) {
        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<RegraMastiffUsuario> listaRegra = new ArrayList();
        try {
//            System.out.println("Quantas vezes Consultas() foi instanciado: " + count3++ +" - total instanciado: " +totalObjInstanciados++);
//            Consulta consulta = new Consultas();

            consulta.consultarUsuarioRegraMastiff(login);
            ResultSet resultSet = statement.executeQuery(consulta.getQuery());

            metaRS = resultSet.getMetaData();
            columnCount = metaRS.getColumnCount();

            while (resultSet.next()) {
//                System.out.println("Quantas vezes PermissaoMastiffUsuario() foi instanciado: " + count4++ +" - total instanciado: " +totalObjInstanciados++);
                RegraMastiffUsuario regraMastiffUsuario = new RegraMastiffUsuario();
                for (int i = 1; i <= columnCount; i++) {
                    regraMastiffUsuario.setRegraMastiff(resultSet.getString(i));
                }
                listaRegra.add(regraMastiffUsuario);
            }
            
            //Mostrar permissões Mastiff
//            System.out.println("Quantas vezes StringBuilder() foi instanciado: " + count5++ +" - total instanciado: " +totalObjInstanciados++);
            StringBuilder saidaString = new StringBuilder();
            listaRegra.forEach((txt) -> {
                saidaString.append(txt.getRegraMastiff());
                saidaString.append("\n");
            });
            jTextAreaRegraMastiff.setText(saidaString.toString());
            jTextAreaRegraMastiff.moveCaretPosition(0); //mover o cursor para o início
           
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**Lotação usuário
     * Lista os locais onde  usuário está lotado.
     * @param login 
     */
    public void listarLotacaoUsuarioSIMP(String login) {
        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<LotacaoUsuarioSIMP> listaLotacao = new ArrayList();

        try {
//            System.out.println("Quantas vezes Consultas() foi instanciado: " + count3++ +" - total instanciado: " +totalObjInstanciados++);

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
            System.out.println("Quantas vezes StringBuilder() foi instanciado: " + count5++ +" - total instanciado: " +totalObjInstanciados++);
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
    
    /** Coordenador usuário
     * Listar locais onde o usuário está cadastrado como coordenador
     * @param login 
     */
    public void listarCoordenadoiaUsuario(String login) {
        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<CoordenadoriaUsuarioSIMP> listaCoordUsuario = new ArrayList();

        try {
//            System.out.println("Quantas vezes Consultas() foi instanciado: " + count3++ +" - total instanciado: " +totalObjInstanciados++);

            consulta.consultarUsuarioCoordenador(login);
            ResultSet resultSet = statement.executeQuery(consulta.getQuery());

            metaRS = resultSet.getMetaData();
            columnCount = metaRS.getColumnCount();

            while (resultSet.next()) {
                CoordenadoriaUsuarioSIMP coordenadoriaUsuarioSIMP = new CoordenadoriaUsuarioSIMP();
                for (int i = 1; i <= columnCount; i++) {
                    coordenadoriaUsuarioSIMP.setCoordenacao(resultSet.getString(i));
                }
                listaCoordUsuario.add(coordenadoriaUsuarioSIMP);
            }
            
            //Mostrar lotação usuário
            System.out.println("Quantas vezes StringBuilder() foi instanciado: " + count5++ +" - total instanciado: " +totalObjInstanciados++);
            StringBuilder saidaString = new StringBuilder();
            listaCoordUsuario.forEach((txt) -> {
                saidaString.append(txt.getCoordenacao());
                saidaString.append("\n");
            });
            jTextAreaCadCoordenador.setText(saidaString.toString());
            jTextAreaCadCoordenador.moveCaretPosition(0); //mover o cursor para o início
           
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**Listar informações GEDOC
     * Lista idUsuario, idLotação, desccrição da lotação, habilitação para despacho e habilitação para distribuição.
     * @param login 
     */
    public void listarInfoGedoc(String login) {

        final ResultSetMetaData metaRS;
        final int columnCount;
        ArrayList<InfoGedoc> listaInfoGedoc = new ArrayList();
        if (!(login.equals(""))) {
            try {
//                System.out.println("Quantas vezes Consultas() foi instanciado: " + count3++ +" - total instanciado: " +totalObjInstanciados++);
//                Consulta consulta = new Consultas();
                // A PARTIR DAQUI, DEVE TRATAR QUAL CONSULTA SERÁ FEITA NO BANCO BASEADO 
                // NO CAMPO QUE FOI INSERIDO O VALOR
                consulta.consultarUsuarioGedoc(login);
                ResultSet resultSet = statement.executeQuery(consulta.getQuery());

                metaRS = resultSet.getMetaData();
                columnCount = metaRS.getColumnCount();

                while (resultSet.next()) { //Percorre todas as linhas do resultado da consulta ao banco
                    System.out.println("Quantas vezes infoGedoc() foi instanciado: " + count6++ +" - total instanciado: " +totalObjInstanciados++);
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
                            default:
                                System.out.println("método listaInfoGedoc \n Case: default");
                                break;
                            
                        }
                    }
                    listaInfoGedoc.add(infoGedoc); //Adiciona as linhas da tabela no Array promotorVinculadoLista
                }
                System.out.println("Quantas vezes ListarInfoGedocTableModel(listaInfoGedoc) foi instanciado: " + count7++ +" - total instanciado: " +totalObjInstanciados++);
                ListarInfoGedocTableModel tableModel = new ListarInfoGedocTableModel(listaInfoGedoc);
                jTableGEDOC.setModel(tableModel);
                System.out.println("Quantas vezes ListarInfoGedocTableCellRenderer() foi instanciado: " + count8++ +" - total instanciado: " +totalObjInstanciados++);
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

    /**
     * Atualizar todos os campos da tela
     * @param linha
     * @param ultimoFocus 
     */
    public void atualizarCamposTabUsuario(int linha, int ultimoFocus) {
        
        String login, nome, matricula, vinculo, email, cpf;
        
        login = jTableConsultaUsuario.getValueAt(linha, 0) == null ? getSemRegistroTxt() : jTableConsultaUsuario.getValueAt(linha, 0).toString();
        nome = jTableConsultaUsuario.getValueAt(linha, 1) == null ? getSemRegistroTxt() : jTableConsultaUsuario.getValueAt(linha, 1).toString();
        matricula = jTableConsultaUsuario.getValueAt(linha, 2) == null ? getSemRegistroTxt() : jTableConsultaUsuario.getValueAt(linha, 2).toString();
        vinculo = jTableConsultaUsuario.getValueAt(linha, 3) == null ? getSemRegistroTxt() : jTableConsultaUsuario.getValueAt(linha, 3).toString();
        email = jTableConsultaUsuario.getValueAt(linha, 4) == null ? getSemRegistroTxt() : jTableConsultaUsuario.getValueAt(linha, 4).toString();
        cpf = jTableConsultaUsuario.getValueAt(linha, 5) == null ? getSemRegistroTxt() : jTableConsultaUsuario.getValueAt(linha, 5).toString();

        
        switch (ultimoFocus) {
            case LOGINFOCUSLOST: {
                jTextFieldNome.setText(nome);
                jTextFieldMatricula.setText(matricula);
                jTextFieldVinculo.setText(vinculo);
                jTextFieldEmail.setText(email);
                jTextFieldCpf.setText(cpf);
            }
            break;
            case NOMEFOCUSLOST: {
                jTextFieldLogin.setText(login);
                jTextFieldMatricula.setText(matricula);
                jTextFieldVinculo.setText(vinculo);
                jTextFieldEmail.setText(email);
                jTextFieldCpf.setText(cpf);
            }
            break;
            default:{
                jTextFieldLogin.setText(login);
                jTextFieldNome.setText(nome);
                jTextFieldMatricula.setText(matricula);
                jTextFieldVinculo.setText(vinculo);
                jTextFieldEmail.setText(email);
                jTextFieldCpf.setText(cpf);
            }
        }
        if (!login.equals(getSemRegistroTxt())) {
            listarPermissaoUsuarioMastiff(login);
            listarRegraUsuarioMastiff(login);
            listarLotacaoUsuarioSIMP(login);
            listarCoordenadoiaUsuario(login);
            listarInfoGedoc(login);
        } else{
            jTextAreaCadMastiff.setText("");
            jTextAreaRegraMastiff.setText("");
            jTextAreaLotacaoUsuario.setText("");
            jTextAreaCadCoordenador.setText("");
            ListarInfoGedocTableModel tableModel = new ListarInfoGedocTableModel();
                jTableGEDOC.setModel(tableModel);
        }
    }
    
    public String getSemRegistroTxt(){
        return "Sem registro";
    }

    private String tratarParamSQL(String parametro) {
        System.out.println("Quantas vezes StringBuilder() foi instanciado: " + count5++ +" - total instanciado: " +totalObjInstanciados++);
        StringBuilder str = new StringBuilder();

        parametro = parametro.trim().replace(" ", "%");

        str.append("%");
        str.append(parametro);
        str.append("%");
        return str.toString();
    }
    
    //Evento disparar ação ao selecionar as linhas da tabela
    private void tableListSelectionListener() {
        System.out.println("Quantas vezes ListSelectionListener() foi instanciado: " + count9++ +" - total instanciado: " +totalObjInstanciados++);
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
