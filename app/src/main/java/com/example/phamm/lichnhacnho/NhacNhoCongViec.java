package com.example.phamm.lichnhacnho;

import java.io.Serializable;

/**
 * Created by phamm on 10/12/2017.
 */

public class NhacNhoCongViec implements Serializable {
    private String NgayThongBao;
    private String TieuDe;
    private String Noidung;
    private String GioThongBao;
    public String key;

    public NhacNhoCongViec(String ngayThongBao, String tieuDe, String noidung, String gioThongBao) {
        NgayThongBao = ngayThongBao;
        TieuDe = tieuDe;
        Noidung = noidung;
        GioThongBao = gioThongBao;
    }
    public NhacNhoCongViec(){

    }
    public String getNgayThongBao() {
        return NgayThongBao;
    }

    public void setNgayThongBao(String ngayThongBao) {
        NgayThongBao = ngayThongBao;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String noidung) {
        Noidung = noidung;
    }

    public String getGioThongBao() {
        return GioThongBao;
    }

    public void setGioThongBao(String gioThongBao) {
        GioThongBao = gioThongBao;
    }
}
