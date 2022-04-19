package br.edu.femass.biblioteca.dao;

import br.edu.femass.biblioteca.model.Emprestimo;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmprestimoDao implements Dao<Emprestimo>{
    private static List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    @Override
    public void gravar(Emprestimo objeto) throws Exception {
        this.emprestimos.add(objeto);
        salvar();
    }
    @Override
    public void salvar() {
        XStream xs = new XStream();
        String xml = xs.toXML(emprestimos);
        try {
            FileWriter fw = new FileWriter("loans.xml");
            fw.write(xml);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Emprestimo> listar() throws Exception {
        try{
            XStream xs = new XStream();
            xs.allowTypes(new Class[]{ br.edu.femass.biblioteca.model.Usuario.class});
            xs.allowTypes(new Class[]{ br.edu.femass.biblioteca.model.Aluno.class});
            xs.allowTypes(new Class[]{ br.edu.femass.biblioteca.model.Professor.class});
            xs.allowTypes(new Class[]{ br.edu.femass.biblioteca.model.Livro.class});
            xs.allowTypes(new Class[]{ br.edu.femass.biblioteca.model.Copia.class});
            xs.allowTypes(new Class[]{ br.edu.femass.biblioteca.model.Emprestimo.class});
            this.emprestimos = (List<Emprestimo>) xs.fromXML(new File("loans.xml"));
            return this.emprestimos;
        } catch(Exception e) {
            salvar();
            listar();
        }
        return this.emprestimos;
    }

    @Override
    public void excluir(Emprestimo objeto) throws Exception {
        this.emprestimos.remove(objeto);
        salvar();
    }
}
