package SDAV.Modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.postgresql.util.Base64;

public class ModeloZona extends Zona {
private static ConexionBD con = new ConexionBD();

    public ModeloZona() {
    }

    public ModeloZona(String idzona, String nombre, String tarifa, String estado) {
        super(idzona, nombre, tarifa, estado);
    }


    private BufferedImage imgBimage(Image img) {

        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bi = new BufferedImage(
                img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D bGR = bi.createGraphics();
        bGR.drawImage(img, 0, 0, null);
        bGR.dispose();
        return bi;
    }

    public static Image obtenImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator it = ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = (ImageReader) it.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        param.setSourceSubsampling(1, 1, 0, 0);
        return reader.read(0, param);
    }

    public List<Zona> listarZonas(String aguja) {
        try {
            String sql;
            sql = "select * from zona WHERE ";
            sql += "UPPER(id_zona)  LIKE UPPER('%" + aguja + "%') OR ";
            sql += "UPPER(nombre)  LIKE  UPPER('%" + aguja + "%') OR ";
            sql += "UPPER(estado)  LIKE UPPER('%" + aguja + "%') ";

            ResultSet rs = con.query(sql);
            List<Zona> lista = new ArrayList<Zona>();
            byte[] bf;
            while (rs.next()) {
                Zona zona = new Zona();
                zona.setIdzona(rs.getString("id_zona"));
                zona.setNombre(rs.getString("nombre"));
                zona.setTarifa(rs.getString("cod_tarifa"));
                zona.setEstado(rs.getString("estado"));
                bf = rs.getBytes("foto");

                if (bf != null) {
                    bf = Base64.decode(bf, 0, bf.length);
                    try {
                        zona.setFoto(obtenImagen(bf));
                    } catch (IOException ex) {
                        zona.setFoto(null);
                        Logger.getLogger(ModeloZona.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    zona.setFoto(null);
                }
                lista.add(zona);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloZona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public boolean crear() {
        String foto64 = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            BufferedImage img = imgBimage(getFoto());
            ImageIO.write(img, "PNG", bos);
            byte[] imgb = bos.toByteArray();
            foto64 = Base64.encodeBytes(imgb);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        String sql;
        sql = "INSERT INTO zona(foto,id_zona, nombre, cod_tarifa, estado)";
        sql += "VALUES('" + foto64 + "','" + getIdzona() + "','" + getNombre() + "','" + getTarifa()
                + "','" + getEstado()+ "')";
        if (con.noquery(sql) == null) {
            return true;
        }
        return false;
    }

    public boolean editar() {
        String foto64 = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            BufferedImage img = imgBimage(getFoto());
            ImageIO.write(img, "PNG", bos);
            byte[] imgb = bos.toByteArray();
            foto64 = Base64.encodeBytes(imgb);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        String sql;
        sql = "UPDATE zona SET id_zona='" + getIdzona()+ "', nombre='" + getNombre()
         + "', cod_tarifa='" + getTarifa() + "',estado='" + getEstado() + "',foto='" + foto64 +"'WHERE id_zona='" + getIdzona()+ "'";
        if (con.noquery(sql) == null) {
            return true;
        }
        return false;
    }

    public boolean eliminar(String id) {
        String sql;
        sql = "DELETE from zona WHERE id_zona='" + getIdzona() + "'";
        if (con.noquery(sql) == null) {
            return true;
        }
        return false;
    }
}