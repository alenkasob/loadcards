
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoadFromFile {
    private static final String FILE_PATH = "D:\\crumzi2\\1\\crumzi_cards.xlsx";
    private static final String TOKEN ="eyJhbGciOiJIUzUxMiJ9.eyJUT0tFTl9UWVBFIjoiU0VTU0lPTiIsIkFQUExJQ0FUSU9OX1RZUEUiOiJTRUxMRVIiLCJVU0VSX0lEIjoiMmM5ZjkxZjQ1OTAyZTRjZjAxNTkwMzQwZDA0NTAwMTYiLCJpc3MiOiJDUlVNWkkiLCJpYXQiOjE0ODg4OTYwNjV9.YK72H2Ay4gsdwMZEBgmtFnRzZji9LWvbvBSBphQSLkuoNOCIybCLiO374KNWGfFjVG9O_q6v0wqc0diEjxlPFw";
    // "eyJhbGciOiJIUzUxMiJ9.eyJUT0tFTl9UWVBFIjoiU0VTU0lPTiIsIkFQUExJQ0FUSU9OX1RZUEUiOiJTRUxMRVIiLCJVU0VSX0lEIjoiMmM5ZjkxZjQ1OTk4YjQ0NTAxNTlhNmM4YmE5NjAwMTciLCJpc3MiOiJDUlVNWkkiLCJpYXQiOjE0ODQ1NzM3NDN9.9fMqNNnPpNGuBizl-q0ZMWuQgpoOW_IIQF4efFprsjnbeLKMLc4Ms-IUNqtOjs4kBSGHlUmyEOPuv1RyuvItnQ";
    private static final String card_id ="2c9f91f45908ad94015909302fcc0006";
    public static void main(String args[]) {
        LoadCardsToApi api = new LoadCardsToApi();
       List<Cards> cardsList =  getCardsListFromExcel();
        System.out.println(cardsList);

        try {
            for(Cards card : cardsList) {
                api.loadBuyerCards(TOKEN,card_id,"+"+Long.toString(card.getPhone()), Long.toString(card.getBarcode()) );
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


    private static  List getCardsListFromExcel() {

        List<Cards> cardList = new ArrayList<Cards>();
        FileInputStream fis = null;

        try {

            fis = new FileInputStream(FILE_PATH);

            // Using XSSF for xlsx format, for xls use HSSF

            Workbook workbook = new XSSFWorkbook(fis);
        int numberOfSheets = workbook.getNumberOfSheets();

            //looping over each workbook sheet

            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                Iterator rowIterator = sheet.iterator();

                //iterating over each row

                while (rowIterator.hasNext()) {
                    Cards card = new Cards();
                   Row row = (org.apache.poi.ss.usermodel.Row) rowIterator.next();
                    Iterator cellIterator = row.cellIterator();


                    //Iterating over each cell (column wise)  in a particular row.

                    while (cellIterator.hasNext()) {
                       Cell cell = (Cell) cellIterator.next();

                        if (cell.getColumnIndex() == 0) {
                            card.setPhone((long) cell.getNumericCellValue());
                        }
                        if (cell.getColumnIndex() == 1) {
                            card.setBarcode((long) cell.getNumericCellValue());
                        }

                    }

                    //end iterating a row, add all the elements of a row in list

                   cardList.add(card);

                }
            }

            fis.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cardList;
    }


}

