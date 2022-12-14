
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Owner
 */
public class Login_Form extends javax.swing.JFrame {

	/**
	 * Creates new form Login_Form
	 */

	private String username;
	private String password;

	public Login_Form() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jTextField1 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jPasswordField1 = new javax.swing.JPasswordField();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(217, 217, 217));

		jPanel2.setBackground(new java.awt.Color(85, 128, 98));
		jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 330, Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 407, Short.MAX_VALUE));

		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jPasswordField1ActionPerformed(evt);
			}
		});

		jButton1.setBackground(new java.awt.Color(85, 128, 98));
		jButton1.setText("Login");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		JButton register = new JButton("Register");
		register.setBackground(new java.awt.Color(85, 128, 98));
		register.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				registerActionPerformed(evt);
			}
		});

		JLabel lblNewLabel = new JLabel("Username");

		JLabel lblNewLabel_1 = new JLabel("Password");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout
				.createSequentialGroup()
				.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
				.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
				.addGap(18)
				.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel1Layout
						.createSequentialGroup()
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
								.addComponent(jPasswordField1)
								.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jButton1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(register)))
						.addContainerGap())
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGap(60)))));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(106).addComponent(jLabel1).addGap(18)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(jButton1)
										.addComponent(register))
								.addContainerGap(174, Short.MAX_VALUE)));
		jPanel1.setLayout(jPanel1Layout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
	}// GEN-LAST:event_jTextField1ActionPerformed

	private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {
	}
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		username = jTextField1.getText();
		char[] pass = jPasswordField1.getPassword();
		password = new String(pass);
		boolean valid = SQLConnect.authenticateAccount(username, password);
		if (!(valid)) {
			System.err.println("Incorrect username/password!");
			dispose();
		}
		try {
			File login_auto = new File("login_username.txt");
			login_auto.createNewFile();
			FileWriter writer = new FileWriter("login_username.txt");
			writer.write(username);
			writer.close();
			dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void registerActionPerformed(java.awt.event.ActionEvent evt) {
	}
	// GEN-LAST:event_jButton1ActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Login_Form.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Login_Form.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Login_Form.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Login_Form.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Login_Form().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPasswordField jPasswordField1;
	private javax.swing.JTextField jTextField1;
}
