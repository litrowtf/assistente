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
public class InfoGedoc {
    
    private int idUsuarioGedoc;
    private int idLotacao;
    private String descLotacao;
    private boolean autorizaDespacho;
    private boolean distribuir;

    public int getIdUsuarioGedoc() {
        return idUsuarioGedoc;
    }

    public void setIdUsuarioGedoc(int idUsuarioGedoc) {
        this.idUsuarioGedoc = idUsuarioGedoc;
    }

    public int getIdLotacao() {
        return idLotacao;
    }

    public void setIdLotacao(int idLotacao) {
        this.idLotacao = idLotacao;
    }

    public String getDescLotacao() {
        return descLotacao;
    }

    public void setDescLotacao(String descLotacao) {
        this.descLotacao = descLotacao;
    }

    public boolean isAutorizaDespacho() {
        return autorizaDespacho;
    }

    public void setAutorizaDespacho(boolean autorizaDespacho) {
        this.autorizaDespacho = autorizaDespacho;
    }

    public boolean isDistribuir() {
        return distribuir;
    }

    public void setDistribuir(boolean distribuir) {
        this.distribuir = distribuir;
    }
    
    
    
}
