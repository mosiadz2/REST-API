package ProjectGraphicCards;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import myApp.GraphicCards;

public class Parser 
{
	boolean inGpuParts = false;
	boolean inGpuPart = false;
	boolean inId = false;
	boolean inName = false;
	boolean inGpuChip = false;
	boolean inMemory = false;
	boolean inRelease = false;
	boolean inGpuClock = false;
	boolean inMemoryClock = false;
	boolean inShaders = false;
	
	GraphicCards currentGraphicCard;
	List<GraphicCards> currentGraphicCardList;
	
	public List<GraphicCards> doParseGraphicCards(String s)
	{
		try
		{
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser pullParser = factory.newPullParser();
			pullParser.setInput(new StringReader(s));
			processDocument(pullParser);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return currentGraphicCardList;
	}
	
	public void processDocument(XmlPullParser pullParser)throws XmlPullParserException, IOException
	{
		int eventType = pullParser.getEventType();
		do
		{
			if(eventType == XmlPullParser.START_DOCUMENT)
			{
				System.out.println("Start Document");
			}
			else if (eventType == XmlPullParser.END_DOCUMENT)
			{
				System.out.println("End Document");
			}
			else if (eventType == XmlPullParser.START_TAG)
			{
				processStartElement(pullParser);
			}
			else if (eventType == XmlPullParser.END_TAG)
			{
				processEndElement(pullParser);
			}
			else if (eventType == XmlPullParser.TEXT)
			{
				processText(pullParser);
			}
			eventType = pullParser.next();
		}
		while(eventType != XmlPullParser.END_DOCUMENT);
	}
	
	public void processStartElement(XmlPullParser event)
	{
		String name = event.getName(); 
		if(name.equals("graphicCardss"))
		{
			inGpuParts = true;
			currentGraphicCardList = new ArrayList<GraphicCards>();
		}
		else if(name.equals("graphicsCards"))
		{
			inGpuPart = true;
			currentGraphicCard = new GraphicCards();
		}
		else if(name.equals("id"))
		{
			inId = true;
		}
		else if(name.equals("name"))
		{
			inName = true;
		}
		else if(name.equals("gpuChip"))
		{
			inGpuChip = true;
		}
		else if(name.equals("memory"))
		{
			inMemory = true;
		}
		else if(name.equals("release"))
		{
			inRelease = true;
		}
		else if(name.equals("gpuClock"))
		{
			inGpuClock = true;
		}
		else if(name.equals("memoryClock"))
		{
			inMemoryClock = true;
		}
		else if(name.equals("shaders"))
		{
			inShaders = true;
		}
	}
	
	public void processText(XmlPullParser event)throws XmlPullParserException
	{
		if(inId)
		{
			String s = event.getText();
			currentGraphicCard.setId(Integer.parseInt(s));
		}
		if(inName)
		{
			String s = event.getText();
			currentGraphicCard.setName(s);
		}
		if(inGpuChip)
		{
			String s = event.getText();
			currentGraphicCard.setGpuChip(s);
		}
		if(inMemory)
		{
			String s = event.getText();
			currentGraphicCard.setMemory(s);
		}
		if(inRelease)
		{
			String s = event.getText();
			currentGraphicCard.setRelease(s);
		}
		if(inGpuClock)
		{
			String s = event.getText();
			currentGraphicCard.setGpuClock(Integer.parseInt(s));
		}
		if(inMemoryClock)
		{
			String s = event.getText();
			currentGraphicCard.setMemoryClock(Integer.parseInt(s));
		}
		if(inShaders)
		{
			String s = event.getText();
			currentGraphicCard.setShaders(Integer.parseInt(s));
		}
	}
	
	public void processEndElement(XmlPullParser event)
	{
		String name = event.getName();
		
		if(name.equals("graphicsCardss"))
		{
			inGpuParts = false;
		}
		else if(name.equals("graphicsCards"))
		{
			inGpuPart = false;
			currentGraphicCardList.add(currentGraphicCard);
		}
		else if(name.equals("id"))
		{
			inId = false;
		}
		else if(name.equals("name"))
		{
			inName = false;
		}
		else if(name.equals("gpuChip"))
		{
			inGpuChip = false;
		}
		else if(name.equals("memory"))
		{
			inMemory = false;
		}
		else if(name.equals("release"))
		{
			inRelease = false;
		}
		else if(name.equals("gpuClock"))
		{
			inGpuClock = false;
		}
		else if(name.equals("memoryClock"))
		{
			inMemoryClock = false;
		}
		else if(name.equals("shaders"))
		{
			inShaders = false;
		}
	}
}
