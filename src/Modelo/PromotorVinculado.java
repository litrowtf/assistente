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
public class PromotorVinculado {
    
    private int idPromotor;
    private String descPromotor;
    private int idLocal;
    private String descLocal;
    private String descComarca;

    public int getIdPromotor() {
        return idPromotor;
    }

    public void setIdPromotor(int idPromotor) {
        this.idPromotor = idPromotor;
    }

    public String getDescPromotor() {
        return descPromotor;
    }

    public void setDescPromotor(String nomePromotor) {
        this.descPromotor = nomePromotor;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getDescLocal() {
        return descLocal;
    }

    public void setDescLocal(String descLocal) {
        this.descLocal = descLocal;
    }

    public String getDescComarca() {
        return descComarca;
    }

    public void setDescComarca(String descComarca) {
        this.descComarca = descComarca;
    }
}
