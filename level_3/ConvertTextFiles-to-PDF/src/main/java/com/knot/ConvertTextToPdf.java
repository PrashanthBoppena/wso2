package com.knot;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;



import org.apache.synapse.MessageContext; 
import org.apache.synapse.mediators.AbstractMediator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ConvertTextToPdf extends AbstractMediator { 

	
		
	
	public boolean mediate(MessageContext mc) {
		
		String msg =  (String) mc.getProperty("message");
		
		mc.setProperty("out", msg);
		
		Document doc = new Document();  
		try  
		{  
		//generate a PDF at the specified location  
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("E:\\EI\\created-csv\\in\\Motivation.pdf"));  
		System.out.println("PDF created.");  
		//opens the PDF  
		doc.open();  
		//adds paragraph to the PDF file  (
		
		doc.add(new Paragraph(msg));   
		//close the PDF file  
		doc.close();  
		//closes the writer  
		writer.close();  
		}   
		catch (DocumentException e)  
		{  
		e.printStackTrace();  
		}   
		catch (FileNotFoundException e)  
		{  
		e.printStackTrace();  
		}
		return true;
	}
}
