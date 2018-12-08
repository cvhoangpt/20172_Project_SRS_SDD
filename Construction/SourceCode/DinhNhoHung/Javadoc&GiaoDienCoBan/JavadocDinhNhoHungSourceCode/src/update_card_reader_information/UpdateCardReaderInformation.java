package update_card_reader_information;

public class UpdateCardReaderInformation {

	public static void main(String[] args) {
		SearchReaderGUI sRGUI=new SearchReaderGUI();
		ReaderController rCtrl=new ReaderController();
		Reader reader=new Reader();
		ReaderInformationGUI rIGUI=new ReaderInformationGUI();
		sRGUI.requestToSearchReader();
		rCtrl.requestGetStudent(20168247);
		reader.update(20168247, "Đinh Nho Hùng", "Vinh", "25/08/1998");
		rIGUI.pushNewInformation();
	}

}
