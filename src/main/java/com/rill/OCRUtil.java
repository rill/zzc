package com.rill;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by rill on 2017/4/15.
 */
public class OCRUtil {

    /**
     * OCR识别.
     *
     * @param imageFile 图片文件
     * @param rectangle 识别区域
     * @param language 语言，对应tessdata下的语言文件；默认英文，中文传入“chi_sim”
     * @param scale 放大比例，默认不放大，输入5表示放大5倍
     * @return 识别结果
     */
    public static String doOCR(File imageFile, Rectangle rectangle, String language, int scale,boolean isInvert) {
        String result = "";
        try {
            //图片文件转bufferImage
            BufferedImage bi = ImageIO.read(imageFile);
            //图像灰度处理
            bi = ImageHelper.convertImageToGrayscale(bi);
            ImageIO.write(bi, "png", new File("d://test//pic//gray.png"));
            //图像二值化
            bi = ImageHelper.convertImageToBinary(bi);
            ImageIO.write(bi, "png", new File("d://test//pic//bw.png"));
            if(rectangle != null){
                //图像裁剪
                bi = bi.getSubimage(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                ImageIO.write(bi, "png", new File("d://test//pic//sub.png"));
            }
            if(isInvert){
                //图像反色
                bi = ImageHelper.invertImageColor(bi);
                ImageIO.write(bi, "png", new File("d://test//pic//invert.png"));
            }
           /* bi = ImageHelper.rotateImage(bi, 45); // deskew image
            ImageIO.write(bi, "png", new File("d://test//pic//rotate.png"));*/
            if(scale>1){
                //图片放大
                bi = ImageHelper.getScaledInstance(bi, bi.getWidth() * scale, bi.getHeight() * scale);
                ImageIO.write(bi, "png", new File("d://test//pic//scale.png"));
            }
            ITesseract instance = new Tesseract();
            //instance.setTessVariable("tessedit_char_whitelist","0123456789");
            //创建默认tess配置路径
            //File tessDataFolder = LoadLibs.extractTessResources("tessdata");
            //System.out.println(tessDataFolder.getAbsolutePath());
            //设置字体文件路径
            //instance.setDatapath(tessDataFolder.getAbsolutePath());
            instance.setDatapath("d://ocr//tessdata");
            //设置配置参数，例如限制数字
            java.util.List<String> configs = Arrays.asList("digits");
            instance.setConfigs(configs);
            if(language != null && language.length()>0){
                //设置要识别的字体，默认是英文数字
                instance.setLanguage(language);
            }
            //调用ocr识别
            result = instance.doOCR(bi);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args){

        File file = new File("d://test//pic//rotate.png");
        Rectangle rectangle = new Rectangle(683,821,240,70);
        System.out.println(doOCR(file,null,null,1,false));
    }
}
