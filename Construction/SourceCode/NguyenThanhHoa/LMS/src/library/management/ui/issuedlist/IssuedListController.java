package library.management.ui.issuedlist;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.management.database.DatabaseHandler;
import library.management.settings.Preferences;
import library.management.util.Util;


public class IssuedListController implements Initializable {

    ObservableList<IssueInfo> list = FXCollections.observableArrayList();

    @FXML
    private TableView<IssueInfo> tableView;
    @FXML
    private TableColumn<IssueInfo, Integer> idCol;
    @FXML
    private TableColumn<IssueInfo, String> bookIDCol;
    @FXML
    private TableColumn<IssueInfo, String> bookNameCol;
    @FXML
    private TableColumn<IssueInfo, String> holderNameCol;
    @FXML
    private TableColumn<IssueInfo, String> issueCol;
    @FXML
    private TableColumn<IssueInfo, Integer> daysCol;
    @FXML
    private TableColumn<IssueInfo, Float> fineCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookIDCol.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        holderNameCol.setCellValueFactory(new PropertyValueFactory<>("holderName"));
        issueCol.setCellValueFactory(new PropertyValueFactory<>("dateOfIssue"));
        daysCol.setCellValueFactory(new PropertyValueFactory<>("days"));
        fineCol.setCellValueFactory(new PropertyValueFactory<>("fine"));
    }

    private void loadData() {
        list.clear();

        DatabaseHandler handler = DatabaseHandler.getInstance();
      
        String qu = "SELECT ISSUE.bookID, ISSUE.memberID, ISSUE.issueTime, MEMBER.name, BOOK.title FROM ISSUE\n"
                + "LEFT OUTER JOIN MEMBER\n"
                + "ON MEMBER.id = ISSUE.memberID\n"
                + "LEFT OUTER JOIN BOOK\n"
                + "ON BOOK.id = ISSUE.bookID";
        ResultSet rs = handler.execQuery(qu);
        Preferences pref = Preferences.getPreferences();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        try {
            int counter = 0;
            while (rs.next()) {
                counter += 1;
                String memberName = rs.getString("name");
                String bookID = rs.getString("bookID");
                String bookTitle = rs.getString("title");
                Timestamp issueTime = rs.getTimestamp("issueTime");
                System.out.println("Issued on " + issueTime);
                Integer days = Math.toIntExact(TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - issueTime.getTime())) + 1;
                Float fine = Util.getFineAmount(days);
                IssueInfo issueInfo = new IssueInfo(counter, bookID, bookTitle, memberName, dateFormat.format(new Date(issueTime.getTime())), days, fine);
                list.add(issueInfo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        tableView.setItems(list);
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
    }

    @FXML
    private void handleMemberEdit(ActionEvent event) {
    }

    @FXML
    private void handleMemberDelete(ActionEvent event) {
    }

    public static class IssueInfo {

        private final int id;
        private final String bookID;
        private final String bookName;
        private final String holderName;
        private final String dateOfIssue;
        private final int nDays;
        private final float fine;

        public IssueInfo(int id, String bookID, String bookName, String holderName, String dateOfIssue, Integer nDays, float fine) {
            this.id = id;
            this.bookID = bookID;
            this.bookName = bookName;
            this.holderName = holderName;
            this.dateOfIssue = dateOfIssue;
            this.nDays = nDays;
            this.fine = fine;
            System.out.println(this.nDays);
        }

        public Integer getId() {
            return id;
        }

        public String getBookID() {
            return bookID;
        }

        public String getBookName() {
            return bookName;
        }

        public String getHolderName() {
            return holderName;
        }

        public String getDateOfIssue() {
            return dateOfIssue;
        }

        public Integer getDays() {
            return nDays;
        }

        public Float getFine() {
            return fine;
        }
    }
}
