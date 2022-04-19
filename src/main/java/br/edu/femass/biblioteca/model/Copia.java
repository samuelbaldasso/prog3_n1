package br.edu.femass.biblioteca.model;

public class Copia {
    private static Integer proximoCodigo = 1;
    private Integer codigo;
    private Boolean fixo;
    private Boolean disponivel;
    public Copia() {
        this.codigo = proximoCodigo;
        proximoCodigo++;
        this.fixo = false;
        this.disponivel = true;
    }
    public static Integer getProximoCodigo() {return proximoCodigo;}
    public Integer getCodigo() {return codigo;}
    public void setCodigo(Integer codigo) {this.codigo = codigo;}
    public Boolean getFixo() {return fixo;}
    public void setFixo(Boolean fixo) {this.fixo = fixo;}
    public Boolean getDisponivel() {return disponivel;}
    public void setDisponivel(Boolean disponivel) {this.disponivel = disponivel;}

    @Override
    public String toString() {
        return "Copia: " +
                "fixa = " + fixo +
                ", disponivel = " + disponivel;
    }
}
