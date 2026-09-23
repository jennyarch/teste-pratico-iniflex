package br.com.jennyfer;

import br.com.jennyfer.model.Funcionario;
import br.com.jennyfer.service.FuncionarioService;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Main {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat FORMATO_MOEDA = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

    static {
        FORMATO_MOEDA.setMinimumFractionDigits(2);
        FORMATO_MOEDA.setMaximumFractionDigits(2);
    }

    public static void main(String[] args) {

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

// ===================== 3.2 - Remover o funcionário "João" =====================
        FuncionarioService.removerPorNome(funcionarios,"João");

// ===================== 3.3 - Imprimir todos os funcionários =====================
        System.out.println("\n===== 3.3 - Lista de funcionários =====");
        imprimirFuncionarios(funcionarios);

// ===================== 3.4 - Aumento de 10% no salário =====================
        FuncionarioService.aumentarSalarios(funcionarios,new BigDecimal("10.0"));

        System.out.println("\n===== 3.4 - Funcionários após aumento de 10% =====");
        imprimirFuncionarios(funcionarios);

        // ===================== 3.5 - Agrupar por função em um Map =====================
        FuncionarioService.agruparPorFuncao(funcionarios);

        // ===================== 3.6 - Imprimir agrupados por função =====================
        System.out.println("\n===== 3.6 - Funcionários agrupados por função =====");
        Map<String, List<Funcionario>> funcionariosPorFuncao = FuncionarioService.agruparPorFuncao(funcionarios);
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println("  - " + f.getNome()));
        });

        // ===================== 3.8 - Aniversariantes nos meses 10 e 12 =====================
        System.out.println("\n===== 3.8 - Aniversariantes nos meses 10 e 12 =====");
        FuncionarioService.aniversariantesMes(funcionarios);

        // ===================== 3.9 - Funcionário com maior idade =====================
        Funcionario maisVelho = FuncionarioService.funcionarioMaisVelho(funcionarios);
        int idade = FuncionarioService.calcularIdade(maisVelho);

        System.out.println("\n===== 3.9 - Funcionário com maior idade =====");
        System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + idade + " anos");

        // ===================== 3.10 - Lista em ordem alfabética =====================
        List<Funcionario> ordemAlfabetica = FuncionarioService.ordenarPorNome(funcionarios);

        System.out.println("\n===== 3.10 - Funcionários em ordem alfabética =====");
        ordemAlfabetica.forEach(f -> System.out.println(f.getNome()));

        // ===================== 3.11 - Total dos salários =====================
        BigDecimal totalSalarios = FuncionarioService.totalSalarios(funcionarios);

        System.out.println("\n===== 3.11 - Total dos salários =====");
        System.out.println("Total: R$ " + FORMATO_MOEDA.format(totalSalarios));

        // ===================== 3.12 - Quantos salários mínimos cada um ganha =====================
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("\n===== 3.12 - Salários mínimos por funcionário =====");
        funcionarios.forEach(f -> {
            BigDecimal quantidadeSalariosMinimos = FuncionarioService.quantidadeSalariosMinimos(f, salarioMinimo);
            System.out.println(f.getNome() + " --> " + FORMATO_MOEDA.format(quantidadeSalariosMinimos) + " salários mínimos");
        });
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            System.out.println(
                    "Nome: " + f.getNome()
                            + " | Data Nascimento: " + f.getDataNascimento().format(FORMATO_DATA)
                            + " | Salário: R$ " + FORMATO_MOEDA.format(f.getSalario())
                            + " | Função: " + f.getFuncao()
            );
        }
    }
}