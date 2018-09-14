/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import FileDB.FileDB;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tungb_000
 */
public class ThuChiQL {
    
    FileDB file;
    
    public ThuChiQL(){
        file = new FileDB();
    }
    
    public ArrayList<ThuChi> get_Ds_ThuChi(){
        return file.get_Ds_ThuChi();
    }
    
    public void themThuChi(ThuChi thuchi){
        FileDB.ds_ThuChi.add(thuchi);
        file.writeThuChiToFile();
    }
    
    public boolean xoaThuChi(int index){
        if (FileDB.ds_ThuChi.get(index).getLoaichiphi().equalsIgnoreCase("Hóa Đơn") || FileDB.ds_ThuChi.get(index).getLoaichiphi().equalsIgnoreCase("Nhập Kho") 
                    || FileDB.ds_ThuChi.get(index).getLoaichiphi().equalsIgnoreCase("Trả Lương")){
            return false;
        } else {
            FileDB.ds_ThuChi.remove(index);
            file.writeThuChiToFile();
            return true;
        }
    }
    
    public void writeThuChiToFile(){
        file.writeThuChiToFile();
    }
    
    public ArrayList<ThuChi> thongKeThuChi(String start,String end) throws ParseException{
        ArrayList<ThuChi> ds_ThuChi = new ArrayList<>();
        ArrayList<ThuChi> ds_ThuChiThongKe = new ArrayList<>();
        ds_ThuChi = get_Ds_ThuChi();
        for (int i = 0 ;i<ds_ThuChi.size();i++){
            ThuChi tc = ds_ThuChi.get(i);
            SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
            Date tc_date = date_format.parse(tc.getNgayThang());
            Date dateStart = date_format.parse(start);
            Date dateEnd = date_format.parse(end);
            if (tc_date.compareTo(dateStart) >=0 && tc_date.compareTo(dateEnd)<=0 ){
                ds_ThuChiThongKe.add(tc);
            }
        }
        return ds_ThuChiThongKe;
    }
    
}
