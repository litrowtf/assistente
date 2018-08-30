/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author leandro
 */
public class ConsultaUsuarioTableModel extends AbstractTableModel {

    private ArrayList<ConsultaUsuario> linhas;
    private String[] colunas = new String[]{"Login", "Nome", "Matricula", "Vinculo", "E-mail", "CPF"}; //Array de String que define o nome das colunas da tabela
    public static final int LOGIN = 0;
    public static final int NOME = 1;
    public static final int MATRICULA = 2;
    public static final int VINCULO = 3;
    public static final int EMAIL = 4;
    public static final int CPF = 5;

    public ConsultaUsuarioTableModel() {
        linhas = new ArrayList<ConsultaUsuario>();
    }

    // Construtor contendo a Array dos dados que serão apresentados, recebida por parâmetro
    public ConsultaUsuarioTableModel(ArrayList<ConsultaUsuario> consultaUsuario) {
        linhas = new ArrayList<ConsultaUsuario>(consultaUsuario);
    }

    //Retorna o número de linhas da tabela
    @Override
    public int getRowCount() {
        return linhas.size();
    }

//Retorna o número de colunas da tabela
    @Override
    public int getColumnCount() {
        return colunas.length;
    }
//Retorna o valor da célula correspondente aos valores de linha e coluna fornecidos por parâmetro

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ConsultaUsuario consultaUsuario = linhas.get(rowIndex);

        switch (columnIndex) {
            case LOGIN:
                return consultaUsuario.getLogin();
            case NOME:
                return consultaUsuario.getNome();
            case MATRICULA:
                return consultaUsuario.getMatricula();
            case VINCULO:
                return consultaUsuario.getVinculo();
            case EMAIL:
                return consultaUsuario.getEmail();
            case CPF:
                return consultaUsuario.getCpf();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

//Define o tipo de dados de cada coluna   
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case LOGIN:
                return String.class;
            case NOME:
                return String.class;
            case MATRICULA:
                return String.class;
            case VINCULO:
                return String.class;
            case EMAIL:
                return String.class;
            case CPF:
                return String.class;
            default:
                //não deve ocorrer
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

//Define o nome das colunas
    @Override
    public String getColumnName(int num) {
        return this.colunas[num];
    }

}
