package com.wordanalyze.demo.Services;

import com.wordanalyze.demo.POJO.Result;
import com.wordanalyze.demo.Repositories.WordFrequencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@Service
public class WordFrequencyService {

   // @Autowired
    WordFrequencyRepository wfRepo = new WordFrequencyRepository();

    public Result analyzeFrequency(String newFileName, String oriFileName, boolean stopSetting){
        return wfRepo.analyzeAndUpdate(newFileName, oriFileName, stopSetting);
    }

    public Result getResultByName(String name){
        return wfRepo.getResultByName(name);
    }

    public List<Result> getResults(){
        return wfRepo.getResults();
    }

    public boolean deleteFile(String file){
        return wfRepo.deleteFile(file);
    }

    public boolean deleteAll(){
        return wfRepo.deleteAll();
    }
}
