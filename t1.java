import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class t1 {
    // intervalo de tempo de chegada dos clientes
    static double itChegada0 = 1;
    static double itChegada1 = 3;

    // intervalo de tempo de atendimento
    static double itAtendimento0 = 2;
    static double itAtendimento1 = 4;

    // numero de servidores
    static int numServ = 2;

    // capacidade da fila //(0 = infinito)
    static int capFila = 5;

    // primeira entrada
    static double chegada = 3;

    // semente
    //static double x0 = 1.5893;
    //static double x0 = 3.1598;
    //static double x0 = 6.8252;
    //static double x0 = 0.6341;
    static double x0 = 1.2345;

    // saida atual
    static List<Evento> saidas = new ArrayList<>();

    //
    static List<Evento> eventos = new ArrayList<>();

    public static void main(String[] args) {
        CongruenteLinear cl = new CongruenteLinear(x0);
        cl.gerarX(100000);

        int fila = 0;
        int serv = 0;
        double tempo = 0;
        double novotempo = chegada;
        double difTempo = novotempo - tempo;
        tempo += novotempo;
        eventos.add(new Evento(chegada, chegada, 0, "chegada", capFila, chegada));
        fila++;

        // calcula saida do primeiro
        double tempoS = (itAtendimento1 - itAtendimento0) * cl.resultados.get(0) + itAtendimento0;
        saidas.add(new Evento(tempo + tempoS, tempoS, 0, "saida", capFila, tempoS));
        serv++;

        for (int i = 1; i < cl.resultados.size(); i++) {
            if (fila==0 && serv == 1) {
                System.out.println(i);
            }
            if (i==2319) {
                System.out.println("oi");
            }
            Collections.sort(saidas, (obj1, obj2) -> Double.compare(obj1.tempo, obj2.tempo));
            double tempoI = (itChegada1 - itChegada0) * cl.resultados.get(i) + itChegada0;

            while (serv >= 1) {
                double teste = saidas.get(0).tempo;
                if (tempoI + tempo > teste) {
                    for (int j = 0; j < saidas.get(0).temposX.length; j++) {
                        saidas.get(0).temposX[j] = eventos.get(eventos.size() - 1).temposX[j];
                        if (fila == j) {
                            saidas.get(0).temposX[j] += saidas.get(0).tempo - eventos.get(eventos.size() - 1).tempo;
                        }
                    }
                    eventos.add(saidas.get(0));
                    tempo = saidas.get(0).tempo;
                    saidas.remove(0);
                    serv--;
                    fila--;
                }else{
                    break;
                }
            }

            if (serv < numServ && fila > 0 && fila>=serv+1) {
                tempoS = (itAtendimento1 - itAtendimento0) * cl.resultados.get(i) + itAtendimento0;
                saidas.add(new Evento(tempo + tempoS, tempoS, 0, "saida", capFila, tempoS));
                serv++;

            } else if (fila < capFila) {
                tempoI = (itChegada1 - itChegada0) * cl.resultados.get(i) + itChegada0;

                tempo = tempoI + tempo;
                Evento evento = new Evento(tempo, tempoI, fila, "chegada", capFila, tempoI);

                for (int j = 0; j < evento.temposX.length; j++) {
                    evento.temposX[j] = eventos.get(eventos.size() - 1).temposX[j];
                    if(fila == j){
                        evento.temposX[j] += tempoI;
                    }
                }

                eventos.add(evento);
                fila++;
            }else{
                tempoI = (itChegada1 - itChegada0) * cl.resultados.get(i) + itChegada0;
                tempo +=tempoI;
            }
        }

        System.out.println(eventos.get(eventos.size()-1));

        double[] vetor = eventos.get(eventos.size()-1).temposX; 
        double tempoF = eventos.get(eventos.size()-1).tempo;

        System.out.println("tam "+vetor.length+ "/// seed: "+ x0);
        for (int i = 0; i < vetor.length; i++) {
            System.out.println((vetor[i]*100)/tempoF+ "%");
        }
    }

}