/* browseFolder.js
* 该文件定义了BrowseFolder()函数，它将提供一个文件夹选择对话框
* 以供用户实现对系统文件夹选择的功能
* 文件夹选择对话框起始目录由
* Shell.BrowseForFolder(WINDOW_HANDLE, Message, OPTIONS, strPath)函数
* 的strPath参数设置
* 例如：0x11--我的电脑
*   0 --桌面
*  "c:\\"--系统C盘
*
* 用如下代码把该函数应用到一个HTML文件中：
*  <script src="browseFolder.js"></script>
    * 或把下面代码直接COPY到<script language="javascript">...</script>标签中；

* 特别注意的是,由于安全方面的问题,你还需要如下设置才能使本JS代码正确运行,
* 否者会出现"没有权限"的问题.
*
* 1、设置可信任站点（例如本地的可以为：http://localhost）
    * 2、其次：可信任站点安全级别自定义设置中：设置下面的选项
* "对没有标记为安全的ActiveX控件进行初始化和脚本运行"----"启用"

browserFolder.js:
/**/
/***
 path 要显示值的对象id
 ****/
function browseFolder(path) {
    try {
        var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939";  //选择框提示信息
        var Shell = new ActiveXObject("Shell.Application");
        var Folder = Shell.BrowseForFolder(0, Message, 64, 17);//起始目录为：我的电脑
        //var Folder = Shell.BrowseForFolder(0,Message,0); //起始目录为：桌面
        if (Folder != null) {
            Folder = Folder.items();  // 返回 FolderItems 对象
            Folder = Folder.item();  // 返回 Folderitem 对象
            Folder = Folder.Path;   // 返回路径
            if (Folder.charAt(Folder.length - 1) != "\\") {
                Folder = Folder + "\\";
            }
            document.getElementById(path).value = Folder;
            return Folder;
        }
    }
    catch (e) {
        alert(e.message);
    }
}