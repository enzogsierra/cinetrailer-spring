package com.example.cinetrailer.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import com.example.cinetrailer.exceptions.FileNotFoundException;
import com.example.cinetrailer.exceptions.StorageException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StorageServiceImpl implements StorageService 
{
    @Value("${storage.location}")
    private String storageLocation;


    @PostConstruct // This method will be called when every time this class is initialized
    @Override
    public void init() 
    {
        try 
        {
            Files.createDirectories(Paths.get(storageLocation));
        } 
        catch(IOException e) 
        {
            throw new StorageException("Error while trying to initialize the storage location");
        }
    }

    @Override
    public String storeFile(MultipartFile file) 
    {
        if(file.isEmpty())
        {
            throw new StorageException("Cannot store an empty file");
        }

        //
        String filename = file.getOriginalFilename();
        
        try 
        {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, Paths.get(storageLocation).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } 
        catch(IOException e) 
        {
            throw new StorageException("Error while trying to store the file " + filename, e);
        }
        return filename;
    }

    @Override
    public Path loadFile(String filename)
    {
        return Paths.get(storageLocation).resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) 
    {
        try 
        {
            Path file = loadFile(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable())
            {
                return resource;
            }
            else
            {
                throw new FileNotFoundException("Can't find the file " + filename);
            }
        } 
        catch(MalformedURLException e) 
        {
            throw new FileNotFoundException("Can't find the file " + filename, e);
        }
    }

    @Override
    public void deleteFile(String filename) 
    {
        Path file = loadFile(filename);

        try 
        {
            FileSystemUtils.deleteRecursively(file);    
        } 
        catch(IOException e) 
        {
            System.out.println(e);
        }
    }
}
