package mainTest;

import dal.QrCodeDAO;
import main.QrCodeController;
import model.QrCode;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QrCodeDAOTest {

    @Test
    public void getAllQrCodeTest() throws SQLException, IOException, ClassNotFoundException, AssertionError, ParseException {
        System.out.println("On test le getAll()");

        QrCode qrCodeDataSet = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
        QrCode qrCodeDataSet2 = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c372", "QrCodeCode2", "QrCodeLabel2","11/02/2020","11/02/2020");
        List<QrCode> qrcodeDataSetList = new ArrayList<>();
        qrcodeDataSetList.add(qrCodeDataSet);
        qrcodeDataSetList.add(qrCodeDataSet2);
        QrCodeDAO dao = Mockito.mock(QrCodeDAO.class);
        Mockito.doReturn(qrcodeDataSetList).when(dao).findAll();
        List<QrCode> listResult = dao.findAll();


        Assert.assertTrue(qrcodeDataSetList.size() == listResult.size());
        assertThat(listResult.get(0))
                .extracting("uid","code","description", "validityDate", "startDate")
                .containsExactly("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
        assertThat(listResult.get(1))
                .extracting("uid","code","description", "validityDate", "startDate")
                .containsExactly("3bcd5088-2788-4df9-9059-74ed9363c372", "QrCodeCode2", "QrCodeLabel2","11/02/2020","11/02/2020");
    }

    @Test
    public void createQrCodeTest() throws SQLException, IOException, ClassNotFoundException, ParseException {

        System.out.println("On test le create()");

        QrCode qrCodeDataSet = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020" );
        QrCodeDAO dao = Mockito.mock(QrCodeDAO.class);
        Mockito.doReturn(true).when(dao).create(qrCodeDataSet);
        Boolean result =  dao.create(qrCodeDataSet);

        Assert.assertTrue(result);
    }


    //
    @Test
    public void getByUIDQrCodeTest() throws SQLException, IOException, ClassNotFoundException, ParseException {
        System.out.println("On test le getByUID()");

        QrCode qrCodeDataSet = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
        QrCodeDAO dao = Mockito.mock(QrCodeDAO.class);
        Mockito.doReturn(qrCodeDataSet).when(dao).findByUid(qrCodeDataSet.getCode());
        QrCode qrCodeResult = dao.findByUid(qrCodeDataSet.getCode());

        Assert.assertTrue(qrCodeDataSet.getCode() == qrCodeResult.getCode());
        Assert.assertTrue(qrCodeDataSet.getDescription() == qrCodeResult.getDescription());
        Assert.assertTrue(qrCodeDataSet.getUid() == qrCodeResult.getUid());
        assertThat(qrCodeDataSet)
                .extracting("uid","code","description", "validityDate", "startDate")
                .containsExactly("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
    }


    @Test
    public void updateByUIDTest() throws SQLException, IOException, ClassNotFoundException, ParseException {
        System.out.println("On test le updateByUID()");

        QrCode qrCodeDataSet = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
        QrCode qrCodeDataSetModif = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCodeModif", "QrCodeLabelModif","11/02/2020","11/02/2020");
        QrCodeDAO dao = Mockito.mock(QrCodeDAO.class);
        Mockito.doReturn(true).when(dao).update(qrCodeDataSet);
        dao.update(qrCodeDataSet);

        Assert.assertTrue(qrCodeDataSet.getUid() == qrCodeDataSetModif.getUid());
        Assert.assertFalse(qrCodeDataSet.getDescription() == qrCodeDataSetModif.getDescription());
        Assert.assertFalse(qrCodeDataSet.getCode() == qrCodeDataSetModif.getCode());
    }



    @Test
    public void deleteByUID() throws SQLException, IOException, ClassNotFoundException, ParseException {
        System.out.println("On test le updateByUID()");

        QrCode qrCodeDataSet = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
        QrCodeDAO dao = Mockito.mock(QrCodeDAO.class);
        Mockito.doReturn(true).when(dao).delete(qrCodeDataSet.getUid());
        boolean result = dao.delete(qrCodeDataSet.getUid());
        Assert.assertTrue(result);
    }
}
