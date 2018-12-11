
思必拓工具app
主要用于调试模块 排查问题  以及演示工具类的接口调用

1.串口调试
2.配置文件预览以及设置
3.GPIO预览以及设置
config.zip 是配置文件使用说明


**说明**：

1.在项目的build.gradle中添加以下
```xml
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
在modle的依赖中添加以下
```xml
	dependencies {
		implementation 'com.github.SpeedataG:Device:1.6.1'
	}

 ``` 
    
2. DataCleanUtils
    可以清除本应用中的内/外缓存，清除数据库，清除sharedPreference，清除files和清除自定义目录。
    **·DataCleanUtils** 
    
    |方法|说明|
    |:----    |:---|
    |cleanInternalCache(Context context) |context 上下文。 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)|
    |cleanDatabases(Context context) |context 上下文。 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) |
    |cleanSharedPreference(Context context) |context 上下文。清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)|
    |cleanFiles(Context context) |context 上下文。清除/data/data/com.xxx.xxx/files下的内容|
    |cleanExternalCache(Context context) |context 上下文。清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)|
    |cleanCustomCache(String filePath) |context 上下文。清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除|
    |cleanApplicationData(Context context, String... filePath) |context 上下文，filePath 文件路径。清除本应用所有的数据|
    |deleteFilesByDirectory(File directory) |directory 文件夹File对象。删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理|
    |getFolderSize(File file) |file 文件名，返回类型为long。 获取文件|
    |getFormatSize(double size) |size 传入的大小，返回类型为String。格式化单位返回格式化之后的值|
    
    
    
     **说明**
    
    ``` 
    
       // getFolderSize(File file)获取文件
            //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
            //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    ```

    
    
    
3. DateTimePickDialogUtil
    时间控件的显示与修改时间，需要界面相关时间控件的支持
    
    **·DateTimePickDialogUtil** 
    
    |方法|说明|
    |:----    |:---|
    |DateTimePickDialogUtil(Activity activity, String initDateTime) |activity：调用的父activity，initDateTime：初始日期时间值，作为弹出窗口的标题和日期时间初始值。 日期时间弹出选择框构造函数|
    |dateTimePicKDialog(final TextView inputDate) |inputDate:为需要设置的日期时间文本编辑框。  弹出日期时间选择框方法 |
    |getCalendarByInintData(String initDateTime)|initDateTime： 初始日期时间值 字符串型。实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar|
    
    

    
4. GsonUtil
    将json映射成bean对象
    **·GsonUtil** 
    
    |方法|说明|
    |:----    |:---|
    |json2Bean(String result, Class<T> clazz) |result：json字符串，clazz：bean对象字节码。 将json映射成bean对象|
    
5. MD5Utils
    MD5值查看
    **·MD5Utils** 
    
    |方法|说明|
    |:----    |:---|
    |md5sum(String filename) |filename：文件名。 查看文件MD5值|
    
    
6. MyDateAndTime
    时间相关方法的封装
    
    **·MyDateAndTime** 
    
    |方法|说明|
    |:----    |:---|
    |getBatchCode()|获取当前时间包含毫秒值的String字符串：yyyyMMddHHmmssSSS|
    |getTimeString()|获取当前时间的String字符串：yyyy-MM-dd HH:mm:ss|
    |getTimeStringYMD()|获取当前日期的String字符串：yyyy-MM-dd|
    |getTimeString(String format)|format:自定义时间格式。获取当前时间的String字符串|
    
    
7. PlaySoundPool
    播放正确与错误的提示音
    
    **·PlaySoundPool** 
    
    |方法|说明|
    |:----    |:---|
    |getPlaySoundPool(Context context)|context：上下文、当前activity。获取PlaySoundPool类的对象|
    |playLaser()|播放成功声音|
    |playError()|播放错误声音|
    
     **说明**
    
    ``` 
    //实际使用中，可以在需要的位置直接调用。
    PlaySoundPool.getPlaySoundPool(mActivity).playLaser();
     PlaySoundPool.getPlaySoundPool(mActivity).playError();
       
    ```
    
    
8. PrefUtils
    SharePreference简单封装
    **·PrefUtils** 
    
    |方法|说明|
    |:----    |:---|
    |getBoolean(Context ctx, String key,boolean defaultValue)|ctx：上下文,key:sp
    的对应key，defaultValue：缺省值，如果不存在key，则返回它。获取此key的boolean值|
    |setBoolean(Context ctx, String key, boolean value)|ctx：上下文,key:sp
    的对应key，value,要写入的值。设置此key的boolean值|
    |getString(Context ctx, String key, String defaultValue)|ctx：上下文,key:sp
    的对应key，defaultValue：缺省值，如果不存在key，则返回它。获取此key的String值|
    |setString(Context ctx, String key, String value)|ctx：上下文,key:sp
    的对应key，value,要写入的值。设置此key的String值|
    |getInt(Context ctx, String key, int defaultValue)|ctx：上下文,key:sp
    的对应key，defaultValue：缺省值，如果不存在key，则返回它。获取此key的int值|
    |setInt(Context ctx, String key, int value)|ctx：上下文,key:sp
    的对应key，value,要写入的值。设置此key的int值|

    
    
9. ProgressDialogUtils
    显示与关闭等待提示框
    **·ProgressDialogUtils** 
    
    |方法|说明|
    |:----    |:---|
    |showProgressDialog(Context context, CharSequence message)|context：上下文,message:显示内容。显示ProgressDialog|
    |dismissProgressDialog()|关闭ProgressDialog|
    
        
10. ToolCommon
    界面操作方法集合
    
    **·ToolCommon** 

    |方法|说明|
    |:----    |:---|
    |isNumeric(String str)|str 输入，返回类型boolean。判断输入是否是数字|
    |hideSoftInput(Context context, View view) |context：上下文，view：当前视图。隐藏软键盘|
    |isNetworkConnected(Context context)|context：上下文，返回类型boolean。检测网络是否可用|
    |dip2px(Context context, float dpValue)|context：上下文，dpValue：转化比率。根据手机的分辨率从 dp 的单位 转成为 px(像素)|
    |getFormatData(long date)|date: long型时间毫秒值，返回类型为String。格式化时间，返回已格式时间|
            
11. ToolUtils
    其他补充工具类
    
    **·ToolUtils** 
    
    |方法|说明|
    |:----    |:---|
    |initTitle(Activity activity,int red, int green, int blue)|activity：要设置的页面，red：颜色red的int值，green：颜色green的int值，blue：颜色blue的int值。状态栏美化，设置背景颜色如363534，传入3个参数36、35、34.需要5.1以上|
    |getVersion(Context context)|context：上下文，返回类型为String。获取当前应用程序的版本号|
    |modelComparison(String model)|model 机型：kt40,kt55...，返回类型为boolean。对比手持机机型与要比对的机型是否相同|
    
    
           
    
