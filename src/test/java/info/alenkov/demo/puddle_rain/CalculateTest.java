package info.alenkov.demo.puddle_rain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculateTest {
	private transient static final Logger LOG = LoggerFactory.getLogger(CalculateTest.class.getName());

	@DataProvider
	public Object[][] data() {
		final Object[] data0 = {new Integer[]{2, 5, 1, 2, 3, 4, 7, 7, 6}, 10};
		final Object[] data1 = {new Integer[]{2, 5, 1, 3, 1, 2, 1, 7, 7, 6}, 17};
		final Object[] data2 = {new Integer[]{5, 1, 3, 6, 1, 6, 1, 3, 1, 4}, 18};
		final Object[] data3 = {new Integer[]{1, 2, 3, 4, 5, 5, 4, 3, 2, 1}, 0};
		final Object[] data4 = {new Integer[]{6, 1, 1, 1, 7, 1, 1, 1, 1, 7}, 39};
		final Object[] data5 = {new Integer[]{7, 1, 1, 1, 7, 1, 1, 1, 1, 7}, 42};
		final Object[] data6 = {new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, 0};
		final Object[] data7 = {new Integer[]{8, 7, 6, 5, 4, 3, 2, 1}, 0};
		final Object[] data8 = {new Integer[]{5, 1, 3, 6, 1, 5, 1, 7, 6, 5}, 17};
		final Object[] data9 = {new Integer[]{7, 1, 3, 6, 1, 5, 1, 7, 6, 5}, 25};
		final Object[] data10 = {new Integer[]{3, 4, 7, 3, 4, 7, 6, 7, 2, 4}, 10};


		return new Object[][]{data0, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10};
	}

	@BeforeMethod
	public void setUp() throws Exception {
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "DEBUG");
		LOG.error("------------------------");
	}

	@Test(dataProvider = "data")
	public void testCalculateVolume(Integer[] data, int volume) throws Exception {
		Assert.assertEquals(Calculate.calculateVolume(data), volume);
	}
}
