package com.cognizant.dailymutualfundnav;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.cognizant.dailymutualfundnav.controller.MutualFundController;
import com.cognizant.dailymutualfundnav.exception.MutualFundNotFoundException;
import com.cognizant.dailymutualfundnav.model.MutualFundDetails;
import com.cognizant.dailymutualfundnav.service.MutualFundService;

/**
 * @author Saikiran
 *
 */
@AutoConfigureMockMvc
@SpringBootTest
public class DailymutualfundnavApplicationTests {

	@Autowired
	private MutualFundController mutualFundController;

	@Autowired
	private MutualFundService mutualFundService;

	@Test
	void contextLoads() {
		assertNotNull(mutualFundController);
	}
	
	@Test
	public void testGetMutualFundById() {
		MutualFundDetails mfdl = new MutualFundDetails();
		try {
			mfdl = (mutualFundService).getMutualFundById("AXIS");
		} catch (MutualFundNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MutualFundDetails mfdtl = new MutualFundDetails("AXIS", "AxisMutualFund", 6000.0);
		assertEquals(mfdl.toString(), mfdtl.toString());
	}
	
	@Test
	public void testGetMutualFundByName() {
		MutualFundDetails mfdl = new MutualFundDetails();
		try {
			mfdl = (mutualFundService).getMutualFundByName("AxisMutualFund");
		} catch (MutualFundNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MutualFundDetails mfdtl = new MutualFundDetails("AXIS", "AxisMutualFund", 6000.0);
		assertEquals(mfdl.toString(), mfdtl.toString());
	}

	@Test
	public void testSetMutualFundId() {
		MutualFundDetails mfd = new MutualFundDetails();
		mfd.setMutualFundId("G");
		assertTrue(mfd.getMutualFundId() == "G");
	}

	@Test
	public void testSetMutualFundName() {
		MutualFundDetails mfd = new MutualFundDetails();
		mfd.setMutualFundName("DLF");
		assertTrue(mfd.getMutualFundName() == "DLF");
	}

	@Test
	public void testSetMutualFund() {
		MutualFundDetails mfd = new MutualFundDetails("AXIS", "AxisMutualFund", 6000.0);
		assertTrue(mfd.getMutualFundId() == "AXIS");
	}

	@Test
	public void testSetMutualFundtoString() {
		MutualFundDetails mfd = new MutualFundDetails("AX", "AxisMutualFund", 6000.0);
		assertFalse(mfd.toString() == "G");
	}

	@Test
	public void testFindOne() throws Exception {
		MutualFundDetails item = mutualFundService.getMutualFundByName("AxisMutualFund");
		boolean value = item.getMutualFundValue() == 6000.0 ? true : false;
		assertTrue(value);
	}

	@Test
	public void testFindTwo() throws Exception {
		MutualFundDetails item = mutualFundService.getMutualFundById("AXIS");
		boolean value = item.getMutualFundValue() == 6000.0 ? true : false;
		assertTrue(value);
	}

	@Test
	public void testFindThree() throws Exception {
		MutualFundDetails item = mutualFundService.getMutualFundById("DSP");
		boolean value = item.getMutualFundValue() == 4300.0 ? true : false;
		assertTrue(value);
	}
	
}