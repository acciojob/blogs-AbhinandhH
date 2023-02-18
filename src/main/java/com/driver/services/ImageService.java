package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        Blog blog = blogRepository2.findById(blogId).get();

        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        blog.getImageList().add(image);

        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        Image image = imageRepository2.findById(id).get();
        Blog blog = null;
        for(Blog blogsOnebyOne : blogRepository2.findAll()){
            if(blogsOnebyOne.getImageList().contains(image)){
                blog = blogsOnebyOne;
                blogsOnebyOne.getImageList().remove(image);
            }
        }
        blogRepository2.save(blog);
        imageRepository2.delete(image);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String dim[] = screenDimensions.split("X");

        int x = Integer.parseInt(dim[0]);
        int y = Integer.parseInt(dim[1]);
        int screenSize = x * y;

        Image image = imageRepository2.findById(id).get();
        String ima = image.getDimensions();
        String dimension[] = ima.split("X");
        int a = Integer.parseInt(dimension[0]);
        int b = Integer.parseInt(dimension[1]);
        int imageSize = a * b;

        return screenSize/imageSize;
    }
}
