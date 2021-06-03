Java从txt文件读取数据统计图显示

用到jfreechart和jcommon，还有控制io的用来读文件

用filereader读取一个文件，把这个当参数扔给 bufferedreader，就是需要经过缓冲区了。按行读字符串格式，赋值给数组s[]

创建数据库dataset1，将数组s[]的值传进去, ChartFactory.*createPieChart*方法绘制饼状图。

将图片保存为jpeg格式的图片。

再创建数据库dataset，把数组s[]传进去，画出柱状图，保存为jpeg

得到两张统计图为