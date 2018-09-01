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
public class PromotorVinculadoTableModel extends AbstractTableModel {

    // Lista de Registros a serem exibidos na tabela
    private ArrayList<PromotorVinculado> linhas;
    private String[] colunas = new String[] { "ID_PROMOTOR", "Promotor", "ID_LOCAL", "Local", "Comarca"}; //Array de String que define o nome das colunas da tabela
    public static final int IDPROMOTOR = 0;
    public static final int DESCPROMOTOR = 1;
    public static final int IDLOCAL = 2;
    public static final int DESCLOCAL = 3;
    public static final int COMARCA = 4;
    
    // Cria um NumeroRegistroTableModel sem nenhuma linha
    public PromotorVinculadoTableModel() {
        linhas = new ArrayList<>();
    }

    // Construtor contendo a Array dos dados que serão apresentados, recebida por parâmetro
    public PromotorVinculadoTableModel(ArrayList<PromotorVinculado> promotorVinculado) {
        linhas = new ArrayList<>(promotorVinculado);
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
        PromotorVinculado promotorVinculado = linhas.get(rowIndex);
        
        switch (columnIndex){
            case IDPROMOTOR:
                return promotorVinculado.getIdPromotor();
            case DESCPROMOTOR:
                return promotorVinculado.getDescPromotor();
            case IDLOCAL:
                return promotorVinculado.getIdLocal();
            case DESCLOCAL:
                return promotorVinculado.getDescLocal();
            case COMARCA:
                return promotorVinculado.getDescComarca();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");    
        }
    }
    
//Define o tipo de dados de cada coluna   
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case IDPROMOTOR:
                return Integer.class;
            case DESCPROMOTOR:
                return String.class;
            case IDLOCAL:
                return Integer.class;
            case DESCLOCAL:
                return String.class;
            case COMARCA:
                return String.class;
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