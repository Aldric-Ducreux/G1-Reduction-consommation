package sample;

public class Model {
    private String id;
    private String mdp;

    public Model(String id, String mdp){
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