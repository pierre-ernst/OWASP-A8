package org.owasp.ottawa.topten2017_A8;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Unused bean with <B>serious</B> side effect :-)
 * 
 * @author Pierre Ernst
 *
 */
public class UnusedBeanWithSideEffect implements Serializable {

	private static final long serialVersionUID = 3681490312670719194L;

	private String cmd = null;

	public UnusedBeanWithSideEffect() {
		/**
		 * Note: this constructor is <b>not</b> called during deserialization
		 */
	}

	
	/**
	 * The simple fact of declaring this private method is enough to trigger a
	 * side effect: it will be called during deserialization. Note: this method
	 * does not even @Override a method from <code>Serializable</code>
	 * 
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {

		// this will deserialize <code>cmd</code>
		in.defaultReadObject();

		Runtime.getRuntime().exec(this.cmd);
	}

	
	/**
	 * Will be called during serialization
	 * 
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();

		// nothing special to do here
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
}
