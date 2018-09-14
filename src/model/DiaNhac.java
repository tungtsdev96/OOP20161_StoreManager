/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author tungb_000
 */
public class DiaNhac extends SanPham{
    private String tenCaSy;
    private String theLoai;

    public DiaNhac(String maSanPham, String tenSanPham, int giaMua, int giaBan, String nhaCungCap,int soLuong,String tenCaSy, String theLoai) {
        super(maSanPham, tenSanPham, giaMua, giaBan, nhaCungCap, soLuong);
        this.tenCaSy = tenCaSy;
        this.theLoai = theLoai;
    }
    
    public String getTenCaSy() {
        return tenCaSy;
    }

    public void setTenCaSy(String tenCaSy) {
        this.tenCaSy = tenCaSy;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }
    
    
}
