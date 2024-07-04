package cc.converter.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.RelativeHorizontalPosition;
import com.aspose.words.RelativeVerticalPosition;
import com.aspose.words.SaveFormat;
import com.aspose.words.Shape;
import com.aspose.words.WrapType;

import cc.converter.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Override
	public byte[] uploadDoc(byte[] imageBytes) throws Exception {

		Document doc = new Document();
		DocumentBuilder builder = new DocumentBuilder(doc);

		Shape imageShape = builder.insertImage(new ByteArrayInputStream(imageBytes));
		imageShape.setWrapType(WrapType.SQUARE);
        imageShape.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
        imageShape.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
		
		double imageWidth = imageShape.getWidth();
		double imageHeight = imageShape.getHeight();
		
		doc.getFirstSection().getPageSetup().setPageHeight(imageHeight);
		doc.getFirstSection().getPageSetup().setPageWidth(imageWidth);
		
		ByteArrayOutputStream wordStream = new ByteArrayOutputStream();
		doc.save(wordStream, SaveFormat.DOCX);
		
		Document pdfDoc = new Document(new ByteArrayInputStream(wordStream.toByteArray()));
		ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
		pdfDoc.save(pdfStream, SaveFormat.PDF);
		
		pdfDoc.cleanup();
		
		return pdfStream.toByteArray();
		
	}

	@Override
	public String testApp() {
		return "Application is running.";
	}
}