package myApp;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "graphicsCards")
//Optional
@XmlType(propOrder = {"id", "name", "gpuChip", "release",
		"memory", "gpuClock", "memoryClock", "shaders"})
public class GraphicCards 
{
	private int id;
	private String name;
	private String gpuChip;
	private String memory;
	private String release;
	private int gpuClock;
	private int memoryClock;
	private int shaders;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGpuChip() {
		return gpuChip;
	}
	public void setGpuChip(String gpuChip) {
		this.gpuChip = gpuChip;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public int getGpuClock() {
		return gpuClock;
	}
	public void setGpuClock(int gpuClock) {
		this.gpuClock = gpuClock;
	}
	public int getMemoryClock() {
		return memoryClock;
	}
	public void setMemoryClock(int memoryClock) {
		this.memoryClock = memoryClock;
	}
	public int getShaders() {
		return shaders;
	}
	public void setShaders(int shaders) {
		this.shaders = shaders;
	}
	
	
	
}
