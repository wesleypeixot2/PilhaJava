package com.company;

public class LabExecutavel {

    public static void main(String[] args) {
        LabPrincipal labirinto = new com.company.LabPrincipal();
        String[][] table = labirinto.esqueletoLabirinto();
        System.out.println("Labirinto");
        labirinto.linhaColunaLabirinto(table);
        while(!labirinto.isEncerraLabirinto()) {
            labirinto.linhaColunaLabirinto(table);
            table = labirinto.MovendoPosicao(table);
        }
        System.out.println("Labirinto Resolvido");
        labirinto.linhaColunaLabirinto(table);

    }
}
