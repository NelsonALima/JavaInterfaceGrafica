package ApresentacaoJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaPrincipal implements ActionListener {

    Pessoa[] listaCadastro = new Pessoa[5];
    int contadorCadastro = 0; // Controla a quantidade de cadastros no vetor

    public void adicionarPessoa(Pessoa pessoa) {
        if (contadorCadastro < listaCadastro.length) {
            listaCadastro[contadorCadastro] = pessoa;  // Adiciona a pessoa no vetor
            contadorCadastro++;  // Incrementa o contador de cadastros
        } else {
            System.out.println("Não há espaço suficiente para adicionar a pessoa.");
        }
    }

    // Componentes da tela principal
    JFrame telaPrincipal = new JFrame("Tela Principal"); // Cria a janela principal com o título "Tela Principal"
    JLabel lbPrincipal = new JLabel(new ImageIcon("C:\\Users\\Administrator\\IdeaProjects\\AulaPoo\\src\\ApresentacaoJava\\icones\\TelaPrincipal.png")); // Cria um JLabel para exibir uma imagem
    JMenuBar barraPrincipal = new JMenuBar(); // Cria uma barra de menus para a janela
    JMenu menu = new JMenu("Gerenciar"); // Cria um menu com o título "Gerenciar"

    JMenu sobre = new JMenu("Sobre");
    JFrame telaSobre = new JFrame("Sobre");
    JLabel lbSobre = new JLabel(new ImageIcon("C:\\Users\\Administrator\\IdeaProjects\\AulaPoo\\src\\ApresentacaoJava\\icones\\BaseCod.png"));
    JMenuItem itemAjuda = new JMenuItem("Modelo");


    JMenuItem itemCadastro = new JMenuItem("Cadastro"); // Cria um item de menu "Cadastro"
    JMenuItem itemBusca = new JMenuItem("Busca"); // Cria um item de menu "Busca"


    // Metodo para criar a tela principal
    public void criarTelaPrincipal() {
        // Configurações da tela principal
        telaPrincipal.setSize(600, 600); // Define o tamanho da janela (600x600 pixels)
        telaPrincipal.setLocationRelativeTo(null); // Centraliza a janela na tela do computador
        telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento quando a janela for fechada (fecha o programa)
        telaPrincipal.setLayout(new BorderLayout());

        // Menu principal
        barraPrincipal.add(menu); // Adiciona o menu "Gerenciar" à barra de menus
        barraPrincipal.add(sobre);

        menu.add(itemCadastro); // Adiciona o item "Cadastro" ao menu "Gerenciar"
        menu.add(itemBusca); // Adiciona o item "Busca" ao menu "Gerenciar"
        telaPrincipal.setJMenuBar(barraPrincipal); // Define a barra de menus na tela principal

        // Adicionando os listeners aos itens do menu
        itemCadastro.addActionListener(this); // Adiciona o listener para o item "Cadastro" (chama o metodo actionPerformed quando clicado)
        itemBusca.addActionListener(this); // Adiciona o listener para o item "Busca" (chama o metodo actionPerformed quando clicado)

        sobre.add(itemAjuda);
        itemAjuda.addActionListener(this);

        // Adicionando a imagem à janela principal e tornando a tela visível
        telaPrincipal.add(lbPrincipal); // Adiciona o JLabel com a imagem à janela principal
        telaPrincipal.setVisible(true); // Torna a janela visível
    }

    public void telaSobre(){
        telaSobre.setSize(800, 440);
        telaSobre.setLocationRelativeTo(null);
        telaSobre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaSobre.setLayout(new BorderLayout());

        telaSobre.add(lbSobre);
        telaSobre.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Verifica qual item de menu foi clicado e executa a ação correspondente
        if (e.getSource() == itemCadastro) {
            // Abrir a tela de cadastro se o item "Cadastro" for clicado
            TelaCadastro telaCadastro = new TelaCadastro(this); // Cria uma instância da tela de cadastro, passando a tela principal como parâmetro
            telaCadastro.abrirTelaCadastro(); // Chama o metodo para abrir a tela de cadastro
        } else if (e.getSource() == itemBusca) {
            // Abrir a tela de busca se o item "Busca" for clicado
            TelaB telaBusca = new TelaB(this); // Cria uma instância da tela de busca, passando a tela principal como parâmetro
            telaBusca.abrirTelaB(); // Chama o metodo para abrir a tela de busca
        } else if (e.getSource() == itemAjuda) {
                telaSobre();
        }
    }

}
