package ApresentacaoJava;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TelaB implements ActionListener, ItemListener {

    // A classe TelaB recebe uma referência de TelaPrincipal para acessar a lista de cadastros
    TelaPrincipal telaPrincipal;

    // Construtor que recebe a referência da tela principal
    public TelaB(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    // Declaração dos componentes da tela de busca
    JFrame telaBus = new JFrame("Tela de Busca"); // Janela principal da tela de busca
    JComboBox<String> comboBox = new JComboBox<>(new String[]{"Selecione...", "Pessoa - Maior Idade", "Pessoa - Sexo Masculino", "Pessoa - Salario Maior que Mil"}); // ComboBox para selecionar os filtros
    JTextField identidadeField = new JTextField(15); // Campo de texto para inserir identidade
    JLabel identidadeLabel = new JLabel("Identidade:"); // Rótulo do campo de identidade
    JButton buscarButton = new JButton("Buscar"); // Botão de busca
    JButton atualizarButton = new JButton("Atualizar"); // Botão para atualizar cadastro
    JButton excluirButton = new JButton("Excluir"); // Botão de exclusão

    JTable table; // Tabela para exibir os resultados
    DefaultTableModel tableModel; // Modelo da tabela

    // Metodo que inicializa a tela de busca
    void abrirTelaB() {
        telaBus.setSize(800, 400); // Define o tamanho da janela
        telaBus.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define que a janela será descartada ao ser fechada
        telaBus.setLocationRelativeTo(null); // Centraliza a janela na tela
        telaBus.setVisible(true); // Exibe a janela

        comboBox.setSelectedIndex(0); // Define o índice selecionado do comboBox (inicialmente "Selecione...")

        // Painel superior com os componentes de busca (comboBox, identidadeField, etc.)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Define o layout do painel (alinhado à esquerda)
        topPanel.add(comboBox); // Adiciona o comboBox
        topPanel.add(identidadeLabel); // Adiciona o rótulo de identidade
        topPanel.add(identidadeField); // Adiciona o campo de texto de identidade
        topPanel.add(buscarButton); // Adiciona o botão de busca
        topPanel.add(atualizarButton);
        topPanel.add(excluirButton); // Adiciona o botão de exclusão

        // Configuração da tabela de resultados
        String[] colunas = {"ID", "Nome", "Salario", "Identidade", "CPF", "Estado Civil", "Telefone", "Idade", "Sexo", "Endereço"}; // Cabeçalhos da tabela
        tableModel = new DefaultTableModel(colunas, 0); // Cria o modelo de tabela com os cabeçalhos
        table = new JTable(tableModel); // Cria a tabela com o modelo
        JScrollPane scrollPane = new JScrollPane(table); // Adiciona um JScrollPane para permitir a rolagem na tabela

        // Adiciona os painéis e componentes à janela
        telaBus.add(topPanel, BorderLayout.NORTH); // Adiciona o painel superior na parte norte
        telaBus.add(scrollPane, BorderLayout.CENTER); // Adiciona a tabela no centro da janela

        // Adiciona os ActionListeners aos botões
        buscarButton.addActionListener(this); // Aciona o metodo actionPerformed quando o botão de busca for pressionado
        atualizarButton.addActionListener(this); // Define o ActionListener para o botão
        excluirButton.addActionListener(this); // Aciona o metodo actionPerformed quando o botão de excluir for pressionado

        // Adiciona o ItemListener ao comboBox
        comboBox.addItemListener(this); // Aciona o metodo itemStateChanged quando a seleção do comboBox mudar

        // Exibe todos os cadastros inicialmente
        exibirCadastros();
    }

    // Metodo que trata os eventos dos botões
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buscarButton) {
            // Se o botão de busca for pressionado, chama o metodo de filtrar por identidade
            filtrarPorIdentidade(identidadeField.getText());
        } else if (e.getSource() == atualizarButton) {
            atualizarCadastro();

        } else if (e.getSource() == excluirButton) {
            // Se o botão de excluir for pressionado, chama o metodo de excluir cadastro
            excluirCadastro();
        }
    }

    // Metodo que trata os eventos do comboBox
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) { // Verifica se a opção foi selecionada
            String selectedOption = (String) comboBox.getSelectedItem(); // Obtém a opção selecionada
            if (selectedOption != null) {
                switch (selectedOption) {
                    case "Selecione...":
                        // Se a opção "Selecione..." for escolhida, exibe todos os cadastros
                        exibirCadastros();
                        break;
                    case "Pessoa - Maior Idade":
                        // Exibe a pessoa com maior idade
                        exibirPessoaMaiorIdade();
                        break;
                    case "Pessoa - Sexo Masculino":
                        // Exibe as pessoas do sexo masculino
                        exibirPessoasSexoMasculino();
                        break;
                    case "Pessoa - Salario Maior que Mil":
                        // Exibe as pessoas com salário maior que mil
                        exibirPessoasSalarioMaiorQueMil();
                        break;
                }
            }
        }
    }

    // Metodo para exibir todos os cadastros na tabela
    public void exibirCadastros() {
        // Define os nomes das colunas da tabela que será exibida na interface
        String[] colunas = {"ID", "Nome", "Salario", "Identidade", "CPF", "Estado Civil", "Telefone", "Idade", "Sexo", "Endereço"};

        // Cria o modelo da tabela com as colunas definidas acima e começa com 0 linhas
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0); // O 0 significa que a tabela começa vazia

        // Loop que percorre todas as pessoas cadastradas na lista
        for (int i = 0; i < telaPrincipal.contadorCadastro; i++) {

            // Obtém a pessoa da lista de cadastros na posição 'i'
            Pessoa pessoa = telaPrincipal.listaCadastro[i];

            // Obtém o endereço da pessoa
            Endereco endereco = pessoa.getEnd();

            // Formata o endereço da pessoa para um formato legível (Rua, Bairro, Cidade, Estado, CEP)
            String enderecoFormatado = String.format("Rua %s, Bairro: %s, Cidade: %s, Estado: %s, CEP: %s",
                    endereco.getRua(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());

            // Adiciona uma linha à tabela com os dados da pessoa e seu endereço formatado
            modelo.addRow(new Object[]{
                    i + 1, // ID da pessoa, que é baseado na posição (i + 1 para começar de 1 e não de 0)
                    pessoa.getNome(), // Nome da pessoa
                    pessoa.getSalario(), // Salário da pessoa
                    pessoa.getIdentidade(), // Identidade da pessoa
                    pessoa.getCpf(), // CPF da pessoa
                    pessoa.getEstadoCivil(), // Estado civil da pessoa
                    pessoa.getTelefone(), // Telefone da pessoa
                    pessoa.getIdade(), // Idade da pessoa
                    pessoa.getSexo(), // Sexo da pessoa
                    enderecoFormatado // Endereço da pessoa, que foi formatado na linha anterior
            });
        }

        // Atualiza o modelo da tabela com os dados adicionados e exibe os resultados na interface
        table.setModel(modelo); // Altera o modelo da tabela para o modelo atualizado com as informações das pessoas
    }

    // Metodo para filtrar cadastros por identidade
    public void filtrarPorIdentidade(String identidade) {
        // Define os nomes das colunas da tabela que será exibida na interface
        String[] colunas = {"ID", "Nome", "Salario", "Identidade", "CPF", "Estado Civil", "Telefone", "Idade", "Sexo", "Endereço"};

        // Cria o modelo da tabela com as colunas definidas acima e começa com 0 linhas
        DefaultTableModel modeloFiltrado = new DefaultTableModel(colunas, 0); // O 0 significa que a tabela começa vazia

        // Loop que percorre todas as pessoas cadastradas na lista
        for (int i = 0; i < telaPrincipal.contadorCadastro; i++) {

            // Obtém a pessoa da lista de cadastros na posição 'i'
            Pessoa pessoa = telaPrincipal.listaCadastro[i];

            // Verifica se a identidade da pessoa é igual ao valor da variável 'identidade' (realiza o filtro)
            if (pessoa.getIdentidade() != null && pessoa.getIdentidade().equals(identidade)) { // Verifica se as identidades são exatamente iguais

                // Obtém o endereço da pessoa
                Endereco endereco = pessoa.getEnd();

                // Formata o endereço da pessoa para um formato legível (Rua, Bairro, Cidade, Estado, CEP)
                String enderecoFormatado = String.format("Rua %s, Bairro: %s, Cidade: %s, Estado: %s, CEP: %s",
                        endereco.getRua(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());

                // Adiciona uma linha à tabela com os dados da pessoa e seu endereço formatado
                modeloFiltrado.addRow(new Object[]{
                        i + 1, // ID da pessoa, que é baseado na posição (i + 1 para começar de 1 e não de 0)
                        pessoa.getNome(), // Nome da pessoa
                        pessoa.getSalario(), // Salário da pessoa
                        pessoa.getIdentidade(), // Identidade da pessoa
                        pessoa.getCpf(), // CPF da pessoa
                        pessoa.getEstadoCivil(), // Estado civil da pessoa
                        pessoa.getTelefone(), // Telefone da pessoa
                        pessoa.getIdade(), // Idade da pessoa
                        pessoa.getSexo(), // Sexo da pessoa
                        enderecoFormatado // Endereço da pessoa, que foi formatado na linha anterior
                });
            }
        }

        // Atualiza o modelo da tabela com os dados filtrados e exibe os resultados na interface
       table.setModel(modeloFiltrado);

    }


    // Metodo para excluir um cadastro
    private void excluirCadastro() {
        int linhaSelecionada = table.getSelectedRow(); // Obtém a linha selecionada no caso a posicao no vetor

        if (linhaSelecionada >= 0 && linhaSelecionada < telaPrincipal.contadorCadastro) {
            // Desloca os elementos para a esquerda a partir da linha selecionada
            for (int i = linhaSelecionada; i < telaPrincipal.contadorCadastro - 1; i++) {
                telaPrincipal.listaCadastro[i] = telaPrincipal.listaCadastro[i + 1];
            }

            // Limpa a última posição do vetor (opcional, mas evita dados residuais)
            telaPrincipal.listaCadastro[telaPrincipal.contadorCadastro - 1] = null;

            // Decrementa o contador de cadastros
            telaPrincipal.contadorCadastro--;

            exibirCadastros(); // Atualiza a tabela após a exclusão
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um cadastro para excluir."); // Exibe mensagem de erro se nenhuma linha for selecionada
        }
    }

    // Metodo para exibir a pessoa com a maior idade
    private void exibirPessoaMaiorIdade() {
        // Define as colunas da tabela que será exibida na interface
        String[] colunas = {"ID", "Nome", "Salario", "Identidade", "CPF", "Estado Civil", "Telefone", "Idade", "Sexo", "Endereço"};

        // Cria o modelo da tabela que usará as colunas definidas acima e começa com 0 linhas
        DefaultTableModel modeloIdadeMaior = new DefaultTableModel(colunas, 0);

        // Variáveis para armazenar a pessoa com a maior idade e seu ID
        Pessoa pessoaMaiorIdade = null; // Inicializa como null, pois ainda não sabemos quem tem a maior idade
        int idMaiorIdade = -1; // Inicializa o ID como -1, que será atualizado posteriormente

        // Laço para percorrer todas as pessoas cadastradas
        for (int i = 0; i < telaPrincipal.contadorCadastro; i++) {
            // Obtém a pessoa da posição 'i' no vetor de cadastros
            Pessoa pessoa = telaPrincipal.listaCadastro[i];

            // Verifica se é a primeira pessoa ou se a pessoa atual tem idade maior que a armazenada
            if (pessoaMaiorIdade == null || pessoa.getIdade() > pessoaMaiorIdade.getIdade()) {
                // Se for a primeira pessoa ou se a atual tiver maior idade, atualiza a pessoa com maior idade
                pessoaMaiorIdade = pessoa;
                // O ID será o índice da pessoa mais 1 (para tornar a numeração mais amigável)
                idMaiorIdade = i + 1; // O ID é baseado na posição no vetor, começando de 1
            }
        }

        // Após o laço, verifica se uma pessoa foi encontrada
        if (pessoaMaiorIdade != null) {
            // Obtém o endereço da pessoa com a maior idade
            Endereco endereco = pessoaMaiorIdade.getEnd();

            // Formata o endereço para exibir de forma legível (Rua, Bairro, Cidade, etc.)
            String enderecoFormatado = String.format("Rua %s, Bairro: %s, Cidade: %s, Estado: %s, CEP: %s",
                    endereco.getRua(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());

            // Adiciona a pessoa com a maior idade e seus dados formatados à tabela
            modeloIdadeMaior.addRow(new Object[]{
                    idMaiorIdade, // ID da pessoa
                    pessoaMaiorIdade.getNome(), // Nome da pessoa
                    pessoaMaiorIdade.getSalario(), // Salário da pessoa
                    pessoaMaiorIdade.getIdentidade(), // Identidade da pessoa
                    pessoaMaiorIdade.getCpf(), // CPF da pessoa
                    pessoaMaiorIdade.getEstadoCivil(), // Estado civil da pessoa
                    pessoaMaiorIdade.getTelefone(), // Telefone da pessoa
                    pessoaMaiorIdade.getIdade(), // Idade da pessoa
                    pessoaMaiorIdade.getSexo(), // Sexo da pessoa
                    enderecoFormatado // Endereço da pessoa, que foi formatado
            });
        }

        // Atualiza o modelo da tabela para exibir a nova linha (da pessoa com maior idade) na interface
        table.setModel(modeloIdadeMaior);
    }


    // Metodo para exibir as pessoas do sexo masculino
    private void exibirPessoasSexoMasculino() {
        String[] colunas = {"ID", "Nome", "Salario", "Identidade", "CPF", "Estado Civil", "Telefone", "Idade", "Sexo", "Endereço"};
        DefaultTableModel modeloSexoMasculino = new DefaultTableModel(colunas, 0);

        for (int i = 0; i < telaPrincipal.contadorCadastro; i++) {
            Pessoa pessoa = telaPrincipal.listaCadastro[i];

            if ("Masculino".equals(pessoa.getSexo())) {

                Endereco endereco = pessoa.getEnd();
                String enderecoFormatado = String.format("Rua %s, Bairro: %s, Cidade: %s, Estado: %s, CEP: %s",
                        endereco.getRua(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());

                modeloSexoMasculino.addRow(new Object[]{
                        i + 1, // ID baseado na posição
                        pessoa.getNome(),
                        pessoa.getSalario(),
                        pessoa.getIdentidade(),
                        pessoa.getCpf(),
                        pessoa.getEstadoCivil(),
                        pessoa.getTelefone(),
                        pessoa.getIdade(),
                        pessoa.getSexo(),
                        enderecoFormatado
                });
            }
        }
        table.setModel(modeloSexoMasculino);
    }

    private void exibirPessoasSalarioMaiorQueMil() {
        String[] colunas = {"ID", "Nome", "Salario", "Identidade", "CPF", "Estado Civil", "Telefone", "Idade", "Sexo", "Endereço"};
        DefaultTableModel modeloSalarioMaior = new DefaultTableModel(colunas, 0);

        for (int i = 0; i < telaPrincipal.contadorCadastro; i++) {
            Pessoa pessoa = telaPrincipal.listaCadastro[i];
            if (pessoa.getSalario() > 1000) {
                Endereco endereco = pessoa.getEnd();
                String enderecoFormatado = String.format("Rua %s, Bairro: %s, Cidade: %s, Estado: %s, CEP: %s",
                        endereco.getRua(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());

                modeloSalarioMaior.addRow(new Object[]{
                        i + 1, // ID baseado na posição
                        pessoa.getNome(),
                        pessoa.getSalario(),
                        pessoa.getIdentidade(),
                        pessoa.getCpf(),
                        pessoa.getEstadoCivil(),
                        pessoa.getTelefone(),
                        pessoa.getIdade(),
                        pessoa.getSexo(),
                        enderecoFormatado
                });
            }
        }
        table.setModel(modeloSalarioMaior);
    }

    private void atualizarCadastro() {
        int linhaSelecionada = table.getSelectedRow(); // Obtém a linha selecionada

        if (linhaSelecionada >= 0 && linhaSelecionada < telaPrincipal.contadorCadastro) {
            // Obtém a pessoa selecionada a partir do vetor
            Pessoa pessoaSelecionada = telaPrincipal.listaCadastro[linhaSelecionada];

            // Cria um painel para inserir novos dados
            JPanel painelEdicao = new JPanel(new GridLayout(0, 2));
            
            painelEdicao.add(new JLabel("Nome:"));
            JTextField nomeField = new JTextField(pessoaSelecionada.getNome());
            painelEdicao.add(nomeField);

            painelEdicao.add(new JLabel("Salário:"));
            JTextField salarioField = new JTextField(String.valueOf(pessoaSelecionada.getSalario()));
            painelEdicao.add(salarioField);

            painelEdicao.add(new JLabel("Identidade:"));
            JTextField identidadeField = new JTextField(pessoaSelecionada.getIdentidade());
            painelEdicao.add(identidadeField);

            painelEdicao.add(new JLabel("CPF:"));
            JTextField cpfField = new JTextField(pessoaSelecionada.getCpf());
            painelEdicao.add(cpfField);

            painelEdicao.add(new JLabel("Estado Civil:"));
            JTextField estadoCivilField = new JTextField(pessoaSelecionada.getEstadoCivil());
            painelEdicao.add(estadoCivilField);

            painelEdicao.add(new JLabel("Telefone:"));
            JTextField telefoneField = new JTextField(pessoaSelecionada.getTelefone());
            painelEdicao.add(telefoneField);

            painelEdicao.add(new JLabel("Idade:"));
            JTextField idadeField = new JTextField(String.valueOf(pessoaSelecionada.getIdade()));
            painelEdicao.add(idadeField);

            painelEdicao.add(new JLabel("Sexo:"));
            JTextField sexoField = new JTextField(pessoaSelecionada.getSexo());
            painelEdicao.add(sexoField);

            //Adicionar os atributos da classe Endereco
            Endereco endereco = pessoaSelecionada.getEnd(); // Obtém o endereço atual
            painelEdicao.add(new JLabel("Rua:"));
            JTextField ruaField = new JTextField(endereco.getRua());
            painelEdicao.add(ruaField);

            painelEdicao.add(new JLabel("Bairro:"));
            JTextField bairroField = new JTextField(endereco.getBairro());
            painelEdicao.add(bairroField);

            painelEdicao.add(new JLabel("Cidade:"));
            JTextField cidadeField = new JTextField(endereco.getCidade());
            painelEdicao.add(cidadeField);

            painelEdicao.add(new JLabel("Estado:"));
            JTextField estadoField = new JTextField(endereco.getEstado());
            painelEdicao.add(estadoField);

            painelEdicao.add(new JLabel("CEP:"));
            JTextField cepField = new JTextField(endereco.getCep());
            painelEdicao.add(cepField);

            // Exibe o diálogo para atualização
            int resultado = JOptionPane.showConfirmDialog(
                    telaBus,
                    painelEdicao,
                    "Atualizar Cadastro",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (resultado == JOptionPane.OK_OPTION) {
                // Atualiza os valores do cadastro selecionado
                pessoaSelecionada.setNome(nomeField.getText());
                pessoaSelecionada.setSalario(Double.parseDouble(salarioField.getText()));
                pessoaSelecionada.setIdentidade(identidadeField.getText());
                pessoaSelecionada.setCpf(cpfField.getText());
                pessoaSelecionada.setEstadoCivil(estadoCivilField.getText());
                pessoaSelecionada.setTelefone(telefoneField.getText());
                pessoaSelecionada.setIdade(Integer.parseInt(idadeField.getText()));
                pessoaSelecionada.setSexo(sexoField.getText());

                // Cria um novo objeto Endereco com os dados atualizados
                Endereco novoEndereco = new Endereco(
                        ruaField.getText(),      // Rua
                        bairroField.getText(),   // Bairro
                        cidadeField.getText(),   // Cidade
                        estadoField.getText(),   // Estado
                        cepField.getText()       // CEP
                );

                // Atualiza o objeto Pessoa com o novo Endereco
                pessoaSelecionada.setEnd(novoEndereco);

                // Atualiza a tabela
                exibirCadastros();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um cadastro para atualizar.");
        }
    }

}