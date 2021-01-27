package com.hendra.myapplication.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class Pahlawan {

    public static final String BANDUNG = "BANDUNG";
    public static final String YOGYAKARTA = "YOGYAKARTA";
    public static final String LOMBOK = "LOMBOK";


    private String id;
    private String nama;
    private String profil;
    private String asal;
    private String thnlahir;
    private String thnwafat;

    public Pahlawan() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getAsal() {
        return asal;
    }

    public void setasal(String asal) {
        this.asal = asal;
    }

    public String getThnlahir() {
        return thnlahir;
    }

    public void setthnlahir(String thnlahir) {
        this.thnlahir = thnlahir;
    }

    public String getThnwafat() {
        return thnwafat;
    }

    public void setThnwafat(String thnwafat) {
        this.thnwafat = thnwafat;
    }

    public static Pahlawan fromJSONObject(JSONObject obj) {
        Pahlawan tr = new Pahlawan();

        try {
            tr.setId(obj.getString("id"));
            tr.setnama(obj.getString("nama"));
            tr.setasal(obj.getString("asal"));
            tr.setthnlahir(obj.getString("thnlahir"));
            tr.setThnwafat(obj.getString("thnwafat"));
            tr.setProfil(obj.getString("profil"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tr;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id", this.id);
            jsonObj.put("nama", this.nama);
            jsonObj.put("asal", this.asal);
            jsonObj.put("thnlahir", this.thnlahir);
            jsonObj.put("thnwafat", this.thnwafat);
            jsonObj.put("profil", this.profil);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}