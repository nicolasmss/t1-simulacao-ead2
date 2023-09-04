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
    static int numServ = 1;

    // capacidade da fila //(0 = infinito)
    static int capFila = 5;

    // primeira entrada
    static double chegada = 3;

    // semente
    static double x0 = 1.5893;

    // saida atual
    static List<Evento> saidas = new ArrayList<>();

    //
    static List<Evento> eventos = new ArrayList<>();

    public static void main(String[] args) {
        CongruenteLinear cl = new CongruenteLinear(x0);
        cl.gerarX(10);

        int fila = 0;
        int serv = 0;
        double tempo = 0;
        double novotempo = chegada;
        double difTempo = novotempo - tempo;
        tempo += novotempo;
        eventos.add(new Evento(chegada, chegada, 0, "chegada", capFila));
        fila++;

        // calcula saida do primeiro
        double tempoS = (itAtendimento1 - itAtendimento0) * cl.resultados.get(0) + itAtendimento0;
        saidas.add(new Evento(tempo + tempoS, tempoS, 0, "saida", capFila));
        serv++;

        for (int i = 1; i < cl.resultados.size(); i++) {
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

            if (serv < numServ) {
                tempoS = (itAtendimento1 - itAtendimento0) * cl.resultados.get(i) + itAtendimento0;
                saidas.add(new Evento(tempo + tempoS, tempoS, 0, "saida", capFila));
                serv++;

            } else if (fila < capFila) {
                tempoI = (itChegada1 - itChegada0) * cl.resultados.get(i) + itChegada0;

                tempo = tempoI + tempo;
                Evento evento = new Evento(tempo, tempoI, fila - 1, "chegada", capFila);

                for (int j = 0; j < evento.temposX.length; j++) {
                    evento.temposX[j] = eventos.get(eventos.size() - 1).temposX[j];
                    if(fila == j){
                        evento.temposX[j] += tempoI;
                    }
                }

                eventos.add(evento);
                fila++;
            }
        }

        System.out.println(eventos);
    }

}