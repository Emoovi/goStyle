package mainTest;

import main.QrCodeService;
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

public class QrCodeServiceTest {

    @Test
    public void getAllQrCodeTest() throws SQLException, IOException, ClassNotFoundException, AssertionError, ParseException {
        System.out.println("On test le getAll()");
        QrCode qrCodeDataSet = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
        QrCode qrCodeDataSet2 = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c372", "QrCodeCode2", "QrCodeLabel2","11/02/2020","11/02/2020");
        List<QrCode> qrcodeDataSetList = new ArrayList<>();
        qrcodeDataSetList.add(qrCodeDataSet);
        qrcodeDataSetList.add(qrCodeDataSet2);
        QrCodeService service = Mockito.mock(QrCodeService.class);
        Mockito.doReturn(qrcodeDataSetList).when(service).getAllQrCode();
        List<QrCode> listResult = service.getAllQrCode();


        Assert.assertEquals(qrcodeDataSetList.size(), listResult.size());
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
        QrCode qrCodeDataSet = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
        QrCodeService service = Mockito.mock(QrCodeService.class);
        Mockito.doReturn(true).when(service).createQrCode(qrCodeDataSet);
        boolean result =  service.createQrCode(qrCodeDataSet);

        Assert.assertTrue(result);
    }


    //
    @Test
    public void getByUIDQrCodeTest() throws SQLException, IOException, ClassNotFoundException, ParseException {
        System.out.println("On test le getByUID()");
        QrCode qrCodeDataSet = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
        QrCodeService service = Mockito.mock(QrCodeService.class);
        Mockito.doReturn(qrCodeDataSet).when(service).findByUidQrCode(qrCodeDataSet.getCode());
        QrCode qrCodeResult = service.findByUidQrCode(qrCodeDataSet.getCode());

        Assert.assertSame(qrCodeDataSet.getCode(), qrCodeResult.getCode());
        Assert.assertSame(qrCodeDataSet.getDescription(), qrCodeResult.getDescription());
        Assert.assertSame(qrCodeDataSet.getUid(), qrCodeResult.getUid());
        assertThat(qrCodeDataSet)
                .extracting("uid","code","description", "validityDate", "startDate")
                .containsExactly("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
    }


    @Test
    public void updateByUIDTest() throws SQLException, IOException, ClassNotFoundException, ParseException {
        System.out.println("On test le updateByUID()");
        QrCode qrCodeDataSet = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
        QrCode qrCodeDataSetModif = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCodeModif", "QrCodeLabelModif","11/02/2020","11/02/2020");
        QrCodeService service = Mockito.mock(QrCodeService.class);
        Mockito.doReturn(qrCodeDataSetModif).when(service).updateByUID(qrCodeDataSet);
        QrCode qrCodeResult = service.updateByUID(qrCodeDataSet);

        Assert.assertSame(qrCodeResult.getUid(), qrCodeDataSetModif.getUid());
        Assert.assertSame(qrCodeResult.getDescription(), qrCodeDataSetModif.getDescription());
        Assert.assertSame(qrCodeResult.getCode(), qrCodeDataSetModif.getCode());
    }



    @Test
    public void deleteByUID() throws SQLException, IOException, ClassNotFoundException, ParseException {
        System.out.println("On test le updateByUID()");
        QrCode qrCodeDataSet = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
        QrCodeService service = Mockito.mock(QrCodeService.class);
        Mockito.doReturn(qrCodeDataSet.getUid()).when(service).deleteByUID(qrCodeDataSet.getUid());
        String result = service.deleteByUID(qrCodeDataSet.getUid());
        Assert.assertSame(result, qrCodeDataSet.getUid());
    }

}
