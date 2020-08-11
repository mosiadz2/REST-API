package ProjectGraphicCards;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.NameValuePair;

import myApp.GraphicCards;

public class PcPartsClient 
{
	
	//post
	public static void Post(String _name, String _gpuChip, String _memory, 
			String _release, int _gpuClock, int _memoryClock, int _shaders) throws Exception
	{
			URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath("/ProjectTemplate/rest/graphicsCards").build();
			
			System.out.println(uri.toString());
			
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setHeader("Accept", "text/xml");
			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			// POST
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("id", ""));
			nameValuePairs.add(new BasicNameValuePair("name", _name));
			nameValuePairs.add(new BasicNameValuePair("gpuChip", _gpuChip));
			nameValuePairs.add(new BasicNameValuePair("memory", _memory));
			nameValuePairs.add(new BasicNameValuePair("release", _release));
			nameValuePairs.add(new BasicNameValuePair("gpuClock",Integer.toString(_gpuClock)));
			nameValuePairs.add(new BasicNameValuePair("memoryClock", Integer.toString(_memoryClock)));
			nameValuePairs.add(new BasicNameValuePair("shaders", Integer.toString(_shaders)));
			              
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs)) ;
			System.out.println("Sending request...");
			CloseableHttpResponse response = httpClient.execute(httpPost);
			
			System.out.print("Response: " + response.toString());
	}
	
	public static void Delete(int _id) throws Exception
	{
		URI uri = new URIBuilder()
				.setScheme("http")
				.setHost("localhost")
				.setPort(8080)
				.setPath("/ProjectTemplate/rest/graphicsCards/" + Integer.toString(_id)).build();
		
		System.out.println(uri.toString());
		HttpDelete httpDelete = new HttpDelete(uri);
		httpDelete.setHeader("Accept", "text/xml");
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		System.out.print("Sending delete request...");
		CloseableHttpResponse response = httpClient.execute(httpDelete);
		System.out.print("Response: " + response.toString());
	}
	
	public static void Update(int _id, String _name, String _gpuChip, String _memory, 
			String _release, int _gpuClock, int _memoryClock, int _shaders) throws Exception
	{
			URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath("/ProjectTemplate/rest/graphicsCards/" + _id).build();
			
			System.out.println(uri.toString());
			
			HttpPut httpPut = new HttpPut(uri);
			httpPut.setHeader("Accept", "text/xml");
			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			// PUT
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("name", _name));
			nameValuePairs.add(new BasicNameValuePair("gpuChip", _gpuChip));
			nameValuePairs.add(new BasicNameValuePair("memory", _memory));
			nameValuePairs.add(new BasicNameValuePair("release", _release));
			nameValuePairs.add(new BasicNameValuePair("gpuClock",Integer.toString(_gpuClock)));
			nameValuePairs.add(new BasicNameValuePair("memoryClock", Integer.toString(_memoryClock)));
			nameValuePairs.add(new BasicNameValuePair("shaders", Integer.toString(_shaders)));
			              
			httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs)) ;
			System.out.println("Sending PUT request...");
			CloseableHttpResponse response = httpClient.execute(httpPut);
			
			System.out.print("Response: " + response.toString());
	}
	
	public static List<GraphicCards> GetGraphicCards() 
	{
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = null;
		
		List<GraphicCards> gpuList = null;
		try 
		{
			URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath("/ProjectTemplate/rest/graphicsCards/").build();

			System.out.println(uri.toString());
			
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "application/xml");
			httpClient = HttpClients.createDefault();
			
			response = httpClient.execute(httpGet);

			String a;
			try 
			{
				HttpEntity entity = response.getEntity();
				a = getASCIIContentFromEntity(entity);
				System.out.println(a);

				gpuList = new Parser().doParseGraphicCards(a);
				System.out.println("------------------");
				for (GraphicCards gpu : gpuList) 
				{
					System.out.println("Id: " + gpu.getId());
					System.out.println("Name: " + gpu.getName());
					System.out.println("Gpu Chip: " + gpu.getGpuChip());
					System.out.println("Memory: " + gpu.getMemory());
					System.out.println("Release: " + gpu.getRelease());
					System.out.println("Gpu Clock: " + gpu.getGpuClock());
					System.out.println("Memory Clock: " + gpu.getMemoryClock());
					System.out.println("Shaders: " + gpu.getShaders());
				}
			} 
			finally 
			{
				response.close();
			}
			System.out.println("Reply: " + a);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return gpuList;
	}
	
	static String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException 
	{ 
		InputStream in = entity.getContent(); 
		StringBuffer out = new StringBuffer(); 
		int n = 1; 
		while (n > 0) 
		{ 
			byte[] b = new byte[4096]; 
			n = in.read(b); 
			if (n > 0) 
				out.append(new String(b, 0, n)); 
		} 
		return out.toString(); 
	}
	

	
	//example of getting the request
	/*public void Client() throws URISyntaxException, IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		URI uri = new URIBuilder()
				.setScheme("http")
				.setHost("www.google.com")
				.setPath("/search")
				.setParameter("q", "httpclient")
				.setParameter("btnG", "Google Search")
				.setParameter("aq", "f")
				.setParameter("oq", "")
				.build();
		HttpGet httpget = new HttpGet(uri);
		CloseableHttpResponse response = httpclient.execute(httpget);
	}*/
}

