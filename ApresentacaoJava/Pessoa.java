package ApresentacaoJava;

public class Pessoa {

    private String nome;
    Endereco end;
    private double salario;
    private String identidade;
    private String cpf;
    private String estadoCivil;
    private String telefone;
    private int idade;
    private String sexo;

    public Pessoa(String nome, Endereco end, double salario, String identidade, String cpf, String estadoCivil, String telefone, int idade, String sexo) {

        this.nome = nome;
        this.end = end;
        this.salario = salario;
        this.identidade = identidade;
        this.cpf = cpf;
        this.estadoCivil = estadoCivil;
        this.telefone = telefone;
        this.idade = idade;
        this.sexo = sexo;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEnd() {
        return end;
    }

    public void setEnd(Endereco end) {
        this.end = end;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getIdentidade() {
        return identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
