package main;


import model.QrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @CrossOrigin
    @GetMapping("/qrcodes")
    public List<QrCode> getAllQrCode() throws SQLException, IOException, ClassNotFoundException {
        return qrCodeService.getAllQrCode();
    }

    @CrossOrigin
    @PostMapping("/qrcodes")
    public boolean createQrCode(@RequestBody QrCode qrcode) throws SQLException, IOException, ClassNotFoundException, ParseException {

        return qrCodeService.createQrCode(qrcode);
    }

    @CrossOrigin
    @GetMapping("/qrcode/{uid}")
    public QrCode getByUIDQrCode(@PathVariable String uid) throws SQLException, IOException, ClassNotFoundException {
        return qrCodeService.findByUidQrCode(uid);
    }

    @CrossOrigin
    @PutMapping("/qrcode")
    public QrCode updateByUID(@RequestBody QrCode qrCode) throws SQLException, IOException, ClassNotFoundException, ParseException {
        return qrCodeService.updateByUID(qrCode);
    }


    @CrossOrigin
    @DeleteMapping("/qrcode/{uid}")
        public String deleteByUID(@PathVariable String uid) throws SQLException, IOException, ClassNotFoundException {
        return qrCodeService.deleteByUID(uid);
    }

}
