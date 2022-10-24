/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

/**
 *
 * @author Adam
 */
public class Person {
    
    private String name , password , email , address , city , contactNo;
    
    public Person(String name ,String password ,String email ,String address ,String city ,String contactNo) {
      this.name = name;
      this.password = password;
      this.email = email;
      this.address = address;
      this.city = city;
      this.contactNo = contactNo;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getContactNo() {
        return contactNo;
    }

}
