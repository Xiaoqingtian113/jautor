package quark.jautor.gui;

import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ConsolePrintStream extends PrintStream {
	JTextArea console;
	StringBuffer strBuf = new StringBuffer();

	public ConsolePrintStream(OutputStream out, JTextArea console) {
		super(out);
		this.console = console;
	}

	public void write(byte[] buf, int off, int len) {
		final String msg = new String(buf, off, len);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				strBuf.append(msg);
				console.setText(strBuf.toString());
			}
		});
	}
}
