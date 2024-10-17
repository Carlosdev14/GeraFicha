package geraFicha;

import java.util.Random; 
import java.util.Scanner; 

public class Atributos {
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);
    private int vida = 0;
    private int[] dados = new int[6];
    private boolean[] dadosUtilizados = new boolean[6];
    private String[] nomeAtributo = {"FORÇA", "DESTREZA", "CONSTITUIÇÃO", "INTELIGÊNCIA", "CARISMA", "SABDORIAE"}; 
    private int For = 0, Des = 0, Const = 0, Int = 0, Car = 0, Sab = 0; 
    private int classe;

    public void setClasse(int classe) {
        this.classe = classe; 
    }

    public void selecionarClasse() {
        String nomeClasse = "", atributoClasse = "";
        switch (classe) {
            case 1 -> {
                nomeClasse = "Guerreiro";
                atributoClasse = "Força";
            }
            case 2 -> {
                nomeClasse = "Ladino";
                atributoClasse = "Destreza";
            }
            case 3 -> {
                nomeClasse = "Curandeiro";
                atributoClasse = "Constituição";
            }
            case 4 -> {
                nomeClasse = "Mago";
                atributoClasse = "Inteligência";
            }
            case 5 -> {
                nomeClasse = "Bardo";
                atributoClasse = "Carisma";
            }
            case 6 -> {
                nomeClasse = "Druida";
                atributoClasse = "Sabedoria";
            }
        }
        System.out.printf("Classe selecionada: %s\n", nomeClasse);
        System.out.printf("Atributo preferível: %s\n", atributoClasse);
    }

    public void rolarDados() {
        AtD20(dados, 6);
    }

    private void AtD20(int[] dadosMatriz, int qntDados) {
        for (int i = 0; i < qntDados; i++) {
            dadosMatriz[i] = (random.nextInt(20) + 1);
        }
    }

    public void atribuirValores() {
        for (int i = 0; i < 6; i++) {
            int aux = scanner.nextInt();

            while (aux > 6 || aux < 1 || dadosUtilizados[aux - 1]) {
                aux = scanner.nextInt();
            }

            dadosUtilizados[aux - 1] = true;
            int atributo = dados[aux - 1];

            switch (i) {
                case 0 -> For = atributo;
                case 1 -> Des = atributo;
                case 2 -> Const = atributo;
                case 3 -> Int = atributo;
                case 4 -> Car = atributo;
                case 5 -> Sab = atributo;
            }
        }
    }

    public int calcularVida() {
        int dadoVida;
        int dVariavel;

        if (classe == 1) {
            dadoVida = random.nextInt(10) + 1;
            dVariavel = 10;
        } 
        
        else if (classe == 4) {
            dadoVida = random.nextInt(6) + 1;
            dVariavel = 6;
        } 
        
        else {
            dadoVida = random.nextInt(8) + 1;
            dVariavel = 8;
        }

        vida = dadoVida + Const;
        
        return vida;
    }

    public void mostrarFicha(String nome, int vida) {
        System.out.printf("Personagem: %s\n", nome);
        System.out.printf("Vida: %d\n", vida);
        System.out.printf("Força: %d\n", For);
        System.out.printf("Destreza: %d\n", Des);
        System.out.printf("Constituição: %d\n", Const);
        System.out.printf("Inteligência: %d\n", Int);
        System.out.printf("Carisma: %d\n", Car);
        System.out.printf("Sabedoria: %d\n", Sab);
    }
}
 {
    
}
