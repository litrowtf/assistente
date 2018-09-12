/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author leandro
 */
public class Consultas {

    private String query;
    public static final int REGISTRODISPONIVEL = 1;
    public static final int PROMOTORVINCULADO = 2;
    public static final int CONSULTARUSUARIO = 3;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void selecionarConsulta(int opcao) {
        
        selecionarConsulta(opcao, null, 0);
        
    }
    
    public void selecionarConsulta(int opcao, String param1) {
        
        selecionarConsulta(opcao, param1, 0);
        
    }

    
    /** Selecionar Consulta.
     * Esse método servirá para selecionar a consulta dependendo da opção escolhida
     * @param opcao
     * @param param1
     * @param tipoConsulta 
     */
    public void selecionarConsulta(int opcao, String param1, int tipoConsulta) {
        switch (opcao) {

            case REGISTRODISPONIVEL: {
                registroDisponivel();
                break;
            }

            case PROMOTORVINCULADO: {
                promotorVinculadoLocal(param1);
                break;
            }

            case CONSULTARUSUARIO: {
                consultarUsuarios(param1, tipoConsulta);
                break;
            }
            default:
                System.out.println("método selecionarConsulta \n Case: default");
                break;

        }
    }

    public void registroDisponivel() {
        setQuery("select * "
                + "from  (Select Rownum NUMEROLOCALREGISTRO_DISPONIVEL "
                + "      From dual "
                + "      Connect By Rownum < 100) b "
                + "where 1=1 "
                + "and   not EXISTS (select 1 "
                + "                  from mppa.local a "
                + "                  where a.NUMEROLOCALREGISTRO = b.NUMEROLOCALREGISTRO_DISPONIVEL)");
    }

    public void promotorVinculadoLocal(String local) {
        setQuery("select a.IDPROMOTOR, "
                + "d.NOME, "
                + "c.IDLOCAL, "
                + "c.DESCRICAO, "
                + "e.DESCRICAOCOMARCA COMARCA "
                + "from MPPA.PROMOTORLOCAL a, "
                + "MPPA.PROMOTOR b, "
                + "MPPA.LOCAL c, "
                + "MPPA.FUNCIONARIO d, "
                + "MPPA.COMARCA e "
                + "where a.IDPROMOTOR = b.IDPROMOTOR "
                + "and a.IDLOCAL = c.IDLOCAL "
                + "and b.IDFUNCIONARIO = d.IDFUNCIONARIO "
                + "and e.IDCOMARCA = c.IDCOMARCA "
                + "and upper(c.DESCRICAO) like upper('" + local + "') "
                + "order by  c.DESCRICAO");
    }
    
    public void consultarUsuarios(String paramConsulta, int tipoConsulta) {
        StringBuilder consulta = new StringBuilder();
        
        consulta.append("SELECT    VSER_LOGIN, "
                        + "         VSER_NM_SERVIDOR, "
                        + "         VSER_CD_MATRICULA, "
                        + "         VSER_TIPO_FUNC, "
                        + "         decode(VSER_CD_TIPO, 'E', 'estagiario@mppa.mp.br', 'F', 'estagiario@mppa.mp.br', VSER_EMAIL) e_mail, "
                        + "         regexp_replace(LPAD(VSER_CPF, 11, 0),'([0-9]{3})([0-9]{3})([0-9]{3})','\\1.\\2.\\3-') VSER_CPF "
                        + "FROM     INTEGRACAO_SQLSERVER.Tab_Pessoagolrh "
                        + "WHERE    VSER_CD_SITUACAO = 'A' ");
        
        
        switch(tipoConsulta){
            case 10:{
                consulta.append("AND      upper(VSER_LOGIN) LIKE upper('");
                consulta.append(paramConsulta);
                consulta.append("')");               
            }
                break;
            case 11:{
                consulta.append("AND      upper(VSER_NM_SERVIDOR) LIKE upper('");
                consulta.append(paramConsulta);
                consulta.append("')");               
            }
                break;
            case 12:{
                consulta.append("AND      VSER_CD_MATRICULA LIKE '");
                consulta.append(paramConsulta);
                consulta.append("'");
            }
                break;
            case 13:{
                consulta.append("AND      VSER_CPF LIKE '");
                consulta.append(paramConsulta);
                consulta.append("'");              
            }
                break;
        }
        
        setQuery(consulta.toString());
    }
    
    public void consultarUsuarioPermissaoMastiff(String login) {
        StringBuilder consulta = new StringBuilder();

        consulta.append("SELECT nome "
                + "FROM 	MPPA.USUARIOPERFILACESSO a, "
                + "		MPPA.PERFILACESSO b, "
                + "		mppa.USUARIO c "
                + "WHERE 	b.IDPERFILACESSO = a.IDPERFILACESSO "
                + "AND    	a.IDUSUARIO = c.IDUSUARIO "
                + "AND 	c.LOGIN LIKE '");
        consulta.append(login);
        consulta.append("'");

        setQuery(consulta.toString());
    }
    
