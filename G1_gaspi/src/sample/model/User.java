package sample.model;

public class User {
    private String pseudo;
    private String mail;
    private String sha1Password;

    public User(String pseudo, String mail, String sha1Password)
    {
        this.pseudo = pseudo;
        this.mail = mail;
        this.sha1Password = sha1Password;
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