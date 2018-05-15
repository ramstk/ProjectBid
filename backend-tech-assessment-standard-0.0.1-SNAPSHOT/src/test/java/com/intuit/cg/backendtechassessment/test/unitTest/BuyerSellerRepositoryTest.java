package com.intuit.cg.backendtechassessment.test.unitTest;
//
//
//import static org.assertj.core.api.Assertions.not;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.intuit.cg.backendtechassessment.domain.Seller;
//import com.intuit.cg.backendtechassessment.repository.BuyerRepository;
//import com.intuit.cg.backendtechassessment.repository.SellerRepository;
//import com.intuit.cg.backendtechassessment.repository.UniIDGen;
//import com.intuit.cg.backendtechassessment.test.util.*;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class BuyerSellerRepositoryTest {
//	private static final long NONEXISTENT_ID = 9999;
//
//	@Autowired
//	private SellerRepository sellerRepository;
//	@Autowired
//	private BuyerRepository buyerRepository;
//	@Autowired
//	private UniIDGen uniIDGenerator;
//	
//	
//	@Test
//	public void createSeller() {
//		Seller sell = new Seller();
//		sellerRepository.insert(newSeller());
//		//Seller sellFromDB = sellerRepository.findById(sell.getId());
//		//Assert.assertEquals(sell.getId(), (long)1);
//		
//	}
//	private Seller newSeller() {
//		Seller sell = new Seller();
//		sell.setsName("Ram");
//		sell.setID((long) 1);
//		return sell;
//	}
//	
//	
//	

//	private void assertFindAllIsCorrectWithSellerCount(int count) throws ParseException {
//		injectGivenNumberOfSellers(count);
//		assertExistingSellerCountIs(count);
//		List<Seller> reservationsFound = sellerRepository.findAll();
//		Assert.assertEquals(count, sellersFound.size());
//	}
//
//	private List<Seller> injectGivenNumberOfSellers(int count) throws ParseException {
//
//		List<Seller> injectedSeller = new ArrayList<>();
//
//		for (int i = 0; i < count; i++) {
//			injectedSeller.add(injectedSellers());
//		}
//
//		return injectedSeller;
//	}
//
//	private Seller injectedSellers() throws ParseException {
//		Seller createdSeller = sellerRepository.insert(BuyerSellerTestUtil.generateTestSeller());
//		return createdSeller;
//	}
//
//	private void assertExistingSellerCountIs(int count) {
//		Assert.assertEquals(count, sellerRepository.getCount());
//	}
//
//	private void assertNoExistingSeller() {
//		assertExistingSellerCountIs(0);
//	}
//
//	private static void assertSellerMatch(Seller expected, Seller actual) {
//		Assert.assertEquals(expected.getId(), actual.getId());
//		assertAllButIdsMatchBetweenSeller(expected, actual);
//	}
//
//	@Test
//	public void testFindExistingSellerEnsureOptionalIsPresent() throws Exception {
//		Reservation injectedSeller = injectSeller();
//		Optional<Seller> foundSeller = repository.findById(injectedSeller.getId());
//		Assert.assertTrue(foundSeller.isPresent());
//	}
//
//	@Test
//	public void testFindNonexistentSellerEnsureNotPresent() throws Exception {
//		assertNoExistingSellers();
//		Optional<Seller> reserve = repository.findById(NONEXISTENT_ID);
//		Assert.assertFalse(reserve.isPresent());
//	}
//
//	@Test
//	public void testFindExistingSellerEnsureCorrectSellerValues() throws Exception {
//		Seller injectedSeller = injectSeller();
//		Optional<Seller> founSeller = repository.findById(injectedSeller.getId());
//		assertSellersMatch(injectedSeller, foundSellern.get());
//	}
//
//	@Test
//	public void testFindAllWithNoExistingSellers() throws Exception {
//		assertFindAllIsCorrectWithSellerCount(0);
//	}
//
//	@Test
//	public void testFindAllWithOneExistingSellers() throws Exception {
//		assertFindAllIsCorrectWithSellerCount(1);
//	}
//	@Test
//	public void createBuyer() {
//		Buyer buy = new Buyer();
//		buyerRepository.insert(newBuyer());
//		//Buyer buyFromDB = buyerRepository.findById(buy.getId());
//		//Assert.assertEquals(buy.getId(), (long)1);
//		
//	}
//	private Buyer newBuyer() {
//		Buyer buy = new Buyer();
//		buy.setbName("Ram");
//		buy.setID((long) 1);
//		return buy;
//	}
//	
//	
//	

//	private void assertFindAllIsCorrectWithSellerCount(int count) throws ParseException {
//		injectGivenNumberOfSellers(count);
//		assertExistingSellerCountIs(count);
//		List<Seller> reservationsFound = sellerRepository.findAll();
//		Assert.assertEquals(count, sellersFound.size());
//	}
//
//	private List<Seller> injectGivenNumberOfSellers(int count) throws ParseException {
//
//		List<Seller> injectedSeller = new ArrayList<>();
//
//		for (int i = 0; i < count; i++) {
//			injectedSeller.add(injectedSellers());
//		}
//
//		return injectedSeller;
//	}
//
//	private Seller injectedSellers() throws ParseException {
//		Seller createdSeller = sellerRepository.insert(BuyerSellerTestUtil.generateTestSeller());
//		return createdSeller;
//	}
//
//	private void assertExistingSellerCountIs(int count) {
//		Assert.assertEquals(count, sellerRepository.getCount());
//	}
//
//	private void assertNoExistingSeller() {
//		assertExistingSellerCountIs(0);
//	}
//
//	private static void assertSellerMatch(Seller expected, Seller actual) {
//		Assert.assertEquals(expected.getId(), actual.getId());
//		assertAllButIdsMatchBetweenSeller(expected, actual);
//	}
//
//	@Test
//	public void testFindExistingSellerEnsureOptionalIsPresent() throws Exception {
//		Reservation injectedSeller = injectSeller();
//		Optional<Seller> foundSeller = repository.findById(injectedSeller.getId());
//		Assert.assertTrue(foundSeller.isPresent());
//	}
//
//	@Test
//	public void testFindNonexistentSellerEnsureNotPresent() throws Exception {
//		assertNoExistingSellers();
//		Optional<Buyer> reserve = repository.findById(NONEXISTENT_ID);
//		Assert.assertFalse(reserve.isPresent());
//	}
//
//	@Test
//	public void testFindExistingBuyerEnsureCorrectBuyerValues() throws Exception {
//		Seller injectedBuyer = injectBuyer();
//		Optional<Buyer> foundBuyer = repository.findById(injectedBuyer.getId());
//		assertBuyersMatch(injectedBuyer, foundBuyer.get());
//	}
//
//	@Test
//	public void testFindAllWithNoExistingBuyers() throws Exception {
//		assertFindAllIsCorrectWithBuyerCount(0);
//	}
//
//	@Test
//	public void testFindAllWithOneExistingBuyers() throws Exception {
//		assertFindAllIsCorrectWithBuyerCount(1);
//	}

}
