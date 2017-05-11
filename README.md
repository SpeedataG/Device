
思必拓工具app
主要用于调试模块 排查问题  以及演示工具类的接口调用

1.串口调试
2.配置文件预览以及设置
3.GPIO预览以及设置
config.zip 是配置文件使用说明


**新增工具类说明**：

1. ColorsUtils
    包含常用颜色的int值，包含深浅以及透明半透明颜色效果。
    例子：`ColorUtils.WHITE`
    
    白色:WHITE                     白色 - 半透明:WHITE_TRANSLUCENT
    黑色:BLACK                     黑色 - 半透明:BLACK_TRANSLUCENT
    透明:TRANSPARENT
    红色:RED                       红色 - 半透明:RED_TRANSLUCENT    
    红色 - 深的:RED_DARK           红色 - 深的 - 半透明:RED_DARK_TRANSLUCENT
    绿色:GREEN                     绿色 - 半透明:GREEN_TRANSLUCENT
    绿色 - 深的:GREEN_DARK         绿色 - 深的 - 半透明:GREEN_DARK_TRANSLUCENT
    绿色 - 浅的:GREEN_LIGHT        绿色 - 浅的 - 半透明:GREEN_LIGHT_TRANSLUCENT
    蓝色:BLUE                      蓝色 - 半透明:BLUE_TRANSLUCENT
    蓝色 - 深的:BLUE_DARK          蓝色 - 深的 - 半透明:BLUE_DARK_TRANSLUCENT
    蓝色 - 浅的:BLUE_LIGHT         蓝色 - 浅的 - 半透明:BLUE_LIGHT_TRANSLUCENT
    天蓝:SKYBLUE                   天蓝 - 半透明:SKYBLUE_TRANSLUCENT
    天蓝 - 深的:SKYBLUE_DARK       天蓝 - 深的 - 半透明:SKYBLUE_DARK_TRANSLUCENT
    天蓝 - 浅的:SKYBLUE_LIGHT      天蓝 - 浅的 - 半透明:SKYBLUE_LIGHT_TRANSLUCENT
    灰色:GRAY                      灰色 - 半透明:GRAY_TRANSLUCENT
    灰色 - 深的:GRAY_DARK           灰色 - 深的 - 半透明:GRAY_DARK_TRANSLUCENT
    灰色 - 暗的:GRAY_DIM            灰色 - 暗的 - 半透明:GRAY_DIM_TRANSLUCENT
    灰色 - 浅的:GRAY_LIGHT          灰色 - 浅的 - 半透明:GRAY_LIGHT_TRANSLUCENT
    橙色:ORANGE                    橙色 - 半透明:ORANGE_TRANSLUCENT
    橙色 - 深的:ORANGE_DARK         橙色 - 深的 - 半透明:ORANGE_DARK_TRANSLUCENT
    橙色 - 浅的:ORANGE_LIGHT        橙色 - 浅的 - 半透明:ORANGE_LIGHT_TRANSLUCENT
    金色:GOLD                      金色 - 半透明:GOLD_TRANSLUCENT
    粉色:PINK                      粉色 - 半透明:PINK_TRANSLUCENT
    紫红色:FUCHSIA                 紫红色 - 半透明:FUCHSIA_TRANSLUCENT
    灰白色:GRAYWHITE               灰白色 - 半透明:GRAYWHITE_TRANSLUCENT
    紫色:PURPLE                    紫色 - 半透明:PURPLE_TRANSLUCENT
    青色:CYAN                      青色 - 半透明:CYAN_TRANSLUCENT
    青色 - 深的:CYAN_DARK           青色 - 深的 - 半透明:CYAN_DARK_TRANSLUCENT
    黄色:YELLOW                    黄色 - 半透明:YELLOW_TRANSLUCENT
    黄色 - 浅的:YELLOW_LIGHT       黄色 - 浅的 - 半透明:YELLOW_LIGHT_TRANSLUCENT
    巧克力色:CHOCOLATE             巧克力色 - 半透明:CHOCOLATE_TRANSLUCENT
    番茄色:TOMATO                  番茄色 - 半透明:TOMATO_TRANSLUCENT
    橙红色:ORANGERED               橙红色 - 半透明:ORANGERED_TRANSLUCENT
    银白色:SILVER                  银白色 - 半透明:SILVER_TRANSLUCENT
    高光:HIGHLIGHT                 低光:LOWLIGHT
    
    
    
    
    
2. DataCleanUtils
    可以清楚本应用中的内/外缓存，清除数据库，清除sharedPreference，清除files和清除自定义目录。
    
3. DateTimePickDialogUtil
    时间控件的显示与修改时间

4. DbHelper
    输入框的自动提示
    
5. GsonUtil
    将json映射成bean对象
    
6. MD5Utils
    MD5
    
7. MyDateAndTime
    时间相关方法的封装
    
8. PlaySoundPool
    播放正确与错误的提示音
    
9. PrefUtils
    SharePreference简单封装
    
10. ProgressDialogUtils
    显示与关闭等待提示框
        
11. ToolCommon
    界面操作方法集合
            
12. ToolUtils
    其他补充工具类       
    