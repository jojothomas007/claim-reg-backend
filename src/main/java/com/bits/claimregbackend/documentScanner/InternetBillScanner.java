package com.bits.claimregbackend.documentScanner;

import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClient;
import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClientBuilder;
import com.azure.ai.formrecognizer.documentanalysis.models.AnalyzeResult;
import com.azure.ai.formrecognizer.documentanalysis.models.AnalyzedDocument;
import com.azure.ai.formrecognizer.documentanalysis.models.OperationResult;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.SyncPoller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class InternetBillScanner {
    private static final String modelId = "composed-model";
    private static final String endpoint = "https://claimsreg.cognitiveservices.azure.com";
    private static final String key = "682037791b864ec68270a3d6ce6fcd41";

    public InternetBillScanResult scanInvoice(Path filePath) throws IOException {
        DocumentAnalysisClient documentAnalysisClient = new DocumentAnalysisClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();

        SyncPoller<OperationResult, AnalyzeResult> analyzeDocumentPoller =
                documentAnalysisClient.beginAnalyzeDocument(modelId, BinaryData.fromFile(filePath));

        AnalyzeResult analyzeResult = analyzeDocumentPoller.getFinalResult();
        final AnalyzedDocument analyzedDocument = analyzeResult.getDocuments().get(0);
        Map<InternetBillFields, DocField> scanResult = new HashMap();
        InternetBillScanResult internetBillScanResult = new InternetBillScanResult();
        analyzedDocument.getFields().forEach((key, documentField) -> {
            DocField docField = new DocField();
            docField.setKey(key);
            docField.setValue(documentField.getContent());
            docField.setConfidence(documentField.getConfidence());
            switch (InternetBillFields.valueOfFieldName(key)) {
                case SUBSCRIPTION_START_DATE:
                    internetBillScanResult.setSubscriptionStartDate(docField);
                    break;
                case SUBSCRIPTION_END_DATE:
                    internetBillScanResult.setSubscriptionEndDate(docField);
                    break;
                case INVOICE_NUMBER:
                    internetBillScanResult.setInvoiceNumber(docField);
                    break;
                case INVOICE_DATE:
                    internetBillScanResult.setInvoiceDate(docField);
                    break;
                case AMOUNT:
                    internetBillScanResult.setAmount(docField);
                    break;
                case NAME:
                    internetBillScanResult.setSubscriberName(docField);
                    break;
                default:
                    new RuntimeException(String.format("'%s' - The scanned field not configured", key));
            }
        });
        return internetBillScanResult;
    }
}