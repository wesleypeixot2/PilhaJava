package com.company;

import java.util.*;

public class LabPrincipal {

    private int idxLin;
    private int idxCol;
    private boolean encerraLabirinto;
    private Deque<String> stack = new ArrayDeque<>();
    private List<String> lastPositions = new ArrayList<>();


    public int getIdxLin() {
        return idxLin;
    }

    public void setIdxLin(int idxLin) {
        this.idxLin = idxLin;
    }

    public int getIdxCol() {
        return idxCol;
    }

    public void setIdxCol(int idxCol) {
        this.idxCol = idxCol;
    }

    public Deque<String> getStack() {
        return stack;
    }

    public boolean isEncerraLabirinto() {
        return encerraLabirinto;
    }

    public void setEncerraLabirinto(boolean encerraLabirinto) {
        this.encerraLabirinto = encerraLabirinto;
    }

    //Criando o esqueleto do Labirinto
    public String[][] esqueletoLabirinto(){
        String [][] tracoLabirinto = new String[11][11];
        for (int lin = 0; lin < tracoLabirinto.length; lin++) {
            for (int col = 0; col < tracoLabirinto.length; col++) {
                tracoLabirinto[lin][col] = "#";
        }
     }
     return caminhoLabirinto(tracoLabirinto);
    }

    //Corpo do labirinto
    public String[][] caminhoLabirinto(String[][] labirinto) {
        int col = 1;
        setIdxCol(col);
        int lin = 1;
        setIdxLin(lin);
        for (int i = 1; i < 10; i++) {
            labirinto[i][1] = " ";
            labirinto[9][i] = " ";
            labirinto[i][9] = " ";
            labirinto[5][i] = " ";
            labirinto[7][i] = " ";
            labirinto[3][i] = " ";
            labirinto[1][i] = " ";
            labirinto[6][3] = " ";
            labirinto[2][7] = " ";
            labirinto[2][5] = " ";
            labirinto[4][5] = " ";
            labirinto[5][2] = "#";
            labirinto[7][2] = "#";
            labirinto[5][8] = "#";
            labirinto[3][3] = "#";
            labirinto[3][6] = "#";
            labirinto[1][2] = "#";
            labirinto[1][6] = "#";
            labirinto[1][8] = "#";
        }
        labirinto[col][lin] = "E";
        labirinto[1][3] = "S";
        return labirinto;
    }

    //Numeração e ordenação da quantidade de linhas e colunas do labirinto
    public void linhaColunaLabirinto(String[][] labirinto){
        System.out.println("0 1 2 3 4 5 6 7 8 9 10 11 12");
        for (int coluna = 0; coluna < labirinto.length; coluna++) {
            System.out.print((coluna != 10 ? coluna + " | " : "0 | "));
            for (int linha = 0; linha < labirinto.length; linha++) {
                System.out.print(labirinto[coluna][linha] + " ");
            }
            System.out.println("|");
        }
        System.out.println();
    }

    //Aqui mostra a legenda do caminho escolhido pelo computador dentro da pilha para chegar ao fim do labirinto
    public void viewPilha(){
        System.out.println("Caminho andado na stack: '(linha,coluna) - Option escolhida pelo computador'");
        System.out.println(getStack().toString());
    }

    //O controle utilizado pelo computador para andar dentro do labirinto, que é muito parecido com um plano cartesiano
    public String[][] MovendoPosicao(String[][] labirinto){
        for (int i = 0; i <= 3; i++) {
            int x = getIdxLin();
            int y = getIdxCol();
            if(i == 0) {
                y--;
            } else if (i == 1){
                x++;
            } else if (i == 2){
                y++;
            } else if (i == 3){
                x--;
            }
            if (labirinto[y][x].equals("S")) {
                setEncerraLabirinto(true);
                return labirinto;
            }
        }
        for (int i = 0; i <= 3; i++) {
            int x = getIdxLin();
            int y = getIdxCol();
            if(i == 0) {
                y--;
            } else if (i == 1){
                x++;
            } else if (i == 2){
                y++;
            } else if (i == 3){
                x--;
            }
            if(labirinto[y][x].equals(" ")){
                labirinto[y][x] = "-";
                setIdxCol(y);
                setIdxLin(x);
                stack.add("("+y+","+x+") - "+i);
                return labirinto;
            }
        }
        for (int i = 0; i <= 3; i++) {
            int x = getIdxLin();
            int y = getIdxCol();
            lastPositions.add(stack.getLast());
            if (i == 0) {
                y--;
            } else if (i == 1) {
                x++;
            } else if (i == 2) {
                y++;
            } else if (i == 3) {
                x--;
            }
            if (labirinto[y][x].equals("-")) {
                String atualPosition = "("+y+","+x+") - "+i;
                if(!lastPositions.contains(atualPosition)){
                    stack.removeLast();
                    setIdxCol(y);
                    setIdxLin(x);
                    return labirinto;
                }
            }
        }
        return labirinto;
    }

}
