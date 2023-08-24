import java.util.ArrayList;
import java.util.List;

public class LCG {
    private long x0;
    private final long a;
    private final long c;
    private final long m;

    List resultados = new ArrayList<>();

    public LCG(long x0, long a, long c, long m) {
        this.x0 = x0;
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public long generate() {
        x0 = (a * x0 + c) % m;
        return x0;
    }

    public void gerarX(long x) {
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
        long x0 = 7;
        long a = 4;
        long c = 4;
        long m = 9;
        LCG lcg = new LCG(x0, a, c, m);

        for (int i = 0; i < 10; i++) {
            System.out.println(lcg.generate());
        }
    }
}
