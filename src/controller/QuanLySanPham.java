/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.DiaNhac;
import model.DiaPhim;
import model.Sach;
import model.SanPham;
import model.SanPhamQL;
import view.FrameChiTietSanPham;
import view.FrameNhanVien;
import view.FrameQuanLy;

/**
 *
 * @author tungb_000
 */
public class QuanLySanPham {

    JTable table;
    SanPhamQL sanPhamQL;
    QuanLyThuChi qlThuChi;
    
    public QuanLySanPham() {
        sanPhamQL = new SanPhamQL();
    }

    public QuanLySanPham(JTable table) {
        this.table = table;
        sanPhamQL = new SanPhamQL();
    }
    
    public ArrayList<Sach> getDS_Sach(){
        return sanPhamQL.getDS_Sach();
    }
    
    public ArrayList<DiaNhac> getDS_DiaNhac(){
        return sanPhamQL.getDS_DiaNhac();
    }
    
    public ArrayList<DiaPhim> getDS_DiaPhim(){
        return sanPhamQL.getDS_DiaPhim();
    }
    
    public void load_SanPham() {
        ArrayList<Sach> ds_Sach = getDS_Sach();
        ArrayList<DiaNhac> ds_DiaNhac = getDS_DiaNhac();
        ArrayList<DiaPhim> ds_DiaPhim = getDS_DiaPhim();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (int i = 0; i < ds_Sach.size(); i++) {
            model.addRow(new Object[]{i + 1, ds_Sach.get(i).getMaSanPham(), ds_Sach.get(i).getTenSanPham(), "Sach", ds_Sach.get(i).getGiaMua(), ds_Sach.get(i).getGiaBan(), ds_Sach.get(i).getSoLuong()});
        }
        for (int i = 0; i < ds_DiaNhac.size(); i++) {
            model.addRow(new Object[]{i + 1 + ds_Sach.size(), ds_DiaNhac.get(i).getMaSanPham(), ds_DiaNhac.get(i).getTenSanPham(), "Đĩa Nhạc", ds_DiaNhac.get(i).getGiaMua(), ds_DiaNhac.get(i).getGiaBan(), ds_DiaNhac.get(i).getSoLuong()});
        }
        for (int i = 0; i < ds_DiaPhim.size(); i++) {
            model.addRow(new Object[]{i + 1 + ds_Sach.size() + ds_DiaNhac.size(), ds_DiaPhim.get(i).getMaSanPham(), ds_DiaPhim.get(i).getTenSanPham(), "Đĩa Phim", ds_DiaPhim.get(i).getGiaMua(), ds_DiaPhim.get(i).getGiaBan(), ds_DiaPhim.get(i).getSoLuong()});
        }
    }

    public void themSanPham() {
        //them type = 1
        FrameChiTietSanPham fr = new FrameChiTietSanPham(this, 0, 0);
        fr.setVisible(true);
    }

    public void themSanPham(SanPham sp) {
        sanPhamQL.themSanPham(sp);
        load_SanPham();
        if (QuanLyDangNhap.isQuanLi) FrameQuanLy.showThongKeSP(thongKe());
        if (QuanLyDangNhap.isNhanVien) FrameNhanVien.showThongKeSP(thongKe());
    }

    public void suaThongTin(int i) {
        //sua type = 2
        int sizeSach = getDS_Sach().size();
        int sizeDiaNhac = getDS_DiaNhac().size();
        int sizeDiaPhim = getDS_DiaPhim().size();
        if (i < sizeSach) { //sach
            FrameChiTietSanPham fr = new FrameChiTietSanPham(this, i, 1);
            fr.setVisible(true);
        } else if (i < sizeSach + sizeDiaNhac) { //dia nhac
            FrameChiTietSanPham fr = new FrameChiTietSanPham(this, i - sizeSach, 2);
            fr.setVisible(true);
        } else { // dia phim
            FrameChiTietSanPham fr = new FrameChiTietSanPham(this, i - sizeSach - sizeDiaNhac, 3);
            fr.setVisible(true);
        }
    }

    public void suaThongTin(SanPham sp, int i) {
        sanPhamQL.suaThongTin(sp, i);
        load_SanPham();
        if (QuanLyDangNhap.isQuanLi) FrameQuanLy.showThongKeSP(thongKe());
        if (QuanLyDangNhap.isNhanVien) FrameNhanVien.showThongKeSP(thongKe());
    }

    public void xoaSanPham(int i) {
        sanPhamQL.xoaSanPham(i);
        load_SanPham();
        if (QuanLyDangNhap.isQuanLi) FrameQuanLy.showThongKeSP(thongKe());
        if (QuanLyDangNhap.isNhanVien) FrameNhanVien.showThongKeSP(thongKe());
    }

//    public void timKiemTheoLoaiSanPham(int type){
//        ArrayList<Sach> ds_Sach = getDS_Sach();
//        ArrayList<DiaNhac> ds_DiaNhac = getDS_DiaNhac();
//        ArrayList<DiaPhim> ds_DiaPhim = getDS_DiaPhim();
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        model.setRowCount(0);
//        if (type == 0) {
//            load_SanPham();
//        } else if (type == 1){
//            for (int i = 0; i < ds_Sach.size(); i++) {
//            model.addRow(new Object[]{i + 1, ds_Sach.get(i).getMaSanPham(), ds_Sach.get(i).getTenSanPham(), "Sach", ds_Sach.get(i).getGiaMua(), ds_Sach.get(i).getGiaBan(), ds_Sach.get(i).getSoLuong()});
//            }
//        } else if (type == 2){
//            for (int i = 0; i < ds_DiaNhac.size(); i++) {
//            model.addRow(new Object[]{i + 1 , ds_DiaNhac.get(i).getMaSanPham(), ds_DiaNhac.get(i).getTenSanPham(), "Đĩa Nhạc", ds_DiaNhac.get(i).getGiaMua(), ds_DiaNhac.get(i).getGiaBan(), ds_DiaNhac.get(i).getSoLuong()});
//            }
//        } else {
//            for (int i = 0; i < ds_DiaPhim.size(); i++) {
//            model.addRow(new Object[]{i + 1 , ds_DiaPhim.get(i).getMaSanPham(), ds_DiaPhim.get(i).getTenSanPham(), "Đĩa Phim", ds_DiaPhim.get(i).getGiaMua(), ds_DiaPhim.get(i).getGiaBan(), ds_DiaPhim.get(i).getSoLuong()});
//            }
//        }
//    }

    public void writeSanPhamToFile() {
        sanPhamQL.writeSanPhamToFile();
    }
    
    public ArrayList thongKe(){
        return sanPhamQL.thongKe();
    }

}
