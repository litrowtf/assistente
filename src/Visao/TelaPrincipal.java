/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Conexao.ConexaoOracle;
import Controle.LocaisAction;
import Controle.UsuariosAction;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author leandro
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public static final int IDPROMOTOR = 1;
    public static final int DESCPROMOTOR = 2;
    public static final int IDLOCAL = 3;
    public static final int DESCLOCAL = 4;
    public static final int COMARCA = 5;
    public static final int CAMPOUSUARIOLOGIN = 10;
    public static final int CAMPOUSUARIONOME = 11;
    public static final int CAMPOUSUARIOMATRICULA = 12;
    public static final int CAMPOUSUARIOCPF = 13;
    private UsuariosAction usuarioAction;
    private int countTest1 = 0;
    private int countTest2 = 0;
    private int countTest3 = 0;
    private String utimoFoco = "";
    private static URL caminhoImagem;
    private String versao = "Alpha V1.2";
    private Connection conexao;
    private Statement statement;
    

    public TelaPrincipal() {
        initComponents();
        countTest2++;
//        System.out.println("Passou " + countTest2 + " vez(es) no public TelaPrincipal()");
        tableListSelectionListener();
        try {
            abrirConexao();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(TelaPrincipal.class.getName());
        }

        //Não mostrar tab "Protocolos" pois ainda não há nada implementado
        jTabbedPanePrincipal.removeTabAt(0);
//Usado para setar o atalho do Copiar, porém não funciou... ainda.        
//jMenuItemCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.ALT_MASK));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        jMenuItemCopiar = new javax.swing.JMenuItem();
        jTabbedPanePrincipal = new javax.swing.JTabbedPane();
        jTabbedPaneProtocolos = new javax.swing.JTabbedPane();
        jTabbedPaneUsuarios = new javax.swing.JTabbedPane();
        jPanelConsultarUsuario = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaCadMastiff = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaLotacaoUsuario = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableGEDOC = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableConsultaUsuario = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldLogin = new javax.swing.JTextField();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldMatricula = new javax.swing.JTextField();
        jTextFieldVinculo = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldCPF = new javax.swing.JTextField();
        jTabbedPaneLocais = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButtonBuscarRegistros = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableNumeroRegistro = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldConsultarLocalPromVinc = new javax.swing.JTextField();
        jButtonConsultarLocalPromVinc = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePromotorVinculado = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenuItemCopiar.setText("Copiar célula");
        jMenuItemCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCopiarActionPerformed(evt);
            }
        });
        jPopupMenu.add(jMenuItemCopiar);

        jPopupMenu.getAccessibleContext().setAccessibleParent(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(getVersao());
        setFocusTraversalPolicyProvider(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPaneProtocolos.setEnabled(false);
        jTabbedPaneProtocolos.setPreferredSize(new java.awt.Dimension(1010, 580));
        jTabbedPanePrincipal.addTab("Protocolos", jTabbedPaneProtocolos);

        jTabbedPaneUsuarios.setPreferredSize(new java.awt.Dimension(1010, 580));

        jPanelConsultarUsuario.setPreferredSize(new java.awt.Dimension(1000, 506));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Painel SIMP"));
        jPanel3.setName(""); // NOI18N

        jLabel9.setText("Cadastro Mastiff:");

        jTextAreaCadMastiff.setEditable(false);
        jTextAreaCadMastiff.setColumns(20);
        jTextAreaCadMastiff.setRows(5);
        jScrollPane4.setViewportView(jTextAreaCadMastiff);

        jLabel10.setText("Lotação usuário:");

        jTextAreaLotacaoUsuario.setEditable(false);
        jTextAreaLotacaoUsuario.setColumns(20);
        jTextAreaLotacaoUsuario.setRows(5);
        jScrollPane5.setViewportView(jTextAreaLotacaoUsuario);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane4, jScrollPane5});

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Painel GEDOC"));

        jTableGEDOC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableGEDOC.setComponentPopupMenu(jPopupMenu);
        jTableGEDOC.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableGEDOC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableGEDOCFocusLost(evt);
            }
        });
        jScrollPane6.setViewportView(jTableGEDOC);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Usuários"));

        jTableConsultaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableConsultaUsuario.setComponentPopupMenu(jPopupMenu);
        jTableConsultaUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableConsultaUsuarioFocusLost(evt);
            }
        });
        jScrollPane3.setViewportView(jTableConsultaUsuario);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Consulta"))));

        jLabel2.setText("Login:");

        jLabel3.setText("Nome:");

        jLabel4.setText("Matrícula:");

        jLabel5.setText("Vínculo:");

        jLabel6.setText("E-mail:");

        jLabel7.setText("CPF:");

        jTextFieldLogin.setBackground(new java.awt.Color(254, 255, 217));
        jTextFieldLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldLoginKeyPressed(evt);
            }
        });

        jTextFieldNome.setBackground(new java.awt.Color(254, 255, 217));
        jTextFieldNome.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextFieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNomeKeyPressed(evt);
            }
        });

        jTextFieldMatricula.setBackground(new java.awt.Color(254, 255, 217));
        jTextFieldMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldMatriculaKeyPressed(evt);
            }
        });

        jTextFieldVinculo.setEditable(false);
        jTextFieldVinculo.setBackground(new java.awt.Color(254, 254, 254));

        jTextFieldEmail.setEditable(false);
        jTextFieldEmail.setBackground(new java.awt.Color(254, 254, 254));

        jTextFieldCPF.setBackground(new java.awt.Color(254, 255, 217));
        jTextFieldCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCPFKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldVinculo)
                    .addComponent(jTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEmail))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldVinculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelConsultarUsuarioLayout = new javax.swing.GroupLayout(jPanelConsultarUsuario);
        jPanelConsultarUsuario.setLayout(jPanelConsultarUsuarioLayout);
        jPanelConsultarUsuarioLayout.setHorizontalGroup(
            jPanelConsultarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultarUsuarioLayout.createSequentialGroup()
                .addGroup(jPanelConsultarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelConsultarUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelConsultarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelConsultarUsuarioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelConsultarUsuarioLayout.setVerticalGroup(
            jPanelConsultarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultarUsuarioLayout.createSequentialGroup()
                .addGroup(jPanelConsultarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneUsuarios.addTab("Consultar usuário", jPanelConsultarUsuario);

        jTabbedPanePrincipal.addTab("Uusários", jTabbedPaneUsuarios);

        jTabbedPaneLocais.setPreferredSize(new java.awt.Dimension(1010, 580));

        jButtonBuscarRegistros.setText("Buscar registros");
        jButtonBuscarRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarRegistrosActionPerformed(evt);
            }
        });

        jTableNumeroRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableNumeroRegistro);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButtonBuscarRegistros)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonBuscarRegistros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneLocais.addTab("Numero registro", jPanel1);

        jLabel1.setText("Informar local:");

        jTextFieldConsultarLocalPromVinc.setBackground(new java.awt.Color(254, 255, 217));
        jTextFieldConsultarLocalPromVinc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldConsultarLocalPromVincKeyPressed(evt);
            }
        });

        jButtonConsultarLocalPromVinc.setText("Consultar");
        jButtonConsultarLocalPromVinc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarLocalPromVincActionPerformed(evt);
            }
        });

        jTablePromotorVinculado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTablePromotorVinculado);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldConsultarLocalPromVinc, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonConsultarLocalPromVinc, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldConsultarLocalPromVinc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConsultarLocalPromVinc))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextFieldConsultarLocalPromVinc.getAccessibleContext().setAccessibleName("");

        jTabbedPaneLocais.addTab("Promotor x Local", jPanel2);

        jTabbedPanePrincipal.addTab("Locais", jTabbedPaneLocais);

        getContentPane().add(jTabbedPanePrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1100, 710));
        jTabbedPanePrincipal.getAccessibleContext().setAccessibleDescription("");

        jMenu2.setText("Ajuda");

        jMenuItem1.setText("Sobre");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarRegistrosActionPerformed
        LocaisAction locaisAction = new LocaisAction(statement);
        locaisAction.buscarNumeroRegistroLocais(jTableNumeroRegistro);
    }//GEN-LAST:event_jButtonBuscarRegistrosActionPerformed

    private void jButtonConsultarLocalPromVincActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarLocalPromVincActionPerformed
        LocaisAction locaisAction = new LocaisAction(statement);
        locaisAction.consultarLocalAction(jTablePromotorVinculado, jTextFieldConsultarLocalPromVinc.getText());
    }//GEN-LAST:event_jButtonConsultarLocalPromVincActionPerformed

    private void jTextFieldConsultarLocalPromVincKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldConsultarLocalPromVincKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            LocaisAction locaisAction = new LocaisAction(statement);
            locaisAction.consultarLocalAction(jTablePromotorVinculado, jTextFieldConsultarLocalPromVinc.getText());
        }
    }//GEN-LAST:event_jTextFieldConsultarLocalPromVincKeyPressed

    private void jTextFieldLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evtjTextFieldUsuarioKeyPressed(jTextFieldLogin.getText(), CAMPOUSUARIOLOGIN);
        }     
    }//GEN-LAST:event_jTextFieldLoginKeyPressed

    private void jTextFieldNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evtjTextFieldUsuarioKeyPressed(jTextFieldNome.getText(), CAMPOUSUARIONOME);
        }
    }//GEN-LAST:event_jTextFieldNomeKeyPressed

    private void jTextFieldMatriculaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMatriculaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evtjTextFieldUsuarioKeyPressed(jTextFieldMatricula.getText(), CAMPOUSUARIOMATRICULA);   
        }
    }//GEN-LAST:event_jTextFieldMatriculaKeyPressed

    private void jTextFieldCPFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCPFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evtjTextFieldUsuarioKeyPressed(jTextFieldCPF.getText(), CAMPOUSUARIOCPF);
        }
    }//GEN-LAST:event_jTextFieldCPFKeyPressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        TelaAbout telaAbout = new TelaAbout();
        telaAbout.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCopiarActionPerformed
        if ((jTableConsultaUsuario.getSelectedRow() < 0 || jTableConsultaUsuario.getSelectedColumn() < 0) && 
                (jTableGEDOC.getSelectedRow() < 0 || jTableGEDOC.getSelectedColumn() < 0)) {
            JOptionPane.showMessageDialog(this, "Nenhuma célula foi selecionada.", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (getUtimoFoco().equals(this.getClass().getDeclaredFields()[48].getName())) { // pega nome do componente jTableConsultaUsuario
                String texto = validaString(jTableConsultaUsuario.getValueAt(jTableConsultaUsuario.getSelectedRow(), jTableConsultaUsuario.getSelectedColumn()));
                copyToClipboard(texto);
            } else if (getUtimoFoco().equals(this.getClass().getDeclaredFields()[49].getName())) {//pega nome do componente jTableGEDOC
                String texto = validaString(jTableGEDOC.getValueAt(jTableGEDOC.getSelectedRow(), jTableGEDOC.getSelectedColumn()));
                copyToClipboard(texto);
            }
        }
    }//GEN-LAST:event_jMenuItemCopiarActionPerformed

    private void jTableConsultaUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableConsultaUsuarioFocusLost
        setUtimoFoco(this.getClass().getDeclaredFields()[48].getName()); //48 é o ID do jTableConsultaUsuario
