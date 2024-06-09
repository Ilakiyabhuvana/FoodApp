package com.example.demo.service;



import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Fooddetails;
import com.example.demo.entity.Foodentity;
import com.example.demo.entity.Foodpost;
import com.example.demo.entity.Payment;
import com.example.demo.repo.Foodpostrepo;
import com.example.demo.repo.Foodrepo;
import com.example.demo.repo.Payrepo;
import com.example.demo.repo.Vegrepo;



@Service
public class Foodservice {
	
	@Autowired
	Foodrepo fdrepo;
	@Autowired
	Foodpostrepo fdprepo;
	@Autowired
	Vegrepo vegrepo;
	@Autowired
	Payrepo prepo;
	
	public Foodentity Saverecord(Foodentity e)
	{
		return fdrepo.save(e);
		
	}
	public Payment payrecord(Payment e)
	{
		return prepo.save(e);
		
	}
	public Foodpost Savefoode(Foodpost e)
	{
		return fdprepo.save(e);
		
	}
	public Foodpost Saveimage(Foodpost image)
	{
		return fdprepo.save(image);
		
	}
//	public List<Foodentity> Findall()
//	{
//		return fdrepo.findAll();
//		
//	}
	public List<Fooddetails> Findalle()
	{
		return vegrepo.findAll();
		
	}
	public List<Fooddetails> Findveg()
	{
		return vegrepo.findAll();
		
	}
//	public List<Foodpost> findAllImages() {
//        List<Foodpost> foodposts = fdprepo.Findalle();
//        for (Foodpost foodpost : foodposts) {
//            foodpost.setFoodimage(convertByteArrayToImage(foodpost.getFoodimage()));
//        }
//        return foodposts;
//    }
//
//    private BufferedImage convertByteArrayToImage(byte[] bytes) {
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
//        try {
//            return ImageIO.read(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
	public Foodentity Findbyid(int  id)
	{
		return fdrepo.findById(id).get();
		
		
		
	}
	public List<Fooddetails> findbycategory(String category)
	{
		return  vegrepo.findByCategory(category);
		
	}
public List<Fooddetails> findbyresname(String resname)
	{
		return  vegrepo.findByResname(resname);
		
	}
public List<Fooddetails> findbyfoodname(String foodname)
{
	return  vegrepo.findByFoodname(foodname);
	
}
}
