package sample.model;

import java.time.LocalDate;

public class ItemHistorique {
    private String produitName;
    private String type;
    private int quantiteGasp;
    private LocalDate dateAchat;

    public ItemHistorique(String produitName, String type, int quantiteGasp, LocalDate dateAchat) {
        this.produitName = produitName;
        this.type = type;
        this.quantiteGasp = quantiteGasp;
        this.dateAchat = dateAchat;
    }

    public String getProduitName() {
        return produitName;
    }

    public void setProduitName(String produitName) {
        this.produitName = produitName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantiteGasp() {
        return quantiteGasp;
    }

    public void setQuantiteGasp(int quantiteGasp) {
        this.quantiteGasp = quantiteGasp;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }
}
