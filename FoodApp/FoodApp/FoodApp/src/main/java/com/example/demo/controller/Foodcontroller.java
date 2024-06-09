package com.example.demo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Fooddetails;
import com.example.demo.entity.Foodentity;
import com.example.demo.entity.Foodpost;
import com.example.demo.entity.Payment;
import com.example.demo.service.Foodservice;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/food")

public class Foodcontroller {
	@Autowired
	Foodservice emps;
	
	@PostMapping("/postfood")
    public ResponseEntity<String> uploadImage(@RequestBody MultipartFile file) throws IOException, SQLException {
		Foodpost image = new Foodpost();
        image.setFoodname(file.getName());
        image.setContent(file.getContentType());
       // image.setData(file.getBytes());
		emps.Saveimage(image);
            return ResponseEntity.ok("Image uploaded successfully");
    }
	@PostMapping("/postrecord")
	public ResponseEntity<Foodentity> postName(@RequestBody Foodentity e)
	{
		
		Foodentity save = emps.Saverecord(e);
		return new ResponseEntity<Foodentity>(save, HttpStatus.ACCEPTED);
		
		
	}
	@PostMapping("/pay")
	public ResponseEntity<Payment> postrecord(@RequestBody Payment e)
	{
		
		Payment save = emps.payrecord(e);
		return new ResponseEntity<Payment>(save, HttpStatus.ACCEPTED);
		
		
	}
	@PostMapping("/postrec")
	public ResponseEntity<Foodpost> postfood(@RequestBody Foodpost e)
	{
		
		Foodpost save = emps.Savefoode(e);
		return new ResponseEntity<Foodpost>(save, HttpStatus.ACCEPTED);
		
		
	}
	/** get food by id **/
	@GetMapping("/getfood/{id}")
	public Foodentity  getrecordbyid(@PathVariable int id)  
	{
		
		return emps.Findbyid(id);
		
		
	}
	/** get food by category like veg, non veg **/
	@GetMapping("/getfoode/{category}")
	public List<Fooddetails> getfoodid(@PathVariable String category) 
	{
		
		return emps.findbycategory(category);
		
		
	}
	/** get food by restaturant name like sangeetha **/
	@GetMapping("/getfoodres/{resname}")
	public List<Fooddetails> getfoodres(@PathVariable String resname) 
	{
		
		return emps.findbyresname(resname);
		
	}
	
	/** get food by food name like meals, briyani**/
	@GetMapping("/getfoodname/{foodname}")
	public List<Fooddetails> getfoodname(@PathVariable String foodname) 
	{
		
		return emps.findbyfoodname(foodname);
		
	}
	@GetMapping(value = "/getfoode/image", 
            produces = MediaType.IMAGE_JPEG_VALUE)

    public void getImage(HttpServletResponse response) throws IOException {

        var imgFile = new ClassPathResource("/image2.jpg");

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	}
	

	
//	@GetMapping(value = "/getfoode/image", 
//            produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<byte[]> getImage() throws IOException {
//
//        var imgFile = new ClassPathResource("/image2.jpg");
//        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
//
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
//    }
	/** get all foods **/
	@GetMapping("/getfoode")
	public List<Fooddetails>  getrecord()  
	{
		
		return emps.Findalle();
		
		
	}

}
