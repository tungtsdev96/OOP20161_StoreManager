/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template FileDB, choose Tools | Templates
 * and open the template in the editor.
 */
package FileDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.DiaNhac;
import model.DiaPhim;
import model.HoaDon;
import model.NhanVien;
import model.Sach;
import model.SanPham;
import model.ThuChi;

/**
 *
 * @author tungb_000
 */
public class FileDB {

    //public static ArrayList<SanPham> ds_SanPham = new ArrayList<SanPham>();
    public static ArrayList<NhanVien> ds_NhanVien = new ArrayList<>(); //type = 0
    public static ArrayList<DiaNhac> ds_DiaNhac = new ArrayList<>(); //type = 1
    public static ArrayList<DiaPhim> ds_DiaPhim = new ArrayList<>(); // type = 2
    public static ArrayList<Sach> ds_Sach = new ArrayList<>(); // type = 3
    public static ArrayList<HoaDon> ds_HoaDon = new ArrayList<>();
    public static ArrayList<ThuChi> ds_ThuChi = new ArrayList<>();

    Workbook workbook, workbook1, workbook2, workbook3;
    WritableWorkbook workbook_To_Write, workbook_To_Write1, workbook_To_Write2, workbook_To_Write3;
    File file, file1, file2, file3;

    public FileDB() {
        try {
            file = new File("Danh Sach San Pham.xls");
            workbook = Workbook.getWorkbook(file);
            getDanhSachSanPham();
        } catch (Exception e) {
        }
//        } catch (IOException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BiffException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            file1 = new File("Danh Sách Nhân Viên.xls");
            workbook1 = Workbook.getWorkbook(file1);
            getDanhSachNhanVien();
        } catch (Exception e) {
        }
//        } catch (IOException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BiffException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            file2 = new File("Danh Sách Thu Chi.xls");
            workbook2 = Workbook.getWorkbook(file2);
            getDanhSachThuChi();
        } catch (Exception e) {
        }
//        } catch (IOException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BiffException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            file3 = new File("Danh Sách Hóa Đơn.txt");
            getDanhSachHoaDon();
//            for (int i= 0;i<ds_HoaDon.size();i++){
//                System.out.println(ds_HoaDon.get(i).getDs_SanPham().size());
//            }
        } catch (Exception e) {
        }