//        for(int i = 0; i <= this.getClass().getDeclaredFields().length ; i++){
//            System.out.println(this.getClass().getDeclaredFields()[i].getName() + i);
//        }
        
    }//GEN-LAST:event_jTableConsultaUsuarioFocusLost

    private void jTableGEDOCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableGEDOCFocusLost
        setUtimoFoco(this.getClass().getDeclaredFields()[49].getName()); //49 é o ID do jTableGEDOC
    }//GEN-LAST:event_jTableGEDOCFocusLost

    private String validaString(Object objeto) {
        if (objeto != null) {
            return objeto.toString();
        }
        return "";
    }
    
    private void copyToClipboard(String texto){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(texto);
        clipboard.setContents(selection, null);
    }
    
    private void evtjTextFieldUsuarioKeyPressed(String parametroConsulta, int campo) {
            usuarioAction = new UsuariosAction(jTextFieldLogin,
                    jTextFieldNome,
                    jTextFieldMatricula,
                    jTextFieldVinculo,
                    jTextFieldEmail,
                    jTextFieldCPF,
                    jTableConsultaUsuario,
                    jTableGEDOC,
                    jTextAreaCadMastiff,
                    jTextAreaLotacaoUsuario,
                    statement,
                    CAMPOUSUARIOCPF);
            

            usuarioAction.consultarUsuario(parametroConsulta, campo);
    }
    
    /**
     * @param args the command line arguments
     */
    
    private void tableListSelectionListener() {
        jTableConsultaUsuario.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
//                    countTest1++; 
//                    System.out.println("Passou "+countTest1+ " vez(es) no public void valueChanged(ListSelectionEvent e){ if (e.getValueIsAdjusting())");
                    return;
                }
