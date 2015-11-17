package proiectmps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author AsliF
 */
public class CreareLista {
    private String fisier;
    private ArrayList<String> words = new ArrayList<>();
    
    public CreareLista(String fisier){
        this.fisier = fisier;
        if (this.words.size() == 0 ||  this.words == null)
            try {
                this.ParsareFisier();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    private void ParsareFisier() throws FileNotFoundException, IOException{
       
        String currentLine;
        try (BufferedReader br = new BufferedReader(new FileReader(fisier))) {

            while ((currentLine = br.readLine())!= null){
                words.add(currentLine);
            }
        }
       
    }

    public int Validare(String cuvant){
        for(String word : words){
            if(word.matches(cuvant)){
                return word.length();
            }             
        }
        return -1;
    }
}
