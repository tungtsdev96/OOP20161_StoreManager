/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import FileDB.FileDB;
import java.util.ArrayList;

/**
 *
 * @author tungb_000
 */
public class SanPhamQL {
    private FileDB file;
    
    public SanPhamQL() {
        file = new FileDB();
    }

    public ArrayList<Sach> getDS_Sach(){
        return file.get_Ds_Sach();
    }
    
    public ArrayList<DiaNhac> getDS_DiaNhac(){
        return file.get_Ds_DiaNhac();
    }
    
    public ArrayList<DiaPhim> getDS_DiaPhim(){
        return file.get_Ds_DiaPhim();
    }
    
    public void themSanPham(SanPham sp) {
        if (sp instanceof Sach) {
            Sach s = (Sach) sp;
            FileDB.ds_Sach.add(s);
        } else if (sp instanceof DiaNhac) {
            DiaNhac dn = (DiaNhac) sp;
            FileDB.ds_DiaNhac.add(dn);
        } else if (sp instanceof DiaPhim){
            DiaPhim dp = (DiaPhim) sp;
            FileDB.ds_DiaPhim.add(dp);
        }
        long tongTien = sp.getGiaMua()*sp.getSoLuong();
        FileDB.ds_ThuChi.add(new ThuChi(sp.getMaSanPham()+": " +sp.getTenSanPham(), tongTien, FileDB.getCurrentDate(), "Nhập Kho","Nhập Kho:\n"+"Ngày: "+FileDB.getCurrentDate()+"\nSản Phẩm: "+sp.getTenSanPham()+"\nSố Lượng: "+sp.getSoLuong()));
        file.writeSanPhamToFile();
        file.writeThuChiToFile();
    }

    public void suaThongTin(SanPham sp, int i) {
        if (sp instanceof Sach) {
            Sach s = (Sach) sp;
            FileDB.ds_Sach.get(i).setMaSanPham(s.getMaSanPham());
            FileDB.ds_Sach.get(i).setTenSanPham(s.getTenSanPham());
            FileDB.ds_Sach.get(i).setTacGia(s.getTacGia());
            FileDB.ds_Sach.get(i).setNhaXuatBan(s.getNhaXuatBan());
            FileDB.ds_Sach.get(i).setGiaMua(s.getGiaMua());
            FileDB.ds_Sach.get(i).setGiaBan(s.getGiaBan());
            FileDB.ds_Sach.get(i).setSoLuong(s.getSoLuong());
            FileDB.ds_Sach.get(i).setNhaCungCap(s.getNhaCungCap());
        } else if (sp instanceof DiaNhac) {
            DiaNhac dn = (DiaNhac) sp;
            FileDB.ds_DiaNhac.get(i).setMaSanPham(dn.getMaSanPham());
            FileDB.ds_DiaNhac.get(i).setTenSanPham(dn.getTenSanPham());
            FileDB.ds_DiaNhac.get(i).setTenCaSy(dn.getTenCaSy());
            FileDB.ds_DiaNhac.get(i).setTheLoai(dn.getTheLoai());
            FileDB.ds_DiaNhac.get(i).setGiaMua(dn.getGiaMua());
            FileDB.ds_DiaNhac.get(i).setGiaBan(dn.getGiaBan());
            FileDB.ds_DiaNhac.get(i).setSoLuong(dn.getSoLuong());
            FileDB.ds_DiaNhac.get(i).setNhaCungCap(dn.getNhaCungCap());
        } else {
            DiaPhim dp = (DiaPhim) sp;
            FileDB.ds_DiaPhim.get(i).setMaSanPham(dp.getMaSanPham());
            FileDB.ds_DiaPhim.get(i).setTenSanPham(dp.getTenSanPham());
            FileDB.ds_DiaPhim.get(i).setTenDienVien(dp.getTenDienVien());
            FileDB.ds_DiaPhim.get(i).setTenDaoDien(dp.getTenDaoDien());
            FileDB.ds_DiaPhim.get(i).setGiaMua(dp.getGiaMua());
            FileDB.ds_DiaPhim.get(i).setGiaBan(dp.getGiaBan());
            FileDB.ds_DiaPhim.get(i).setSoLuong(dp.getSoLuong());
            FileDB.ds_DiaPhim.get(i).setNhaCungCap(dp.getNhaCungCap());
        }
        file.writeSanPhamToFile();
    }

    public void xoaSanPham(int i) {
        int sizeSach = getDS_Sach().size();
        int sizeDiaNhac = getDS_DiaNhac().size();
        int sizeDiaPhim = getDS_DiaPhim().size();
        if (i < sizeSach) { // sach
            FileDB.ds_Sach.remove(i);
        } else if (i < sizeSach + sizeDiaNhac) { // dia nhac
            FileDB.ds_DiaNhac.remove(i - sizeSach);
        } else { // dia phim
            FileDB.ds_DiaPhim.remove(i - sizeSach - sizeDiaNhac);
        }
        file.writeSanPhamToFile();
    }

    public void writeSanPhamToFile() {
        file.writeSanPhamToFile();
    }

    public ArrayList thongKe() {
        ArrayList ds = new ArrayList<>();
        int tongSoSP = FileDB.ds_Sach.size() + FileDB.ds_DiaNhac.size() + FileDB.ds_DiaPhim.size();
        ds.add(tongSoSP);
        int sachSize = 0;
        long tongGiaTriSach = 0;
        for (Sach sach : FileDB.ds_Sach){
            sachSize += sach.getSoLuong();
            tongGiaTriSach += sach.getGiaBan()*sach.getSoLuong();
        }
        ds.add(sachSize);
        int diaPhimSize = 0;
        long tongGiaTriDiaPhim = 0;
        for (DiaPhim dp : FileDB.ds_DiaPhim){
            diaPhimSize += dp.getSoLuong();
            tongGiaTriDiaPhim+= dp.getGiaBan()*dp.getSoLuong();
        }
        ds.add(diaPhimSize);
        int diaNhacSize = 0;
        long tongGiaTriDiaNhac = 0;
        for (DiaNhac dn : FileDB.ds_DiaNhac){
            diaNhacSize += dn.getSoLuong();
            tongGiaTriDiaNhac+= dn.getGiaBan()*dn.getSoLuong();
        }
        ds.add(diaNhacSize);
        long tongTien = tongGiaTriDiaNhac + tongGiaTriDiaPhim + tongGiaTriSach;
        ds.add(tongTien);
        return ds;
    }
}
