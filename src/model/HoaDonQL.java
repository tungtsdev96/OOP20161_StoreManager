/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import FileDB.FileDB;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tungb_000
 */
public class HoaDonQL {
    
    FileDB file;
    
    public HoaDonQL(){
        file = new FileDB();
    }
    
    public ArrayList<HoaDon> get_Ds_HoaDon(){
        return FileDB.ds_HoaDon;
    }
    
    public void xoaHoaDon(int index) throws IOException{
        FileDB.ds_HoaDon.remove(index);
        file.updatedanhSachHoaDon();
    }

    public void themHoaDon(HoaDon hd) throws IOException {
        FileDB.ds_HoaDon.add(hd);
        String noiDung = "";
        for (int i= 0;i<hd.getDs_SanPham().size();i++){
            SanPham sp = hd.getDs_SanPham().get(i);
            noiDung+= "Sản Phẩm: "+sp.getTenSanPham() + "\nSố Lượng: " +sp.getSoLuong()+"\n";
        }
        FileDB.ds_ThuChi.add(new ThuChi(hd.getMaHoaDon(), hd.getTongTien(), FileDB.getCurrentDate(), "Hóa Đơn", noiDung));
        file.writeHoaDonToFile(hd);
        file.writeThuChiToFile();
    }
    
//    public void writeHoaDonToFile() {
//        file.writeHoaDonToFile();
//    }
}
