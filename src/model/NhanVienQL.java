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
public class NhanVienQL {
    private FileDB file;

    public NhanVienQL() {
        file = new FileDB();
    }

    public ArrayList<NhanVien> getDS_NhanVien() {
        return file.get_Ds_NhanVien();
    }

    public void themNhanVien(NhanVien nv) {
        FileDB.ds_NhanVien.add(nv);
        file.writeNhanVienToFile();
    }

    public void suaThongTin(NhanVien nv, int i) {
        FileDB.ds_NhanVien.get(i).setMaNhanVien(nv.getMaNhanVien());
        FileDB.ds_NhanVien.get(i).setTenNhanVien(nv.getTenNhanVien());
        FileDB.ds_NhanVien.get(i).setNgaySinh(nv.getNgaySinh());
        FileDB.ds_NhanVien.get(i).setGioiTinh(nv.getGioiTinh());
        FileDB.ds_NhanVien.get(i).setEmail(nv.getEmail());
        FileDB.ds_NhanVien.get(i).setSoDienThoai(nv.getSoDienThoai());
        FileDB.ds_NhanVien.get(i).setTenDangNhap(nv.getTenDangNhap());
        FileDB.ds_NhanVien.get(i).setMatKhau(nv.getMatKhau());
        FileDB.ds_NhanVien.get(i).setChucVu(nv.getChucVu());
        FileDB.ds_NhanVien.get(i).setDiaChi(nv.getDiaChi());
        FileDB.ds_NhanVien.get(i).setLuongTheoThang(nv.getLuongTheoThang());
        file.writeNhanVienToFile();
    }

    public void xoaNhanVien(int i) {
        FileDB.ds_NhanVien.remove(i);
        file.writeNhanVienToFile();
    }

    public void writeNhanVienToFile() {
        file.writeNhanVienToFile();
    }
    
    public void traLuong(){
        
    }

    public ArrayList thongKe() {
        ArrayList ds = new ArrayList();
        int tongSoNV = FileDB.ds_NhanVien.size();
        long tongLuong = 0;
        int soBaoVe = 0;
        int soBanHang = 0;
        for (NhanVien nv : FileDB.ds_NhanVien){
            tongLuong += nv.getLuongTheoThang();
            if (nv.getChucVu().equalsIgnoreCase("Bảo Vệ")) {
                soBaoVe++;
            } else if (nv.getChucVu().equalsIgnoreCase("Bán Hàng")) {
                soBanHang++;
            } 
        }
        ds.add(tongSoNV);
        ds.add(tongLuong);
        ds.add(soBaoVe);
        ds.add(soBanHang);
        return ds;
    }
}
