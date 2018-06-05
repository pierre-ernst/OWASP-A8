package org.owasp.ottawa.topten2017_A8.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.HexDump;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.owasp.ottawa.topten2017_A8.BicycleBean;

/**
 * 
 * @author Pierre Ernst
 *
 */
public class BicycleBeanTest {

	@Test
	public void serializationTest() {
		try {
			BicycleBean fatBike = new BicycleBean();
			fatBike.setName("Fat Bike");
			fatBike.setNumberOfRiders(1);
			fatBike.setNumberOfWheels(2);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(fatBike);
			byte[] serialized = baos.toByteArray();
			byte[] expectedHeader = new byte[] { (byte) 0xAC, (byte) 0xED, 0, 5 };
			assertTrue(Arrays.equals(expectedHeader, Arrays.copyOfRange(serialized, 0, 4)));

			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			HexDump.dump(serialized, 0, baos2, 0);
			InputStream generatedDump = new ByteArrayInputStream(baos2.toByteArray());
			InputStream expectedDump = new FileInputStream("target/test-classes/fat-bike.xxd.txt");
			String[] expectedLines = IOUtils.readLines(expectedDump, Charset.forName("UTF-8")).toArray(new String[0]);
			String[] generatedLines = IOUtils.readLines(generatedDump, Charset.forName("UTF-8")).toArray(new String[0]);
			assertTrue(Arrays.equals(expectedLines, generatedLines));

			String expectedB64 = FileUtils.readFileToString(new File("target/test-classes/fat-bike.b64.txt"),
					Charset.forName("UTF-8"));
			String generatedB64 = Base64.getEncoder().encodeToString(serialized);
			assertEquals(expectedB64, generatedB64);

		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			fail(ex.getMessage());
		}
	}

	@Test
	public void deserializationTest() {
		try {
			BicycleBean expected = new BicycleBean();
			expected.setName("Fat Bike");
			expected.setNumberOfRiders(1);
			expected.setNumberOfWheels(2);

			String savedB64 = FileUtils.readFileToString(new File("target/test-classes/fat-bike.b64.txt"),
					Charset.forName("UTF-8"));

			ByteArrayInputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(savedB64));
			ObjectInputStream ois = new ObjectInputStream(bais);
			BicycleBean deserialized = (BicycleBean) ois.readObject();

			assertEquals(expected, deserialized);

		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			fail(ex.getMessage());
		}
	}
}
