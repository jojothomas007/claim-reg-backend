package com.bits.claimregbackend.contorller;

import com.bits.claimregbackend.documentScanner.DocField;
import com.bits.claimregbackend.documentScanner.InternetBillScanner;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ScannerController {
    @PostMapping("/scan-file")
    public List<DocField> scanFile(@RequestBody String filePath) {//@HeaderParam("filePath")
        try {
            Path path = Paths.get(filePath);
            return new InternetBillScanner().scanInvoice(path);
//            InternetBillScanResult internetBillScanResult = new InternetBillScanner().scanInvoice(path);
//            printScannedValueAndConfidence(internetBillScanResult.getSubscriberName());
//            printScannedValueAndConfidence(internetBillScanResult.getInvoiceNumber());
//            printScannedValueAndConfidence(internetBillScanResult.getInvoiceDate());
//            printScannedValueAndConfidence(internetBillScanResult.getSubscriptionStartDate());
//            printScannedValueAndConfidence(internetBillScanResult.getSubscriptionEndDate());
//            printScannedValueAndConfidence(internetBillScanResult.getAmount());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/scan-pdf-file", consumes = {"multipart/form-data"})
    public List<DocField> scanPdfFile(@RequestParam(name = "file") MultipartFile file) {
        try {
            return new InternetBillScanner().scanInvoiceFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

//    private void printScannedValueAndConfidence(DocField docField) {
//        System.out.println(String.format("Key : %s, Value: %s, ConfidenceLevel : %s", docField.getKey(), docField.getValue(), docField.getConfidence()));
//    }

}