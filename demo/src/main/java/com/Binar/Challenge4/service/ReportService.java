package com.Binar.Challenge4.service;

import com.Binar.Challenge4.entity.UserEntity;
import com.Binar.Challenge4.repository.UserRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private UserRepository repository;


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\basan\\Desktop\\Report";
        List<UserEntity> Users = repository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:User.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Users);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Please work by", "Nico");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\Users.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Users.pdf");
        }

        return "report generated in path : " + path;
    }
}