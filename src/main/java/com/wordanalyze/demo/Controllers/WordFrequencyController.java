package com.wordanalyze.demo.Controllers;

import com.wordanalyze.demo.POJO.Result;
import com.wordanalyze.demo.Services.WordFrequencyService;
import com.wordanalyze.demo.Utilities.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class WordFrequencyController implements HandlerExceptionResolver {

    @Autowired
    private WordFrequencyService wfService;

    /**
     * localhost:8080/api/ddMMyyyyHHmmss produces the file created at that moment
     * 
     * @param name - name of the file
     * @return the result if found
     */
    @GetMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<Result> getResultByName(@PathVariable("name") String name) {
        if (!name.matches("-?\\d+(\\.\\d+)?")) { // not numeric
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Result res = wfService.getResultByName(name);
        if (res == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * localhost:8080/api/results Gets the list of results in results.json
     * 
     * @return - the list of results
     */
    @GetMapping(value = "/results", produces = "application/json")
    public ResponseEntity<List<Result>> getAllResults() {
        return new ResponseEntity<>(wfService.getResults(), HttpStatus.OK);
    }

    /**
     * localhost:8080/api/analyze Post request to upload file and analyze the words'
     * frequency
     * 
     * @param file      - file to be uploaded. Must be less than 128kb as stated in
     *                  application.properties
     * @param stopWords - to specify if stopWords should be excluded when analyzing
     * @return - the analyzed results if post was successful
     */
    @PostMapping(value = "/analyze", produces = "application/json")
    public ResponseEntity<Result> uploadFileAndAnalyze(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "stopWords", required = false) String stopWords) {
        if (file != null) {

            PropertiesLoader propLoader = PropertiesLoader.getInstance();

            // Uploaded file name saved in /data folder will be names at time
            // uploaded to prevent collision
            String pattern = "MMddyyyyHHmmss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date now = Calendar.getInstance().getTime();
            String sdf = df.format(now);
            String newFileName = propLoader.getDataLocation() + sdf + ".txt";

            /**
             * Get name of the file without the path C:users/text1.txt -> text1.txt
             */
            String originalFileName = file.getOriginalFilename();
            int startIndex = originalFileName.replaceAll("\\\\", "/").lastIndexOf("/");
            originalFileName = originalFileName.substring(startIndex + 1);

            File newFile = new File(newFileName);
            Result res;
            try {
                file.transferTo(newFile); // creates a new file to disk
                boolean stopSetting;
                if (stopWords == null) {
                    stopSetting = false;
                } else {
                    stopSetting = true;
                }
                res = wfService.analyzeFrequency(sdf + ".txt", originalFileName, stopSetting);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            if (res != null) {
                return new ResponseEntity<>(res, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/delete/{name}", produces = "application/json")
    public ResponseEntity<Result> deleteFile(@PathVariable("file") String file) {
        if (wfService.deleteFile(file)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/deleteAll", produces = "application/json")
    public ResponseEntity<Result> deleteFile() {
        if (wfService.deleteAll()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/download/{name}")
    public void downloadText(HttpServletResponse response,
                             @PathVariable("name") String fileName){

        PropertiesLoader propLoader = PropertiesLoader.getInstance();

        String fileNameNoExtension = fileName.substring(0, fileName.length() - 4);
        Result result = wfService.getResultByName(fileNameNoExtension);

        Path file = Paths.get(propLoader.getDataLocation(), fileName);
        if (Files.exists(file))
        {
            response.setContentType("application/txt");
            response.addHeader("Content-Disposition", "attachment; filename="+result.getOriginalFileName());
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*** To handle when uploaded file exceeded size and client cant stop it ***/
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception exception) {
        Map<String, Object> model = new HashMap<>();
        if (exception instanceof MaxUploadSizeExceededException) {
            model.put("errors", exception.getMessage());
        } else {
            model.put("errors", "Unexpected error: " + exception.getMessage());
        }
        return new ModelAndView("/", model);
    }
}
