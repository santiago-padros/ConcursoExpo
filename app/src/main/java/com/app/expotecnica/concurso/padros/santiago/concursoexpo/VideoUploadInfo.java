package com.app.expotecnica.concurso.padros.santiago.concursoexpo;

/**
 * Created by fliap on 5/9/2017.
 */

public class VideoUploadInfo {
    public String videoName;


    public String videoURL;
    public String Nombre;
    public String Apellido;
    public String Curso;



    public VideoUploadInfo(String name, String url, String name2, String name3, String name4) {

        this.videoName = name;
        this.videoURL= url;
        this.Nombre = name2;
        this.Apellido = name3;
        this.Curso = name4;
    }

    public String getImageName() {
        return videoName;
    }

    public String getImageURL() {
        return videoURL;
    }
    public String getNombre() {
        return Nombre;
    }
    public String getApellido() {
        return Apellido;
    }
    public String getCurso() {
        return Curso;
    }
}
