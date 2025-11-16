//package tests;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import utils.ExcelReader;
//
//public class ExcelReaderTest {
//
//    @Test
//    public void excelReadTest() {
//        ExcelReader reader = new ExcelReader("test-data/search-data.xlsx", "Sayfa1");
//
//        String value1 = reader.getCell(0, 0); // -> şort
//        String value2 = reader.getCell(1, 0); // -> gömlek
//
//        Assertions.assertEquals("şort", value1);
//        Assertions.assertEquals("gömlek", value2);
//
//        reader.close();
//    }
//}
