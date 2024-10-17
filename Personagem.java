package geraFicha;

import java.util.Random; 

public class Personagem {
    private String nome; 
    private String classe;
    private int vida; 
    private int forca;
    private int destreza; 
    private int inteligencia; 

    public Personagem(String nome, String classe) {
        this.nome = nome; 
        this.classe = classe; 
        gerarAtributos(); 
    }

    private void gerarAtributos() {
        Random random = new Random(); 

        int baseVida = 10; 
        int dadoVida = random.nextInt(10) + 1; 
        this.vida = baseVida + dadoVida; 

        this.forca = random.nextInt(6) + 1; 
        this.destreza = random.nextInt(6) + 1; 
        this.inteligencia = random.nextInt(6) + 1; 
    }

    public String getNome() {
        return nome;
    }

    public String getClasse() {
        return classe;
    }

    public int getVida() {
        return vida;
    }

    public int getForca() {
        return forca;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getInteligencia() {
        return inteligencia;
    }
}
