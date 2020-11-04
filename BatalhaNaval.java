/*
SOBRE O MAPA:
-> água = ~
-> afundado = *
-> barco jogador 1 = 1
-> barco jogador 2 = 2
*/

package Jogo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BatalhaNaval {
    protected String[][][] espaco = new String[2][10][10];
    protected int oponente;
    protected String barcoOponente;
    
    //construtor que deixa o mapa inteiro como água (~)
    public BatalhaNaval(String espaco) {

        for (int jogador=1; jogador<=2; jogador++){
            for(int i=0; i<10; i++){
                for(int j=0; j<10; j++){
                    this.espaco[jogador-1][i][j] = espaco;
                }
            }
        }
    }
    

    //Método que recebe direção, valida, e a retorna
    public int validaDirecao(int i){
        Scanner ler = new Scanner(System.in);
        int direcao = 0;
        int j = i+1;
        for(; i<j; i++){
            try{
                System.out.println("[1] Horizontal\n[2] Vertical");
                System.out.print("Jogador "+i+": ");
                direcao = ler.nextInt();
                
                if(direcao<1 || direcao>2){
                    System.out.println("Valor precisa ser 1 ou 2, repasse-o novamente:\n");
                    i--;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Valor precisa ser inteiro, repasse-o novamente:\n");
                i--;
                ler.next();
            }
        }
        return direcao;
    }
       
    
    //Método que valida e retorna a cordenada inicial do barco
    public int validaCordenada(int i){
        Scanner ler = new Scanner(System.in);
        int cordenada = 0;
        int j = i+1;
        
        for(; i<j; i++){
            try{
                System.out.print("Jogador "+i+": ");
                cordenada = ler.nextInt();
                
                if(cordenada<1 || cordenada>10){
                    System.out.println("Valor precisa ser de 0 a 10, repasse-o novamente:\n");
                    i--;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Valor precisa ser inteiro, repasse-o novamente:\n");
                i--;
                ler.next();
            }
        }
        return cordenada;
    }
    
    
    //Método que verifica se o barco cabe na posição e se não há outro barco no caminho
    //se não haver nada, insere o barco no mapa
    public boolean aprovaBarco(int jogador, int direcao, int linha, int coluna, int tamanho){
        String[][][] aux;
        aux = espaco;
        if(direcao == 1){//horizontal
            for(int i=0; i<tamanho; i++){
                if(coluna+tamanho > 10){
                    System.out.println("Seu barco não cabe aí!");
                    System.out.println("Por favor, reensira-a\n");
                    return false;
                }
                else if(!espaco[jogador-1][linha][coluna+i].equals("~")){
                    System.out.println("A posição que você escolheu não está disponível");
                    System.out.println("Por favor, reensira-a\n");
                    return false;
                }
                else{
                    aux[jogador-1][linha][coluna+i] = Integer.toString(jogador);
                    int jao = coluna + i;
                    System.out.println(jao);
                    System.out.println(tamanho);
                    setEspaco(aux);
                }
            }
        }
        else{//vertical
            for(int i=0; i<tamanho; i++){
                if(linha+tamanho > 10){
                    System.out.println("Seu barco não cabe aí!");
                    System.out.println("Por favor, reensira-a\n");
                    return false;
                }
                else if(!espaco[jogador-1][linha+i][coluna].equals("~")){
                    System.out.println("A posição que você escolheu não está disponível");
                    System.out.println("Por favor, reensira-a\n");
                    return false;
                }
                else{
                    aux[jogador-1][linha+i][coluna] = Integer.toString(jogador);
                    setEspaco(aux);
                }
            }
        }
        return true;
    }
    
    
    //Método que mostra o mapa
    public void mostraMapa(){
        for(int jogador=1; jogador<=2; jogador++){
            System.out.println("\nJogador "+jogador+": ");
            for(int i=0; i<10; i++){
                for(int j=0; j<10; j++){
                    System.out.print(espaco[jogador-1][i][j]+" ");
                }
                System.out.println("");
            }
        }
    }

    //Método que atualiza as propriedades oponente e barcoOponente dependendo do jogador
    public void defineOponente(int jogador){
        if(jogador==1){
            oponente=1;
            barcoOponente="2";
        }else {
            oponente=0;
            barcoOponente="1";
        }
    }

    //Método que realiza os ataques
    public boolean atacar(int jogador, int linha, int coluna) {

        defineOponente(jogador);

        if(this.espaco[oponente][linha-1][coluna-1].equals(barcoOponente)){
            this.espaco[oponente][linha-1][coluna-1] = "*";
            System.out.println("Água!\nMais uma jogada\n");
            return true;
        }else{
            System.out.println("Erro!\n");
            return false;
        }
    }

    //Método para checar se o jogo acabou
    public boolean checarFinal(int jogador){
        int oponentesRestando = 0;

        defineOponente(jogador);

        for(int linha=0; linha<10; linha++){
            for(int coluna=0; coluna<10; coluna++){
                if(this.espaco[oponente][linha][coluna].equals(barcoOponente)){
                    oponentesRestando++;
                }
            }
        }
        if(oponentesRestando>0){
            return false;
        }
        mostraMapa();
        return true;
    }

    //GETS E SETS
    public String[][][] getEspaco() {
        return espaco;
    }

    public void setEspaco(String[][][] espaco) {
        this.espaco = espaco;
    }

    /*public void teste(){
        for (int i=0; i<10; i++){
            espaco[0][0][i]="1";
            espaco[1][0][i]="2";
        }
    }*/
}
