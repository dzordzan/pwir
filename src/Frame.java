import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class Frame extends JFrame {
	/**
	 * The text area which is used for displaying logging information.
	 */
	private JTextArea textArea;

	private JButton buttonStart = new JButton("Start");
	private JButton buttonClear = new JButton("Clear");
	private JLabel label = new JLabel("Liczba Studentów: ");
	private JTextField numberOfStudents = new JTextField(3);

	private PrintStream standardOut;

	public Frame() {
		super("STUDENTYYYY");

		textArea = new JTextArea(50, 10);
		textArea.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(
				textArea));

		// keeps reference of standard output stream
		standardOut = System.out;

		// re-assigns standard output stream and error output stream
		System.setOut(printStream);
		System.setErr(printStream);

		// creates the GUI
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.anchor = GridBagConstraints.WEST;

		add(buttonStart, constraints);

		constraints.gridx = 1;
		add(buttonClear, constraints);
		constraints.anchor = GridBagConstraints.PAGE_START;
		add(label, constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;

		add(new JScrollPane(textArea), constraints);

		// adds event handler for button Start
		buttonStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				printLog();
			}
		});

		// adds event handler for button Clear
		buttonClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// clears the text area
				try {
					textArea.getDocument().remove(0,
							textArea.getDocument().getLength());
					standardOut.println("Text area cleared");
				} catch (BadLocationException ex) {
					ex.printStackTrace();
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(480, 320);
		setLocationRelativeTo(null); // centers on screen
	}

	/**
	 * Prints log statements for testing in a thread
	 */
	private void printLog() {
		Student[] student = new Student[10];
		for (int i = 0; i < 10; i++) {
			
			student[i] = new Student(i);
			student[i].start();
		}

	}
}