package sample;

public class Connexion {
    private String id;
    private String mdp;

    public Connexion(String id, String mdp){
        this.id = id;
        this.mdp = mdp;
    }

    public boolean connec (String id, String mdp){
        if (id.equals("admin") && mdp.equals("admin")) {
            return true;
        }
        return false;
    }
}