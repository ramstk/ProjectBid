///**
// * Testing sample for verifying Repository
// */
//package com.intuit.cg.backendtechassessment.test.unitTest;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//import java.sql.SQLException;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.intuit.cg.backendtechassessment.domain.Buyer;
//import com.intuit.cg.backendtechassessment.domain.Seller;
//import com.intuit.cg.backendtechassessment.repository.BuyerRepository;
//import com.intuit.cg.backendtechassessment.repository.SellerRepository;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RepositoryTest {
//	//@Autowired
//   // private TestEntityManager entityManager;
//	@Autowired
//	JdbcTemplate jdbcTemplate;
//    @Autowired
//    private BuyerRepository bRepository;
//    @Autowired
//    private SellerRepository sRepository;
//    
//    @Test
//    public void whenBuyerFindByID_thenReturnBuyer() {
//        // given
//        Buyer buy = new Buyer();
//        buy.setbName("Ram");
//        buy.setID((long) 1);
//       // entityManager.persist(0);
//      //flush();
//     
//        // when
//        Buyer found = bRepository.findById(buy.getId());
//     
//        // then
//        assertThat(found.getId())
//          .isEqualTo(buy.getId());
//    }
//    @Test
//    public void whenSellerFindByID_thenReturnSeller() {
//        // given
//        Seller sell = new Seller();
//        sell.setsName("Ram");
//        sell.setID((long) 1);
//     
//        // when
//        Seller found = sRepository.findById(sell.getId());
//     
//        // then
//        assertThat(found.getId())
//          .isEqualTo(sell.getId());
//    }
//    @SuppressWarnings("unused")
//	@Test
//    public void insertSeller_verify() throws SQLException {
//        // given
//        Seller sell = new Seller();
//        sell.setsName("Ram");
//        sell.setID((long) 1);
//        @SuppressWarnings("unused")
//		long  sid = sell.getId();
//        // when
//        @SuppressWarnings("unused")
//		int found = sRepository.insert(sell);
//        // Seller found = sRepository.findById(sell.getId());
//        //ResultSet seller = (ResultSet) jdbcTemplate.queryForObject("select * from seller where sid=?", new Object[] { sid }, new BeanPropertyRowMapper<Seller>(Seller.class));
//     
//        // then
//        assertThat(1)
//        .isEqualTo(found);
//    }
//    
// 
//    
//}
