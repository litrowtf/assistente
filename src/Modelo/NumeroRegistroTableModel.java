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
public class NumeroRegistroTableModel extends AbstractTableModel {

    // Lista de Registros a serem exibidos na tabela
    private ArrayList<NumeroRegistro> linhas;
    private String[] colunas = new String[] { "Numero Registro"}; //Array de String que define o nome das colunas da tabela
    private static final int NUMEROREGISTRO = 0;
    
    // Cria um NumeroRegistroTableModel sem nenhuma linha
    public NumeroRegistroTableModel() {
        linhas = new ArrayList<NumeroRegistro>();
    }

    // Construtor contendo a Array dos dados que serão apresentados recebida por parâmetro
    public NumeroRegistroTableModel(ArrayList<NumeroRegistro> listaDeRegistros) {
        linhas = new ArrayList<NumeroRegistro>(listaDeRegistros);
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
        NumeroRegistro numeroRegistro = linhas.get(rowIndex);
        
        switch (columnIndex){
            case NUMEROREGISTRO:
                return numeroRegistro.getNumReg();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");    
        }
    }
    
//Define o tipo de dados de cada coluna   
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case NUMEROREGISTRO:
                return Integer.class;
            default:
                //não deve ocorrer
            throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

//Define o nome das colunas
    @Override
    public String getColumnName(int num){
        return this.colunas[num];
    }

}