//                System.out.println("Teste da condição if(usuarioAction != null){" + (usuarioAction != null));
                if (usuarioAction != null) {
//                    System.out.println("entrou!");
                    usuarioAction.atualizarCamposTabUsuario((jTableConsultaUsuario.getSelectedRow() == -1) ? 
                                                            0 : jTableConsultaUsuario.getSelectedRow(), 10); //10 é o LOGINFOCUSLOST da UsuarioAction
                }
//                countTest3++;
//                    System.out.println("Passou "+countTest3+ " vez(es) no public void valueChanged(ListSelectionEvent e){ fora do if");
            }
        });
    }

    public String getUtimoFoco() {
        return utimoFoco;
    }

    public void setUtimoFoco(String utimoFoco) {
        this.utimoFoco = utimoFoco;
    }
    
    //pega imagem do ícone do programa
    private Image getIcon() {
        caminhoImagem = this.getClass().getClassLoader().getResource("log.png");
        return Toolkit.getDefaultToolkit().getImage(caminhoImagem);
    }

    public String getVersao() {
        return "Support Assistant - "+versao;
    }

    private void abrirConexao() throws SQLException {
        //criar variável para verificar se a conexão foi bem sucedida
        if (conexao == null) {
            conexao = ConexaoOracle.ObterConexao();
            statement = conexao.createStatement();
        } else{
            statement = conexao.createStatement();
        }
    }
    
   
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarRegistros;
    private javax.swing.JButton jButtonConsultarLocalPromVinc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemCopiar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelConsultarUsuario;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPaneLocais;
    private javax.swing.JTabbedPane jTabbedPanePrincipal;
    private javax.swing.JTabbedPane jTabbedPaneProtocolos;
    private javax.swing.JTabbedPane jTabbedPaneUsuarios;
    private javax.swing.JTable jTableConsultaUsuario;
    private javax.swing.JTable jTableGEDOC;
    private javax.swing.JTable jTableNumeroRegistro;
    private javax.swing.JTable jTablePromotorVinculado;
    private javax.swing.JTextArea jTextAreaCadMastiff;
    private javax.swing.JTextArea jTextAreaLotacaoUsuario;
    private javax.swing.JTextField jTextFieldCPF;
    private javax.swing.JTextField jTextFieldConsultarLocalPromVinc;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldMatricula;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldVinculo;
    // End of variables declaration//GEN-END:variables

}
