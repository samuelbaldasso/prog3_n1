package br.edu.femass.biblioteca.dao;

import br.edu.femass.biblioteca.model.Usuario;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDao implements Dao<Usuario>{
    private static List<Usuario> usuarios = new ArrayList<Usuario>();
    @Override
    public void gravar(Usuario objeto) throws Exception {
        this.usuarios.add(objeto);
        salvar();
    }
    @Override
    public void salvar() {
        XStream xs = new XStream();
        String xml = xs.toXML(usuarios);
        try {
            FileWriter fw = new FileWriter("users.xml");
            fw.write(xml);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Usuario> listar() throws Exception {
        try{
            XStream xs = new XStream();
            xs.allowTypes(new Class[]{ br.edu.femass.biblioteca.model.Usuario.class});
            xs.allowTypes(new Class[]{ br.edu.femass.biblioteca.model.Aluno.class});
            xs.allowTypes(new Class[]{ br.edu.femass.biblioteca.model.Professor.class});
            this.usuarios = (List<Usuario>) xs.fromXML(new File("users.xml"));
            return this.usuarios;
        } catch(Exception e) {
            salvar();
            listar();
        }
        return usuarios;
    }

    @Override
    public void excluir(Usuario objeto) throws Exception {
        usuarios.remove(objeto);
        salvar();
    }
}
