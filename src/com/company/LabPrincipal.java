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
        setIdxCol(3);
        setIdxLin(2);
        String [][] labirinto = {
                {"0","1","2","3","4","5","6","7","8","9","0","1","2","3"},
                {"1","|","#","#","#","#","#","#","#","#","#","#","#","|"},
                {"2","|","#","E","#","S"," "," ","#"," ","#"," ","#","|"},
                {"3","|","#"," ","#","#","#"," ","#"," ","#"," ","#","|"},
                {"4","|","#"," "," ","#"," "," ","#"," "," "," ","#","|"},
                {"5","|","#"," ","#","#","#"," ","#","#","#"," ","#","|"},
                {"6","|","#"," ","#"," "," "," "," "," ","#"," ","#","|"},
                {"7","|","#"," ","#"," ","#","#","#","#","#"," ","#","|"},
                {"8","|","#"," ","#"," "," "," "," "," "," "," ","#","|"},
                {"9","|","#"," ","#","#","#","#","#","#","#"," ","#","|"},
                {"0","|","#"," "," "," "," "," "," "," "," "," ","#","|"},
                {"1","|","#","#","#","#","#","#","#","#","#","#","#","|"}
        };
     return labirinto;
    }

    //Numeração e ordenação da quantidade de linhas e colunas do labirinto
    public void linhaColunaLabirinto(String[][] labirinto){
        System.out.println(labirinto.length);
        for (int coluna = 0; coluna < 12; coluna++) {
            System.out.println();
            for (int linha = 0; linha < 14; linha++) {
                System.out.print(labirinto[coluna][linha] + " ");
            }
        }
        System.out.println();
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
