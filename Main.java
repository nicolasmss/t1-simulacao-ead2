import java.util.Random;

class Cliente {
    double tempoChegada;
    double tempoAtendimento;

    public Cliente(double tempoChegada, double tempoAtendimento) {
        this.tempoChegada = tempoChegada;
        this.tempoAtendimento = tempoAtendimento;
    }
}

class FilaSimulador {
    private int capacidadeFila;
    private int numeroServidores;
    private Random random;
    
    public FilaSimulador(int capacidadeFila, int numeroServidores) {
        this.capacidadeFila = capacidadeFila;
        this.numeroServidores = numeroServidores;
        this.random = new Random();
    }
    
    public void simular(int numExecucoes) {
        for (int i = 0; i < numExecucoes; i++) {
            double tempoAtual = 0.0;
            int clientesPerdidos = 0;
            
            for (int j = 0; j < 100000; j++) {
                double chegada = tempoAtual + gerarIntervaloChegada();
                double atendimento = gerarTempoAtendimento();
                
                if (atendimento <= chegada) {
                    tempoAtual = chegada;
                } else {
                    tempoAtual = atendimento;
                }
                
                if (tempoAtual > chegada) {
                    clientesPerdidos++;
                }
            }
            
            System.out.println("Execução " + (i+1) + ": Taxa de Perda = " + (clientesPerdidos / 100000.0));
        }
    }
    
    private double gerarIntervaloChegada() {
        return 1 + (3 - 1) * random.nextDouble();
    }
    
    private double gerarTempoAtendimento() {
        return 2 + (4 - 2) * random.nextDouble();
    }
}

public class Main {
    public static void main(String[] args) {
        FilaSimulador simulador1 = new FilaSimulador(5, 1);
        System.out.println("Simulação Fila G/G/1/5:");
        simulador1.simular(5);
        
        FilaSimulador simulador2 = new FilaSimulador(5, 2);
        System.out.println("Simulação Fila G/G/2/5:");
        simulador2.simular(5);
    }
}
