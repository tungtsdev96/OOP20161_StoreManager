/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;
import model.NhanVienQL;
import view.FrameQuanLy;
import view.FrameChiTietNhanVien;

/**
 *
 * @author tungb_000
 */
public class QuanLyNhanVien {
    JTable table;
    NhanVienQL nhanVienQL;

    public QuanLyNhanVien(JTable table) {
        this.table = table;
        nhanVienQL = new NhanVienQL();
    }

    public QuanLyNhanVien() {
        nhanVienQL = new NhanVienQL();
    }

    public ArrayList<NhanVien> getDS_NhanVien() {
        return nhanVienQL.getDS_NhanVien();
    }

    public void load_NhanVien() {
        ArrayList<NhanVien> ds_NhanVien = getDS_NhanVien();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (int i = 0; i < ds_NhanVien.size(); i++) {
            model.addRow(new Object[]{i + 1,
                ds_NhanVien.get(i).getMaNhanVien(),
                ds_NhanVien.get(i).getTenNhanVien(),
                ds_NhanVien.get(i).getGioiTinh(), 
                ds_NhanVien.get(i).getNgaySinh(),
                ds_NhanVien.get(i).getChucVu(),
            });
        }
    }

    public void themNhanVien(){
        FrameChiTietNhanVien fr = new FrameChiTietNhanVien(1 ,0 ,this);
        fr.setVisible(true);
    }
    
    public void themNhanVien(NhanVien nv) {
        nhanVienQL.themNhanVien(nv);
        load_NhanVien();
        FrameQuanLy.showThongKeNV(thongKe());
    }
    
    public void suaThongTinNhanVien(int index){
        FrameChiTietNhanVien fr = new FrameChiTietNhanVien(2, index , this);
        fr.setVisible(true);
    }
    
    public void suaThongTinNhanVien(NhanVien nv, int index){
        nhanVienQL.suaThongTin(nv, index);
        load_NhanVien();
    }
    
    public void xoaNhanVien(int index) {
        nhanVienQL.xoaNhanVien(index);
        load_NhanVien();
        FrameQuanLy.showThongKeNV(thongKe());
    }
     
    public void writeNhanVienToFile() {
        nhanVienQL.writeNhanVienToFile();
    }
    
    public ArrayList thongKe(){
        return nhanVienQL.thongKe();
    }
}