    /** Regras Mastiff
     * Consultar regras cadastradas para o usuário no Mastiff.
     * @param login 
     */
    public void consultarUsuarioRegraMastiff(String login) {
        StringBuilder consulta = new StringBuilder();

        consulta.append("SELECT "
                + "	DECODE( a.IDREGRA, 1, 'Administrador', "
                + "	2, 'Processo Sigiloso', "
                + "	3, 'Movimentar Não Distribuídos', "
                + "	4, 'Arquivar Não Distribuídos', "
                + "	5, 'Anexar até 20Mb', "
                + "	6, 'Editar partes' )"
                + "FROM "
                + "	MPPA.USUARIOREGRA a, "
                + "	mppa.USUARIO c "
                + "WHERE "
                + "	a.IDUSUARIO = c.IDUSUARIO "
                + "	AND c.LOGIN LIKE '");
        consulta.append(login);
        consulta.append("'");

        setQuery(consulta.toString());
    }
    
    public void consultarUsuarioLotacao(String login) {
        StringBuilder consulta = new StringBuilder();

        consulta.append("SELECT b.DESCRICAO "
                + "FROM 	MPPA.LOCALUSUARIO a, "
                + "		MPPA.LOCAL b, "
                + "		MPPA.USUARIO c "
                + "WHERE 	a.IDLOCAL = b.IDLOCAL "
                + "AND		a.IDUSUARIO = c.IDUSUARIO "
                + "AND 	c.LOGIN LIKE '");
        consulta.append(login);
        consulta.append("'");

        setQuery(consulta.toString());
    }
    
    public void consultarUsuarioCoordenador(String login) {
        StringBuilder consulta = new StringBuilder();

        consulta.append("SELECT b.DESCRICAO "
                + "FROM 	MPPA.LOCALCOORDENADOR a, "
                + "		MPPA.LOCAL b, "
                + "		MPPA.USUARIO c "
                + "WHERE 	a.IDLOCAL = b.IDLOCAL "
                + "AND		a.IDUSUARIO = c.IDUSUARIO "
                + "AND 	c.LOGIN LIKE '");
        consulta.append(login);
        consulta.append("'");

        setQuery(consulta.toString());
    }
    
    public void consultarUsuarioGedoc(String login) {
        StringBuilder consulta = new StringBuilder();

        consulta.append("SELECT "
                + "	a.ID, "
                + "	b.FK_LOTACAO, "
                + "	c.TXT_NOME, "
                + "	a.BOO_AUTORIZA_DESPACHO, "
                + "	a.BOO_DISTRIBUIR "
                + "FROM "
                + "	gedoc.tb_usuario a "
                + "JOIN gedoc.tb_lotacao_usuario b ON "
                + "	b.fk_usuario = a.id "
                + "LEFT JOIN GEDOC.TB_LOTACAO c ON "
                + "	c.id = b.FK_LOTACAO "
                + "WHERE a.TXT_LOGIN LIKE '");
        consulta.append(login);
        consulta.append("'");

        setQuery(consulta.toString());
    }
    
    
    /** Protocolos Erro Local Externo
     * Consulta para busccar os protocolos que causo o erro: "LocalOrigemVO".
     * 
     * @param numeroRegistro
     * @param mostrarProtApen 
     */
    public void consultarProtocolosErroLE(String numeroRegistro, boolean mostrarProtApen) {
        StringBuilder consulta = new StringBuilder();

        consulta.append("SELECT NUMEROREGISTRO "
                + "FROM 	PROTOCOLO "
                + "WHERE 	SITUACAO = 4 "
                + "AND 	IDLOCALATUAL IS NOT NULL "
                + "AND 	IDDETENTORATUAL IS NOT NULL ");
        if (!mostrarProtApen) { //Se mostrarProtApen for true mostra todos os protocolos
            consulta.append("AND 	TIPOAPENSO = 1 ");
        }
        if (!numeroRegistro.isEmpty()) { //Se numeroRegistro for vazio, mostra todos os protocolos
            consulta.append("AND NUMEROREGISTRO LIKE '");
            consulta.append(numeroRegistro);
            consulta.append("'");
        }

        setQuery(consulta.toString());
    }
    
    
//    public void gerarMensagemSQL(String numeroRegistro) {
    /**Gerar mensagem SQL
     * 
     * @param numeroRegistro numero de registro do protocolo SIMP
     */
    public void gerarMensagemSQL(String numeroRegistro) {
        StringBuilder consulta = new StringBuilder();
        consulta.append("SELECT 'UPDATE \n");
        consulta.append("       PROTOCOLO \n");
        consulta.append("SET \n");
        consulta.append("       IDLOCALATUAL = NULL \n");
        consulta.append("       ,IDLOCALDESTINO = ' || IDLOCALDESTINO || '\n");
        consulta.append("       ,IDDETENTORATUAL = NULL \n");
        consulta.append("WHERE '");
        consulta.append("        || 'IDPROTOCOLO = ' || IDPROTOCOLO ");
        consulta.append("FROM (SELECT MAX(a.DATAHORA ), MAX(a.IDLOCALDESTINO) IDLOCALDESTINO, "
                + "MAX(b.IDPROTOCOLO) IDPROTOCOLO FROM MPPA.PROTOCOLOMOVIMENTO a, "
                + "MPPA.PROTOCOLO b "
                + "WHERE a.IDPROTOCOLO = "
                + "b.IDPROTOCOLO AND a.ATIVO = 2 " //apenas movimentos ativos
                + "AND a.IDMOVIMENTO = 920024 " //movimento de encaminhamento a orgão externo
//                + "AND b.SITUACAO != 3 " //não considerar protocolos autuados (cod 3)
                + "AND b.NUMEROREGISTRO LIKE '");
        consulta.append(numeroRegistro);
        consulta.append("' GROUP BY b.NUMEROREGISTRO)");

        setQuery(consulta.toString());
    }
}
