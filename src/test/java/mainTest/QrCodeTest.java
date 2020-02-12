package mainTest;


import model.QrCode;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.Date;

public class QrCodeTest {



    @Test
    public void QrCodeGet() throws ParseException {
        System.out.println("On test les getter");

        QrCode qrCode = Mockito.mock(QrCode.class);
        QrCode qrCodeDataSet = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");

        Mockito.doReturn(qrCodeDataSet.getUid()).when(qrCode).getUid();
        Mockito.doReturn(qrCodeDataSet.getCode()).when(qrCode).getCode();
        Mockito.doReturn(qrCodeDataSet.getDescription()).when(qrCode).getDescription();

        Assert.assertEquals(qrCodeDataSet.getUid(), qrCode.getUid());
        Assert.assertEquals(qrCodeDataSet.getCode(), qrCode.getCode());
        Assert.assertEquals(qrCodeDataSet.getDescription(), qrCode.getDescription());
    }

    @Test
    public void QrCodeSet() throws ParseException {
        System.out.println("On test les setter");

        QrCode qrCodeDataSet = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");
        QrCode qrCodeResult = new QrCode();

        qrCodeResult.setUid(qrCodeDataSet.getUid());
        qrCodeResult.setCode(qrCodeDataSet.getCode());
        qrCodeResult.setDescription(qrCodeDataSet.getDescription());

        Assert.assertEquals(qrCodeDataSet.getUid(), qrCodeResult.getUid());
        Assert.assertEquals(qrCodeDataSet.getCode(), qrCodeResult.getCode());
        Assert.assertEquals(qrCodeDataSet.getDescription(), qrCodeResult.getDescription());
    }

    @Test
    public void QrCodeControllerTest() throws ParseException {
        System.out.println("On test les constructeurs");

        QrCode qrCode = new QrCode("3bcd5088-2788-4df9-9059-74ed9363c377", "QrCodeCode", "QrCodeLabel","11/02/2020","11/02/2020");

        Assert.assertEquals(qrCode.getUid(), "3bcd5088-2788-4df9-9059-74ed9363c377");
        Assert.assertEquals(qrCode.getCode(), "QrCodeCode");
        Assert.assertEquals(qrCode.getDescription(), "QrCodeLabel");
    }
}
