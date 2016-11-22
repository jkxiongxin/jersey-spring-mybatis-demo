package com.xx.jersey.demo.http;

import com.xx.jersey.demo.aop.Compress;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

/**
 * Created by hzxiongxin on 2016/11/17.
 */
@Path("http/file")
public class FileUtil {

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Compress
    public Response getFile(){
        File file = new File("d:/1.rar");
        String type = new MimetypesFileTypeMap().getContentType(file);
        return Response
                .ok(file,type)
                .header("Cache-Control","no-cache")
                .header("Content-Disposition","attachment;filename=1.rar")
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("1")
    public Response getFile1(){
        File file = new File("d:/1.rar");
        String type = new MimetypesFileTypeMap().getContentType(file);
        return Response
                .ok(file,type)
                .header("Cache-Control","no-cache")
                .header("Content-Disposition","attachment;filename=1.rar")
                .build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response post(@FormDataParam("file")InputStream file, @FormDataParam("file")FormDataContentDisposition header){
        System.out.println("Processiong file *"+header.getFileName());

        File file1 = new File("D:/2.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file1);
            int length = 1024;
            byte[] buffer = new byte[length];
            while (true) {
                int ins = file.read(buffer);
                if(ins == -1){
                    file.close();
                    fos.flush();
                    fos.close();
                    return Response.ok(1).build();
                }
                fos.write(buffer,0,ins);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.ok(0).build();
    }
}
