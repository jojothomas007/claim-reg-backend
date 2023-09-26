package com.bits.claimregbackend.contorller;

import com.bits.claimregbackend.documentScanner.DocField;
import com.bits.claimregbackend.documentScanner.InternetBillScanResult;
import com.bits.claimregbackend.documentScanner.InternetBillScanner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ScannerController {
    @PostMapping("/scan-file")
    public void scanFile(@RequestBody String filePath) {//@HeaderParam("filePath")
        try {
            Path path;
//            path = Paths.get("C:\\Users\\jojot\\Documents\\Jojo_Files\\Exams\\2022\\BITS\\Sem4\\Project\\data\\training\\asianet\\asia-inv-jj-05-23.pdf");
            path = Paths.get(filePath);
            InternetBillScanResult internetBillScanResult = new InternetBillScanner().scanInvoice(path);
            printScannedValueAndConfidence(internetBillScanResult.getSubscriberName());
            printScannedValueAndConfidence(internetBillScanResult.getInvoiceNumber());
            printScannedValueAndConfidence(internetBillScanResult.getInvoiceDate());
            printScannedValueAndConfidence(internetBillScanResult.getSubscriptionStartDate());
            printScannedValueAndConfidence(internetBillScanResult.getSubscriptionEndDate());
            printScannedValueAndConfidence(internetBillScanResult.getAmount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printScannedValueAndConfidence(DocField docField) {
        System.out.println(String.format("Key : %s, Value: %s, ConfidenceLevel : %s", docField.getKey(), docField.getValue(), docField.getConfidence()));
    }

}