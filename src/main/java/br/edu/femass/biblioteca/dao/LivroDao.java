package br.edu.femass.biblioteca.dao;

import br.edu.femass.biblioteca.model.Livro;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public class LivroDao implements Dao<Livro>{
    private static List<Livro> livros = new ArrayList<Livro>();
    @Override
    public void gravar(Livro objeto) throws Exception {
        this.livros.add(objeto);
        salvar();
    }
    @Override
    public void salvar() {
        XStream xs = new XStream();
        String xml = xs.toXML(livros);
        try {
            FileWriter fw = new FileWriter("books.xml");
            fw.write(xml);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Livro> listar() throws Exception {
        try{
            XStream xs = new XStream();
            xs.allowTypes(new Class[]{ br.edu.femass.biblioteca.model.Livro.class});
            xs.allowTypes(new Class[]{ br.edu.femass.biblioteca.model.Autor.class});
            xs.allowTypes(new Class[]{ br.edu.femass.biblioteca.model.Copia.class});
            this.livros = (List<Livro>) xs.fromXML(new File("books.xml"));
            return this.livros;
        } catch(Exception e) {
            salvar();
            listar();
        }
        return this.livros;
    }

    @Override
    public void excluir(Livro objeto) throws Exception {
        this.livros.remove(objeto);
        salvar();
    }
}
