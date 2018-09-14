/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.NhanVien;
import view.FrameDangNhap;
import view.FrameNhanVien;
import view.FrameQuanLy;

/**
 *
 * @author tungb_000
 */
public class QuanLyDangNhap {
    
    QuanLyNhanVien qlnv;
    ArrayList<NhanVien> ds_NhanVien;
    public static boolean isQuanLi = false;
    public static boolean isNhanVien = false;
    
    public QuanLyDangNhap(){
        qlnv = new QuanLyNhanVien();
        ds_NhanVien =qlnv.getDS_NhanVien();
    }
    
    public int Login(String username, String password) {
        for (int i = 0; i < ds_NhanVien.size(); i++) {
            if (username.equals(ds_NhanVien.get(i).getTenDangNhap()) && password.equals((ds_NhanVien.get(i).getMatKhau()))) {
                if (ds_NhanVien.get(i).getChucVu().equalsIgnoreCase("Quản Lý") || ds_NhanVien.get(i).getChucVu().equalsIgnoreCase("Giám Đốc")){
                    isQuanLi = true; isNhanVien = false;
                    return 0;
                } else {
                    isQuanLi = false; isNhanVien = true;
                    return 1;
                }
            }
        }
        return 2;
    }
    
    public void login(int type,String user){
        if (type == 1){
            FrameNhanVien fr = new FrameNhanVien(user);
            fr.setVisible(true);
        } else {
            FrameQuanLy fr = new FrameQuanLy();
            fr.setVisible(true);
        }
    }
    
    public void logout(){
        FrameDangNhap fr = new FrameDangNhap();
        fr.setVisible(true);
    }
}
