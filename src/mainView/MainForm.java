package mainView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import translateTo3N.Get3NFormat;
import webCrawler.Crawler;
import webCrawlerforBD.HtmlUnitforBD;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import getContent.GetContent;

/**
 * @author Zhao Hang
 * @date:2015-3-13����1:06:14
 * @email:1610227688@qq.com
 */
public class MainForm {
	/**
	 * �Զ��岿�ֱ���
	 */
	Crawler crawler;
	private String deftitle = "�Ƽ���";
	private int defurlCount = 1000;
	private int defthreadCount = 5;// ��ʾ���ͬʱ�������ж��ٸ��߳�
	private double defthreshold = 0.91;
	private String defstartURL = "http://www.csdn.net/article/2015-03-25/2824310";
	// baidu���ز��ֵĳ�ʼ��������
	private int baidu_num = 10;
	private String baidu_word = "��";
	HtmlUnitforBD hu;
	private JFrame frame;

	private JTextField textField_threadNum;
	private JTextField textField_index;
	private JTextField textField_title;
	private JTextField textField_starturl;
	private JTextField textField_links;
	private static ArrayList<String> temp3N = new ArrayList<>();// �����ݴ��3N��ʽ
	private JTextField txtOwlBase;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
					// SwingUtilities.updateComponentTreeUI(window);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String newtitle = "http://www.csdn.net/article/2015-03-25/2824310";
		JLabel label_6 = new JLabel("\u5F53\u524D\u67E5\u627E\u6570");

