package com.misiontic.account_ms.models;
import org.springframework.data.annotation.Id;
import java.util.Date;


public class Account {
    // Attributes
    @Id
    private String username;
    private Integer balance;
    private Date lastChange;

    // Constructor
    public Account(String username, Integer balance, Date lastChange) {
            this.username = username;
            this.balance = balance;
            this.lastChange = lastChange;
    }

    // Methods
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public Integer getBalance(){
        return this.balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Date getLastChange(){
        return this.lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

}
