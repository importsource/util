package com.importsource.util.img;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.net.ssl.HttpsURLConnection;

public class Snippet {
	public void replaceImageColor(String file, Color srcColor, Color targetColor) throws IOException{
	        URL http;
	        if(file.trim().startsWith("https")){
	            http = new URL(file);
	            HttpsURLConnection conn = (HttpsURLConnection) http.openConnection();
	            conn.setRequestMethod("GET"); 
	        }else if(file.trim().startsWith("http")){
	            http = new URL(file);
	            HttpURLConnection conn = (HttpURLConnection) http.openConnection();
	            conn.setRequestMethod("GET"); 
	        }else{
	            http = new File(file).toURI().toURL();
	        }
	        BufferedImage bi = ImageIO.read(http.openStream());
	        
	        for (int i = 0; i < bi.getWidth(); i++) {
	            for (int j = 0; j < bi.getHeight(); j++) {
	                  System.out.println(bi.getRGB(i, j));
	                  int rgbInt=bi.getRGB(i, j);
	                  Color c=new Color(rgbInt);
	                  System.out.println("r:"+c.getRed()+",g:"+c.getGreen()+",b:"+c.getBlue());
	                  if(srcColor.getRGB()==bi.getRGB(i, j)){
	                      System.out.println(i+","+j+"  from:"+srcColor.getRGB()+"to"+targetColor.getRGB());
	                      bi.setRGB(i, j, targetColor.getRGB());
	                  }
	            }
	        }
	        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");
	        ImageWriter writer = it.next();
	        File f = new File("Y:\\test03.png");
	        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
	        writer.setOutput(ios);
	        writer.write(bi);
	        bi.flush();
	        ios.flush();
	        ios.close();
	    }
	    
	    public void createImage(int width, int height) throws IOException{
	        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
	        Graphics2D graphic = bi.createGraphics();
	        graphic.setColor(new Color(0.2f,0.3f,0.4f,0.4f));
	        graphic.fillRect(0, 0, width, height);
	        
	        for (int i = 0; i < width; i++) {
	            for (int j = 0; j < height; j++) {
	                  //result[i][j] = bi.getRGB(i, j) & 0xFFFFFF;
	                  System.out.println(bi.getRGB(i, j));
	                 // bi.setRGB(i, j, 0xFFFFFF);
	            }
	       }
	        
	        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");
	        ImageWriter writer = it.next();
	        File f = new File("Y:\\test02.png");
	        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
	        writer.setOutput(ios);
	        
	        writer.write(bi);
	    }
	    
	    public static void main(String[] args) throws IOException{
	    	Snippet snippet=new Snippet();
	    	snippet.createImage(200, 200);
	    	snippet.replaceImageColor("Y:\\test02.png", new Color(0.2f,0.3f,0.4f,0.0f), new Color(0.7f,0.8f,0.9f,0.0f));
	    }
}

