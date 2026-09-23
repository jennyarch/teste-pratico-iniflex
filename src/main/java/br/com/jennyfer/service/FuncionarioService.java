package br.com.jennyfer.service;

import br.com.jennyfer.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class FuncionarioService {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void removerPorNome(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(f -> f.getNome().equals(nome));
    }

    public static void aumentarSalarios(List<Funcionario> funcionarios, BigDecimal percentual) {
        BigDecimal percentualComEscala = percentual.setScale(2, RoundingMode.HALF_UP);
        funcionarios.forEach(f -> f.aumentarSalario(percentualComEscala));
    }

    public static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public static void aniversariantesMes(List<Funcionario> funcionarios) {
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(f.getNome() + " - " + f.getDataNascimento().format(FORMATO_DATA)));
    }

    public static Funcionario funcionarioMaisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow(() -> new java.util.NoSuchElementException("Lista de funcionários vazia"));
    }

    public static int calcularIdade(Funcionario funcionario) {
        return Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears();
    }

    public static List<Funcionario> ordenarPorNome(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
    }

    public static BigDecimal totalSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal quantidadeSalariosMinimos(Funcionario funcionario, BigDecimal salarioMinimo) {
        return funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
    }
}
