package library.management.ui.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.management.alert.AlertScene;
import library.management.database.DatabaseHandler;
import library.management.util.Util;

public class MainController implements Initializable {

    private static final String BOOK_NOT_AVAILABLE = "Not Available";
    private static final String NO_SUCH_BOOK_AVAILABLE = "No Such Book Available";
    private static final String NO_SUCH_MEMBER_AVAILABLE = "No Such Member Available";
    private static final String BOOK_AVAILABLE = "Available";

    private Boolean isReadyForSubmission = false;
    private DatabaseHandler databaseHandler;
    private PieChart bookChart;
    private PieChart memberChart;

    @FXML
    private HBox book_info;
    @FXML
    private HBox member_info;
    @FXML
    private TextField bookIDInput;
    @FXML
    private Text bookName;
    @FXML
    private Text bookAuthor;
    @FXML
    private Text bookStatus;
    @FXML
    private TextField memberIDInput;
    @FXML
    private Text memberName;
    @FXML
    private Text memberMobile;
    @FXML
    private JFXTextField bookID;
    @FXML
    private StackPane rootPane;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Text memberNameHolder;
    @FXML
    private Text memberEmailHolder;
    @FXML
    private Text memberContactHolder;
    @FXML
    private Text bookNameHolder;
    @FXML
    private Text bookAuthorHolder;
    @FXML
    private Text bookPublisherHolder;
    @FXML
    private Text issueDateHolder;
    @FXML
    private Text numberDaysHolder;
    @FXML
    private Text fineInfoHolder;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private JFXButton renewButton;
    @FXML
    private JFXButton submissionButton;
    @FXML
    private HBox submissionDataContainer;
    @FXML
    private StackPane bookInfoContainer;
    @FXML
    private StackPane memberInfoContainer;
    @FXML
    private Tab bookIssueTab;
    @FXML
    private Tab renewTab;
    @FXML
    private JFXTabPane mainTabPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXDepthManager.setDepth(book_info, 1);
        JFXDepthManager.setDepth(member_info, 1);

