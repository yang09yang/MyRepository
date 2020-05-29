package com.jeff.sorm.utils;
/**
 * package java file
 * @author lcyan
 *
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jeff.sorm.bean.ColumnInfo;
import com.jeff.sorm.bean.JavaFieldGetSet;
import com.jeff.sorm.bean.TableInfo;
import com.jeff.sorm.core.DBManager;
import com.jeff.sorm.core.MySqlTypeConvertor;
import com.jeff.sorm.core.TableContext;
import com.jeff.sorm.core.TypeConvertor;
/**
 * 
 * @author lcyan
 *
 */
public class JavaFileUtils {
	/**
	 * according to fieldInfo generate java attribute info and set and get method
	 * @param column
	 * @param convertor
	 * @return the source of the set and get method
	 */
	public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo column,TypeConvertor convertor) {

		JavaFieldGetSet jfgs=new JavaFieldGetSet();
		String javaFieldType =convertor.database2JavaType(column.getDataType());
		jfgs.setFieldInfo("\tprivate "+javaFieldType+" "+column.getName()+";\n");
		//public String getUserName(){return username;}
		StringBuilder getSrc=new StringBuilder();
	
		getSrc.append("\tpublic "+javaFieldType+" get"+StringUtils.firstChar2UpperCase(column.getName())+"(){\n");
		getSrc.append("\t\treturn "+column.getName()+";\n");
		getSrc.append("\t}\n");
		jfgs.setGetInfo(getSrc.toString());
		//public void setUserName(String username){this.username=username;}
		StringBuilder setSrc=new StringBuilder();
		setSrc.append("\tpublic void set"+StringUtils.firstChar2UpperCase(column.getName())+"(");
		setSrc.append(javaFieldType+" "+ column.getName()+"){\n");
		setSrc.append("\t\tthis."+column.getName()+"="+column.getName()+";\n");
		setSrc.append("\t}\n");
		jfgs.setSetInfo(setSrc.toString());
		return jfgs;
	}
	public static String createJavaSrc(TableInfo tableInfo, TypeConvertor convertor) {
		Map<String,ColumnInfo> columns=tableInfo.getColumns();
		List<JavaFieldGetSet> javaFields=new ArrayList<JavaFieldGetSet>();
		for (ColumnInfo c:columns.values()) {
			javaFields.add(createFieldGetSetSRC(c, convertor));
		}
		StringBuilder src=new StringBuilder();
		/**
		 * generate package statement
		 */
		src.append("package  "+DBManager.getConf().getPoPackage()+";\n\n");
		/**
		 * generate import statement
		 */
		src.append("import java.sql.*;\n");
		src.append("import java.util.*;\n\n");
		/**
		 * generate class declaration statement
		 */
		src.append("public class "+ StringUtils.firstChar2UpperCase(tableInfo.getTname())+"{\n");
		/**
		 * generate parameter list
		 */
		for(JavaFieldGetSet f:javaFields) {
			src.append(f.getFieldInfo());
		}
		src.append("\n\n");
		/**
		 * generate get method
		 */
		for(JavaFieldGetSet f:javaFields) {
			src.append(f.getGetInfo());
		}
		src.append("\n\n");
		
		/**
		 * generate set method
		 */
		for(JavaFieldGetSet f:javaFields) {
			src.append(f.getSetInfo());
		}
		src.append("\n}\n");
		return src.toString();
		
	}
	public static void createJavaPOFile(TableInfo tableInfo,TypeConvertor convertor) {
		String src= createJavaSrc(tableInfo, convertor);
		String srcPath=DBManager.getConf().getSrcPath()+"\\";
		String packagePath=DBManager.getConf().getPoPackage().replaceAll("\\.","\\\\");
		File f=new File(srcPath+packagePath);
		if(!f.exists()) {
			f.mkdirs();
		}
		BufferedWriter bw=null;
		try {
			bw=new BufferedWriter(new FileWriter(f.getAbsoluteFile()+"/"+StringUtils.firstChar2UpperCase(tableInfo.getTname())+".java"));
			bw.write(src);
			System.out.println("create table "+tableInfo.getTname()+" corresponding java file: "+StringUtils.firstChar2UpperCase(tableInfo.getTname())+".java");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(bw!=null) {
					bw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
//	public static void main(String[] args) {
//		Map<String,TableInfo> map=TableContext.tables;
//		TableInfo tableInfo=map.get("emp");
//		createJavaPOFile(tableInfo, new MySqlTypeConvertor());
//	//	Map<String,TableInfo> map=TableContext.tables;
//		for (TableInfo tableInfo : map.values()) {
//			createJavaPOFile(tableInfo, new MySqlTypeConvertor());
//		}
//	}
}
