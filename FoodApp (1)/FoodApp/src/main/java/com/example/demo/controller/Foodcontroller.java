package com.example.demo.controller;

import java.awt.Image;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;
import java.sql.Blob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Fooddetails;
import com.example.demo.entity.Foodentity;
import com.example.demo.entity.Foodpost;
import com.example.demo.entity.Payment;
import com.example.demo.entity.Userlogin;
import com.example.demo.service.Foodservice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/food")

public class Foodcontroller {

@Autowired
	Foodservice emps;
/** get all foods **/

//@GetMapping("/getfoode")
//public List<Fooddetails> getrecord()  
//{
//	
//	return emps.Findalle();
//	
//	
//}
@GetMapping("/getfoode")
public List<Fooddetails> getrecord() {
    List<Fooddetails> foodList = emps.Findalle();
    
    for (Fooddetails food : foodList) {
    byte[] imageData = food.getImagedata();
        
        // Convert the byte array to a Base64 encoded string
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        
        // Set the Base64 encoded string as a property on the Fooddetails object
        food.setImagedata(base64Image);
    
    }
    
    return foodList;
}
@GetMapping(value = "/imagedata/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
public ResponseEntity<byte[]> getImage(@PathVariable int id) {
    Optional<Fooddetails> imageEntityOptional = emps.findById(id);
    if (imageEntityOptional.isPresent()) {
        byte[] imageData = imageEntityOptional.get().getImagedata();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
    } else {
        return ResponseEntity.notFound().build();
    }
}
//@GetMapping("/getfoode")
//public ResponseEntity<Fooddetails> getFoodDetails() {
//  List<Fooddetails> save = emps.Findalle();
//  return new ResponseEntity<Fooddetails>(HttpStatus.OK);
//}
//@GetMapping("/getfoode")
//public ResponseEntity<Fooddetails>  getrecord(@RequestParam int  id)  
//{
//	Image image = emps.findByIde(id).orElse(null); 
//    if (image == null) {
//        List<Fooddetails> foodDetails = emps.Findalle(); 
//        return new ResponseEntity<Fooddetails>(HttpStatus.OK);
//    }
//    return ResponseEntity.ok()
//            .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
//            .body(image.getImage());
//}
//
//}
	
//	@PostMapping("/postfood")
//    public ResponseEntity<String> uploadImage(@RequestBody MultipartFile file) throws IOException, SQLException {
//		Foodpost image = new Foodpost();
//        image.setFoodname(file.getName());
//        image.setContent(file.getContentType());
//       // image.setData(file.getBytes());
//		emps.Saveimage(image);
//            return ResponseEntity.ok("Image uploaded successfully");
//    }
//	@PostMapping("/postrecord")
//	public ResponseEntity<Foodentity> postName(@RequestBody Foodentity e)
//	{
//		
//		Foodentity save = emps.Saverecord(e);
//		return new ResponseEntity<Foodentity>(save, HttpStatus.ACCEPTED);
//		
//		
//	}
//@Autowired
//private JavaMailSender javaMailSender;
	@PostMapping("/signup")
	public ResponseEntity<Userlogin> signuppage( @Valid @RequestBody Userlogin e)
	{
		
		Userlogin save = emps.signup(e);
		return new ResponseEntity<Userlogin>(save, HttpStatus.ACCEPTED);
		// Send the welcome email
//		 try {
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(save.getEmail());
//        msg.setSubject("Welcome to Our Service");
//        msg.setText("Hello " + save.getUsername() + ",\n\nThank you for signing up!\n\nBest regards,\nOur Team");
//        javaMailSender.send(msg);
//
//        return new ResponseEntity<>("Signup successful and email sent", HttpStatus.ACCEPTED);
//    } catch (Exception ex) {
//        return new ResponseEntity<>("Error in sending email: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}

	@PostMapping("/pay")
	public ResponseEntity<Payment> postrecord( @Valid @RequestBody Payment e)
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
	/** get login by id **/
	@GetMapping("/login/{username}/{password}")
	public List<Userlogin> getloginbyid(@PathVariable String username,@PathVariable String password)  
	{
		
		return emps.findbylogin(username,password);
		
		
	}
	/** get food by id **/
//	@GetMapping("/getfood/{id}")
//	public Foodentity  getrecordbyid(@PathVariable int id)  
//	{
//		
//		return emps.Findbyid(id);
//		
//		
//	}
	/** get food by category like veg, non veg **/
	@GetMapping("/getfoode/{category}")
	public List<Fooddetails> getfoodid(@PathVariable String category) 
	{
		List<Fooddetails> foodList = emps.findbycategory(category);
		 for (Fooddetails food : foodList) {
			    byte[] imageData = food.getImagedata();
			        
			        // Convert the byte array to a Base64 encoded string
			        String base64Image = Base64.getEncoder().encodeToString(imageData);
			        
			        // Set the Base64 encoded string as a property on the Fooddetails object
			        food.setImagedata(base64Image);
			    
			    }
			    
			    return foodList;
		
	}
	/** get food by restaturant name like sangeetha **/
	@GetMapping("/getfoode/getfoodres/{resname}")
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
	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void getImage(HttpServletResponse response) throws IOException {

        var imgFile = new ClassPathResource("/image2.jpg");

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	}
	
	
// display image
//@GetMapping("/display")
//public ResponseEntity<byte[]> displayImage(@RequestParam("id") int id) throws IOException, SQLException
//{
//    Fooddetails image = emps.viewById(id);
//    byte [] imageBytes = null;
//    imageBytes = image.getImage().getBytes(1,(int) image.getImage().length());
//    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
//    return emps.Findalle();
//}
//
//// view All images
//@GetMapping("/viewimage")
//public ModelAndView home(){
//    ModelAndView mv = new ModelAndView("index");
//    List<Fooddetails> imageList = emps.viewAll();
//    mv.addObject("imageList", imageList);
//    return mv;
//}
//
//// add image - get
//@GetMapping("/addgetimage")
//public ModelAndView addImage(){
//    return new ModelAndView("addimage");
//}
//
//// add image - post
//@PostMapping("/addimage")
//public String addImagePost(HttpServletRequest request,@RequestParam("image") MultipartFile file) throws IOException, SerialException, SQLException
//{
//    byte[] bytes = file.getBytes();
//    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//
//    Fooddetails image = new Fooddetails();
//    image.setImage(blob);
//    emps.postimage(image);
//    return "redirect:/";
//}
}