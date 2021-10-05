/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.plano_de_saude.negocio;

import br.com.plano_de_saude.entidade.Plano;
import br.com.plano_de_saude.tela.CadastroPlanoDeSaude;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author joao.costa3
 */
public class PlanoNegocio {

    private Plano plano;

    public void salvarPlano(Plano plano) {
        plano.setSenha(gerarSenha());
        plano.setPlano(gerarPlano());
        if(plano.getTipo() == 1){
            plano.setMensalidade(500);
        }else{
            plano.setMensalidade(70);
        }
    }

    private String gerarSenha() {
        String[] letras = {"a", "b",
            "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int tamanho = letras.length;
        String senha = "";
        int indice;
        for (int i = 0; i < 4; i++) {
            indice = (int) (Math.random() * tamanho);
            senha = senha + letras[indice];
        }
        return senha;
    }

    private String gerarPlano() {
        String[] planoSaude = {"basico", "total"};
        int indice = (int) (Math.random() * planoSaude.length);
        return planoSaude[indice];
    }

    public void pagarMensalidade(double dinheiro) {
        if (plano.getPlano().equals("total")) {
            if (dinheiro != plano.getMensalidade()) {
                JOptionPane.showMessageDialog(null, "valor invalido!!!");
            } else {
                plano.setMensalidade(plano.getMensalidade() - dinheiro);
            }
        } else {
            if (dinheiro != plano.getMensalidade() + plano.getPreco()) {
                JOptionPane.showMessageDialog(null, "valor invalido!!!");
            } else {
                plano.setMensalidade(plano.getMensalidade() - 500);
                plano.setPreco(0);
            }
        }

    }

    public String cadastrarExameEConsulta(Plano plano, double valor) {
        String mensagem;
        plano.setPreco(plano.getPreco() + valor);
        if (plano.getMensalidade() == 70) {
            String linhaHistorico = "\n" + gerarDataHoraAtual() + "Exame/Consulta: " + plano.getExameConsulta()
                    + "Valor do Exame/Consulta: R$" + plano.getPreco();
            plano.setHistorico(plano.getHistorico() + " " + linhaHistorico + "\n" + plano.getMensalidade());
            mensagem = "Exame/Consulta cadastrado com sucesso";
        } else {
            String linhaHistorico = "\n" + gerarDataHoraAtual() + "Exame/Consulta: " + plano.getExameConsulta();
            plano.setHistorico(plano.getHistorico() + " " +  linhaHistorico + "\n" + plano.getMensalidade());
            mensagem = "Exame/Consulta cadastrado com sucesso";
        }
        return mensagem;
    }

    private String gerarDataHoraAtual() {
        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter conversorData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return conversorData.format(dataAtual);
    }

}
