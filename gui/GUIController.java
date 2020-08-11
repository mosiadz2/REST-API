package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import ProjectGraphicCards.PcPartsClient;
import myApp.GraphicCardsResource;
import myApp.GraphicCards;
import myApp.GraphicCardsDAO;

public class GUIController implements ActionListener, MouseListener
{
	private GUIWindow GUI;
	private String value;
	private PcPartsClient pcClient;
	private GraphicCardsDAO dao;
	
	public GUIController(GUIWindow _GUI)
	{
		GUI = _GUI;
		UpdateTable();
	}
	
	
	public void controll()
	{
		GUI.getAddButton().addActionListener(this);
		GUI.getUpdateButton().addActionListener(this);
		GUI.getDeleteButton().addActionListener(this);
		
		GUI.getIdInput().addActionListener(this);
		GUI.getNameInput().addActionListener(this);
		GUI.getGpuChipInput().addActionListener(this);
		GUI.getReleaseInput().addActionListener(this);
		GUI.getMemoryInput().addActionListener(this);
		GUI.getGpuClockInput().addActionListener(this);
		GUI.getMemoryClockInput().addActionListener(this);
		GUI.getShadersInput().addActionListener(this);
		
		GUI.getTable().addMouseListener(this);
	}
	
	public void UpdateTable()
	{
		DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name","GPU Chip","Release", "Memory", "GPU Clock", "Memory Clock", "Shaders"}, 0);
		
		List<GraphicCards> graphics = pcClient.GetGraphicCards();
		
		for(GraphicCards gpu : graphics)
	     {
	          model.addRow(new Object[]{gpu.getId(), gpu.getName(), gpu.getGpuChip(), gpu.getRelease(), gpu.getMemory(), 
	        		  gpu.getGpuClock(), gpu.getMemoryClock(), gpu.getShaders()});
	     }
	     
	     GUI.getTable().setModel(model);
	}
	
	public void resetInfo()
	{
		GUI.getIdInput().setText("");
		GUI.getNameInput().setText("");
		GUI.getGpuChipInput().setText("");
		GUI.getReleaseInput().setText("");
		GUI.getMemoryInput().setText("");
		GUI.getGpuClockInput().setText("");
		GUI.getMemoryClockInput().setText("");
		GUI.getShadersInput().setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(GUI.getAddButton()))
		{
			
			String name = GUI.getNameInput().getText();
			String gpuChip = GUI.getGpuChipInput().getText();
			String memory = GUI.getMemoryInput().getText();
			String release = GUI.getReleaseInput().getText();
			int gpuClock = Integer.parseInt(GUI.getGpuClockInput().getText());
			int memoryClock = Integer.parseInt(GUI.getMemoryClockInput().getText());
			int shaders = Integer.parseInt(GUI.getShadersInput().getText());
			
			
			
			try {
				pcClient.Post(name, gpuChip, memory, release, gpuClock, memoryClock, shaders);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			resetInfo();
			UpdateTable();
			
		}
		else if(e.getSource().equals(GUI.getUpdateButton()))
		{
			int id = Integer.parseInt(GUI.getIdInput().getText());
			String name = GUI.getNameInput().getText();
			String gpuChip = GUI.getGpuChipInput().getText();
			String memory = GUI.getMemoryInput().getText();
			String release = GUI.getReleaseInput().getText();
			int gpuClock = Integer.parseInt(GUI.getGpuClockInput().getText());
			int memoryClock = Integer.parseInt(GUI.getMemoryClockInput().getText());
			int shaders = Integer.parseInt(GUI.getShadersInput().getText());
			
			try {
				pcClient.Update(id, name, gpuChip, memory, release, gpuClock, memoryClock, shaders);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			UpdateTable();
			resetInfo();
		}
		else if(e.getSource().equals(GUI.getDeleteButton()))
		{	
			int id = Integer.parseInt(GUI.getIdInput().getText());
			try {
				pcClient.Delete(id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			resetInfo();
			UpdateTable();
		}
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int column = 0;
		int row = GUI.getTable().getSelectedRow();
		value = GUI.getTable().getModel().getValueAt(row, column).toString();
		
		System.out.println(value);
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
