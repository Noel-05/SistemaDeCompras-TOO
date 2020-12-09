package JavaBeans;

public class Usuario {
    private String nomUsuario, password;
    public Usuario(String user, String password) {
        this.nomUsuario = user;
        this.password = password;
    }
    public void setNomUsuario(String nom){
        this.nomUsuario = nom;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    public String getNomUsuario(){
        return nomUsuario;
    }
    public String getPassword(){
        return password;
    }
}
