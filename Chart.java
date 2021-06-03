package work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Color;
import java.awt.Font;
import java.io.*; 
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.*; 
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.*; 
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
 
public class Chart { 
    private static FileOutputStream fos_jpg;

	public static void main(String[] args) throws IOException{ 
		//从txt文件中读数据
    	File file = new File("data.txt");
        BufferedReader reader = null;
        String tempString = null;
        int line =1;
        String[] s=new String[5];
        
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((tempString = reader.readLine()) != null) {
                s[line-1] = tempString;               //txt文件中的数据赋值给数组是s[]
                line ++ ;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    	
        int width = 640; // 图像宽
	    int height = 480; //图像高
	    
    	DefaultPieDataset dataset1 = new DefaultPieDataset( ); //创建数据库dataset1，将数组s[]的值传进去
        dataset1.setValue("type1", new Double(s[0]) );
        dataset1.setValue("type2", new Double(s[1]) );
        dataset1.setValue("type3", new Double(s[2]) );
        dataset1.setValue("type4", new Double(s[3]) );
        dataset1.setValue("type5", new Double(s[4]) );
        
        
        JFreeChart chart1 = ChartFactory.createPieChart(    //画统计图  饼状图
        	       "PieChart", // chart title
        	       dataset1, // data
        	       true, // include legend
        	       true,
        	       false);
        File pieChart = new File( "饼状图.jpeg" );     //保存图片
        ChartUtilities.saveChartAsJPEG( pieChart , chart1 , width , height );
        
    	
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
        	    
        dataset.addValue(new Double(s[0]), "pe1", "ty1"); 
        dataset.addValue(new Double(s[1]), "pe2", "ty2"); 
        dataset.addValue(new Double(s[2]), "pe3", "ty3"); 
        dataset.addValue(new Double(s[3]), "pe4", "ty4"); 
        dataset.addValue(new Double(s[4]), "pe5", "ty5"); 	    
        	    
        JFreeChart chart= ChartFactory.createBarChart(     //画统计图  柱状图
                           "BarChart", // 图表标题
                           "横轴type", // 目录轴的显示标签
                           "纵轴", // 数值轴的显示标签
                            dataset, // 数据集
                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                            true,  // 是否显示图例(对于简单的柱状图必须是 false)
                            false, // 是否生成工具
                            false  // 是否生成 URL 链接
                            ); 
        
        chart.setBackgroundPaint(Color.WHITE); 
        CategoryPlot plot = chart.getCategoryPlot(); 
        CategoryAxis domainAxis = plot.getDomainAxis(); 
        domainAxis.setAxisLineVisible(false); 
        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));
        //chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        plot.setDomainAxis(domainAxis); 
        BarRenderer3D renderer = new BarRenderer3D(); 
        renderer.setBaseOutlinePaint(Color.BLACK);
        // 显示每个柱的数值，并修改该数值的字体属性 
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
        // 设置每个地区所包含的平行柱的之间距离 
        renderer.setItemMargin(0.1); 
        // 设置柱的数值可见
        renderer.setBaseItemLabelsVisible(true);
        plot.setRenderer(renderer); 
        // 设置柱的透明度 
        plot.setForegroundAlpha(0.8f); 

        fos_jpg = null; 
        try { 
        	File pieChart1 = new File( "柱状图.jpeg" );   //保存图片
    	    ChartUtilities.saveChartAsJPEG( pieChart1 , chart , width , height );

        } finally { 
            try { 
                fos_jpg.close(); 
            } catch (Exception e) {} 
        } 
               
    }
}
