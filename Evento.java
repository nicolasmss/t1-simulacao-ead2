import java.util.Arrays;

public class Evento {
    String tipo; //saida ou chegada
    double tempo;
    double [] temposX;
    int tamX;

    public Evento(double tempo, double tempoX, int posX, String tipo, int tamX) {
        this.tempo = tempo;
        this.tipo = tipo;
        temposX = new double[tamX];
        for (int i = 0; i < temposX.length; i++) {
            temposX[i] = 0;
        }
        temposX[posX] += tempoX;
        
    }

    @Override
    public String toString() {
        return "\nEvento [tipo=" + tipo + ", tempo=" + tempo + ", temposX=" + Arrays.toString(temposX) + "]";
    }
}
