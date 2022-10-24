/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author Adam
 */
public class Librarian extends Person {
    
    private static int noOfLibrarians;
    private static Librarian lArray [];
    
    public static void init(int s){
        lArray = new Librarian[s];
    }
    
    public static Librarian getLibrarian(int i) {
        return lArray[i];
    }
    
    public static int getNoOfLibrarians() {
        return noOfLibrarians;
    }
    
    public Librarian(String name ,String password ,String email ,String address ,String city ,String contactNo){
        super(name, password, email, address, city, contactNo);
    }
    
    public static void write(){
        File file = new File ("librarians.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            int i = 0;
            while (i < noOfLibrarians){
                bw.write(lArray[i].getName()+"\""+lArray[i].getPassword()+"\""+lArray[i].getEmail()+"\""+lArray[i].getAddress()+"\""+lArray[i].getCity()+"\""+lArray[i].getContactNo());
                bw.newLine();
                i++;
            }
            bw.close();    
        } catch (IOException ex) {return;}
    }
    
    public static void read(){
        File file = new File("librarians.txt");
        try {
            Scanner s = new Scanner(file).useDelimiter("\"");
            Librarian l;
            int i = 0;
            while(s.hasNext()){
                l = new Librarian(s.next(),s.next(),s.next(),s.next(),s.next(),s.next());    
                lArray[i++]= l;
            }
            noOfLibrarians = i;
            s.close();   
        } catch (FileNotFoundException ex){
            if(file.exists()){
            JOptionPane.showMessageDialog(null,"Couldn't read Librarians","Couldn't read",2);
            return;
            }
            return;}
    }
    
    public void add(){
        lArray[noOfLibrarians]=(this);
        noOfLibrarians++;
    }
    
    public static int search(String name , String password){
        int i; 
        for (i=0;i<noOfLibrarians;i++){
            if (lArray[i].getName().equals(name) && lArray[i].getPassword().equals(password))
            return i; 
        }
        return -1;
    }
            
    public static boolean delete(int i){
    if (i<=0 || i > noOfLibrarians) return false;
    else {
        noOfLibrarians--;
        for (;i<noOfLibrarians;i++)
            lArray[i-1]=lArray[i];
        return true;
        }
    }
}
