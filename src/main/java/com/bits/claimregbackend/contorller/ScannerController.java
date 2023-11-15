package com.bits.claimregbackend.contorller;

import com.bits.claimregbackend.documentScanner.DocField;
import com.bits.claimregbackend.documentScanner.InternetBillScanner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
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

//    private void printScannedValueAndConfidence(DocField docField) {
//        System.out.println(String.format("Key : %s, Value: %s, ConfidenceLevel : %s", docField.getKey(), docField.getValue(), docField.getConfidence()));
//    }

}