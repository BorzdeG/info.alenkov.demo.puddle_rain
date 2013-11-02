package info.alenkov.demo.puddle_rain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculateTest {
	private transient static final Logger LOG = LoggerFactory.getLogger(CalculateTest.class.getName());

	final Object[] data0  = {new Integer[]{2, 5, 1, 2, 3, 4, 7, 7, 6}, 10};
	final Object[] data1  = {new Integer[]{2, 5, 1, 3, 1, 2, 1, 7, 7, 6}, 17};
	final Object[] data2  = {new Integer[]{5, 1, 3, 6, 1, 6, 1, 3, 1, 4}, 18};
	final Object[] data3  = {new Integer[]{1, 2, 3, 4, 5, 5, 4, 3, 2, 1}, 0};
	final Object[] data4  = {new Integer[]{6, 1, 1, 1, 7, 1, 1, 1, 1, 7}, 39};
	final Object[] data5  = {new Integer[]{7, 1, 1, 1, 7, 1, 1, 1, 1, 7}, 42};
	final Object[] data6  = {new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, 0};
	final Object[] data7  = {new Integer[]{8, 7, 6, 5, 4, 3, 2, 1}, 0};
	final Object[] data8  = {new Integer[]{5, 1, 3, 6, 1, 5, 1, 7, 6, 5}, 17};
	final Object[] data9  = {new Integer[]{7, 1, 3, 6, 1, 5, 1, 7, 6, 5}, 25};
	final Object[] data10 = {new Integer[]{3, 4, 7, 3, 4, 7, 6, 7, 2, 4}, 10};
	final Object[] data11 = {new Integer[]{5, 1, 4, 2, 3}, 4};
	final Object[] data12 = {new Integer[]{6, 1, 5, 2, 1, 4}, 9};
	final Object[] data13 = {new Integer[]{4, 1, 2, 5, 1, 6}, 9};
	final Object[] data14 = {new Integer[]{7, 1, 5, 2, 1, 4}, 9};
	final Object[] data15 = {new Integer[]{7, 1, 5, 2, 1, 4, 2}, 9};
	final Object[] data16 = {new Integer[]{7, 1, 5, 2, 1, 4, 3, 2}, 9};
	final Object[] data17 = {new Integer[]{7, 1, 5, 2, 1, 4, 3, 2}, 9};
	final Object[] data18 = {new Integer[]{5, 1, 5, 1, 5, 1, 5}, 12};
	final Object[] data19 = {new Integer[]{1, 5, 1, 5, 1, 5, 1}, 8};
	final Object[] data20 = {new Integer[]{5, 1, 3}, 2};
	final Object[] data21 = {new Integer[]{0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 1}, 0};

	@DataProvider
	public Object[][] dataAll() {
		return new Object[][]{data0,
		                      data1,
		                      data2,
		                      data3,
		                      data4,
		                      data5,
		                      data6,
		                      data7,
		                      data8,
		                      data9,
		                      data10,
		                      data11,
		                      data12,
		                      data13,
		                      data14,
		                      data15,
		                      data16,
		                      data17,
		                      data18,
		                      data19,
		                      data20,
		                      data21};
	}

	@DataProvider
	public Object[][] dataOne() {
		return new Object[][]{data9};
	}

	@BeforeMethod
	public void setUp() throws Exception {
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "DEBUG");
		LOG.debug("---------");
	}

	@Test(dataProvider = "dataAll", dependsOnMethods = "testCalculateVolumeOne", enabled = true)
	public void testCalculateVolumeAll(Integer[] data, int volume) throws Exception {
		Assert.assertEquals(Calculate.calculateVolume(data), volume);
	}

	@Test(dataProvider = "dataOne")
	public void testCalculateVolumeOne(Integer[] data, int volume) throws Exception {
		Assert.assertEquals(Calculate.calculateVolume(data), volume);
	}
}
