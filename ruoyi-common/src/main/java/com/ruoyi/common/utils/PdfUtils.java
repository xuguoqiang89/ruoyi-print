package com.ruoyi.common.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: XGQ
 * @Date: 2020\6\16
 */
public class PdfUtils {
	// 利用模板生成pdf
	public static void pdfout(String templatePath, String newPDFPath, Map<String,Object> o, String fontName) {
		PdfReader reader;
		FileOutputStream out;
		ByteArrayOutputStream bos;
		PdfStamper stamper;
		try {
			//给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
			BaseFont bf = BaseFont.createFont(fontName , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font FontChinese = new Font(bf, 5, Font.NORMAL);
			// 输出流
			out = new FileOutputStream(newPDFPath);
			// 读取pdf模板
			reader = new PdfReader(templatePath);
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();
			//遍历数据
			Map<String,Object> datemap = (Map<String,Object>)o.get("datamap");
			// 添加所创建的字体
			form.addSubstitutionFont(bf);
			for(String key : datemap.keySet()){
				String value = datemap.get(key).toString();
				form.setField(key,value);
			}
			Map<String,Object> qrcodeFields=(Map<String, Object>) o.get("qrcodeFields");
			//遍历二维码字段
			if(qrcodeFields!=null){
				for (Map.Entry<String, Object> entry : qrcodeFields.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					// 获取属性的类型
					if(value != null && form.getField(key) != null){
						//获取位置(左上右下)
						AcroFields.FieldPosition fieldPosition = form.getFieldPositions(key).get(0);
						//绘制二维码
						float width = fieldPosition.position.getRight() - fieldPosition.position.getLeft();
						BarcodeQRCode pdf417 = new BarcodeQRCode(value.toString(), (int)width, (int)width, null);
						//生成二维码图像
						Image image128 = pdf417.getImage();
						//绘制在第一页
						PdfContentByte cb = stamper.getOverContent(1);
						//左边距(居中处理)
						float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft() - image128.getWidth()) / 2;
						//条码位置
						image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft, fieldPosition.position.getBottom());
						//加入条码
						cb.addImage(image128);
					}
				}
			}

			//遍历条码字段
			Map<String,Object> barcodeFields=(Map<String, Object>) o.get("barcodeFields");
			if(barcodeFields!=null){
				for (Map.Entry<String, Object> entry : barcodeFields.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					// 获取属性的类型
					if(value != null && form.getField(key) != null){
						//获取位置(左上右下)
						AcroFields.FieldPosition fieldPosition = form.getFieldPositions(key).get(0);
						//绘制条码
						Barcode128 barcode128 = new Barcode128();
						//字号
						barcode128.setSize(10);
						//条码高度
						barcode128.setBarHeight(35);
						//条码与数字间距
						barcode128.setBaseline(10);
						//条码值
						barcode128.setCode(value.toString());
						barcode128.setStartStopText(false);
						barcode128.setExtended(true);
						//绘制在第一页
						PdfContentByte cb = stamper.getOverContent(1);
						//生成条码图片
						Image image128 = barcode128.createImageWithBarcode(cb, null, null);
						//左边距(居中处理)
						float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft() - image128.getWidth()) / 2;
						//条码位置
						image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft, fieldPosition.position.getBottom());
						//加入条码
						cb.addImage(image128);
					}
				}
			}

			//图片类的内容处理
			Map<String,String> imgmap = (Map<String,String>)o.get("imgmap");
			if(imgmap!=null){
				for(String key : imgmap.keySet()) {
					String value = imgmap.get(key);
					String imgpath = value;
					int pageNo = form.getFieldPositions(key).get(0).page;
					Rectangle signRect = form.getFieldPositions(key).get(0).position;
					float x = signRect.getLeft();
					float y = signRect.getBottom();
					//根据路径读取图片
					Image image = Image.getInstance(imgpath);
					//获取图片页面
					PdfContentByte under = stamper.getOverContent(pageNo);
					//图片大小自适应
					image.scaleToFit(signRect.getWidth(), signRect.getHeight());
					//添加图片
					image.setAbsolutePosition(x, y);
					under.addImage(image);
				}
			}

			// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
			stamper.setFormFlattening(true);
			stamper.close();
			Document doc = new Document();
			Font font = new Font(bf, 20);
			PdfCopy copy = new PdfCopy(doc, out);
			doc.open();
			PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
			copy.addPage(importPage);
			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Map<String,String> map = new HashMap();
		map.put("order_code","YD202006160001");
		map.put("room_name","新月河湾 A区 1# 2302");
		map.put("room_hx","三室一厅");
		map.put("building_area","145.00");
		map.put("inside_area","130.00");
		map.put("sale_mode","建筑面积");
		map.put("client_name","吴小莉");
		map.put("telephone","13899881111");
		map.put("cert_no","3882293198808121243");
		map.put("price","8700.00");
		map.put("total_price","1028700.00");
		map.put("book_price","8700.00");
		map.put("book_total","1028700.00");
		map.put("book_date","2020-06-18");
		map.put("plan_next_date","2020-07-18");
		map.put("zdr","小王");
		map.put("zygw","吴晓敏");
		map.put("remark","一次性");
		map.put("attachment","暂无附件");

		Map<String,String> map2 = new HashMap();
//		map2.put("img","c:/50336.jpg");

		Map<String,Object> o=new HashMap();
		o.put("datamap",map);
//		o.put("imgmap",map2);

		String file_path = "E:";

		// 模板路径
		String templatePath = file_path + "/pdfTemplate/预订审批单-form.pdf";
		// 生成的新文件路径
		String newPDFPath = file_path + "/oaPdf/预订审批单-data1.pdf";

		String fontName = file_path+"/font/msyh.ttf";

		pdfout(templatePath, newPDFPath, o, fontName);
	}
}
