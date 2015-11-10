package proiectmps;
import java.util.*;

public class Controller {
	
	public Zar[] Zaruri = new Zar[9];
	public String word;
	
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
		
		this.Zaruri[0].faces = z1;
		this.Zaruri[1].faces = z2;
		this.Zaruri[2].faces = z3;
		this.Zaruri[3].faces = z4;
		this.Zaruri[4].faces = z5;
		this.Zaruri[5].faces = z6;
		this.Zaruri[6].faces = z7;
		this.Zaruri[7].faces = z8;
		this.Zaruri[8].faces = z9;
		
		
		for(int i=0; i<9; i++){
			randZar(this.Zaruri[i]);
		}
		
	}
	
	public Zar[] getZaruri(){
		return this.Zaruri;
	}
	
	public void getWord(String w){
		this.word = w;
	}
	
	public String sendWord(){
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
