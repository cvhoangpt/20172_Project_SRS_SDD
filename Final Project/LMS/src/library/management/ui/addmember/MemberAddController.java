package library.management.ui.addmember;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import library.management.alert.AlertScene;
import library.management.database.DataAccessHelper;
import library.management.ui.listmember.MemberListController;
import library.management.ui.listmember.MemberListController.Member;

public class MemberAddController implements Initializable {

    DataAccessHelper handler;

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    
    private Boolean isInEditMode = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = DataAccessHelper.getInstance();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)name.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addMember(ActionEvent event) {
        String mName = name.getText();
        String mID = id.getText();
        String mMobile = mobile.getText();
        String mEmail = email.getText();

        Boolean flag = mName.isEmpty() || mID.isEmpty() || mMobile.isEmpty() || mEmail.isEmpty();
        if (flag) {
            AlertScene.showErrorMessage("Cant process member", "Please Enter in all fields");
            return;
        }
        
        if(isInEditMode)
        {
            handleUpdateMember();
            return;
        }
        
        String st = "INSERT INTO MEMBER VALUES ("
                + "'" + mID + "',"
                + "'" + mName + "',"
                + "'" + mMobile + "',"
                + "'" + mEmail + "'"
                + ")";
        System.out.println(st);
        if (handler.execAction(st)) {
            AlertScene.showSimpleAlert("Member Added", "Saved");
        } else {
            AlertScene.showErrorMessage("Member cant be added", "Error Occured");
        }
    }
    
    public void infalteUI(MemberListController.Member member)
    {
        name.setText(member.getName());
        id.setText(member.getId());
        id.setEditable(false);
        mobile.setText(member.getMobile());
        email.setText(member.getEmail());
        
        isInEditMode = Boolean.TRUE;
    }

    private void handleUpdateMember() 
    {
        Member member = new MemberListController.Member(name.getText(), id.getText(), mobile.getText(), email.getText());
        if (DataAccessHelper.getInstance().updateMember(member)) {
            AlertScene.showSimpleAlert("Success", "Member Updated");
        } else {
            AlertScene.showErrorMessage("Failed", "Cant update member");
        }
    }

}
