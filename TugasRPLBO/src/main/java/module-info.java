module org.tugasrplbo.tugasrplbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens org.tugasrplbo.tugasrplbo to javafx.fxml;
    exports org.tugasrplbo.tugasrplbo;
    exports org.tugasrplbo.tugasrplbo.Controller;
    opens org.tugasrplbo.tugasrplbo.Controller to javafx.fxml;
    exports org.tugasrplbo.tugasrplbo.Model;
    opens org.tugasrplbo.tugasrplbo.Model to javafx.fxml;


}