package proiectmps;
import java.util.*;

public class Controller {

    private Zar[] Zaruri = new Zar[9];
    private String word;
    private CreareLista lista;

    public Controller(){

        // Initializare zaruri
        char[] z1 = {'A', 'A', 'U', 'I', 'H', 'J'};
        char[] z2 = {'T', 'R', 'N', 'S', 'M', 'B'};
        char[] z3 = {'A', 'A', 'R', 'C', 'D', 'M'};
        char[] z4 = {'E', 'E', 'I', 'O', 'D', 'F'};
        char[] z5 = {'A', 'E', 'U', 'S', 'F', 'V'};
        char[] z6 = {'T', 'L', 'N', 'P', 'G', 'C'};
        char[] z7 = {'A', 'I', 'O', 'E', 'X', 'Z'};
        char[] z8 = {'N', 'S', 'T', 'R', 'G', 'B'};
        char[] z9 = {'I', 'I', 'U', 'E', 'L', 'P'};

        this.Zaruri[0] = new Zar(z1);
        this.Zaruri[1] = new Zar(z2);
        this.Zaruri[2] = new Zar(z3);
        this.Zaruri[3] = new Zar(z4);
        this.Zaruri[4] = new Zar(z5);
        this.Zaruri[5] = new Zar(z6);
        this.Zaruri[6] = new Zar(z7);
        this.Zaruri[7] = new Zar(z8);
        this.Zaruri[8] = new Zar(z9);

        this.lista = new CreareLista("ProiectMps\\src\\proiectmps\\resources\\Fisier.txt");
    }

    public Zar[] getZaruri(){
        for(int i=0; i<9; i++){
            randZar(this.Zaruri[i]);
        }
        return  this.Zaruri;
    }



    public int sendWord(String w){
        return this.lista.Validare(w);
    }

    public String getWord(){
        return this.word;
    }

    public int randNum(){
        int maxIndex = 6; // The # of elements in your vector
        Random generator = new Random();
        int rn = generator.nextInt(maxIndex) ;

        return rn;
    }

    public void randZar(Zar z){
        int nr = randNum();
        z.active_face = nr;
    }
}