//        } catch (IOException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BiffException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public void writeSanPhamToFile() {
        try {
            file = new File("Danh Sach San Pham.xls");
            if (file.exists()) {
                file.delete();
            }
            workbook_To_Write = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook_To_Write.createSheet("Sách", 0);
            sheet1.mergeCells(0, 0, 8, 0);
            sheet1.addCell(new Label(0, 0, "Danh Sách Sách", Cell(sheet1, Alignment.CENTRE)));
            sheet1.addCell(new Label(0, 1, "STT", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(1, 1, "Mã Sách", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(2, 1, "Tên Sách", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(3, 1, "Tác Giả", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(4, 1, "Nhà Xuất Bản", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(5, 1, "Giá mua", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(6, 1, "Giá Bán", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(7, 1, "Nhà Cung Cấp", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(8, 1, "Số Lượng", Cell(sheet1, Alignment.CENTRE)));

            for (int i = 0; i < ds_Sach.size(); i++) {
                sheet1.addCell(new jxl.write.Number(0, i + 2, i + 1, Cell(sheet1, Alignment.CENTRE)));
                sheet1.addCell(new Label(1, i + 2, ds_Sach.get(i).getMaSanPham(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(2, i + 2, ds_Sach.get(i).getTenSanPham(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(3, i + 2, ds_Sach.get(i).getTacGia(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(4, i + 2, ds_Sach.get(i).getNhaXuatBan(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new jxl.write.Number(5, i + 2, ds_Sach.get(i).getGiaMua(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new jxl.write.Number(6, i + 2, ds_Sach.get(i).getGiaBan(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(7, i + 2, ds_Sach.get(i).getNhaCungCap(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new jxl.write.Number(8, i + 2, ds_Sach.get(i).getSoLuong(), Cell(sheet1, Alignment.CENTRE)));
            }

            WritableSheet sheet2 = workbook_To_Write.createSheet("Đĩa Nhạc", 1);
            sheet2.mergeCells(0, 0, 8, 0);
            sheet2.addCell(new Label(0, 0, "Danh Sách Đĩa Nhạc", Cell(sheet2, Alignment.CENTRE)));
            sheet2.addCell(new Label(0, 1, "STT", Cell(sheet2, jxl.format.Alignment.CENTRE)));
            sheet2.addCell(new Label(1, 1, "Mã Đĩa Nhạc", Cell(sheet2, jxl.format.Alignment.CENTRE)));
            sheet2.addCell(new Label(2, 1, "Tên Đĩa Nhạc", Cell(sheet2, jxl.format.Alignment.CENTRE)));
            sheet2.addCell(new Label(3, 1, "Tên Ca Sỹ", Cell(sheet2, jxl.format.Alignment.CENTRE)));
            sheet2.addCell(new Label(4, 1, "Thể Loại", Cell(sheet2, jxl.format.Alignment.CENTRE)));
            sheet2.addCell(new Label(5, 1, "Giá mua", Cell(sheet2, jxl.format.Alignment.CENTRE)));
            sheet2.addCell(new Label(6, 1, "Giá Bán", Cell(sheet2, jxl.format.Alignment.CENTRE)));
            sheet2.addCell(new Label(7, 1, "Nhà Cung Cấp", Cell(sheet2, jxl.format.Alignment.CENTRE)));
            sheet2.addCell(new Label(8, 1, "Số Lượng", Cell(sheet1, Alignment.CENTRE)));

            for (int i = 0; i < ds_DiaNhac.size(); i++) {
                sheet2.addCell(new jxl.write.Number(0, i + 2, i + 1, Cell(sheet2, Alignment.CENTRE)));
                sheet2.addCell(new Label(1, i + 2, ds_DiaNhac.get(i).getMaSanPham(), Cell(sheet2, Alignment.LEFT)));
                sheet2.addCell(new Label(2, i + 2, ds_DiaNhac.get(i).getTenSanPham(), Cell(sheet2, Alignment.LEFT)));
                sheet2.addCell(new Label(3, i + 2, ds_DiaNhac.get(i).getTenCaSy(), Cell(sheet2, Alignment.LEFT)));
                sheet2.addCell(new Label(4, i + 2, ds_DiaNhac.get(i).getTheLoai(), Cell(sheet2, Alignment.LEFT)));
                sheet2.addCell(new jxl.write.Number(5, i + 2, ds_DiaNhac.get(i).getGiaMua(), Cell(sheet2, Alignment.LEFT)));
                sheet2.addCell(new jxl.write.Number(6, i + 2, ds_DiaNhac.get(i).getGiaBan(), Cell(sheet2, Alignment.LEFT)));
                sheet2.addCell(new Label(7, i + 2, ds_DiaNhac.get(i).getNhaCungCap(), Cell(sheet2, Alignment.LEFT)));
                sheet2.addCell(new jxl.write.Number(8, i + 2, ds_DiaNhac.get(i).getSoLuong(), Cell(sheet1, Alignment.CENTRE)));
            }

            WritableSheet sheet3 = workbook_To_Write.createSheet("Đĩa Phim", 2);
            sheet3.mergeCells(0, 0, 8, 0);
            sheet3.addCell(new Label(0, 0, "Danh Sách Đĩa Phim", Cell(sheet3, Alignment.CENTRE)));
            sheet3.addCell(new Label(0, 1, "STT", Cell(sheet3, jxl.format.Alignment.CENTRE)));
            sheet3.addCell(new Label(1, 1, "Mã Đĩa Phim", Cell(sheet3, jxl.format.Alignment.CENTRE)));
            sheet3.addCell(new Label(2, 1, "Tên Đĩa Phim", Cell(sheet3, jxl.format.Alignment.CENTRE)));
            sheet3.addCell(new Label(3, 1, "Tên Diễn Viên", Cell(sheet3, jxl.format.Alignment.CENTRE)));
            sheet3.addCell(new Label(4, 1, "Thể Loại", Cell(sheet3, jxl.format.Alignment.CENTRE)));
            sheet3.addCell(new Label(5, 1, "Giá mua", Cell(sheet3, jxl.format.Alignment.CENTRE)));
            sheet3.addCell(new Label(6, 1, "Giá Bán", Cell(sheet3, jxl.format.Alignment.CENTRE)));
            sheet3.addCell(new Label(7, 1, "Nhà Cung Cấp", Cell(sheet3, jxl.format.Alignment.CENTRE)));
            sheet3.addCell(new Label(8, 1, "Số Lượng", Cell(sheet1, Alignment.CENTRE)));

            for (int i = 0; i < ds_DiaPhim.size(); i++) {
                sheet3.addCell(new jxl.write.Number(0, i + 2, i + 1, Cell(sheet3, Alignment.CENTRE)));
                sheet3.addCell(new Label(1, i + 2, ds_DiaPhim.get(i).getMaSanPham(), Cell(sheet3, Alignment.LEFT)));
                sheet3.addCell(new Label(2, i + 2, ds_DiaPhim.get(i).getTenSanPham(), Cell(sheet3, Alignment.LEFT)));
                sheet3.addCell(new Label(3, i + 2, ds_DiaPhim.get(i).getTenDienVien(), Cell(sheet3, Alignment.LEFT)));
                sheet3.addCell(new Label(4, i + 2, ds_DiaPhim.get(i).getTenDaoDien(), Cell(sheet3, Alignment.LEFT)));
                sheet3.addCell(new jxl.write.Number(5, i + 2, ds_DiaPhim.get(i).getGiaMua(), Cell(sheet3, Alignment.LEFT)));
                sheet3.addCell(new jxl.write.Number(6, i + 2, ds_DiaPhim.get(i).getGiaBan(), Cell(sheet3, Alignment.LEFT)));
                sheet3.addCell(new Label(7, i + 2, ds_DiaPhim.get(i).getNhaCungCap(), Cell(sheet3, Alignment.LEFT)));
                sheet3.addCell(new jxl.write.Number(8, i + 2, ds_DiaPhim.get(i).getSoLuong(), Cell(sheet1, Alignment.CENTRE)));
            }
            workbook_To_Write.write();
            workbook_To_Write.close();
        } catch (IOException ex) {
            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getDanhSachSanPham() {
        try {
            ds_Sach.clear();
            Sheet sheet1 = workbook.getSheet(0);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            for (int row = 2; row < rows; row++) {
                Sach s = new Sach(sheet1.getCell(1, row).getContents(), sheet1.getCell(2, row).getContents(),
                        Integer.parseInt(sheet1.getCell(5, row).getContents()), Integer.parseInt(sheet1.getCell(6, row).getContents()),
                        sheet1.getCell(7, row).getContents(), Integer.parseInt(sheet1.getCell(8, row).getContents()),
                        sheet1.getCell(3, row).getContents(), sheet1.getCell(4, row).getContents());
                ds_Sach.add(s);
            }
        } catch (Exception e) {
        }
        try {
            ds_DiaNhac.clear();
            Sheet sheet1 = workbook.getSheet(1);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            for (int row = 2; row < rows; row++) {
                DiaNhac s = new DiaNhac(sheet1.getCell(1, row).getContents(), sheet1.getCell(2, row).getContents(),
                        Integer.parseInt(sheet1.getCell(5, row).getContents()), Integer.parseInt(sheet1.getCell(6, row).getContents()),
                        sheet1.getCell(7, row).getContents(), Integer.parseInt(sheet1.getCell(8, row).getContents()),
                        sheet1.getCell(3, row).getContents(), sheet1.getCell(4, row).getContents());
                ds_DiaNhac.add(s);
            }
        } catch (Exception e) {
        }
        try {
            ds_DiaPhim.clear();
            Sheet sheet1 = workbook.getSheet(2);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            for (int row = 2; row < rows; row++) {
                DiaPhim s = new DiaPhim(sheet1.getCell(1, row).getContents(), sheet1.getCell(2, row).getContents(),
                        Integer.parseInt(sheet1.getCell(5, row).getContents()), Integer.parseInt(sheet1.getCell(6, row).getContents()),
                        sheet1.getCell(7, row).getContents(), Integer.parseInt(sheet1.getCell(8, row).getContents()),
                        sheet1.getCell(3, row).getContents(), sheet1.getCell(4, row).getContents());
                ds_DiaPhim.add(s);
            }
        } catch (Exception e) {
        }
    }

    public void writeNhanVienToFile() {
        file1 = new File("Danh Sách Nhân Viên.xls");
        if (file1.exists()) {
            file1.delete();
        }
        try {
            workbook_To_Write1 = Workbook.createWorkbook(file1);
            WritableSheet sheet1 = workbook_To_Write1.createSheet("Nhân Viên", 0);
            sheet1.mergeCells(0, 0, 11, 0);
            sheet1.addCell(new Label(0, 0, "Danh Sách Nhân Viên", Cell(sheet1, Alignment.CENTRE)));
//            for (int i = 1;i<12;i++){
//                CellView cell = sheet1.getColumnView(i);
//                cell.setAutosize(true);
//                sheet1.setColumnView(i, cell);
//            }
            sheet1.addCell(new Label(0, 1, "STT", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(1, 1, "Mã Nhân Viên", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(2, 1, "Tên Nhân Viên", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(3, 1, "Giới Tính", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(4, 1, "Ngày Sinh", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(5, 1, "Địa Chỉ", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(6, 1, "Chức Vụ", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(7, 1, "Email", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(8, 1, "Số Điện Thoại", Cell(sheet1, Alignment.CENTRE)));
            sheet1.addCell(new Label(9, 1, "Tên Đăng Nhập", Cell(sheet1, Alignment.CENTRE)));
            sheet1.addCell(new Label(10, 1, "Mật Khẩu", Cell(sheet1, Alignment.CENTRE)));
            sheet1.addCell(new Label(11, 1, "Lương", Cell(sheet1, Alignment.CENTRE)));

            for (int i = 0; i < ds_NhanVien.size(); i++) {
                sheet1.addCell(new jxl.write.Number(0, i + 2, i + 1, Cell(sheet1, Alignment.CENTRE)));
                sheet1.addCell(new Label(1, i + 2, ds_NhanVien.get(i).getMaNhanVien(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(2, i + 2, ds_NhanVien.get(i).getTenNhanVien(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(3, i + 2, ds_NhanVien.get(i).getGioiTinh(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(4, i + 2, ds_NhanVien.get(i).getNgaySinh(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(5, i + 2, ds_NhanVien.get(i).getDiaChi(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(6, i + 2, ds_NhanVien.get(i).getChucVu(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(7, i + 2, ds_NhanVien.get(i).getEmail(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(8, i + 2, ds_NhanVien.get(i).getSoDienThoai(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(9, i + 2, ds_NhanVien.get(i).getTenDangNhap(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(10, i + 2, ds_NhanVien.get(i).getMatKhau(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new jxl.write.Number(11, i + 2, ds_NhanVien.get(i).getLuongTheoThang(), Cell(sheet1, Alignment.LEFT)));
            }
            workbook_To_Write1.write();
            workbook_To_Write1.close();
        } catch (IOException ex) {
            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getDanhSachNhanVien() {
        try {
            ds_NhanVien.clear();
            Sheet sheet1 = workbook1.getSheet(0);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            for (int row = 2; row < rows; row++) {
                String maNhanVien = sheet1.getCell(1, row).getContents();
                String tenNhanVien = sheet1.getCell(2, row).getContents();
                String gioiTinh = sheet1.getCell(3, row).getContents();
                String ngaySinh = sheet1.getCell(4, row).getContents();
                String diaChi = sheet1.getCell(5, row).getContents();
                String chucVu = sheet1.getCell(6, row).getContents();
                String email = sheet1.getCell(7, row).getContents();
                String soDienThoai = sheet1.getCell(8, row).getContents();
                String tenDangNhap = sheet1.getCell(9, row).getContents();
                String matKhau = sheet1.getCell(10, row).getContents();
                int luongTheoThang = Integer.parseInt(sheet1.getCell(11, row).getContents());
                NhanVien nv = new NhanVien(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, diaChi, chucVu, email, soDienThoai, tenDangNhap, matKhau, luongTheoThang);
                ds_NhanVien.add(nv);
            }
        } catch (Exception e) {
        }
    }

    public void writeThuChiToFile() {
        file2 = new File("Danh Sách Thu Chi.xls");
        if (file2.exists()) {
            file2.delete();
        }
        try {
            workbook_To_Write2 = Workbook.createWorkbook(file2);
            WritableSheet sheet2 = workbook_To_Write2.createSheet("Danh Sách Thu Chi", 0);
            sheet2.mergeCells(0, 0, 4, 0);
            sheet2.addCell(new Label(0, 0, "Danh Sách Thu Chi", Cell(sheet2, Alignment.CENTRE)));
            sheet2.addCell(new Label(0, 1, "STT", Cell(sheet2, jxl.format.Alignment.CENTRE)));
            sheet2.addCell(new Label(1, 1, "Tên Thu Chi", Cell(sheet2, jxl.format.Alignment.CENTRE)));
            sheet2.addCell(new Label(2, 1, "Loại Thu Chi", Cell(sheet2, jxl.format.Alignment.CENTRE)));
            sheet2.addCell(new Label(3, 1, "Ngày", Cell(sheet2, jxl.format.Alignment.CENTRE)));
            sheet2.addCell(new Label(4, 1, "Tổng Tiền", Cell(sheet2, Alignment.LEFT)));
            sheet2.addCell(new Label(5, 1, "Nội Dung", Cell(sheet2, Alignment.LEFT)));
            for (int i = 0; i < ds_ThuChi.size(); i++) {
                sheet2.addCell(new jxl.write.Number(0, i + 2, i + 1, Cell(sheet2, Alignment.CENTRE)));
                sheet2.addCell(new Label(1, i + 2, ds_ThuChi.get(i).getTenChiPhi(), Cell(sheet2, Alignment.LEFT)));
                sheet2.addCell(new Label(2, i + 2, ds_ThuChi.get(i).getLoaichiphi(), Cell(sheet2, Alignment.LEFT)));
                sheet2.addCell(new Label(3, i + 2, ds_ThuChi.get(i).getNgayThang(), Cell(sheet2, Alignment.LEFT)));
                sheet2.addCell(new jxl.write.Number(4, i + 2, ds_ThuChi.get(i).getMucChiPhi(), Cell(sheet2, Alignment.LEFT)));
                sheet2.addCell(new Label(5, i + 2, ds_ThuChi.get(i).getNoiDung(), Cell(sheet2, Alignment.LEFT)));
            }
            workbook_To_Write2.write();
            workbook_To_Write2.close();
        } catch (IOException ex) {
            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getDanhSachThuChi() {
        try {
            Sheet sheet1 = workbook2.getSheet(0);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            ds_ThuChi.clear();
            for (int row = 2; row < rows; row++) {
                String tenChiPhi = sheet1.getCell(1, row).getContents();
                String loaiChiPhi = sheet1.getCell(2, row).getContents();
                String ngay = sheet1.getCell(3, row).getContents();
                int tongTien = Integer.parseInt(sheet1.getCell(4, row).getContents());
                String noiDung = sheet1.getCell(5, row).getContents();
                ds_ThuChi.add(new ThuChi(tenChiPhi, tongTien, ngay, loaiChiPhi, noiDung));
            }
        } catch (Exception e) {
        }
    }

    public void writeHoaDonToFile(HoaDon hd) throws IOException {
        //file3 = new File("Danh Sách Hóa Đơn.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file3, true));
        bw.write(hd.getMaHoaDon() + "," + hd.getNgay() + "," + hd.getTongTien() + "," + hd.getDs_SanPham().size() + ",");
        for (int i = 0; i < hd.getDs_SanPham().size(); i++) {
            SanPham sp = hd.getDs_SanPham().get(i);
            bw.write(sp.getMaSanPham() + "," + sp.getTenSanPham() + "," + sp.getGiaBan() + "," + sp.getSoLuong() + ",");
        }
        bw.newLine();
        bw.close();
    }

    private void getDanhSachHoaDon() throws FileNotFoundException, IOException {
        ds_HoaDon.clear();
        BufferedReader br = new BufferedReader(new FileReader(file3));
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] s = line.split(",");
            String maHoaDon = s[0];
            String ngay = s[1];
            long tongTien = Long.parseLong(s[2]);
            int soMatHang = Integer.parseInt(s[3]);
            ArrayList<SanPham> listSanPhamTrongHoaDon = new ArrayList<>();
            for (int k = 0; k < soMatHang; k++) {
                String maSP = s[4 * k + 4];
                String tenSanPham = s[4 * k + 5];
                int giaBan = Integer.parseInt(s[4 * k + 6]);
                int soLuong = Integer.parseInt(s[4 * k + 7]);
                listSanPhamTrongHoaDon.add(new SanPham(maSP, tenSanPham, giaBan, soLuong));
            }
            ds_HoaDon.add(new HoaDon(maHoaDon, ngay, listSanPhamTrongHoaDon, tongTien));
        }
    }

    public void updatedanhSachHoaDon() throws IOException {
        file3.delete();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file3));
        for (int i = 0; i < ds_HoaDon.size(); i++) {
            HoaDon hd = ds_HoaDon.get(i);
            bw.write(hd.getMaHoaDon() + "," + hd.getNgay() + "," + hd.getTongTien() + "," + hd.getDs_SanPham().size() + ",");
            for (int k = 0; k < hd.getDs_SanPham().size(); k++) {
                SanPham sp = hd.getDs_SanPham().get(k);
                bw.write(sp.getMaSanPham() + "," + sp.getTenSanPham() + "," + sp.getGiaBan() + "," + sp.getSoLuong() + ",");
            }
            bw.newLine();
        }
        bw.close();
    }

    public ArrayList<HoaDon> get_Ds_Hoa_Don() {
        return ds_HoaDon;
    }

    public ArrayList<ThuChi> get_Ds_ThuChi() {
        return ds_ThuChi;
    }

    public ArrayList<NhanVien> get_Ds_NhanVien() {
        return ds_NhanVien;
    }

    public ArrayList<Sach> get_Ds_Sach() {
        return ds_Sach;
    }

    public ArrayList<DiaNhac> get_Ds_DiaNhac() {
        return ds_DiaNhac;
    }

    public ArrayList<DiaPhim> get_Ds_DiaPhim() {
        return ds_DiaPhim;
    }

    //can le
    public jxl.format.CellFormat Cell(WritableSheet sheet, jxl.format.Alignment alignment) {
        WritableCellFormat cellFormat = new WritableCellFormat();
        try {
            cellFormat.setAlignment(alignment);
            cellFormat.setWrap(true);
        } catch (WriteException ex) {
            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cellFormat;
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dates = new Date();
        return dateFormat.format(dates);
    }

}
