package com.wordanalyze.demo.Controllers;

import com.wordanalyze.demo.Exceptions.FileUploadErrorException;
import com.wordanalyze.demo.POJO.Result;
import com.wordanalyze.demo.Repositories.WordFrequencyRepository;
import com.wordanalyze.demo.Services.WordFrequencyService;
import com.wordanalyze.demo.Utilities.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/analyze")
public class WordFrequencyController implements HandlerExceptionResolver {

    @Autowired
    private WordFrequencyService wfService;

    @GetMapping("/")
    public Result getResultByName(@RequestParam(value="name") String name){
        Result res = wfService.getResultByName(name);
        if (res == null){
            throw new FileUploadErrorException();
        }
        return res;
    }

    @GetMapping("/all")
    public List<Result> getAllResults(){
        return wfService.getResults();
    }

    @PostMapping("/")
    public String uploadFileAndAnalyze(@RequestParam("file")MultipartFile file,
                                       @RequestParam(value = "stopWords", required = false) String stopWords,
                                       RedirectAttributes redirectAttributes){

        System.out.println("in post mapping");
        if (file != null){

            PropertiesLoader propLoader = PropertiesLoader.getInstance();

            // Uploaded file name saved in /data folder will be names at time
            // uploaded to prevent collision
            String pattern = "MMddyyyyHHmmss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date now = Calendar.getInstance().getTime();
            String sdf = df.format(now);
            String newFileName = propLoader.getDataLocation() + sdf +".txt";

            /**
             * Get name of the file without the path
             * C:users/text1.txt -> text1.txt
             */
            String originalFileName = file.getOriginalFilename();
            int startIndex = originalFileName.
                    replaceAll("\\\\", "/").lastIndexOf("/");
            originalFileName = originalFileName.substring(startIndex + 1);

            File newFile = new File(newFileName);
            try{
                file.transferTo(newFile); // creates a new file to disk
                boolean stopSetting;
                if (stopWords == null){
                    stopSetting = false;
                }
                else {
                    stopSetting = true;
                }
                wfService.analyzeFrequency(sdf + ".txt", originalFileName, stopSetting);
            }
            catch (IOException e){
                e.printStackTrace();
                return "redirect:/"; //TODO redirect :/data/{newFileName}
            }
        }
        else {
            throw new FileUploadErrorException();
        }
        return "redirect:/"; //TODO
    }

    /*** Trap Exceptions during the upload and show errors back in view form ***/
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception exception)
    {
        Map<String, Object> model = new HashMap<>();
        if (exception instanceof MaxUploadSizeExceededException)
        {
            model.put("errors", exception.getMessage());
        } else
        {
            model.put("errors", "Unexpected error: " + exception.getMessage());
        }
        return new ModelAndView("/", model);
    }
}