		frame = new JFrame();
		int windowsWedth = 1200;
		int windowsHeight = 768;
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - windowsWedth) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - windowsHeight) / 2;
		System.out.println("����frame����\t���꣺(" + w + ":" + h + ")\t��С��" + windowsWedth + "��" + windowsHeight);

		frame.setTitle("\u57FA\u4E8E\u672C\u4F53\u8BED\u8A00\u7684\u673A\u5668\u5B66\u4E60\u81EA\u52A8\u6784\u5EFA\u7CFB\u7EDF\u7814\u7A76\u6A21\u62DF\u7CFB\u7EDF");
		frame.setBounds(w, h, windowsWedth, windowsHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menu_1 = new JMenu("\u6587\u4EF6");
		menuBar.add(menu_1);

		JMenuItem menuItem = new JMenuItem("\u6253\u5F00");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("open files");
			}
		});
		menu_1.add(menuItem);

		JMenu menu = new JMenu("\u4FE1\u606F\u68C0\u7D22");
		menuBar.add(menu);

		JPanel panel = new JPanel();

		JPanel panel_4 = new JPanel();

		JPanel panel_6 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 598, GroupLayout.PREFERRED_SIZE)
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
										.addGroup(
												groupLayout.createSequentialGroup()
														.addComponent(panel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))).addContainerGap()));

		JPanel panel_7 = new JPanel();

		JPanel panel_9 = new JPanel();

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.LIGHT_GRAY);

		JLabel lblNewLabel = new JLabel(
				"\u589E\u52A0\u672C\u4F53\uFF0C\u6269\u5145\u672C\u4F53\uFF0C\u4FEE\u6B63\u672C\u4F53\uFF0C\u5220\u9664\u672C\u4F53\u64CD\u4F5C\u7684\u81EA\u52A8\u5904\u7406");
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_panel_6
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_panel_6.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel_11, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
										.addComponent(panel_9, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
										.addComponent(panel_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
										.addComponent(lblNewLabel, Alignment.LEADING)).addContainerGap()));
		gl_panel_6.setVerticalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_6.createSequentialGroup().addContainerGap()
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_11, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)));

		JButton btnowltxt = new JButton("\u6253\u5F00\u65B0\u6587\u4EF6\uFF08.owl\u6216\u8005.txt\uFF09");

		final JTextPane textPane_6 = new JTextPane();
		final JLabel label_13 = new JLabel("\u4FE1\u606F\u53CD\u9988\uFF1A");
		btnowltxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textPane_6.setText(null);
				label_13.setText("��Ϣ������");
				FileDialog fd;
				Frame f = null;
				File file1 = null;
				fd = new FileDialog(f, "��ѡ��һ���ı��ļ�", FileDialog.LOAD);
				fd.setVisible(true); // ��������ʾ���ļ��Ի���
				file1 = new File(fd.getDirectory(), fd.getFile());
				if (file1.getAbsolutePath().endsWith(".txt")) {
					try {
						label_13.setText("��Ϣ���������ļ��ɹ���·����" + file1.getAbsolutePath());
						FileReader fr = new FileReader(file1);
						BufferedReader br = new BufferedReader(fr);
						String aline;
						SimpleAttributeSet set = new SimpleAttributeSet();
						while ((aline = br.readLine()) != null)
							textPane_6.getDocument().insertString(textPane_6.getDocument().getLength(), aline + "\n", set);
						fr.close();
						br.close();
					} catch (IOException | BadLocationException ioe) {

					}
				} else if (file1.getAbsolutePath().endsWith(".owl")) {
					label_13.setText("��Ϣ��������owl�ļ��ɹ�");
					textPane_6.setText("owl�ļ�������ϣ�����ִ����һ��������");
				}
			}
		});

		JPanel panel_12 = new JPanel();

		final JLabel label_14 = new JLabel("\u6267\u884C\u53CD\u9988\uFF1A");
		JButton btnNewButton_3 = new JButton("\u5F00\u59CB\u6267\u884C\u64CD\u4F5C");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String temp = "temp_updated";// ������D:/owlModel/�ļ����µ�temp��
				String base = txtOwlBase.getText();
				String info = textPane_6.getText();

				String newinfo = GetContent.getcontent(info, info);

				try {
					OWLModel owlmodel = ProtegeOWL.createJenaOWLModel();
					translateStructclassToOWL.TranslateStructclassToOWLMain.mainfunction(owlmodel, newinfo, base, temp);
					SimpleAttributeSet set = new SimpleAttributeSet();
					StyleConstants.setBackground(set, Color.YELLOW);
				} catch (OntologyLoadException | IOException e1) {
					e1.printStackTrace();
				}
				try {
					Desktop.getDesktop().open(new File("D:/owlmodel/temp_updated.owl"));
					label_14.setText("ִ�з�����ִ�н������Ѿ�������ļ�D:/owlmodel/temp_updated.owl");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		GroupLayout gl_panel_11 = new GroupLayout(panel_11);
		gl_panel_11.setHorizontalGroup(gl_panel_11
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel_11.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING).addComponent(btnowltxt).addComponent(label_13))
								.addContainerGap(341, Short.MAX_VALUE))
				.addComponent(panel_12, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
				.addGroup(gl_panel_11.createSequentialGroup().addContainerGap().addComponent(btnNewButton_3).addContainerGap(437, Short.MAX_VALUE))
				.addGroup(gl_panel_11.createSequentialGroup().addContainerGap().addComponent(label_14).addContainerGap(476, Short.MAX_VALUE)));
		gl_panel_11.setVerticalGroup(gl_panel_11.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_11.createSequentialGroup().addContainerGap().addComponent(btnowltxt).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(label_13).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton_3).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(label_14).addContainerGap(97, Short.MAX_VALUE)));

		JScrollPane scrollPane_6 = new JScrollPane();
		GroupLayout gl_panel_12 = new GroupLayout(panel_12);
		gl_panel_12.setHorizontalGroup(gl_panel_12.createParallelGroup(Alignment.LEADING).addComponent(scrollPane_6, Alignment.TRAILING,
				GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE));
		gl_panel_12.setVerticalGroup(gl_panel_12.createParallelGroup(Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				gl_panel_12.createSequentialGroup().addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(157, Short.MAX_VALUE)));

		scrollPane_6.setViewportView(textPane_6);
		panel_12.setLayout(gl_panel_12);
		panel_11.setLayout(gl_panel_11);

		JScrollPane scrollPane_5 = new JScrollPane();
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(gl_panel_9.createParallelGroup(Alignment.LEADING).addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 540,
				Short.MAX_VALUE));
		gl_panel_9.setVerticalGroup(gl_panel_9.createParallelGroup(Alignment.LEADING).addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 146,
				Short.MAX_VALUE));

		JPanel panel_10 = new JPanel();
		scrollPane_5.setColumnHeaderView(panel_10);

		JButton button_2 = new JButton("\u5F00\u59CB");
		final JTextPane textPane_1 = new JTextPane();
		final JTextPane textPane_2 = new JTextPane();
		final JTextPane textPane_3 = new JTextPane();
		final JTextPane textPane_4 = new JTextPane();
		final JTextPane textPane_5 = new JTextPane();
		txtOwlBase = new JTextField();
		txtOwlBase.setText("base");
		txtOwlBase.setColumns(10);
		final JRadioButton rdbtnrdf = new JRadioButton(
				"\u751F\u6210\u57FA\u7840RDF\uFF0Cowl\u7684\u57FA\u7840\u5F62\u5F0F\uFF0C\u4E3B\u8981\u7B2C\u4E09\u65B9jar\uFF1Acom.hp.hpl.jena");
		final JRadioButton rdbtnowl = new JRadioButton(
				"\u751F\u6210OWL\u672C\u4F53\u8BED\u8A00\u5F62\u5F0F\uFF0C\u4E3B\u8981\u4F7F\u7528\u7B2C\u4E09\u65B9jar\uFF1Aedu.stanford.smi.protege");

		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (rdbtnrdf.isSelected()) {
					OntModel ontmodel = ModelFactory.createOntologyModel();
					textPane_5.setText("�½�OntModelģ�ͽ���\n");
					String outFileuri = "D:/owlmodel/temp.rdf";
					String content = textPane_1.getText();
					String newcontent = "";
					GetContent.getcontent(content, newcontent);
					translateStructclassToRDF.TranslateStructclassToRDFMain.mainfunction(ontmodel, newcontent, outFileuri);
					translateStructclassToRDF.TranslateStructclassToRDFMain.write(ontmodel, outFileuri);
					FileOutputStream outFile;
					try {
						outFile = new FileOutputStream(outFileuri);
						Writer out = new OutputStreamWriter(outFile);
						ontmodel.write(out);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					FileReader fr;
					try {
						fr = new FileReader(outFileuri);
						BufferedReader br = new BufferedReader(fr);
						String aline = "";
						SimpleAttributeSet set = new SimpleAttributeSet();

						while ((aline = br.readLine()) != null) {
							textPane_5.getDocument().insertString(textPane_5.getDocument().getLength(), aline + "\n", set);
						}
						br.close();
					} catch (IOException | BadLocationException e1) {
						e1.printStackTrace();
					}
				}
				if (rdbtnowl.isSelected()) {
					textPane_5.setText("��ʼ����owlģ�͡�����");
					String temp = "temp";// ������D:/owlModel/�ļ����µ�temp��
					String base = txtOwlBase.getText();
					String info = textPane_1.getText();
					info = GetContent.getcontent(info, info);
					System.out.println(info);
					try {
						OWLModel owlmodel = ProtegeOWL.createJenaOWLModel();
						translateStructclassToOWL.TranslateStructclassToOWLMain.mainfunction(owlmodel, info, base, temp);
						SimpleAttributeSet set = new SimpleAttributeSet();
						StyleConstants.setBackground(set, Color.YELLOW);
						textPane_5.getDocument().insertString(
								textPane_5.getDocument().getLength(),
								"\n����java protege��APIֱ�ӽ���OWL�ļ���java��Ŀ�еĹ�����utf-8��ʽ������Unicode�����ʽ���벻���ݣ��������̽��鷳����Ϊ�ô�ֻ��չʾ���̣��ʴ˴�ʹ�ÿɽ���owl��������򿪲���ʾ�ļ���"
										+ "\n������ļ�D:/owlmodel/temp.owl", set);
					} catch (OntologyLoadException | IOException | BadLocationException e1) {
						e1.printStackTrace();
					}

					/*
					 * ���ڽ���OWL���ı���ʽ�������⣬ֻ���������ʽ�򿪡�
					 */
					// String charsetName = "Unicode";
					// File f = new File("D:/owlmodel/temp.owl.txt");
					// try {
					// InputStreamReader insReader = new InputStreamReader(new
					// FileInputStream(f), charsetName);
					// BufferedReader bufReader = new BufferedReader(insReader);
					// String aline = "";
					// SimpleAttributeSet set = new SimpleAttributeSet();
					//
					// while ((aline = bufReader.readLine()) != null) {
					// System.out.println(aline);
					// textPane_5.getDocument().insertString(textPane_5.getDocument().getLength(),
					// aline + "\n", set);
					// }
					// bufReader.close();
					// } catch (IOException | BadLocationException e2) {
					// //
					// e2.printStackTrace();
					// }
				}
			}
		});

		rdbtnrdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (rdbtnowl.isSelected())
					rdbtnowl.setSelected(false);
			}
		});
		rdbtnrdf.setSelected(true);
		rdbtnowl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (rdbtnrdf.isSelected())
					rdbtnrdf.setSelected(false);
			}
		});

		JLabel lblOel = new JLabel("owl\u5F62\u5F0F\u8BF7\u8F93\u5165\u672C\u4F53\u7684\u6839\u540D\u79F0");

		JSeparator separator = new JSeparator();

		JButton btnNewButton_2 = new JButton("\u6267\u884C\u5B8C\u751F\u6210OWL\u683C\u5F0F\u70B9\u6B64\u6253\u5F00");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					Desktop.getDesktop().open(new File("D:/owlmodel/temp.owl"));
				} catch (IOException e1) {
					//
					e1.printStackTrace();
				}
			}
		});

		JButton btnowl = new JButton("\u70B9\u6B64\u6E05\u7406\u6B64owl\u6587\u6863");
		btnowl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				File file = new File("D:/owlmodel/temp.owl");
				file.delete();
				textPane_5.setText("�ĵ������Ѿ������");
			}
		});

		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(gl_panel_10.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_10
						.createSequentialGroup()
						.addGroup(
								gl_panel_10
										.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnrdf)
										.addGroup(
												gl_panel_10
														.createSequentialGroup()
														.addGap(21)
														.addComponent(button_2)
														.addGroup(
																gl_panel_10
																		.createParallelGroup(Alignment.LEADING)
																		.addGroup(
																				gl_panel_10.createSequentialGroup().addGap(103)
																						.addComponent(txtOwlBase, 233, 233, 233))
																		.addGroup(
																				gl_panel_10.createSequentialGroup().addGap(18)
																						.addComponent(btnNewButton_2)
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addComponent(btnowl))))
										.addGroup(
												gl_panel_10.createSequentialGroup().addContainerGap()
														.addComponent(separator, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE))
										.addComponent(rdbtnowl).addGroup(gl_panel_10.createSequentialGroup().addGap(21).addComponent(lblOel)))
						.addContainerGap(851, Short.MAX_VALUE)));
		gl_panel_10.setVerticalGroup(gl_panel_10.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_10
						.createSequentialGroup()
						.addComponent(rdbtnrdf)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(rdbtnowl)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panel_10.createParallelGroup(Alignment.BASELINE).addComponent(lblOel)
										.addComponent(txtOwlBase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(1)
						.addGroup(
								gl_panel_10.createParallelGroup(Alignment.BASELINE).addComponent(button_2).addComponent(btnNewButton_2)
										.addComponent(btnowl)).addContainerGap()));
		panel_10.setLayout(gl_panel_10);

		scrollPane_5.setViewportView(textPane_5);
		panel_9.setLayout(gl_panel_9);

		JScrollPane scrollPane_4 = new JScrollPane();
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING).addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 540,
				Short.MAX_VALUE));
		gl_panel_7.setVerticalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING).addComponent(scrollPane_4, Alignment.TRAILING,
				GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE));

		scrollPane_4.setViewportView(textPane_4);

		JPanel panel_8 = new JPanel();
		scrollPane_4.setColumnHeaderView(panel_8);

		JLabel label_12 = new JLabel("\u5F15\u5165\u7C7B-\u5C5E\u6027\u7ED3\u6784");

		final JRadioButton radioButton_2 = new JRadioButton("\u5F15\u5165\u4E0A\u6B65\u6267\u884C\u6240\u5F97");
		radioButton_2.setSelected(true);
		final JRadioButton radioButton_3 = new JRadioButton("\u6253\u5F00\u4E09\u5143\u5F0F\u6587\u4EF6");
		radioButton_3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (radioButton_2.isSelected())
					radioButton_2.setSelected(false);
			}
		});
		radioButton_2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (radioButton_3.isSelected())
					radioButton_3.setSelected(false);
			}
		});

		JButton button_1 = new JButton("\u6267\u884C\u64CD\u4F5C");
		button_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (radioButton_2.isSelected()) {
					textPane_4.setText("^  .  ^~       ���������� ���� ��ϵ�б� �����Ժ� ������\n");
					String temp = textPane_2.getText();// ����ԭʼ�ı�
					ArrayList<String> al = new ArrayList<String>();// ����־�
					al = hanLP.HanLPMain.separateString(temp);// �־丳ֵ��al
					String[] eachstring = temp.split("\r\n");
					int alsize = al.size();
					int num = 0;
					while (num < alsize) {
						String newtemp = hanLP.HanLPMain.getObject(hanLP.HanLPMain.getAdvancedTermOfthisSentencs(hanLP.HanLPMain
								.getBaseTermOfthisSentence(eachstring[num])));
						System.out.println("newtemp is" + newtemp);
						SimpleAttributeSet set = new SimpleAttributeSet();
						try {
							textPane_4.getDocument().insertString(textPane_4.getDocument().getLength(), newtemp + "\n", set);
						} catch (BadLocationException e1) {
							//
							e1.printStackTrace();
						}
						num++;
					}
				}
			}
		});
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(gl_panel_8.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_8.createSequentialGroup().addComponent(label_12).addPreferredGap(ComponentPlacement.RELATED).addComponent(radioButton_2)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(radioButton_3).addGap(48).addComponent(button_1)
						.addContainerGap(119, Short.MAX_VALUE)));
		gl_panel_8.setVerticalGroup(gl_panel_8.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_8
						.createSequentialGroup()
						.addGroup(
								gl_panel_8.createParallelGroup(Alignment.BASELINE).addComponent(label_12).addComponent(radioButton_2)
										.addComponent(radioButton_3).addComponent(button_1))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_8.setLayout(gl_panel_8);
		panel_7.setLayout(gl_panel_7);
		panel_6.setLayout(gl_panel_6);

		JScrollPane scrollPane = new JScrollPane();

		final JButton crawlerbt = new JButton("��ʼ");
		JLabel lblNewLabel_2 = new JLabel("���罨ģ��ȡ���̷���");
		scrollPane.setColumnHeaderView(lblNewLabel_2);

		final JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		// System.out.println(newtitle);
		crawler = new Crawler("�Ƽ���", newtitle, textPane, label_6, crawlerbt);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { scrollPane }));

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel label_10 = new JLabel("\u68C0\u7D22\u4E2D\u6587\u4FE1\u606F");
		scrollPane_1.setColumnHeaderView(label_10);

		scrollPane_1.setViewportView(textPane_1);

		JLabel lblNewLabel_1 = new JLabel("ϵͳ���з���");

		JButton btnNewButton = new JButton("\u5BF9\u4EE5\u4E0A\u4E2D\u6587\u4FE1\u606F\u751F\u6210\u65AD\u8A00");
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String tp1text = textPane_1.getText();

				Pattern pattern = Pattern.compile("[\u4E00-\uFA29]+");
				Matcher matcher = pattern.matcher(tp1text);
				String temp = "";
				while (matcher.find()) {
					System.out.println("�����" + matcher.group());
					temp += matcher.group() + ".\r\n";
				}
				textPane_2.setText(temp);
			}
		});

		JLabel label_3 = new JLabel("\u6216\u8005\u4F60\u53EF\u4EE5");

		JButton openduanyan = new JButton("\u6253\u5F00\u65AD\u8A00\u6587\u4EF6\uFF08.txt\u683C\u5F0F\uFF09");
		openduanyan.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				FileDialog fd;
				Frame f = null;
				File file1 = null;
				fd = new FileDialog(f, "��ѡ��һ���ı��ļ�", FileDialog.LOAD);
				fd.setVisible(true); // ��������ʾ���ļ��Ի���
				try {
					textPane_2.setText("");
					file1 = new File(fd.getDirectory(), fd.getFile());
					FileReader fr = new FileReader(file1);
					BufferedReader br = new BufferedReader(fr);
					String aline;
					SimpleAttributeSet set = new SimpleAttributeSet();
					while ((aline = br.readLine()) != null)
						textPane_2.getDocument().insertString(textPane_2.getDocument().getLength(), aline + "\n", set);
					fr.close();
					br.close();
				} catch (IOException | BadLocationException ioe) {

				}

			}
		});

		JScrollPane scrollPane_2 = new JScrollPane();

		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_panel_4
						.createSequentialGroup()
						.addGroup(
								gl_panel_4
										.createParallelGroup(Alignment.TRAILING)
										.addComponent(scrollPane_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
										.addComponent(scrollPane_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
										.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
										.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1, Alignment.LEADING)
										.addGroup(
												Alignment.LEADING,
												gl_panel_4.createSequentialGroup().addComponent(btnNewButton)
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(label_3)
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(openduanyan))).addContainerGap()));
		gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_4
						.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNewLabel_1)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panel_4.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton).addComponent(label_3)
										.addComponent(openduanyan)).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)));

		scrollPane_3.setViewportView(textPane_3);

		JPanel panel_5 = new JPanel();
		scrollPane_3.setColumnHeaderView(panel_5);

		JLabel lblnshengchengfangshi = new JLabel("\u5206\u8BCD\u751F\u6210\u7ED3\u6784\u4F53");

		final JRadioButton radioButton = new JRadioButton("\u6A21\u677F\u5339\u914D");
		final JRadioButton rdbtnHanlp = new JRadioButton("HanLP\u5206\u8BCD\u5DE5\u5177\u5224\u65AD\uFF08\u9ED8\u8BA4\uFF09");
		rdbtnHanlp.setSelected(true);

		radioButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (rdbtnHanlp.isSelected()) {
					rdbtnHanlp.setSelected(false);
				}
			}
		});

		rdbtnHanlp.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (radioButton.isSelected()) {
					radioButton.setSelected(false);
				}
			}
		});

		final JLabel label_11 = new JLabel("\u5206\u8BCD\u6267\u884C\u8FC7\u7A0B\uFF1A");

		JButton button = new JButton("\u5F00\u59CB\u6267\u884C\u751F\u6210");
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (radioButton.isSelected()) {
					textPane_3.setText("^  .  ^~       ���ڼ���ģ�壬���Ե� ������\n");
					String tp2text = textPane_2.getText();
					System.out.println("!!!" + tp2text);
					String[] a = tp2text.split("\r\n");
					System.out.println(a.length);
					int num = 0;
					while (num < a.length) {
						System.out.println((num + 1) + ":" + a[num] + "---" + Get3NFormat.contentFromText(a[num]));
						String aline = Get3NFormat.contentFromText(a[num]);
						SimpleAttributeSet set = new SimpleAttributeSet();
						try {
							if (aline != null) {
								textPane_3.getDocument().insertString(textPane_3.getDocument().getLength(), aline, set);
								temp3N.add(aline);
							}
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
						num++;
					}

					System.out.println(temp3N);
					label_11.setText("�ִʽ������Ѿ�����3-N�ṹʽ��");
					label_11.setBackground(Color.gray);
				}
				if (rdbtnHanlp.isSelected()) {
					textPane_3.setText("^  .  ^~       ���ڼ���HanLP���ߣ����Ե� ������\n");
					String temp = textPane_2.getText();// ����ԭʼ�ı�
					ArrayList<String> al = new ArrayList<String>();// ����־�
					al = hanLP.HanLPMain.separateString(temp);// �־丳ֵ��al
					String[] eachstring = temp.split("\r\n");
					int alsize = al.size();
					int num = 0;
					while (num < alsize) {
						String newtemp = hanLP.HanLPMain.getAdvancedTermOfthisSentencs(hanLP.HanLPMain.getBaseTermOfthisSentence(eachstring[num]))
								.toString();
						SimpleAttributeSet set = new SimpleAttributeSet();
						try {
							textPane_3.getDocument().insertString(textPane_3.getDocument().getLength(), newtemp + "\n", set);
						} catch (BadLocationException e1) {
							//
							e1.printStackTrace();
						}
						num++;
					}
				}
			}
		});
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel_5
								.createSequentialGroup()
								.addGap(4)
								.addGroup(
										gl_panel_5
												.createParallelGroup(Alignment.LEADING)
												.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 582, GroupLayout.PREFERRED_SIZE)
												.addGroup(
														gl_panel_5
																.createSequentialGroup()
																.addComponent(lblnshengchengfangshi, GroupLayout.PREFERRED_SIZE, 115,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(
																		gl_panel_5
																				.createParallelGroup(Alignment.LEADING)
																				.addGroup(
																						gl_panel_5.createSequentialGroup().addComponent(radioButton)
																								.addGap(130).addComponent(button))
																				.addComponent(rdbtnHanlp)))).addContainerGap(172, Short.MAX_VALUE)));
		gl_panel_5.setVerticalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_5
						.createSequentialGroup()
						.addGroup(
								gl_panel_5.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblnshengchengfangshi, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE).addComponent(rdbtnHanlp))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE).addComponent(button).addComponent(radioButton)).addGap(1)
						.addComponent(label_11).addContainerGap()));
		panel_5.setLayout(gl_panel_5);

		scrollPane_2.setViewportView(textPane_2);

		JLabel label_7 = new JLabel("\u65AD\u8A00\u5982\u4E0B");
		scrollPane_2.setColumnHeaderView(label_7);
		panel_4.setLayout(gl_panel_4);
		panel.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);

		final JPanel textField_keyword = new JPanel();
		tabbedPane.addTab("�������潨ģ", null, textField_keyword, null);
		GridBagLayout gbl_textField_keyword = new GridBagLayout();
		gbl_textField_keyword.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_textField_keyword.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_textField_keyword.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_textField_keyword.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		textField_keyword.setLayout(gbl_textField_keyword);

		JLabel lblNewLabel_4 = new JLabel("����");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		textField_keyword.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textField_title = new JTextField();
		// textField_title.setText(crawler.getStartURL());
		GridBagConstraints gbc_textField_title = new GridBagConstraints();
		gbc_textField_title.gridwidth = 8;
		gbc_textField_title.insets = new Insets(0, 0, 5, 0);
		gbc_textField_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_title.gridx = 1;
		gbc_textField_title.gridy = 0;
		textField_keyword.add(textField_title, gbc_textField_title);
		textField_title.setColumns(10);

		JLabel lblUrl = new JLabel("��ʼURL");
		GridBagConstraints gbc_lblUrl = new GridBagConstraints();
		gbc_lblUrl.anchor = GridBagConstraints.EAST;
		gbc_lblUrl.insets = new Insets(0, 0, 5, 5);
		gbc_lblUrl.gridx = 0;
		gbc_lblUrl.gridy = 1;
		textField_keyword.add(lblUrl, gbc_lblUrl);

		textField_starturl = new JTextField();
		GridBagConstraints gbc_textField_starturl = new GridBagConstraints();
		gbc_textField_starturl.gridwidth = 8;
		gbc_textField_starturl.insets = new Insets(0, 0, 5, 0);
		gbc_textField_starturl.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_starturl.gridx = 1;
		gbc_textField_starturl.gridy = 1;
		textField_keyword.add(textField_starturl, gbc_textField_starturl);
		textField_starturl.setColumns(10);

		JLabel label = new JLabel("\u5173\u952E\u8BCD");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		textField_keyword.add(label, gbc_label);

		final JTextArea textArea_keywords = new JTextArea();
		textArea_keywords.setColumns(10);
		GridBagConstraints gbc_textArea_keywords = new GridBagConstraints();
		gbc_textArea_keywords.gridwidth = 8;
		gbc_textArea_keywords.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_keywords.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea_keywords.gridx = 1;
		gbc_textArea_keywords.gridy = 2;
		textField_keyword.add(textArea_keywords, gbc_textArea_keywords);

		JLabel lblLinks = new JLabel("��������");
		GridBagConstraints gbc_lblLinks = new GridBagConstraints();
		gbc_lblLinks.anchor = GridBagConstraints.EAST;
		gbc_lblLinks.insets = new Insets(0, 0, 5, 5);
		gbc_lblLinks.gridx = 0;
		gbc_lblLinks.gridy = 3;
		textField_keyword.add(lblLinks, gbc_lblLinks);

		textField_links = new JTextField();
		GridBagConstraints gbc_textField_links = new GridBagConstraints();
		gbc_textField_links.insets = new Insets(0, 0, 5, 5);
		gbc_textField_links.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_links.gridx = 1;
		gbc_textField_links.gridy = 3;
		textField_keyword.add(textField_links, gbc_textField_links);
		textField_links.setColumns(10);

		JLabel label_1 = new JLabel("�߳���");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.VERTICAL;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 3;
		textField_keyword.add(label_1, gbc_label_1);

		textField_threadNum = new JTextField();
		GridBagConstraints gbc_textField_threadNum = new GridBagConstraints();
		gbc_textField_threadNum.insets = new Insets(0, 0, 5, 5);
		gbc_textField_threadNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_threadNum.gridx = 3;
		gbc_textField_threadNum.gridy = 3;
		textField_keyword.add(textField_threadNum, gbc_textField_threadNum);
		textField_threadNum.setColumns(10);

		JLabel label_5 = new JLabel("\u9600\u503C");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.VERTICAL;
		gbc_label_5.insets = new Insets(0, 0, 0, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 4;
		textField_keyword.add(label_5, gbc_label_5);

		textField_index = new JTextField();
		GridBagConstraints gbc_textField_index = new GridBagConstraints();
		gbc_textField_index.insets = new Insets(0, 0, 0, 5);
		gbc_textField_index.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_index.gridx = 1;
		gbc_textField_index.gridy = 4;
		textField_keyword.add(textField_index, gbc_textField_index);
		textField_index.setColumns(10);

		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.insets = new Insets(0, 0, 0, 5);
		gbc_label_6.gridx = 2;
		gbc_label_6.gridy = 4;
		textField_keyword.add(label_6, gbc_label_6);

		final JButton crawlerstopbt = new JButton("ֹͣ");
		crawlerstopbt.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				crawler.stopSearch();
				crawlerstopbt.setEnabled(true);
			}
		});

		crawlerbt.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// crawlerbt.setText("��ʼ");
				textPane.setText("*******************start*******************\n");
				crawler.initialize();
				crawler.parallelhandle();
				// crawlerbt.setEnabled(false);
			}
		});

		JButton button_setbt = new JButton("Ӧ��");
		button_setbt.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				crawler.setTitle(textField_title.getText());
				crawler.setStartURL(textField_starturl.getText());
				try {
					int urlcount = Integer.parseInt(textField_links.getText());
					crawler.setUrlCount(urlcount);
				} catch (NumberFormatException ee) {
					JOptionPane.showMessageDialog(null, "Format Error of Url Count!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				try {
					int threadCount = Integer.parseInt(textField_threadNum.getText());
					crawler.setThreadCount(threadCount);
				} catch (NumberFormatException ee) {
					JOptionPane.showMessageDialog(null, "Format Error of thread Count!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				try {
					double threshold = Double.parseDouble(textField_index.getText());
					crawler.setThreshold(threshold);
				} catch (NumberFormatException ee) {
					JOptionPane.showMessageDialog(null, "Format Error of threshold!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				crawler.removeAllKeyWords();
				String[] keywords = textArea_keywords.getText().split("\\|");
				for (int i = 0; i < keywords.length; i++) {
					crawler.addKeyWord(keywords[i], 0);
				}
				JOptionPane.showMessageDialog(null, "Succeed to set the configuration!", "Set", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		GridBagConstraints gbc_button_setbt = new GridBagConstraints();
		gbc_button_setbt.insets = new Insets(0, 0, 0, 5);
		gbc_button_setbt.gridx = 4;
		gbc_button_setbt.gridy = 4;
		textField_keyword.add(button_setbt, gbc_button_setbt);

		crawler.setStartURL(newtitle);
		crawler.setTitle(deftitle);
		crawler.setThreadCount(defthreadCount);
		crawler.setThreshold(defthreshold);
		crawler.setUrlCount(defurlCount);

		textField_title.setText(deftitle);
		textField_starturl.setText(defstartURL);
		textField_links.setText("" + defurlCount);
		textField_threadNum.setText("" + defthreadCount);
		textField_index.setText("" + defthreshold);
		crawler.addKeyWord("�Ƽ���", 0);
		crawler.addKeyWord("��������", 0);
		crawler.addKeyWord("ƽ̨", 0);
		crawler.addKeyWord("�ܹ�", 0);
		crawler.addKeyWord("���ݿ�", 0);
		crawler.addKeyWord("��ȫ", 0);
		crawler.addKeyWord("Hadoop", 0);
		crawler.addKeyWord("�洢", 0);
		crawler.addKeyWord("���⻯", 0);
		crawler.addKeyWord("��˽", 0);
		crawler.addKeyWord("�ڿ�", 0);
		crawler.addKeyWord("�ֲ�ʽ", 0);
		crawler.addKeyWord("MapReduce", 0);
		crawler.addKeyWord("cloud", 0);
		Enumeration<String> keywords = crawler.getKeyWords();
		while (keywords.hasMoreElements()) {
			(textArea_keywords).append(keywords.nextElement() + "|");
		}

		GridBagConstraints gbc_crawlerbt = new GridBagConstraints();
		gbc_crawlerbt.insets = new Insets(0, 0, 0, 5);
		gbc_crawlerbt.gridwidth = 2;
		gbc_crawlerbt.gridx = 5;
		gbc_crawlerbt.gridy = 4;
		textField_keyword.add(crawlerbt, gbc_crawlerbt);
		GridBagConstraints gbc_crawlerstopbt = new GridBagConstraints();
		gbc_crawlerstopbt.gridwidth = 2;
		gbc_crawlerstopbt.gridx = 7;
		gbc_crawlerstopbt.gridy = 4;
		textField_keyword.add(crawlerstopbt, gbc_crawlerstopbt);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("�ٶ���������", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel label_8 = new JLabel("\u5173\u952E\u8BCD");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 0;
		panel_1.add(label_8, gbc_label_8);

		final JTextArea bd_keyword = new JTextArea();
		GridBagConstraints gbc_bd_keyword = new GridBagConstraints();
		gbc_bd_keyword.anchor = GridBagConstraints.BASELINE;
		gbc_bd_keyword.gridwidth = 10;
		gbc_bd_keyword.insets = new Insets(0, 0, 5, 0);
		gbc_bd_keyword.fill = GridBagConstraints.HORIZONTAL;
		gbc_bd_keyword.gridx = 1;
		gbc_bd_keyword.gridy = 0;
		panel_1.add(bd_keyword, gbc_bd_keyword);

		JLabel label_9 = new JLabel("\u641C\u7D22\u6761\u6570");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 0;
		gbc_label_9.gridy = 1;
		panel_1.add(label_9, gbc_label_9);

		final JTextArea bd_num = new JTextArea();
		GridBagConstraints gbc_bd_num = new GridBagConstraints();
		gbc_bd_num.anchor = GridBagConstraints.BASELINE;
		gbc_bd_num.insets = new Insets(0, 0, 5, 5);
		gbc_bd_num.fill = GridBagConstraints.HORIZONTAL;
		gbc_bd_num.gridx = 1;
		gbc_bd_num.gridy = 1;
		panel_1.add(bd_num, gbc_bd_num);
		bd_keyword.setText(baidu_word + "");
		bd_num.setText(baidu_num + "");

		JButton bdsearch_startbt = new JButton("��ʼ");
		bdsearch_startbt.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String word = bd_keyword.getText();
				int num = Integer.parseInt(bd_num.getText());
				hu = new HtmlUnitforBD();
				try {
					textPane.setText("start!\n");
					HtmlUnitforBD.mainFunction(num, word, textPane, textPane_1);

					// SimpleAttributeSet set = new SimpleAttributeSet();
					// StyleConstants.setUnderline(set, true);
					// hu.insertWeb(textPane);
					// textPane.getDocument().insertString(textPane.getDocument().getLength(),
					// "\n", set);
				} catch (FailingHttpStatusCodeException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_bdsearch_startbt = new GridBagConstraints();
		gbc_bdsearch_startbt.insets = new Insets(0, 0, 0, 5);
		gbc_bdsearch_startbt.gridx = 9;
		gbc_bdsearch_startbt.gridy = 3;
		panel_1.add(bdsearch_startbt, gbc_bdsearch_startbt);

		JButton bdsearch_stopbt = new JButton("ֹͣ");
		bdsearch_stopbt.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});
		GridBagConstraints gbc_bdsearch_stopbt = new GridBagConstraints();
		gbc_bdsearch_stopbt.gridx = 10;
		gbc_bdsearch_stopbt.gridy = 3;
		panel_1.add(bdsearch_stopbt, gbc_bdsearch_stopbt);

		Panel panel_2 = new Panel();
		tabbedPane.addTab("���ñ����ļ���ģ", null, panel_2, null);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblNewLabel_3 = new JLabel("���ñ��ص��ı�ʵ��֪ʶ����Ĺ���");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridwidth = 3;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);
		JButton btnNewButton_1 = new JButton("�򿪱����ļ�");

		final JLabel label_fname = new JLabel("null");
		GridBagConstraints gbc_label_fname = new GridBagConstraints();
		gbc_label_fname.anchor = GridBagConstraints.WEST;
		gbc_label_fname.insets = new Insets(0, 0, 0, 5);
		gbc_label_fname.gridx = 1;
		gbc_label_fname.gridy = 3;
		panel_2.add(label_fname, gbc_label_fname);

		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SimpleAttributeSet set = new SimpleAttributeSet();
				StyleConstants.setBackground(set, Color.YELLOW);
				try {
					textPane.getDocument().insertString(textPane.getDocument().getLength(), "�����罨ģ���˴�����Ҫ��ʾ\n", set);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
				FileDialog fd;
				Frame f = null;
				File file1 = null;
				fd = new FileDialog(f, "��ѡ��һ���ı��ļ�", FileDialog.LOAD);
				fd.setVisible(true); // ��������ʾ���ļ��Ի���
				try {
					textPane_1.setText("");
					file1 = new File(fd.getDirectory(), fd.getFile());
					FileReader fr = new FileReader(file1);
					BufferedReader br = new BufferedReader(fr);
					label_fname.setText(file1.getAbsolutePath());
					String aline;
					SimpleAttributeSet set2 = new SimpleAttributeSet();
					// StyleConstants.setUnderline(set, true);
					while ((aline = br.readLine()) != null)
						// ���ж�ȡ�ı�
						textPane_1.getDocument().insertString(textPane_1.getDocument().getLength(), aline + "\n", set2);
					fr.close();
					br.close();
				} catch (IOException | BadLocationException ioe) {

				}
			}
		});
		JLabel lbltxt = new JLabel("\u8BF7\u9009\u62E9\u4E00\u4E2A\u6587\u672C\uFF08txt\uFF09\u6765\u4F5C\u4E3A\u5EFA\u6A21\u6587\u672C\u3002");
		GridBagConstraints gbc_lbltxt = new GridBagConstraints();
		gbc_lbltxt.gridwidth = 4;
		gbc_lbltxt.anchor = GridBagConstraints.WEST;
		gbc_lbltxt.insets = new Insets(0, 0, 5, 5);
		gbc_lbltxt.gridx = 0;
		gbc_lbltxt.gridy = 1;
		panel_2.add(lbltxt, gbc_lbltxt);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridwidth = 5;
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;
		panel_2.add(btnNewButton_1, gbc_btnNewButton_1);

		JLabel label_2 = new JLabel("\u6587\u4EF6\u540D\uFF1A");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 0, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 3;
		panel_2.add(label_2, gbc_label_2);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("��������OWL�ļ���ģ", null, panel_3, null);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		JLabel label_4 = new JLabel("\u6587\u4EF6");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 0;
		panel_3.add(label_4, gbc_label_4);
		frame.getContentPane().setLayout(groupLayout);
	}
}