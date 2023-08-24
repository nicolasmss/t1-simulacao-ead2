public class fila {
        //intervalo de tempo de chegada dos clientes
        double tc0 = 0;
        double tcf = 2;
    
        //intervalo de tempo de atendimento
        double it0 = 0;
        double itf = 2;
    
        //numero de servidores
        int numServ = 1;
    
        //capacidade da fila
        int capFila = 5;

        public fila(double tc0, double tcf, double it0, double itf, int numServ, int capFila) {
            this.tc0 = tc0;
            this.tcf = tcf;
            this.it0 = it0;
            this.itf = itf;
            this.numServ = numServ;
            this.capFila = capFila;
        }


}
