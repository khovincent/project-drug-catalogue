package org.tugasrplbo.tugasrplbo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.tugasrplbo.tugasrplbo.Controller.*;
import org.tugasrplbo.tugasrplbo.Model.Obat;

import java.io.IOException;

public class Apps extends Application {
    private static Stage primaryStage;


    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Title");
        primaryStage.setScene(new Scene(loadFXML("login-view")));
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setRoot(String fxml, String title, boolean isResizeable) throws IOException {
        primaryStage.getScene().setRoot(loadFXML(fxml));
        primaryStage.sizeToScene();
        primaryStage.setResizable(isResizeable);
        if (title != null) {
            primaryStage.setTitle(title);
        }
        primaryStage.show();
    }

    public static void openViewWithModal(String fxml, String title, boolean isResizeable, Obat obat, HomeController homeController) throws IOException {
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();

        if (obat != null && homeController != null) {
            PerbaruiObatController controller = loader.getController();
            controller.initData(homeController, obat);
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    public static void openTambahObatView(String fxml, String title, boolean isResizeable, HomeController homeController) throws IOException {
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();

        // Get the controller and set the data
        TambahObatController controller = loader.getController();
        controller.setHomeController(homeController);

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    public static int openTambahJumlahObatView(String fxml, String title, boolean isResizeable, UserHomeController userHomeController) throws IOException {
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();

        // Get the controller and set the data
        TambahJumlahObatController controller = loader.getController();
        controller.setUserHomeController(userHomeController);

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        if (controller.isConfirmed()) {
            return controller.getJumlah();
        }
        return -1; // Indicates that the action was canceled
    }

    public static void openKeranjangViewWithController(String fxml, String title, boolean isResizeable, UserHomeController userHomeController) throws IOException {
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();

        // Get the controller and set the data
        KeranjangController controller = loader.getController();
        controller.setKeranjangItems(userHomeController.getKeranjangObats());

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.show();
    }
    public static void openProfileViewWithController(String fxml, String title, boolean isResizeable, UserHomeController userHomeController) throws IOException {
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();

        // Optionally, set any data or controllers if needed here

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.show();
    }



    public static void openKeranjangViewWithController(String fxml, String title,  boolean isResizeable, ProfileController profileController) throws IOException{
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();

        // Optionally, set any data or controllers if needed here

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.show();
    }
    public static void openUserhomeViewWithController(String fxml, String title,  boolean isResizeable, ProfileController profileController) throws IOException{
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();

        // Optionally, set any data or controllers if needed here

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.show();
    }

    public static void openAdminHomeViewWithController(String fxml, String title, boolean isResizeable, ProfileAdminController profileAdminController) throws IOException {
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.show();
    }

    public static void openLogOutViewWithController(String fxml, String title,  boolean isResizeable, ProfileController profileController) throws IOException{
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();

        // Optionally, set any data or controllers if needed here

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.show();
    }



    public static void openLogOutViewWithController(String fxml, String title, boolean resizable, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        loader.setController(controller);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.setResizable(resizable);
        stage.show();
    }

    public static void openProfileAdminHomeViewWithController(String fxml, String title, boolean isResizeable, HomeController HomeController) throws IOException {
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();

        // Optionally, set any data or controllers if needed here

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.show();
    }
    public static void openLogOutAdminViewWithController(String fxml, String title,  boolean isResizeable, ProfileAdminController ProfileAdminController) throws IOException{
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();

        // Optionally, set any data or controllers if needed here

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.show();
    }

    public static void opencreditHomeViewWithController(String fxml, String title,  boolean isResizeable, HomeController homeController) throws IOException{
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();

        // Optionally, set any data or controllers if needed here

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.show();
    }

    public static void opencreditUserViewWithController(String fxml, String title,  boolean isResizeable, UserHomeController userHomeController) throws IOException{
        FXMLLoader loader = new FXMLLoader(Apps.class.getResource(fxml + ".fxml"));
        Parent parent = loader.load();

        // Optionally, set any data or controllers if needed here

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.sizeToScene();
        stage.setTitle(title);
        stage.setResizable(isResizeable);
        stage.initOwner(primaryStage);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
