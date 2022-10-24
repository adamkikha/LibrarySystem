/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Adam
 */
public class Book {
    private String callNO , name , publisher, author,addedDate;
    private int quantity , issued;
    private static int noOfBooks;
    private static Book bArray [];

    public String getAddedDate() {
        return addedDate;
    }

    public String getCallNO() {
        return callNO;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }


    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public static int getNoOfBooks() {
        return noOfBooks;
    }
    
    public static void init(int n){
    bArray = new Book[n];
    }
    public Book (String callNO ,String name ,String author,String publisher,String addedDate,int quantity,int issued){
        this.callNO = callNO;
        this.name = name;
        this.publisher = publisher;
        this.author = author;
        this.addedDate = addedDate;
        this.quantity = quantity;
        this.issued = issued;
    }
    
    public static Book getBook(int i) {
        return bArray[i];
    }

    public int getIssued() {
        return issued;
    }
    
    public boolean issue(){
        if(issued < quantity){
            issued++;
            return true;
        }
        else return false;    
    }
    public void returnBack(){
           issued--;
    }
    public void add(){
    bArray[noOfBooks]=(this);
    noOfBooks++;
}
    public static int search(String callNo){
    int i; 
    for (i=0;i<noOfBooks;i++){
    if (bArray[i].callNO.equals(callNo))
        return i; 
    }
    return -1;
    }

    public static void write(){
        File file = new File ("Books.txt");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file));) {
            int i = 0;
            while (i < noOfBooks){
                bw.write(bArray[i].getCallNO()+"\""+bArray[i].getName()+"\""+bArray[i].getAuthor()+"\""+bArray[i].getPublisher()+"\""+bArray[i].getAddedDate()+"\""+Integer.toString(bArray[i].getQuantity())+"\""+Integer.toString(bArray[i].getIssued()));
                bw.newLine();
                i++;
            }
            bw.close();    
        } catch (IOException ex) {return;}
    }
    
    public static void read(){
        File file = new File("Books.txt");
        Scanner s, s2;
        String line;
        try{
            s = new Scanner(file);    
            int i = 0;
            while(s.hasNextLine()){
                line = s.nextLine();
                s2 = new Scanner(line).useDelimiter("\"");
                Book b;
                while(s2.hasNext()){
                    b = new Book(s2.next(),s2.next(),s2.next(),s2.next(),s2.next(),Integer.parseInt(s2.next()),Integer.parseInt(s2.next()));    
                    bArray[i++]= b;
                }
            }
            noOfBooks = i;
            s.close();
        } catch (FileNotFoundException ex){
            if(file.exists()){
            JOptionPane.showMessageDialog(null,"Couldn't read Books","Couldn't read",2);
            return;
            }
            return;}
    }
    










}
