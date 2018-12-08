package search_book;

public class SearchBook {

	public static void main(String[] args) {
		SearchBookGUI sbg=new SearchBookGUI();
		BookController bCtrl=new BookController();
		Book book=new Book();
		sbg.requestToSearchBook();
		bCtrl.requestGetBookFromList("Chúa tể những chiếc nhẫn");
		book.getBookList(2016,"Đinh Nho Hùng","JR","Fiction");
	}

}
