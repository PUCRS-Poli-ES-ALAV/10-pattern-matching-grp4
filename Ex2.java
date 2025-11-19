import java.util.Random;

// rabin karp O(n+m) // pior caso O(n*m)
public class Ex2 {

    private static long iter = 0;
    private static long inst = 0;

    public static void main(String[] args) {

        int N = 600_000;
        int M = 100_000;

        String pattern = geraString(M);
        String text = geraString(N);

        int pos = new Random().nextInt(N - M + 1);
        text = text.substring(0, pos) + pattern + text.substring(pos + M);

        System.out.println("Tamanho do texto:   " + text.length());
        System.out.println("Tamanho do padrão:  " + pattern.length());
        System.out.println("Posição real:       " + pos);
        System.out.println("-----");

        iter = 0;
        inst = 0;

        long inicio = System.nanoTime();
        int resp = search(pattern, text);
        long fim = System.nanoTime();

        System.out.println("Posição encontrada: " + resp);
        System.out.println("Iterações:          " + iter);
        System.out.println("Instruções:         " + inst);
        System.out.println("Tempo (ms):         " + (fim - inicio) / 1_000_000);
    }

    // o tal do rabin karp
    public static int search(String pat, String txt) {
        int M = pat.length();
        inst++;
        int N = txt.length();
        inst++;

        long patHash = hash(pat, M);
        inst++;

        for (int i = 0; i <= N - M; i++) {
            iter++;
            inst++;

            long txtHash = hash(txt.substring(i, i + M), M);
            inst++;

            if (patHash == txtHash) {
                inst++;
                // ocorrência ou colisão)
                return i;
            }
        }

        return N;
    }

    // hash com horner
    private static long hash(String s, int M) {
        long h = 0;
        inst++;

        int R = 256; // tamanho do "alfabeto decente haha"
        inst++;

        long Q = 1_000_000_007L; // primo giga
        inst++;

        for (int j = 0; j < M; j++) {
            inst += 4; // atualização loop + mult + soma + mod
            h = (h * R + s.charAt(j)) % Q;
        }

        return h;
    }

    // string aleatoria gerar
    public static String geraString(int tam) {
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(tam);
        Random rnd = new Random();
        for (int i = 0; i < tam; i++) {
            sb.append(alfabeto.charAt(rnd.nextInt(alfabeto.length())));
        }
        return sb.toString();
    }

}
