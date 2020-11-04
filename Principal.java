/*
SOBRE O MAPA:
-> água = ~
-> parte de barco afundada = *
-> barco jogador 1 = 1
-> barco jogador 2 = 2
*/


package Jogo;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        BatalhaNaval jogo = new BatalhaNaval("~");
        
        int direcao;
        int linha;
        int coluna;
        int jogador;
        int tamanho;
        boolean jogoFinalizado=false;

        System.out.println("Bem vindos à batalha naval!\n");
        System.out.println("Fase de escolha das posições dos barcos\n");

        //jogo.teste();
        //Escolha do barco de 4 espaços para os dois jogadores
        for(int i=1; i<3; i++){
            System.out.println("");
            jogador = i;
            tamanho = 4;
            
            System.out.println("Escolha seu único barco de 4 espaços");
            direcao = jogo.validaDirecao(i);

            System.out.println("\nRepasse a cordenada de início do barco, começando pela linha");
            linha = jogo.validaCordenada(i);
            linha--;

            System.out.println("\nAgora, Repasse a coluna");
            coluna = jogo.validaCordenada(i);
            coluna--;

            boolean barcoValido = jogo.aprovaBarco(jogador, direcao, linha, coluna, tamanho);

            if(barcoValido){
                System.out.println("Barco salvo com sucesso!");
                jogo.mostraMapa();
            }
            else{
                i--;
            }
        }
        
        //Escolha dos 2 barcos de 3 espaços para os dois jogadores
        for(int j=1; j<3; j++){
            for(int i=1; i<3; i++){
                System.out.println("");
                jogador = i;
                tamanho = 3;

                System.out.println("Escolha um de seus 2 barcos de 3 espaços");
                direcao = jogo.validaDirecao(i);

                System.out.println("\nRepasse a cordenada de início do barco, começando pela linha");
                linha = jogo.validaCordenada(i);
                linha--;

                System.out.println("\nAgora, Repasse a coluna");
                coluna = jogo.validaCordenada(i);
                coluna--;

                boolean barcoValido = jogo.aprovaBarco(jogador, direcao, linha, coluna, tamanho);

                if(barcoValido){
                    System.out.println("Barco salvo com sucesso!");
                    jogo.mostraMapa();
                }
                else{
                    i--;
                }
            }
        }
        
        //Escolha dos 3 barcos de 2 espaços para os dois jogadores
        for(int j=1; j<4; j++){
            for(int i=1; i<3; i++){
                System.out.println("");
                jogador = i;
                tamanho = 2;

                System.out.println("Escolha um de seus 3 barcos de 2 espaços");
                direcao = jogo.validaDirecao(i);

                System.out.println("\nRepasse a cordenada de início do barco, começando pela linha");
                linha = jogo.validaCordenada(i);
                linha--;

                System.out.println("\nAgora, Repasse a coluna");
                coluna = jogo.validaCordenada(i);
                coluna--;

                boolean barcoValido = jogo.aprovaBarco(jogador, direcao, linha, coluna, tamanho);

                if(barcoValido){
                    System.out.println("Barco salvo com sucesso!");
                    jogo.mostraMapa();
                }
                else{
                    i--;
                }
            }
        }
        
        //Escolha dos 4 barcos de 1 espaço para os dois jogadores
        for(int j=1; j<5; j++){
            for(int i=1; i<3; i++){
                System.out.println("");
                jogador = i;
                tamanho = 1;

                System.out.println("Escolha um de seus 4 barcos de 1 espaço");

                System.out.println("\nRepasse a cordenada de início do barco, começando pela linha");
                linha = jogo.validaCordenada(i);
                linha--;

                System.out.println("\nAgora, Repasse a coluna");
                coluna = jogo.validaCordenada(i);
                coluna--;

                boolean barcoValido = jogo.aprovaBarco(jogador, 1, linha, coluna, tamanho);

                if(barcoValido){
                    System.out.println("Barco salvo com sucesso!");
                    jogo.mostraMapa();
                }
                else{
                    i--;
                }
            }
        }

        //Começo do Jogo
        System.out.println("\n======COMEÇANDO O JOGO======\n");
        jogador=1;
        do {
            System.out.println("Vez do Jogador "+jogador);
            System.out.println("\nInforme onde você quer atacar.");
            System.out.print("Começando pela linha: ");
            linha = ler.nextInt();
            System.out.print("Agora a coluna: ");
            coluna = ler.nextInt();
            boolean acerto = jogo.atacar(jogador, linha, coluna);

            jogoFinalizado = jogo.checarFinal(jogador);
            if(!jogoFinalizado && !acerto){
                if(jogador==1){
                    jogador=2;
                }else{
                    jogador=1;
                }
            }
        }while (!jogoFinalizado);

        System.out.println("\nJogo acabou!\nJogador "+jogador+" ganhou!\nParabéns!");
    }
}
