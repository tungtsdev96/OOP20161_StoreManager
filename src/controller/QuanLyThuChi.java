/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ThuChi;
import model.ThuChiQL;
import view.FrameQuanLy;
import view.FrameThuChi;

/**
 *
 * @author tungb_000
 */
public class QuanLyThuChi {
    
    JTable table;
    ThuChiQL thuChiQL;
    
    public QuanLyThuChi(){
        thuChiQL = new ThuChiQL();
    }
    
    public QuanLyThuChi(JTable table){
        this.table = table;
        thuChiQL = new ThuChiQL();
    }
    
    public ArrayList<ThuChi> get_DS_ThuChi(){
        return thuChiQL.get_Ds_ThuChi();
    }
    
    public void loadThuChi(){
        ArrayList<ThuChi> ds_ThuChi = new ArrayList<>();
        ds_ThuChi = get_DS_ThuChi();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (int i = 0; i < ds_ThuChi.size(); i++) {
            model.addRow(new Object[]{i + 1, ds_ThuChi.get(i).getTenChiPhi(), ds_ThuChi.get(i).getLoaichiphi(), ds_ThuChi.get(i).getMucChiPhi(), ds_ThuChi.get(i).getNgayThang()});
        }
        FrameQuanLy.showTongThuChi();
    }
    
    public void themThuChi(){
        FrameThuChi fr = new FrameThuChi(this,1,"");
        fr.setVisible(true);
    }
    
    public void themThuChi(ThuChi thuchi){
        thuChiQL.themThuChi(thuchi);
        loadThuChi();
        if (QuanLyDangNhap.isQuanLi) FrameQuanLy.showTongThuChi();
    }
    
    public boolean xoaThuChi(int index){
        return thuChiQL.xoaThuChi(index);
    }
    
    public void xemChiTiet(String tenThuChi){
        FrameThuChi fr = new FrameThuChi(this,2,tenThuChi);
        fr.setVisible(true);
    }
    
    public ArrayList<ThuChi> thongKeThuChi(String start,String end) throws ParseException{
        return thuChiQL.thongKeThuChi(start, end);
    }

    public void writeThuChiToFile(){
        thuChiQL.writeThuChiToFile();
    }
}
