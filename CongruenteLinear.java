import java.util.ArrayList;
import java.util.List;

public class CongruenteLinear {
    private double x0;
    private double a;
    private double c;
    private double m;

    List<Double> resultados = new ArrayList<>();

    public CongruenteLinear(double x0, double a, double c, double m) {
        this.x0 = x0;
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public CongruenteLinear(double x0){
        this.x0 = x0;
        a = 2.67974;
        c = 2.3952;
        m = 1;
    }

    public double generate() {
        x0 = (a * x0 + c) % m;
        return x0;
    }

    public void gerarX(double x) {
        for (int i = 0; i < x; i++) {
            resultados.add(generate());
        }
    }

    public void print() {
        for (Object object : resultados) {
            System.out.println(object);
        }
    }

    //teste
    public static void main(String[] args) {
        double x0 = 5;
        double a = 5;
        double c = 9;
        double m = 16;
        CongruenteLinear congruenteLinear = new CongruenteLinear(x0, a, c, m);

        for (int i = 0; i < 10; i++) {
            System.out.println(congruenteLinear.generate()/16);
        }

        System.out.println("\n---------------separador-------------\n");

        x0 = 1.5893;
        a = 2.67974;
        c = 2.3952;
        m = 1;
        congruenteLinear = new CongruenteLinear(x0, a, c, m);

        for (int i = 0; i < 10; i++) {
            System.out.println(congruenteLinear.generate());
        }

        double [] lol = new double[2];
        lol[0]+=1;
        System.out.println(lol[0]);
        
    }
}
