/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author leandro
 */
public class ListarInfoGedocTableCellRenderer implements TableCellRenderer {

    private final JLabel componenteRenderizador;
    private final Font font = Font.getFont(Font.DIALOG); //essa fonte é a fonte para informações padrão;

    public ListarInfoGedocTableCellRenderer() {
        this.componenteRenderizador = new JLabel();
        this.componenteRenderizador.setOpaque(true);
        this.componenteRenderizador.setFont(font); //Determina a fonte dos registros da tabela
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componenteRenderizador.setEnabled(true);        
        componenteRenderizador.setFocusable(true);        
        componenteRenderizador.setText(String.valueOf(value));
        componenteRenderizador.setForeground(getCorForeground(value));
        componenteRenderizador.setBackground(getCorBackground(isSelected));
//        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return componenteRenderizador;
    }

    private Color getCorForeground(Object value) {
        if (value instanceof Boolean) {

            if (value.equals(false)) {
                componenteRenderizador.setText("Não");
                return Color.RED;
            } else if (value.equals(true)) {
                componenteRenderizador.setText("Sim");
                return Color.BLUE;
            }
        }
        return Color.BLACK;

    }
    
    private Color getCorBackground(boolean isSelected) {
        
        if(isSelected){
            return Color.getHSBColor(10,155,98);            
        }
        return Color.WHITE;

    }

}
