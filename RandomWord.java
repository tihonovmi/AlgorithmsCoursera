/* *****************************************************************************
 *  Name:              Maksim Tikhonov
 *  Coursera User ID:  d951be14d5a09d1ae01e539156f6cd0c
 **************************************************************************** */


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        double i = 0;
        String championWord = "";

        while (!StdIn.isEmpty()) {
            String stringBuffer = StdIn.readString();
            if (StdRandom.bernoulli(1 / ++i)) {
                championWord = stringBuffer;
            }
        }

        StdOut.println(championWord);
    }
}
