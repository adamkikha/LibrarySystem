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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Adam
 */
public class Student extends Person{
    private static int noOfStudents;
    private static Student sArray [];
    private int noOfBooks;
    private IssuedBook books [];

    public int getNoOfBooks() {
        return noOfBooks;
    }

    public IssuedBook getBook(int i) {
        return books[i];
    }
    
    
    public static void init(int s){
        sArray = new Student[s];
    }
    
    public static Student getStudent(int i) {
        return sArray[i];
    }
    
    public static int getNoOfStudents() {
        return noOfStudents;
    }
    
    public Student(String name ,String password ,String email ,String address ,String city ,String contactNo, int n){
        super(name, password, email, address, city, contactNo);
        books = new IssuedBook[3];
        noOfBooks = n;
    }
    
    public static void write(){
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        File file = new File ("Students.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            int j , i = 0;
            while (i < noOfStudents){
                j=0;
                bw.write(sArray[i].getName()+"\""+sArray[i].getPassword()+"\""+sArray[i].getEmail()+"\""+sArray[i].getAddress()+"\""+sArray[i].getCity()+"\""+sArray[i].getContactNo()+"\""+sArray[i].getNoOfBooks());
                while(j<sArray[i].noOfBooks){
                    bw.write("\""+sArray[i].books[j].getBook().getCallNO()+"\""+sArray[i].books[j].getIssuedDate()+"\""+format.format(sArray[i].books[j].getReturnDate()));
                    j++;
                }
                bw.newLine();
                i++;
            }
        } catch (IOException ex) {return;}
    }
    
    public static void read(){
        File file = new File("Students.txt");
        try{
            Scanner sc1 = new Scanner(file);
            Scanner sc2;
            Student s;
            IssuedBook b;
            String line;
            Date date;
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            int k , j , i = 0;
            while(sc1.hasNext()){
                j=0;
                line = sc1.nextLine();
                sc2 = new Scanner(line).useDelimiter("\"");
                s = new Student(sc2.next(),sc2.next(),sc2.next(),sc2.next(),sc2.next(),sc2.next(),Integer.parseInt(sc2.next()));
                while(sc2.hasNext()){
                    try {
                        b = new IssuedBook(Book.getBook(Book.search(sc2.next())),sc2.next(),format.parse(sc2.next()));
                    } catch (Exception e) {
                        break;
                    }
                    s.books[j++] = b;
                }
                sArray[i++]= s;
            }
            noOfStudents = i;
            sc1.close();
            sc1.close();
        } catch (FileNotFoundException ex){
            if(file.exists()){
            JOptionPane.showMessageDialog(null,"Couldn't read students","Couldn't read",2);
            return;
            }
            return;}
    }
    
    public void add(){
        sArray[noOfStudents]=(this);
        noOfStudents++;
    }
    
    public static int search(String name , String password){
        int i; 
        for (i=0;i<noOfStudents;i++){
            if (sArray[i].getName().equals(name) && sArray[i].getPassword().equals(password))
            return i; 
        }
        return -1;
    }
            
    public boolean issue(IssuedBook book){
        if(noOfBooks==3) return false;
        else{
            sArray[search(this.getName(), this.getPassword())].books[noOfBooks]=book;
            noOfBooks++;
            JOptionPane.showMessageDialog(null,"Book issued successfully","Done",1);
            return true;
        }
    }
    
    public boolean returnBack(Book book , Date date){
        for (int i=0;i<noOfBooks;i++){
            if(books[i].getBook().equals(book)){
                if(date.after(books[i].getReturnDate()))
                    JOptionPane.showMessageDialog(null,"a penality must be payed due to late returning"+'\n'+"for more details check with your nearest librarian.", "a penalty is due", 2);
                noOfBooks--;
                JOptionPane.showMessageDialog(null,"Book returned successfully","Done",1);
                return true;
            }    
        }
        return false;
}
}
