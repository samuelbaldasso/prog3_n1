module br.edu.femass.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires xstream;
    requires java.logging;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires static lombok;

    opens br.edu.femass.biblioteca.dao to xstream;
    opens br.edu.femass.biblioteca.model to xstream;
    opens br.edu.femass.biblioteca to javafx.fxml;
    opens br.edu.femass.biblioteca.gui to javafx.fxml;

    exports br.edu.femass.biblioteca;
    exports br.edu.femass.biblioteca.model;
    exports br.edu.femass.biblioteca.gui;

}