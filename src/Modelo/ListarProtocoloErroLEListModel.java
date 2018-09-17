/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.AbstractListModel;

/**
 *
 * @author leandro
 */
public class ListarProtocoloErroLEListModel extends AbstractListModel{
    
    private ArrayList<ProtocoloErroLE> linhas;
    
    public ListarProtocoloErroLEListModel(){
        linhas = new ArrayList<ProtocoloErroLE>();
    }
    
    public ListarProtocoloErroLEListModel(ArrayList<ProtocoloErroLE> protocoloerroLE){
        linhas = new ArrayList<ProtocoloErroLE>(protocoloerroLE);
    }
    
    @Override
    public int getSize() {
        return linhas.size();
    }

    @Override
    public String getElementAt(int index) {
        ProtocoloErroLE protocoloErroLE = linhas.get(index);
        return protocoloErroLE.getNumeroRegistroErroLE();
    }
    
    public void removeElementAt(int[] selecionados) {
        int size = selecionados.length - 1;
        for (int i = size; i >= 0; i--) {
            linhas.remove(selecionados[i]);
        }
    }
    public void removeAllElements() {
        int size = linhas.size() - 1;
        for (int i = size; i >= 0; i--) {
            linhas.remove(i);
        }
    }
    
    public void atualizarLista(){
        super.fireContentsChanged(this, linhas.size(), linhas.size());
    }    
}
