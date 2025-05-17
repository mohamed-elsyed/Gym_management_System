package GP;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GymManagementSystem {

	
	 private static ArrayList<Gym> gymMembers= new ArrayList<>();

	public static void main(String[] args) { 
		
		JFrame frame = new JFrame("Gym Management System");
		frame.setSize(400,300);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		JButton addButton = new JButton("Add Client");
		JButton viewButton = new JButton("View Clients");
		JButton searchClientButton = new JButton("Search Client");
		JButton exitButton = new JButton("Exit");
        
		frame.add(addButton);
		frame.add(viewButton);
		frame.add(searchClientButton);
		frame.add(exitButton);
		
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addClient();				
			}
		});
		
		
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewClients();				
			}
		});
		
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		searchClientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchName = JOptionPane.showInputDialog(null,"Enter Client name to seach:");
				boolean found = false;
				for(Gym g : gymMembers) {
					if(g.getName().equalsIgnoreCase(searchName)) {
						String info = "Name: "+ g.getName()+"\n"
						 +"Age: " +g.getAge()+"\n"
						 +"Height: "+g.getHeight()+"\n"
						 +"weight: "+g.getWeight()+"\n"
						 +"Subscription Start: "+g.getSubscriptionStartDate()+"\n"
						 +"Subscription End: "+g.getSubscriptionEndDate();
						JOptionPane.showMessageDialog(null, info);
						found = true;
						break;
						
					}
					
				}
				if(!found) {
					JOptionPane.showMessageDialog(null, "Client not found!");
					
				}
				
			}
		});
		
		
		frame.setVisible(true);
		
					}
	private static void addClient() {
		JTextField nameField = new JTextField(10);
		JTextField ageField = new JTextField(5);
		JTextField heightField = new JTextField(5);
		JTextField weightField = new JTextField(5);
		JTextField startDateField = new JTextField(10);
		JTextField endDateField = new JTextField(10);
		
	    JPanel panel = new JPanel(new GridLayout(6,2));
	    panel.add(new JLabel("Name:") );
	    panel.add(nameField);
        
	    panel.add(new JLabel("Age:") );
	    panel.add(ageField);
	    
	    panel.add(new JLabel("Height:") );
	    panel.add(heightField);
	    
	    panel.add(new JLabel("Weight:") );
	    panel.add(weightField);
	    
	    panel.add(new JLabel("Strart Date:") );
	    panel.add(startDateField);
	    
	    panel.add(new JLabel("End Date:") );
	    panel.add(endDateField);
	    
	    int result = JOptionPane.showConfirmDialog(null, panel,"Add New Client",JOptionPane.OK_CANCEL_OPTION);

	    if(result == JOptionPane.OK_OPTION ) {
	     Gym newGym = new Gym(nameField.getText(),
	    		 Integer.parseInt(ageField.getText()),
	    		 Float.parseFloat(heightField.getText()),
	    		 Float.parseFloat(weightField.getText()),
	    		  startDateField.getText(),
	    		 endDateField.getText()
	    		 );
	     gymMembers.add(newGym);
	     try {
	    	    Connection conn = DatabaseConnection.connect();
	    	    String sql = "INSERT INTO clients (name, age, height, weight, subscriptionStartDate, subscriptionEndDate) VALUES (?, ?, ?, ?, ?, ?)";
	    	    PreparedStatement stmt = conn.prepareStatement(sql);
	    	    stmt.setString(1, newGym.getName());
	    	    stmt.setInt(2, newGym.getAge());
	    	    stmt.setFloat(3, newGym.getHeight());
	    	    stmt.setFloat(4, newGym.getWeight());
	    	    stmt.setDate(5, newGym.getSubscriptionStartDate());
	    	    stmt.setDate(6, newGym.getSubscriptionEndDate());
	    	    stmt.executeUpdate();
	    	    stmt.close();
	    	    conn.close();
	    	    JOptionPane.showMessageDialog(null, "Client added to database successfully");
	    	} catch (Exception ex) {
	    	    JOptionPane.showMessageDialog(null, "Error saving client to database: " + ex.getMessage());
	    	    ex.printStackTrace();
	    	}
	     JOptionPane.showMessageDialog(null, "Client added successfully");
	    	
	    }
	    
	
		
	}
	

	private static void viewClients() {
	    StringBuilder builder = new StringBuilder();
	    try {
	        Connection conn = DatabaseConnection.connect();
	        String sql = "SELECT * FROM clients";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            builder.append("Name: ").append(rs.getString("name")).append("\n");
	            builder.append("Age: ").append(rs.getInt("age")).append("\n");
	            builder.append("Height: ").append(rs.getFloat("height")).append("\n");
	            builder.append("Weight: ").append(rs.getFloat("weight")).append("\n");
	            builder.append("Subscription Start: ").append(rs.getDate("subscriptionStartDate")).append("\n");
	            builder.append("Subscription End: ").append(rs.getDate("subscriptionEndDate")).append("\n\n");
	        }

	        rs.close();
	        stmt.close();
	        conn.close();
	    } catch (Exception e) {
	        builder.append("Error retrieving clients: ").append(e.getMessage());
	        e.printStackTrace();
	    }

	    JTextArea textArea = new JTextArea(builder.toString());
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    textArea.setEditable(false);
	    JOptionPane.showMessageDialog(null, scrollPane, "List of Clients", JOptionPane.INFORMATION_MESSAGE);
	}
}