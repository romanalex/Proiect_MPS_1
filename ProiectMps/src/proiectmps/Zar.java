/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiectmps;

/**
 *
 * @author DAN
 */
public class Zar {
    private char [] faces;
    private char activeFace;


    public Zar(char[] face){
        faces = new char[9];
        for(int i = 0; i < 9; i ++){
            faces[i] = face[i];
        }
    }

    public int getActiveFace() {
        return activeFace;
    }


}
