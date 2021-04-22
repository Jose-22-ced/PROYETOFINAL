/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.postgresql.util.Base64;

/**
 *
 * @author Bryans
 */
public class ModeloVehiculo extends Vehiculo{
    private static ConexionBD conex = new ConexionBD();

    public ModeloVehiculo() {
    }

    public ModeloVehiculo(String placa, String cedula_cli, String marca, String color, String tipo_vehiculo) {
        super(placa, cedula_cli, marca, color, tipo_vehiculo);
    }
public boolean crearV(){
    String foto64 = null;
    ByteArrayOutputStream bos= new ByteArrayOutputStream();    
    try{
        BufferedImage img =imgBimage(getFoto());
        ImageIO.write(img, "PNG", bos);
        byte[] imgb=bos.toByteArray();
        foto64=Base64.encodeBytes(imgb);
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }    

    String sql;
    sql="INSERT INTO vehiculo(placa,cedula_cli,marca,color,tipo_v,foto)";
    sql+="VALUES ('"+getPlaca()+"','"+getCedula_cli()+"','"+getMarca()+"','"+getColor()+"','"+getTipo_vehiculo()+"','"+foto64+"')";
    if(conex.noquery(sql)==null){
        return true;
    }else {
        return false;
    }
    
    }
    private BufferedImage imgBimage(Image img){
       if (img instanceof BufferedImage){
            return (BufferedImage)img;
        }
        BufferedImage bi = new BufferedImage(
                img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB
        );
        
        Graphics2D bGR = bi.createGraphics();
        bGR.drawImage(img, 0, 0,null);
        bGR.dispose();
        return bi;  
    } 
    public List<Vehiculo> listar(String aguja){
        
    try {
        String sql="SELECT placa,cedula_cli,marca,color,tipo_v,foto FROM vehiculo WHERE ";
        sql+="UPPER(placa) LIKE UPPER('%"+aguja+"%') OR ";
        sql+="UPPER(cedula_cli) LIKE UPPER('%"+aguja+"%') OR ";
        sql+="UPPER(marca) LIKE UPPER('%"+aguja+"%')";
        
        ResultSet rs=conex.query(sql);
        List<Vehiculo> lista=new ArrayList<Vehiculo>();
        byte[] bf;
        while (rs.next()) {
            Vehiculo vehiculo=new Vehiculo();
            
            vehiculo.setPlaca(rs.getString("placa"));
            vehiculo.setCedula_cli(rs.getString("cedula_cli"));
            vehiculo.setMarca(rs.getString("marca"));
            vehiculo.setColor(rs.getString("color"));          
            vehiculo.setTipo_vehiculo(rs.getString("tipo_v"));
            bf=rs.getBytes("foto");
            
            if(bf!=null){
                    bf=Base64.decode(bf,0,bf.length);
                    try {
                        //OBTENER IMAGEN
                        vehiculo.setFoto(obtenImagen(bf));
                    } catch (IOException ex) {
                        vehiculo.setFoto(null);
                        Logger.getLogger(ModeloVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                 vehiculo.setFoto(null);
                }
            
            lista.add(vehiculo);
        }
        rs.close();
        return lista;
    } catch (SQLException ex) {
        Logger.getLogger(ModeloVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
    }  
    
    public static Image obtenImagen(byte[] bytes)throws IOException{
        ByteArrayInputStream bis= new ByteArrayInputStream(bytes);
        Iterator it= ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = (ImageReader)it.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis,true);
        ImageReadParam param = reader.getDefaultReadParam();
        param.setSourceSubsampling(1, 1, 0,0);
        return reader.read(0,param);
    }
       public boolean eliminarV(){
        String sql;
         sql="DELETE FROM vehiculo"+" "+"WHERE placa='"+getPlaca()+"'";
        
        
        if(conex.noquery(sql)==null){
        return true;
    }else {
        return false;
    }
    }   
    public boolean editarV(){
         String foto64 = null;
    ByteArrayOutputStream bos= new ByteArrayOutputStream();    
    try{
        BufferedImage img =imgBimage(getFoto());
        ImageIO.write(img, "PNG", bos);
        byte[] imgb=bos.toByteArray();
        foto64=Base64.encodeBytes(imgb);
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }    
        
     String sql;
        sql="UPDATE vehiculo"+" "+"SET cedula_cli='"+getCedula_cli()+"',marca='"+getMarca()+"',color='"+getColor()+"',tipo_v='"+getTipo_vehiculo()+"',foto='"+ foto64+"'";
        sql+="WHERE placa='"+getPlaca()+"'";
          if(conex.noquery(sql)==null){
        return true;
    }else {
        return false;
    }
    }
    
    
    
    
    
}
