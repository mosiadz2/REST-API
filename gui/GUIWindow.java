package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class GUIWindow extends JFrame
{
	
	private JPanel rightPanel = new JPanel();
	private JPanel leftPanel = new JPanel();
	
	private JPanel rightPanelTop = new JPanel();
	private JPanel rightPanelBottom = new JPanel();
	private JPanel leftPanelTop = new JPanel();
	private JPanel leftPanelBottom = new JPanel();

	private JLabel idLabel = new JLabel("ID");
	private JLabel nameLabel = new JLabel("Name");
	private JLabel gpuChipLabel = new JLabel("GPU Chip");
	private JLabel releaseLabel = new JLabel("Release");
	private JLabel memoryLabel = new JLabel("Memory");
	private JLabel gpuClockLabel = new JLabel("GPU Clock");
	private JLabel memoryClockLabel = new JLabel("Memory Clock");
	private JLabel shadersLabel = new JLabel("Shaders");
	
	private JTable table = new JTable();
	private JScrollPane scrollPanel = new JScrollPane(table);
	private String[] collumnTitles = {"ID", "Name","GPU Chip","Release", "Memory", "GPU Clock", "Memory Clock", "Shaders"};
	
	private JTextField idInput = new JTextField(15);
	private JTextField nameInput = new JTextField(15);
	private JTextField gpuChipInput = new JTextField(15);
	private JTextField releaseInput = new JTextField(15);
	private JTextField memoryInput = new JTextField(15);
	private JTextField gpuClockInput = new JTextField(15);
	private JTextField memoryClockInput = new JTextField(15);
	private JTextField shadersInput = new JTextField(15);
	
	private JButton addButton = new JButton("Add");
	private JButton updateButton = new JButton("Update");
	private JButton deleteButton = new JButton("Delete");
	
	
	public GUIWindow(String text)
	{
		super("Graphic Cards Liblary");
		
		//LEFT PANEL//
		leftPanel.setBorder(BorderFactory.createTitledBorder("Info"));//add Title to panel
		((javax.swing.border.TitledBorder) leftPanel.getBorder()).setTitleFont(new Font("Times New Roma", Font.BOLD, 18));//change font
		
		//leftPanelTop
		  leftPanelTop.setLayout(new GridLayout(8,2, 20, 20));
		
		  leftPanelTop.add(idLabel);
		  leftPanelTop.add(idInput);
		  
		  leftPanelTop.add(nameLabel);
		  leftPanelTop.add(nameInput);
		  
		  leftPanelTop.add(gpuChipLabel);
		  leftPanelTop.add(gpuChipInput);
		  
		  leftPanelTop.add(releaseLabel);
		  leftPanelTop.add(releaseInput);
		  
		  leftPanelTop.add(memoryLabel);
		  leftPanelTop.add(memoryInput);
		  
		  leftPanelTop.add(gpuClockLabel);
		  leftPanelTop.add(gpuClockInput);
		  
		  leftPanelTop.add(memoryClockLabel);
		  leftPanelTop.add(memoryClockInput);
		  
		  leftPanelTop.add(shadersLabel);
		  leftPanelTop.add(shadersInput);
		    
		  
		//rightPanelBottom
		leftPanelBottom.setLayout(new BoxLayout(leftPanelBottom,BoxLayout.Y_AXIS));
  
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		addButton.setMaximumSize(new Dimension(150,30));
		leftPanelBottom.add(addButton);
		leftPanelBottom.add(Box.createVerticalStrut(20));
		
		updateButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
		updateButton.setMaximumSize(new Dimension(150,30));
		leftPanelBottom.add(updateButton);
		leftPanelBottom.add(Box.createVerticalStrut(20));
		
		deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteButton.setMaximumSize(new Dimension(150,30));
		leftPanelBottom.add(deleteButton);
		leftPanelBottom.add(Box.createVerticalStrut(20));
		
		//add both panels to the left one
		leftPanel.setLayout(new BorderLayout(0, 25));
		leftPanel.add(leftPanelTop, BorderLayout.NORTH);
		leftPanel.add(leftPanelBottom, BorderLayout.CENTER);
      
		//RIGHT PANEL// 
		rightPanel.setBorder(BorderFactory.createTitledBorder("GPU's"));//add Title to panel
		((javax.swing.border.TitledBorder) rightPanel.getBorder()).setTitleFont(new Font("Times New Roma", Font.BOLD, 18));//change font
		
		
        rightPanel.add(scrollPanel);
        scrollPanel.setPreferredSize(new Dimension(670, 465));
        DefaultTableModel tableModel = new DefaultTableModel(null, collumnTitles);
		table.setModel(tableModel);
		
		
		
		//setting border layout
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
        
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(1070,535);
		setVisible(true);	
		
	}


	public JTextField getIdInput() {
		return idInput;
	}

	public JTextField getNameInput() {
		return nameInput;
	}


	public JTextField getGpuChipInput() {
		return gpuChipInput;
	}


	public JTextField getReleaseInput() {
		return releaseInput;
	}
	
	public JTextField getMemoryInput() {
		return memoryInput;
	}


	public JTextField getGpuClockInput() {
		return gpuClockInput;
	}


	public JTextField getMemoryClockInput() {
		return memoryClockInput;
	}
	
	public JTextField getShadersInput() {
		return shadersInput;
	}


	public JButton getAddButton() {
		return addButton;
	}


	public JButton getUpdateButton() {
		return updateButton;
	}


	public JButton getDeleteButton() {
		return deleteButton;
	}


	public JTable getTable() {
		return table;
	}

}