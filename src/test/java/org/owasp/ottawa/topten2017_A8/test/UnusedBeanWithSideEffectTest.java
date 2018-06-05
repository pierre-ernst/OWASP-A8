package org.owasp.ottawa.topten2017_A8.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.owasp.ottawa.topten2017_A8.UnusedBeanWithSideEffect;

/**
 * 
 * @author Pierre Ernst
 *
 */
public class UnusedBeanWithSideEffectTest {

	@Test
	public void calcLinuxTest() {
		try {
			String expectedB64 = FileUtils.readFileToString(new File("target/test-classes/calc-linux.b64.txt"),
					Charset.forName("UTF-8"));

			UnusedBeanWithSideEffect calcLinux = new UnusedBeanWithSideEffect();
			calcLinux.setCmd("/usr/bin/gnome-calculator");

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(calcLinux);
			String generatedB64 = Base64.getEncoder().encodeToString(baos.toByteArray());

			assertEquals(expectedB64, generatedB64);

		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			fail(ex.getMessage());
		}
	}

	@Test
	public void calcWinTest() {
		try {
			String expectedB64 = FileUtils.readFileToString(new File("target/test-classes/calc-win.b64.txt"),
					Charset.forName("UTF-8"));

			UnusedBeanWithSideEffect calcWin = new UnusedBeanWithSideEffect();
			calcWin.setCmd("calc.exe");

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(calcWin);
			String generatedB64 = Base64.getEncoder().encodeToString(baos.toByteArray());

			assertEquals(expectedB64, generatedB64);

		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			fail(ex.getMessage());
		}
	}
}
