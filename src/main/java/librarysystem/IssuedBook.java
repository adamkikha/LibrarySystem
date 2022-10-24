/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.util.Date;

/**
 *
 * @author Adam
 */
public class IssuedBook {
private Book book;
private String issuedDate;
private Date returnDate;

public IssuedBook(Book b,String iDate , Date rDate){
    book = b;
    issuedDate = iDate;
    returnDate = rDate;
}

public Book getBook() {
    return book;
}

public String getIssuedDate() {
    return issuedDate;
}

public Date getReturnDate() {
    return returnDate;
}
}
