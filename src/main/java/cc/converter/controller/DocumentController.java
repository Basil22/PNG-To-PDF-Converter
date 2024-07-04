package cc.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import cc.converter.service.DocumentService;

@RestController
@RequestMapping("/doc")
public class DocumentController {

	@Autowired
	private DocumentService docService;

	@GetMapping
	public String testApp() {
		return docService.testApp();
	}

	@CrossOrigin("http://localhost:4200")
	@PostMapping("/convert")
	public ResponseEntity<byte[]> uploadDoc(@RequestParam("file") MultipartFile file) {
		try {

			byte[] imageBytes = file.getBytes();
			byte[] result = docService.uploadDoc(imageBytes);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
            		ContentDisposition.attachment()
            		.filename(file.getOriginalFilename() + ".pdf	")
            		.build()
            		);
			
			return ResponseEntity.ok().headers(headers).body(result);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}