package LibrarianGUI;

import java.util.LinkedHashMap;

import MemberGUI.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Message;
import gui.*;
public class Librarian_AddNewBookFormController {

    @FXML
    private TextArea Librarian_ShortDescribtionNewBook_TXA;

    @FXML
    private TextField Librarian_TitleNewBook_TXF;

    @FXML
    private TextField Librarian_AutorNewBook_TXF;

    @FXML
    private TextField Librarian_EditionNumNewBook_TXF;

    @FXML
    private TextField Librarian_ExecutionDateNewBook_TXF;

    @FXML
    private TextField Librarian_CategoryNewBook_TXF;

    @FXML
    private TextField Librarian_CatalogNumNewBook_TXF;

    @FXML
    private TextField Librarian_NumOfCopyNewBook_TXF;

    @FXML
    private TextField Librarian_PurchaseDateNewBook_TXF;

    @FXML
    private TextField Librarian_LocationNewBook_TXF;

    @FXML
    private Button StudentMainPage_Cancel_BTN;

    @FXML
    private Label StudentMainPage_DoneAddBook_LB;

    @FXML
    private TextField Librarian_PathOfContents_TXF;

    @FXML
    void LibrarianAddNewBookForm_Done() {
    	LinkedHashMap<String,Object> m = new LinkedHashMap<String,Object>();
    	Message msg;
    	/*m.put("B_name", );
    	m.put("B_author", );
    	m.put("B_edition", );
    	m.put("B_", );
    	m.put("", );
    	m.put("", );
    	m.put("", );*/
    }

    @FXML
    void LibrarianAddNewBookForm_cancel() {

    }

}
