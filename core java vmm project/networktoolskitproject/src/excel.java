
        try {

            File f1 = new File(System.getProperty("user.home") + "\\Controller Files");
            if (!f1.exists()) {
                f1.mkdir();
            }
            File f = new File(System.getProperty("user.home") + "\\Controller Files\\" + lb_pcname.getText() + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow row0 = sheet.createRow((short) 0);
            row0.createCell(0).setCellValue("ip : ");
            row0.createCell(1).setCellValue(j7_ip.getText());

            HSSFRow row1 = sheet.createRow((short) 1);
            row1.createCell(0).setCellValue("pc name : ");
            row1.createCell(1).setCellValue(j8_pc name.getText());

            HSSFRow row2 = sheet.createRow((short) 2);
            row2.createCell(0).setCellValue("OS : ");
            row2.createCell(1).setCellValue(j9_os.getText());

            HSSFRow row3 = sheet.createRow((short) 3);
            row3.createCell(0).setCellValue("no.of prors");
            row3.createCell(1).setCellValue(j10_no.of processors.getText());
            HSSFRow row4 = sheet.createRow((short) 4);
            row4.createCell(0).setCellValue("Ram");
            row4.createCell(1).setCellValue(j11_ram.getText());



            FileOutputStream fileOut = new FileOutputStream(f);
            workbook.write(fileOut);
            fileOut.close();
            //System.out.println("Your excel file has been generated!");
            int r = JOptionPane.showConfirmDialog(rootPane, "Excel File has been generated!!!!!!!\n Do you want to open? ");
            if (r == JOptionPane.YES_OPTION) {
                Desktop.getDesktop().open(f);
            }
