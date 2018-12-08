package library.management.ui.listmember;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import library.management.alert.AlertScene;
import library.management.database.DataAccessHelper;
import library.management.ui.addbook.BookAddController;
import library.management.ui.addmember.MemberAddController;
import library.management.ui.main.MainController;
import library.management.util.Util;

public class MemberListController implements Initializable {

    ObservableList<Member> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member, String> nameCol;
    @FXML
    private TableColumn<Member, String> idCol;
    @FXML
    private TableColumn<Member, String> mobileCol;
    @FXML
    private TableColumn<Member, String> emailCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
    
    private void loadData() {
        list.clear();
        
        DataAccessHelper handler = DataAccessHelper.getInstance();
        String qu = "SELECT * FROM MEMBER";
        ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()) {
                String name = rs.getString("name");
                String mobile = rs.getString("mobile");
                String id = rs.getString("id");
                String email = rs.getString("email");
               
                list.add(new Member(name, id, mobile, email));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableView.setItems(list);
    }

    @FXML
    private void handleMemberDelete(ActionEvent event) {
         //Fetch the selected row
        MemberListController.Member selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertScene.showErrorMessage("No member selected", "Please select a member for deletion.");
            return;
        }
        if(DataAccessHelper.getInstance().isMemberHasAnyBooks(selectedForDeletion))
        {
            AlertScene.showErrorMessage("Cant be deleted", "This member has some books.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting book");
        alert.setContentText("Are you sure want to delete " + selectedForDeletion.getName()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Boolean result = DataAccessHelper.getInstance().deleteMember(selectedForDeletion);
            if (result) {
                AlertScene.showSimpleAlert("Book deleted", selectedForDeletion.getName()+ " was deleted successfully.");
                list.remove(selectedForDeletion);
            } else {
                AlertScene.showSimpleAlert("Failed", selectedForDeletion.getName()+ " could not be deleted");
            }
        } else {
            AlertScene.showSimpleAlert("Deletion cancelled", "Deletion process cancelled");
        }
    }

    @FXML
    private void handleRefresh(ActionEvent event) 
    {
        loadData();
    }

    @FXML
    private void handleMemberEdit(ActionEvent event) 
    {
       
        Member selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertScene.showErrorMessage("No member selected", "Please select a member for edit.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/management/ui/addmember/member_add.fxml"));
            Parent parent = loader.load();

            MemberAddController controller = (MemberAddController) loader.getController();
            controller.infalteUI(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Member");
            stage.setScene(new Scene(parent));
            stage.show();
          
            
            stage.setOnCloseRequest((e)->{
                handleRefresh(new ActionEvent());
            });
            
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static class Member {

        private final String name;
        private final String id;
        private final String mobile;
        private final String email;

        public Member(String name, String id, String mobile, String email) {
            this.name = name;
            this.id = id;
            this.mobile = mobile;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public String getMobile() {
            return mobile;
        }

        public String getEmail() {
            return email;
        }

    }

}
