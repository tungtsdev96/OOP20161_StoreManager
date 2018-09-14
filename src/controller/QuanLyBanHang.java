/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.HoaDonQL;
import model.SanPham;
import view.FrameChiTietHoaDon;

/**
 *
 * @author tungb_000
 */


public class QuanLyBanHang {

    HoaDonQL hoadonQL;
    JTable table;
    
    public QuanLyBanHang(){
        hoadonQL = new HoaDonQL();
    }
    
    public QuanLyBanHang(JTable table){
        hoadonQL = new HoaDonQL();
        this.table = table;
    }
    
    public void loadDanhSachHoaDon(){
        ArrayList<HoaDon> ds_HoaDon = new ArrayList<>();
        ds_HoaDon = getDsHoaDon();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (int i = 0;i<ds_HoaDon.size();i++){
            model.addRow(new Object[]{i+1,
                ds_HoaDon.get(i).getMaHoaDon(),
                ds_HoaDon.get(i).getTongTien(),
                ds_HoaDon.get(i).getNgay(),
            });
        }
        table.setModel(model);
    }
    
    public ArrayList<HoaDon> getDsHoaDon(){
        return hoadonQL.get_Ds_HoaDon();
    }
    
    public void taoHoaDon(HoaDon hd) throws IOException{
        hoadonQL.themHoaDon(hd);
    }    
    
//    public void writeHoaDonToFile(){
//        hoadonQL.writeHoaDonToFile();
//    }

    public void xoaHoaDon(int index) throws IOException {
        hoadonQL.xoaHoaDon(index);
        loadDanhSachHoaDon();
    }

    public void xemChiTiet(int index) {
        FrameChiTietHoaDon fr = new FrameChiTietHoaDon(index);
        fr.setVisible(true);
    }
}
