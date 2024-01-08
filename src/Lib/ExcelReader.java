package Lib;

public class ExcelReader {
    public static int totalRow;

//    public List<Map<String, String>> getData(String excelFilePath, String sheetName)
//            throws IOException {
//
//        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
//        Sheet sheet = workbook.getSheet(sheetName);
//        workbook.close();
//        return readSheet(sheet);
//    }
//
//    private List<Map<String, String>> readSheet(Sheet sheet) {
//
//        Row row;
//        Cell cell;
//
//        totalRow = sheet.getLastRowNum();
//
//        List<Map<String, String>> excelRows = new ArrayList<Map<String, String>>();
//
//        for (int currentRow = 1; currentRow <= totalRow; currentRow++) {
//
//            row = sheet.getRow(currentRow);
//
//            int totalColumn = row.getLastCellNum();
//
//            LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<String, String>();
//
//            for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
//
//                cell = row.getCell(currentColumn);
//
//                String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
//                        .getStringCellValue();
//
//                columnMapdata.put(columnHeaderName, cell.getStringCellValue());
//            }
//
//            excelRows.add(columnMapdata);
//        }
//
//        return excelRows;
//    }
//
//    public int countRow() {
//
//        return totalRow;
//    }
//
//    /**
//     * This is used to read the data
//     **/
//    public String[][] getExcelData(String excelFilePath, String sheetName) {
//        String[][] arrayExcelData = null;
//        try {
//            Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
//            Sheet sheet = workbook.getSheet(sheetName);
//            int totalNoOfCols = sheet.getRow(0).getLastCellNum();
//            int totalNoOfRows = sheet.getLastRowNum();
//            arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];
//            for (int i = 1; i < totalNoOfRows; i++) {
//                for (int j = 0; j < totalNoOfCols; j++) {
//                    arrayExcelData[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return arrayExcelData;
//    }
}
