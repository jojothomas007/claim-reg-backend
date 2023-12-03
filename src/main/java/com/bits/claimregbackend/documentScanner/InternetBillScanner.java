package com.bits.claimregbackend.documentScanner;

import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClient;
import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClientBuilder;
import com.azure.ai.formrecognizer.documentanalysis.models.AnalyzeResult;
import com.azure.ai.formrecognizer.documentanalysis.models.AnalyzedDocument;
import com.azure.ai.formrecognizer.documentanalysis.models.OperationResult;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.SyncPoller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class InternetBillScanner {
    private static final String modelId = "composed-model";
    private static final String endpoint = "https://claimsreg.cognitiveservices.azure.com";
    private static final String key = "682037791b864ec68270a3d6ce6fcd41";

    public List<DocField> scanInvoice(Path filePath) throws IOException {
        DocumentAnalysisClient documentAnalysisClient = new DocumentAnalysisClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();

        SyncPoller<OperationResult, AnalyzeResult> analyzeDocumentPoller =
                documentAnalysisClient.beginAnalyzeDocument(modelId, BinaryData.fromFile(filePath));
        return analyzeResult(analyzeDocumentPoller.getFinalResult());
    }

    public List<DocField> scanInvoiceFile(MultipartFile multipartFile) throws IOException {
        DocumentAnalysisClient documentAnalysisClient = new DocumentAnalysisClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();

        SyncPoller<OperationResult, AnalyzeResult> analyzeDocumentPoller =
                documentAnalysisClient.beginAnalyzeDocument(modelId, BinaryData.fromBytes(multipartFile.getBytes()));
        return analyzeResult(analyzeDocumentPoller.getFinalResult());
    }

    private List<DocField> analyzeResult(AnalyzeResult analyzeResult) {

        final AnalyzedDocument analyzedDocument = analyzeResult.getDocuments().get(0);
        Map<InternetBillFields, DocField> scanResult = new HashMap();
        InternetBillScanResult internetBillScanResult = new InternetBillScanResult();
        List<DocField> docFieldList = new ArrayList<>();
        analyzedDocument.getFields().forEach((key, documentField) -> {
            DocField docField = new DocField();
            docField.setKey(key);
            docField.setConfidence(documentField.getConfidence());
            //To handle different date formats in different documents. It should be ideally handled at Azure AI side
            switch (InternetBillFields.valueOfFieldName(key)) {
                case SUBSCRIPTION_START_DATE:
                case SUBSCRIPTION_END_DATE:
                case INVOICE_DATE:
                    String content = documentField.getContent();
                    String formattedDate = "";
                    if (Pattern.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})", content)) {
                        formattedDate = getFormattedDateString("dd/MM/yyyy", content);
                    } else if (Pattern.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})", content)) {
                        formattedDate = getFormattedDateString("dd-MM-yyyy", content);
                    } else if (Pattern.matches("([0-9]{2})-([a-z|A-Z]{3})-([0-9]{4})", content)) {
                        formattedDate = getFormattedDateString("dd-MMM-yyyy", content);
                    } else if (Pattern.matches("([0-9]{2}) ([a-z|A-Z]{3}) ([0-9]{4})", content)) {
                        formattedDate = getFormattedDateString("dd MMM yyyy", content);
                    } else formattedDate = content;
                    docField.setValue(formattedDate);
                    break;
                case INVOICE_NUMBER:
                case AMOUNT:
                case NAME:
                default:
                    docField.setValue(documentField.getContent());
                    break;
            }
            docFieldList.add(docField);
        });
        return docFieldList;
    }

    private static String getFormattedDateString(String pattern, String content) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            LocalDate localDate = LocalDate.parse(content, formatter);
            String result = localDate.format(formatter);
            if (result.equalsIgnoreCase(content)) {
                return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            }
        } catch (DateTimeParseException exp) {
            System.out.println("Date formatting was not successful");
        }
        return content;
    }
}