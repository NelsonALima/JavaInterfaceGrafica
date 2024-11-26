package ApresentacaoJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro implements ActionListener {

    //Variavel do tipo da classe TelaPrincipal
    TelaPrincipal telaPrincipal;

    // Construtor que cria um link com a class TelaPrincipal
    public TelaCadastro(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    // Definição dos componentes da tela
    JFrame telaCadastro;  // Janela principal da tela de cadastro
    JPanel painelCadastro = new JPanel();  // Painel onde os componentes serão adicionados

    // Rótulos para os campos de entrada de dados
    JLabel lnome = new JLabel("Nome:");
    JLabel lendereco = new JLabel("Endereço:");
    JLabel lsalario = new JLabel("Salario:");
    JLabel lidentidade = new JLabel("Identidade:");
    JLabel lcpf = new JLabel("CPF:");
    JLabel lestadoCivil = new JLabel("Estado Civil:");
    JLabel ltelefone = new JLabel("Telefone:");
    JLabel lidade = new JLabel("Idade:");
    JLabel lsexo = new JLabel("Sexo:");

    // Campos de entrada de dados
    JTextField txtnome = new JTextField();
    JTextField txtsalario = new JTextField();
    JTextField txtidentidade = new JTextField();
    JTextField txtcpf = new JTextField();
    JTextField txtestadoCivil = new JTextField();
    JTextField txttelefone = new JTextField();
    JTextField txtidade = new JTextField();

    // Campos de endereço
    JTextField txtrua = new JTextField();
    JTextField txtruablock = new JTextField("Rua");
    JTextField txtbairro = new JTextField();
    JTextField txtbairroblock = new JTextField("Bairro");
    JTextField txtcidade = new JTextField();
    JTextField txtcidadeblock = new JTextField("Cidade");
    JTextField txtestado = new JTextField();
    JTextField txtestadoblock = new JTextField("Estado");
    JTextField txtcep = new JTextField();
    JTextField txtcepblock = new JTextField("CEP");

    // Botões de opção para o sexo
    JRadioButton rbMasculino = new JRadioButton("Masculino");
    JRadioButton rbFeminino = new JRadioButton("Feminino");

    // Grupo de botões de opção (para garantir que apenas uma opção seja selecionada)
    ButtonGroup grupoSexo = new ButtonGroup();

    // Botões de ação para cadastrar e limpar
    JButton btCadastrar = new JButton();
    JButton btLimpar = new JButton();

    // Metodo para abrir a tela de cadastro
    public void abrirTelaCadastro() {
        telaCadastro = new JFrame("Cadastro de Pessoas");  // Cria a janela de cadastro
        painelCadastro = new JPanel();  // Cria o painel que vai conter os componentes
        telaCadastro.setSize(350, 550);  // Define o tamanho da janela
        telaCadastro.setLocationRelativeTo(null);  // Centraliza a janela
        painelCadastro.setLayout(null);  // Define o layout como null para posicionamento absoluto

        // Adiciona os componentes (rótulos e campos de texto) à tela
        adicionarComponentes();

        // Adiciona os botões de ação (Cadastrar e Limpar)
        configurarBotoes();

        // Adiciona o painel à tela e exibe a janela
        telaCadastro.add(painelCadastro);
        telaCadastro.setVisible(true);
    }

    // Metodo para adicionar os componentes à tela (rótulos, campos de texto e botões)
    private void adicionarComponentes() {
        // Definições de posição e tamanho para cada componente
        lnome.setBounds(10, 10, 80, 25);
        txtnome.setBounds(100, 10, 150, 25);

        lsalario.setBounds(10, 40, 80, 25);
        txtsalario.setBounds(100, 40, 150, 25);

        lidentidade.setBounds(10, 70, 80, 25);
        txtidentidade.setBounds(100, 70, 150, 25);

        lcpf.setBounds(10, 100, 80, 25);
        txtcpf.setBounds(100, 100, 150, 25);

        lestadoCivil.setBounds(10, 130, 80, 25);
        txtestadoCivil.setBounds(100, 130, 150, 25);

        ltelefone.setBounds(10, 160, 80, 25);
        txttelefone.setBounds(100, 160, 150, 25);

        lidade.setBounds(10, 190, 80, 25);
        txtidade.setBounds(100, 190, 50, 25);

        lsexo.setBounds(10, 220, 80, 25);
        rbMasculino.setBounds(100, 220, 100, 25);
        rbFeminino.setBounds(200, 220, 100, 25);

        lendereco.setBounds(10, 250, 60, 25);
        txtruablock.setBounds(100, 250, 50, 25);
        txtruablock.setEnabled(false);
        txtruablock.setHorizontalAlignment(JTextField.CENTER);
        txtruablock.setDisabledTextColor(Color.BLACK);
        txtrua.setBounds(155, 250, 100, 25);

        txtbairroblock.setBounds(100, 280, 50, 25);
        txtbairroblock.setEnabled(false);
        txtbairroblock.setHorizontalAlignment(JTextField.CENTER);
        txtbairroblock.setDisabledTextColor(Color.BLACK);
        txtbairro.setBounds(155, 280, 100, 25);

        txtcidadeblock.setBounds(100, 310, 50, 25);
        txtcidadeblock.setEnabled(false);
        txtcidadeblock.setHorizontalAlignment(JTextField.CENTER);
        txtcidadeblock.setDisabledTextColor(Color.BLACK);
        txtcidade.setBounds(155, 310, 100, 25);

        txtestadoblock.setBounds(100, 340, 50, 25);
        txtestadoblock.setEnabled(false);
        txtestadoblock.setHorizontalAlignment(JTextField.CENTER);
        txtestadoblock.setDisabledTextColor(Color.BLACK);
        txtestado.setBounds(155, 340, 100, 25);

        txtcepblock.setBounds(100, 370, 50, 25);
        txtcepblock.setEnabled(false);
        txtcepblock.setHorizontalAlignment(JTextField.CENTER);
        txtcepblock.setDisabledTextColor(Color.BLACK);
        txtcep.setBounds(155, 370, 100, 25);

        // Adicionando todos os componentes ao painel
        painelCadastro.add(lnome);
        painelCadastro.add(txtnome);
        painelCadastro.add(lsalario);
        painelCadastro.add(txtsalario);
        painelCadastro.add(lcpf);
        painelCadastro.add(txtcpf);
        painelCadastro.add(lestadoCivil);
        painelCadastro.add(txtestadoCivil);
        painelCadastro.add(ltelefone);
        painelCadastro.add(txttelefone);
        painelCadastro.add(lidade);
        painelCadastro.add(txtidade);
        painelCadastro.add(lidentidade);
        painelCadastro.add(txtidentidade);
        painelCadastro.add(lsexo);
        painelCadastro.add(rbMasculino);
        painelCadastro.add(rbFeminino);

        painelCadastro.add(lendereco);
        painelCadastro.add(txtrua);
        painelCadastro.add(txtruablock);
        painelCadastro.add(txtbairro);
        painelCadastro.add(txtbairroblock);
        painelCadastro.add(txtcidade);
        painelCadastro.add(txtcidadeblock);
        painelCadastro.add(txtestado);
        painelCadastro.add(txtestadoblock);
        painelCadastro.add(txtcep);
        painelCadastro.add(txtcepblock);
    }

    // Metodo para configurar os botões de ação (Cadastrar e Limpar)
    public void configurarBotoes() {
        // Definindo posição e tamanho dos botões
        btCadastrar.setBounds(100, 420, 60, 50);
        btLimpar.setBounds(170, 420, 60, 50);

        // Definindo os ícones dos botões
        btCadastrar.setIcon(new ImageIcon("C:\\Users\\Administrator\\IdeaProjects\\AulaPoo\\src\\ApresentacaoJava\\icones\\salvar.png"));
        btLimpar.setIcon(new ImageIcon("C:\\Users\\Administrator\\IdeaProjects\\AulaPoo\\src\\ApresentacaoJava\\icones\\limpar.png"));

        // Definindo as dicas que aparecerão ao passar o mouse sobre os botões
        btCadastrar.setToolTipText("Clique para salvar");
        btLimpar.setToolTipText("Clique para limpar os campos");

        // Adicionando os listeners aos botões
        btCadastrar.addActionListener(this);
        btLimpar.addActionListener(this);

        // Adicionando os botões de sexo ao grupo de botões para garantir que apenas um seja selecionado
        grupoSexo.add(rbMasculino);
        grupoSexo.add(rbFeminino);

        // Adicionando os botões ao painel
        painelCadastro.add(btCadastrar);
        painelCadastro.add(btLimpar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btCadastrar) {
            // Coleta os dados inseridos pelo usuário
            String nome = txtnome.getText();
            double salario = Double.parseDouble(txtsalario.getText());
            String identidade = txtidentidade.getText();
            String cpf = txtcpf.getText();
            String estadoCivil = txtestadoCivil.getText();
            String telefone = txttelefone.getText();
            int idade = Integer.parseInt(txtidade.getText());

            String sexo = rbMasculino.isSelected() ? "Masculino" : "Feminino";

            Endereco endereco = new Endereco(
                    txtrua.getText(),
                    txtbairro.getText(),
                    txtcidade.getText(),
                    txtestado.getText(),
                    txtcep.getText()
            );

            Pessoa pessoa = new Pessoa(
                    nome, endereco, salario, identidade, cpf, estadoCivil, telefone, idade, sexo
            );
            telaPrincipal.adicionarPessoa(pessoa);

            // Ação de salvar os dados ou outra lógica
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");

            // Limpa os campos após salvar
            limparCampos();
        } else if (e.getSource() == btLimpar) {
            limparCampos();  // Limpa os campos de entrada
        }
    }

    // Metodo para limpar os campos
    public void limparCampos() {
        txtnome.setText("");
        txtsalario.setText("");
        txtidentidade.setText("");
        txtcpf.setText("");
        txtestadoCivil.setText("");
        txttelefone.setText("");
        txtidade.setText("");
        txtrua.setText("");
        txtbairro.setText("");
        txtcidade.setText("");
        txtestado.setText("");
        txtcep.setText("");
    }
}
