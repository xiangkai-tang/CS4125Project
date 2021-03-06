package promotion;

/*
 * @author Xiangkai Tang
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import report.Report;
import customer.Customer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFcreater {
    
	Customer customer;
    Report report;
	
    public PDFcreater(Report r, Customer customer) {
		super();
		this.report = r;
		this.customer = customer;
	}
    
    public void outputPDF(){
	    Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Receipt.pdf"));
            document.open();
            document.add(new Paragraph("First Name:"+customer.getFirstName()));
            document.add(new Paragraph("Last Name:"+customer.getLastName()));
            document.add(new Paragraph("LeftMinutes:"+report.getLeftMinutes()));
            document.add(new Paragraph("LeftMessages:"+report.getLeftMessages()));
            document.add(new Paragraph("LeftMobileData:"+report.getLeftMobileData()));
            document.add(new Paragraph("CostUntilNow:"+report.getCostUntilNow()));
            document.add(new Paragraph("MobileData:"+report.getMobileData()));
            document.add(new Paragraph("InternationalMinutes:"+report.getLeftInternationalMinutes()));
            document.add(new Paragraph("DifferentProviderMinutes:"+report.getLeftDifferentProviderMinutes()));
            document.add(new Paragraph("Enddate:"+report.getEndDate()));
            document.add(new Paragraph("Number:"+report.getNumber()));
            document.add(new Paragraph("NumberCount:"+report.getNumberCount()));
            if(report.getNumbers()!=null){
            	int num = 1;
            	for(String item: report.getNumbers()){
            	    document.add(new Paragraph("Number"+num+":"+item));
            		num++;
            	}
            }
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
	}   
}
