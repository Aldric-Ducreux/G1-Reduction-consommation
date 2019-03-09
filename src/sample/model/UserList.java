package sample.model;

import java.util.ArrayList;

public class UserList {
    //pour inscription
    private ArrayList<User> comptes;

    public UserList (ArrayList<User> comptes){
        this.comptes = comptes;
    }

    public ArrayList<User> getComptes() { return comptes; }
    public void setComptes(ArrayList<User> comptes) { this.comptes = comptes; }


}

