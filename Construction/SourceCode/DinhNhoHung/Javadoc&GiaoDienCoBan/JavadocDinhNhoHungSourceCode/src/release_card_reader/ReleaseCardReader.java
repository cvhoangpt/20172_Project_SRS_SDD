package release_card_reader;

public class ReleaseCardReader {

	public static void main(String[] args) {
		AcountGUI acountGUI=new AcountGUI();
		Create cre=new Create();
		CardReader cardReader=new CardReader();
		acountGUI.requestToCreateNewCard();
		cre.informationCard(20168247, "Đinh Nho Hùng", "Vinh", "25/8/1998");
		cardReader.create();
	}

}
