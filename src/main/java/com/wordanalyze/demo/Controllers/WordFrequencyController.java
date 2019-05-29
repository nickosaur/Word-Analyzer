package com.wordanalyze.demo.Controllers;

import com.wordanalyze.demo.Exceptions.FileUploadErrorException;
import com.wordanalyze.demo.Repositories.WordFrequencyRepository;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/analyze")
public class WordFrequencyController implements HandlerExceptionResolver {

    @Autowired
    private WordFrequencyRepository wfRepo;

    @PostMapping("/")
    public String uploadFileAndAnalyze(@RequestParam("file")MultipartFile file,
                                       RedirectAttributes redirectAttributes){

        System.out.println("in post mapping");
        if (file != null){
            // do something
            //file.transferTo(); -> location held by dev-app.properties
            /**
             * we save the file name as the timestamp so we can have unique name?
             * do we pass it to something else to do the saving?
             * How to validate file
             */
            System.out.println(System.getProperty("user.dir"));
            PropertiesLoader propLoader = PropertiesLoader.getInstance();
            /*System.out.println("data location " + propLoader.getDataLocation());
            String pattern = "MMddyyyyHHmmss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date now = Calendar.getInstance().getTime();
            String sdf = df.format(now);
            String newFileName = propLoader.getDataLocation() + "/" + sdf +".txt";
            System.out.println(newFileName);*/
            //File newFile = new File(propLoader.getDataLocation());
            File newFile = new File(System.getProperty("user.dir")+"/data/abc.txt");
            try{
                file.transferTo(newFile);
                System.out.println("Saved file?");
            }
            catch (IOException e){
                System.out.println("Error" + e);
                return "redirect:/";
            }
            System.out.println("transferedTO DONE");
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
        Map<String, Object> model = new HashMap<String, Object>();
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