        databaseHandler = DatabaseHandler.getInstance();

        
        initGraphs();
    }

    @FXML
    private void loadBookInfo(ActionEvent event) {
        clearBookCache();
        enableDisableGraph(false);

        String id = bookIDInput.getText();
        String qu = "SELECT * FROM BOOK WHERE id = '" + id + "'";
        ResultSet rs = databaseHandler.execQuery(qu);
        Boolean flag = false;
        try {
            while (rs.next()) {
                String bName = rs.getString("title");
                String bAuthor = rs.getString("author");
                Boolean bStatus = rs.getBoolean("isAvail");

                bookName.setText(bName);
                bookAuthor.setText(bAuthor);
                String status = (bStatus) ? BOOK_AVAILABLE : BOOK_NOT_AVAILABLE;
                bookStatus.setText(status);

                flag = true;
            }

            if (!flag) {
                bookName.setText(NO_SUCH_BOOK_AVAILABLE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void clearBookCache() {
        bookName.setText("");
        bookAuthor.setText("");
        bookStatus.setText("");
    }

    void clearMemberCache() {
        memberName.setText("");
        memberMobile.setText("");
    }

    @FXML
    private void loadMemberInfo(ActionEvent event) {
        clearMemberCache();
        enableDisableGraph(false);

        String id = memberIDInput.getText();
        String qu = "SELECT * FROM MEMBER WHERE id = '" + id + "'";
        ResultSet rs = databaseHandler.execQuery(qu);
        Boolean flag = false;
        try {
            while (rs.next()) {
                String mName = rs.getString("name");
                String mMobile = rs.getString("mobile");

                memberName.setText(mName);
                memberMobile.setText(mMobile);

                flag = true;
            }

            if (!flag) {
                memberName.setText(NO_SUCH_MEMBER_AVAILABLE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadIssueOperation(ActionEvent event) {
        if (checkForIssueValidity()) {
            JFXButton btn = new JFXButton("Okay!");
            AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Invalid Input", null);
            return;
        }
        if (bookStatus.getText().equals(BOOK_NOT_AVAILABLE)) {
            JFXButton btn = new JFXButton("OK!");
            JFXButton viewDetails = new JFXButton("View Details");
            viewDetails.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
                String bookToBeLoaded = bookIDInput.getText();
                bookID.setText(bookToBeLoaded);
                bookID.fireEvent(new ActionEvent());
                mainTabPane.getSelectionModel().select(renewTab);
            });
            AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn, viewDetails), "Already issued book", "This book is already issued. Cant process issue request");
            return;
        }

        String memberID = memberIDInput.getText();
        String bookID = bookIDInput.getText();

        JFXButton yesButton = new JFXButton("YES");
        yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
            String str = "INSERT INTO ISSUE(memberID,bookID) VALUES ("
                    + "'" + memberID + "',"
                    + "'" + bookID + "')";
            String str2 = "UPDATE BOOK SET isAvail = false WHERE id = '" + bookID + "'";
            System.out.println(str + " and " + str2);

            if (databaseHandler.execAction(str) && databaseHandler.execAction(str2)) {
                JFXButton button = new JFXButton("Done!");
                AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(button), "Book Issue Complete", null);
                refreshGraphs();
            } else {
                JFXButton button = new JFXButton("OK");
                AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(button), "Issue Operation Failed", null);
            }
            clearIssueEntries();
        });
        JFXButton noButton = new JFXButton("NO");
        noButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
            JFXButton button = new JFXButton("That's Okay");
            AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(button), "Issue Cancelled", null);
            clearIssueEntries();
        });
        AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(yesButton, noButton), "Confirm Issue", "Are you sure want to issue the book " + bookName.getText() + " to " + memberName.getText() + " ?");
    }

    @FXML
    private void loadBookInfo2(ActionEvent event) {
        clearEntries();
        ObservableList<String> issueData = FXCollections.observableArrayList();
        isReadyForSubmission = false;

        try {
            String id = bookID.getText();
            String myQuery = "SELECT ISSUE.bookID, ISSUE.memberID, ISSUE.issueTime, ISSUE.renew_count,\n"
                    + "MEMBER.name, MEMBER.mobile, MEMBER.email,\n"
                    + "BOOK.title, BOOK.author, BOOK.publisher\n"
                    + "FROM ISSUE\n"
                    + "LEFT JOIN MEMBER\n"
                    + "ON ISSUE.memberID=MEMBER.ID\n"
                    + "LEFT JOIN BOOK\n"
                    + "ON ISSUE.bookID=BOOK.ID\n"
                    + "WHERE ISSUE.bookID='" + id + "'";
            ResultSet rs = databaseHandler.execQuery(myQuery);
            if (rs.next()) {
                memberNameHolder.setText(rs.getString("name"));
                memberContactHolder.setText(rs.getString("mobile"));
                memberEmailHolder.setText(rs.getString("email"));

                bookNameHolder.setText(rs.getString("title"));
                bookAuthorHolder.setText(rs.getString("author"));
                bookPublisherHolder.setText(rs.getString("publisher"));

                Timestamp mIssueTime = rs.getTimestamp("issueTime");
                Date dateOfIssue = new Date(mIssueTime.getTime());
                issueDateHolder.setText(dateOfIssue.toString());
                Long timeElapsed = System.currentTimeMillis() - mIssueTime.getTime();
                Long daysElapsed = TimeUnit.DAYS.convert(timeElapsed, TimeUnit.MILLISECONDS);
                numberDaysHolder.setText(daysElapsed.toString());
                fineInfoHolder.setText("Invalid");

                isReadyForSubmission = true;
                disableEnableControls(true);
                submissionDataContainer.setOpacity(1);
            } else {
                JFXButton button = new JFXButton("OK");
                AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(button), "Invalid", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void loadSubmissionOp(ActionEvent event) {
        if (!isReadyForSubmission) {
            JFXButton btn = new JFXButton("OK");
            AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Please select a book to submit", "Cant simply submit a null book :-)");
            return;
        }

        JFXButton yesButton = new JFXButton("Yes");
        yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
            String id = bookID.getText();
            String ac1 = "DELETE FROM ISSUE WHERE BOOKID = '" + id + "'";
            String ac2 = "UPDATE BOOK SET ISAVAIL = TRUE WHERE ID = '" + id + "'";

            if (databaseHandler.execAction(ac1) && databaseHandler.execAction(ac2)) {
                JFXButton btn = new JFXButton("Done!");
                AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Book has been submitted", null);
                disableEnableControls(false);
                submissionDataContainer.setOpacity(0);
            } else {
                JFXButton btn = new JFXButton("OK");
                AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Submission Has Been Failed", null);
            }
        });
        JFXButton noButton = new JFXButton(" Cancel");
        noButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
            JFXButton btn = new JFXButton("OK");
            AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Submission Operation cancelled", null);
        });

        AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(yesButton, noButton), "Confirm Submission Operation", "Are you sure want to return the book ?");
    }

    @FXML
    private void loadRenewOp(ActionEvent event) {
        if (!isReadyForSubmission) {
            JFXButton btn = new JFXButton("Okay!");
            AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Please select a book to renew", null);
            return;
        }
        JFXButton yesButton = new JFXButton("Yes");
        yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
            String ac = "UPDATE ISSUE SET issueTime = CURRENT_TIMESTAMP, renew_count = renew_count+1 WHERE BOOKID = '" + bookID.getText() + "'";
            System.out.println(ac);
            if (databaseHandler.execAction(ac)) {
                JFXButton btn = new JFXButton("Alright!");
                AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Book Has Been Renewed", null);
                disableEnableControls(false);
                submissionDataContainer.setOpacity(0);
            } else {
                JFXButton btn = new JFXButton("OK");
                AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Renew Has Been Failed", null);
            }
        });
        JFXButton noButton = new JFXButton("Cancel");
        noButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
            JFXButton btn = new JFXButton("OK");
            AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(btn), "Renew Operation cancelled", null);
        });
        AlertScene.showMaterialDialog(rootPane, rootAnchorPane, Arrays.asList(yesButton, noButton), "Confirm Renew Operation", "Are you sure want to renew the book ?");
    }

    @FXML
    private void handleMenuClose(ActionEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();
    }

    @FXML
    private void handleMenuAddBook(ActionEvent event) {
        Util.loadWindow(getClass().getResource("/library/management/ui/addbook/add_book.fxml"), "Add New Book", null);
    }

    @FXML
    private void handleMenuAddMember(ActionEvent event) {
        Util.loadWindow(getClass().getResource("/library/management/ui/addmember/member_add.fxml"), "Add New Member", null);
    }

    @FXML
    private void handleMenuViewBook(ActionEvent event) {
        Util.loadWindow(getClass().getResource("/library/management/ui/listbook/book_list.fxml"), "Book List", null);
    }
    @FXML
    private void handleMenuViewMember(ActionEvent event) {
        Util.loadWindow(getClass().getResource("/library/management/ui/listmember/member_list.fxml"), "Member List", null);
    }

    @FXML
    private void handleAboutMenu(ActionEvent event) {
        Util.loadWindow(getClass().getResource("/library/management/ui/about/about.fxml"), "About Me", null);
    }
     @FXML
    private void handleMenuViewIssuedList(ActionEvent event) {
        Util.loadWindow(getClass().getResource("/library/management/ui/issuedlist/issued_list.fxml"), "Issued List", null);
    }
    @FXML
    private void loadSettings(ActionEvent event) {
        Util.loadWindow(getClass().getResource("/library/management/settings/settings.fxml"), "Settings", null);
    }

    @FXML
    private void handleMenuFullScreen(ActionEvent event) {
        Stage stage = ((Stage) rootPane.getScene().getWindow());
        stage.setFullScreen(!stage.isFullScreen());
    }

  

    private void clearEntries() {
        memberNameHolder.setText("");
        memberEmailHolder.setText("");
        memberContactHolder.setText("");

        bookNameHolder.setText("");
        bookAuthorHolder.setText("");
        bookPublisherHolder.setText("");

        issueDateHolder.setText("");
        numberDaysHolder.setText("");
        fineInfoHolder.setText("");

        disableEnableControls(false);
        submissionDataContainer.setOpacity(0);
    }

    private void disableEnableControls(Boolean enableFlag) {
        if (enableFlag) {
            renewButton.setDisable(false);
            submissionButton.setDisable(false);
        } else {
            renewButton.setDisable(true);
            submissionButton.setDisable(true);
        }
    }

    private void clearIssueEntries() {
        bookIDInput.clear();
        memberIDInput.clear();
        bookName.setText("");
        bookAuthor.setText("");
        bookStatus.setText("");
        memberMobile.setText("");
        memberName.setText("");
        enableDisableGraph(true);
    }

    private void initGraphs() {
        bookChart = new PieChart(databaseHandler.getBookGraphStatistics());
        memberChart = new PieChart(databaseHandler.getMemberGraphStatistics());
        bookInfoContainer.getChildren().add(bookChart);
        memberInfoContainer.getChildren().add(memberChart);

        bookIssueTab.setOnSelectionChanged((Event event) -> {
            clearIssueEntries();
            if (bookIssueTab.isSelected()) {
                refreshGraphs();
            }
        });
    }

    private void refreshGraphs() {
        bookChart.setData(databaseHandler.getBookGraphStatistics());
        memberChart.setData(databaseHandler.getMemberGraphStatistics());
    }

    private void enableDisableGraph(Boolean status) {
        if (status) {
            bookChart.setOpacity(1);
            memberChart.setOpacity(1);
        } else {
            bookChart.setOpacity(0);
            memberChart.setOpacity(0);
        }
    }

    private boolean checkForIssueValidity() {
        bookIDInput.fireEvent(new ActionEvent());
        memberIDInput.fireEvent(new ActionEvent());
        return bookIDInput.getText().isEmpty() || memberIDInput.getText().isEmpty()
                || memberName.getText().isEmpty() || bookName.getText().isEmpty()
                || bookName.getText().equals(NO_SUCH_BOOK_AVAILABLE) || memberName.getText().equals(NO_SUCH_MEMBER_AVAILABLE);
    }

}
