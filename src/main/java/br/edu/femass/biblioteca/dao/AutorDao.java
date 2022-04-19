package br.edu.femass.biblioteca.dao;

import br.edu.femass.biblioteca.model.Autor;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutorDao implements Dao<Autor>{
    private static List<Autor> autores = new ArrayList<Autor>();

    @Override
    public void gravar(Autor objeto) throws Exception {
        this.autores.add(objeto);
        salvar();
    }
    @Override
    public void salvar() {
        XStream xs = new XStream();
        String xml = xs.toXML(autores);
        try {
            FileWriter fw = new FileWriter("autor.xml");
            fw.write(xml);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Autor> listar() throws Exception {
        try{
            XStream xs = new XStream();
            this.autores = (List<Autor>) xs.fromXML(new File("autores.xml"));
            return this.autores;
        } catch(Exception e) {
            salvar();
            listar();
        }
        return autores;
    }

    @Override
    public void excluir(Autor objeto) throws Exception {
        autores.remove(objeto);
    }
}
