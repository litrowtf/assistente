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
public class ListarInfoGedocTableModel extends AbstractTableModel{

    private ArrayList<InfoGedoc> linhas;
    private String[] colunas = new String[]{"ID Usuário", "ID Lotação", "Descrição Lotação", "Autoriza Despacho", "Distribuir"}; //Array de String que define o nome das colunas da tabela

    public ListarInfoGedocTableModel() {
        linhas = new ArrayList<InfoGedoc>();
    }

    // Construtor contendo a Array dos dados que serão apresentados, recebida por parâmetro
    public ListarInfoGedocTableModel(ArrayList<InfoGedoc> infoGedoc) {
        linhas = new ArrayList<InfoGedoc>(infoGedoc);
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
        InfoGedoc infoGedoc = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return infoGedoc.getIdUsuarioGedoc();
            case 1:
                return infoGedoc.getIdLotacao();
            case 2:
                return infoGedoc.getDescLotacao();
            case 3:
                return infoGedoc.isAutorizaDespacho();
            case 4:
                return infoGedoc.isDistribuir();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

//Define o tipo de dados de cada coluna   
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return int.class;
            case 1:
                return int.class;
            case 2:
                return String.class;
            case 3:
                return boolean.class;
            case 4:
                return boolean.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");//não deve ocorrer
        }
    }

//Define o nome das colunas
    @Override
    public String getColumnName(int num) {
        return this.colunas[num];
    }


    
}
