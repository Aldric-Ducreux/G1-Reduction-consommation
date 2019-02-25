package sample.model;

public class User {
    private int id;
    private String name;
    private String pseudo;
    private String mail;
    private String sha1Password;

    public User(int id, String name, String pseudo, String mail, String sha1Password)
    {
        this.id = id;
        this.name = name;
        this.pseudo = pseudo;
        this.mail = mail;
        this.sha1Password = sha1Password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSha1Password() {
        return sha1Password;
    }
    public void setSha1Password(String sha1Password) {
        this.sha1Password = sha1Password;
    }
}