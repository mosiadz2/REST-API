package myApp;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/graphicsCards")
public class GraphicCardsResource 
{	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public List<GraphicCards> getGraphicsCards()
	{
		return GraphicCardsDAO.instance.getGraphicsCards();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	@Path("{graphicsCardsId}")
	public GraphicCards getGraphicsCards(@PathParam("graphicsCardsId") String id)
	{
		return GraphicCardsDAO.instance.getGraphicsCards(Integer.parseInt(id));
	}
	

	
	@POST
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postGraphicCard(
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("gpuChip") String gpuChip, 
			@FormParam("memory") String memory,
			@FormParam("release") String release, 
			@FormParam("gpuClock") int gpuClock, 
			@FormParam("memoryClock") int memoryClock,
			@FormParam("shaders") int shaders,
			@Context HttpServletResponse servletResponse) throws IOException {
		System.out.println("Inside POST id = " + id);
		System.out.println("name = " + name);

		GraphicCards graphicCard = new GraphicCards();
		graphicCard.setId(id);
		graphicCard.setName(name);
		graphicCard.setGpuChip(gpuChip);
		graphicCard.setMemory(memory);
		graphicCard.setRelease(release);
		graphicCard.setGpuClock(gpuClock);
		graphicCard.setMemoryClock(memoryClock);
		graphicCard.setShaders(shaders);

		GraphicCardsDAO.instance.createGraphicCard(graphicCard);

		servletResponse.sendRedirect("../createGraphicCard.html");
	}

	@DELETE
	@Produces(MediaType.TEXT_XML)
	@Path("{graphicCardID}")
	public void deletegraphicCard(@PathParam("graphicCardID") int id) throws IOException 
	{
		System.out.println("Delete id = " + id);

		GraphicCardsDAO.instance.deleteGraphicCard(id);
	}

	@PUT
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{graphicCardID}")
	public void putGraphicCard(
			@PathParam("graphicCardID") String id,
			
			@FormParam("name") String name,
			@FormParam("gpuChip") String gpuChip, 
			@FormParam("memory") String memory,
			@FormParam("release") String release, 
			@FormParam("gpuClock") int gpuClock, 
			@FormParam("memoryClock") int memoryClock,
			@FormParam("shaders") int shaders,
			@Context HttpServletResponse servletResponse) throws IOException {
		
		System.out.println("PUT id = " + id);
		System.out.println("name = " + name);

		GraphicCards graphicCard = new GraphicCards();
		graphicCard.setId(Integer.parseInt(id));
		graphicCard.setName(name);
		graphicCard.setGpuChip(gpuChip);
		graphicCard.setMemory(memory);
		graphicCard.setRelease(release);
		graphicCard.setGpuClock(gpuClock);
		graphicCard.setMemoryClock(memoryClock);
		graphicCard.setShaders(shaders);	
		
		GraphicCardsDAO.instance.updateGraphicCard(graphicCard);
	}
	
}
