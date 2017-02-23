package utility;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.google.gson.JsonObject;

import dbconnection.InsertionDAO;
import dbconnection.TradingManagerDAO;
import dbconnection.UserDAO;
import elements.AuctionOffer;
import elements.Insertion;

public class AuctionManager extends Thread {

	public AuctionManager() {
		insertionDAO = new InsertionDAO();
		tradingManagerDAO = new TradingManagerDAO();
		userDAO=new UserDAO();
	}

	@Override
	public void run() {
		while (true) {
			try {
				checkInsertion();
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				sleep(wait);
				System.out.println("kokoko");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void checkInsertion() throws AddressException, MessagingException {
		List<Insertion> insertions = insertionDAO.getAuctionNotProcessed();
		System.out.println("inserzioni      " + insertions.size());
		for (Insertion insertion : insertions) {
			System.out.println(insertions.size());
			System.out.println("id_insertion   " + insertion.getId_item());
			ArrayList<AuctionOffer> offers = tradingManagerDAO.getOfferByIdItem(insertion.getId_item());
			if (offers.isEmpty())
				continue;
			AuctionOffer max = offers.get(0);
			for (int i = 1; i < offers.size(); i++) {
				if (offers.get(i).getOffer() > max.getOffer()) {
					max = offers.get(i);
				}
			}
			tradingManagerDAO.addToCart(max.getId_item(), max.getId_user());
			insertionDAO.updateProcessed(insertion.getId_item());
			JavaMail.sendMail(userDAO.getMailByUserId(max.getId_user()), JavaMail.Buyer(insertion));
			JavaMail.sendMail(userDAO.getMailByUserId(insertion.getId_user()), JavaMail.Seller(insertion,max.getOffer()));
		}

	}

	private long wait = 60000;
	InsertionDAO insertionDAO;
	TradingManagerDAO tradingManagerDAO;
	UserDAO userDAO;

	public static void main(String[] args) {
		 AuctionManager auctionManager=new AuctionManager();
		 auctionManager.start();
//		InsertionDAO insertionDAO=new InsertionDAO();
//		TradingManagerDAO tradingManagerDAO=new TradingManagerDAO();
//		List<Insertion> insertions = insertionDAO.getAuctionNotProcessed();
////		System.out.println("inserzioni      "+insertions.size());
//		for (Insertion insertion : insertions) {
//			System.out.println(insertions.size());
////			System.out.println("id_insertion   "+insertion.getId_item() );
//			ArrayList<AuctionOffer> offers = tradingManagerDAO.getOfferByIdItem(insertion.getId_item());
////			System.out.println(offers.size());
////			System.out.println(offers.isEmpty());
//			if (offers.isEmpty())
//				continue;
////			AuctionOffer max = offers.get(0);
////			for (int i = 1; i < offers.size(); i++) {
////				if (offers.get(i).getOffer() > max.getOffer()) {
////					max = offers.get(i);
////				}
////			}
//			tradingManagerDAO.addToCart(max.getId_item(), max.getId_user());
//			insertionDAO.updateProcessed(insertion.getId_item());
//			System.out.println("fine");
//		}

		
		
		
		

	}
}
