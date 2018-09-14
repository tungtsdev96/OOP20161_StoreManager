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
public class ThuChi {
    private String tenChiPhi;
    private long mucChiPhi;
    private String loaichiphi;
    private String ngayThang;
    private String noiDung;

    public ThuChi() {
    }

    public ThuChi(String tenChiPhi, long mucChiPhi, String ngayThang, String loaichiphi,String noiDung) {
        this.tenChiPhi = tenChiPhi;
        this.mucChiPhi = mucChiPhi;
        this.ngayThang = ngayThang;
        this.loaichiphi = loaichiphi;
        this.noiDung = noiDung;
    }

    public String getTenChiPhi() {
        return tenChiPhi;
    }

    public void setTenChiPhi(String tenChiPhi) {
        this.tenChiPhi = tenChiPhi;
    }

    public long getMucChiPhi() {
        return mucChiPhi;
    }

    public void setMucChiPhi(long mucChiPhi) {
        this.mucChiPhi = mucChiPhi;
    }

    public String getLoaichiphi() {
        return loaichiphi;
    }

    public void setLoaichiphi(String loaichiphi) {
        this.loaichiphi = loaichiphi;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }
}
